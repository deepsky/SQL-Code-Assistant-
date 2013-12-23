package com.deepsky.lang.tnsnames.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public interface NetServiceDesc extends PsiElement {

    @NotNull
    String getNetworkAlias();

    @NotNull
    Address[] getAddressInfos();

    @NotNull
    String getBody();
}
