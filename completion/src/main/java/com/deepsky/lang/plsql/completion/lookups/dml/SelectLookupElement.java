/*
 * Copyright (c) 2009,2014 Serhiy Kulyk
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *      1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
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

package com.deepsky.lang.plsql.completion.lookups.dml;

import com.deepsky.lang.plsql.completion.lookups.LookupUtils;
import com.deepsky.lang.plsql.completion.lookups.TableLookupElement;
import com.deepsky.view.Icons;
import com.intellij.codeInsight.CodeInsightSettings;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.completion.impl.CompletionServiceImpl;
import com.intellij.codeInsight.editorActions.CompletionAutoPopupHandler;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElementDecorator;
import com.intellij.ide.PowerSaveMode;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.util.Alarm;


public class SelectLookupElement<T extends LookupElement> extends LookupElementDecorator<T> {


    private final Alarm myAlarm = new Alarm();

    protected SelectLookupElement(T delegate) {
        super(delegate);
    }

    public static SelectLookupElement create() {
        LookupElement e = LookupElementBuilder.create("select")//it.getName())
//                .withTailText(it.getTail(), true)
//                .withTypeText(it.getType())
//                .withIcon(it.getIcon())
                .withPresentableText("select .. from <table>")
                .withCaseSensitivity(false)
                .withStrikeoutness(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "select *\nfrom "; //it.getQualifyName(); //forceUsingTableAlias);
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);

//                        LookupElement item = TableLookupElement.create("", Icons.TABLE);
                        final Document document = editor.getDocument();
//                        final String lookupString = item.getLookupString();
//
//                        document.insertString(context.getTailOffset(), lookupString);
                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);

//                        int startOffset = context.getStartOffset();
//                        CodeStyleManager.getInstance(context.getProject()).reformatText(context.getFile(),
//                                startOffset,
//                                startOffset + prefix.length() + 1);

                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                });

        return new SelectLookupElement<LookupElement>(e);
    }


    public static SelectLookupElement createSelectFromSelect() {
        LookupElement e = LookupElementBuilder.create("select")//it.getName())
//                .withTailText(it.getTail(), true)
//                .withTypeText(it.getType())
//                .withIcon(it.getIcon())
                .withPresentableText("select .. from (select .. from <table>)")
                .withCaseSensitivity(false)
                .withStrikeoutness(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "select *\nfrom (select * from  );"; //it.getQualifyName(); //forceUsingTableAlias);
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);

//                        LookupElement item = TableLookupElement.create("", Icons.TABLE);
                        final Document document = editor.getDocument();
//                        final String lookupString = item.getLookupString();
//
//                        document.insertString(context.getTailOffset(), lookupString);
                        editor.getCaretModel().moveToOffset(context.getTailOffset()-2);
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);

                        int startOffset = context.getStartOffset();
                        CodeStyleManager.getInstance(context.getProject()).reformatText(context.getFile(),
                                startOffset,
                                startOffset + prefix.length() + 1);

                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                });

        return new SelectLookupElement<LookupElement>(e);
    }


    public void __handleInsert(InsertionContext context) {

        final Editor editor = context.getEditor();
        String prefix = "select *\nfrom "; //it.getQualifyName(); //forceUsingTableAlias);
        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
//        emulateInsertion(TableLookupElement.create("", Icons.TABLE), context.getTailOffset(), context);

        LookupElement item = TableLookupElement.create("", Icons.TABLE);
        final Document document = editor.getDocument();
        final String lookupString = item.getLookupString();

        document.insertString(context.getTailOffset(), lookupString);
        editor.getCaretModel().moveToOffset(context.getTailOffset());
//        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);


//                setOffsets(context, offset, offset);

        //final Editor editor = context.getEditor();
//        int offset = editor.getCaretModel().getOffset() + prefix.length() + 1;
//        final Document document = editor.getDocument();
//        LookupElement item = TableLookupElement.create("", Icons.TABLE);
//        final String lookupString = item.getLookupString();
//
//        document.insertString(offset, lookupString);
//        editor.getCaretModel().moveToOffset(context.getTailOffset());

/*
There is no fixed set of events that can trigger parsing. The parsing will occur every time someone
has called PsiDocumentManager.commitDocument(). Parsing for autopopup code completion is performed
in a background thread and should not affect editor responsiveness.
*/
        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);

