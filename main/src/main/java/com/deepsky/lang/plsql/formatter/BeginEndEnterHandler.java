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

package com.deepsky.lang.plsql.formatter;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.intellij.codeInsight.CodeInsightSettings;
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegateAdapter;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorModificationUtil;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.CodeStyleManager;
import org.jetbrains.annotations.NotNull;

public class BeginEndEnterHandler extends EnterHandlerDelegateAdapter {
    // see org.jetbrains.plugins.groovy.lang.editor.actions.GroovyEnterHandler

    public Result postProcessEnter(@NotNull PsiFile file, @NotNull Editor editor, @NotNull DataContext dataContext) {
        return Result.Continue;
    }


    public Result preprocessEnter(@NotNull PsiFile file,
                                  @NotNull Editor editor,
                                  @NotNull Ref<Integer> caretOffset,
                                  @NotNull Ref<Integer> caretAdvance,
                                  @NotNull DataContext dataContext,
                                  EditorActionHandler originalHandler) {

        String text = editor.getDocument().getText();
        if (StringUtil.isEmpty(text)) {
            return Result.Continue;
        }
        final int caret = editor.getCaretModel().getOffset();
        final EditorHighlighter highlighter = ((EditorEx)editor).getHighlighter();
        if (caret >= 1 && caret < text.length() && CodeInsightSettings.getInstance().SMART_INDENT_ON_ENTER) {

            PsiElement e = file.findElementAt(caret);
            if(e instanceof PsiWhiteSpace && e.getPrevSibling().getNode().getElementType() == PlSqlTokenTypes.KEYWORD_BEGIN){
                    //&& e.getNextSibling().getNode().getElementType() == PlSqlElementTypes.ERROR_TOKEN_A){

                int newOffset = e.getPrevSibling().getTextOffset();
                EditorModificationUtil.insertStringAtCaret(editor, "\n");
                PsiDocumentManager.getInstance(file.getProject()).commitDocument(editor.getDocument());
                CodeStyleManager.getInstance(file.getProject()).adjustLineIndent(file, newOffset);
                return Result.Stop;
            }

//            HighlighterIterator iterator = highlighter.createIterator(caret);
//            iterator.retreat();
//            while (!iterator.atEnd() && TokenType.WHITE_SPACE == iterator.getTokenType()) {
//                iterator.retreat();
//            }
        }

        return Result.Continue;
    }


}
