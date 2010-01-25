/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
 *
 * SQL CODE ASSISTANT PLUG-IN FOR INTELLIJ IDEA IS PROVIDED BY SERHIY KULYK
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL SERHIY KULYK BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.deepsky.database;

import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SqlScriptManager {

    static LoggerProxy log = LoggerProxy.getInstance("#SqlScriptManager");

    private static SqlScriptManager scriptManager = new SqlScriptManager();

    Map<String, UIDHelper> fileCache = new HashMap<String, UIDHelper>();
    private String directory = "."; // todo --

    private SqlScriptManager() {
        // todo --
    }

    public static SqlScriptManager getInstance() {
        return scriptManager;
    }


    PlSqlFile findDbObjectDefinition(Project project, DbObject dbo) {

        SqlScriptLocator locator = dbo.getLocator();
        if (locator == null) {
            log.warn("Source locator not found for: " + dbo.getClass().getSimpleName());
            return null;
        }

        PlSqlFile file = null;
        String url = locator.getPresentableUrl();
        final FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        for (VirtualFile v : fileEditorManager.getOpenFiles()) {
            if (v.getPath().equals(url)) {
                file = (PlSqlFile) PsiManager.getInstance(project).findFile(v);
                break;
            }
        }

        if (file == null) {
            VirtualFile vf = locator.getScript();
            file = (PlSqlFile) PsiManager.getInstance(project).findFile(vf);
        }
        return file;
    }


    public static boolean openFileInEditor(Project project, DbObject dbo) {

        SqlScriptLocator locator = dbo.getLocator();
        if (locator == null) {
            log.warn("Source locator not found for: " + dbo.getClass().getSimpleName());
            return false;
        }

        String url = locator.getPresentableUrl();
        final FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        for (VirtualFile v : fileEditorManager.getOpenFiles()) {
            if (v.getPath().equals(url)) {
                //
                fileEditorManager.openFile(v, true);
                return true;
            }
        }

        VirtualFile vf = locator.getScript();
        PsiFile file = PsiManager.getInstance(project).findFile(vf);
        if (file != null) {
            VirtualFile vfile = file.getViewProvider().getVirtualFile();
            fileEditorManager.openFile(vfile, true);
            return true;
        }

        return false;
    }

/*
todo -- "def vs decl" concept for dbobject need be considered
todo -- there is need a mapping facilities Obj DEF -> Obj DECL and vise verse 

    PlSqlFile findDbObjectDeclaration(DbObject dbo) {
        if(ObjectCacheFactory.getObjectCache().findByNameForType())

        Project project = DataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
        String url = dbo.getLocator().getPresentableUrl();

        DbObject root = dbo.getRootContext();
        PlSqlFile file = null;
//        String id = SqlScriptManager.getInstance().getId(dbo);
        final FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        for (VirtualFile v : fileEditorManager.getOpenFiles()) {
            if (v.getPath().equals(url)) {
                file = (PlSqlFile) PsiManager.getInstance(project).findFile(v);
                break;
            }
        }

        if (file == null) {
            VirtualFile vf = findScript(dbo);
            file = (PlSqlFile) PsiManager.getInstance(project).findFile(vf);
        }
        return file;
    }
*/

//    private PlSqlFile lookIntoTheCache(Project project, @NotNull SqlScriptLocator locator){
//        project.getUserData()
//    }


//    class PlSqlFileCache {
//        WeakHashMap weakMap = new WeakHashMap();
//
//        public void addFile()
//    }

    public static PsiElement openFile(Project project, DbObject dbo) {
        SqlScriptLocator locator = dbo.getLocator();
        if (locator == null) {
            log.warn("Source locator not found for: " + dbo.getClass().getSimpleName());
            return null;
        }
        VirtualFile vf = locator.getScript();
        // we do not expect any more files
        return PsiManager.getInstance(project).findFile(vf);
    }

    public static PsiElement openFile(Project project, String user, int type, String name) {
        ObjectCache ocache = ObjectCacheFactory.getObjectCache();
        if (ocache.isReady()) {
            DbObject[] objects = ocache.findByNameForType(user, type, name);
            for (DbObject dbo : objects) {
                SqlScriptLocator locator = dbo.getLocator();
                if (locator == null) {
                    log.warn("Source locator not found for: " + dbo.getClass().getSimpleName());
                    continue;
                }
                VirtualFile vf = locator.getScript();
                // we do not expect any more files
                return PsiManager.getInstance(project).findFile(vf);
            }
        }

        // not found
        return null;
    }


    public static PlSqlElement mapToPsiTree(Project project, DbObject dbo) {

        SqlScriptLocator locator = dbo.getLocator();
        if (locator == null) {
            log.warn("Source locator not found for: " + dbo.getClass().getSimpleName());
            return null;
        }

        PlSqlFile file = null;
        String url = locator.getPresentableUrl();
        final FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        for (VirtualFile v : fileEditorManager.getOpenFiles()) {
            if (v.getPath().equals(url)) {
                file = (PlSqlFile) PsiManager.getInstance(project).findFile(v);
                break;
            }
        }

        if (file == null) {
            VirtualFile vf = locator.getScript();
            if (vf != null) {
                file = (PlSqlFile) PsiManager.getInstance(project).findFile(vf);
            }
        }

        if (file != null) {
            if (dbo instanceof ViewDescriptor) {
                return (PlSqlElement) file.findDeclaration((ViewDescriptor) dbo);
            } else if (dbo instanceof TableDescriptor) {
                return (PlSqlElement) file.findDeclaration((TableDescriptor) dbo);
            } else if (dbo instanceof ColumnDescriptor) {
                return (PlSqlElement) file.findDeclaration((ColumnDescriptor) dbo);
            } else if (dbo instanceof VariableDescriptor) {
                return (PlSqlElement) file.findDeclaration((VariableDescriptor) dbo);
            } else if (dbo instanceof RefCursorTypeDescriptor) {
                return (PlSqlElement) file.findDeclaration((RefCursorTypeDescriptor) dbo);
            } else if (dbo instanceof UserDefinedTypeDescriptor) {
                return (PlSqlElement) file.findDeclaration((UserDefinedTypeDescriptor) dbo);
            } else if (dbo instanceof RecordTypeItemDescriptor) {
                return (PlSqlElement) file.findDeclaration((RecordTypeItemDescriptor) dbo);
            } else if (dbo instanceof PackageBodyDescriptor) {
                return (PlSqlElement) file.findDeclaration((PackageBodyDescriptor) dbo);
            } else if (dbo instanceof PackageDescriptor) {
                return (PlSqlElement) file.findDeclaration((PackageDescriptor) dbo);
            } else if (dbo instanceof ExecutableDescriptor) {
                return (PlSqlElement) file.findDeclaration((ExecutableDescriptor) dbo);
            }
        }

        // not found
        return file;
    }

    public static boolean moveToOffset(@NotNull PlSqlFile plSqlFile, int offset) {
        Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());

        final FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        OpenFileDescriptor desc = new OpenFileDescriptor(project, plSqlFile.getVirtualFile(), offset);
        return fileEditorManager.openTextEditor(desc, true) != null;
    }

    public static PlSqlFile loadFile(@NotNull Project project, File file) {
        VirtualFile f = VirtualFileManager.getInstance().findFileByUrl("file://" + file.toURI().getPath()); //file.toString());
        if (f != null) {
            PsiFile psiFile = PsiManager.getInstance(project).findFile(f);
            return (PlSqlFile) psiFile;
        } else {
            return null;
        }
    }


    class UIDHelper {
        Map<String, Object> attributes = new HashMap<String, Object>();

        Object get(String key) {
            return attributes.get(key);
        }

        void put(String key, Object attr) {
            attributes.put(key, attr);
        }
    }

    @Nullable
    public Object getAttribute(@NotNull VirtualFile vfile, String key) {
        UIDHelper helper = fileCache.get(vfile.getPath());
        if (helper == null) {
            return null;
        }
        return helper.get(key);
    }

    public void setAttribute(@NotNull VirtualFile vfile, String key, Object value) {
        UIDHelper helper = fileCache.get(vfile.getPath());
        if (helper == null) {
            helper = new UIDHelper();
            fileCache.put(vfile.getPath(), helper);
        }
        helper.put(key, value);
    }

}
