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

import com.deepsky.lang.plsql.resolver.ContextPathProvider;
import com.deepsky.lang.plsql.resolver.factory.ContextPathManager;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ASTNodeSubst implements ASTNode, ContextPathProvider {

    Node node;

    public CtxPath getCtxPath1() {
        return ContextPathManager.getCtxPath(this);
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public IElementType getElementType() {
        return node.getElementType();
    }

    public String getText() {
        return node.getText();
    }

    public CharSequence getChars() {
        throw new MethodNotSupportedException("Not supported: getChars");
    }

    public boolean textContains(char c) {
        throw new MethodNotSupportedException("Not supported: textContains");
    }

    public int getStartOffset() {
        throw new MethodNotSupportedException("Not supported: getStartOffset");
    }

    public int getTextLength() {
        throw new MethodNotSupportedException("Not supported: getTextLength");
    }

    public TextRange getTextRange() {
        return new TextRange(node.getRange().getStart(), node.getRange().getEnd());
    }

    public ASTNode getTreeParent() {
        Node parent = node.getParent();
        if (parent != null) {
             return parent.getASTNode();
        }
        return null;
    }

    public ASTNode getFirstChildNode() {
        Node first = node.getFirstChildNode();
        if(first != null){
            return first.getASTNode();
        }
        return null;

/*
        Node[] children = node.getChildren();
        if (children.length > 0) {
            return children[0].getASTNode();
        } else {
            return null;///throw new IllegalAccessException();
        }
*/
    }

    public ASTNode getLastChildNode() {
        throw new MethodNotSupportedException("Not supported: getLastChildNode");
    }

    public ASTNode getTreeNext() {
        Node next = node.getTreeNext();
        if (next != null) {
            return next.getASTNode();
        }

/*
        Node parent = node.getParent();
        if (parent != null) {
            int index = parent.indexOf(node);
            Node next = parent.getChild(index + 1);
            if (next != null) {
                return next.getASTNode();
            }
        }
*/
        return null;
    }

    public ASTNode getTreePrev() {
        throw new MethodNotSupportedException("Not supported: getTreePrev");
    }

    public ASTNode[] getChildren(@Nullable TokenSet filter) {
        Node[] children = node.getChildren(filter);
        ASTNode[] out = new ASTNode[children.length];
        for (int i = 0; i < children.length; i++) {
            out[i] = children[i].getASTNode();
        }

        return out;
    }

    public void addChild(@NotNull ASTNode child) {
        throw new MethodNotSupportedException("Not supported: addChild");
    }

    public void addChild(@NotNull ASTNode child, ASTNode anchorBefore) {
        throw new MethodNotSupportedException("Not supported: addChild");
    }

    public void addLeaf(@NotNull IElementType leafType, CharSequence leafText, ASTNode anchorBefore) {
        throw new MethodNotSupportedException("Not supported: addLeaf");
    }

    public void removeChild(@NotNull ASTNode child) {
        throw new MethodNotSupportedException("Not supported: removeChild");
    }

    public void removeRange(@NotNull ASTNode firstNodeToRemove, ASTNode firstNodeToKeep) {
        throw new MethodNotSupportedException("Not supported: removeRange");
    }

    public void replaceChild(@NotNull ASTNode oldChild, @NotNull ASTNode newChild) {
        throw new MethodNotSupportedException("Not supported: replaceChild");
    }

    public void replaceAllChildrenToChildrenOf(ASTNode anotherParent) {
        throw new MethodNotSupportedException("Not supported: replaceAllChildrenToChildrenOf");
    }

    public void addChildren(ASTNode firstChild, ASTNode firstChildToNotAdd, ASTNode anchorBefore) {
        throw new MethodNotSupportedException("Not supported: addChildren");
    }

    public ASTNode copyElement() {
        throw new MethodNotSupportedException("Not supported: copyElement");
    }

    public ASTNode findLeafElementAt(int offset) {
        throw new MethodNotSupportedException("Not supported: findLeafElementAt");
    }

    public <T> T getCopyableUserData(Key<T> key) {
        throw new MethodNotSupportedException("Not supported: getCopyableUserData");
    }

    public <T> void putCopyableUserData(Key<T> key, T value) {
        throw new MethodNotSupportedException("Not supported: putCopyableUserData");
    }

    public ASTNode findChildByType(IElementType type) {
            Node[] node2 = node.findChildrenByType(type);
            if (node2.length > 0) {
                return node2[0].getASTNode();
            } else {
                return null;
            }
    }

    public ASTNode findChildByType(@NotNull TokenSet typesSet) {
            // args[0] instanceof TokenSet
            Node[] node2 = node.findChildrenByTypes(typesSet);
            if (node2.length == 1) {
                return node2[0].getASTNode();
            } else {
                return null;
            }
    }

    public ASTNode findChildByType(@NotNull TokenSet typesSet, @Nullable ASTNode anchor) {
        throw new MethodNotSupportedException("Not supported: findChildByType");
    }

    public PsiElement getPsi() {
        return node.getPsi();
    }

    public <T extends PsiElement> T getPsi(Class<T> clazz) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public <T> T getUserData(@NotNull Key<T> key) {
        throw new MethodNotSupportedException("Not supported: getUserData");
    }

    public <T> void putUserData(@NotNull Key<T> key, @Nullable T value) {
        throw new MethodNotSupportedException("Not supported: putUserData");
    }

    public Object clone(){
        throw new MethodNotSupportedException("Not supported: clone");
    }

    public String toString(){
        return node.getElementType().toString();
    }
}
