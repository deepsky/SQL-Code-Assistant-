package com.deepsky.lang.common;

import com.deepsky.integration.lexer.generated.PlSqlBaseTokenTypes;
import com.intellij.lang.CodeDocumentationAwareCommenter;
import com.intellij.lang.Commenter;
import com.intellij.psi.PsiComment;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Nullable;

public class PlSqlCommenter implements CodeDocumentationAwareCommenter {
    @Nullable
    public String getLineCommentPrefix() {
        return "--";
    }

    public boolean isLineCommentPrefixOnZeroColumn() {
        return false;
    }

    @Nullable
    public String getBlockCommentPrefix() {
        return "/*";
    }

    @Nullable
    public String getBlockCommentSuffix() {
        return "*/";
    }

    public String getCommentedBlockCommentPrefix() {
        // todo --
        return null;
    }

    public String getCommentedBlockCommentSuffix() {
        // todo --
        return null;
    }

    @Nullable
    @Override
    public IElementType getLineCommentTokenType() {
        return PlSqlBaseTokenTypes.SL_COMMENT;
    }

    @Nullable
    @Override
    public IElementType getBlockCommentTokenType() {
        return PlSqlBaseTokenTypes.ML_COMMENT;
    }

    @Nullable
    @Override
    public IElementType getDocumentationCommentTokenType() {
        return null;
    }

    @Nullable
    @Override
    public String getDocumentationCommentPrefix() {
        return null;
    }

    @Nullable
    @Override
    public String getDocumentationCommentLinePrefix() {
        return null;
    }

    @Nullable
    @Override
    public String getDocumentationCommentSuffix() {
        return null;
    }

    @Override
    public boolean isDocumentationComment(PsiComment element) {
        return false;
    }
}
