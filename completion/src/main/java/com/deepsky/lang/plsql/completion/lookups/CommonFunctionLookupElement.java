package com.deepsky.lang.plsql.completion.lookups;

import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElementDecorator;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiDocumentManager;

public class CommonFunctionLookupElement<T extends LookupElement> extends LookupElementDecorator<T> {
    protected CommonFunctionLookupElement(T delegate) {
        super(delegate);
    }

    public static CommonFunctionLookupElement createCount(){
        LookupElement e = LookupElementBuilder.create("count")
                .withCaseSensitivity(false)
                .withBoldness(true)
                .withPresentableText("count(*)")
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "count(*) ";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        final Document document = editor.getDocument();
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                });

        return new CommonFunctionLookupElement<LookupElement>(e);
    }

}
