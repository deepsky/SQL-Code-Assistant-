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


import com.deepsky.lang.PsiUtil;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.completion.SyntaxTreePath;
import com.deepsky.lang.plsql.completion.VariantsProvider;
import com.deepsky.lang.plsql.completion.lookups.*;
import com.deepsky.lang.plsql.completion.lookups.ddl.AlterIndexLookupElement;
import com.deepsky.lang.plsql.completion.lookups.ddl.AlterTableLookupElement;
import com.deepsky.lang.plsql.completion.lookups.dml.CommentLookupElement;
import com.deepsky.lang.plsql.completion.lookups.dml.InsertLookupElement;
import com.deepsky.lang.plsql.completion.lookups.dml.SelectLookupElement;
import com.deepsky.lang.plsql.completion.lookups.plsql.PlSqlPackageLookupElement;
import com.deepsky.lang.plsql.completion.syntaxTreePath.ClassUtil;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ref.TableRef;
import com.deepsky.utils.StringUtils;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@SyntaxTreePath("/..#ERROR_TOKEN_A")
public class ErrorNodeProcessor extends CompletionBase {

    @SyntaxTreePath("/1#C_MARKER")
    public void process$Start(C_Context ctx, ASTNode node) {
        completeStartWithPlSqlBlock(ctx);
    }

    @SyntaxTreePath("/#CREATE 1#C_MARKER")
    public void process$Create(C_Context ctx, ASTNode node) {
        ctx.addElement(PlSqlPackageLookupElement.createPackage());
        ctx.addElement(PlSqlPackageLookupElement.createPackageBody());
    }

    @SyntaxTreePath("/..#SEMI #C_MARKER")
    public void process$Start3(C_Context ctx) {
        completeStartWithPlSqlBlock(ctx);
    }

    @SyntaxTreePath("/#DELETE #FROM #TABLE_ALIAS #WHERE_CONDITION 1#C_MARKER")
    public void process$Start5(C_Context ctx, ASTNode node) {
        if (is2ndLatest(node.getTreeParent(), node)) {
            completeStart(ctx);
        } else {
            ctx.addElement(KeywordLookupElement.create("exists"));
            ctx.addElement(KeywordLookupElement.create("or"));
            ctx.addElement(KeywordLookupElement.create("and"));
        }
    }

