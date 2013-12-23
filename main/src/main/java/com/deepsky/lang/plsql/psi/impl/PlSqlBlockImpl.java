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

package com.deepsky.lang.plsql.psi.impl;

import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PlSqlBlockImpl extends PlSqlElementBase implements PlSqlBlock {

    public PlSqlBlockImpl(ASTNode astNode) {
        super(astNode);
    }

//    public Statement[] getStatementList() {
//        final ASTNode[] alternatives = getNode().getChildren(PLSqlTypesAdopted.STATEMENTS);
//
//        Statement[] statements = new Statement[alternatives.length];
//        for(int i=0; i<alternatives.length; i++){
//            statements[i]  = (Statement) alternatives[i].getPsi();
//        }
//        return statements;
//    }

    public Declaration[] getDeclarations() {
        final ASTNode node = getNode().findChildByType(PLSqlTypesAdopted.DECLARE_LIST);
        if (node != null) {
            return ((DeclarationList) node.getPsi()).getDeclList();
        }
        return new Declaration[0];
    }

    public ExceptionSection getExceptionSection() {
        final ASTNode node = getNode().findChildByType(PLSqlTypesAdopted.EXCEPTION_SECTION);

        if (node != null) {
            return (ExceptionSection) node.getPsi();
        } else {
            return null;
        }
    }

    public PlSqlBlock[] getBlockList() {
        final ASTNode[] nodes = getNode().getChildren(PlSqlElementTypes.BLOCKS);

        PlSqlBlock[] blocks = new PlSqlBlock[nodes.length];
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = (PlSqlBlock) nodes[i].getPsi();
        }
        return blocks;
    }

    @NotNull
    public PlSqlElement[] getObjectList() {
        List<PlSqlElement> out = new ArrayList<PlSqlElement>();
        for (PsiElement psi : this.getChildren()) {
            innerLoop(psi, out);
        }

        return out.toArray(new PlSqlElement[out.size()]);
    }


    private void innerLoop(PsiElement element, List<PlSqlElement> l) {
        if (element instanceof PlSqlElement) {
            l.add((PlSqlElement) element);
        } else {
            for (PsiElement psi : element.getChildren()) {
                innerLoop(psi, l);
            }
        }
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitPlSqlBlock(this);
        } else {
            super.accept(visitor);
        }
    }

}
