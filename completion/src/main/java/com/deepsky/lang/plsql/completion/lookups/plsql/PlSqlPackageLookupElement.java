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

import com.deepsky.lang.plsql.completion.lookups.UI.CreatePackageBody;
import com.deepsky.lang.plsql.completion.lookups.UI.CreatePackageSpec;
import com.deepsky.lang.plsql.psi.PackageBody;
import com.deepsky.lang.plsql.psi.PackageSpec;
import com.deepsky.view.Icons;
import com.intellij.codeInsight.TailType;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.completion.PrioritizedLookupElement;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;

import javax.swing.*;

public class PlSqlPackageLookupElement<T extends LookupElement> extends BaseLookupDecorator<T> {

    protected PlSqlPackageLookupElement(T delegate) {
        super(delegate);
    }


    public static PlSqlPackageLookupElement create(String name, Icon icon) {
        LookupElement e = LookupElementBuilder.create(name)
                .withIcon(icon)
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
//        final char completionChar = context.getCompletionChar();
                        final TailType tailType = TailType.DOT;

                        context.setAddCompletionChar(false);
                        tailType.processTail(editor, context.getTailOffset());
                        editor.getScrollingModel().scrollToCaret(ScrollType.RELATIVE);
                    }
                });

        return new PlSqlPackageLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 6));
    }


    public static PlSqlPackageLookupElement createPackage() {
        LookupElement e = LookupElementBuilder.create("create package")
                .withIcon(Icons.PACKAGE_SPEC)
                .withPresentableText("create package <package name> as ... end")
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(final InsertionContext context, LookupElement item) {
//                        final Editor editor = context.getEditor();
                        String prefix = "create package package1 as\nend;\n/";

                        final CreatePackageSpec f = new CreatePackageSpec("package1");
                        insertPrefix2(context, prefix, f, PackageSpec.class, new BaseLookupDecorator.InsertionHandler<PackageSpec>() {
                            @Override
                            public void handle(Editor editor, PackageSpec e) {
                                TextRange range = e.getPackageNameElement().getTextRange();
                                int startOffset = e.getTextRange().getStartOffset();
                                int endOffset = e.getTextRange().getEndOffset();
                                int cursorOffset = range.getStartOffset() + f.getName().length();
                                int increment = f.getName().length() - range.getLength();
                                editor.getDocument().replaceString(range.getStartOffset(), range.getEndOffset(), f.getName());

                                if (f.isCreateOrReplace()) {
                                    // Add "OR REPLACE"
                                    String pkgText = editor.getDocument().getText().substring(
                                            e.getTextRange().getStartOffset(),
                                            e.getTextRange().getEndOffset() + increment);

                                    PackageSpec exec = insertOrReplace(pkgText);
                                    cursorOffset = startOffset + exec.getPackageNameElement().getTextRange().getEndOffset();
                                    editor.getDocument().replaceString(
                                            startOffset,
                                            endOffset + increment,
                                            exec.getText());
                                }

                                PsiDocumentManager.getInstance(context.getProject()).commitDocument(editor.getDocument());
                                editor.getCaretModel().moveToOffset(cursorOffset);
                            }
                        });
                    }
                })
                .withStrikeoutness(false);

        return new PlSqlPackageLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 6));
    }


    public static PlSqlPackageLookupElement createPackageBody() {
        LookupElement e = LookupElementBuilder.create("create package body")
                .withIcon(Icons.PACKAGE_BODY)
                .withPresentableText("create package body <package name> as ... end")
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(final InsertionContext context, LookupElement item) {
                        String prefix = "create package body package1 as\n\nend;\n/\n";

                        final CreatePackageBody f = new CreatePackageBody("package1");
                        insertPrefix2(context, prefix, f, PackageBody.class, new BaseLookupDecorator.InsertionHandler<PackageBody>() {
                            @Override
                            public void handle(Editor editor, PackageBody e) {
                                String text1 = e.getText().replaceFirst("package1", f.getName());
                                if (f.insertInitSection()) {
                                    // Insert ... BEGIN\n\tNULL;
                                    text1 = text1.replaceFirst("end;",
                                            "begin\n" +
                                            "\t-- Add package initialization code here\n" +
                                            "\tNULL;\n" +
                                            "end;");
                                }

                                TextRange range = e.getPackageNameElement().getTextRange();
                                int cursorOffset = range.getStartOffset() + f.getName().length();
                                int increment = text1.length() - e.getTextRange().getLength();
                                editor.getDocument().replaceString(
                                        e.getTextRange().getStartOffset(),
                                        e.getTextRange().getEndOffset(), text1);

                                if (f.isCreateOrReplace()) {
                                    // Add "OR REPLACE"
                                    PackageBody exec = insertOrReplace(text1);
                                    cursorOffset = e.getTextRange().getStartOffset() + exec.getPackageNameElement().getTextRange().getEndOffset();
                                    editor.getDocument().replaceString(
                                            e.getTextRange().getStartOffset(),
                                            e.getTextRange().getEndOffset() + increment,
                                            exec.getText());
                                }

                                PsiDocumentManager.getInstance(context.getProject()).commitDocument(editor.getDocument());
                                editor.getCaretModel().moveToOffset(cursorOffset);
                            }
                        });

                    }
                })
                .withStrikeoutness(false);

        return new PlSqlPackageLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 6));
    }
}
