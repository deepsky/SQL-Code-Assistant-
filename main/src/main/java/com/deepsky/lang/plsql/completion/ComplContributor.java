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
import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.common.PluginKeys2;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.completion.lookups.GenericLookupElement;
import com.deepsky.lang.plsql.completion.lookups.KeywordLookupElement;
import com.deepsky.lang.plsql.completion.utils.VariantProcessor;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.AlterTable;
import com.deepsky.lang.plsql.psi.ddl.CreateIndex;
import com.deepsky.lang.plsql.psi.ddl.RenameTable;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.psi.names.ColumnNameRef;
import com.deepsky.lang.plsql.psi.ref.TableRef;
import com.deepsky.lang.plsql.psi.resolve.ASTNodeHandler;
import com.deepsky.lang.plsql.psi.resolve.ASTTreeProcessor;
import com.deepsky.lang.plsql.psi.types.ColumnTypeRef;
import com.deepsky.lang.plsql.psi.types.RowtypeType;
import com.deepsky.lang.plsql.resolver.factory.CompositeElementExt;
import com.deepsky.lang.PsiUtil;
import com.deepsky.lang.plsql.resolver.utils.ResolveUtil;
import com.deepsky.lang.plsql.resolver.utils.TableRefCallback;
import com.deepsky.lang.plsql.sqlIndex.AbstractSchema;
import com.deepsky.utils.StringUtils;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
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
            PlSqlElementTypes.SQL_CURSOR_FAKE_ATTR,
            PlSqlElementTypes.COLUMN_TYPE_REF,
            PlSqlElementTypes.TABLE_TYPE_REF,
            PlSqlElementTypes.REF_CURSOR,

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


    final ASTNode[] sqlStmt = new ASTNode[1];
    final ASTNode[] execB = new ASTNode[1];


    @Override
    public void fillCompletionVariants(CompletionParameters parameters, CompletionResultSet result) {
        if(parameters.getCompletionType() != CompletionType.BASIC){
            return;
        }

        final ContextElementHelper contextHelper = new ContextElementHelper();
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
                final IElementType type = node.getElementType();
                if (type == PlSqlElementTypes.ERROR_TOKEN_A) {
                    if(targetNode[0] == null) targetNode[0] = node;
                } else if (type == PlSqlElementTypes.TABLE_REF) {
                    if(targetNode[0] == null) targetNode[0] = node;
                } else if (type == PlSqlElementTypes.COLUMN_NAME_REF) {
                    if(targetNode[0] == null) targetNode[0] = node;
                } else if (type == PlSqlElementTypes.NAME_FRAGMENT) {
                    if(targetNode[0] == null) targetNode[0] = node;
                } else if (type == PlSqlElementTypes.EXEC_NAME_REF) {
                    if(targetNode[0] == null) targetNode[0] = node;
                } else if (type == PlSqlElementTypes.SQL_CURSOR_FAKE_ATTR) {
                    if(targetNode[0] == null) targetNode[0] = node;

                } else if (type == PlSqlElementTypes.COLUMN_TYPE_REF) {
                    if(targetNode[0] == null) targetNode[0] = node;
                } else if (type == PlSqlElementTypes.TABLE_TYPE_REF) {
                    if(targetNode[0] == null) targetNode[0] = node;
                } else if (type == PlSqlElementTypes.REF_CURSOR) {
                    if(targetNode[0] == null) targetNode[0] = node;
                } else if (type == PlSqlElementTypes.ALIAS_IDENT) {
                    if(targetNode[0] == null) targetNode[0] = node;

                    // check SQL statement context
                } else if (type == PlSqlElementTypes.SELECT_EXPRESSION) {
                    contextHelper.add((PlSqlElement) node.getPsi());
                    sqlStmt[0] = node;
                } else if (type == PlSqlElementTypes.INSERT_COMMAND) {
                    contextHelper.add((PlSqlElement) node.getPsi());
                    sqlStmt[0] = node;
                } else if (type == PlSqlElementTypes.DELETE_COMMAND) {
                    contextHelper.add((PlSqlElement) node.getPsi());
                    sqlStmt[0] = node;
                } else if (type == PlSqlElementTypes.UPDATE_COMMAND) {
                    contextHelper.add((PlSqlElement) node.getPsi());
                    sqlStmt[0] = node;
                } else if (type == PlSqlElementTypes.SUBQUERY_UPDATE_COMMAND) {
                    contextHelper.add((PlSqlElement) node.getPsi());
                    sqlStmt[0] = node;
                } else if (type == PlSqlElementTypes.MERGE_COMMAND) {
                    contextHelper.add((PlSqlElement) node.getPsi());
                    sqlStmt[0] = node;

                    // check executable context
                } else if (type == PlSqlElementTypes.FUNCTION_BODY) {
                    contextHelper.add((PlSqlElement) node.getPsi());
                    execB[0] = node;
                } else if (type == PlSqlElementTypes.PROCEDURE_BODY) {
                    contextHelper.add((PlSqlElement) node.getPsi());
                    execB[0] = node;
                } else if (type == PlSqlElementTypes.PLSQL_BLOCK) {
                    contextHelper.add((PlSqlElement) node.getPsi());
                    execB[0] = node;
                }
                //
                return false;
            }
        });

        final ASTNode nodeToComplete = parameters.getPosition().getNode();
        runner.process(nodeToComplete);

        if (targetNode[0] != null) {
            final String lookupStr = stripText(StringUtils.discloseDoubleQuotes(nodeToComplete.getText()));
            final VariantsProvider provider = chooseSearchDomain((PlSqlFile) targetNode[0].getPsi().getContainingFile());
            if (provider != null) {
                final List<LookupElement> variants = new ArrayList<LookupElement>();
                try {
                    variants.addAll(calculate(targetNode[0], contextHelper, lookupStr, provider));
                } catch (SyntaxNotCompleteException e) {

                    final ASTNode _target = targetNode[0].getElementType() == PlSqlElementTypes.ERROR_TOKEN_A? nodeToComplete: targetNode[0];
                    PlSqlElementProxyFactory.identifyNode(_target, new PlSqlElementProxyFactory.RestoredNodeProcessor() {
                        public void processComment(PlSqlElementProxy eProxy) {
                            if (eProxy.getTargets().contains(PlSqlElementTypes.TABLE_REF)) {
                                variants.addAll(provider.collectTableNameVariants(lookupStr));
                            } else if (eProxy.getTargets().contains(PlSqlElementTypes.COLUMN_NAME_REF)) {
                                ASTNode ref = eProxy.findNode(PlSqlElementTypes.TABLE_REF);
                                if (ref != null) {
                                    String tableName = StringUtils.discloseDoubleQuotes(ref.getText());
                                    variants.addAll(provider.collectColumnNames(tableName, lookupStr));
                                }
                            }
                        }

                        public void processUpdateCommand(PlSqlElementProxy eProxy) {
                            if (eProxy.getTargets().contains(PlSqlElementTypes.TABLE_REF)) {
                                variants.addAll(provider.collectTableNameVariants(lookupStr));
                            } else if (eProxy.getTargets().contains(PlSqlElementTypes.COLUMN_NAME_REF)) {
                                ASTNode ref = eProxy.findNode(PlSqlElementTypes.TABLE_ALIAS);
                                if (ref != null) {
                                    provider.collectColumnNames((TableAlias) ref.getPsi(), lookupStr, false);
                                    variants.addAll(provider.takeCollectedLookups());
                                }
                            } else if (eProxy.getTargets().contains(PlSqlTokenTypes.KEYWORD_SET)) {
                                variants.add(KeywordLookupElement.create("set"));
                            }
                        }

                        public void processInsertCommand(PlSqlElementProxy eProxy) {
                            if (eProxy.getTargets().contains(PlSqlElementTypes.COLUMN_NAME_REF)) {
                                ASTNode ref = eProxy.findNode(PlSqlElementTypes.TABLE_ALIAS);
                                if (ref != null) {
                                    provider.collectColumnNames((TableAlias) ref.getPsi(), lookupStr, false);
                                    variants.addAll(provider.takeCollectedLookups());
                                }
                            }
                            if (eProxy.getTargets().contains(PlSqlTokenTypes.KEYWORD_INTO)) {
                                variants.add(KeywordLookupElement.create("into"));
                            }
                            if (eProxy.getTargets().contains(PlSqlTokenTypes.KEYWORD_SELECT)) {
                                variants.add(KeywordLookupElement.create("select"));
                            }
                            if (eProxy.getTargets().contains(PlSqlTokenTypes.KEYWORD_VALUES)) {
                                variants.add(KeywordLookupElement.create("values"));
                            }
                        }

                        public void processFileLevelText(ASTNode node) {
                            final String lookup = stripText(node.getText()).toLowerCase();
                            if("insert".startsWith(lookup)) variants.add(KeywordLookupElement.create("insert"));
                            if("select".startsWith(lookup)) variants.add(KeywordLookupElement.create("select"));
                            if("update".startsWith(lookup)) variants.add(KeywordLookupElement.create("update"));
                            if("delete".startsWith(lookup)) variants.add(KeywordLookupElement.create("delete"));

                            if("alter".startsWith(lookup)) variants.add(KeywordLookupElement.create("alter"));
                            if("rename".startsWith(lookup)) variants.add(KeywordLookupElement.create("rename"));
                            if("create".startsWith(lookup)) variants.add(KeywordLookupElement.create("create"));
                        }
                    });
                }

                for (LookupElement elem : variants) {
                    result.withPrefixMatcher(lookupStr).addElement(elem);
                }

                super.fillCompletionVariants(parameters, result);
            }
        }
    }

    public void beforeCompletion(@NotNull final CompletionInitializationContext context) {
//        context.setFileCopyPatcher(Constants.IDENT_PATCHER);
//        context.setDummyIdentifier(Constants.COMPL_IDENTIFIER);

        Constants.IDENT_PATCHER.patch();
    }

    private PsiElement getExecCtx() {
        return execB[0] != null ? execB[0].getPsi() : null;
    }

    private List<LookupElement> calculate(final @NotNull ASTNode target, ContextElementHelper contextHelper, final String lookupStr, final VariantsProvider provider) {
        final List<LookupElement> out = new ArrayList<LookupElement>();
        final ASTNode parentAST = target.getTreeParent();
        final IElementType targetType = target.getElementType();
        final PsiElement targetPsi = target.getPsi();

        if (targetPsi instanceof TableRef) {
            // todo -- exclude tables found in parent subqueries
            ResolveUtil.processInContext((TableRef) target.getPsi(), new TableRefCallback() {
                public void process(ForeignKeyConstraint fkConst) {
                    out.addAll(provider.collectTableNameVariants(lookupStr));
                    // Filter out a table which is a context of the FK specification
                    if (fkConst.getParent() instanceof TableDefinition) {
                        final String tableName = ((TableDefinition) fkConst.getParent()).getTableName();
                        Iterator<LookupElement> ite = out.iterator();
                        while (ite.hasNext()) {
                            LookupElement e = ite.next();
                            if (e.getLookupString().equalsIgnoreCase(tableName)) {
                                ite.remove();
                            }
                        }
                    }
                }

                public void process(ColumnFKSpec fkConst) {
                    out.addAll(provider.collectTableNameVariants(lookupStr));
                    // Filter out a table which is a context of the FK specification
                    if (fkConst.getParent().getParent() instanceof TableDefinition) {
                        final String tableName = ((TableDefinition) fkConst.getParent().getParent()).getTableName();
                        Iterator<LookupElement> ite = out.iterator();
                        while (ite.hasNext()) {
                            LookupElement e = ite.next();
                            if (e.getLookupString().equalsIgnoreCase(tableName)) {
                                ite.remove();
                            }
                        }
                    }
                }

                public void process(AlterTable alter) {
                    out.addAll(provider.collectTableNameVariants(lookupStr));
                }

                public void process(CreateIndex parent) {
                    out.addAll(provider.collectTableNameVariants(lookupStr));
                }

                public void process(ColumnTypeRef parent) {
                    // TODO -- implement me
                }

                public void process(RowtypeType parent) {
                    // TODO -- implement me
                }

                public void process(TableRef ref) {
                    out.addAll(provider.collectTableNameVariants(lookupStr));
                }

                public void process(TableAlias alias) {
                    out.addAll(provider.collectTableNameVariants(lookupStr));
                    out.addAll(provider.collectViewNameVariants(lookupStr));
                }

                public void process(PlSqlFile file) {
                    throw new SyntaxNotCompleteException();
                }

                public void process(ErrorTokenWrapper parent) {
                    throw new SyntaxNotCompleteException();
                }

                @Override
                public void process(RenameTable renameTable) {
                    out.addAll(provider.collectTableNameVariants(lookupStr));
                }
            });


        } else if (targetPsi instanceof ColumnNameRef) {
            if (parentAST.getPsi() instanceof PsiFile || parentAST.getPsi() instanceof ErrorTokenWrapper) {
                throw new SyntaxNotCompleteException();
            } else {
                out.addAll(provider.collectColumnNameRef((ColumnNameRef) target.getPsi(), lookupStr));
            }

        } else if (targetPsi instanceof NameFragmentRef) {
            NameFragmentRef nameFragment = (NameFragmentRef) target.getPsi();
            return new VariantProcessor(provider).process(nameFragment, contextHelper);

        } else if (targetType == PlSqlElementTypes.ALIAS_IDENT) {
            throw new SyntaxNotCompleteException();
        } else if (targetType == PlSqlElementTypes.SQL_CURSOR_FAKE_ATTR) {
            List<LookupElement> out1 = new ArrayList<LookupElement>();
            out1.add(GenericLookupElement.create("found", null));
            out1.add(GenericLookupElement.create("notfound", null));
            out1.add(GenericLookupElement.create("isopen", null));
            out1.add(GenericLookupElement.create("rowcount", null));
            out1.add(GenericLookupElement.create("bulk_rowcount", null));
            return out1;
        } else if (targetType == PlSqlElementTypes.COLUMN_TYPE_REF) {
            List<LookupElement> out1 = new ArrayList<LookupElement>();
            out1.add(GenericLookupElement.create("type", null));
            return out1;
        } else if (targetType == PlSqlElementTypes.TABLE_TYPE_REF) {
            List<LookupElement> out1 = new ArrayList<LookupElement>();
            out1.add(GenericLookupElement.create("rowtype", null));
            return out1;
        } else if (targetType == PlSqlElementTypes.REF_CURSOR) {
            List<LookupElement> out1 = new ArrayList<LookupElement>();
            out1.add(GenericLookupElement.create("type", null));
            out1.add(GenericLookupElement.create("rowtype", null));
            return out1;
        } else if (targetType == PlSqlElementTypes.ERROR_TOKEN_A) {
            final IElementType parentType = parentAST.getElementType();

            if (parentType == PlSqlTokenTypes.FILE) {
                throw new SyntaxNotCompleteException();
            } else if (parentType == PlSqlElementTypes.STATEMENT_LIST) {
                String ctxPath = ((CompositeElementExt) parentAST).getCtxPath1().getPath();
                // 1. variable assignment
                // 2. procedure call
                // 3. procedure call prefixed by package name, i.e. <package_name>.<proc_call>
                out.addAll(provider.collectProcCall(
                        ctxPath,
                        null,
                        lookupStr)
                );

                out.addAll(provider.collectVarVariants(
                        ctxPath,
                        lookupStr)
                );

                out.addAll(provider.collectPackageVariants(
                        lookupStr)
                );

                return out;
            } else if (parentType == PlSqlElementTypes.RETURN_STATEMENT) {
                if (getExecCtx() != null) {
                    String ctxPath = ((CompositeElementExt) parentAST).getCtxPath1().getPath();

                    // 1. return variable
                    // 2. function call result
                    // 3. a complex name - <package_name>.<object_name>
                    out.addAll(provider.collectVarVariants(ctxPath, lookupStr));
                    out.addAll(provider.collectFuncCall(ctxPath, null, lookupStr));
                    out.addAll(provider.collectPackageVariants(lookupStr));

                    return out;
                }
            } else if (parentType == PlSqlElementTypes.ASSIGNMENT_STATEMENT) {
                final ASTNode prev = PsiUtil.prevVisibleSibling(target);
                if (prev != null && prev.getElementType() == PlSqlTokenTypes.ASSIGNMENT_EQ) {
                    // rvalue
                    if (getExecCtx() != null) {
                        String ctxPath = ((CompositeElementExt) parentAST).getCtxPath1().getPath();
                        // 1. return variable
                        // 2. function call result
                        // 3. a complex name - <package_name>.<object_name>
                        out.addAll(provider.collectVarVariants(ctxPath, lookupStr));
                        out.addAll(provider.collectFuncCall(ctxPath, null, lookupStr));
                        out.addAll(provider.collectPackageVariants(lookupStr));
                    }
                }
            } else if (parentType == PlSqlElementTypes.RENAME_TABLE) {
                ElementarySyntaxGuesser ite = ElementarySyntaxGuesser.create(parentAST);
                IElementType[] types = ite.getTypeFor(target);
                for(IElementType type: types){
                    if(type == PlSqlElementTypes.TABLE_REF){
                        out.addAll(provider.collectTableNameVariants(lookupStr));
                    } else if(type == PlSqlElementTypes.SCHEMA_NAME){
                        // TODO - collect schema names
                    }
                }

            } else if (parentType == PlSqlElementTypes.PLSQL_BLOCK) {
                // todo -- generate lookup variants
                int h = 0;
            } else if (parentType == PlSqlElementTypes.SIMPLE_UPDATE_COMMAND) {
                throw new SyntaxNotCompleteException();
            } else if (parentType == PlSqlElementTypes.UPDATE_COMMAND) {
                throw new SyntaxNotCompleteException();
            }
        }

        return out;
    }

    public static String stripText(String text) {
        if(text == null){
            return null;
        }
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

    private VariantsProvider chooseSearchDomain(PlSqlFile plsqlFile) {
        DbUrl dbUrl = plsqlFile.getDbUrl();
        AbstractSchema i = PluginKeys2.SQL_INDEX_MAN.getData(plsqlFile.getProject()).getIndex(dbUrl, 0);
        return (i != null) ? i.getVariantsProvider() : null;
    }


    public static class ContextElementHelper {
        List<PlSqlElement> stack = new ArrayList<PlSqlElement>();

        void add(PlSqlElement e) {
            stack.add(e);
        }

        public SelectStatement findClosestContext() {
            for (PlSqlElement e : stack) {
                if (e instanceof SelectStatement) {
                    return (SelectStatement) e;
                }
            }
            return null;
        }

        public Iterator<PlSqlElement> ctxIterator(Class... kk) {
            return new ctxIterator(kk);
        }

        class ctxIterator implements Iterator<PlSqlElement> {
            int index = 0;
            Class[] kk;

            public ctxIterator(Class[] kk) {
                this.kk = kk;
            }

            public boolean hasNext() {
                for (; index != -1 && index < stack.size(); index++) {
                    final PlSqlElement e = stack.get(index);
                    for (Class clazz : kk) {
                        if (clazz.isInstance(e)) {
                            index++;
                            return true;
                        }
                    }
                }

                index = -1;
                return false;
            }

            public PlSqlElement next() {
                return index != -1 ? stack.get(index - 1) : null;
            }

            public void remove() {
            }
        }
    }

}
