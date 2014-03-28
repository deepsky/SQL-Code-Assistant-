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
import com.deepsky.lang.plsql.completion.VariantsProvider;
import com.deepsky.lang.plsql.completion.logic.TreePathBuilder;
import com.deepsky.lang.plsql.completion.lookups.KeywordLookupElement;
import com.deepsky.lang.plsql.completion.lookups.ddl.AlterTableLookupElement;
import com.deepsky.lang.plsql.completion.lookups.dml.DeleteLookupElement;
import com.deepsky.lang.plsql.completion.lookups.dml.InsertLookupElement;
import com.deepsky.lang.plsql.completion.lookups.dml.SelectLookupElement;
import com.deepsky.lang.plsql.completion.lookups.dml.UpdateLookupElement;
import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.TreePath;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ref.TableRef;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public abstract class CompletionBase {

    // Set of aggregate functions
    private final static Set<String> AGGR_FUNC_NAMES = new HashSet<String>(
            Arrays.asList(new String[]{
                    // TODO -- add more AGGR functions
                    "MAX", "MIN", "COUNT", "SUM", "AVG", "COLLECT", "FIRST", "LAST", "GROUP_ID", "MEDIAN"})
    );


    /**
     * Is the specified expression an aggregate function
     *
     * @param expression expr to test
     * @return true if it is an aggregate like MIN, MAX, etc
     */
    protected boolean isExprAggrFunction(Expression expression) {
        if (expression instanceof Callable) {
            final String name = ((Callable) expression).getFunctionName();
            return AGGR_FUNC_NAMES.contains(name.toUpperCase());
        }
        return false;
    }

    protected void addIfMatch(String reference, List<LookupElement> source, List<LookupElement> out) {
        for (LookupElement e : source) {
            if (e.getLookupString().equalsIgnoreCase(reference.replaceAll(" ", ""))) {
                out.add(e);
                break;
            }
        }
    }

    /**
     * Trivial compare expressions
     *
     * @param expr
     * @param expressions
     * @return
     */
    protected boolean checkExpr(Expression expr, Expression[] expressions) {
        final String exprTest = expr.getText().replaceAll("[\n\t ]", "");
        for (Expression e : expressions) {
            return exprTest.equalsIgnoreCase(e.getText().replaceAll("[\n\t ]", ""));
        }

        return false;
    }


    protected boolean is2ndLatest(@NotNull ASTNode root, @NotNull ASTNode child) {
        if (child.getTextRange().getEndOffset() == root.getTextRange().getEndOffset()) {
            // Looks like child is the latest element,to make sure check against SEMI
            PsiElement next = PsiUtil.nextNonWSLeaf(child.getPsi());
            return !(next != null && next.getNode().getElementType() == PlSqlTokenTypes.SEMI);
        }
        return false;
    }


    protected void collectColumns(@NotNull C_Context ctx, @NotNull SelectStatement select, @Nullable NameFragmentRef nameRef) {
        VariantsProvider provider = ctx.getProvider();
        final NameFragmentRef prev = nameRef != null ? nameRef.getPrevFragment() : null;
        final String prevText = prev != null ? prev.getText() : null;

        // Collect column names
        provider.collectColumnVariants(select, prevText);
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.takeCollectedLookups());

        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }

        // Collect table names/table correlation names as a prefix of column names
        for (LookupElement elem : provider.collectCorrelationOrTableNames(select, ctx.getLookup())){
            ctx.addElement(elem);
        }
    }

    protected void collectColumnsAndSysFunc(@NotNull C_Context ctx, @NotNull SelectStatement select, @Nullable NameFragmentRef nameRef) {
        VariantsProvider provider = ctx.getProvider();
        final NameFragmentRef prev = nameRef != null ? nameRef.getPrevFragment() : null;
        final String prevText = prev != null ? prev.getText() : null;

        provider.collectColumnVariants(select, prevText);

        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.takeCollectedLookups());

        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }

        if (prev == null) {
            ctx.addElement(KeywordLookupElement.create("sysdate"));
            ctx.addElement(KeywordLookupElement.create("systimestamp"));
            ctx.addElement(KeywordLookupElement.create("dbtimezone"));
            ctx.addElement(KeywordLookupElement.create("current_timestamp"));
        }
    }

    protected void collectColumns(C_Context ctx, TableAlias tableName, boolean forceUsingTableAlias) {
        VariantsProvider provider = ctx.getProvider();
        provider.collectColumnNames(tableName, ctx.getLookup(), forceUsingTableAlias);

        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.takeCollectedLookups());

        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }

    protected void collectColumns(C_Context ctx, String tableName) {
        VariantsProvider provider = ctx.getProvider();
        for (LookupElement elem : provider.collectColumnNames(tableName, ctx.getLookup())) {
            ctx.addElement(elem);
        }
    }


    protected void collectTableNames(C_Context ctx) {
        VariantsProvider provider = ctx.getProvider();
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.collectTableNameVariants(ctx.getLookup()));

        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }

    protected void collectTableNames(@NotNull C_Context ctx, @NotNull InsertHandler<LookupElement> insertHandler) {
        VariantsProvider provider = ctx.getProvider();
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.collectTableNameVariants(ctx.getLookup(), insertHandler));

        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }

    protected void collectTableNamesFinalize(C_Context ctx) {
        VariantsProvider provider = ctx.getProvider();
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.collectTableNameVariants(ctx.getLookup(), new InsertHandler<LookupElement>() {
            @Override
            public void handleInsert(InsertionContext context, LookupElement item) {
                final Editor editor = context.getEditor();
                String prefix = item.getLookupString() + ";";
                editor.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), prefix);
                editor.getCaretModel().moveToOffset(context.getTailOffset());
                final Document document = editor.getDocument();
                PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);
            }
        }));

        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }

    protected void collectTableViewNames(C_Context ctx) {
        VariantsProvider provider = ctx.getProvider();
        final List<LookupElement> variants = new ArrayList<LookupElement>();
        variants.addAll(provider.collectTableNameVariants(ctx.getLookup()));
        variants.addAll(provider.collectViewNameVariants(ctx.getLookup()));

        for (LookupElement elem : variants) {
            ctx.addElement(elem);
        }
    }

    protected void completeStart(C_Context ctx) {
        ctx.addElement(SelectLookupElement.create());
//        ctx.addElement(SelectLookupElement.createSelectFromSelect());
        ctx.addElement(InsertLookupElement.create());
        ctx.addElement(UpdateLookupElement.create());
        ctx.addElement(DeleteLookupElement.create());
        ctx.addElement(KeywordLookupElement.create("create"));
        ctx.addElement(KeywordLookupElement.create("drop"));
        ctx.addElement(KeywordLookupElement.create("alter"));
        ctx.addElement(KeywordLookupElement.create("comment"));
    }


    public abstract class TreePathBuilderAbstract implements TreePathBuilder {

        public abstract void processSelectStatement(SelectStatement select);

        @Override
        final public void addNode(ASTNode prev) {
            if (prev.getElementType() == PlSqlElementTypes.SELECT_EXPRESSION) {
                processSelectStatement((SelectStatement) prev.getPsi());
            } else if (prev.getElementType() == PlSqlElementTypes.SELECT_EXPRESSION_UNION) {
                processSelectStatement((SelectStatement) prev.getPsi());
            }
        }

        @Override
        final public void goUp() {
        }

        @Override
        final public TreePath complete() {
            return null;
        }
    }

    protected void alterTableRenameConstraint(final C_Context ctx, TableRef tableRef) {
        VariantsProvider provider = ctx.getProvider();
        String tableName = tableRef.getTableName();

        provider.collectTableConstraints(tableName, new VariantsProvider.TableConstraintProcessor() {
            @Override
            public void process(String constraintName, int constraintType) {
                ctx.addElement(AlterTableLookupElement.createRenameConstraintName(constraintName, constraintType == VariantsProvider.PK_CONSTRAINT));
            }
        });
    }

}
