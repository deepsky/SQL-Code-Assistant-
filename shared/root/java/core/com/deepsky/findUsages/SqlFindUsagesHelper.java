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
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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

import com.deepsky.findUsages.actions.FindUsagesOptionsAdopted;
import com.deepsky.findUsages.actions.SqlSpecificSearchRunner;
import com.deepsky.findUsages.actions.SqlUsageTarget;
import com.deepsky.findUsages.actions.SqlUsageViewPresentation;
import com.deepsky.findUsages.search.*;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.CreateView;
import com.deepsky.lang.plsql.psi.ddl.VColumnDefinition;
import com.deepsky.lang.plsql.psi.impl.NameFragmentRefImpl;
import com.deepsky.lang.plsql.psi.ref.DDLTable;
import com.deepsky.lang.plsql.psi.ref.DDLView;
import com.deepsky.lang.plsql.psi.ref.Table;
import com.deepsky.lang.plsql.psi.resolve.ASTNodeHandler;
import com.deepsky.lang.plsql.psi.resolve.ASTTreeProcessor;
import com.intellij.find.findUsages.FindUsagesOptions;
import com.intellij.ide.DataManager;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.usages.UsageInfoToUsageConverter;
import com.intellij.usages.UsageTarget;
import com.intellij.usages.UsageView;
import com.intellij.usages.UsageViewManager;
import org.jetbrains.annotations.NotNull;

public class SqlFindUsagesHelper {

    public static void runFindUsages(Project project, PsiElement _psi) {
        GenericSearchOptions p = canApplyFindUsagesTo(_psi);

        if (p != null) {
            _psi = p.getTarget();

            FindUsagesOptions options = new FindUsagesOptionsAdopted(project, DataManager.getInstance().getDataContext());

            SqlFindUsagesDialog dialog = new SqlFindUsagesDialog(
                    project, options, true, true, false, p
            );
            dialog.show();
            if (dialog.isOK()) {
                options = new FindUsagesOptionsAdopted(project, DataManager.getInstance().getDataContext());

                SqlUsageTarget usageTarget = new SqlUsageTarget();
                usageTarget.setPresentableText(p.getTargetPresentableText());
                UsageTarget[] utargets = new UsageTarget[]{usageTarget};

                SqlUsageViewPresentation uvpresentation = new SqlUsageViewPresentation();
                uvpresentation.setCodeUsages(false); //true);
                uvpresentation.setCodeUsagesString("Found Usages");
                uvpresentation.setScopeText("Scope");
                uvpresentation.setTabName("Tab Name1");
                uvpresentation.setTargetsNodeText(p.getTargetNode());
                uvpresentation.setToolwindowTitle("Toolwindow Title1");
                uvpresentation.setUsagesString("Usages");
                uvpresentation.setTabText(p.getPresentableSearchParameters() + " in " + options.searchScope.getDisplayName());
                uvpresentation.setTabName("Tab Name");

                final UsageInfoToUsageConverter.TargetElementsDescriptor descriptor = new UsageInfoToUsageConverter.TargetElementsDescriptor(_psi);

                UsageViewManager uvmanager = UsageViewManager.getInstance(project);
                uvmanager.searchAndShowUsages(utargets,
                        new SqlSpecificSearchRunner.SearchUsageFactory(descriptor, options),
                        true, true,
                        uvpresentation,
                        new UsageViewManager.UsageViewStateListener() {
                            public void usageViewCreated(UsageView usageView) {
                                int hh = 0;
                            }

                            public void findingUsagesFinished(UsageView usageView) {
                                int hh = 0;
                            }
                        });
            }
        }
    }


