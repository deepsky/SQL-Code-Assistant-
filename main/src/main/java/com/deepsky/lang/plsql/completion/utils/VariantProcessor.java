/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
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

package com.deepsky.lang.plsql.completion.utils;

import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.lang.plsql.completion.*;
import com.deepsky.lang.plsql.completion.lookups.GenericLookupElement;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.resolve.ASTNodeHandlerAdapter;
import com.deepsky.lang.plsql.psi.resolve.ASTTreeProcessor;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.ContextPathProvider;
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

import java.util.*;

public class VariantProcessor {


    private final static int COLLECT_SYSTEM_FUNC_CALLS = 1;
    private final static int COLLECT_COLUMN = 2; // columns relevant to the usage context and ancestors (SQL stmt context)
    private final static int COLLECT_COLUMN_IN_CTX = 21; // columns relevant to the usage context (current SQL stmt context only)
    private final static int COLLECT_VAR_VARS = 3;         // variables relevant to the usage context (Exec context)
    private final static int COLLECT_PACKAGE_VARS = 4;     // package names
    private final static int COLLECT_PACKAGE_VAR_VARS = 5; // variables in the package
    private final static int COLLECT_USER_FUNC_CALLS = 6;  // calls to the user defined functions
    private final static int COLLECT_USER_PROC_CALLS = 7;  // calls to the user defined procedures
    private final static int COLLECT_DATA_TYPE_VARS = 8;   // data type variants
    private final static int COLLECT_TYPE_NAME_VARS = 9;   // type name (UDT) variants
    //        final int COLLECT_NAMES_IN_CTX_VARS = 10;
    private final static int COLLECT_PARAM_NAME_VARS = 10; // names of proc/func parameters
    //    private final static int COLLECT_COLUMN_SPEC_VARS = 11; // columns in insert/update/merge/delete/select stmts
    private final static int COLLECT_SEQUENCE_REF = 12; // sequence.nextval(curval)



    private VariantsProvider provider;

    public VariantProcessor( VariantsProvider provider){
        this.provider = provider;
    }


