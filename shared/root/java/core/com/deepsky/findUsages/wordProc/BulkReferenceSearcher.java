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

import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.CreateSequence;
import com.deepsky.lang.plsql.psi.ddl.CreateView;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.psi.ddl.VColumnDefinition;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.sqlIndex.WordIndexManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.util.Processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BulkReferenceSearcher extends ReferenceSearcher {

    public BulkReferenceSearcher(WordIndexManager manager) {
        super(manager);
    }


    private int findParentCtxType(PlSqlElement target) {
        String ctxPath = target.getCtxPath1().getPath();
        String parentCtx = ContextPathUtil.extractParentCtx(ctxPath);
        return ContextPathUtil.extractLastCtxType(parentCtx);
    }


    public void doSearch(
            Project project, PsiElement[] targets, final Processor<PsiReference> consumer) {

        final ElementMatcher m = buildMatcher(targets);
        if (!m.isValid()) {
            return;
        }
        try {
            // search over all files
            manager.scanDir(project, m.getTextToMatch(), new FileProcessor() {
                public void processFile(PlSqlElement element, String[] hittedWords) {
                    for (Pair p : m.getPairs(hittedWords)) {
                        new ReferenceExtractorImpl(
                                p.text,
                                p.ctxPath,
                                new ReferenceConsumer() {
                                    public void consume(PsiReference reference) {
                                        if (!consumer.process(reference)) {
                                            // break reference search cycle
                                            throw new CancelProcessing();
                                        }
                                    }
                                }).processTree(element);
                    }
                }
            });
        } catch (CancelProcessing e) {
            // reference search was canceled
        }
    }

    private ElementMatcher buildMatcher(PsiElement[] targets) {
        ElementMatcher matcher = new ElementMatcher();
        for (PsiElement e : targets) {
            boolean localSearchScope = false;
            String text = null;
            if (e instanceof VColumnDefinition) {
                text = ((VColumnDefinition) e).getColumnName();
            } else if (e instanceof ColumnDefinition) {
                text = ((ColumnDefinition) e).getColumnName();
            } else if (e instanceof TableDefinition) {
                text = ((TableDefinition) e).getTableName();
            } else if (e instanceof CreateView) {
                text = ((CreateView) e).getViewName();
            } else if (e instanceof VariableDecl) {
                int pCtx = findParentCtxType((PlSqlElement) e);
                localSearchScope = pCtx != -1 && pCtx != ContextPath.PACKAGE_SPEC;
                text = ((VariableDecl) e).getVariableName().getText();
            } else if (e instanceof Argument) {
                localSearchScope = true;
                text = ((Argument) e).getArgumentName();
            } else if (e instanceof Executable) {
                int pCtx = findParentCtxType((PlSqlElement) e);
                localSearchScope = pCtx != -1 && pCtx != ContextPath.PACKAGE_SPEC;

                text = ((Executable) e).getEName();
            } else if (e instanceof ExecutableSpec) {
                int pCtx = findParentCtxType((PlSqlElement) e);
                localSearchScope = pCtx != -1 && pCtx != ContextPath.PACKAGE_SPEC;

                text = ((ExecutableSpec) e).getEName();
            } else if (e instanceof PackageBody) {
                text = ((PackageBody) e).getPackageName();
            } else if (e instanceof PackageSpec) {
                text = ((PackageSpec) e).getPackageName();
            } else if (e instanceof TypeDeclaration) {
                int pCtx = findParentCtxType((PlSqlElement) e);
                localSearchScope = pCtx != -1 && pCtx != ContextPath.PACKAGE_SPEC;
                text = ((TypeDeclaration) e).getDeclName();
            } else if (e instanceof CreateSequence) {
                text = ((CreateSequence) e).getSequenceName();
            } else if (e instanceof RecordTypeItem) {
                text = ((RecordTypeItem) e).getRecordItemName();
            } else {
                throw new Error("FindUsages error: target type not supported: " + e);
            }

            matcher.add((PlSqlElement) e, text); //, e.getCtxPath1().getPath());
            matcher.setSearchLocalScope(localSearchScope);
        }

        matcher.complete();
        return matcher;
    }


    private class ElementMatcher {
        private boolean searchScopeLocal;
        List<Pair> pairs = new ArrayList<Pair>();

        public void add(PlSqlElement e, String text){ //}, String ctxPath) {
            pairs.add(new Pair(text.toLowerCase(), e.getCtxPath1().getPath()));
        }

        public void setSearchLocalScope(boolean localSearchScope) {
            // not supported
        }

//        public boolean getSearchScopeLocal() {
//            return searchScopeLocal;
//        }

        public boolean isValid() {
            return pairs.size() > 0;
        }


        public String[] getTextToMatch() {
            String[] out = new String[pairs.size()];
            for(int i = 0; i < pairs.size(); i++){
                out[i] = pairs.get(i).text;
            }
            return out;
        }


        public Pair[] getPairs(String[] params ) {
            List<Pair> out = new ArrayList<Pair>();
            for(String text: params){
                int index = Collections.binarySearch(pairs, new Pair(text,""), new Comparator<Pair>(){
                    public int compare(Pair o1, Pair o2) {
                        return o1.text.compareTo(o2.text);
                    }
                });

                if(index != -1){
                    out.add(pairs.get(index));
                }
            }

            return out.toArray(new Pair[out.size()]);
        }

        public void complete() {
            Collections.sort(pairs, new Comparator<Pair>(){
                public int compare(Pair o1, Pair o2) {
                    return o1.text.compareTo(o2.text);
                }
            });
        }
    }


    private class Pair {
        public String text; // lower case
        public String ctxPath;

        public Pair(String text, String ctxPath){
            this.text = text;
            this.ctxPath = ctxPath;
        }
    }

}
