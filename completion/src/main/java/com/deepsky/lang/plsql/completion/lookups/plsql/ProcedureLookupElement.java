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

import com.deepsky.lang.plsql.completion.lookups.UI.CreateOrReplaceProcedure;
import com.deepsky.lang.plsql.completion.lookups.UI.CreateProcedureBody;
import com.deepsky.lang.plsql.completion.lookups.UI.CreateProcedureSpec;
import com.deepsky.lang.plsql.psi.Procedure;
import com.deepsky.lang.plsql.psi.ProcedureSpec;
import com.deepsky.view.Icons;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.completion.PrioritizedLookupElement;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;


public class ProcedureLookupElement<T extends LookupElement> extends BaseLookupDecorator<T> {

    protected ProcedureLookupElement(T delegate) {
        super(delegate);
    }


    public static ProcedureLookupElement create() {
        LookupElement e = LookupElementBuilder.create("create procedure")
                .withIcon(Icons.PROCEDURE_BODY)
                .withPresentableText("Create Procedure")
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(final InsertionContext context, LookupElement item) {
                        String prefix = "create procedure proc1\n" +
                                "is\n" +
                                "begin\n" +
                                "NULL;\n" +
                                "end;\n" +
                                "/";

                        final CreateOrReplaceProcedure f = new CreateOrReplaceProcedure("proc1");
                        __insertPrefix3(context, prefix, f, Procedure.class, new InsertionHandler<Procedure>() {
                            @Override
                            public void handle(Editor editor, Procedure e) {
                                TextRange range = e.getEObjectName().getTextRange();
                                editor.getCaretModel().moveToOffset(range.getEndOffset());
                            }
                        });

//                        insertPrefix2(context, prefix, f, Procedure.class, new InsertionHandler<Procedure>() {
//                            @Override
//                            public void handle(Editor editor, Procedure e) {
//                                int startOffset = e.getTextRange().getStartOffset();
//                                int endOffset = e.getTextRange().getEndOffset();
//
//                                TextRange range = e.getEObjectName().getTextRange();
//                                int cursorOffset = range.getStartOffset() + f.getName().length();
//                                int increment = f.getName().length() - range.getLength();
//                                editor.getDocument().replaceString(range.getStartOffset(), range.getEndOffset(), f.getName());
//
//
//                                if (f.isCreateOrReplace()) {
//                                    // Add "OR REPLACE"
//                                    String procText = editor.getDocument().getText().substring(
//                                            startOffset,
//                                            endOffset + increment);
//                                    Procedure exec = insertOrReplace(procText);
//                                    cursorOffset = startOffset + exec.getEObjectName().getTextRange().getEndOffset();
//                                    editor.getDocument().replaceString(
//                                            startOffset,
//                                            endOffset + increment,
//                                            exec.getText());
//                                }
//
//                                PsiDocumentManager.getInstance(context.getProject()).commitDocument(editor.getDocument());
//                                editor.getCaretModel().moveToOffset(cursorOffset);
//                            }
//                        });
                    }
                }).
                withStrikeoutness(false);

        return new ProcedureLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 6));
    }


    public static ProcedureLookupElement createBody(final String text) {
        LookupElement e = LookupElementBuilder.create("procedure")
                .withIcon(Icons.PROCEDURE_BODY)
                .withPresentableText("Create Procedure Body")
                .withTypeText(text, false)
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(final InsertionContext context, LookupElement item) {
                        String prefix = "procedure proc1 \n" +
                                "is\n" +
                                "begin\n" +
                                "NULL;\n" +
                                "end;\n";

                        final CreateProcedureBody f = new CreateProcedureBody("proc1", text);
                        __insertPrefix3(context, prefix, f, Procedure.class, new InsertionHandler<Procedure>() {
                            @Override
                            public void handle(Editor editor, Procedure e) {
                                TextRange range = e.getEObjectName().getTextRange();
                                editor.getCaretModel().moveToOffset(range.getEndOffset());
                            }
                        });

//                        insertPrefix2(context, prefix, f, Procedure.class, new InsertionHandler<Procedure>() {
//                            @Override
//                            public void handle(Editor editor, Procedure e) {
//                                TextRange range = e.getEObjectName().getTextRange();
//                                int cursorOffset = range.getStartOffset() + f.getName().length();
//                                editor.getDocument().replaceString(range.getStartOffset(), range.getEndOffset(), f.getName());
//                                PsiDocumentManager.getInstance(context.getProject()).commitDocument(editor.getDocument());
//                                editor.getCaretModel().moveToOffset(cursorOffset);
//                            }
//                        });

                    }
                })
                .withStrikeoutness(false);

        return new ProcedureLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 6));
    }


    public static LookupElement createSpec(final String text) {
        LookupElement e = LookupElementBuilder.create("procedure")
                .withIcon(Icons.PROCEDURE_SPEC)
                .withPresentableText("Create Procedure Specification")
                .withTypeText(text, false)
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(final InsertionContext context, LookupElement item) {
                        String prefix = "procedure proc1;";

                        final CreateProcedureSpec f = new CreateProcedureSpec("proc1", text);
                        __insertPrefix3(context, prefix, f, ProcedureSpec.class, new InsertionHandler<ProcedureSpec>() {
                            @Override
                            public void handle(Editor editor, ProcedureSpec e) {
                                TextRange range = e.getEObjectName().getTextRange();
                                editor.getCaretModel().moveToOffset(range.getEndOffset());
                            }
                        });
//                        insertPrefix2(context, prefix, f, ProcedureSpec.class, new InsertionHandler<ProcedureSpec>() {
//                            @Override
//                            public void handle(Editor editor, ProcedureSpec e) {
//                                TextRange range = e.getEObjectName().getTextRange();
//                                int cursorOffset = range.getStartOffset() + f.getName().length();
//                                editor.getDocument().replaceString(range.getStartOffset(), range.getEndOffset(), f.getName());
//                                PsiDocumentManager.getInstance(context.getProject()).commitDocument(editor.getDocument());
//                                editor.getCaretModel().moveToOffset(cursorOffset);
//                            }
//                        });

                    }
                })
                .withStrikeoutness(false);

        return new ProcedureLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 6));
    }
}