    public static GenericSearchOptions canApplyFindUsagesTo(PsiElement element) {
        if (element == null) {
            return null;
        }

        final PsiElement[] etype = new PsiElement[]{null};
        ASTTreeProcessor treeProcessor = new ASTTreeProcessor();
        treeProcessor.add(new ASTNodeHandler() {
            @NotNull
            public TokenSet getTokenSet() {
                return TokenSet.create(
                        PlSqlElementTypes.PACKAGE_NAME,
                        PlSqlElementTypes.OBJECT_NAME, // name of Procedure or Function
                        PlSqlElementTypes.VAR_REF, // name of variable or table column
                        PlSqlElementTypes.TABLE_NAME,
                        PlSqlElementTypes.VIEW_NAME_DDL,
                        PlSqlElementTypes.COLUMN_NAME_DDL,
                        PlSqlElementTypes.TABLE_NAME_DDL,
                        PlSqlElementTypes.TYPE_NAME_REF,
                        PlSqlElementTypes.COLUMN_NAME_REF,
                        PlSqlElementTypes.CALLABLE_NAME_REF, //
                        PlSqlElementTypes.SEQUENCE_EXPR,
                        PlSqlElementTypes.COLUMN_SPEC,
                        PlSqlElementTypes.NAME_FRAGMENT,
                        PlSqlElementTypes.VARIABLE_NAME,
                        PlSqlElementTypes.PARAMETER_NAME,
                        PlSqlElementTypes.PLSQL_VAR_REF,

                        PlSqlElementTypes.CREATE_VIEW,
                        PlSqlElementTypes.TABLE_DEF
                );
            }

            public boolean handleNode(@NotNull ASTNode node) {
                etype[0] = node.getPsi();
                return true;
            }
        });

        treeProcessor.process(element.getNode(), true /* break on first hit*/);

        if (etype[0] == null || etype[0].getNode() == null) {
            return null;
        }

        IElementType ietype = etype[0].getNode().getElementType();

        if (ietype == PlSqlElementTypes.PACKAGE_NAME) {
            return new PackageSearchOptions(etype[0].getText());
        } else if (ietype == PlSqlElementTypes.NAME_FRAGMENT) {
            PsiElement referenced = ((NameFragmentRefImpl) etype[0]).resolve();
            if (referenced instanceof VColumnDefinition) {
                VColumnDefinition vcolumnDef = (VColumnDefinition) referenced;
                return new ViewColumnSearchOptions(vcolumnDef);
            } else if (referenced instanceof ColumnDefinition) {
                ColumnDefinition columnDef = (ColumnDefinition) referenced;
                return new TableColumnSearchOptions(columnDef);
            } else if (referenced instanceof VariableDecl) {
                VariableDecl var = (VariableDecl) referenced;
                return new VariableSearchOptions(var);
            } else if (referenced instanceof Argument) {
                Argument argument = (Argument) referenced;
                return new ArgumentSearchOptions(argument);
            } else if (referenced instanceof Function) {
                Function exec = (Function) referenced;
                return new FunctionSearchOptions(exec);
            } else if (referenced instanceof Procedure) {
                Procedure exec = (Procedure) referenced;
                return new ProcedureSearchOptions(exec);
            } else if(referenced instanceof FunctionSpec){
                FunctionSpec exec = (FunctionSpec) referenced;
                return new FunctionSearchOptions(exec);
            } else if(referenced instanceof ProcedureSpec){
                ProcedureSpec exec = (ProcedureSpec) referenced;
                return new ProcedureSearchOptions(exec);
            } else {
                // todo --
            }

        } else if (ietype == PlSqlElementTypes.TABLE_NAME) {
            PsiElement psi = ((Table) etype[0]).resolve();
            if (psi instanceof CreateView) {
                return new ViewSearchOptions((CreateView) psi);
            } else if (psi instanceof TableDefinition) {
                return new TableSearchOptions((TableDefinition) psi);
            }
        } else if (ietype == PlSqlElementTypes.VIEW_NAME_DDL) {
            PsiElement psi = ((DDLView) etype[0]).resolve();
            if (psi instanceof CreateView) {
                return new ViewSearchOptions((CreateView) psi);
            }
        } else if (ietype == PlSqlElementTypes.TABLE_NAME_DDL) {
            PsiElement psi = ((DDLTable) etype[0]).resolve();
            if (psi instanceof TableDefinition) {
                return new TableSearchOptions((TableDefinition) psi);
            }
        } else if (ietype == PlSqlElementTypes.COLUMN_NAME_DDL) {
            PsiElement referenced = ((ColumnNameDDL) etype[0]).resolve();
            if (referenced instanceof VColumnDefinition) {
                VColumnDefinition vcolumnDef = (VColumnDefinition) referenced;
                return new ViewColumnSearchOptions(vcolumnDef);
            } else if (referenced instanceof ColumnDefinition) {
                ColumnDefinition columnDef = (ColumnDefinition) referenced;
                return new TableColumnSearchOptions(columnDef);
            } else {
                // todo --
            }

        } else if (ietype == PlSqlElementTypes.COLUMN_NAME_REF) {
            PsiElement referenced = ((ColumnNameRef) etype[0]).resolve();
            if (referenced instanceof VColumnDefinition) {
                VColumnDefinition vcolumnDef = (VColumnDefinition) referenced;
                return new ViewColumnSearchOptions(vcolumnDef);
            } else if (referenced instanceof ColumnDefinition) {
                ColumnDefinition columnDef = (ColumnDefinition) referenced;
                return new TableColumnSearchOptions(columnDef);
            } else {
                // todo --
            }
        } else if (ietype == PlSqlElementTypes.VARIABLE_NAME) {
            VariableName varName = (VariableName) etype[0];
            VariableDecl var = varName.getVariableDecl();
            return new VariableSearchOptions(var);
        } else if (ietype == PlSqlElementTypes.PARAMETER_NAME) {
            ParameterName varName = (ParameterName) etype[0];
            Argument argument = varName.getArgument();
            return new ArgumentSearchOptions(argument);
        } else if (ietype == PlSqlElementTypes.OBJECT_NAME) {
            PsiElement parent = etype[0].getParent();
            if(parent instanceof Function){
                Function exec = (Function) parent;
                return new FunctionSearchOptions(exec);
            } else if(parent instanceof Procedure){
                Procedure exec = (Procedure) parent;
                return new ProcedureSearchOptions(exec);
            } else if(parent instanceof FunctionSpec){
                FunctionSpec exec = (FunctionSpec) parent;
                return new FunctionSearchOptions(exec);
            } else if(parent instanceof ProcedureSpec){
                ProcedureSpec exec = (ProcedureSpec) parent;
                return new ProcedureSearchOptions(exec);
            }
        } else if (ietype == PlSqlElementTypes.VAR_REF) {
            // todo --
        } else if (ietype == PlSqlElementTypes.TYPE_NAME_REF) {
            // todo --
        } else if (ietype == PlSqlElementTypes.CALLABLE_NAME_REF) {
            // todo --
        } else if (ietype == PlSqlElementTypes.SEQUENCE_EXPR) {
            // todo --
        } else if (ietype == PlSqlElementTypes.COLUMN_SPEC) {
            // todo --
        } else if (ietype == PlSqlElementTypes.PLSQL_VAR_REF) {
            // todo --
        } else if (ietype == PlSqlElementTypes.TABLE_DEF) {
            return new TableSearchOptions((TableDefinition) etype[0]);
        } else if (ietype == PlSqlElementTypes.CREATE_VIEW) {
            return new ViewSearchOptions((CreateView) etype[0]);
        }

        return null;
    }

}
