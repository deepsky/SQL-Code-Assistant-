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
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElementDecorator;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiDocumentManager;

public class AlterTableLookupElement<T extends LookupElement> extends LookupElementDecorator<T> {
    protected AlterTableLookupElement(T delegate) {
        super(delegate);
    }

    public static AlterTableLookupElement create() {
        LookupElement e = LookupElementBuilder.create("table")
                .withPresentableText("alter table <table> ...")
                .withCaseSensitivity(false)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "table ";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);

                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new AlterTableLookupElement<LookupElement>(e);
    }


    public static AlterTableLookupElement createAddColumn(String tableName) {
        LookupElement e = LookupElementBuilder.create("add")
//                .withPresentableText("alter table " + tableName + " add <column> <type>")
                .withPresentableText("add <column> <type>")
                .withCaseSensitivity(false)
                .withTypeText("Add column to " + tableName, true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "add ";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                    }
                })
                .withStrikeoutness(false);

        return new AlterTableLookupElement<LookupElement>(e);
    }


    public static AlterTableLookupElement createRenameColumn(String tableName) {
        LookupElement e = LookupElementBuilder.create("column")
                .withPresentableText("column <old column> to <new column>")
                .withCaseSensitivity(false)
                .withTypeText("Rename column in " + tableName, true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "column ";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new AlterTableLookupElement<LookupElement>(e);
    }

    public static AlterTableLookupElement createRenameConstraint(String tableName) {
        LookupElement e = LookupElementBuilder.create("constraint")
                .withPresentableText("constraint <old name> to <new name>")
                .withCaseSensitivity(false)
                .withTypeText("Rename constraint in " + tableName, true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "constraint ";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new AlterTableLookupElement<LookupElement>(e);
    }

    public static LookupElement createRenameConstraintName(final String constraintName, boolean isPK_Constraint) {
        String message = isPK_Constraint?
                "Rename primary key constraint":
                "Rename foreign key constraint";

        LookupElement e = LookupElementBuilder.create(constraintName)
                .withCaseSensitivity(false)
                .withTypeText(message, true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), constraintName + " to ");
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new AlterTableLookupElement<LookupElement>(e);
    }


    public static AlterTableLookupElement createRenameTable(String tableName) {
        LookupElement e = LookupElementBuilder.create("to")
                .withPresentableText("to <new table name>")
                .withCaseSensitivity(false)
                .withTypeText("Rename " + tableName, true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "to ";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
//                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new AlterTableLookupElement<LookupElement>(e);
    }

    public static AlterTableLookupElement createRename(String tableName) {
        LookupElement e = LookupElementBuilder.create("rename")
                .withPresentableText("rename")
                .withCaseSensitivity(false)
                .withTypeText("Rename column, constraint or table " + tableName, true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "rename ";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new AlterTableLookupElement<LookupElement>(e);
    }

    public static AlterTableLookupElement createDropColumn(String tableName) {
        LookupElement e = LookupElementBuilder.create("drop")
                .withPresentableText("drop column <column>")
                .withCaseSensitivity(false)
                .withTypeText("Drop a column in " + tableName , true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "drop column ";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new AlterTableLookupElement<LookupElement>(e);
    }

    public static AlterTableLookupElement createDrop(String tableName) {
        LookupElement e = LookupElementBuilder.create("drop")
                .withPresentableText("drop")
                .withCaseSensitivity(false)
                .withTypeText("Drop column, primary key or constraint in " + tableName, true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "drop ";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new AlterTableLookupElement<LookupElement>(e);
    }

    public static LookupElement createDropPKCascade() {
        LookupElement e = LookupElementBuilder.create("cascade")
                .withPresentableText("cascade")
                .withCaseSensitivity(false)
                .withTypeText("Drop a primary key cascade", true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "cascade";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        //LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new AlterTableLookupElement<LookupElement>(e);
    }

    public static LookupElement createDropPK_DropIndex() {
        LookupElement e = LookupElementBuilder.create("drop")
                .withPresentableText("drop index")
                .withCaseSensitivity(false)
                .withTypeText("Drop a primary key and drop index", true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "drop index";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        //LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new AlterTableLookupElement<LookupElement>(e);
    }

    public static LookupElement createDropPK_KeepIndex() {
        LookupElement e = LookupElementBuilder.create("keep")
                .withPresentableText("keep index")
                .withCaseSensitivity(false)
                .withTypeText("Drop a primary key but keep index", true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "keep index";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        //LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new AlterTableLookupElement<LookupElement>(e);
    }

    public static LookupElement createDropConstraint(final String constraintName, boolean isPK_Constraint) {
        String message = isPK_Constraint?
                "Drop primary key constraint":
                "Drop foreign key constraint";

        LookupElement e = LookupElementBuilder.create(constraintName)
                .withCaseSensitivity(false)
                .withTypeText(message, true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), constraintName);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        //LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new AlterTableLookupElement<LookupElement>(e);
    }

    public static LookupElement createAddColumnPK() {
        LookupElement e = LookupElementBuilder.create("primary")
                .withPresentableText("primary key")
                .withCaseSensitivity(false)
                .withTypeText("Add a primary key column", true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "primary key";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        //LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new AlterTableLookupElement<LookupElement>(e);
    }

    public static LookupElement createAddColumnFK() {
        LookupElement e = LookupElementBuilder.create("references")
                .withPresentableText("references")
                .withCaseSensitivity(false)
                .withTypeText("Add a foreign key", true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "references ";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new AlterTableLookupElement<LookupElement>(e);
    }

    public static LookupElement createAddColumnNotNull() {
        LookupElement e = LookupElementBuilder.create("not")
                .withPresentableText("not null")
                .withCaseSensitivity(false)
                .withTypeText("not null constraint", true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "not null";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        //LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new AlterTableLookupElement<LookupElement>(e);
    }

    public static LookupElement createAddConstraint() {
        LookupElement e = LookupElementBuilder.create("constraint")
                .withPresentableText("constraint")
                .withCaseSensitivity(false)
                .withTypeText("Add constraint on column", true)
                .withInsertHandler(new InsertHandler<LookupElement>() {
                    @Override
                    public void handleInsert(InsertionContext context, LookupElement item) {
                        final Editor editor = context.getEditor();
                        String prefix = "constraint ";
                        editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                        final Document document = editor.getDocument();

                        editor.getCaretModel().moveToOffset(context.getTailOffset());
                        PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                        //LookupUtils.scheduleAutoPopup(editor, context);
                    }
                })
                .withStrikeoutness(false);

        return new AlterTableLookupElement<LookupElement>(e);
    }
}