    public List<LookupElement> process(final NameFragmentRef nameFragment, final ComplContributor.ContextElementHelper helper) {

        // possible parent's type
        // VAR_REF
        // PLSQL_VAR_REF
        // TYPE_NAME_REF
        // CALLABLE_NAME_REF
        // MEMBER_OF
        // IDENT_ASTERISK_COLUMN
        // PARAMETER_REF
        // COLUMN_SPEC
        // TRIGGER_COLUMN_REF

        final List<LookupElement> out = new ArrayList<LookupElement>();

        final String usageCtxPath = nameFragment.getCtxPath1().getPath();
        final String lookUpStr = stripText(nameFragment.getText());
        final NameFragmentRef prev = nameFragment.getPrevFragment();
        final String prevText = prev != null ? prev.getText() : null;

        ObjectReferenceDispatcher.process(nameFragment, new ObjectReferenceDispatcher.ParentNodeHandler() {

            private void __collect__(int cmd) {
                switch (cmd) {
                    case COLLECT_SYSTEM_FUNC_CALLS:
                        out.addAll(provider.collectSystemFuncCall(lookUpStr));
                        break;
                    case COLLECT_SEQUENCE_REF:
                        out.addAll(provider.collectSequenceVariants(prevText, lookUpStr));
                        break;
                    case COLLECT_COLUMN:
                        processElementContext(helper, new ContextElementProcessor() {
                            public void process(SelectStatement select, int counter) {
                                provider.collectColumnVariants(select, prevText);
                            }

                            public void process(UpdateStatement update) {
                                TableAlias t = update.getTargetTable();
                                provider.collectColumnNames(t, lookUpStr, false);
                            }

                            public void process(InsertStatement insert) {
                                TableAlias t = insert.getIntoTable();
                                provider.collectColumnNames(t, lookUpStr, false);
                            }

                            public void process(DeleteStatement delete) {
                                TableAlias t = delete.getTargetTable();
                                provider.collectColumnNames(t, lookUpStr, false);
                            }
                        });

                        out.addAll(provider.takeCollectedLookups());
                        break;
                    case COLLECT_COLUMN_IN_CTX:
                        provider.collectColumnVariants(helper.findClosestContext(), prevText);
                        out.addAll(provider.takeCollectedLookups());
                        break;
                    case COLLECT_VAR_VARS:
                        out.addAll(provider.collectVarVariants(usageCtxPath, lookUpStr));
                        break;
                    case COLLECT_PACKAGE_VARS:
                        out.addAll(provider.collectPackageVariants(lookUpStr));
                        break;
                    case COLLECT_PACKAGE_VAR_VARS:
                        out.addAll(provider.collectVarVariantsInPackage(prevText, lookUpStr));
                        break;
                    case COLLECT_USER_FUNC_CALLS:
                        out.addAll(provider.collectFuncCall(usageCtxPath, prevText, lookUpStr));
                        break;
                    case COLLECT_DATA_TYPE_VARS:
                        out.addAll(provider.collectDataTypeVariants(usageCtxPath, lookUpStr));
                        break;
                    case COLLECT_TYPE_NAME_VARS:
                        provider.collectTypeNameVariants(usageCtxPath, lookUpStr);
                        break;
                    case COLLECT_USER_PROC_CALLS:
                        out.addAll(provider.collectProcCall(usageCtxPath, prevText, lookUpStr));
                        break;
//                    case COLLECT_NAMES_IN_CTX_VARS:
//                        if(prev != null){
//                            out.addAll(provider.collectNamesInContext(prev,lookUpStr));
//                        }
//                        break;
                    case COLLECT_PARAM_NAME_VARS:
                        out.addAll(provider.collectParametersNames(
                                (ParameterReference) nameFragment.getParent(), lookUpStr)
                        );
                        break;
                }
            }

            public void visitRelationCondition(RelationCondition l) {
                if (isPLSQLContext(l) && !isSqlContext(l)) {
                    __collect__(COLLECT_VAR_VARS);
                } else if (isSqlContext(l)) {
                    // sql statement expression
                    __collect__(COLLECT_COLUMN);
                }
            }

            public void visitLogicalExpression(LogicalExpression l) {
                // select field expression
                __collect__(COLLECT_COLUMN);
            }

            public void visitWhereCondition(WhereCondition l) {
                // select field expression
                __collect__(COLLECT_COLUMN);
            }

            public void visitInCondition(InCondition l) {
                __collect__(COLLECT_COLUMN);
            }

            public void visitBetweenCondition(BetweenCondition l) {
                __collect__(COLLECT_COLUMN);
            }

            public void visitDefaultExprForVariable(VariableDecl l) {
                if (prev == null) {
                    __collect__(COLLECT_SYSTEM_FUNC_CALLS);
                    __collect__(COLLECT_VAR_VARS);
                } else {
                    out.addAll(provider.collectNamesInContext(prev, lookUpStr, VariantsProvider.FILTER_EXPR));
//                    __collect__(COLLECT_NAMES_IN_CTX_VARS);
                }
                int h = 0;
            }

            public void visitArithmeticExpression(ArithmeticExpression l) {
                if (isPLSQLContext(l)) {
                    // variables, func/proc arguments, etc
                    __collect__(COLLECT_VAR_VARS);
                }
                if (isSqlContext(l)) {
                    // select field expression
                    __collect__(COLLECT_COLUMN);
                }
            }

            public void visitCallArgument(CallArgument l) {
                if (isPLSQLContext(l) && !isSqlContext(l)) {
                    if (prev == null) {
                        // 1. might be a package name
                        __collect__(COLLECT_PACKAGE_VARS);
                        // 2. system functions
                        __collect__(COLLECT_SYSTEM_FUNC_CALLS);
                        // 3. variables
                        __collect__(COLLECT_VAR_VARS);
                    } else {
                        // 1. variables in the package scope
                        __collect__(COLLECT_PACKAGE_VAR_VARS);
                        // 2. user defined functions
                        __collect__(COLLECT_USER_FUNC_CALLS);
                    }
                }
                if (isSqlContext(l)) {
                    // select field expression
                    __collect__(COLLECT_COLUMN);
                }
            }

            public void visitAssignmentStatement(AssignmentStatement l) {
                // IMPORTANT: pay attention to the side
                if (prev == null) {
                    // 1. might be a package name
                    __collect__(COLLECT_PACKAGE_VARS);
                    // 2. system functions
                    __collect__(COLLECT_SYSTEM_FUNC_CALLS);
                    // 3. variables
                    __collect__(COLLECT_VAR_VARS);

                } else {
                    // 1. variables in the package scope
                    __collect__(COLLECT_PACKAGE_VAR_VARS);
                    // 2. user defined functions
                    __collect__(COLLECT_USER_FUNC_CALLS);
                }
            }

            public void visitSelectFieldExpr(SelectFieldExpr l) {
                // select field expression
                if (prev == null && lookUpStr.length() > 0) {
                    __collect__(COLLECT_SYSTEM_FUNC_CALLS);
                }
                __collect__(COLLECT_COLUMN);

                final PsiElement parent = l.getParent();
                if (parent instanceof SelectStatement) {
                    final GroupByClause groupBy = ((SelectStatement) parent).getGroupByClause();
                    if (groupBy != null) {
                        List<LookupElement> out1 = new ArrayList<LookupElement>();
                        // Filter out all names that don't match names in groupBy clause
                        for (Expression expr : groupBy.getExprList()) {
                            if (expr instanceof ObjectReference) {
                                addIfMatch(expr.getText(), out, out1);
                            }
                        }

                        out.clear();
                        out.addAll(out1);
                    } else {
                        __collect__(COLLECT_SEQUENCE_REF);
                    }
                }
            }

            public void visitGroupByClause(GroupByClause l) {
                // select field expression
                if (prev == null && lookUpStr.length() > 0) {
                    __collect__(COLLECT_SYSTEM_FUNC_CALLS);
                }

                __collect__(COLLECT_COLUMN_IN_CTX);
            }

            public void visitOrderByClause(OrderByClause l) {
                // select field expression
                if (prev == null && lookUpStr.length() > 0) {
                    __collect__(COLLECT_SYSTEM_FUNC_CALLS);
                }
                __collect__(COLLECT_COLUMN_IN_CTX);

                // Do some column filtering based on context
                if (l.getParent() instanceof SelectStatement) {
                    final GroupByClause groupBy = ((SelectStatement) l.getParent()).getGroupByClause();
                    if (groupBy != null) {
                        List<LookupElement> out1 = new ArrayList<LookupElement>();
                        // Filter out all names that don't match names in groupBy clause
                        for (Expression expr : groupBy.getExprList()) {
                            if (expr instanceof ObjectReference) {
                                addIfMatch(expr.getText(), out, out1);
                            }
                        }

                        out.clear();
                        out.addAll(out1);
                    }

                    // Search for aggregate functions among select fields, if they have alias, use them
                    for (SelectFieldCommon f : ((SelectStatement) l.getParent()).getSelectFieldList()) {
                        if (f instanceof SelectFieldExpr) {
                            final String alias = ((SelectFieldExpr) f).getAlias();
                            if (alias != null) {
                                try {
                                    final Expression expr = ((SelectFieldExpr) f).getExpression();
                                    Type t = expr.getExpressionType();
                                    if (isExprAggrFunction(expr)) {
                                        // Case: count(*) cnt
                                        out.add(GenericLookupElement.create(alias, t.typeName(), null, null));
                                    } else {
                                        if (groupBy != null) {
                                            // Case: to_char(date1, 'YYYY') if GROUP_BY has the same
                                            if (checkExpr(expr, groupBy.getExprList())) {
                                                out.add(GenericLookupElement.create(alias, t.typeName(), null, null));
                                            }
                                        } else {
                                            // Add just alias name of select field expr
                                            out.add(GenericLookupElement.create(alias, t.typeName(), null, null));
                                        }
                                    }
                                } catch (ValidationException e) {
                                    // Ignore single validation error
                                }
                            }
                        }
                    }
                } else if (l.getParent() instanceof CallArgumentList) {
                    if (l.getParent().getParent() instanceof SSystemFunctionCall) {

                        SSystemFunctionCall call = (SSystemFunctionCall) l.getParent().getParent();
                        // Do filtering needed?
                    /*
                    This may be an analytical function, example
                    SELECT date_event,transaction_id,
                    LEAD(sal, 1, 0) OVER (PARTITION BY transaction_id ORDER BY <caret> DESC NULLS LAST) NEXT_LOWER_SAL,
                    LAG(sal, 1, 0) OVER (PARTITION BY transaction_id ORDER BY sal DESC NULLS LAST) PREV_HIGHER_SAL
                    FROM itravel_log

                    */

                    }
                }

            }

            public void visitANSISelectCondition(PsiElement expr) {
                __collect__(COLLECT_COLUMN);
            }

            public void visitAnalyticalFunction(PsiElement parent) {
                __collect__(COLLECT_COLUMN);
            }

            public void visitColumnSpec(ColumnSpec l) {
                final ColumnSpecList list = l.getColumnSpecList();
                if (list != null) {
                    // returning clause (PL/SQL)
                    // insert/insert-merge
                    // subquery update
                    final PsiElement parent = list.getParent();
                    if (parent instanceof PsiFile || parent instanceof ErrorTokenWrapper) {
                        throw new SyntaxNotCompleteException();
                    } else {
                        __collect__(COLLECT_COLUMN);
                    }
                } else {
                    // select -> column_spec(+)
                    // update/update-merge
                    if (l.getParent() instanceof PsiFile) {
                        throw new SyntaxNotCompleteException();
                    } else {
                        __collect__(COLLECT_COLUMN);
                    }
                }
            }

            public void visitExpressionList(ExpressionList l) {
                if (isPLSQLContext(l)) {
                    __collect__(COLLECT_VAR_VARS);
                }
                // todo -- insert values ....
                // todo -- open cursor (...)
            }

            public void visitTypeNameReference(TypeNameReference l) {
                if (prev == null) {
                    switch (matchParent(nameFragment, a1)) {
                        case PLSqlTokenTypes.TABLE_DEF:
                        case PLSqlTokenTypes.COLUMN_DEF:
                            // table definition case
                            __collect__(COLLECT_DATA_TYPE_VARS);
                        default: // other cases
                            __collect__(COLLECT_DATA_TYPE_VARS);
                            __collect__(COLLECT_TYPE_NAME_VARS);
                    }
                } else {
                    ResolveDescriptor rhlp = prev.resolveLight();
                    if (rhlp != null) {
                        out.addAll(provider.collectNamesInContext(rhlp, lookUpStr));
                    } else {
                        // try to resolve the prev as a table name
                        // todo --
                        // try to resolve the prev as a schema name
                        // todo --
                    }
                }
            }

            public void visitCallableCompositeName(CallableCompositeName l) {
                Callable callable = l.getCallable();
                if (callable.getCallArguments().length == 0) {
                    // no arguments
                    // candidates are:
                    // 1. proc call w/o args
                    // 2. proc call with default args
                    // 3. variable (LVALUE)
                    if (callable instanceof ProcedureCall && isPLSQLContext(l)) {
                        // procedure call inside executable
                        __collect__(COLLECT_USER_PROC_CALLS);
                        __collect__(COLLECT_VAR_VARS);
                    }
                } else {
                    // arguments present
                    // candidates are:
                    // 1. proc call with args
                    // 2. func call with args
                    if (callable instanceof FunctionCall && isSqlContext(l)) {
                        // function call inside sql statement
                        __collect__(COLLECT_SYSTEM_FUNC_CALLS);
                    } else if (callable instanceof FunctionCall && isPLSQLContext(l)) {
                        // function call inside sql statement
                        __collect__(COLLECT_USER_FUNC_CALLS);
                    } else if (callable instanceof ProcedureCall) {
                        if (prev == null) {
                            __collect__(COLLECT_PACKAGE_VARS);
                        }
                        __collect__(COLLECT_USER_PROC_CALLS);
                    }
                }
            }

            public void visitParameterNameRef(ParameterReference l) {
                // proc/function parameters
                __collect__(COLLECT_PARAM_NAME_VARS);
            }

            public void visitReturnStatement(ReturnStatement l) {
                if (prev == null) {
                    // 1. return variable
                    __collect__(COLLECT_VAR_VARS);
                    // 2. function call result
                    __collect__(COLLECT_USER_FUNC_CALLS);
                    // 3. system functions
                    __collect__(COLLECT_SYSTEM_FUNC_CALLS);
                    // 4. oackage names
                    __collect__(COLLECT_PACKAGE_VARS);
                } else {
                    // collect names in context
                    out.addAll(provider.collectNamesInContext(prev, lookUpStr, VariantsProvider.FILTER_EXPR));
                }
            }

            public void visitSelectStatement(PsiElement expr) {
                // completion invoked on a select field with errored syntax
                __collect__(COLLECT_COLUMN);
            }

            public void visitPlSqlReference(ObjectReference reference) {
                // plsql variable
                __collect__(COLLECT_VAR_VARS);
            }

            public void visitUnhandledNode(PsiElement e) {
                throw new SyntaxNotCompleteException();
            }

        });

        return out;
    }

