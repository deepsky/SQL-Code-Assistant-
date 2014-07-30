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

import com.deepsky.lang.plsql.completion.lookups.UI.CreateFunctionBody;
import com.deepsky.lang.plsql.completion.lookups.UI.CreateFunctionSpec;
import com.deepsky.lang.plsql.completion.lookups.UI.CreateOrReplaceFunction;
import com.deepsky.lang.plsql.completion.lookups.UI.FunctionParamPopup;
import com.deepsky.lang.plsql.psi.Function;
import com.deepsky.lang.plsql.psi.FunctionSpec;
import com.deepsky.view.Icons;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.completion.PrioritizedLookupElement;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.TextRange;


public class FunctionLookupElement<T extends LookupElement> extends BaseLookupDecorator<T> {

    protected FunctionLookupElement(T delegate) {
        super(delegate);
    }

    public static FunctionLookupElement create() {
        LookupElement e = LookupElementBuilder.create("create function")
                .withIcon(Icons.FUNCTION_BODY)
                .withPresentableText("Create Function")
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        String prefix = "create function func1\n" +
                                "return NUMBER\n" +
                                "is\n" +
                                "begin\n" +
                                "return 0;\n" +
                                "end;\n" +
                                "/";

                        final CreateOrReplaceFunction f = new CreateOrReplaceFunction("func1", "NUMBER");
                        __insertPrefix3(context, prefix, f, Function.class, new InsertionHandler<Function>() {
                            @Override
                            public void handle(Editor editor, Function e) {
                                TextRange range = e.getEObjectName().getTextRange();
                                editor.getCaretModel().moveToOffset(range.getEndOffset());
                            }
                        });
                    }
                })
                .withStrikeoutness(false);

        return new FunctionLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 6));
    }


    public static FunctionLookupElement createBody(final String text) {
        LookupElement e = LookupElementBuilder.create("function")
                .withIcon(Icons.FUNCTION_BODY)
                .withPresentableText("Create Function Body")
                .withTypeText(text, false)
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        String prefix = "function func1\n" +
                                "return NUMBER\n" +
                                "is\n" +
                                "begin\n" +
                                "return 0;\n" +
                                "end;\n";

                        final FunctionParamPopup f = new CreateFunctionBody("func1", "NUMBER", text);
                        __insertPrefix3(context, prefix, f, Function.class, new InsertionHandler<Function>() {
                            @Override
                            public void handle(Editor editor, Function e) {
                                TextRange range = e.getEObjectName().getTextRange();
                                editor.getCaretModel().moveToOffset(range.getEndOffset());
                            }
                        });
                    }
                })
                .withStrikeoutness(false);

        return new FunctionLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 6));
    }


    public static LookupElement createSpec(final String text) {
        LookupElement e = LookupElementBuilder.create("function")
                .withIcon(Icons.FUNCTION_SPEC)
                .withPresentableText("Create Function Specification")
                .withTypeText(text, false)
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(final InsertionContext context, LookupElement item) {
                        String prefix = "function func1 return NUMBER;";

                        final CreateFunctionSpec f = new CreateFunctionSpec("func1", "NUMBER", text);
                        __insertPrefix3(context, prefix, f, FunctionSpec.class, new InsertionHandler<FunctionSpec>() {
                            @Override
                            public void handle(Editor editor, FunctionSpec e) {
                                TextRange range = e.getEObjectName().getTextRange();
                                editor.getCaretModel().moveToOffset(range.getEndOffset());
                            }
                        });
                    }
                })
                .withStrikeoutness(false);

        return new FunctionLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 6));
    }

}
