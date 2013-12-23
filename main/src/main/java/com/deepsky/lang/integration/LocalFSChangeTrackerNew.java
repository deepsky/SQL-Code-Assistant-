package com.deepsky.lang.integration;

import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PlSqlProjectComponent;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.indexMan.DbTypeChangeListener;
import com.deepsky.lang.plsql.indexMan.FSIndexer;
import com.deepsky.lang.plsql.indexMan.IndexBulkChangeListener;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.resolver.factory.PlSqlElementLocator;
import com.deepsky.lang.plsql.resolver.index.IndexEntriesWalkerInterruptable;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.deepsky.lang.plsql.sqlIndex.WordIndexChangeListener;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.deepsky.services.GenericThreadService;
import com.deepsky.utils.FileUtils;
import com.deepsky.utils.StringUtils;
import com.intellij.ProjectTopics;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileTypes.FileTypeEvent;
import com.intellij.openapi.fileTypes.FileTypeListener;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.ModuleListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.*;
import com.intellij.openapi.roots.impl.DirectoryIndexExcludePolicy;
import com.intellij.openapi.vfs.*;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.util.Function;
import com.intellij.util.messages.MessageBus;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.*;

public class LocalFSChangeTrackerNew extends GenericThreadService {

    private final Logger log = Logger.getInstance("#LocalFSChangeTracker");

    private Project project;
    private FSIndexer fsIndexer;
    private DirectoryIndexExcludePolicy[] excludePolicies;

    FSContentTracker helper = new FSContentTracker();

    public LocalFSChangeTrackerNew(@NotNull Project project, @NotNull FSIndexer fsIndexer) {
        this.project = project;
        this.fsIndexer = fsIndexer;

        VirtualFileManager man = VirtualFileManager.getInstance();
        man.addVirtualFileListener(new VirtualFileListenerImpl(), project);

        MessageBus bus = project.getMessageBus();
        bus.connect().subscribe(ProjectTopics.MODULES, new ModuleListenerImpl());
        bus.connect().subscribe(ProjectTopics.PROJECT_ROOTS, new ModuleRootListenerImpl());
        bus.connect().subscribe(FileTypeManager.TOPIC, new FileTypeListenerImpl());

        excludePolicies = Extensions.getExtensions(DirectoryIndexExcludePolicy.EP_NAME, project);
    }


    final private LinkedList<FileUpdateRequest> pipeline = new LinkedList<FileUpdateRequest>();


    protected void runInternal() {
        log.info("Idle run ... ");
        final LinkedList<FileUpdateRequest> shortList = new LinkedList<FileUpdateRequest>();

        while (true) {
            synchronized (pipeline) {
                for (int i = 0; i < 50 && pipeline.size() > 0; i++) {
                    shortList.push(pipeline.pop());
                }
            }

            if (shortList.size() == 0) {
                // Nothing to do
                return;
            }

            final Set<String> typesBeingUpdated = new HashSet<String>();
            final MessageBus bus1 = project.getMessageBus();

/*
            ApplicationManager.getApplication().runReadAction(new Runnable() {
                public void run() {
                    for (FileUpdateRequest r : shortList) {
                        if (r.command == LocalFileProcessor.ADD_TO_INDEX) {
                            long ts = fsIndexer.getFileTimestamp(r.file.getPath());
                            if (ts != r.file.getTimeStamp()) {
                                log.info("#INDEX FILE: " + r.file.getPath());
                                Set<String> typesBeenAdded = indexFile(r.file);
                                typesBeingUpdated.addAll(typesBeenAdded);
                                bus1.syncPublisher(WordIndexChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, r.file.getPath());
                            }
                        } else if (r.command == LocalFileProcessor.REMOVE_FROM_INDEX) {
                            Set<String> typesBeenDeleted = fsIndexer.deleteFile(r.file.getPath());
                            typesBeingUpdated.addAll(typesBeenDeleted);
                            String[] ttypes = typesBeenDeleted.toArray(new String[typesBeenDeleted.size()]);
                            bus1.syncPublisher(WordIndexChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, r.file.getPath());
                            log.info("#DELETE FILE FROM INDEX: " + r.file.getName() + " types: " + Arrays.toString(ttypes));
                        }
                    }

                    if (typesBeingUpdated.size() > 0) {
                        bus1.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(
                                IndexManager.FS_URL, typesBeingUpdated.toArray(new String[typesBeingUpdated.size()])
                        );
                    }
                }
            });
*/
            break;
        }
    }

