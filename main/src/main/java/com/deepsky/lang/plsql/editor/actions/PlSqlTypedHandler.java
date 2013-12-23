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

import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.PsiUtil;
import com.intellij.codeInsight.AutoPopupController;
import com.intellij.codeInsight.completion.CompletionPhase;
import com.intellij.codeInsight.completion.impl.CompletionServiceImpl;
import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.codeInsight.lookup.LookupManager;
import com.intellij.codeInsight.lookup.impl.LookupImpl;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorModificationUtil;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import org.jetbrains.annotations.NotNull;

/*
 * Complete the following:
 * <p/>
 * ":"      => ":=" <caret>
 * "q'["    => "q'[<caret>]'"      TODO -- implement me
 * "="      => "=>" <caret>        TODO -- implement me
 * "|"      => "||" <caret>
 * <p/>
 * Show auto popup for percentage character
 * <p/>
 * "%"      =>  sql%found
 *      sql%notfound
 *      sql%rowcount
 *      sql%isopen
 *      sql%bulk_rowcount
 *      sql%bulk_exception
 * <p/>
 *       name%type
 *      name%rowtype
 */
public class PlSqlTypedHandler extends TypedHandlerDelegate {

    final static private String CHARS_TO_COMPLETE = ":|";

    @Override
    public Result charTyped(final char c, Project project, Editor editor, @NotNull PsiFile file) {
        if (CHARS_TO_COMPLETE.indexOf(c) == -1 || project == null || !canBeInvoked(editor, project)) {
            return Result.CONTINUE;
        }

        if (!(file instanceof PlSqlFile)) return Result.CONTINUE;

        int caret = editor.getCaretModel().getOffset();
        if (caret < 1) return Result.CONTINUE;

        if (c == ':') {
            // 1. file_location_spec
            // 2. ':' ('new' | 'old')
            // 3. ':='
            // 4. ':' identifier       - bind variable
            PsiElement elementAtCaret = file.findElementAt(caret);
            if (elementAtCaret != null && elementAtCaret.getNode().getElementType() == TokenType.WHITE_SPACE) {
                if (PsiUtil.findAncestor(elementAtCaret, PlSqlElementTypes.PLSQL_BLOCK_PARENTS) != null) {
                    editor.getDocument().insertString(caret, "=");
                    editor.getCaretModel().moveToOffset(caret + 1);
                    return Result.STOP;
                }
            }
        } else if (c == '|') {
            final EditorHighlighter highlighter = ((EditorEx) editor).getHighlighter();
            HighlighterIterator iterator = highlighter.createIterator(caret - 1);
            if(iterator.getTokenType() != PlSqlTokenTypes.CONCAT){
                editor.getDocument().insertString(caret, "|");
                editor.getCaretModel().moveToOffset(caret + 1);
                return Result.STOP;
            }
        }

        return Result.CONTINUE;

//        final EditorHighlighter highlighter = ((EditorEx)editor).getHighlighter();
//        HighlighterIterator iterator = highlighter.createIterator(caret - 1);
///*
//        if (iterator.getTokenType() != GroovyElementTypes.mLCURLY) return Result.CONTINUE;
//        iterator.retreat();
//        if (iterator.atEnd() || iterator.getTokenType() != GroovyElementTypes.mDOLLAR) return Result.CONTINUE;
//        iterator.advance();
//        if (iterator.atEnd()) return Result.CONTINUE;
//        iterator.advance();
//        if (iterator.getTokenType() != GroovyTokenTypes.mGSTRING_BEGIN) return Result.CONTINUE;
//*/
//
//        editor.getDocument().insertString(caret, "=");
//        editor.getCaretModel().moveToOffset(caret+1);
//        return Result.STOP;
    }

    public Result checkAutoPopup(char charTyped, final Project project, final Editor editor, final PsiFile file) {
        if (charTyped != '%') {
            return Result.CONTINUE;
        }

        CompletionPhase oldPhase = CompletionServiceImpl.getCompletionPhase();
        if (oldPhase instanceof CompletionPhase.EmptyAutoPopup && ((CompletionPhase.EmptyAutoPopup) oldPhase).editor != editor) {
            CompletionServiceImpl.setCompletionPhase(CompletionPhase.NoCompletion);
        }


        if (oldPhase instanceof CompletionPhase.CommittingDocuments && ((CompletionPhase.CommittingDocuments) oldPhase).isRestartingCompletion()) {
            return Result.STOP;
        }
//        if (oldPhase instanceof CompletionPhase.CommittingDocuments && ((CompletionPhase.CommittingDocuments) oldPhase).restartCompletion()) {
//            return Result.STOP;
//        }

        LookupImpl lookup = (LookupImpl) LookupManager.getActiveLookup(editor);
        if (lookup != null) {
            if (editor.getSelectionModel().hasSelection()) {
                lookup.performGuardedChange(new Runnable() {
                    public void run() {
                        EditorModificationUtil.deleteSelectedText(editor);
                    }
                });
            }
            return Result.STOP;
        }

        AutoPopupController.getInstance(project).scheduleAutoPopup(editor, null);
        return Result.STOP;
    }


    public static boolean canBeInvoked(final Editor editor, final Project project) {
        if (isReadOnly(editor)) {
            return false;
        }
        if (getPsiFile(editor, project) == null) {
            return false;
        }

        return true;
    }

    public static PsiFile getPsiFile(@NotNull final Editor editor, final Project project) {
        return PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
    }

    public static boolean isReadOnly(@NotNull final Editor editor) {
        if (editor.isViewer()) {
            return true;
        }
        Document document = editor.getDocument();
        return !document.isWritable();
    }

}
