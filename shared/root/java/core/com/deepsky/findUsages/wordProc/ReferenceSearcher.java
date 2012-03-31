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

package com.deepsky.findUsages.wordProc;

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.CreateSequence;
import com.deepsky.lang.plsql.psi.ddl.CreateView;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.psi.ddl.VColumnDefinition;
import com.deepsky.lang.plsql.psi.names.ColumnNameRef;
import com.deepsky.lang.plsql.psi.names.CompositeName;
import com.deepsky.lang.plsql.psi.ref.SequenceRef;
import com.deepsky.lang.plsql.psi.ref.TableRef;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.psibased.ReferenceProcessor;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.sqlIndex.WordIndexManager;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.Processor;


public class ReferenceSearcher {

    private static TokenSet coll2 = TokenSet.create(
            PlSqlElementTypes.FUNCTION_CALL, PlSqlElementTypes.C_RECORD_ITEM_REF
    );

    protected WordIndexManager manager;

    public ReferenceSearcher(WordIndexManager manager) {
        this.manager = manager;
    }


    private int findParentCtxType(PlSqlElement target){
        String ctxPath = target.getCtxPath1().getPath();
        String parentCtx = ContextPathUtil.extractParentCtx(ctxPath);
        return ContextPathUtil.extractLastCtxType(parentCtx);
    }

    public void doSearch(
            PlSqlElement target, final Processor<PsiReference> consumer) {
        // find text being searched
        String text = null;
        boolean localSearchScope = false;
        if (target instanceof VColumnDefinition) {
            text = ((VColumnDefinition) target).getColumnName();
        } else if (target instanceof ColumnDefinition) {
            text = ((ColumnDefinition) target).getColumnName();
        } else if (target instanceof TableDefinition) {
            text = ((TableDefinition) target).getTableName();
        } else if (target instanceof CreateView) {
            text = ((CreateView) target).getViewName();
        } else if (target instanceof VariableDecl) {
            int pCtx = findParentCtxType(target);
            localSearchScope = pCtx != -1 && pCtx != ContextPath.PACKAGE_SPEC;
            text = ((VariableDecl) target).getVariableName().getText();
        } else if (target instanceof Argument) {
            localSearchScope = true;
            text = ((Argument) target).getArgumentName();
        } else if (target instanceof Executable) {
            int pCtx = findParentCtxType(target);
            localSearchScope = pCtx != -1 && pCtx != ContextPath.PACKAGE_SPEC;

            text = ((Executable) target).getEName();
        } else if (target instanceof ExecutableSpec) {
            int pCtx = findParentCtxType(target);
            localSearchScope = pCtx != -1 && pCtx != ContextPath.PACKAGE_SPEC;

            text = ((ExecutableSpec) target).getEName();
        } else if (target instanceof PackageBody) {
            text = ((PackageBody) target).getPackageName();
        } else if (target instanceof PackageSpec) {
            text = ((PackageSpec) target).getPackageName();
        } else if (target instanceof TypeDeclaration) {
            int pCtx = findParentCtxType(target);
            localSearchScope = pCtx != -1 && pCtx != ContextPath.PACKAGE_SPEC;
            text = ((TypeDeclaration) target).getDeclName();
        } else if (target instanceof CreateSequence) {
            text = ((CreateSequence) target).getSequenceName();
        } else if (target instanceof RecordTypeItem) {
            text = ((RecordTypeItem) target).getRecordItemName();
        } else {
            throw new Error("FindUsages error: target type not supported: " + target);
        }


        final String finalText = text;
        final String ctxPathTarget = target.getCtxPath1().getPath();

        try {
            if (localSearchScope) {
                // search in the file which the target belongs to
                PsiElement element = target.getContainingFile();
                new ReferenceExtractorImpl(
                        finalText,
                        ctxPathTarget,
                        new ReferenceConsumer() {
                            public void consume(PsiReference reference) {
                                if (!consumer.process(reference)) {
                                    // break reference search cycle
                                    throw new CancelProcessing();
                                }
                            }
                        }).processTree(element);

            } else {
                // search over all files
                manager.scanDir(target.getProject(), new String[]{text}, new FileProcessor() {
                    public void processFile(PlSqlElement element, String[] hittedWords) { //String fullPath) {
                        new ReferenceExtractorImpl(
                                finalText,
                                ctxPathTarget,
                                new ReferenceConsumer() {
                                    public void consume(PsiReference reference) {
                                        if (!consumer.process(reference)) {
                                            // break reference search cycle
                                            throw new CancelProcessing();
                                        }
                                    }
                                }).processTree(element);

                    }
                });
            }
        } catch (CancelProcessing e) {
            // reference search was canceled
        }
    }


