package com.deepsky.lang.plsql.completion.lookups.select;

import com.deepsky.lang.plsql.completion.lookups.LookupUtils;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElementDecorator;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiDocumentManager;

public class CaseExpressionLookupElement <T extends LookupElement> extends LookupElementDecorator<T> {
    protected CaseExpressionLookupElement(T delegate) {
        super(delegate);
    }

    public static LookupElement createCase() {
        LookupElement e = LookupElementBuilder.create("case")
                .withPresentableText("case when <condition> then <return expr> else <return expr> end")
                .withCaseSensitivity(false)
                .withBoldness(true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "case when  then 1 else 0 end";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset()-18);
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new CaseExpressionLookupElement<LookupElement>(e);
    }

    public static LookupElement createCaseWhen() {
        LookupElement e = LookupElementBuilder.create("when")
                .withPresentableText("case when <condition> then <return expr> else <return expr> end")
                .withCaseSensitivity(false)
                .withBoldness(true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "when  then 1 else 0 end";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset()-18);
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new CaseExpressionLookupElement<LookupElement>(e);
    }

    public static LookupElement createCaseWhenThen() {
        LookupElement e = LookupElementBuilder.create("then")
                .withPresentableText("case when <condition> then <return expr> else <return expr> end")
                .withCaseSensitivity(false)
                .withBoldness(true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "then  else 0 end";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset()-11);
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new CaseExpressionLookupElement<LookupElement>(e);
    }

    public static LookupElement createCaseWhenThenWhen() {
        LookupElement e = LookupElementBuilder.create("when")
                .withPresentableText("case when <condition> then <return expr> when <condition> then <return expr> end")
                .withCaseSensitivity(false)
                .withBoldness(true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "when  then 1 else 0 end";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset()-18);
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new CaseExpressionLookupElement<LookupElement>(e);
    }

}
