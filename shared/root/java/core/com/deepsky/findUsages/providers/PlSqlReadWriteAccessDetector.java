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

package com.deepsky.findUsages.providers;

import com.deepsky.integration.lexer.generated.PlSqlBaseTokenTypes;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.psi.Argument;
import com.deepsky.lang.plsql.psi.RecordTypeItem;
import com.deepsky.lang.plsql.psi.VariableDecl;
import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.tree.TokenSet;

public class PlSqlReadWriteAccessDetector extends ReadWriteAccessDetector {

    PsiElement element;

    @Override
    public boolean isReadWriteAccessible(PsiElement element) {
        this.element = element;
        return element instanceof VariableDecl
                || element instanceof Argument
                || element instanceof RecordTypeItem;
    }

    @Override
    public boolean isDeclarationWriteAccess(PsiElement element) {
        return false;
    }

    @Override
    public Access getReferenceAccess(PsiElement referencedElement, PsiReference reference) {
        return null;
    }

    @Override
    public Access getExpressionAccess(PsiElement expression) {
        ASTNode parent = expression.getParent().getNode();
        if (parent.getElementType() == PLSqlTypesAdopted.PLSQL_VAR_REF) {
            // variable_ref
            return analysePLSQL_VAR_REF(parent);
        } else if (parent.getElementType() == PLSqlTypesAdopted.VAR_REF) {
            return analyseVAR_REF(parent);
        } else if (parent.getElementType() == PLSqlTypesAdopted.CALLABLE_NAME_REF) {
            ASTNode pp = parent.getTreeParent().getTreeParent();
            if (pp.getElementType() == PLSqlTypesAdopted.COLLECTION_METHOD_CALL) {
                return analysePLSQL_VAR_REF(pp);
            } else if (pp.getElementType() == PLSqlTypesAdopted.ASSIGNMENT_STATEMENT) {
                return isTargetLvalue(pp, parent)? Access.Write: Access.Read;
            } else {
                // todo --
                int h = 0;
            }
        } else {
            // todo --
            int h = 0;
        }
        return null;
    }


    private Access analysePLSQL_VAR_REF(ASTNode node) {
        ASTNode pp = node.getTreeParent();
        // plsql_lvalue
        if (pp.getElementType() == PLSqlTypesAdopted.INSERT_COMMAND) {
            return Access.Read;
        } else if (pp.getElementType() == PLSqlTypesAdopted.FETCH_STATEMENT) {
            return Access.Write;
        } else if (pp.getElementType() == PLSqlTypesAdopted.FORALL_LOOP_SPEC) {
            return Access.Read;
        } else if (pp.getElementType() == PLSqlTypesAdopted.INTO_CLAUSE) {
            return Access.Write;
        } else if (pp.getElementType() == PLSqlTypesAdopted.IMMEDIATE_COMMAND) {
            return Access.Write;
        } else if (pp.getElementType() == PLSqlTypesAdopted.ASSIGNMENT_STATEMENT) {
            ASTNode[] children = pp.getChildren(TokenSet.create(
                    PLSqlTypesAdopted.PLSQL_VAR_REF,
                    PLSqlTypesAdopted.COLLECTION_METHOD_CALL,
                    PlSqlBaseTokenTypes.ASSIGNMENT_EQ));
            if (children.length == 2 && children[1].getElementType() == PlSqlBaseTokenTypes.ASSIGNMENT_EQ) {
                return Access.Write;
            }
        }
        // default
        return Access.Read;
    }


    private Access analyseVAR_REF(ASTNode node) {
        ASTNode pp = node.getTreeParent();

        if (pp.getElementType() == PLSqlTypesAdopted.PLSQL_EXPR_LIST_USING) {
            // keep analysis
            ASTNode[] children = pp.getChildren(TokenSet.create(
                    PLSqlTypesAdopted.VAR_REF,
                    PlSqlBaseTokenTypes.KEYWORD_OUT, PlSqlBaseTokenTypes.KEYWORD_IN));
            for (int i = 0; i < children.length; i++) {
                if (children[i] == node) {
                    if (i > 0 && children[i-1].getElementType() == PlSqlBaseTokenTypes.KEYWORD_OUT) {
                        return Access.Write;
                    }
                    break;
                }
            }
        } else if (pp.getElementType() == PLSqlTypesAdopted.CALL_ARGUMENT) {
            // keep analysis
            // todo -- check whether the formal argument has OUT qualifier
            // todo -- requries enhancing index, no info on that currenlty
        }
        return Access.Read;
    }


    private boolean isTargetLvalue(ASTNode assignment, ASTNode target){
        if(assignment.getElementType() == PLSqlTypesAdopted.ASSIGNMENT_STATEMENT){
            ASTNode t = assignment.getFirstChildNode();
            while(t != null){
                if(t.getElementType() == PlSqlBaseTokenTypes.ASSIGNMENT_EQ){
                    return false;
                } else if(t == target){
                    return true;
                } else {
                    if(findReccursively(t, target)){
                        return true;
                    }
                }

                t = t.getTreeNext();
            }
        }
        return false;
    }


    private boolean findReccursively(ASTNode root, ASTNode elem){
        ASTNode t = root.getFirstChildNode();
        while(t != null){
            if(t == elem){
                return true;
            } else {
                if( findReccursively(t, elem)){
                    return true;
                }
            }

            t = t.getTreeNext();
        }
        return false;
    }
}
