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

import com.deepsky.lang.plsql.completion.lookups.UI.CreateFunction;
import com.deepsky.lang.plsql.completion.lookups.UI.CreateOrReplaceFunction;
import com.deepsky.lang.plsql.completion.lookups.UI.FunctionParamPopup;
import com.deepsky.lang.plsql.completion.lookups.UI.ParamProviderPopup;
import com.deepsky.lang.plsql.psi.Expression;
import com.deepsky.lang.plsql.psi.Function;
import com.deepsky.lang.plsql.psi.ReturnStatement;
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
                .withPresentableText("create function <function name> is begin .. end;")
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


//    public static FunctionLookupElement createOrReplace() {
//        LookupElement e = LookupElementBuilder.create("create or replace function")
//                .withIcon(Icons.FUNCTION_BODY)
//                .withPresentableText("create or replace function <function name> is begin .. end;")
//                .withCaseSensitivity(false)
//                .withInsertHandler(new InsertHandler<LookupElement>() {
//                    @Override
//                    public void handleInsert(InsertionContext context, LookupElement item) {
//                        final Editor editor = context.getEditor();
//                        String prefix = "create or replace function func1\n" +
//                                "return NUMBER\n" +
//                                "is\n" +
//                                "begin\n" +
//                                "return 0;\n" +
//                                "end;\n" +
//                                "/";
//
//                        updateFunctionBody(context, editor, prefix, true);
//                    }
//                })
//                .withStrikeoutness(false);
//
//        return new FunctionLookupElement<LookupElement>(
//                PrioritizedLookupElement.withGrouping(e, 6));
//    }


    public static FunctionLookupElement createBody(String text) {
        LookupElement e = LookupElementBuilder.create("function")
                .withIcon(Icons.FUNCTION_BODY)
                .withPresentableText("function <function name> is begin .. end;")
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
                                            funcText = insertOrReplace(funcText);
                                            editor.getDocument().replaceString(
                                                    funcRange.getStartOffset(),
                                                    funcRange.getStartOffset() + textLength,
                                                    funcText);
                                        }
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

    private static String insertOrReplace(String text) {
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode root = generator.parse(text);
        Function func1 = (Function) root.getFirstChildNode().getPsi();
        if(!func1.createOrReplace()){
            // Add "OR REPLACE"
            return text.replaceFirst("^create (?i)", "create or replace ");
        }
        return text;
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

    public static LookupElement createSpec(String text) {
        // TODO - implement me
        return null;  //To change body of created methods use File | Settings | File Templates.
    }


/*
    static private void insertPrefix2(final InsertionContext context, final Editor editor, String prefix) {

        int i = context.getStartOffset();
        for (int cnt = prefix.length() + 20; i > 0 && cnt > 0; cnt--, i--) ;

        String _prefix = LookupUtils.calcLookupPrefix(prefix, editor.getDocument().getText().substring(i, context.getStartOffset()));
        if (prefix.startsWith(_prefix)) {
            String prefixBeingInserted = prefix.substring(_prefix.length());

            editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefixBeingInserted);
            final Document document = editor.getDocument();

            PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);

            int startOffset = context.getStartOffset();
            CodeStyleManager.getInstance(context.getProject()).reformatText(context.getFile(),
                    startOffset - _prefix.length(),
                    startOffset + prefixBeingInserted.length() + 1);

            PsiElement startElem = context.getFile().findElementAt(startOffset);
            PsiElement func = startElem != null ? startElem.getNextSibling() : null;
            while (func != null && !(func instanceof Function)) {
                func = func.getParent();
            }

            if (func != null) {
                final Function finalFunc = (Function) func;
                final Type type = finalFunc.getReturnType();

                final CreateFunction f = new CreateFunction(
                        finalFunc.getEName(),
                        type.typeName(),
                        finalFunc.getPackageName());

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
                                        TextRange range = finalFunc.getEObjectName().getTextRange();
                                        int increment = f.getName().length() - range.getLength();
                                        editor.getDocument().replaceString(range.getStartOffset(), range.getEndOffset(), f.getName());

                                        if(typeUpdated){
                                            String text = editor.getDocument().getText();
                                            TextRange funcRange = finalFunc.getTextRange();
                                            String funcText = text.substring(
                                                    funcRange.getStartOffset(),
                                                    funcRange.getEndOffset() + increment);

                                            funcText = adoptReturnTypeAndValue(funcText, f.getFunctionType());
                                            editor.getDocument().replaceString(
                                                    funcRange.getStartOffset(),
                                                    funcRange.getEndOffset() + increment,
                                                    funcText);
                                        }
                                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                                        editor.getCaretModel().moveToOffset(range.getStartOffset() + f.getName().length());

                                    } catch (Throwable e) {
                                        log.warn(e.getMessage());
                                    }
                                }
                            }
                        });
                    }
                });

                ObjectUIBuilder b = new ObjectUIBuilder(context.getProject(), f);
                b.show(editor.getComponent());
            }
        }
    }
*/



}
