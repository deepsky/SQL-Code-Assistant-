package com.deepsky.lang.plsql.tree;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.WhitespacesAndCommentsBinder;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Nullable;


class BaseMarker  implements PsiBuilder.Marker {

    BaseMarker prev;
    BaseMarker next;

    protected final int local;
    public BaseMarker(int local){
        this.local = local;
    }

    public PsiBuilder.Marker precede() {
        return null;
    }

    public void drop() {
    }

    public void rollbackTo() {
    }

    public void done(IElementType type) {
    }

    public void collapse(IElementType type) {
    }

    public void doneBefore(IElementType type, PsiBuilder.Marker before) {
    }

    public void doneBefore(IElementType type, PsiBuilder.Marker before, String errorMessage) {
    }

    public void error(String message) {
    }

    public void errorBefore(String message, PsiBuilder.Marker before) {
    }

    public void setCustomEdgeTokenBinders(@Nullable WhitespacesAndCommentsBinder left, @Nullable WhitespacesAndCommentsBinder right) {
    }
}

