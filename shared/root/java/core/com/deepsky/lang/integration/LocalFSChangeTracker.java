package com.deepsky.lang.integration;

import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.plsql.indexMan.DbTypeChangeListener;
import com.deepsky.lang.plsql.indexMan.FSIndexer;
import com.deepsky.lang.plsql.indexMan.IndexBulkChangeListener;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.deepsky.lang.plsql.sqlIndex.WordIndexChangeListener;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.deepsky.utils.StringUtils;
import com.intellij.ProjectTopics;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.ModuleListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.*;
import com.intellij.openapi.roots.impl.DirectoryIndexExcludePolicy;
import com.intellij.openapi.vfs.*;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.util.messages.MessageBus;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LocalFSChangeTracker {

    private final Logger log = Logger.getInstance("#LocalFSChangeTracker");

    Project project;
    FSIndexer fsIndexer;
    DirectoryIndexExcludePolicy[] excludePolicies;

    FSTrackHelper helper = new FSTrackHelper();

    public LocalFSChangeTracker(@NotNull Project project, @NotNull FSIndexer fsIndexer) {
        this.project = project;
        this.fsIndexer = fsIndexer;

        VirtualFileManager man = VirtualFileManager.getInstance();
        man.addVirtualFileListener(new VirtualFileListenerImpl(), project);

        MessageBus bus = project.getMessageBus();
        bus.connect().subscribe(ProjectTopics.MODULES, new ModuleListenerImpl());
        bus.connect().subscribe(ProjectTopics.PROJECT_ROOTS, new ModuleRootListenerImpl());

        excludePolicies = Extensions.getExtensions(DirectoryIndexExcludePolicy.EP_NAME, project);
    }

    public void start() {
        ApplicationManager.getApplication().runWriteAction(new Runnable() {
            public void run() {
                if (!project.isDisposed()) {
                    processInternal();
                }
            }
        });
    }

    private synchronized void processInternal() {
        log.info("#START SCAN ROOT DIR");

        final Set<String> typesBeingUpdated = new HashSet<String>();
        final MessageBus bus1 = project.getMessageBus();
        Module[] modules = ModuleManager.getInstance(project).getModules();

        helper.process(convert(modules), new LocalFileProcessor() {
            public void process(VirtualFile virtualFile, int command) {
                if (command == LocalFileProcessor.ADD_TO_INDEX) {
                    long ts = fsIndexer.getFileTimestamp(virtualFile.getPath());
                    if (ts != virtualFile.getTimeStamp()) {
                        log.info("#INDEX FILE: " + virtualFile.getPath());
                        Set<String> typesBeenAdded = indexFile(virtualFile);
                        typesBeingUpdated.addAll(typesBeenAdded);
                        bus1.syncPublisher(WordIndexChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, virtualFile.getPath());
                    }
                } else if (command == LocalFileProcessor.REMOVE_FROM_INDEX) {
                    Set<String> typesBeenDeleted = fsIndexer.deleteFile(virtualFile.getPath());
                    typesBeingUpdated.addAll(typesBeenDeleted);
                    String[] ttypes = typesBeenDeleted.toArray(new String[typesBeenDeleted.size()]);
                    bus1.syncPublisher(WordIndexChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, virtualFile.getPath());
                    log.info("#DELETE FILE FROM INDEX: " + virtualFile.getName() + " types: " + Arrays.toString(ttypes));
                }
            }
        });

        if (typesBeingUpdated.size() > 0) {
            bus1.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(
                    IndexManager.FS_URL, typesBeingUpdated.toArray(new String[typesBeingUpdated.size()])
            );
        }
        log.info("#SCAN ROOT DIR COMPLETE");
    }

    private Set<String> indexFile(VirtualFile virtualFile) {
        final Set<String> typesBeingUpdated = new HashSet<String>();
        final DbTypeChangeListener listener = new DbTypeChangeListener() {
            public void handleUpdatedTypes(String[] types) {
                typesBeingUpdated.addAll(Arrays.asList(types));
            }
        };

        String filePath = virtualFile.getPath();
        try {
            long ms0 = System.currentTimeMillis();

            String content = StringUtils.file2string(new File(filePath));
            MarkupGeneratorEx2 generator = new MarkupGeneratorEx2(virtualFile);
            ASTNode root = generator.parse(content);

            long ms1 = System.currentTimeMillis();

            if (root == null) {
                log.info("ERROR [File] " + filePath + " Could not parse");
            } else {
                int cacheSizeBefore = 0; //itree.getEntriesCount();
                fsIndexer.indexPlSqlFile((PlSqlElement) root.getPsi(), listener);
                int sizeAfter = 0; //itree.getEntriesCount();
                int added = sizeAfter - cacheSizeBefore;

                long ms2 = System.currentTimeMillis();
                log.debug("[Indexing] time (ms): " + (ms2 - ms1) + " \t[Parsing] time (ms): " + (ms1 - ms0) + " \t[Indexes] " + added + "(" + sizeAfter + ")" + "\t[File] " + filePath);
            }
        } catch (Throwable e) {
            fsIndexer.setFileTimestamp(filePath, virtualFile.getTimeStamp(), virtualFile.getModificationStamp());
            log.info("ERROR [File] " + filePath + " Could not build index");
        }

        return typesBeingUpdated;
    }


    private void indexFileWithNotification(VirtualFile virtualFile) {
        Set<String> typesBeingAdded = indexFile(virtualFile);
        String[] ttypes = typesBeingAdded.toArray(new String[typesBeingAdded.size()]);
        MessageBus bus1 = project.getMessageBus();
        if (typesBeingAdded.size() > 0) {
            bus1.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, ttypes);
        }
        bus1.syncPublisher(WordIndexChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, virtualFile.getPath());
    }

    private class ModuleRootListenerImpl implements ModuleRootListener {

        public void beforeRootsChange(ModuleRootEvent event) {
        }

        public void rootsChanged(ModuleRootEvent event) {
            processInternal();
        }
    }

    private Set<TrivialContentEntry> convert(Module[] modules) {
        Set<TrivialContentEntry> out = new HashSet<TrivialContentEntry>();
        for (Module module : modules) {
            ContentEntry[] entries = ModuleRootManager.getInstance(module).getContentEntries();
            for (ContentEntry contentEntry : entries) {
                VirtualFile contentRoot = contentEntry.getFile();
                if (contentRoot != null) {
                    TrivialContentEntry trivial = new TrivialContentEntry(contentRoot);
                    for (ExcludeFolder e : contentEntry.getExcludeFolders()) {
                        if (e.getFile() != null) {
                            trivial.addExcludeFolder(e.getFile());
                        }
                    }

                    for (DirectoryIndexExcludePolicy policy : excludePolicies) {
                        for (VirtualFile f : policy.getExcludeRootsForProject()) {
                            trivial.addExcludeFolder(f);
                        }
                    }
                    out.add(trivial);
                }
            }
        }
        return out;
    }

    private class ModuleListenerImpl implements ModuleListener {

        public void moduleAdded(Project project, Module module) {
            // todo -- implement me
        }

        public void beforeModuleRemoved(Project project, Module module) {
            // todo -- implement me
        }

        public void moduleRemoved(Project project, Module module) {
            // todo -- implement me
        }

        public void modulesRenamed(Project project, List<Module> modules) {
            // todo -- implement me
        }
    }

    private class VirtualFileListenerImpl implements VirtualFileListener {
        public void propertyChanged(VirtualFilePropertyEvent event) {
            log.info("propertyChanged: " + event.getFileName());
            log.info("oldModStamp: " + event.getOldModificationStamp() + " newModStamp: " + event.getNewModificationStamp());
        }

        public void contentsChanged(VirtualFileEvent event) {
            if (helper.isFileValid(event.getFile())) {
                VirtualFile vf = event.getFile();
                long modCount = fsIndexer.getFileModCount(vf.getPath());
                if (modCount == event.getOldModificationStamp()) {
                    fsIndexer.setFileTimestamp(vf.getPath(), vf.getTimeStamp(), vf.getModificationStamp());
                } else {
                    log.info("#REINDEX FILE: " + vf.getPath());
                    indexFileWithNotification(vf);
                }
            }
        }

        public void fileCreated(VirtualFileEvent event) {
            if (helper.isFileValid(event.getFile())) {
                log.info("fileCreated: " + event.getFileName() + " timestamp: " + event.getFile().getTimeStamp() + " count: " + event.getFile().getModificationStamp());
                log.info("#BUILD INDEX FOR FILE: " + event.getFile().getPath());
                indexFileWithNotification(event.getFile());
            }
        }

        public void fileDeleted(VirtualFileEvent event) {
            if (helper.isFileValid(event.getFile())) {
                Set<String> typesBeenDeleted = fsIndexer.deleteFile(event.getFile().getPath());
                String[] ttypes = typesBeenDeleted.toArray(new String[typesBeenDeleted.size()]);
                log.info("fileDeleted: " + event.getFileName() + " count: " + event.getFile().getModificationStamp() + " types: " + Arrays.toString(ttypes));
                MessageBus bus1 = project.getMessageBus();
                if (ttypes.length > 0) {
                    bus1.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, ttypes);
                }
                bus1.syncPublisher(WordIndexChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, event.getFile().getPath());
            }
        }

        public void fileMoved(VirtualFileMoveEvent event) {
            String fileName = event.getFileName();
            String oldParentPath = event.getOldParent().getPath();
            String newParentPath = event.getNewParent().getPath();
            File oldF = new File(new File(oldParentPath), fileName);
            File newF = new File(new File(newParentPath), fileName);

            boolean originValid = helper.isFileValid(oldF);
            boolean targetValid = helper.isFileValid(newF);
            String[] ttypes = new String[0];
            if (!originValid && !targetValid) {
                //
                return;
            } else if (originValid && !targetValid) {
                //  delete origin file
                Set<String> typesBeenDeleted = fsIndexer.deleteFile(oldF.getPath());
                ttypes = typesBeenDeleted.toArray(new String[typesBeenDeleted.size()]);
            } else if (!originValid && targetValid) {
                // add target file
                VirtualFile target = event.getNewParent().findChild(event.getFileName());
                Set<String> typesBeenAdded = indexFile(target);
                ttypes = typesBeenAdded.toArray(new String[typesBeenAdded.size()]);
            } else {
                // rename file
                Set<String> typesInFile = fsIndexer.moveFile(oldF, newF, event.getNewModificationStamp());
                ttypes = typesInFile.toArray(new String[typesInFile.size()]);
                resetCache(newF);
            }

            MessageBus bus1 = project.getMessageBus();
            if (ttypes.length > 0) {
                bus1.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, ttypes);
            }
            bus1.syncPublisher(WordIndexChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, event.getFile().getPath());
        }

        public void fileCopied(VirtualFileCopyEvent event) {
//            log.info("fileCopied: " + event.getFileName());
        }

        public void beforePropertyChange(VirtualFilePropertyEvent event) {
//            log.info("beforePropertyChange: " + event.getFileName());
        }

        public void beforeContentsChange(VirtualFileEvent event) {
//            log.info("beforeContentsChange: " + event.getFileName());
        }

        public void beforeFileDeletion(VirtualFileEvent event) {
//            log.info("beforeFileDeletion: " + event.getFileName());
        }

        public void beforeFileMovement(VirtualFileMoveEvent event) {
//            log.info("beforeFileMovement: " + event.getFileName());
        }
    }

    private void resetCache(File file) {
        URI uri = file.toURI();
        try {
            VirtualFile f = VfsUtil.findFileByURL(uri.toURL());
            final FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
            for (VirtualFile v : fileEditorManager.getOpenFiles()) {
                if (v.getPath().equals(f.getPath())) {
                    //
                    PsiFile psiFile = PsiManager.getInstance(project).findFile(v);
                    if (psiFile != null) {
                        ((PlSqlFile) psiFile).resetCaches();
                    }
                    return;
                }
            }

        } catch (MalformedURLException e) {
            // todo -- report issue
        }
    }


}