    protected static class ReferenceExtractorImpl extends ReferenceProcessor {
        String text;
        String ctxPathTarget;
        ReferenceConsumer consumer;

        public ReferenceExtractorImpl(
                String text, String ctxPathTarget, ReferenceConsumer consumer) {
            this.text = text.toLowerCase();
            this.ctxPathTarget = ctxPathTarget;
            this.consumer = consumer;
        }

        @Override
        protected void visitObjectReference(final ObjectReference node) {
            resolveTargetFragment(text, node, new ResolveCallback() {
                public void resolve(NameFragmentRef fragment) {
                    PsiElement target = fragment.resolve();
                    // implicitly check target on NULL
                    if (target instanceof PlSqlElement) {
                        if (((PlSqlElement) target).getCtxPath1().getPath().equals(ctxPathTarget)) {
                            // reference found
                            consumer.consume(fragment);
                        }
                    }
                }
            });
        }

        @Override
        protected void visitTypeNameReference(final TypeNameReference node) {
            resolveTargetFragment(text, node, new ResolveCallback() {
                public void resolve(NameFragmentRef fragment) {
                    PsiElement target = fragment.resolve();
                    // implicitly check target on NULL
                    if (target instanceof PlSqlElement) {
                        if (((PlSqlElement) target).getCtxPath1().getPath().equals(ctxPathTarget)) {
                            // reference found
                            consumer.consume(fragment);
                        }
                    }
                }
            });
        }

        @Override
        protected void visitFunctionCall(final FunctionCall node) {
            resolveTargetFragment(text, node.getCompositeName(), new ResolveCallback() {
                public void resolve(NameFragmentRef fragment) {
                    PsiElement target = fragment.resolve();
                    // implicitly check target on NULL
                    if (target instanceof PlSqlElement) {
                        if (((PlSqlElement) target).getCtxPath1().getPath().equals(ctxPathTarget)) {
                            // reference found
                            consumer.consume(fragment);
                        }
                    }
                }
            });
        }

        @Override
        protected void visitSpecFunctionCall(SpecFunctionCall node) {
            //todo -- implement me
        }

        @Override
        protected void visitProcedureCall(final ProcedureCall node) {
            resolveTargetFragment(text, node.getCompositeName(), new ResolveCallback() {
                public void resolve(NameFragmentRef fragment) {
                    PsiElement target = fragment.resolve();
                    // implicitly check target on NULL
                    if (target instanceof PlSqlElement) {
                        if (((PlSqlElement) target).getCtxPath1().getPath().equals(ctxPathTarget)) {
                            // reference found
                            consumer.consume(fragment);
                        }
                    }
                }
            });
        }

        @Override
        protected void visitCollectionMethodCall2(final CollectionMethodCall2 elem) {
            // procedure_call ( d:DOT procedure_call )+
            // example: l_ret_val(1).amk_cols.EXTEND
            ASTNode[] procs = elem.getNode().getChildren(TokenSet.create(PlSqlElementTypes.PROCEDURE_CALL));

            for (ASTNode n : procs) {
                ProcedureCall proc = (ProcedureCall) n.getPsi();

                resolveTargetFragment(text, proc.getCompositeName(), new ResolveCallback() {
                    public void resolve(NameFragmentRef fragment) {
                        PsiElement target = fragment.resolve();
                        // implicitly check target on NULL
                        if (target instanceof PlSqlElement) {
                            if (((PlSqlElement) target).getCtxPath1().getPath().equals(ctxPathTarget)) {
                                // reference found
                                consumer.consume(fragment);
                            }
                        }
                    }
                });
            }
        }

