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

package com.deepsky.lang.plsql.resolver.factory;

import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.*;
import com.deepsky.lang.plsql.psi.internal.CreateViewColumnDefInternal;
import com.deepsky.lang.plsql.resolver.utils.PsiElementUtil;
import com.deepsky.lang.plsql.sqlIndex.AbstractSchema;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class PlSqlElementLocator {

    String ctxPath;


    public static PsiElement locatePsiElement(Project project, @NotNull DbUrl dbUrl, @NotNull String ctxPath) {
        AbstractSchema sindex = PluginKeys.SQL_INDEX_MAN.getData(project).findOrCreateIndex(dbUrl, 0);
        VirtualFile vfile = sindex.getSourceFile(ctxPath);
        if (vfile != null) {
            String filePath = vfile.getPath();

            if (filePath != null) {
                final FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
                for (VirtualFile v : fileEditorManager.getOpenFiles()) {
                    if (v.getPath().equals(filePath)) {
                        //
                        PsiFile file = PsiManager.getInstance(project).findFile(v);
                        if (file != null) {
                            return new PlSqlElementLocator().locate((PlSqlFile) file, ctxPath);
                        }
                        return file;
                    }
                }

                VirtualFile vf = sindex.getSourceFile(ctxPath);
                PsiFile file = PsiManager.getInstance(project).findFile(vf);
                if (file != null) {
                    return new PlSqlElementLocator().locate((PlSqlFile) file, ctxPath);
                }
            }
        }

        return null;
    }


    public static boolean openFileInEditor(Project project, @NotNull DbUrl dbUrl, @NotNull String ctxPath) {

        AbstractSchema sindex = PluginKeys.SQL_INDEX_MAN.getData(project).findOrCreateIndex(dbUrl, 0);
        VirtualFile vfile = sindex.getSourceFile(ctxPath);
        if (vfile != null) {
            String filePath = vfile.getPath();

            if (filePath != null) {
                final FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
                for (VirtualFile v : fileEditorManager.getOpenFiles()) {
                    if (v.getPath().equals(filePath)) {
                        //
                        PsiFile file = PsiManager.getInstance(project).findFile(v);
                        if (file != null) {
                            PsiElement psiElem = new PlSqlElementLocator().locate((PlSqlFile) file, ctxPath);
                            int offset = psiElem.getTextOffset();
                            moveToOffset((PlSqlFile) file, offset);
                            return true;
                        }
                        return false;
                    }
                }

                VirtualFile vf = sindex.getSourceFile(ctxPath);
                PsiFile file = PsiManager.getInstance(project).findFile(vf);
                if (file != null) {
                    PsiElement psiElem = new PlSqlElementLocator().locate((PlSqlFile) file, ctxPath);
                    int offset = psiElem.getTextOffset();
                    moveToOffset((PlSqlFile) file, offset);
                    return true;
                }
            }
        }

        return false;
    }


    public static boolean moveToOffset(@NotNull PlSqlFile plSqlFile, int offset) {
        Project project = plSqlFile.getProject();

        final FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        OpenFileDescriptor desc = new OpenFileDescriptor(project, plSqlFile.getVirtualFile(), offset);
        return fileEditorManager.openTextEditor(desc, true) != null;
    }


    public PsiElement locate(PsiFile psiFile, String ctxPath) {
        this.ctxPath = ctxPath;
        PlSqlElementVisitor visitor = new PlSqlElementVisitorInternal();

        try {
            psiFile.accept(visitor);
            // target element not found
            return psiFile;
        } catch (TargetElementFoundException e) {
            return e.getTargetPsi();
        }
    }


    private void checkPsiElement(PlSqlElement element) {
        String ctxPath = element.getCtxPath1().getPath();
        if (ctxPath.equals(this.ctxPath)) {
            throw new TargetElementFoundException(element);
        }
    }

    private class TargetElementFoundException extends Error {
        PsiElement psi;

        public TargetElementFoundException(PsiElement psi) {
            this.psi = psi;
        }

        public PsiElement getTargetPsi() {
            return psi;
        }
    }


    // ---
    private class PlSqlElementVisitorInternal extends PlSqlElementVisitor {
        public void visitElement(PsiElement element) {
            PsiElement child = element.getFirstChild();
            while (child != null) {
                IElementType itype = child.getNode().getElementType();
                if (!PsiElementUtil.toSkip.contains(itype)) {
                    try {
                        child.accept(this);
                    } catch (ValidationException e) {
                        // just skip failed element
                        System.err.println("Cannot process: " + e.getMessage());
                    }
                }
                child = child.getNextSibling();
            }
        }

        public void visitPlSqlFile(PlSqlFile plSqlFile) {
            visitElement(plSqlFile);
        }

        public void visitPackageBody(PackageBody node) {
            super.visitPackageBody(node);
            checkPsiElement(node);
        }

        public void visitPackageSpec(PackageSpec node) {
            super.visitPackageSpec(node);
            checkPsiElement(node);
        }

        public void visitCreateSynonym(CreateSynonym node) {
            checkPsiElement(node);
        }

        public void visitCreateSequence(CreateSequence node) {
            checkPsiElement(node);
        }


        public void visitSelectStatement(SelectStatement node) {
            super.visitSelectStatement(node);
            checkPsiElement(node);
        }

        public void visitUpdateStatement(UpdateStatement node) {
            super.visitUpdateStatement(node);
            checkPsiElement(node);
        }

        public void visitDeleteStatement(DeleteStatement node) {
            super.visitDeleteStatement(node);
            checkPsiElement(node);
        }

        public void visitInsertStatement(InsertStatement node) {
            super.visitInsertStatement(node);
            checkPsiElement(node);
        }

        public void visitMergeStatement(MergeStatement node) {
            super.visitMergeStatement(node);
            checkPsiElement(node);
        }

        public void visitPlSqlBlock(PlSqlBlock node) {
            super.visitPlSqlBlock(node);
            checkPsiElement(node);
        }

        public void visitLoopIndex(LoopIndex loopIndex) {
            checkPsiElement(loopIndex);
        }

        public void visitRecordTypeItem(RecordTypeItem rti) {
            checkPsiElement(rti);
        }

        public void visitObjectTypeDecl(ObjectTypeDecl decl) {
            super.visitObjectTypeDecl(decl);
            checkPsiElement(decl);
        }

        public void visitRecordTypeDecl(RecordTypeDecl rt) {
            super.visitRecordTypeDecl(rt);
            checkPsiElement(rt);
        }

        public void visitTableCollectionDecl(TableCollectionDecl node) {
            checkPsiElement(node);
        }

        public void visitVarrayCollectionDecl(VarrayCollectionDecl node) {
            super.visitVarrayCollectionDecl(node);
            checkPsiElement(node);
        }

        public void visitRefCursorDecl(RefCursorDecl node) {
            checkPsiElement(node);
        }

        public void visitVariableDecl(VariableDecl node) {
            checkPsiElement(node);
        }

        public void visitArgument(Argument node) {
            checkPsiElement(node);
        }

        public void visitTableDefinition(TableDefinition node) {
            super.visitTableDefinition(node);
            checkPsiElement(node);
        }

        public void visitColumnDefinition(ColumnDefinition node) {
            checkPsiElement(node);
        }

        public void visitLoopStatement(LoopStatement statement) {
            super.visitLoopStatement(statement);
            checkPsiElement(statement);
        }

        public void visitFunction(Function node) {
            checkPsiElement(node);
            super.visitFunction(node);
        }

        public void visitFunctionSpec(FunctionSpec node) {
            checkPsiElement(node);
            super.visitFunctionSpec(node);
        }

        public void visitProcedure(Procedure node) {
            checkPsiElement(node);
            super.visitProcedure(node);
        }

        public void visitProcedureSpec(ProcedureSpec node) {
            checkPsiElement(node);
            super.visitProcedureSpec(node);
        }

        public void visitCreateTriggerGeneric(CreateTriggerGeneric trigger) {
            checkPsiElement(trigger);
            super.visitCreateTriggerGeneric(trigger);
        }

        public void visitCreateTriggerDML(CreateTriggerDML trigger) {
            checkPsiElement(trigger);
            super.visitCreateTriggerDML(trigger);
        }

        public void visitCursorDecl(CursorDecl node) {
            super.visitCursorDecl(node);
            checkPsiElement(node);
        }

        public void visitCreateView(CreateView view) {
            super.visitCreateView(view);
            checkPsiElement(view);
        }


        public void visitCreateViewColumnDefInternal(CreateViewColumnDefInternal node) {
            checkPsiElement(node);
        }

        public void visitSubquery(Subquery node) {
            super.visitSubquery(node);
            checkPsiElement(node);
        }

//        public void visitTable(Table node) {
//            String ctxPath = node.getCtxPath1().getPath();
//            if(ctxPath.equals(PlSqlElementLocator.this.ctxPath)){
//                if(node.get)
//                throw new TargetElementFoundException(element);
//            }
//
//            String p = node.getCtxPath1().getPath();
//        }

    }


}
