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

package com.deepsky.findUsages;

import com.deepsky.database.ObjectCache;
import com.deepsky.database.ObjectCacheFactory;
import com.deepsky.database.SqlScriptManager;
import com.deepsky.database.cache.Cache;
import com.deepsky.findUsages.persistence.SqlSearchParameters;
import com.deepsky.findUsages.scopes.DbScopeDescriptorProvider;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.CreateView;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.psi.ddl.VColumnDefinition;
import com.deepsky.lang.plsql.psi.ref.DDLTable;
import com.deepsky.lang.plsql.psi.ref.Table;
import com.deepsky.lang.plsql.psi.types.ColumnTypeRef;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.search.TextOccurenceProcessor;
import com.intellij.psi.search.UsageSearchContext;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.util.Processor;
import com.intellij.util.QueryExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ColumnSearcher implements QueryExecutor<PsiReference, ReferencesSearch.SearchParameters> {

    private static final LoggerProxy log = LoggerProxy.getInstance("#ColumnSearcher");

    public boolean execute(ReferencesSearch.SearchParameters queryParameters, final Processor<PsiReference> consumer) {

        final Project project = queryParameters.getElementToSearch().getProject();
//        final Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
        SqlSearchParameters searchParams = SqlSearchParameters.getInstance(project);

//        if (project == null) {
//            return false;
//        }

        // this is needed just for getting a real SearchScope
        SearchScope scope = searchParams.searchScope;

        if (scope instanceof DbScopeDescriptorProvider.DbSearchScope) {
            // search the database
            return searchDatabase(project, searchParams, queryParameters.getElementToSearch(), scope, consumer);
        } else {
            // search file system
            return execute(project, searchParams, queryParameters.getElementToSearch(), scope, consumer);
        }
    }


    public boolean execute(final Project project, final SqlSearchParameters searchParams, PsiElement elemToSearch, SearchScope scope, final Processor<PsiReference> consumer) {
        final PsiElement elem = elemToSearch; //queryParameters.getElementToSearch();

        if (elem instanceof VColumnDefinition || elem instanceof ColumnDefinition) {
            String text = "";
            if (elem instanceof VColumnDefinition) {
                text = ((VColumnDefinition) elem).getColumnName();
            } else {
                text = ((ColumnDefinition) elem).getColumnName();
            }

            boolean ret = doSearch1(scope, project, text, new TextOccurenceProcessor() {
                public boolean execute(PsiElement element, int offsetInElement) {
                    if (searchParams.isColumnUsages && element instanceof ColumnNameDDL) {
                        if (((ColumnNameDDL) element).isReferenceTo(elem)) {
                            consumer.process((PsiReference) element);
                        }
                    } else if (searchParams.isColumnUsages && element instanceof NameFragmentRef) {
                        if (((NameFragmentRef) element).isReferenceTo(elem)) {
                            consumer.process((PsiReference) element);
                        }
                    } else if (searchParams.isUsagesOfColumnTypeRef && element instanceof ColumnNameRef) {
                        if (element.getParent() instanceof ColumnTypeRef) {
                            ColumnNameRef ref = (ColumnNameRef) element;
                            if (ref.isReferenceTo(elem)) {
                                consumer.process(ref);
                            }
                        }
                    }

                    return true;
                }
            });
            return false;
        } else if (elem instanceof TableDefinition) {
            ColumnDefinition[] vcolumns = new ColumnDefinition[0];
            if (searchParams != null && searchParams.isUsagesOfColumns) {
                // search for column usages
                vcolumns = ((TableDefinition) elem).getColumnDefs();
            }

            // search for table usages
            final boolean isTableUsages = searchParams != null && searchParams.isTableUsages;
            final ColumnDefinition[] finalVcolumns = vcolumns;

//            String text = ((TableDefinition) elem).getTableName();
            String[] words = new String[]{};
            if (finalVcolumns.length > 0 && isTableUsages) {
                words = new String[finalVcolumns.length + 1];
                for (int i = 0; i < vcolumns.length; i++) {
                    words[i + 1] = vcolumns[i].getColumnName();
                }
                words[0] = ((TableDefinition) elem).getTableName();
            } else if (finalVcolumns.length > 0 && !isTableUsages) {
                words = new String[finalVcolumns.length];
                for (int i = 0; i < vcolumns.length; i++) {
                    words[i] = vcolumns[i].getColumnName();
                }
            } else if (isTableUsages) {
                // finalVcolumns.length == 0 && isTableUsages == true
                words = new String[]{((TableDefinition) elem).getTableName()};
            }

            boolean ret = doSearchWords(scope, project, words, new TextOccurenceProcessor() {
                public boolean execute(PsiElement element, int offsetInElement) {
                    if (element instanceof ColumnNameDDL) {
                        ColumnNameDDL columnNameDDL = (ColumnNameDDL) element;
                        for (ColumnDefinition column : finalVcolumns) {
                            if (columnNameDDL.isReferenceTo(column)) {
                                consumer.process(columnNameDDL);
                            }
                        }
                    } else if (element instanceof NameFragmentRef) {
                        NameFragmentRef ref = (NameFragmentRef) element;
                        for (ColumnDefinition column : finalVcolumns) {
                            if (ref.isReferenceTo(column)) {
                                consumer.process(ref);
                            }
                        }
                    } else if (element instanceof Table) {
                        Table ref = (Table) element;
                        if (isTableUsages && ref.isReferenceTo(elem)) {
                            consumer.process(ref);
                        }
                    } else if (element instanceof DDLTable) {
                        DDLTable ref = (DDLTable) element;
                        if (isTableUsages && ref.isReferenceTo(elem)) {
                            consumer.process(ref);
                        }
                    }

                    return true;
                }
            });
            return false;
        } else if (elem instanceof CreateView) {
            VColumnDefinition[] vcolumns = new VColumnDefinition[0];
            if (searchParams != null && searchParams.isUsagesOfColumns) {
                // search for column usages
                vcolumns = ((CreateView) elem).getColumnDefs();
            }

            // search for table usages
            final boolean isViewUsages = searchParams != null && searchParams.isViewUsages;
            final VColumnDefinition[] finalVcolumns = vcolumns;
            String[] words = new String[]{};
            if (finalVcolumns.length > 0 && isViewUsages) {
                words = new String[finalVcolumns.length + 1];
                for (int i = 0; i < vcolumns.length; i++) {
                    words[i + 1] = vcolumns[i].getColumnName();
                }
                words[0] = ((CreateView) elem).getViewName();
            } else if (finalVcolumns.length > 0 && !isViewUsages) {
                words = new String[finalVcolumns.length];
                for (int i = 0; i < vcolumns.length; i++) {
                    words[i] = vcolumns[i].getColumnName();
                }
            } else if (isViewUsages) {
                // finalVcolumns.length == 0 && isTableUsages == true
                words = new String[]{((CreateView) elem).getViewName()};
            }

//            String text = ((CreateView) elem).getViewName();
            boolean ret = doSearchWords(scope, project, words, new TextOccurenceProcessor() {
                public boolean execute(PsiElement element, int offsetInElement) {
//                    if (element instanceof ColumnNameDDL) {
//                        ColumnNameDDL columnNameDDL = (ColumnNameDDL) element;
//                        for (ColumnDefinition column : finalVcolumns) {
//                            if (columnNameDDL.isReferenceTo(column)) {
//                                consumer.process(columnNameDDL);
//                            }
//                        }
//                    } else
                    if (element instanceof NameFragmentRef) {
                        NameFragmentRef ref = (NameFragmentRef) element;
                        for (VColumnDefinition column : finalVcolumns) {
                            if (ref.isReferenceTo(column)) {
                                consumer.process(ref);
                            }
                        }
                    } else if (element instanceof Table) {
                        Table ref = (Table) element;
                        if (isViewUsages && ref.isReferenceTo(elem)) {
                            consumer.process(ref);
                        }
//                    } else if (element instanceof DDLTable) {
//                        DDLTable ref = (DDLTable) element;
//                        if (isUsages && ref.isReferenceTo(elem)) {
//                            consumer.process(ref);
//                        }
                    }

                    return true;
                }
            });
            return false;
        } else {
            return true;
        }

    }

    boolean doSearch1(SearchScope scope, Project project, String textToSearch, TextOccurenceProcessor processor) {
        //short searchContext = UsageSearchContext.IN_CODE | UsageSearchContext.IN_COMMENTS | UsageSearchContext.IN_FOREIGN_LANGUAGES;
        final PsiManager psiManager = PsiManager.getInstance(project);
        boolean toContinue = psiManager.getSearchHelper().processElementsWithWord(
                processor,
                scope,
                textToSearch,
                UsageSearchContext.IN_FOREIGN_LANGUAGES,
                false
        );

        return toContinue;
    }


    boolean doSearchWords(SearchScope scope, Project project, String[] textToSearch, TextOccurenceProcessor processor) {
        //short searchContext = UsageSearchContext.IN_CODE | UsageSearchContext.IN_COMMENTS | UsageSearchContext.IN_FOREIGN_LANGUAGES;
        final PsiManager psiManager = PsiManager.getInstance(project);
        for (String text : textToSearch) {
            boolean toContinue = psiManager.getSearchHelper().processElementsWithWord(
                    processor,
                    scope,
                    text,
                    UsageSearchContext.IN_FOREIGN_LANGUAGES,
                    false
            );
        }

        return true;
    }

    public boolean searchDatabase(final @NotNull Project project, final SqlSearchParameters searchParams,PsiElement elemToSearch, SearchScope scope, final Processor<PsiReference> consumer) {
        final PsiElement elem = elemToSearch; //queryParameters.getElementToSearch();

        if (elem instanceof VColumnDefinition || elem instanceof ColumnDefinition) {
            String text = "";
            if (elem instanceof VColumnDefinition) {
                text = ((VColumnDefinition) elem).getColumnName();
            } else {
                text = ((ColumnDefinition) elem).getColumnName();
            }

            doSearch(project, text,
                    new PlSqlElementVisitor() {
                        public void visitElement(PsiElement element) {
                            super.visitElement(element);
                            element.acceptChildren(this);
                        }

                        public void visitColumnNameDDL(ColumnNameDDL columnNameDDL) {
                            if (searchParams.isColumnUsages && columnNameDDL.isReferenceTo(elem)) {
                                consumer.process(columnNameDDL);
                            }
                        }

                        public void visitNameFragmentRef(NameFragmentRef ref) {
                            if (searchParams.isColumnUsages && ref.isReferenceTo(elem)) {
                                consumer.process(ref);
                            }
                        }

                        public void visitColumnNameRef(ColumnNameRef ref) {
                            // check for %TYPE
                            if (searchParams.isUsagesOfColumnTypeRef && ref.getParent() instanceof ColumnTypeRef) {
                                if (ref.isReferenceTo(elem)) {
                                    consumer.process(ref);
                                }
                            }
                        }
                    });

            return false;
        } else if (elem instanceof TableDefinition) {
            ColumnDefinition[] vcolumns = new ColumnDefinition[0];
            if (searchParams != null && searchParams.isUsagesOfColumns) {
                // search for column usages
                vcolumns = ((TableDefinition) elem).getColumnDefs();
            }

            // search for table usages
            final boolean isUsages = searchParams != null && searchParams.isTableUsages;
            final ColumnDefinition[] finalVcolumns = vcolumns;

            doSearch(project, ((TableDefinition) elem).getTableName(),
//            doSearch(path, project,
                    new PlSqlElementVisitor() {
                        public void visitElement(PsiElement element) {
                            super.visitElement(element);
                            element.acceptChildren(this);
                        }

                        public void visitTable(Table ref) {
                            // check for %ROWTYPE
                            if (isUsages && ref.isReferenceTo(elem)) {
                                consumer.process(ref);
                            }
                        }

                        public void visitDDLTable(DDLTable ref) {
                            if (isUsages && ref.isReferenceTo(elem)) {
                                consumer.process(ref);
                            }
                        }

                        public void visitColumnNameDDL(ColumnNameDDL columnNameDDL) {
                            for (ColumnDefinition column : finalVcolumns) {
                                if (columnNameDDL.isReferenceTo(column)) {
                                    consumer.process(columnNameDDL);
                                }
                            }
                        }

                        public void visitNameFragmentRef(NameFragmentRef ref) {
                            for (ColumnDefinition column : finalVcolumns) {
                                if (ref.isReferenceTo(column)) {
                                    consumer.process(ref);
                                }
                            }
                        }
                    });

            return false;
        } else if (elem instanceof CreateView) {

            VColumnDefinition[] vcolumns = new VColumnDefinition[0];
            if (searchParams != null && searchParams.isUsagesOfColumns) {
                // search for column usages
                vcolumns = ((CreateView) elem).getColumnDefs();
            }

            // search for table usages
            final boolean isUsages = searchParams != null && searchParams.isViewUsages;
            final VColumnDefinition[] finalVcolumns = vcolumns;
            doSearch(project, ((CreateView) elem).getViewName(),
                    new PlSqlElementVisitor() {
                        public void visitElement(PsiElement element) {
                            super.visitElement(element);
                            element.acceptChildren(this);
                        }

                        public void visitTable(Table ref) {
                            if (isUsages && ref.isReferenceTo(elem)) {
                                consumer.process(ref);
                            }
                        }

                        public void visitNameFragmentRef(NameFragmentRef ref) {
                            for (VColumnDefinition column : finalVcolumns) {
                                if (ref.isReferenceTo(column)) {
                                    consumer.process(ref);
                                }
                            }
                        }
                    });

            return false;
        } else if (elem instanceof VariableDecl) {
            final VariableDecl var = (VariableDecl) elem;
            // search for usages of variable
            doSearch(project, var.getDeclName(),
                    new PlSqlElementVisitor() {
                        public void visitElement(PsiElement element) {
                            super.visitElement(element);
                            element.acceptChildren(this);
                        }

                        public void visitNameFragmentRef(NameFragmentRef ref) {
                            if (ref.isReferenceTo(var)) {
                                consumer.process(ref);
                            }
                        }
                    });

            return false;
        } else if (elem instanceof Argument) {
            final Argument argument = (Argument) elem;
            // search for usages of variable
            doSearch(project, argument.getArgumentName(),
                    new PlSqlElementVisitor() {
                        public void visitElement(PsiElement element) {
                            super.visitElement(element);
                            element.acceptChildren(this);
                        }

                        public void visitNameFragmentRef(NameFragmentRef ref) {
                            if (ref.isReferenceTo(argument)) {
                                consumer.process(ref);
                            }
                        }
                    });

            return false;
        } else if (elem instanceof Executable) {
            final Executable exec = (Executable) elem;
            // search for usages of variable
            doSearch(project, exec.getEName(),
                    new PlSqlElementVisitor() {
                        public void visitElement(PsiElement element) {
                            super.visitElement(element);
                            element.acceptChildren(this);
                        }

                        public void visitCallableCompositeName(CallableCompositeName compositeName) {
                            NameFragmentRef[] refs = compositeName.getNamePieces();
                            PsiElement psi = refs[refs.length-1].resolve();

                            if(psi == exec){
                                consumer.process(refs[refs.length-1]);
                            }
                        }
                    });

            return false;
        } else if (elem instanceof ExecutableSpec) {
            final ExecutableSpec exec = (ExecutableSpec) elem;
            // search for usages of variable
            doSearch(project, exec.getEName(),
                    new PlSqlElementVisitor() {
                        public void visitElement(PsiElement element) {
                            super.visitElement(element);
                            element.acceptChildren(this);
                        }

                        public void visitCallableCompositeName(CallableCompositeName compositeName) {
                            NameFragmentRef[] refs = compositeName.getNamePieces();
                            PsiElement psi = refs[refs.length-1].resolve();

                            if(psi == exec){
                                consumer.process(refs[refs.length-1]);
                            }
                        }
                    });

            return false;
        } else {
            return true;
        }
    }


//    VColumnDefinition[] getVColumnDef(@NotNull CreateView creatView) {
//        return creatView.getColumnDefs();
//    }
//
//
//    ColumnDefinition[] getColumnDef(@NotNull TableDefinition tabDef) {
//        return tabDef.getColumnDefs();
//    }

    void doSearch(final Project project, final String text, final PlSqlElementVisitor visitor) {
        ApplicationManager.getApplication().runReadAction(new Runnable() {
            public void run() {
                String[] types = new String[]{
                        DbObject.PACKAGE,
                        DbObject.PACKAGE_BODY,
                        DbObject.TRIGGER,
                        DbObject.FUNCTION,
                        DbObject.FUNCTION_BODY,
                        DbObject.PROCEDURE,
                        DbObject.PROCEDURE_BODY,
                        DbObject.TABLE,
                        DbObject.VIEW
                };

                ObjectCache ocache = PluginKeys.OBJECT_CACHE.getData(project); //ObjectCacheFactory.getObjectCache();
                if (ocache.isReady()) {
                    String user = ocache.getCurrentUser();
                    Cache c0 = ocache.getCache(user);
                    List<Cache.NameTypePair> pairs = c0.searchContentIndex(types, text);
                    for (Cache.NameTypePair pair : pairs) {
                        DbObject dbo = c0.get(pair.getName(), pair.getType());
                        if (dbo != null) {
                            PsiElement psi = SqlScriptManager.openFile(project, dbo);
                            try {
                                if (psi != null) {
                                    psi.acceptChildren(visitor);
                                }
                            } catch (SyntaxTreeCorruptedException e) {
                                log.error("SyntaxTreeCorruptedException!");
                            }
                        }
                    }
                }
            }
        });
    }


}
