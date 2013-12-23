package com.deepsky.lang.tnsnames.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class AddressElementImpl extends ASTWrapperPsiElement implements AddressElement {
    public AddressElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    public String getElementName() {
        return getNode().getFirstChildNode().getText();
    }

    @NotNull
    public String getElementValue() {
        return getNode().getLastChildNode().getText();
    }

    @NotNull
    public ASTNode getElementNode() {
        return getNode().getLastChildNode();
    }
}
