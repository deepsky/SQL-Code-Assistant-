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

package com.deepsky.lang.plsql.completion.processors;


import com.deepsky.lang.plsql.completion.SyntaxTreePath;
import com.deepsky.lang.plsql.completion.VariantsProvider;
import com.deepsky.lang.plsql.completion.lookups.KeywordLookupElement;
import com.deepsky.lang.plsql.completion.lookups.LookupUtils;
import com.deepsky.lang.plsql.completion.lookups.ddl.AlterTableLookupElement;
import com.deepsky.lang.plsql.psi.ref.TableRef;
import com.deepsky.utils.StringUtils;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiDocumentManager;

import java.util.List;


@SyntaxTreePath("/..#ALTER_TABLE")
public class AlterStmtProcessor extends CompletionBase {

    @SyntaxTreePath("/#ALTER #TABLE #TABLE_REF/#C_MARKER")
    public void process$alterTableName(C_Context ctx) {
        collectTableNames(ctx, new InsertHandler<LookupElement>() {
            @Override
            public void handleInsert(InsertionContext context, LookupElement item) {
                final Editor editor = context.getEditor();
                String prefix = item.getLookupString() + " ";
                editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                editor.getCaretModel().moveToOffset(context.getTailOffset());
                final Document document = editor.getDocument();
                PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                LookupUtils.scheduleAutoPopup(editor, context);
            }
        });
    }


    @SyntaxTreePath("/#ALTER #ERROR_TOKEN_A/#TABLE #C_MARKER")
    public void process$alterTableName2(C_Context ctx) {
        collectTableNames(ctx, new InsertHandler<LookupElement>() {
            @Override
            public void handleInsert(InsertionContext context, LookupElement item) {
                final Editor editor = context.getEditor();
                String prefix = item.getLookupString() + " ";
                editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                editor.getCaretModel().moveToOffset(context.getTailOffset());
                final Document document = editor.getDocument();
                PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                LookupUtils.scheduleAutoPopup(editor, context);
            }
        });
    }

    @SyntaxTreePath("/#ALTER #INDEX #TABLE_REF/#C_MARKER")
    public void process$anyExists(C_Context ctx) {
        // TODO -- implement me
    }

    @SyntaxTreePath("/#ALTER #TABLE #TABLE_REF #ADD #A_COLUMN_DEF/#COLUMN_NAME_DDL #TYPE_NAME_REF/#NAME_FRAGMENT/#C_MARKER")
    public void process$alterTableAddColumnType(C_Context ctx) {
        VariantsProvider provider = ctx.getProvider();
        collectTypeNames(ctx, false);
    }

    @SyntaxTreePath("/#ALTER #TABLE #TABLE_REF #ERROR_TOKEN_A/#DROP #C_MARKER")
    public void process$alterTableDrop(C_Context ctx) {
//        InsertHandler insertHandler = new InsertHandler<LookupElement>() {
//            @Override
//            public void handleInsert(InsertionContext context, LookupElement item) {
//                final Editor editor = context.getEditor();
//                editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), "column ;");
//                editor.getCaretModel().moveToOffset(context.getTailOffset()-1);
//                final Document document = editor.getDocument();
//                PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
//                LookupUtils.scheduleAutoPopup(editor, context);
//            }
//        };

        ctx.addElement(KeywordLookupElement.create("column"));
        ctx.addElement(KeywordLookupElement.create("primary key"));
        ctx.addElement(KeywordLookupElement.create("unique"));
        ctx.addElement(KeywordLookupElement.create("constraint"));
    }


    @SyntaxTreePath("/#ALTER #TABLE #TABLE_REF #DROP #ERROR_TOKEN_A/#PRIMARY #C_MARKER")
    public void process$alterTableDropPrimary(C_Context ctx) {
        ctx.addElement(KeywordLookupElement.create("key"));
    }

    @SyntaxTreePath("/#ALTER #TABLE 1#TABLE_REF #ALTER_TABLE_DROP_COL/#DROP #COLUMN #COLUMN_NAME_REF/#C_MARKER")
    public void process$alterTableDropColumn(C_Context ctx, ASTNode tableRef) {
        collectColumns(ctx, ((TableRef)tableRef.getPsi()).getTableName());
    }