    private class FileUpdateRequest {
        VirtualFile file;
        int command;

        FileUpdateRequest(VirtualFile file, int command) {
            this.file = file;
            this.command = command;
        }
    }

    public void start() {
        ApplicationManager.getApplication().runWriteAction(new Runnable() {
            public void run() {
                if (!project.isDisposed()) {
                    processInternal();
                }
            }
        });

        if (!project.isDisposed()) {
            processInternal();
        }
        super.start();
    }

    public void stop() {
        super.stop();
        //TODO implement me
    }

    private synchronized void processInternal() {
        log.info("#START SCAN ROOT DIR");

//        final Set<String> typesBeingUpdated = new HashSet<String>();
//        final MessageBus bus1 = project.getMessageBus();
        Module[] modules = ModuleManager.getInstance(project).getModules();

        helper.updateContent(1, convert(modules), new LocalFileProcessor() {
            public void process(VirtualFile virtualFile, int command) {

                synchronized (pipeline) {
                    pipeline.push(new FileUpdateRequest(virtualFile, command));
                }
//                if (command == LocalFileProcessor.ADD_TO_INDEX) {
//                    long ts = fsIndexer.getFileTimestamp(virtualFile.getPath());
//                    if (ts != virtualFile.getTimeStamp()) {
//                        log.info("#INDEX FILE: " + virtualFile.getPath());
//                        Set<String> typesBeenAdded = indexFile(virtualFile);
//                        typesBeingUpdated.addAll(typesBeenAdded);
//                        bus1.syncPublisher(WordIndexChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, virtualFile.getPath());
//                    }
//                } else if (command == LocalFileProcessor.REMOVE_FROM_INDEX) {
//                    Set<String> typesBeenDeleted = fsIndexer.deleteFile(virtualFile.getPath());
//                    typesBeingUpdated.addAll(typesBeenDeleted);
//                    String[] ttypes = typesBeenDeleted.toArray(new String[typesBeenDeleted.size()]);
//                    bus1.syncPublisher(WordIndexChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, virtualFile.getPath());
//                    log.info("#DELETE FILE FROM INDEX: " + virtualFile.getName() + " types: " + Arrays.toString(ttypes));
//                }
            }
        });

        tweak();

//        if (typesBeingUpdated.size() > 0) {
//            bus1.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(
//                    IndexManager.FS_URL, typesBeingUpdated.toArray(new String[typesBeingUpdated.size()])
//            );
//        }
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
            long parsingTime = 0;
            // Use content of PsiFile if it is found (it can be found if file is opened in the editor)
            PsiFile psi = PlSqlElementLocator.locatePsiFile(project, virtualFile);
            if (psi != null) {
                if (psi instanceof PlSqlFile) {
                    ((PlSqlFile) psi).resetCaches();
                }
            } else {
                long ms0 = System.currentTimeMillis();
                String content = StringUtils.file2string(new File(filePath));
                MarkupGeneratorEx2 generator = new MarkupGeneratorEx2(virtualFile);
                ASTNode root = generator.parse(content);
                psi = (PsiFile) root.getPsi();
                parsingTime = System.currentTimeMillis() - ms0;
            }

            if (psi == null) {
                log.info("ERROR [File] " + filePath + " Could not parse");
            } else {
                int cacheSizeBefore = 0;
                long ms = System.currentTimeMillis();
                fsIndexer.indexPlSqlFile((PlSqlElement) psi, listener);
                int sizeAfter = 0;
                int added = sizeAfter - cacheSizeBefore;

                long ms2 = System.currentTimeMillis();
                log.debug("[Indexing] time (ms): " + (ms2 - ms) + " \t[Parsing] time (ms): " + parsingTime + " \t[Indexes] " + added + "(" + sizeAfter + ")" + "\t[File] " + filePath);
            }
        } catch (Throwable e) {
            fsIndexer.setFileTimestamp(filePath, virtualFile.getTimeStamp(), virtualFile.getModificationStamp());
            log.info("ERROR [File] " + filePath + " Could not build index");
        }