        @Override
        protected void visitCollectionMethodCall(CollectionMethodCall elem) {
            // function_call ( d:DOT! ((function_call) => function_call | c_record_item_ref ) )+
            ASTNode[] funcs = elem.getNode().getChildren(coll2);
            for (ASTNode n : funcs) {
                if(n.getElementType() == PlSqlElementTypes.FUNCTION_CALL){
                    FunctionCall func = (FunctionCall) n.getPsi();
                    resolveTargetFragment(text, func.getCompositeName(), new ResolveCallback() {
                        public void resolve(NameFragmentRef fragment) {
                            PsiElement target = fragment.resolve();
                            // implicitly check target on NULL
                            if (target instanceof PlSqlElement) {
                                if (((PlSqlElement) target).getCtxPath1().getPath().equals(ctxPathTarget)) {
                                    // reference found
                                    consumer.consume(fragment);
                                }
                            }
                        }
                    });
                } else if(n.getElementType() == PlSqlElementTypes.C_RECORD_ITEM_REF){
                    if(n.getText().equalsIgnoreCase(text)){
                        CRecordItemRef ref = (CRecordItemRef)n.getPsi();
                        PsiElement target = ref.resolve();
                        // implicitly check target on NULL
                        if (target instanceof PlSqlElement) {
                            if (((PlSqlElement) target).getCtxPath1().getPath().equals(ctxPathTarget)) {
                                // reference found
                                consumer.consume(ref);
                            }
                        }
                    }
                }
            }
        }

        @Override
        protected void visitTableRef(final TableRef table) {
            String tableName = table.getTableName().toLowerCase();
            if (ReferenceExtractorImpl.this.text.equals(tableName)) {
                PsiElement _target = table.resolve();
                if (_target != null && ((PlSqlElement) _target).getCtxPath1().getPath().equals(ctxPathTarget)) {
                    // reference found
                    consumer.consume(table);
                }
            }
        }

        @Override
        protected void visitSequenceRef(SequenceRef node) {
            final String sequenceName = node.getSequenceName().toLowerCase();
            if (ReferenceExtractorImpl.this.text.equals(sequenceName)) {
                PsiElement _target = node.resolve();
                if (_target != null && ((PlSqlElement) _target).getCtxPath1().getPath().equals(ctxPathTarget)) {
                    // reference found
                    consumer.consume(node);
                }
            }
        }


        @Override
        protected void visitColumnNameRef(ColumnNameRef columnNameRef) {
            final String name = columnNameRef.getColumnName().toLowerCase();
            if(ReferenceExtractorImpl.this.text.equalsIgnoreCase(name)){
                PsiElement _target = columnNameRef.resolve();
                if (_target != null && ((PlSqlElement) _target).getCtxPath1().getPath().equals(ctxPathTarget)) {
                    // reference found
                    consumer.consume(columnNameRef);
                }
            }
        }

        @Override
        protected void visitColumnSpec(ColumnSpec node) {
            resolveTargetFragment(text, node, new ResolveCallback() {
                public void resolve(NameFragmentRef fragment) {
                    PsiElement target = fragment.resolve();
                    // implicitly check target on NULL
                    if (target instanceof PlSqlElement) {
                        if (((PlSqlElement) target).getCtxPath1().getPath().equals(ctxPathTarget)) {
                            // reference found
                            consumer.consume(fragment);
                        }
                    }
                }
            });
        }


        // helper method
        private void resolveTargetFragment(String text, CompositeName cname, ResolveCallback callback) {
            if (cname.getText().toLowerCase().indexOf(text) != -1) {
                NameFragmentRef[] fragments = cname.getNamePieces();
                try {
                    for (NameFragmentRef f : fragments) {
                        if (text.equalsIgnoreCase(f.getFragmentText())) {
                            callback.resolve(f);
                            break;
                        }
                    }
                } catch (CancelProcessing e) {
                    throw e;
                } catch (Throwable e) {
                    // ignore
                    int hh = 0;
                }
            }
        }
    }



    private interface ResolveCallback {
        void resolve(NameFragmentRef fragment);
    }


    protected static interface ReferenceConsumer {
        void consume(PsiReference reference);
    }

    protected static class CancelProcessing extends Error {
    }


}

