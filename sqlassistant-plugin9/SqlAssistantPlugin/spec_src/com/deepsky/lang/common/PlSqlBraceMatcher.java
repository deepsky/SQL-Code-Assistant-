package com.deepsky.lang.common;

import com.intellij.lang.PairedBraceMatcher;
import com.intellij.lang.BracePair;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlSqlBraceMatcher  implements PairedBraceMatcher {

    private static final BracePair[] PAIRS = new BracePair[] {
        // todo --- 8.0 !!! new BracePair('(',PlSqlTokenTypes.OPEN_PAREN, ')', PlSqlTokenTypes.CLOSE_PAREN, false)
        new BracePair(PlSqlTokenTypes.OPEN_PAREN, PlSqlTokenTypes.CLOSE_PAREN, false)
    };

  public BracePair[] getPairs() {
    return PAIRS;
  }

    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType iElementType, @Nullable IElementType iElementType1) {
        // TODO
        return false;
    }

    public int getCodeConstructStart(PsiFile psiFile, int i) {
        // todo --- 8.0 !!!
        return 0;
    }
}
