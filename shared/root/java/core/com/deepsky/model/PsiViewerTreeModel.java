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

package com.deepsky.model;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.deepsky.lang.common.PlSqlProjectComponent;

import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.event.TreeModelListener;
import java.util.List;
import java.util.ArrayList;

public class PsiViewerTreeModel implements TreeModel {

    private PsiElement _root;
    private final PlSqlProjectComponent _projectComponent;

    public PsiViewerTreeModel(PlSqlProjectComponent projectComponent) {
        _projectComponent = projectComponent;
    }

    public Object getRoot() {
        return _root;
    }

    public void setRoot(PsiElement root) {
        _root = root;
    }

    public Object getChild(Object parent, int index) {
        PsiElement psi = (PsiElement) parent;
        List children = getFilteredChildren(psi);
        return children.get(index);
    }

    public int getChildCount(Object parent) {
        PsiElement psi = (PsiElement) parent;
        return getFilteredChildren(psi).size();
    }

    public boolean isLeaf(Object node) {
        PsiElement psi = (PsiElement) node;
        return psi.getChildren().length == 0;
    }

    private List getFilteredChildren(PsiElement psi) {
        PsiElement child[] = psi.getChildren();
        List filteredChildren = new ArrayList(child.length);
        for (int i = 0; i < child.length; i++)
            if (isValid(child[i]))
                filteredChildren.add(child[i]);

        return filteredChildren;
    }

    private boolean isValid(PsiElement psiElement) {
        return !(psiElement instanceof PsiWhiteSpace);
    }

    public int getIndexOfChild(Object parent, Object child) {
        PsiElement psiParent = (PsiElement) parent;
        List psiChildren = getFilteredChildren(psiParent);
        return psiChildren.indexOf(child);
    }

    public void valueForPathChanged(TreePath treepath, Object obj) {
    }

    public synchronized void addTreeModelListener(TreeModelListener treemodellistener) {
    }

    public synchronized void removeTreeModelListener(TreeModelListener treemodellistener) {
    }
}
