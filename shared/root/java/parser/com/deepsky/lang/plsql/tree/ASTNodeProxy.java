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

package com.deepsky.lang.plsql.tree;

import com.deepsky.lang.common.PlSqlParserDefinition;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ASTNodeProxy implements InvocationHandler {

    Node node;

    private ASTNodeProxy() {
    }

    private void setNode(Node node) {
        this.node = node;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("getText")) {
            return node.getText();
        } else if (method.getName().equals("getTextRange")) {
            return new TextRange(node.getRange().getStart(), node.getRange().getEnd());
        } else if (method.getName().equals("getElementType")) {
            return node.getElementType();
        } else if (method.getName().equals("getPsi")) {
            return node.getPsi();
        } else if (method.getName().equals("getChildren")) {
            TokenSet types = (TokenSet) args[0];
            Node[] children = node.getChildren(types);
            ASTNode[] out = new ASTNode[children.length];
            for (int i = 0; i < children.length; i++) {
                out[i] = children[i].getASTNode();
            }

            return out;
        } else if (method.getName().equals("findChildByType")) {
            if (args[0] instanceof IElementType) {
                Node[] node2 = node.findChildrenByType((IElementType) args[0]);
                if (node2.length > 0) {
                    return node2[0].getASTNode();
                } else {
                    return null;
                }
            } else {
                // args[0] instanceof TokenSet
                Node[] node2 = node.findChildrenByTypes((TokenSet) args[0]);
                if (node2.length == 1) {
                    return node2[0].getASTNode();
                } else {
                    return null;
                }
            }
        } else if (method.getName().equals("getFirstChildNode")) {
            Node[] children = node.getChildren();
            if (children.length > 0) {
                return children[0].getASTNode();
            } else {
                return null;///throw new IllegalAccessException();
            }
        } else if (method.getName().equals("getTreeNext")) {
            Node next = node.getTreeNext();
            if (next != null) {
                return next.getASTNode();
            }
            return null;
/*
            Node parent = node.getParent();
            if (parent != null) {
                int index = parent.indexOf(node);
                Node next = parent.getChild(index + 1);
                if (next != null) {
                    return next.getASTNode();
                }
            }
            return null;
*/
        } else if (method.getName().equals("getTreeParent")) {
            Node parent = node.getParent();
            if (parent != null) {
                return parent.getASTNode();
            }
            return null;
        } else if (method.getName().equals("toString")) {
            return node.getElementType().toString();
        } else {
            System.out.println("Method not implemented: " + method.getName());
            throw new IllegalAccessException("Not supported: " + method.getName());
        }
    }

    public static ASTNode createASTNode(Node node) {
        ASTNodeSubst f = new ASTNodeSubst();
        f.setNode(node);

        node.setASTNode(f);
        return f;
    }


    public static PsiElement createPsiElement(Node node) {
        ASTNode f = node.getASTNode();

        PsiElement psi = createPsiElement(f);
        node.setPsi(psi);
        return psi;
    }


    public static PsiElement createPsiElement(ASTNode f) {
//        final IElementType type = f.getElementType();
//        int ttype = PlSqlElementType.mapTo_TokenType(type);
        PsiElement psi = PlSqlParserDefinition.createElement2(f);

        if (psi == null) {
            psi = new ASTWrapperPsiElementExt(f);
        }
        return psi;
    }


    private static class ASTWrapperPsiElementExt extends ASTWrapperPsiElement {

        public ASTWrapperPsiElementExt(@org.jetbrains.annotations.NotNull ASTNode node) {
            super(node);
        }

        @NotNull
        public PsiElement[] getChildren() {
            PsiElement psiChild = getFirstChild();
            if (psiChild == null) return EMPTY_ARRAY;

            List<PsiElement> result = new ArrayList<PsiElement>();
            while (psiChild != null) {
                // todo -- might need in the performance optimization
                IElementType itype = psiChild.getNode().getElementType();
                if (itype == TokenType.WHITE_SPACE || itype == PlSqlTokenTypes.WS
                        || itype == PlSqlTokenTypes.ML_COMMENT || itype == PlSqlTokenTypes.SL_COMMENT) {
                    // skip symbol processing
                } else {

//                if (psiChild.getNode() instanceof PlSqlElement) {
                    result.add(psiChild);
                }
                psiChild = psiChild.getNextSibling();
            }
            return result.toArray(new PsiElement[result.size()]);
        }
    }
}