    /**
     * Trivial compare expressions
     *
     * @param expr
     * @param expressions
     * @return
     */
    private boolean checkExpr(Expression expr, Expression[] expressions) {
        final String exprTest = expr.getText().replaceAll("[\n\t ]", "");
        for(Expression e: expressions){
            return exprTest.equalsIgnoreCase(e.getText().replaceAll("[\n\t ]", ""));
        }

        return false;
    }

    /**
     * Is the specified expression an aggregate function
     *
     * @param expression expr to test
     * @return true if it is an aggregate like MIN, MAX, etc
     */
    private boolean isExprAggrFunction(Expression expression) {
        if(expression instanceof Callable){
            final String name = ((Callable)expression).getFunctionName();
            return AGGR_FUNC_NAMES.contains(name.toUpperCase());
        }
        return false;
    }

    private void addIfMatch(String reference, List<LookupElement> source, List<LookupElement> out) {
        for(LookupElement e: source){
            if(e.getLookupString().equalsIgnoreCase(reference.replaceAll(" ", ""))){
                out.add(e);
                break;
            }
        }
    }


    private int matchParent(PsiElement psiElement, int[] tokenTypes) {

        ASTNodeHandlerAdapter handler = new ASTNodeHandlerAdapter(tokenTypes);

        ASTTreeProcessor runner = new ASTTreeProcessor();
        runner.add(handler);
        runner.process(psiElement.getNode());

        return handler.result();
    }

