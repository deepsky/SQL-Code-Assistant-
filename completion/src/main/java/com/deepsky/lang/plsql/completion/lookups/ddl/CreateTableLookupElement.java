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

package com.deepsky.lang.plsql.completion.lookups.ddl;

import com.deepsky.lang.plsql.completion.lookups.LookupUtils;
import com.deepsky.lang.plsql.completion.lookups.UI.CreateTable;
import com.deepsky.lang.plsql.completion.lookups.plsql.BaseLookupDecorator;
import com.deepsky.lang.plsql.formatter.settings.PlSqlCodeStyleSettings;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.deepsky.lang.plsql.psi.TableAlias;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.psi.ref.TableRef;
import com.deepsky.view.Icons;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;

public class CreateTableLookupElement<T extends LookupElement> extends BaseLookupDecorator<T> {

    protected CreateTableLookupElement(T delegate) {
        super(delegate);
    }

    public static CreateTableLookupElement createRegular() {
        LookupElement e = LookupElementBuilder.create("create table")
                .withIcon(Icons.TABLE)
                .withPresentableText("Create Table")
                .withCaseSensitivity(false)
                .withStrikeoutness(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(final InsertionContext context, LookupElement item) {
                        String prefix = "create table table1(\nid number primary key\n);\n";

                        final CreateTable f = new CreateTable("table1");
                        insertPrefix2(context, prefix, f, TableDefinition.class, new BaseLookupDecorator.InsertionHandler<TableDefinition>() {
                            @Override
                            public void handle(Editor editor, TableDefinition e) {

                                final int startOffset = e.getTextRange().getStartOffset();
                                final int endOffset = e.getTextRange().getEndOffset();
                                String text = f.getStatementText();
                                int indent = text.length() - e.getTextRange().getLength();

                                // Replace old definition with a new one
                                editor.getDocument().replaceString(startOffset, endOffset, text);
                                PsiDocumentManager.getInstance(context.getProject()).commitDocument(editor.getDocument());

                                // Format text but keep location of the code
                                PlSqlCodeStyleSettings settings = CodeStyleSettingsManager.getSettings(
                                        context.getProject()).getCustomSettings(PlSqlCodeStyleSettings.class);
                                int old = settings.MAX_LINES_BETWEEN_FILE_LEVEL_STMT;
                                settings.MAX_LINES_BETWEEN_FILE_LEVEL_STMT = 200;
                                CodeStyleManager.getInstance(context.getProject()).reformatText(context.getFile(),
                                        startOffset,
                                        endOffset + indent);
                                settings.MAX_LINES_BETWEEN_FILE_LEVEL_STMT = old;

                                // Set cursor position at the right place
                                PsiElement startElem = context.getFile().findElementAt(startOffset + 10);
                                PsiElement func = startElem != null ? startElem.getNextSibling() : null;
                                while (func != null && !(func instanceof TableDefinition)) {
                                    func = func.getParent();
                                }

                                if (func != null) {
                                    TableDefinition def = (TableDefinition) func;
                                    if (def.definedAsSelect()) {
                                        // CREATE TABLE TAB1 AS SELECT * FROM TAB2
                                        // Set cursor at table name
                                        SelectStatement select = def.getSelectStatement();
                                        if (select != null) {
                                            TableRef ref = ((TableAlias) select.getFromClause().getTableList()[0]).getTableNameElement();
                                            editor.getCaretModel().moveToOffset(ref.getTextRange().getStartOffset());
                                            editor.getSelectionModel().setSelection(
                                                    ref.getTextRange().getStartOffset(),
                                                    ref.getTextRange().getEndOffset());
                                            LookupUtils.scheduleAutoPopup2(editor, context.getProject());
                                        }

                                    } else {
                                        // Remove column if auto generation of ID column was not requested
                                        if (!f.isIDAutogenerated()) {
                                            // TODO - implement me
                                        } else {
                                            TextRange range = def.getTableNameElement().getTextRange();
                                            int cursorOffset = range.getStartOffset() + f.getName().length();
                                            editor.getCaretModel().moveToOffset(cursorOffset);
                                        }
                                    }
                                }
                            }
                        });

                    }
                });

        return new CreateTableLookupElement<LookupElement>(e);
    }


}

