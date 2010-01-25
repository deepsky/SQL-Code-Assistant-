package com.deepsky.lang.common;

import com.intellij.lang.Commenter;
import org.jetbrains.annotations.Nullable;

public class PlSqlCommenter implements Commenter {
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
}