    @SyntaxTreePath("/#ALTER #TABLE 1#TABLE_REF #DROP #ALTER_TABLE_DROP_CONSTR/#CONSTRAINT #CONSTRAINT_NAME/#C_MARKER")
    public void process$alterTableDropConstraint(final C_Context ctx, ASTNode tableRef) {
        VariantsProvider provider = ctx.getProvider();
        String tableName = StringUtils.discloseDoubleQuotes(tableRef.getText());

        provider.collectTableConstraints(tableName, new VariantsProvider.TableConstraintProcessor() {
            @Override
            public void process(String constraintName, int constraintType) {
                ctx.addElement(AlterTableLookupElement.createDropConstraint(constraintName, constraintType == VariantsProvider.PK_CONSTRAINT));
            }
        });
    }

    @SyntaxTreePath("/#ALTER #TABLE 1#TABLE_REF #ERROR_TOKEN_A/#RENAME #C_MARKER")
    public void process$alterTableRename(C_Context ctx, ASTNode tableRef) {
        ctx.addElement(AlterTableLookupElement.createRenameColumn(tableRef.getText()));
        ctx.addElement(AlterTableLookupElement.createRenameConstraint(tableRef.getText()));
        ctx.addElement(AlterTableLookupElement.createRenameTable(tableRef.getText()));
    }

    @SyntaxTreePath("/#ALTER #TABLE 1#TABLE_REF #ERROR_TOKEN_A/#RENAME #COLUMN #COLUMN_NAME_REF/#C_MARKER")
    public void process$alterTableRenameColumn(C_Context ctx, ASTNode tableRef) {
        collectColumns(ctx, ((TableRef)tableRef.getPsi()).getTableName());
    }

    @SyntaxTreePath("/#ALTER #TABLE 1#TABLE_REF #ALTER_TABLE_RENAME_COL/#RENAME #COLUMN #COLUMN_NAME_REF/#C_MARKER")
    public void process$alterTableRenameColumn2(C_Context ctx, ASTNode tableRef) {
        collectColumns(ctx, ((TableRef)tableRef.getPsi()).getTableName());
    }

    @SyntaxTreePath("/#ALTER #TABLE 1#TABLE_REF #ALTER_TABLE_RENAME_CONSTR/#RENAME #CONSTRAINT #C_MARKER")
    public void process$alterTableRenameConstraint(C_Context ctx, ASTNode tableRef) {
        alterTableRenameConstraint(ctx, (TableRef) tableRef.getPsi());
    }

    @SyntaxTreePath("/#ALTER #TABLE 1#TABLE_REF #ERROR_TOKEN_A/#RENAME #CONSTRAINT #C_MARKER")
    public void process$renameConstraint2(C_Context ctx, ASTNode tableRef) {
        alterTableRenameConstraint(ctx, (TableRef) tableRef.getPsi());
    }

    @SyntaxTreePath("/#ALTER #TABLE 1#TABLE_REF #ADD #A_COLUMN_DEF/2#COLUMN_NAME_DDL $TypeSpec #ERROR_TOKEN_A/#CONSTRAINT #CONSTRAINT_NAME/3#C_MARKER")
    public void process$generateConstraintName(C_Context ctx, ASTNode tableRef, ASTNode column, ASTNode marker) {
        final String tableName = StringUtils.discloseDoubleQuotes(tableRef.getText());
        final String columnName = StringUtils.discloseDoubleQuotes(column.getText());

        ctx.addElement(AlterTableLookupElement.buildAddConstraintName(tableName, columnName));
    }

    @SyntaxTreePath("/#ALTER #TABLE 1#TABLE_REF #ADD #A_COLUMN_DEF/#COLUMN_NAME_DDL $TypeSpec #ERROR_TOKEN_A/#CONSTRAINT #CONSTRAINT_NAME 2#C_MARKER")
    public void process$addConstraintOnColumn(C_Context ctx, ASTNode tableRef, ASTNode marker) {
        final boolean doFinalize = is2ndLatest(tableRef.getTreeParent(), marker);
        ctx.addElement(AlterTableLookupElement.createAddColumnPK(doFinalize));
        ctx.addElement(AlterTableLookupElement.createAddColumnFK());
        ctx.addElement(AlterTableLookupElement.createAddColumnNotNull(doFinalize));
    }

