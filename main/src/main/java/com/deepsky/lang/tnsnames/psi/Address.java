package com.deepsky.lang.tnsnames.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public interface Address extends PsiElement {

    @NotNull
    AddressElement[] getElements();

    AddressElement findElementByName(String name);

}
