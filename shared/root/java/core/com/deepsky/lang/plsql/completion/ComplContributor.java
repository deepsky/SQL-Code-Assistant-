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

package com.deepsky.lang.plsql.completion;

import com.deepsky.database.ora.DbUrl;
import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.integration.PlSqlElementType;
import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ref.TableRef;
import com.deepsky.lang.plsql.psi.resolve.ASTNodeHandler;
import com.deepsky.lang.plsql.psi.resolve.ASTNodeHandlerAdapter;
import com.deepsky.lang.plsql.psi.resolve.ASTTreeProcessor;
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.deepsky.lang.plsql.resolver.factory.CompositeElementExt;
import com.deepsky.lang.plsql.resolver.utils.PsiUtil;
import com.deepsky.lang.plsql.sqlIndex.AbstractSchema;
import com.deepsky.utils.StringUtils;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ComplContributor extends CompletionContributor {
    private static final Logger log = Logger.getInstance("#ComplContributor");


    final static TokenSet contexts = TokenSet.create(
            PlSqlElementTypes.ERROR_TOKEN_A,
            PlSqlTokenTypes.FILE,
            PlSqlElementTypes.TABLE_REF,
            PlSqlElementTypes.COLUMN_NAME_REF,
            PlSqlElementTypes.NAME_FRAGMENT,
            PlSqlElementTypes.EXEC_NAME_REF,

            PlSqlElementTypes.SELECT_EXPRESSION,
            PlSqlElementTypes.INSERT_COMMAND,
            PlSqlElementTypes.DELETE_COMMAND,
            PlSqlElementTypes.UPDATE_COMMAND,
            PlSqlElementTypes.SUBQUERY_UPDATE_COMMAND,
            PlSqlElementTypes.MERGE_COMMAND,

            PlSqlElementTypes.ALIAS_IDENT,

            PlSqlElementTypes.FUNCTION_BODY,
            PlSqlElementTypes.PROCEDURE_BODY,
            PlSqlElementTypes.PLSQL_BLOCK
    );


    final static TokenSet sqlStmts = TokenSet.create(
            PlSqlElementTypes.TABLE_DEF,
            PlSqlElementTypes.SELECT_EXPRESSION,
            PlSqlElementTypes.INSERT_COMMAND,
            PlSqlElementTypes.DELETE_COMMAND,
            PlSqlElementTypes.UPDATE_COMMAND,
            PlSqlElementTypes.MERGE_COMMAND,
            PlSqlElementTypes.FUNCTION_BODY,
            PlSqlElementTypes.PROCEDURE_BODY
    );

    final ASTNode[] sqlStmt = new ASTNode[1];
    final ASTNode[] execB = new ASTNode[1];


    @Override
    public void fillCompletionVariants(CompletionParameters parameters, CompletionResultSet result) {
        final ASTNode[] targetNode = {null};

        sqlStmt[0] = null;
        execB[0] = null;

        // Try to identify a target node and parent context
        ASTTreeProcessor runner = new ASTTreeProcessor();
        runner.add(new ASTNodeHandler() {
            @NotNull
            public TokenSet getTokenSet() {
                return contexts;
            }

            public boolean handleNode(@NotNull ASTNode node) {
                if (node.getElementType() == PlSqlElementTypes.ERROR_TOKEN_A) {
                    targetNode[0] = node;
                } else if (node.getElementType() == PlSqlElementTypes.TABLE_REF) {
                    targetNode[0] = node;
                } else if (node.getElementType() == PlSqlElementTypes.COLUMN_NAME_REF) {
                    targetNode[0] = node;
                } else if (node.getElementType() == PlSqlElementTypes.NAME_FRAGMENT) {
                    targetNode[0] = node;
                } else if (node.getElementType() == PlSqlElementTypes.EXEC_NAME_REF) {
                    targetNode[0] = node;

                    // check SQL statement context
                } else if (node.getElementType() == PlSqlElementTypes.SELECT_EXPRESSION) {
                    sqlStmt[0] = node;
                } else if (node.getElementType() == PlSqlElementTypes.INSERT_COMMAND) {
                    sqlStmt[0] = node;
                } else if (node.getElementType() == PlSqlElementTypes.DELETE_COMMAND) {
                    sqlStmt[0] = node;
                } else if (node.getElementType() == PlSqlElementTypes.UPDATE_COMMAND) {
                    sqlStmt[0] = node;
                } else if (node.getElementType() == PlSqlElementTypes.SUBQUERY_UPDATE_COMMAND) {
                    sqlStmt[0] = node;
                } else if (node.getElementType() == PlSqlElementTypes.MERGE_COMMAND) {
                    sqlStmt[0] = node;
                } else if (node.getElementType() == PlSqlElementTypes.ALIAS_IDENT) {
                    targetNode[0] = node;

                    // check executable context
                } else if (node.getElementType() == PlSqlElementTypes.FUNCTION_BODY) {
                    execB[0] = node;
                } else if (node.getElementType() == PlSqlElementTypes.PROCEDURE_BODY) {
                    execB[0] = node;
                } else if (node.getElementType() == PlSqlElementTypes.PLSQL_BLOCK) {
                    execB[0] = node;
                }
                //
                return false;
            }
        });

        runner.process(parameters.getPosition().getNode());

        LookupElement[] variants = calculate(targetNode[0]);
        for (LookupElement elem : variants) {
            result.addElement(elem);
        }


/*
        VariantsProvider.NamedItem[] variants = calculate(targetNode[0]);
        Arrays.sort(variants, new Comparator<VariantsProvider.NamedItem>() {
            public int compare(VariantsProvider.NamedItem o1, VariantsProvider.NamedItem o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        for (VariantsProvider.NamedItem item : variants) {
            result.addElement(
                    LookupElementBuilder.create(item.getName())
                            .setTailText(item.getTail(), true)
                            .setTypeText(item.getType())
                            .setIcon(item.getIcon())
                            .setCaseSensitive(false)
                            .setInsertHandler(new InsertHandler<LookupElement>() {
                                public void handleInsert(InsertionContext context, LookupElement item) {
                                    // todo --
                                    int hh = 0;
                                }
                            }).setStrikeout(item.isStrikeout())
            );
        }
*/
        super.fillCompletionVariants(parameters, result);
    }

    public void beforeCompletion(@NotNull final CompletionInitializationContext context) {
        context.setFileCopyPatcher(Constants.IDENT_PATCHER);
//        context.setDummyIdentifier(CompletionInitializationContext.DUMMY_IDENTIFIER_TRIMMED);
    }

    private PsiElement getSqlStmtCtx() {
        return sqlStmt[0] != null ? sqlStmt[0].getPsi() : null;
    }

    private PsiElement getExecCtx() {
        return execB[0] != null ? execB[0].getPsi() : null;
    }

    private LookupElement[] calculate(ASTNode target) {
        List<LookupElement> out = new ArrayList<LookupElement>();
        if (target != null) {
            final String lookUpStr = stripText(StringUtils.discloseDoubleQuotes(target.getText()));
//            ResolveFacade r = ((PlSqlFile) target.getPsi().getContainingFile()).getResolver();
//            VariantsProvider provider = r.getNameProvider();
            VariantsProvider provider = chooseSearchDomain((PlSqlFile) target.getPsi().getContainingFile());
            if (provider == null) {
                return new LookupElement[0];
            }

            if (target.getPsi() instanceof TableRef) {
                out.addAll(provider.collectTableNameVariants(lookUpStr));

            } else if (target.getPsi() instanceof ColumnNameRef) {
                out.addAll(provider.collectColumnNameRef((ColumnNameRef) target.getPsi(), lookUpStr));

            } else if (target.getPsi() instanceof NameFragmentRef) {
                NameFragmentRef nameFragment = (NameFragmentRef) target.getPsi();
                return processNameFragment2(provider, nameFragment);

            } else if (target.getElementType() == PlSqlElementTypes.ALIAS_IDENT) {
                if(sqlStmt[0] != null && sqlStmt[0].getElementType() == PlSqlElementTypes.UPDATE_COMMAND ){
                    if(lookUpStr.length() == 0 || "set".startsWith(lookUpStr) ){
                         out.add(LookupElementBuilder.create("set"));
/*
                            .setInsertHandler(new InsertHandler<LookupElement>() {
                                public void handleInsert(InsertionContext context, LookupElement item) {
                                    // todo --
                                    int hh = 0;
                                }
                            })
                         );
*/
                    }
                }
            } else if (target.getElementType() == PlSqlElementTypes.ERROR_TOKEN_A) { //&& lookUpStr.length() > 0) {
                ASTNode parent = target.getTreeParent();
                if (parent.getElementType() == PlSqlElementTypes.STATEMENT_LIST) {
                    String ctxPath = ((CompositeElementExt) parent).getCtxPath1().getPath();
                    // 1. variable assignment
                    // 2. procedure call
                    // 3. procedure call prefixed by package name, i.e. <package_name>.<proc_call>
                    out.addAll(provider.collectProcCall(
                            ctxPath,
                            null,
                            lookUpStr)
                    );

                    out.addAll(provider.collectVarVariants(
                            ctxPath,
                            lookUpStr)
                    );

                    out.addAll(provider.collectPackageVariants(
                            lookUpStr)
                    );

                    return out.toArray(new LookupElement[out.size()]);
                } else if (parent.getElementType() == PlSqlElementTypes.RETURN_STATEMENT) {
                    if (getExecCtx() != null) {
                        String ctxPath = ((CompositeElementExt) parent).getCtxPath1().getPath();

                        // 1. return variable
                        // 2. function call result
                        // 3. a complex name - <package_name>.<object_name>
                        out.addAll(provider.collectVarVariants(
                                ctxPath,
                                lookUpStr)
                        );

                        out.addAll(provider.collectFuncCall(
                                ctxPath,
                                null,
                                lookUpStr)
                        );

                        out.addAll(provider.collectPackageVariants(
                                lookUpStr)
                        );

                        return out.toArray(new LookupElement[out.size()]);
                    }
                } else if (parent.getElementType() == PlSqlElementTypes.PLSQL_BLOCK) {
                    // todo -- generate lookup variants
                    int h = 0;
                } else if (parent.getElementType() == PlSqlElementTypes.UPDATE_COMMAND) {
                    // todo -- generate lookup variants
                    int h = 0;
                } else if (parent.getElementType() == PlSqlTokenTypes.FILE) {
                    // todo -- generate lookup variants
                    int h = 0;
                }
            }
        }

        return out.toArray(new LookupElement[out.size()]);
    }

    static int[] a1 = new int[]{
            PLSqlTokenTypes.TABLE_DEF,
            PLSqlTokenTypes.COLUMN_DEF
    };


//    static final String IntellijIdeaRulezzz = "IntellijIdeaRulezzz";

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


    private int matchParent(PsiElement psiElement, int[] tokenTypes) {

        ASTNodeHandlerAdapter handler = new ASTNodeHandlerAdapter(tokenTypes);

        ASTTreeProcessor runner = new ASTTreeProcessor();
        runner.add(handler);
        runner.process(psiElement.getNode());

        return handler.result();
    }


    private VariantsProvider chooseSearchDomain(PlSqlFile plsqlFile) {
        DbUrl dbUrl = plsqlFile.getDbUrl();
        AbstractSchema i = PluginKeys.SQL_INDEX_MAN.getData(plsqlFile.getProject()).getIndex(dbUrl, 0);

        return (i != null) ? i.getVariantsProvider() : null;
    }

    private final static int COLLECT_SYSTEM_FUNC_CALLS = 1;
    private final static int COLLECT_COLUMN_EXPR_VARS = 2; // columns relevant to the usage context (SQL stmt context)
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


    private LookupElement[] processNameFragment2(
            final VariantsProvider provider, final NameFragmentRef nameFragment) {

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
                    case COLLECT_COLUMN_EXPR_VARS:
                        out.addAll(provider.collectColumnExprVariants(usageCtxPath, prevText, lookUpStr));
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

            public void visitSelectFieldExpr(SelectFieldExpr l) {
                // select field expression
                if (prev == null && lookUpStr.length() > 0) {
                    __collect__(COLLECT_SYSTEM_FUNC_CALLS);
                }
                __collect__(COLLECT_COLUMN_EXPR_VARS);
            }

            public void visitRelationCondition(RelationCondition l) {
                if (getExecCtx() != null && getSqlStmtCtx() == null) {
                    __collect__(COLLECT_VAR_VARS);
                } else if (getSqlStmtCtx() != null) {
                    // sql statement expression
                    __collect__(COLLECT_COLUMN_EXPR_VARS);
                }
            }

            public void visitLogicalExpression(LogicalExpression l) {
                // select field expression
                __collect__(COLLECT_COLUMN_EXPR_VARS);
            }

            public void visitWhereCondition(WhereCondition l) {
                // select field expression
                __collect__(COLLECT_COLUMN_EXPR_VARS);
            }

            public void visitInCondition(InCondition l) {
                __collect__(COLLECT_COLUMN_EXPR_VARS);
            }

            public void visitBetweenCondition(BetweenCondition l) {
                __collect__(COLLECT_COLUMN_EXPR_VARS);
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
                if (getExecCtx() != null) {
                    __collect__(COLLECT_VAR_VARS);
                }
                if (getSqlStmtCtx() != null) {
                    // select field expression
                    __collect__(COLLECT_COLUMN_EXPR_VARS);
                }
            }

            public void visitCallArgument(CallArgument l) {
                if (getExecCtx() != null && getSqlStmtCtx() == null) {
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
                if (getSqlStmtCtx() != null) {
                    // select field expression
                    __collect__(COLLECT_COLUMN_EXPR_VARS);
                }
            }

            public void visitAssignmentStatement(AssignmentStatement l) {
                // todo -- pay attention on the side
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

            public void visitGroupByClause(GroupByClause l) {
                // select field expression
                if (prev == null && lookUpStr.length() > 0) {
                    __collect__(COLLECT_SYSTEM_FUNC_CALLS);
                }
                __collect__(COLLECT_COLUMN_EXPR_VARS);
            }

            public void visitOrderByClause(OrderByClause l) {
                // select field expression
                if (prev == null && lookUpStr.length() > 0) {
                    __collect__(COLLECT_SYSTEM_FUNC_CALLS);
                }
                __collect__(COLLECT_COLUMN_EXPR_VARS);
            }

            public void visitColumnSpec(ColumnSpec l) {
                final ColumnSpecList list = l.getColumnSpecList();
                if (list != null) {
                    // returning clause (PL/SQL)
                    // insert/insert-merge
                    // subquery update
                    if (list.getParent() instanceof PsiFile) {
                        TableAlias tab = identifyTableAlias(list);
                        if(tab != null)
                            out.addAll(provider.collectColumnNames(tab, lookUpStr));
                    } else {
                        __collect__(COLLECT_COLUMN_EXPR_VARS);
                    }
                } else {
                    // select -> column_spec(+)
                    // update/update-merge
                    if (l.getParent() instanceof PsiFile) {
                        TableAlias tab = identifyTableAlias(l);
                        if(tab != null)
                            out.addAll(provider.collectColumnNames(tab, lookUpStr));
                    } else {
                        __collect__(COLLECT_COLUMN_EXPR_VARS);
                    }
                }
            }

            public void visitExpressionList(ExpressionList l) {
                if (getExecCtx() != null) {
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
                    if (callable instanceof ProcedureCall && getExecCtx() != null) {
                        // procedure call inside executable
                        __collect__(COLLECT_USER_PROC_CALLS);
                    }
                } else {
                    // arguments present
                    // candidates are:
                    // 1. proc call with args
                    // 2. func call with args
                    if (callable instanceof FunctionCall && getSqlStmtCtx() != null) {
                        // function call inside sql statement
                        __collect__(COLLECT_SYSTEM_FUNC_CALLS);
                    } else if (callable instanceof FunctionCall && getExecCtx() != null) {
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
                __collect__(COLLECT_COLUMN_EXPR_VARS);
            }

            public void visitPlSqlReference(ObjectReference reference) {
                // plsql variable
                __collect__(COLLECT_VAR_VARS);
            }

            public void visitUnhandledNode(PsiElement e) {
                int jj = 0;
            }
        });

        return out.toArray(new LookupElement[out.size()]);
    }

    private TableAlias identifyTableAlias(PsiElement e) {
        final TableAlias[] alias = new TableAlias[1];
        final int[] index = {insertStmt.length-1};
        PsiUtil.iterateOverSiblings(e, new PsiUtil.SiblingVisitor() {
            public boolean visit(PsiElement e) {
                if(index[0] < 0){
                    return false;
                }
                ASTNode n = e.getNode();
                if( n.getElementType() != insertStmt[index[0]]){
                    return false;
                }

                if( n.getElementType() == PlSqlElementTypes.TABLE_ALIAS){
                    alias[0]= (TableAlias) e;
                }
                index[0]--;
                return true;
            }
        });
        return alias[0];
    }

    private static IElementType[] insertStmt = new IElementType[]{
            PlSqlTokenTypes.KEYWORD_INSERT,
            PlSqlTokenTypes.KEYWORD_INTO,
            PlSqlElementTypes.TABLE_ALIAS,
            PlSqlTokenTypes.OPEN_PAREN
    };
}