    @SyntaxTreePath("/#CREATE #SEQUENCE 1#SEQUENCE_NAME .. #C_MARKER")
    public void process$Start6(C_Context ctx, ASTNode node) {
        completeStart(ctx);
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///     SELECT
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @SyntaxTreePath("/#SELECT #ASTERISK 1#C_MARKER")
    public void process$SelectAsterisk(C_Context ctx, ASTNode marker) {
        ctx.addElement(KeywordLookupElement.create("from"));
    }

    @SyntaxTreePath("/#ERROR_TOKEN_A/#SELECT #ASTERISK_COLUMN 1#C_MARKER")
    public void process$SelectAsteriskColumn(C_Context ctx, ASTNode marker) {
        ctx.addElement(KeywordLookupElement.create("from"));
    }

    @SyntaxTreePath("/#ERROR_TOKEN_A/#SELECT ..#EXPR_COLUMN/..#ALIAS_NAME/#ALIAS_IDENT/1#C_MARKER")
    public void process$SelectCount(C_Context ctx, ASTNode marker) {
        ctx.addElement(KeywordLookupElement.create("from"));
    }

    @SyntaxTreePath("/#ERROR_TOKEN_A/#SELECT ..#EXPR_COLUMN/1#VAR_REF/#NAME_FRAGMENT/#C_MARKER")
    public void process$SelectSysFunc(C_Context ctx, ASTNode varRef) {
        if(varRef.getChildren(null).length == 1){
            collectSystemFunctions(ctx);
            ctx.addElement(CaseExpressionLookupElement.createCase());
            ctx.addElement(KeywordLookupElement.create("systimestamp"));
            ctx.addElement(KeywordLookupElement.create("sysdate"));
            ctx.addElement(KeywordLookupElement.create("sessiontimezone"));
            ctx.addElement(KeywordLookupElement.create("dbtimezone"));
        }

    }

    @SyntaxTreePath("/#SELECT ..#EXPR_COLUMN/..#ALIAS_NAME/#ALIAS_IDENT/1#C_MARKER")
    public void process$SelectCompleteFrom2(C_Context ctx, ASTNode marker) {
        ctx.addElement(KeywordLookupElement.create("from"));
    }

    @SyntaxTreePath("/#SELECT 1#C_MARKER")
    public void process$Select(C_Context ctx, ASTNode marker) {
        collectSystemFunctions(ctx);
        ctx.addElement(KeywordLookupElement.create("systimestamp"));
        ctx.addElement(KeywordLookupElement.create("sysdate"));
        ctx.addElement(KeywordLookupElement.create("sessiontimezone"));
        ctx.addElement(KeywordLookupElement.create("dbtimezone"));
        ctx.addElement(CaseExpressionLookupElement.createCase());
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///     CASE WHEN
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @SyntaxTreePath("/#SELECT #CASE 1#C_MARKER")
    public void process$SelectCase(C_Context ctx, ASTNode marker) {
        ctx.addElement(CaseExpressionLookupElement.createCaseWhen());
    }

//    @SyntaxTreePath("/#SELECT ..#ERROR_TOKEN_A/#ERROR_TOKEN_A/#CASE #WHEN 1#VAR_REF/#NAME_FRAGMENT/#C_MARKER")
//    public void process$SelectCaseWhen(C_Context ctx, ASTNode varRef) {
//        if(varRef.getChildren(null).length == 1){
//            ctx.addElement(KeywordLookupElement.create("systimestamp"));
//            ctx.addElement(KeywordLookupElement.create("sysdate"));
//            ctx.addElement(KeywordLookupElement.create("sessiontimezone"));
//            ctx.addElement(KeywordLookupElement.create("dbtimezone"));
//        }
//    }


//    @SyntaxTreePath("/#SELECT ..#ERROR_TOKEN_A//..#ERROR_TOKEN_A/#CASE #WHEN $Condition #C_MARKER")
//    public void process$SelectCaseWhenThen(C_Context ctx) {
//        ctx.addElement(CaseExpressionLookupElement.createCaseWhenThen());
//    }

    @SyntaxTreePath("/#SELECT ..#EXPR_COLUMN//..#ERROR_TOKEN_A/#CASE 1#VAR_REF/#NAME_FRAGMENT/#C_MARKER")
    public void process$SelectCase2(C_Context ctx, ASTNode varRef) {
        if(varRef.getChildren(null).length == 1){
            ctx.addElement(CaseExpressionLookupElement.createCaseWhen());
        }
    }

//    @SyntaxTreePath("/#SELECT ..#EXPR_COLUMN//..#ERROR_TOKEN_A/#CASE #WHEN 1#VAR_REF/#NAME_FRAGMENT/#C_MARKER")
//    public void process$SelectCaseWhen2(C_Context ctx, ASTNode varRef) {
//        if(varRef.getChildren(null).length == 1){
//            ctx.addElement(KeywordLookupElement.create("systimestamp"));
//            ctx.addElement(KeywordLookupElement.create("sysdate"));
//            ctx.addElement(KeywordLookupElement.create("sessiontimezone"));
//            ctx.addElement(KeywordLookupElement.create("dbtimezone"));
//        }
//    }

    @SyntaxTreePath("/#SELECT ..1#EXPR_COLUMN//..#ERROR_TOKEN_A/#CASE #WHEN $Condition #THEN 2#VAR_REF/#NAME_FRAGMENT/#C_MARKER")
    public void process$SelectCaseWhenThenExpr2(C_Context ctx, ASTNode columnExpr, ASTNode varRef) {
        if(varRef.getChildren(null).length == 1){
            ctx.addElement(KeywordLookupElement.create("systimestamp"));
            ctx.addElement(KeywordLookupElement.create("sysdate"));
            ctx.addElement(KeywordLookupElement.create("sessiontimezone"));
            ctx.addElement(KeywordLookupElement.create("dbtimezone"));
            ctx.addElement(CaseExpressionLookupElement.createCaseWhenThenWhen());
        }
    }

//    @SyntaxTreePath("/#SELECT ..1#EXPR_COLUMN//..#ERROR_TOKEN_A/#CASE #WHEN $Condition #THEN $Expression #ELSE 2#VAR_REF/#NAME_FRAGMENT/#C_MARKER")
//    public void process$SelectCaseWhenThenElseExpr2(C_Context ctx, ASTNode columnExpr, ASTNode varRef) {
//        if(varRef.getChildren(null).length == 1){
//            ctx.addElement(KeywordLookupElement.create("systimestamp"));
//            ctx.addElement(KeywordLookupElement.create("sysdate"));
//            ctx.addElement(KeywordLookupElement.create("sessiontimezone"));
//            ctx.addElement(KeywordLookupElement.create("dbtimezone"));
//        }
//    }

    @SyntaxTreePath("/#SELECT ..#ERROR_TOKEN_A//..#ERROR_TOKEN_A/#CASE #WHEN $Condition #THEN 1#VAR_REF/#NAME_FRAGMENT/#C_MARKER")
    public void process$SelectCaseWhenThenExpr(C_Context ctx, ASTNode varRef) {
        if(varRef.getChildren(null).length == 1){
            ctx.addElement(KeywordLookupElement.create("systimestamp"));
            ctx.addElement(KeywordLookupElement.create("sysdate"));
            ctx.addElement(KeywordLookupElement.create("sessiontimezone"));
            ctx.addElement(KeywordLookupElement.create("dbtimezone"));
        }
    }

//    @SyntaxTreePath("/#SELECT ..#ERROR_TOKEN_A//..#ERROR_TOKEN_A/#CASE #WHEN $Condition #THEN $Expression #C_MARKER")
//    public void process$SelectCaseWhenThenExpr2(C_Context ctx) {
//        ctx.addElement(CaseExpressionLookupElement.createCaseWhenThenWhen());
//        ctx.addElement(KeywordLookupElement.create("else"));
//        ctx.addElement(KeywordLookupElement.create("end"));
//    }
//

    @SyntaxTreePath("/#SELECT #ERROR_TOKEN_A/..$Expression/..1#VAR_REF/#NAME_FRAGMENT/#C_MARKER")
    public void process$SelectCompleteExpr(C_Context ctx, ASTNode varRef) {
        if(varRef.getChildren(null).length == 1){
            collectSystemFunctions(ctx);
            ctx.addElement(KeywordLookupElement.create("systimestamp"));
            ctx.addElement(KeywordLookupElement.create("sysdate"));
            ctx.addElement(KeywordLookupElement.create("sessiontimezone"));
            ctx.addElement(KeywordLookupElement.create("dbtimezone"));
        }
    }

    @SyntaxTreePath("/#SELECT 1#IDENTIFIER 2#C_MARKER")
    public void process$SelectIdent(C_Context ctx, ASTNode ident, ASTNode marker) {
        ctx.addElement(KeywordLookupElement.create("from"));
    }

    @SyntaxTreePath("/#SELECT ..#EXPR_COLUMN #COMMA 1#ERROR_TOKEN_A/#IDENTIFIER #C_MARKER")
    public void process$SelectIdent3(C_Context ctx, ASTNode error) {
        ctx.addElement(KeywordLookupElement.create("from"));
    }

    // select (select 1-2 from dual) asd, (select <caret>) t
    @SyntaxTreePath("/#SELECT ..#EXPR_COLUMN #COMMA #ERROR_TOKEN_A/..#SUBQUERY_EXPR//#OPEN_PAREN #SELECT #C_MARKER")
    public void process$SelectExpr() {
        // TODO - implement me
    }

    // select (select 1-2 from dual) asd, (select <caret>) t
    @SyntaxTreePath("/#SELECT ..#COMMA #EXPR_COLUMN/..#SUBQUERY_EXPR//#OPEN_PAREN 2$SelectStatement/..$FromClause/..#TABLE_ALIAS/..#TABLE_REF/#C_MARKER")
    public void process$SelectSubqueryExpr() {
        // TODO - implement me
    }


    @SyntaxTreePath("//..$FromClause ..#ERROR_TOKEN_A/#ORDER #C_MARKER")
    public void process$SelectOrder() {
        // TODO - implement me
    }

    @SyntaxTreePath("/#SELECT .. #EXPR_COLUMN #COMMA #ERROR_TOKEN_A/1#C_MARKER")
    public void process$SelectColumnCommaMarker(C_Context ctx, ASTNode marker) {
        collectSystemFunctions(ctx);
        ctx.addElement(KeywordLookupElement.create("systimestamp"));
        ctx.addElement(KeywordLookupElement.create("sysdate"));
        ctx.addElement(KeywordLookupElement.create("sessiontimezone"));
        ctx.addElement(KeywordLookupElement.create("dbtimezone"));
    }

    @SyntaxTreePath("/#SELECT .. 1#EXPR_COLUMN 2#C_MARKER")
    public void process$SelectColumnMarker() {
        // TODO - implement me
    }

    @SyntaxTreePath("/#SELECT .. 1#EXPR_COLUMN #AS 2#C_MARKER")
    public void process$SelectColumnAsMarker() {
        // TODO - implement me
    }

    // select asd, t from a order by c1 <caret>
    // select pop from tab1 group by pop <caret>
    @SyntaxTreePath("/1$SelectStatement ..2#C_MARKER")
    public void process$SelectAppender(C_Context ctx, SelectStatement select, ASTNode marker) {
        ASTNode last = select.getNode().getLastChildNode();
        ASTNode next = PsiUtil.nextVisibleSibling(marker);
        final boolean isSemiLast = next != null && next.getElementType() == PlSqlTokenTypes.SEMI;

        if (last.getElementType() == PlSqlElementTypes.TABLE_REFERENCE_LIST_FROM) {
            // select * from ...
            if (!isSemiLast) {
                completeStart(ctx);
            }
            ctx.addElement(KeywordLookupElement.create("where"));
            ctx.addElement(OrderByLookupElement.create());
            ctx.addElement(GroupByLookupElement.create());

            // Check against INNER/LEFT/RIGHT/FULL table alias name if existing
            FromClause from = (FromClause) last.getPsi();
            GenericTable[] tables = from.getTableList();
            String tableAlias = tables[tables.length - 1].getAlias();
            ctx.addElement(SelectLookupElement.createInnerJoin(SelectLookupElement.NO_PREFIX, false));
            ctx.addElement(SelectLookupElement.createLeftJoin(SelectLookupElement.NO_PREFIX, false));
            ctx.addElement(SelectLookupElement.createRightJoin(SelectLookupElement.NO_PREFIX, false));
            ctx.addElement(SelectLookupElement.createFullJoin(SelectLookupElement.NO_PREFIX, false));

        } else if (last.getElementType() == PlSqlElementTypes.WHERE_CONDITION) {
            // select * from ... where...
            if (!isSemiLast) {
                completeStart(ctx);
            }

            ctx.addElement(OrderByLookupElement.create());
            ctx.addElement(GroupByLookupElement.create());
            ctx.addElement(KeywordLookupElement.create("or"));
            ctx.addElement(KeywordLookupElement.create("and"));
            ctx.addElement(KeywordLookupElement.create("like"));
        } else if (last.getElementType() == PlSqlElementTypes.ORDER_CLAUSE) {
            // select * from ... order by ..
            if (!isSemiLast) {
                completeStart(ctx);
            }
        } else if (last.getElementType() == PlSqlElementTypes.GROUP_CLAUSE) {
            // select * from ... group by ..
            if (!isSemiLast) {
                completeStart(ctx);
            }
            ctx.addElement(OrderByLookupElement.create());
            ctx.addElement(GroupByLookupElement.createHaving());
        }
    }

    @SyntaxTreePath("/1$SelectStatement #INNER 2#C_MARKER")
    public void process$SelectInner(C_Context ctx, SelectStatement select, ASTNode marker) {
        ctx.addElement(SelectLookupElement.createInnerJoin(SelectLookupElement.PREFIX, false));
    }

    @SyntaxTreePath("/1$SelectStatement #LEFT 2#C_MARKER")
    public void process$SelectLeft(C_Context ctx, SelectStatement select, ASTNode marker) {
        ctx.addElement(SelectLookupElement.createLeftJoin(SelectLookupElement.PREFIX, false));
        ctx.addElement(SelectLookupElement.createLeftOuterJoin(SelectLookupElement.PREFIX, false));
    }

    @SyntaxTreePath("/1$SelectStatement #RIGHT 2#C_MARKER")
    public void process$SelectRight(C_Context ctx, SelectStatement select, ASTNode marker) {
        ctx.addElement(SelectLookupElement.createRightJoin(SelectLookupElement.PREFIX, false));
        ctx.addElement(SelectLookupElement.createRightOuterJoin(SelectLookupElement.PREFIX, false));
    }

    @SyntaxTreePath("/1$SelectStatement #FULL 2#C_MARKER")
    public void process$SelectFull(C_Context ctx, SelectStatement select, ASTNode marker) {
        ctx.addElement(SelectLookupElement.createFullJoin(SelectLookupElement.PREFIX, false));
        ctx.addElement(SelectLookupElement.createFullOuterJoin(SelectLookupElement.PREFIX, false));
    }


    @SyntaxTreePath("//..#EXISTS_EXPR/#EXISTS #ERROR_TOKEN_A/#C_MARKER")
    public void process$ExistsExpr(C_Context ctx) {
        ctx.addElement(SelectLookupElement.createSubquery());
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///     CREATE TABLE
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @SyntaxTreePath("/#CREATE #TABLE #TABLE_NAME_DDL #AS #SELECT_EXPRESSION #C_MARKER")
    public void process$CreateTabAsSelect(C_Context ctx) {
        completeStart(ctx);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///     COMMENT
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @SyntaxTreePath("/#COMMENT #C_MARKER")
    public void process$Comment(C_Context ctx) {
        ctx.addElement(CommentLookupElement.createOnColumn());
        ctx.addElement(CommentLookupElement.createOnTable());
    }

    @SyntaxTreePath("/#COMMENT #ON #C_MARKER")
    public void process$CommentOn(C_Context ctx) {
        ctx.addElement(CommentLookupElement.createColumn());
        ctx.addElement(CommentLookupElement.createTable());
    }

    @SyntaxTreePath("/#COMMENT #ON #TABLE #C_MARKER")
    public void process$CommentOnTable(C_Context ctx) {
        collectTableNames(ctx);
    }

    @SyntaxTreePath("/#COMMENT #ON #COLUMN #TABLE_REF/#C_MARKER")
    public void process$CommentOnColumn(C_Context ctx) {
        collectTableNames(ctx, new InsertHandler<LookupElement>() {
            @Override
            public void handleInsert(InsertionContext context, LookupElement item) {
                final Editor editor = context.getEditor();
                String prefix = item.getLookupString() + ".";
                editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                editor.getCaretModel().moveToOffset(context.getTailOffset());
                final Document document = editor.getDocument();
                PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
                LookupUtils.scheduleAutoPopup(editor, context);
            }
        });
    }

    @SyntaxTreePath("/#COMMENT #ON #COLUMN 1#TABLE_REF #DOT 2#COLUMN_NAME_REF #C_MARKER")
    public void process$CommentOnColumnTail(C_Context ctx, ASTNode tabRef, ASTNode columnRef) {
        ctx.addElement(CommentLookupElement.createTail(tabRef.getText(), columnRef.getText()));
    }

    @SyntaxTreePath("/#COMMENT #ON #TABLE 1#TABLE_REF #C_MARKER")
    public void process$CommentOnTableTail(C_Context ctx, ASTNode tabRef) {
        ctx.addElement(CommentLookupElement.createTail(tabRef.getText(), null));
    }

    @SyntaxTreePath("/#COMMENT #ON #TABLE 1#IDENTIFIER #C_MARKER")
    public void process$CommentOnTableTail2(C_Context ctx, ASTNode tabRef) {
        ctx.addElement(CommentLookupElement.createTail(tabRef.getText(), null));
    }

    // COMMENT ON COLUMN XSL_RPT_PARAM_T.ID IS 'Unique ID for each record created in this table.'
    @SyntaxTreePath("/#COMMENT #ON #COLUMN 1#TABLE_REF #DOT #COLUMN_NAME_REF/#C_MARKER")
    public void process$CommentOnColumn2(C_Context ctx, ASTNode tableRef) {
        VariantsProvider provider = ctx.getProvider();
        final List<LookupElement> variants = new ArrayList<LookupElement>();

        String tableName = StringUtils.discloseDoubleQuotes(tableRef.getText());
        variants.addAll(provider.collectColumnNames(tableName, ctx.getLookup()));
        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///     UPDATE
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @SyntaxTreePath("/1#SIMPLE_UPDATE_COMMAND #C_MARKER")
    public void process$UpdateStart(C_Context ctx, ASTNode update) {
        // TODO -- too complex , subject to review
        ASTNode last = update.getLastChildNode();
        if (last.getElementType() == PlSqlElementTypes.ERROR_TOKEN_A) {
            // Try to identify details of the errored node
            ASTNode prev = PsiUtil.prevVisibleSibling(last);
            if (prev != null && prev.getElementType() == PlSqlTokenTypes.KEYWORD_SET) {
                if (last.getChildren(null).length == 1) {
                    if (last.getChildren(null)[0].getElementType() == PlSqlTokenTypes.IDENTIFIER) {
                        ctx.addElement(KeywordLookupElement.create("="));
                    }
                }
            }
        } else {
            completeStart(ctx);
            ctx.addElement(KeywordLookupElement.create("where"));
        }
    }

    @SyntaxTreePath("/#ERROR_TOKEN_A/#UPDATE $TableAlias/..#ALIAS_NAME/#ALIAS_IDENT/#C_MARKER")
    public void process$UpdateTabSet(C_Context ctx) {
        ctx.addElement(KeywordLookupElement.create("set"));
    }

    @SyntaxTreePath("/#ERROR_TOKEN_A/#UPDATE $TableAlias/#TABLE_REF/#C_MARKER")
    public void process$UpdateTab(C_Context ctx) {
        collectTableNames(ctx);
    }

    @SyntaxTreePath("/#SIMPLE_UPDATE_COMMAND/#UPDATE $TableAlias/#TABLE_REF/#C_MARKER")
    public void process$UpdateTab2(C_Context ctx) {
        collectTableNames(ctx);
    }

    @SyntaxTreePath("/#SUBQUERY_UPDATE_COMMAND/#UPDATE 1$TableAlias #SET #ERROR_TOKEN_A/2$ColumnSpecList/..#COLUMN_SPEC/#NAME_FRAGMENT/#C_MARKER")
    public void process$SubqueryUpdate(C_Context ctx, TableAlias t, ColumnSpecList l) {
        collectColumns(ctx, t, false);
        // TODO - filter out columns using ColumnSpecList
    }

    @SyntaxTreePath("/#SUBQUERY_UPDATE_COMMAND/#UPDATE 1$TableAlias #SET 2$ColumnSpecList/..#COLUMN_SPEC/#NAME_FRAGMENT/#C_MARKER")
    public void process$SubqueryUpdate2(C_Context ctx, TableAlias t, ColumnSpecList l) {
        collectColumns(ctx, t, false);
        // TODO - filter out columns using ColumnSpecList
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///     INSERT
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @SyntaxTreePath("/1#INSERT_COMMAND 2#C_MARKER")
    public void process$InsertStart(C_Context ctx, ASTNode insert, ASTNode marker) {
        InsertStatement insertStatement = (InsertStatement) insert.getPsi();
        if (is2ndLatest(insert.getTreeParent(), marker)) {
            // Marker is the last element of the statement
            SelectStatement select = insertStatement.getSubquery();
            if (select != null) {
                if (select.getWhereCondition() == null)
                    ctx.addElement(KeywordLookupElement.create("where"));
                if (select.getGroupByClause() == null)
                    ctx.addElement(GroupByLookupElement.create());
                if (select.getOrderByClause() == null)
                    ctx.addElement(OrderByLookupElement.create());

            }
            completeStart(ctx);
        } else {
            // Marker is in the middle of the statement
            // Check whether WHERE clause exists
            SelectStatement select = insertStatement.getSubquery();
            if (select != null) {
                if (select.getWhereCondition() == null)
                    ctx.addElement(KeywordLookupElement.create("where"));
                if (select.getGroupByClause() == null) ctx.addElement(GroupByLookupElement.create());
                if (select.getOrderByClause() == null) ctx.addElement(OrderByLookupElement.create());
            }
        }
    }

    // INSERT related
    @SyntaxTreePath("/#INSERT #C_MARKER")
    public void process$InsertInto(C_Context ctx) {
        ctx.addElement(KeywordLookupElement.create("into"));
    }

    // INSERT related
    @SyntaxTreePath("/#INSERT #INTO $TableAlias/#TABLE_REF/#C_MARKER")
    public void process$InsertIntoTab(C_Context ctx) {
        collectTableNames(ctx);
    }

    @SyntaxTreePath("/#ERROR_TOKEN_A/#INSERT #INTO $TableAlias/#TABLE_REF/#C_MARKER")
    public void process$InsertIntoTab2(C_Context ctx) {
        collectTableNames(ctx);
    }


    // insert into tab <caret>
    @SyntaxTreePath("/#INSERT #INTO 1$TableAlias/..#ALIAS_NAME/#ALIAS_IDENT/#C_MARKER")
    public void process$InsertIntoTab3(C_Context ctx, TableAlias tableAlias) {
        ctx.addElement(
                InsertLookupElement.createInsertIntoTab(ctx.getProvider(), tableAlias.getTableName()));
    }

    // insert into tab <caret>
    @SyntaxTreePath("/#ERROR_TOKEN_A/#INSERT #INTO 1$TableAlias/..#ALIAS_NAME/#ALIAS_IDENT/#C_MARKER")
    public void process$InsertIntoTab4(C_Context ctx, TableAlias ta) {
        ctx.addElement(InsertLookupElement.createInsertIntoTabColumnList(ctx.getProvider(), ta.getTableName()));
        ctx.addElement(InsertLookupElement.createInsertIntoTab(ctx.getProvider(), ta.getTableName()));
    }


    // insert into tab <caret>
    @SyntaxTreePath("/#ERROR_TOKEN_A/#INSERT #INTO 1$TableAlias $ColumnSpecList/..$ColumnSpec/..#NAME_FRAGMENT/#C_MARKER")
    public void process$InsertIntoTabColumn(C_Context ctx, TableAlias ta) {
        collectColumns(ctx, ta, false);
    }

    @SyntaxTreePath("/#INSERT #INTO 1$TableAlias $ColumnSpecList/..2$ColumnSpec/..#NAME_FRAGMENT/#C_MARKER")
    public void process$InsertColumnName(C_Context ctx, TableAlias t, ColumnSpec nameRef) {
        VariantsProvider provider = ctx.getProvider();
        provider.collectColumnNames(t, ctx.getLookup(), false);

        // TODO - filter out columns already existing in column list
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.takeCollectedLookups());

        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }

    @SyntaxTreePath("/#INSERT #INTO 1$TableAlias 2$ColumnSpecList #C_MARKER")
    public void process$InsertValues(C_Context ctx, TableAlias ta, ColumnSpecList list) {
        ctx.addElement(InsertLookupElement.createInsertFromSelect(ta.getTableName(), list));
        ctx.addElement(KeywordLookupElement.create("values", true, new InsertHandler<LookupElement>() {
            @Override
            public void handleInsert(InsertionContext context, LookupElement item) {
                final Editor editor = context.getEditor();
                String prefix = "values ();";
                editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                editor.getCaretModel().moveToOffset(context.getTailOffset() - 2);
                final Document document = editor.getDocument();
                PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
            }
        }));
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///     DROP
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @SyntaxTreePath("/#DROP #TABLE #IDENTIFIER #C_MARKER")
    public void process$DropTableStart(C_Context ctx) {
        completeStart(ctx);
        // TODO implement more case
    }

    @SyntaxTreePath("/#DROP #VIEW #IDENTIFIER #C_MARKER")
    public void process$DropViewStart(C_Context ctx) {
        completeStart(ctx);
        // TODO implement more case
    }

    @SyntaxTreePath("/#DROP #SEQUENCE #IDENTIFIER #C_MARKER")
    public void process$DropSeqStart(C_Context ctx) {
        completeStart(ctx);
        // TODO implement more case
    }

    @SyntaxTreePath("/#DROP #INDEX #IDENTIFIER #C_MARKER")
    public void process$DropIndexStart(C_Context ctx) {
        completeStart(ctx);
        // TODO implement more case
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///     ALTER
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @SyntaxTreePath("/..#ALTER #C_MARKER")
    public void process$Alter(C_Context ctx) {
        ctx.addElement(AlterTableLookupElement.create());
        ctx.addElement(AlterIndexLookupElement.create());
    }

    @SyntaxTreePath("/1#DROP #C_MARKER")
    public void process$AlterTableDropPK(C_Context ctx, ASTNode drop) {
        ctx.addElement(KeywordLookupElement.create("table"));
        ctx.addElement(KeywordLookupElement.create("view"));
        ctx.addElement(KeywordLookupElement.create("procedure"));
        ctx.addElement(KeywordLookupElement.create("function"));
        ctx.addElement(KeywordLookupElement.create("sequence"));
        ctx.addElement(KeywordLookupElement.create("trigger"));
//        ASTNode errorNode = drop.getTreeParent();
//        ASTNode prev = PsiUtil.prevVisibleSibling(errorNode);
//        if(prev != null && prev.getElementType() == PlSqlElementTypes.ALTER_TABLE){
//            ctx.addElement(KeywordLookupElement.create("index"));
//        }
    }

/*
    @SyntaxTreePath("/..1#ALTER_TABLE 1#C_MARKER")
    public void process$Start4(C_Context ctx, ASTNode alterTable, ASTNode caret) {
        ASTNode nextLeaf = PsiUtil.nextNonWSLeaf(caret);
        boolean nextSemi = nextLeaf != null && nextLeaf.getElementType() == PlSqlTokenTypes.SEMI;

        List<ASTNode> childList = getChildren(alterTable);
        if (match(childList, "ALTER ERROR_TOKEN_A")) {
            List<ASTNode> nodes = getChildren(childList.get(1));
            if (match(nodes, "TABLE IDENTIFIER")) {
                ctx.addElement(AlterTableLookupElement.createAddColumn(nodes.get(1).getText()));
                ctx.addElement(AlterTableLookupElement.createDrop(nodes.get(1).getText()));
                ctx.addElement(AlterTableLookupElement.createRename(nodes.get(1).getText()));
                return;
            }
        } else if (childList.get(childList.size() - 1).getElementType() == PlSqlElementTypes.ALTER_TABLE_DROP_PK) {
            final ASTNode lastNode = childList.get(childList.size() - 1);
            if (match(lastNode, "PRIMARY KEY")) {
                ctx.addElement(AlterTableLookupElement.createDropPKCascade());
                ctx.addElement(AlterTableLookupElement.createDropPK_DropIndex());
                ctx.addElement(AlterTableLookupElement.createDropPK_KeepIndex());
            } else if (match(lastNode, "PRIMARY KEY CASCADE")) {
                ctx.addElement(AlterTableLookupElement.createDropPK_DropIndex());
                ctx.addElement(AlterTableLookupElement.createDropPK_KeepIndex());
            } else if (match(lastNode, "PRIMARY KEY KEEP INDEX")) {
                //ctx.addElement(AlterTableLookupElement.createDropPKCascade());
            } else if (match(lastNode, "PRIMARY KEY DROP INDEX")) {
                //ctx.addElement(AlterTableLookupElement.createDropPKCascade());
            }
        } else if (childList.get(childList.size() - 1).getElementType() == PlSqlElementTypes.ALTER_TABLE_DROP_CONSTR) {
            final ASTNode lastNode = childList.get(childList.size() - 1);
            if (match(lastNode, "CONSTRAINT CONSTRAINT_NAME")) {
                ctx.addElement(KeywordLookupElement.create("cascade"));
            }
        } else if (match(childList, "ALTER TABLE TABLE_REF ADD A_COLUMN_DEF")) {
//            final ASTNode lastNode = childList.get(childList.size() - 1);
//            if (match(lastNode, "COLUMN_NAME_DDL DATATYPE ERROR_TOKEN_A")) {
//                ctx.addElement(KeywordLookupElement.create("cascade"));
//            }
            ctx.addElement(AlterTableLookupElement.createAddColumnPK());
            ctx.addElement(AlterTableLookupElement.createAddColumnFK());
            ctx.addElement(AlterTableLookupElement.createAddColumnNotNull());
            ctx.addElement(AlterTableLookupElement.createAddConstraint());
            ctx.addElement(KeywordLookupElement.create("default"));
        }

        if(!nextSemi){
            completeStart(ctx);
        }
    }
*/
//    @SyntaxTreePath("/..1#ALTER_TABLE 1#C_MARKER")
//    public void process$Start4(C_Context ctx, ASTNode alterTable, ASTNode caret) {
//        int hh = 0;
//    }

    @SyntaxTreePath("/..#ALTER_TABLE/#ALTER #TABLE 1#TABLE_REF #ADD #A_COLUMN_DEF/2#COLUMN_NAME_DDL $TypeSpec #ERROR_TOKEN_A/#CONSTRAINT #CONSTRAINT_NAME/3#C_MARKER")
    public void process$generateConstraintName(C_Context ctx, ASTNode tableRef, ASTNode column, ASTNode marker) {
        final String tableName = StringUtils.discloseDoubleQuotes(tableRef.getText());
        final String columnName = StringUtils.discloseDoubleQuotes(column.getText());

        ctx.addElement(AlterTableLookupElement.buildAddConstraintName(tableName, columnName));
    }

    @SyntaxTreePath("/..1#ALTER_TABLE(/#ALTER ..#ADD #A_COLUMN_DEF(/#COLUMN_NAME_DDL $TypeSpec #ERROR_TOKEN_A(/#CONSTRAINT #CONSTRAINT_NAME #REFERENCES 2#IDENTIFIER))) 3#C_MARKER")
    public void process$AlterTabReferencesTabColumn(C_Context ctx, ASTNode alterTable, ASTNode tableRef, ASTNode marker) {
        final boolean doFinalize = is2ndLatest(alterTable.getTreeParent(), marker);
        final String tableName = StringUtils.discloseDoubleQuotes(tableRef.getText());
        ctx.addElement(AlterTableLookupElement.createAddColumnFK(tableName));
    }

    @SyntaxTreePath("/..1#ALTER_TABLE(/#ALTER .. #SEMI) 3#C_MARKER")
    public void process$AlterTabEnd(C_Context ctx, ASTNode alterTable, ASTNode caret) {
        completeStart(ctx);
    }

    @SyntaxTreePath("/..1#ALTER_TABLE(/#ALTER #ERROR_TOKEN_A(/#TABLE 2$PsiElement)) 3#C_MARKER")
    public void process$StartTest1111(C_Context ctx, ASTNode alterTable, PsiElement tabName, ASTNode caret) {
        ctx.addElement(AlterTableLookupElement.createAddColumn(tabName.getText()));
        ctx.addElement(AlterTableLookupElement.createDrop(tabName.getText()));
        ctx.addElement(AlterTableLookupElement.createRename(tabName.getText()));
    }

//    @SyntaxTreePath("/..1#ALTER_TABLE(/#ALTER #ERROR_TOKEN_A(/#TABLE 2#IDENTIFIER)) 3#C_MARKER")
//    public void process$StartTest1111(C_Context ctx, ASTNode alterTable, ASTNode tabName, ASTNode caret) {
//        ctx.addElement(AlterTableLookupElement.createAddColumn(tabName.getText()));
//        ctx.addElement(AlterTableLookupElement.createDrop(tabName.getText()));
//        ctx.addElement(AlterTableLookupElement.createRename(tabName.getText()));
//    }

    @SyntaxTreePath("/..1#ALTER_TABLE(/#ALTER #TABLE #TABLE_REF #DROP #ALTER_TABLE_DROP_PK(/#PRIMARY #KEY #CASCADE)) 1#C_MARKER")
    public void process$StartTest1112(C_Context ctx, ASTNode alterTable, ASTNode caret) {
        ASTNode nextLeaf = PsiUtil.nextNonWSLeaf(caret);
        boolean nextSemi = nextLeaf != null && nextLeaf.getElementType() == PlSqlTokenTypes.SEMI;

        ctx.addElement(AlterTableLookupElement.createDropPK_DropIndex());
        ctx.addElement(AlterTableLookupElement.createDropPK_KeepIndex());
        if(!nextSemi){
            completeStart(ctx);
        }
    }

    @SyntaxTreePath("/..1#ALTER_TABLE(/#ALTER #TABLE #TABLE_REF #DROP #ALTER_TABLE_DROP_PK(/#PRIMARY #KEY))  1#C_MARKER")
    public void process$StartTest1115(C_Context ctx, ASTNode alterTable, ASTNode caret) {
        ASTNode nextLeaf = PsiUtil.nextNonWSLeaf(caret);
        boolean nextSemi = nextLeaf != null && nextLeaf.getElementType() == PlSqlTokenTypes.SEMI;

        ctx.addElement(AlterTableLookupElement.createDropPKCascade());
        ctx.addElement(AlterTableLookupElement.createDropPK_DropIndex());
        ctx.addElement(AlterTableLookupElement.createDropPK_KeepIndex());
        if(!nextSemi){
            completeStart(ctx);
        }
    }

    @SyntaxTreePath("/..1#ALTER_TABLE(/#ALTER #TABLE #TABLE_REF #DROP #ALTER_TABLE_DROP_CONSTR(/#CONSTRAINT #CONSTRAINT_NAME)) 1#C_MARKER")
    public void process$StartTest1113(C_Context ctx, ASTNode alterTable, ASTNode caret) {
        ASTNode nextLeaf = PsiUtil.nextNonWSLeaf(caret);
        boolean nextSemi = nextLeaf != null && nextLeaf.getElementType() == PlSqlTokenTypes.SEMI;
        ctx.addElement(KeywordLookupElement.create("cascade"));

        if(!nextSemi){
            completeStart(ctx);
        }
    }

    @SyntaxTreePath("/..1#ALTER_TABLE(/#ALTER #TABLE #TABLE_REF #ADD #A_COLUMN_DEF(/#COLUMN_NAME_DDL $TypeSpec #ERROR_TOKEN_A(/#REFERENCES 2#IDENTIFIER))) #C_MARKER")
    public void process$AlterTableRef(C_Context ctx, ASTNode alterTable, ASTNode tableRef) {
        final String tableName = StringUtils.discloseDoubleQuotes(tableRef.getText());
        ctx.addElement(AlterTableLookupElement.createAddColumnFK(tableName));
    }

    @SyntaxTreePath("/..1#ALTER_TABLE(/#ALTER #TABLE #TABLE_REF #ADD 2#A_COLUMN_DEF(/#COLUMN_NAME_DDL $TypeSpec)) 3#C_MARKER")
    public void process$StartTest1114(C_Context ctx, ASTNode alterTable, ASTNode colDef, ASTNode caret) {
        ASTNode nextLeaf = PsiUtil.nextNonWSLeaf(caret);
        boolean nextSemi = nextLeaf != null && nextLeaf.getElementType() == PlSqlTokenTypes.SEMI;

        ctx.addElement(AlterTableLookupElement.createAddColumnPK(!nextSemi));
        ctx.addElement(AlterTableLookupElement.createAddColumnFK());
        ctx.addElement(AlterTableLookupElement.createAddColumnNotNull(!nextSemi));
        ctx.addElement(AlterTableLookupElement.createAddConstraint());
        ctx.addElement(KeywordLookupElement.create("default"));

        if(!nextSemi){
            completeStart(ctx);
        }
    }

    @SyntaxTreePath("/..#ALTER_TABLE/#ALTER #TABLE 1#TABLE_REF #ERROR_TOKEN_A/#RENAME #COLUMN #COLUMN_NAME_REF/#C_MARKER")
    public void process$alterTableRenameColumn(C_Context ctx, ASTNode tableRef) {
        collectColumns(ctx, ((TableRef)tableRef.getPsi()).getTableName());
    }

    @SyntaxTreePath("/..#ALTER_TABLE/#ALTER #TABLE 1#TABLE_REF #ERROR_TOKEN_A/#RENAME #CONSTRAINT #C_MARKER")
    public void process$alterTableRenameConstraint(final C_Context ctx, ASTNode tableRef) {
        alterTableRenameConstraint(ctx, (TableRef) tableRef.getPsi());
    }

    private boolean match(@NotNull ASTNode astNode, @NotNull String pattern) {
        String[] parts = pattern.split(" ");
        List<ASTNode> childList = getChildren(astNode);
        if (parts.length != childList.size()) {
            return false;
        }

        for (int i = 0; i < childList.size(); i++) {
            if (childList.get(i).getElementType() != ClassUtil.retrieveFieldValue(parts[i])) {
                return false;
            }
        }
        return true;
    }


    private boolean match(@NotNull List<ASTNode> childList, @NotNull String pattern) {
        String[] parts = pattern.split(" ");
        if (parts.length != childList.size()) {
            return false;
        }

        for (int i = 0; i < childList.size(); i++) {
            if (childList.get(i).getElementType() != ClassUtil.retrieveFieldValue(parts[i])) {
                return false;
            }
        }
        return true;
    }

    private List<ASTNode> getChildren(ASTNode parent) {
        List<ASTNode> out = new ArrayList<ASTNode>();
        ASTNode[] childList = parent.getChildren(null);
        for (ASTNode a : childList) {
            final IElementType etype = a.getElementType();
            if (etype != PlSqlTokenTypes.WS && etype != PlSqlTokenTypes.LF && etype != TokenType.WHITE_SPACE
                    && etype != PlSqlTokenTypes.SL_COMMENT && etype != PlSqlTokenTypes.ML_COMMENT) {
                out.add(a);
            }
        }

        return out;
    }
}

