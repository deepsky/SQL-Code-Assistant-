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

package com.deepsky.lang.plsql.resolver.psibased;

import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ref.SequenceRef;
import com.deepsky.lang.plsql.psi.ref.TableRef;
import com.deepsky.lang.plsql.resolver.utils.PsiElementUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

public abstract class ReferenceProcessor {

    protected PlSqlElementVisitor visitor;

    public void processTree(PsiElement element) {
        visitor = new PlSqlElementVisitorImpl();
        element.accept(visitor);
    }


    protected abstract void visitObjectReference(ObjectReference node);

    protected abstract void visitTypeNameReference(TypeNameReference node);

    protected abstract void visitFunctionCall(FunctionCall node);

    protected abstract void visitSpecFunctionCall(SpecFunctionCall node);

    protected abstract void visitProcedureCall(ProcedureCall node);

    protected abstract void visitCollectionMethodCall2(CollectionMethodCall2 elem);

    protected abstract void visitCollectionMethodCall(CollectionMethodCall elem);

    protected abstract void visitTableRef(TableRef table);

    protected abstract void visitSequenceRef(SequenceRef node);

    protected abstract void visitColumnNameRef(ColumnNameRef columnNameRef);

    protected abstract void visitColumnSpec(ColumnSpec columnNameRef);

    protected void visitElement(PsiElement element) {
        PsiElement child = element.getFirstChild();
        while (child != null) {
            IElementType itype = child.getNode().getElementType();
            if (!PsiElementUtil.toSkip.contains(itype)) {
                try {
                    child.accept(visitor);
                }catch(Throwable e){
                    System.err.println("Ref Processor: " + e.getMessage());
                }
            }
            child = child.getNextSibling();
        }
    }


    private class PlSqlElementVisitorImpl extends PlSqlElementVisitor {

        public void visitElement(PsiElement element) {
            // makes error handling more flexibile in derived classes
            ReferenceProcessor.this.visitElement(element);
        }


        final public void visitObjectReference(ObjectReference node) {
            node.getCtxPath1();
            ReferenceProcessor.this.visitObjectReference(node);
        }


        final public void visitSequenceRef(SequenceRef node) {
            node.getCtxPath1();
            ReferenceProcessor.this.visitSequenceRef(node);
        }

        final public void visitTypeNameReference(TypeNameReference node) {
            node.getCtxPath1();
            ReferenceProcessor.this.visitTypeNameReference(node);
        }

        final public void visitFunctionCall(FunctionCall node) {
            node.getCtxPath1();
            super.visitFunctionCall(node);
            ReferenceProcessor.this.visitFunctionCall(node);
        }

        final public void visitSpecFunctionCall(SpecFunctionCall node) {
            node.getCtxPath1();
            super.visitSpecFunctionCall(node);
            ReferenceProcessor.this.visitSpecFunctionCall(node);
        }

        final public void visitProcedureCall(ProcedureCall node) {
            node.getCtxPath1();
            super.visitProcedureCall(node);
            ReferenceProcessor.this.visitProcedureCall(node);
        }

        final public void visitCollectionMethodCall2(CollectionMethodCall2 elem) {
            elem.getCtxPath1();
            ReferenceProcessor.this.visitCollectionMethodCall2(elem);
        }

        final public void visitCollectionMethodCall(CollectionMethodCall elem) {
            elem.getCtxPath1();
            ReferenceProcessor.this.visitCollectionMethodCall(elem);
        }

        final public void visitColumnNameRef(ColumnNameRef columnNameRef){
            ReferenceProcessor.this.visitColumnNameRef(columnNameRef);
        }

        final public void visitTableRef(TableRef table) {
            table.getCtxPath1();
            ReferenceProcessor.this.visitTableRef(table);
        }

        final public void visitColumnSpec(ColumnSpec table) {
            table.getCtxPath1();
            ReferenceProcessor.this.visitColumnSpec(table);
        }

        // markup rest of code
        final public void visitFunction(Function node) {
            super.visitFunction(node);
            node.getCtxPath1();
        }

        final public void visitFunctionSpec(FunctionSpec node) {
            super.visitFunctionSpec(node);
            node.getCtxPath1();
        }

        final public void visitProcedure(Procedure node) {
            super.visitProcedure(node);
            node.getCtxPath1();
        }

        final public void visitProcedureSpec(ProcedureSpec node) {
            super.visitProcedureSpec(node);
            node.getCtxPath1();
        }

        final public void visitSubquery(Subquery node) {
            super.visitSubquery(node);
            node.getCtxPath1();
        }

        final public void visitUpdateStatement(UpdateStatement node) {
            super.visitUpdateStatement(node);
            node.getCtxPath1();
        }

        final public void visitSelectStatement(SelectStatement node) {
            super.visitSelectStatement(node);
            node.getCtxPath1();
        }

        final public void visitDeleteStatement(DeleteStatement node) {
            super.visitDeleteStatement(node);
            String e = node.getCtxPath1().getPath();
        }

        final public void visitInsertStatement(InsertStatement node) {
            super.visitInsertStatement(node);
            node.getCtxPath1();
        }

        final public void visitMergeStatement(MergeStatement node) {
            super.visitMergeStatement(node);
            node.getCtxPath1();
        }

        final public void visitLoopStatement(LoopStatement node) {
            super.visitLoopStatement(node);
            node.getCtxPath1();
        }

        final public void visitCursorDecl(CursorDecl node){
            super.visitCursorDecl(node);
            node.getCtxPath1();
        }
    }

}
