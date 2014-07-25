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

import com.deepsky.lang.plsql.completion.lookups.UI.*;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.view.Icons;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.completion.PrioritizedLookupElement;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;


public class FunctionLookupElement<T extends LookupElement> extends BaseLookupDecorator<T> {

    static LoggerProxy log = LoggerProxy.getInstance("#FunctionLookupElement");

    protected FunctionLookupElement(T delegate) {
        super(delegate);
    }


    public static FunctionLookupElement create() {
        LookupElement e = LookupElementBuilder.create("create function")
                .withIcon(Icons.FUNCTION_BODY)
                .withPresentableText("create function <function name> return <data type> is .. end")
//                .withTypeText("Create User Function", true)
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "create function func1\n" +
                                "return NUMBER\n" +
                                "is\n" +
                                "begin\n" +
                                "return 0;\n" +
                                "end;\n" +
                                "/";


                        updateFunctionBody(context, editor, prefix, true);

                    }
                })
                .withStrikeoutness(false);

        return new FunctionLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 6));
    }


    public static FunctionLookupElement createBody(String text) {
        LookupElement e = LookupElementBuilder.create("function")
                .withIcon(Icons.FUNCTION_BODY)
                .withPresentableText("function <function name> return <data type> is .. end")
                .withTailText("(Body)", true)
                .withTypeText(text, false)
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "function func1\n" +
                                "return NUMBER\n" +
                                "is\n" +
                                "begin\n" +
                                "return 0;\n" +
                                "end;\n";

                        updateFunctionBody(context, editor, prefix, false);
                    }
                })
                .withStrikeoutness(false);

        return new FunctionLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 6));
    }


    public static LookupElement createSpec(final String text) {
        LookupElement e = LookupElementBuilder.create("function")
                .withIcon(Icons.FUNCTION_SPEC)
                .withPresentableText("function <function name> return <data type>")
                .withTailText("(Specification)", true)
                .withTypeText(text, false)
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(final InsertionContext context, LookupElement item) {
                        String prefix = "function func1 return NUMBER;";

                        final CreateFunction f = new CreateFunction("func1", "NUMBER", text);
                        insertPrefix2(context, prefix, f, FunctionSpec.class, new InsertionHandler<FunctionSpec>() {
                            @Override
                            public void handle(Editor editor, FunctionSpec e) {
                                // Check function return type
                                boolean typeUpdated = false;
                                if(!"NUMBER".equalsIgnoreCase(f.getFunctionType())){
                                    typeUpdated = true;
                                }
                                TextRange range = e.getEObjectName().getTextRange();
                                int cursorOffset = range.getStartOffset() + f.getName().length();
                                int increment = f.getName().length() - range.getLength();
                                editor.getDocument().replaceString(range.getStartOffset(), range.getEndOffset(), f.getName());
                                TextRange funcRange = e.getTextRange();
                                String text = editor.getDocument().getText();
                                String funcText = text.substring(
                                        funcRange.getStartOffset(),
                                        funcRange.getEndOffset() + increment);

                                if(typeUpdated){
                                    funcText = adoptReturnType(funcText, f.getFunctionType());
                                    editor.getDocument().replaceString(
                                            funcRange.getStartOffset(),
                                            funcRange.getEndOffset() + increment,
                                            funcText);
                                }

                                PsiDocumentManager.getInstance(context.getProject()).commitDocument(editor.getDocument());
                                editor.getCaretModel().moveToOffset(cursorOffset);
                            }
                        });
                    }
                })
                .withStrikeoutness(false);

        return new FunctionLookupElement<LookupElement>(
                PrioritizedLookupElement.withGrouping(e, 6));
    }


    private static void updateFunctionBody(final InsertionContext context, final Editor editor, String prefix, final boolean orReplace) {

        insertPrefix(context, editor, prefix, Function.class, new PopupBuilder<Function>() {
            @Override
            public ParamProviderPopup createPopup(final Function e) {
                final Type type = e.getReturnType();

                final FunctionParamPopup f = orReplace?
                        new CreateOrReplaceFunction(e.getEName(),type.typeName()):
                        new CreateFunction(e.getEName(),type.typeName(),e.getPackageName());

                f.addCloseEventLister(new ParamProviderPopup.CloseEventListener() {
                    @Override
                    public void close(final boolean isOk) {
                        ApplicationManager.getApplication().runWriteAction(new Runnable() {
                            public void run() {
                                // User pressed OK, update function name and return type
                                if (isOk && !context.getProject().isDisposed()) {
                                    try {
                                        // Check function return type
                                        boolean typeUpdated = false;
                                        if(!type.typeName().equalsIgnoreCase(f.getFunctionType())){
                                            typeUpdated = true;
                                        }
                                        TextRange range = e.getEObjectName().getTextRange();
                                        int cursorOffset = range.getStartOffset() + f.getName().length();
                                        int increment = f.getName().length() - range.getLength();
                                        editor.getDocument().replaceString(range.getStartOffset(), range.getEndOffset(), f.getName());
                                        TextRange funcRange = e.getTextRange();
                                        String text = editor.getDocument().getText();
                                        String funcText = text.substring(
                                                funcRange.getStartOffset(),
                                                funcRange.getEndOffset() + increment);

                                        if(typeUpdated){
                                            funcText = adoptReturnTypeAndValue(funcText, f.getFunctionType());
                                            editor.getDocument().replaceString(
                                                    funcRange.getStartOffset(),
                                                    funcRange.getEndOffset() + increment,
                                                    funcText);
                                        }

                                        if(f instanceof CreateOrReplaceFunction && ((CreateOrReplaceFunction)f).isCreateOrReplace()){
                                            // Add "OR REPLACE"
                                            int textLength = funcText.length();
                                            Executable exec = insertOrReplace(funcText);
                                            cursorOffset = e.getTextRange().getStartOffset() + exec.getEObjectName().getTextRange().getEndOffset();
                                            editor.getDocument().replaceString(
                                                    funcRange.getStartOffset(),
                                                    funcRange.getStartOffset() + textLength,
                                                    exec.getText());
                                        }
                                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(editor.getDocument());
                                        editor.getCaretModel().moveToOffset(cursorOffset);

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


    private static String adoptReturnTypeAndValue(String text, String newType) {
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode root = generator.parse(text);
        Function func1 = (Function) root.getFirstChildNode().getPsi();

        ReturnStatement retStmt = func1.getBlock().findReturnStatement();
        if(retStmt != null){
            String adoptedValue = "NULL";
            if(newType.equals("NUMBER") || newType.equals("INTEGER") || newType.equals("INT")
                    || newType.equals("DOUBLE PRECISION") || newType.equals("FLOAT")
                    || newType.equals("NUMERIC") || newType.equals("DECIMAL")){
                adoptedValue = "0";
            }
            Expression expr = retStmt.getExpression();
            text = text.substring(0, expr.getTextRange().getStartOffset()) + adoptedValue + text.substring(expr.getTextRange().getEndOffset());

            generator = new MarkupGeneratorEx2();
            root = generator.parse(text);
            func1 = (Function) root.getFirstChildNode().getPsi();
        }

        TextRange range1 = func1.getReturnTypeElement().getTextRange();
        text = text.substring(0, range1.getStartOffset()) + newType + text.substring(range1.getEndOffset());
        return text;
    }

    private static String adoptReturnType(String text, String newType) {
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode root = generator.parse(text);
        FunctionSpec func1 = (FunctionSpec) root.getFirstChildNode().getPsi();

        TextRange range1 = func1.getReturnTypeElement().getTextRange();
        text = text.substring(0, range1.getStartOffset()) + newType + text.substring(range1.getEndOffset());
        return text;
    }



}
