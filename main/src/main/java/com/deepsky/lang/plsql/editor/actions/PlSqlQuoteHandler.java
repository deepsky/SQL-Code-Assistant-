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

package com.deepsky.lang.plsql.editor.actions;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.intellij.codeInsight.editorActions.QuoteHandler;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.intellij.psi.tree.IElementType;

public class PlSqlQuoteHandler implements QuoteHandler {
    public boolean isClosingQuote(HighlighterIterator iterator, int offset) {
        final IElementType tokenType = iterator.getTokenType();
        if (tokenType == PlSqlTokenTypes.QUOTED_STR || tokenType == PlSqlTokenTypes.DOUBLE_QUOTED_STRING) {
            int start = iterator.getStart();
            int end = iterator.getEnd();
            return end - start >= 1 && offset == end - 1;
        }
        return false;
    }

    public boolean isOpeningQuote(HighlighterIterator iterator, int offset) {
        final IElementType tokenType = iterator.getTokenType();

        if (tokenType == PlSqlTokenTypes.QUOTED_STR || tokenType == PlSqlTokenTypes.DOUBLE_QUOTED_STRING
                || tokenType == PlSqlTokenTypes.BAD_CHAR_LITERAL) {
            int start = iterator.getStart();
            return offset == start;
        }
        return false;
    }

    public boolean hasNonClosedLiteral(Editor editor, HighlighterIterator iterator, int offset) {
        return true;
    }

    public boolean isInsideLiteral(HighlighterIterator iterator) {
        final IElementType tokenType = iterator.getTokenType();
        return tokenType == PlSqlTokenTypes.QUOTED_STR ||  tokenType == PlSqlTokenTypes.DOUBLE_QUOTED_STRING;
    }

}