/*
        int startOffset = context.getStartOffset();
        CodeStyleManager.getInstance(context.getProject()).reformatText(context.getFile(),
                startOffset,
                startOffset + prefix.length() + 1);
*/

        scheduleAutoPopup(editor, context);
        //item.handleInsert(context);

    }


/*
    public void renderElement(LookupElementPresentation presentation) {
        getDelegate().renderElement(presentation);
//        final String castType = getItemText(presentation, getCastItem());
//        presentation.setItemText("(" + castType + ")" + presentation.getItemText());
        presentation.setTypeText("select ... from <table>");
    }
*/

    public static void emulateInsertion(LookupElement item, int offset, InsertionContext context) {
        final Editor editor = context.getEditor();
        final Document document = editor.getDocument();
        final String lookupString = item.getLookupString();

        document.insertString(offset, lookupString);
        editor.getCaretModel().moveToOffset(context.getTailOffset() + 1);
        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
    }


    public void scheduleAutoPopup(final Editor editor, //@Nullable final Condition<PsiFile> condition,
                                  final InsertionContext context) {
        if (ApplicationManager.getApplication().isUnitTestMode() && !CompletionAutoPopupHandler.ourTestingAutopopup) {
            return;
        }

        if (!CodeInsightSettings.getInstance().AUTO_POPUP_COMPLETION_LOOKUP) {
            return;
        }
        if (PowerSaveMode.isEnabled()) {
            return;
        }

        if (!CompletionServiceImpl.isPhase(CompletionPhase.CommittingDocuments.class, CompletionPhase.NoCompletion.getClass())) {
            return;
        }

        final CompletionProgressIndicator currentCompletion = CompletionServiceImpl.getCompletionService().getCurrentCompletion();
        if (currentCompletion != null) {
            currentCompletion.closeAndFinish(true);
        }

        final CompletionPhase.CommittingDocuments phase = new CompletionPhase.CommittingDocuments(null, editor);
        CompletionServiceImpl.setCompletionPhase(phase);

        Runnable request = new Runnable() {
            @Override
            public void run() {
                if (context.getProject().isDisposed()) return;
                CompletionAutoPopupHandler.runLaterWithCommitted(context.getProject(), editor.getDocument(), new Runnable() {
                    @Override
                    public void run() {
                        if (phase.checkExpired()) return;

                        PsiFile file = PsiDocumentManager.getInstance(context.getProject()).getPsiFile(editor.getDocument());
                        //if (file != null && condition != null && !condition.value(file)) return;

                        CompletionAutoPopupHandler.invokeCompletion(CompletionType.BASIC, true, context.getProject(), editor, 0, false);
                    }
                });
            }
        };

        context.setLaterRunnable(request);
//        addRequest(request, CodeInsightSettings.getInstance().AUTO_LOOKUP_DELAY);
    }


    private void addRequest(final Runnable request, final int delay) {
        Runnable runnable = new Runnable() {
            public void run() {
                myAlarm.addRequest(request, delay);
            }
        };
        if (ApplicationManager.getApplication().isUnitTestMode()) {
            runnable.run();
        } else {
            ApplicationManager.getApplication().invokeLater(runnable);
        }
    }


    public static LookupElement createSubquery(final boolean finalize) {

        LookupElement e = LookupElementBuilder.create("(select")
//                .withTailText(it.getTail(), true)
//                .withTypeText(it.getType())
//                .withIcon(it.getIcon())
                .withPresentableText("(select .. from <table>)")
                .withCaseSensitivity(false)
                .withStrikeoutness(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "(select * from )" + (finalize?";": "");
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);

                        final Document document = editor.getDocument();
                        editor.getCaretModel().moveToOffset(context.getTailOffset()-(1+(finalize?1:0)));
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);

//                        int startOffset = context.getStartOffset();
//                        CodeStyleManager.getInstance(context.getProject()).reformatText(context.getFile(),
//                                startOffset,
//                                startOffset + prefix.length() + 1);

                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                });

        return new SelectLookupElement<LookupElement>(e);
    }


    public static LookupElement createSubquery() {
        return  SelectLookupElement.createSubquery(false);
    }
}
