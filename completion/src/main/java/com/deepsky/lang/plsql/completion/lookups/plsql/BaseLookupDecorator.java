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
import com.deepsky.lang.plsql.completion.lookups.UI.ObjectUIBuilder;
import com.deepsky.lang.plsql.completion.lookups.UI.ParamProviderPopup;
import com.deepsky.lang.plsql.formatter.settings.PlSqlCodeStyleSettings;
import com.deepsky.lang.plsql.psi.Executable;
import com.deepsky.lang.plsql.psi.Function;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.spices.CompilableObject;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementDecorator;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;

public abstract class BaseLookupDecorator <T extends LookupElement> extends LookupElementDecorator<T> {

    static LoggerProxy log = LoggerProxy.getInstance("#BaseLookupDecorator");

    protected BaseLookupDecorator(T delegate) {
        super(delegate);
    }

    public static interface PopupBuilder<T extends PlSqlElement> {
        ParamProviderPopup createPopup(T e);
    }


    protected static <T extends PlSqlElement> void insertPrefix(
            final InsertionContext context, final Editor editor, String prefix, Class<T> target, PopupBuilder<T> builder) {

        int i = context.getStartOffset();
        for (int cnt = prefix.length() + 20; i > 0 && cnt > 0; cnt--, i--) ;

        String _prefix = LookupUtils.calcLookupPrefix(prefix, editor.getDocument().getText().substring(i, context.getStartOffset()));
        if (prefix.startsWith(_prefix)) {
            String prefixBeingInserted = prefix.substring(_prefix.length());

            editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefixBeingInserted);
            final Document document = editor.getDocument();

            PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);

            // Format text but keep location of the code
            PlSqlCodeStyleSettings settings = CodeStyleSettingsManager.getSettings(context.getProject()).getCustomSettings(PlSqlCodeStyleSettings.class);
            int old1 = settings.MAX_LINES_BETWEEN_FILE_LEVEL_STMT;
            int old2 = settings.MAX_LINES_BETWEEN_PKG_LEVEL_STMT;
            settings.MAX_LINES_BETWEEN_FILE_LEVEL_STMT = 200;
            settings.MAX_LINES_BETWEEN_PKG_LEVEL_STMT = 200;
            int startOffset = context.getStartOffset();
            CodeStyleManager.getInstance(context.getProject()).reformatText(context.getFile(),
                    startOffset - _prefix.length(),
                    startOffset + prefixBeingInserted.length() + 1);
            settings.MAX_LINES_BETWEEN_FILE_LEVEL_STMT = old1;
            settings.MAX_LINES_BETWEEN_PKG_LEVEL_STMT = old2;

            PsiElement startElem = context.getFile().findElementAt(startOffset);
            PsiElement func = startElem != null ? startElem.getNextSibling() : null;
            while (func != null && !target.isInstance(func)) {
                func = func.getParent();
            }

            if (func != null) {
                editor.getCaretModel().moveToOffset(func.getTextRange().getStartOffset());
                editor.getScrollingModel().scrollToCaret(ScrollType.RELATIVE);

                ParamProviderPopup f = builder.createPopup((T) func);

                ObjectUIBuilder b = new ObjectUIBuilder(context.getProject(), f);
                b.show(editor.getComponent());
            }
        }

    }


    protected static interface InsertionHandler <T extends PlSqlElement> {
        void handle(Editor editor, T e);
    }

    protected static <T extends PlSqlElement> void insertPrefix2(
            final InsertionContext context, String prefix, final ParamProviderPopup f,
            Class<T> target, final InsertionHandler<T> handler) {

        insertPrefix(context, context.getEditor(), prefix, target, new BaseLookupDecorator.PopupBuilder<T>() {
            @Override
            public ParamProviderPopup createPopup(final T e) {
                f.addCloseEventLister(new ParamProviderPopup.CloseEventListener() {
                    @Override
                    public void close(final boolean isOk) {
                        ApplicationManager.getApplication().runWriteAction(new Runnable() {
                            public void run() {
                                // User pressed OK, update procedure name
                                if (isOk && !context.getProject().isDisposed()) {
                                    try {
                                        handler.handle(context.getEditor(), e);
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




    protected static <T extends CompilableObject> T insertOrReplace(String text) {
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode root = generator.parse(text);
        T func1 = (T) root.getFirstChildNode().getPsi();
        String adoptedText = text;
        if(!func1.createOrReplace()){
            // Add "OR REPLACE"
            adoptedText = text.replaceFirst("^create[ \n\t](?i)", "create or replace ");
        }

        root = generator.parse(adoptedText);
        return (T) root.getFirstChildNode().getPsi();
    }


}