    @SyntaxTreePath("/#ALTER #TABLE 1#TABLE_REF #ADD #A_COLUMN_DEF/#COLUMN_NAME_DDL $TypeSpec #ERROR_TOKEN_A/#REFERENCES #C_MARKER")
    public void process$addReferencesOnColumn(C_Context ctx, ASTNode tableRef) {
        collectTableNames(ctx);
    }

    @SyntaxTreePath("/#ALTER #TABLE 1#TABLE_REF #ADD #A_COLUMN_DEF/#COLUMN_NAME_DDL $TypeSpec #ERROR_TOKEN_A/#NOT 2#C_MARKER")
    public void process$addNotNull(C_Context ctx, ASTNode tableRef, ASTNode marker) {
        final boolean doFinalize = is2ndLatest(tableRef.getTreeParent(), marker);
        ctx.addElement(KeywordLookupElement.create("null", false, doFinalize));
    }

    @SyntaxTreePath("/#ALTER #TABLE 1#TABLE_REF #ADD #A_COLUMN_DEF/#COLUMN_NAME_DDL $TypeSpec #ERROR_TOKEN_A/#CONSTRAINT #CONSTRAINT_NAME #NOT 2#C_MARKER")
    public void process$addConstraintOnColumnNot(C_Context ctx, ASTNode tableRef, ASTNode marker) {
        final boolean doFinalize = is2ndLatest(tableRef.getTreeParent(), marker);
        ctx.addElement(KeywordLookupElement.create("null", false, doFinalize));
    }

    @SyntaxTreePath("/#ALTER #TABLE 1#TABLE_REF #ADD #A_COLUMN_DEF/#COLUMN_NAME_DDL $TypeSpec #ERROR_TOKEN_A/#CONSTRAINT #CONSTRAINT_NAME #PRIMARY 2#C_MARKER")
    public void process$addConstraintOnColumnPrimary(C_Context ctx, ASTNode tableRef, ASTNode marker) {
        final boolean doFinalize = is2ndLatest(tableRef.getTreeParent(), marker);
        ctx.addElement(KeywordLookupElement.create("key", false, doFinalize));
    }


    @SyntaxTreePath("/#ALTER #TABLE 1#TABLE_REF #ADD #A_COLUMN_DEF/#COLUMN_NAME_DDL $TypeSpec #COLUMN_FK_SPEC/..#REFERENCES 2#TABLE_REF #OPEN_PAREN #COLUMN_NAME_REF/3#C_MARKER")
    public void process$addRefConstraintOnColumn(C_Context ctx, ASTNode tableRef, ASTNode tableRef2, ASTNode marker) {
        collectColumns(ctx, StringUtils.discloseDoubleQuotes(tableRef2.getText()));
    }

    @SyntaxTreePath("/#ALTER #TABLE 1#TABLE_REF #ADD #A_COLUMN_DEF/#COLUMN_NAME_DDL $TypeSpec #ERROR_TOKEN_A/#PRIMARY 2#C_MARKER")
    public void process$addPKOnColumn(C_Context ctx, ASTNode tableRef, ASTNode marker) {
        final boolean doFinalize = is2ndLatest(tableRef.getTreeParent(), marker);
        ctx.addElement(KeywordLookupElement.create("key", false, doFinalize));
    }

    @SyntaxTreePath("/#ALTER #TABLE 1#TABLE_REF #ADD #A_COLUMN_DEF/#COLUMN_NAME_DDL $TypeSpec #ERROR_TOKEN_A/#CONSTRAINT #CONSTRAINT_NAME #REFERENCES #C_MARKER")
    public void process$addConstraintOnColumnReferences(C_Context ctx, ASTNode tableRef) {
        collectTableNames(ctx);
    }

    @SyntaxTreePath("/#ALTER #TABLE #TABLE_REF #ADD #A_COLUMN_DEF/..#COLUMN_FK_SPEC/#REFERENCES 1#TABLE_REF #OPEN_PAREN #COLUMN_NAME_REF/#C_MARKER")
    public void process$addColumnVarsFOrFK(C_Context ctx, ASTNode tableRef) {
        final String tableName = StringUtils.discloseDoubleQuotes(tableRef.getText());
        collectColumns(ctx, tableName);
    }

}