        return typesBeingUpdated;
    }


    private Set<String> indexFileWithNotification(VirtualFile virtualFile, boolean notifyIndexManager) {
        Set<String> typesBeingAdded = indexFile(virtualFile);
        String[] ttypes = typesBeingAdded.toArray(new String[typesBeingAdded.size()]);
        MessageBus bus1 = project.getMessageBus();
        if (notifyIndexManager && typesBeingAdded.size() > 0) {
            bus1.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, ttypes);
        }
        bus1.syncPublisher(WordIndexChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, virtualFile.getPath());
        return typesBeingAdded;
    }

    private Set<String> deleteIndex(VirtualFile file, boolean notifyIndexManager) {
        return deleteIndex(file.getPath(), notifyIndexManager);
    }

    private Set<String> deleteIndex(String path, boolean notifyIndexManager) {
        Set<String> typesBeenDeleted = fsIndexer.deleteFile(path);
        String[] ttypes = typesBeenDeleted.toArray(new String[typesBeenDeleted.size()]);
        log.info("fileDeleted: " + path + " types: " + Arrays.toString(ttypes));
        MessageBus bus1 = project.getMessageBus();
        if (notifyIndexManager && ttypes.length > 0) {
            bus1.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, ttypes);
        }
        bus1.syncPublisher(WordIndexChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, path);
        return typesBeenDeleted;
    }

    private class ModuleRootListenerImpl implements ModuleRootListener {

        public void beforeRootsChange(ModuleRootEvent event) {
        }

        public void rootsChanged(ModuleRootEvent event) {
            processInternal();
        }
    }

    private Set<MContent> convert(Module[] modules) {
        Set<MContent> out = new HashSet<MContent>();
        for (Module module : modules) {
            ContentEntry[] entries = ModuleRootManager.getInstance(module).getContentEntries();
            for (ContentEntry contentEntry : entries) {
                VirtualFile contentRoot = contentEntry.getFile();
                if (contentRoot != null) {
                    MContent trivial = new MContent(contentRoot);
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

        @Override
        public void modulesRenamed(Project project, List<Module> modules, Function<Module, String> moduleStringFunction) {

        }

        public void modulesRenamed(Project project, List<Module> modules) {
            // todo -- implement me
        }
    }


    private class VirtualFileListenerImpl implements VirtualFileListener {
        public void propertyChanged(VirtualFilePropertyEvent event) {
            log.info("propertyChanged: " + event.getPropertyName());
            if (event.getPropertyName().equals(VirtualFile.PROP_NAME) && event.getParent() != null) {
                VirtualFile vfile = event.getParent();
                if (vfile != null) {
                    // name was changed
                    File parent = new File(vfile.getPath());
                    String oldFileName = (String) event.getOldValue();
                    String newFileName = event.getFile().getPath();

                    if (helper.isFileValid(new File(parent, oldFileName))) {
                        deleteIndex(new File(parent, oldFileName).toString(), true);
                    }
                    if (helper.isFileValid(new File(parent, newFileName))) {
                        indexFileWithNotification(event.getFile(), true);
                    }
                }
            }
        }

        public void contentsChanged(VirtualFileEvent event) {
            if (helper.isFileUnderSourceRoot(event.getFile())) {
                VirtualFile vf = event.getFile();
                long modCount = fsIndexer.getFileModCount(vf.getPath());
                if (modCount == event.getOldModificationStamp()) {
                    fsIndexer.setFileTimestamp(vf.getPath(), vf.getTimeStamp(), vf.getModificationStamp());
                } else {
                    log.info("#REINDEX FILE: " + vf.getPath());
                    indexFileWithNotification(vf, true);
                }
            }
        }

        public void fileCreated(VirtualFileEvent event) {
            if (event.getFile().isDirectory()) {
                // scan directory recursively and add SQL files to index
                final Set<String> updatedTypes = new HashSet<String>();
                FileUtils.processDirectoryTree(event.getFile(), new FileUtils.VirtualFileProcessor() {
                    public void handleEntry(VirtualFile parent, VirtualFile file) {
                        if (helper.isFileUnderSourceRoot(file)) {
                            log.info("fileCreated: " + file.getPath() + " timestamp: " + file.getModificationStamp());
                            updatedTypes.addAll(indexFileWithNotification(file, false));
                        }
                    }
                });
                if (updatedTypes.size() > 0) {
                    MessageBus bus1 = project.getMessageBus();
                    String[] _updatedTypes = updatedTypes.toArray(new String[updatedTypes.size()]);
                    bus1.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, _updatedTypes);
                }

            } else if (helper.isFileUnderSourceRoot(event.getFile())) {
                log.info("fileCreated: " + event.getFileName() + " timestamp: " + event.getFile().getTimeStamp() + " count: " + event.getFile().getModificationStamp());
                indexFileWithNotification(event.getFile(), true);
            }
        }

        public void fileDeleted(VirtualFileEvent event) {
            // Referenced file does not exist any longer
            // fortunately to handle file deletion we need file path only
            if (event.getFile().isDirectory()) {
                // scan directory recursively and delete relevant files from index
                final Set<String> updatedTypes = new HashSet<String>();
                FileUtils.processDirectoryTree(event.getFile(), new FileUtils.VirtualFileProcessor() {
                    public void handleEntry(VirtualFile parent, VirtualFile file) {
                        if (helper.isFileUnderSourceRoot(file)) {
                            updatedTypes.addAll(deleteIndex(file, false));
                        }
                    }
                });
                if (updatedTypes.size() > 0) {
                    MessageBus bus1 = project.getMessageBus();
                    String[] _updatedTypes = updatedTypes.toArray(new String[updatedTypes.size()]);
                    bus1.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, _updatedTypes);
                }
            } else if (helper.isFileUnderSourceRoot(event.getFile())) {
                deleteIndex(event.getFile(), true);
            }
        }


        public void fileMoved(final VirtualFileMoveEvent event) {
            if (event.getFile().isDirectory()) {
                // scan directory recursively and add SQL files to index
                final Set<String> updatedTypes = new HashSet<String>();
                FileUtils.processDirectoryTree(event.getFile(), new FileUtils.VirtualFileProcessor() {
                    public void handleEntry(VirtualFile parent, VirtualFile file) {
                        if (helper.isFileUnderSourceRoot(file)) {
                            log.info("fileMoved: " + file.getPath() + " timestamp: " + file.getModificationStamp());
                            updatedTypes.addAll(indexFileWithNotification(file, false));
                        }
                    }
                });
                if (updatedTypes.size() > 0) {
                    MessageBus bus1 = project.getMessageBus();
                    String[] _updatedTypes = updatedTypes.toArray(new String[updatedTypes.size()]);
                    bus1.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, _updatedTypes);
                }

            } else if (helper.isFileUnderSourceRoot(event.getFile())) {
                log.info("fileMoved: " + event.getFileName() + " timestamp: " + event.getFile().getTimeStamp() + " count: " + event.getFile().getModificationStamp());
                indexFileWithNotification(event.getFile(), true);
            }
        }

        public void fileCopied(VirtualFileCopyEvent event) {
            if (event.getFile().isDirectory()) {
                // scan directory recursively and add SQL files to index
                final Set<String> updatedTypes = new HashSet<String>();
                FileUtils.processDirectoryTree(event.getFile(), new FileUtils.VirtualFileProcessor() {
                    public void handleEntry(VirtualFile parent, VirtualFile file) {
                        if (helper.isFileUnderSourceRoot(file)) {
                            log.info("fileCopied: " + file.getPath() + " timestamp: " + file.getModificationStamp());
//                            log.info("#BUILD INDEX FOR FILE: " + file.getPath());
                            updatedTypes.addAll(indexFileWithNotification(file, false));
                        }
                    }
                });
                if (updatedTypes.size() > 0) {
                    MessageBus bus1 = project.getMessageBus();
                    String[] _updatedTypes = updatedTypes.toArray(new String[updatedTypes.size()]);
                    bus1.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, _updatedTypes);
                }

            } else if (helper.isFileUnderSourceRoot(event.getFile())) {
                log.info("fileCopied: " + event.getFileName() + " timestamp: " + event.getFile().getTimeStamp() + " count: " + event.getFile().getModificationStamp());
//                log.info("#BUILD INDEX FOR FILE: " + event.getFile().getPath());
                indexFileWithNotification(event.getFile(), true);
            }
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
            if (event.getFile().isDirectory()) {
                // scan directory recursively and delete relevant files from index
                final Set<String> updatedTypes = new HashSet<String>();
                FileUtils.processDirectoryTree(event.getFile(), new FileUtils.VirtualFileProcessor() {
                    public void handleEntry(VirtualFile parent, VirtualFile file) {
                        if (helper.isFileUnderSourceRoot(file)) {
                            log.info("beforeFileMovement: " + file + ", delete from index");
                            updatedTypes.addAll(deleteIndex(file, false));
                        }
                    }
                });
                if (updatedTypes.size() > 0) {
                    MessageBus bus1 = project.getMessageBus();
                    String[] _updatedTypes = updatedTypes.toArray(new String[updatedTypes.size()]);
                    bus1.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, _updatedTypes);
                }
            } else if (helper.isFileUnderSourceRoot(event.getFile())) {
                log.info("beforeFileMovement: " + event.getFile() + ", delete from index");
                deleteIndex(event.getFile(), true);
            }
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


    private class FileTypeListenerImpl implements FileTypeListener {
        public void beforeFileTypesChanged(FileTypeEvent event) {
        }

        public void fileTypesChanged(FileTypeEvent event) {
            Set<String> old = PluginKeys.ACTIVE_FILE_PATTERNS.getData(project);
            Set<String> newOnes = PlSqlProjectComponent.getActiveFilePatterns();

            Set<String> intersected = new HashSet<String>(old);
            intersected.retainAll(newOnes);

            final Set<String> beingDeleted = new HashSet<String>(old);
            beingDeleted.removeAll(intersected);

            final Set<String> beingAdded = new HashSet<String>(newOnes);
            beingAdded.removeAll(intersected);

            PluginKeys.ACTIVE_FILE_PATTERNS.putData(newOnes, project);
            if (beingAdded.size() == 0 && beingDeleted.size() == 0) {
                // No changes in SQL PL/SQL file types
                return;
            }

            ApplicationManager.getApplication().runWriteAction(new Runnable() {
                public void run() {
                    final MessageBus bus1 = project.getMessageBus();
                    final Set<String> typesBeingUpdated = new HashSet<String>();
                    if (beingDeleted.size() != 0 && !project.isDisposed()) {
                        final List<String> fileToDelete = new ArrayList<String>();
                        final List<String> filePatterns = convertToPatterns(beingDeleted);
                        IndexTree itree = fsIndexer.getIndex();
                        // Iterate over index and collect file being deleted
                        itree.iterateFileNames(new IndexEntriesWalkerInterruptable() {
                            public boolean process(String ctxPath, String value) {
                                String path = ContextPathUtil.extractFilePath(ctxPath);
                                for (String p : filePatterns) {
                                    if (new File(path).getName().matches(p)) {
                                        fileToDelete.add(path);
                                    }
                                }
                                return true;
                            }
                        });

                        // Do actual file deletion form index
                        typesBeingUpdated.addAll(fsIndexer.deleteFile(fileToDelete));
                        // Notify services
                        for (String f : fileToDelete) {
                            bus1.syncPublisher(WordIndexChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, f);
                            log.info("fileDeleted: " + f);
                        }
                    }

                    if (beingAdded.size() != 0 && !project.isDisposed()) {
                        helper.iterateOverContentEntries(new LocalFileProcessor() {
                            public void process(VirtualFile virtualFile, int command) {
                                if (command == LocalFileProcessor.ADD_TO_INDEX) {
                                    long ts = fsIndexer.getFileTimestamp(virtualFile.getPath());
                                    if (ts != virtualFile.getTimeStamp()) {
                                        log.info("#INDEX FILE: " + virtualFile.getPath());
                                        Set<String> typesBeenAdded = indexFile(virtualFile);
                                        typesBeingUpdated.addAll(typesBeenAdded);
                                        bus1.syncPublisher(WordIndexChangeListener.TOPIC).handleUpdate(IndexManager.FS_URL, virtualFile.getPath());
                                    }
                                }
                            }
                        });
                    }

                    if (typesBeingUpdated.size() > 0) {
                        bus1.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(
                                IndexManager.FS_URL, typesBeingUpdated.toArray(new String[typesBeingUpdated.size()])
                        );
                    }
                }
            });

            System.out.println("FileType changed, added: " + beingAdded.size() + " deleted: " + beingDeleted.size());
        }

        private List<String> convertToPatterns(Set<String> set) {
            List<String> out = new ArrayList<String>(set.size());
            for (String type : set) {
                out.add(type.replace(".", "\\.").replace("?", ".").replace("*", ".*").replace("_", "\\_"));
            }

            return out;
        }
    }
}
