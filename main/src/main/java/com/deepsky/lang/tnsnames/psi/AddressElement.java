package com.deepsky.lang.tnsnames.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public interface AddressElement extends PsiElement {

    @NotNull
    String getElementName();

    @NotNull
    String getElementValue();

    @NotNull
    ASTNode getElementNode();

}
