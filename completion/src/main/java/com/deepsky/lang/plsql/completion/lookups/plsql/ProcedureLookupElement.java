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

package com.deepsky.lang.plsql.completion.lookups.plsql;

import com.deepsky.lang.plsql.completion.lookups.LookupUtils;
import com.deepsky.lang.plsql.completion.lookups.UI.CreateFunction;
import com.deepsky.lang.plsql.completion.lookups.UI.CreateProcedure;
import com.deepsky.lang.plsql.completion.lookups.UI.ParamProviderPopup;
import com.deepsky.lang.plsql.psi.Function;
import com.deepsky.lang.plsql.psi.Procedure;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.view.Icons;
import com.intellij.codeInsight.TailType;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.completion.PrioritizedLookupElement;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElementDecorator;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.codeStyle.CodeStyleManager;


public class ProcedureLookupElement <T extends LookupElement> extends BaseLookupDecorator<T> {

    static LoggerProxy log = LoggerProxy.getInstance("#ProcedureLookupElement");

    protected ProcedureLookupElement(T delegate) {
        super(delegate);
    }


    public static ProcedureLookupElement create() {
        LookupElement e = LookupElementBuilder.create("create procedure")
                .withIcon(Icons.PROCEDURE_BODY)
                .withPresentableText("create procedure <procedure name> is begin .. end;")
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "create or replace procedure proc1\n" +
                                "is\n" +
                                "begin\n" +
                                "NULL;\n" +
                                "end;\n" +
                                "/";

                        insertPrefix(context, editor, prefix);

                    }
                })
                .withStrikeoutness(false);

        return new ProcedureLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 6));
    }


    public static ProcedureLookupElement createOrReplace() {
        LookupElement e = LookupElementBuilder.create("create or replace procedure")
                .withIcon(Icons.PROCEDURE_BODY)
                .withPresentableText("create or replace procedure <procedure name> is begin .. end;")
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "create or replace procedure proc1\n" +
                                "is\n" +
                                "begin\n" +
                                "NULL;\n" +
                                "end;\n" +
                                "/";

                        insertPrefix(context, editor, prefix);

                    }
                })
                .withStrikeoutness(false);

        return new ProcedureLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 6));
    }


    public static ProcedureLookupElement createBody(String text) {
        LookupElement e = LookupElementBuilder.create("procedure")
                .withIcon(Icons.PROCEDURE_BODY)
                .withPresentableText("procedure <procedure name> is begin .. end;")
                .withTypeText(text, false)
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "procedure proc1 \n" +
                                "is\n" +
                                "begin\n" +
                                "NULL;\n" +
                                "end;\n";

                        insertPrefix(context, editor, prefix);

                    }
                })
                .withStrikeoutness(false);

        return new ProcedureLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 6));
    }


    private static void insertPrefix(final InsertionContext context, final Editor editor, String prefix) {

        insertPrefix(context, editor, prefix, Procedure.class, new BaseLookupDecorator.PopupBuilder<Procedure>() {
            @Override
            public ParamProviderPopup createPopup(final Procedure e) {
                 final CreateProcedure f = new CreateProcedure(
                        e.getEName(),
                        e.getPackageName());

                f.addCloseEventLister(new ParamProviderPopup.CloseEventListener() {
                    @Override
                    public void close(final boolean isOk) {
                        ApplicationManager.getApplication().runWriteAction(new Runnable() {
                            public void run() {
                                // User pressed OK, update procedure name
                                if (isOk && !context.getProject().isDisposed()) {
                                    try {
                                        TextRange range = e.getEObjectName().getTextRange();
                                        editor.getDocument().replaceString(range.getStartOffset(), range.getEndOffset(), f.getName());
                                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(editor.getDocument());
                                        editor.getCaretModel().moveToOffset(range.getStartOffset() + f.getName().length());

                                    } catch (Throwable e) {
                                        log.warn(e.getMessage());
                                    }
                                }
                            }
                        });
                    }
                });
                return f;
            }
        });

    }


    public static LookupElement createSpec(String text) {
        // TODO - implement me
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}
