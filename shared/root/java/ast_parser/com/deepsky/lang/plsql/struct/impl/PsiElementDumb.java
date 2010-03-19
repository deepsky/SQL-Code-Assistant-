package com.deepsky.lang.plsql.struct.impl;

import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.lang.plsql.processors.SQLGenerator;
import com.deepsky.lang.plsql.psi.NameFragmentRef;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.Visitor;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.UsageContext;
import com.deepsky.lang.plsql.psi.resolve.VariantsProcessor777;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.psi.*;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public abstract class PsiElementDumb implements PlSqlElement {


    public String getStrippedText() {
        return null;
    }

    public CtxPath getCtxPath() {
        PsiElement parent = this;
        do {
            parent = parent.getParent();
            if (parent instanceof PlSqlElement) {
                return ((PlSqlElement) parent).getCtxPath();
            }
        } while (parent != null);
        return null;
    }
    
    // todo -----------------------------------
    @NotNull
    public ResolveContext777 resolveContext() throws NameNotResolvedException{
        throw new NameNotResolvedException("Not supported");
    }


    @NotNull
    public ResolveContext777 getResolveContext() throws NameNotResolvedException {
        throw new NameNotResolvedException("Not supported");
    }

    @NotNull
    public NameFragmentRef[] getNamePieces() {
        return new NameFragmentRef[0];
    }

    public int getFragmentIndex(@NotNull NameFragmentRef fragment) {
        return 0;
    }

    @NotNull
    public VariantsProcessor777 createVariantsProcessor(PlSqlElement elem) throws NameNotResolvedException {
        return null;
    }
    // -----------------------------------------

    public void process(Visitor proc) {
        throw new NotSupportedException("Class: " + getClass());
    }

    @Nullable
    public String getQuickNavigateInfo(){
        return null;
    }

    @NotNull
    public Project getProject() throws PsiInvalidElementAccessException {
        return null;
    }

    @NotNull
    public Language getLanguage() {
        return null;
    }

    public PsiManager getManager() {
        return null;
    }

    @NotNull
    public PsiElement[] getChildren() {
        return new PsiElement[0];
    }

    public PsiElement getParent() {
        return null;
    }

    @Nullable
    public PsiElement getFirstChild() {
        return null;
    }

    @Nullable
    public PsiElement getLastChild() {
        return null;
    }

    @Nullable
    public PsiElement getNextSibling() {
        return null;
    }

    @Nullable
    public PsiElement getPrevSibling() {
        return null;
    }

    public PsiFile getContainingFile() throws PsiInvalidElementAccessException {
        return null;
    }

    public TextRange getTextRange() {
        return null;
    }

    public int getStartOffsetInParent() {
        return 0;
    }

    public int getTextLength() {
        return 0;
    }

    @Nullable
    public PsiElement findElementAt(int i) {
        return null;
    }

    @Nullable
    public PsiReference findReferenceAt(int i) {
        return null;
    }

    public int getTextOffset() {
        return 0;
    }

    @NonNls
    public String getText() {
        SQLGenerator gen = new SQLGenerator();
        return gen.outToString(this);
    }

    @NotNull
    public char[] textToCharArray() {
        return new char[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement getNavigationElement() {
        return null;
    }

    public PsiElement getOriginalElement() {
        return null;
    }

    public boolean textMatches(@NotNull @NonNls CharSequence charSequence) {
        return false;
    }

    public boolean textMatches(@NotNull PsiElement psiElement) {
        return false;
    }

    public boolean textContains(char c) {
        return false;
    }

    public void accept(@NotNull PsiElementVisitor psiElementVisitor) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void acceptChildren(@NotNull PsiElementVisitor psiElementVisitor) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement copy() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement add(@NotNull PsiElement psiElement) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement addBefore(@NotNull PsiElement psiElement, PsiElement psiElement1) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement addAfter(@NotNull PsiElement psiElement, PsiElement psiElement1) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void checkAdd(@NotNull PsiElement psiElement) throws IncorrectOperationException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement addRange(PsiElement psiElement, PsiElement psiElement1) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement addRangeBefore(@NotNull PsiElement psiElement, @NotNull PsiElement psiElement1, PsiElement psiElement2) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement addRangeAfter(PsiElement psiElement, PsiElement psiElement1, PsiElement psiElement2) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void delete() throws IncorrectOperationException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void checkDelete() throws IncorrectOperationException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deleteChildRange(PsiElement psiElement, PsiElement psiElement1) throws IncorrectOperationException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public PsiElement replace(@NotNull PsiElement psiElement) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isValid() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isWritable() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Nullable
    public PsiReference getReference() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public PsiReference[] getReferences() {
        return new PsiReference[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Nullable
    public <T> T getCopyableUserData(Key<T> tKey) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public <T> void putCopyableUserData(Key<T> tKey, T t) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean processDeclarations(@NotNull PsiScopeProcessor psiScopeProcessor, @NotNull ResolveState resolveState, @Nullable PsiElement psiElement, @NotNull PsiElement psiElement1) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean processDeclarations(@NotNull PsiScopeProcessor psiScopeProcessor, @NotNull PsiSubstitutor psiSubstitutor, @Nullable PsiElement psiElement, @NotNull PsiElement psiElement1) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Nullable
    public PsiElement getContext() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isPhysical() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public GlobalSearchScope getResolveScope() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public SearchScope getUseScope() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Nullable
    public ASTNode getNode() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isEquivalentTo(PsiElement psiElement) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public <T> T getUserData(Key<T> tKey) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public <T> void putUserData(Key<T> tKey, T t) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Icon getIcon(int i) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public String getName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ItemPresentation getPresentation() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public FileStatus getFileStatus() {
        return FileStatus.NOT_CHANGED;
    }

    public void navigate(boolean requestFocus) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean canNavigate() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean canNavigateToSource() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public UsageContext getUsageContext(){
        // todo ---
        return null;
    }

}