    static int[] a1 = new int[]{
            PLSqlTokenTypes.TABLE_DEF,
            PLSqlTokenTypes.COLUMN_DEF
    };

    public static String stripText(String text) {
        int idx = text.indexOf(Constants.COMPL_IDENTIFIER); //IntellijIdeaRulezzz);
        if (idx >= 0) {
            if (idx == 0) {
                return "";
            } else {
                return text.substring(0, idx);
            }
        } else {
            return text;
        }
    }

    private boolean isSqlContext(ContextPathProvider p){
        ContextPathUtil.CtxPathBackIterator iterator = new ContextPathUtil.CtxPathBackIterator(p.getCtxPath1().getPath());
        while(iterator.next()){
            switch(iterator.getType()){
                case ContextPath.SELECT_EXPR:
                case ContextPath.DELETE_STMT:
                case ContextPath.UPDATE_STMT:
                case ContextPath.MERGE_STMT:
                case ContextPath.INSERT_STMT:
                    return true;
            }
        }
        return false;
    }

    private boolean isPLSQLContext(ContextPathProvider p){
        ContextPathUtil.CtxPathBackIterator iterator = new ContextPathUtil.CtxPathBackIterator(p.getCtxPath1().getPath());
        while(iterator.next()){
            switch(iterator.getType()){
                case ContextPath.FUNCTION_BODY:
                case ContextPath.PROCEDURE_BODY:
                case ContextPath.PLSQL_BLOCK:
                case ContextPath.PACKAGE_BODY:
                case ContextPath.PACKAGE_SPEC:
                    return true;
            }
        }
        return false;
    }

