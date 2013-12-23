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

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class LightPsiLeafElement extends LightLeafElement implements PsiElement {

    public LightPsiLeafElement(IElementType type, CharSequence text) {
        super(type, text);
    }

    public PsiElement getPsi(){
        return this;
    }

    @NotNull
    public Project getProject() throws PsiInvalidElementAccessException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public Language getLanguage() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiManager getManager() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public PsiElement[] getChildren() {
        ASTNode[] children = getChildren(null);
        PsiElement[] out = new PsiElement[children!=null? children.length: 0];
        for(int i=0; i<out.length; i++){
            out[i] = children[i].getPsi();
        }

        return out;//(PsiElement[]) getChildren(null); //new PsiElement[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement getParent() {
        ASTNode node = getTreeParent();
        return node != null? node.getPsi(): null;
    }

    public PsiElement getFirstChild() {
        ASTNode node = getFirstChildNode();
        return node != null? node.getPsi(): null;
    }

    public PsiElement getLastChild() {
        ASTNode node = getLastChildNode();
        return node != null? node.getPsi(): null;
    }

    public PsiElement getNextSibling() {
        ASTNode node = getTreeNext();
        return node != null? node.getPsi(): null;
    }

    public PsiElement getPrevSibling() {
        ASTNode node = getTreePrev();
        return node != null? node.getPsi(): null;
    }

    public PsiFile getContainingFile() throws PsiInvalidElementAccessException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

/*
    public int getStartOffsetInParent() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
*/

    public PsiElement findElementAt(int offset) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiReference findReferenceAt(int offset) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getTextOffset() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement getNavigationElement() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement getOriginalElement() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean textMatches(@NotNull CharSequence text) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean textMatches(@NotNull PsiElement element) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        visitor.visitElement(this);
    }

    public void acceptChildren(@NotNull PsiElementVisitor visitor) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement copy() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement add(@NotNull PsiElement element) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement addBefore(@NotNull PsiElement element, PsiElement anchor) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement addAfter(@NotNull PsiElement element, PsiElement anchor) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void checkAdd(@NotNull PsiElement element) throws IncorrectOperationException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement addRange(PsiElement first, PsiElement last) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement addRangeBefore(@NotNull PsiElement first, @NotNull PsiElement last, PsiElement anchor) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement addRangeAfter(PsiElement first, PsiElement last, PsiElement anchor) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void delete() throws IncorrectOperationException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void checkDelete() throws IncorrectOperationException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deleteChildRange(PsiElement first, PsiElement last) throws IncorrectOperationException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement replace(@NotNull PsiElement newElement) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isValid() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isWritable() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiReference getReference() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public PsiReference[] getReferences() {
        return new PsiReference[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, @Nullable PsiElement lastParent, @NotNull PsiElement place) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement getContext() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isPhysical() {
        return true;
    }

    @NotNull
    public GlobalSearchScope getResolveScope() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public SearchScope getUseScope() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ASTNode getNode() {
        return this;
    }

    public boolean isEquivalentTo(PsiElement another) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Icon getIcon(int flags) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