    // Set of aggregate functions
    private final static Set<String> AGGR_FUNC_NAMES = new HashSet<String>(
            Arrays.asList(new String[]{
                    // TODO -- add more AGGR functions
                    "MAX", "MIN", "COUNT", "SUM", "AVG", "COLLECT", "FIRST", "LAST", "GROUP_ID", "MEDIAN"})
    );

    private interface ContextElementProcessor {
        void process(SelectStatement select, int counter);
        void process(UpdateStatement update);
        void process(InsertStatement insert);
        void process(DeleteStatement delete);
    }

    static private final Class[] kk = new Class[]{
            SelectStatement.class,
            UpdateStatement.class,
            InsertStatement.class,
            DeleteStatement.class
    };

    private void processElementContext(ComplContributor.ContextElementHelper e, ContextElementProcessor proc){
        Iterator<PlSqlElement> iterator = e.ctxIterator(kk);
        int counter = 0;
        while(iterator.hasNext()){
            PlSqlElement e1 = iterator.next();
            if(e1 instanceof SelectStatement){
                if(++counter >= 3){
                    break;
                }
                proc.process((SelectStatement) e1, counter);
            } if(e1 instanceof InsertStatement){
                proc.process((InsertStatement) e1);
                break;
            } if(e1 instanceof UpdateStatement){
                proc.process((UpdateStatement) e1);
                break;
            } if(e1 instanceof DeleteStatement){
                proc.process((DeleteStatement) e1);
                break;
            }
        }


    }


}
