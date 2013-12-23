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

import com.deepsky.database.ora.DbUrl;
import com.deepsky.findUsages.options.*;
import com.deepsky.findUsages.workarounds.UsageViewManagerImpl;
import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.ResolveProvider;
import com.deepsky.lang.common.SysFuncPsiFile;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.CreateSequence;
import com.deepsky.lang.plsql.psi.ddl.CreateView;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.psi.ddl.VColumnDefinition;
import com.deepsky.lang.plsql.psi.names.ColumnNameDDL;
import com.deepsky.lang.plsql.psi.names.ColumnNameRef;
import com.deepsky.lang.plsql.psi.names.ParameterName;
import com.deepsky.lang.plsql.psi.names.VariableName;
import com.deepsky.lang.plsql.psi.ref.DDLTable;
import com.deepsky.lang.plsql.psi.ref.DDLView;
import com.deepsky.lang.plsql.psi.ref.TableRef;
import com.deepsky.lang.plsql.psi.resolve.ASTNodeHandler;
import com.deepsky.lang.plsql.psi.resolve.ASTTreeProcessor;
import com.intellij.find.findUsages.FindUsagesOptions;
import com.intellij.ide.DataManager;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Key;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.usages.*;
import org.jetbrains.annotations.NotNull;

public class SqlFindUsagesHelper {

    public static final Key<SqlSearchOptions> SQL_SEARCH_OPTIONS = Key.create("SQL_SEARCH_OPTIONS");

    public static void runFindUsages(Project project, PsiElement _psi) {
        runFindUsages(project, _psi, false);
    }

    public static void runFindUsages(Project project, PsiElement _psi, boolean databaseDefaultScope) {
        AbstractSqlSearchOptions searchOptions = (AbstractSqlSearchOptions) applyFindUsagesTo(_psi);

        if (searchOptions != null) {
            DbUrl searchScope = ((PlSqlFile)_psi.getContainingFile()).getDbUrl();
            searchOptions.setTargetSchema(searchScope);
            _psi = searchOptions.getTargetElement();
            // TODO -- DataManager.getDataContext() is deprecated
            FindUsagesOptions options = new FindUsagesOptions(project, DataManager.getInstance().getDataContext());

            SqlFindUsagesDialog dialog = new SqlFindUsagesDialog(
                    project, options, true, false, false, searchOptions
                );

            dialog.show();
            if (dialog.isOK()) {
                // TODO -- DataManager.getDataContext() is deprecated
                options = new FindUsagesOptions(project, DataManager.getInstance().getDataContext());
                SqlUsageTarget usageTarget = new SqlUsageTarget();
                usageTarget.setPresentableText(searchOptions.getTargetPresentableText());
                UsageTarget[] utargets = new UsageTarget[]{usageTarget};

//                SqlUsageViewPresentation uvpresentation = new SqlUsageViewPresentation();
                UsageViewPresentation uvpresentation = new UsageViewPresentation();
                uvpresentation.setCodeUsages(true); //false); //true);
                uvpresentation.setCodeUsagesString("Found Usages");
                uvpresentation.setScopeText(((PlSqlFile) _psi.getContainingFile()).getDbUrl().getAlias());
                uvpresentation.setTabName("Tab Name1");

                uvpresentation.setTargetsNodeText(searchOptions.getTargetNode());
                uvpresentation.setToolwindowTitle("Toolwindow Title1");
                uvpresentation.setUsagesString("Usages");
                uvpresentation.setTabText(searchOptions.getPresentableSearchParameters());
                uvpresentation.setTabName("Tab Name");
                uvpresentation.setOpenInNewTab(dialog.isShowInSeparateWindow());

//                PsiElement[] elemToSearch = searchOptions.getElementsToSearch();

                _psi.putUserData(SQL_SEARCH_OPTIONS, searchOptions);
                final UsageInfoToUsageConverter.TargetElementsDescriptor descriptor =
                        new UsageInfoToUsageConverter.TargetElementsDescriptor(_psi);

                UsageViewManager uvmanager = new UsageViewManagerImpl(project); //UsageViewManager.getInstance(project);
//                UsageViewManager uvmanager = UsageViewManager.getInstance(project);
                uvmanager.searchAndShowUsages(utargets,
                        new SqlSpecificSearchRunner.SearchUsageFactory(project, descriptor, options),
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


    private static TokenSet eligableForSearchUsages =
            TokenSet.create(
                    PlSqlElementTypes.PACKAGE_NAME,
                    PlSqlElementTypes.OBJECT_NAME, // name of Procedure or Function

                    PlSqlElementTypes.VIEW_NAME_DDL,
                    PlSqlElementTypes.COLUMN_NAME_DDL,  // column in the table definition
                    PlSqlElementTypes.V_COLUMN_DEF,     // column in the view definition
                    PlSqlElementTypes.TABLE_NAME_DDL,   // name of table in the table definition 

                    PlSqlElementTypes.TYPE_NAME,
                    PlSqlElementTypes.VARIABLE_NAME,
                    PlSqlElementTypes.PARAMETER_NAME,
                    PlSqlElementTypes.SEQUENCE_NAME,

                    PlSqlElementTypes.TABLE_REF,        // table reference
                    PlSqlElementTypes.TYPE_NAME_REF,
                    PlSqlElementTypes.COLUMN_NAME_REF,  // column reference
                    PlSqlElementTypes.CALLABLE_NAME_REF, //
                    PlSqlElementTypes.VAR_REF,          // name of variable or table column
                    PlSqlElementTypes.PLSQL_VAR_REF,
                    PlSqlElementTypes.NAME_FRAGMENT,
                    PlSqlElementTypes.C_RECORD_ITEM_REF,    // "edesc" in "l_ta_exc_rc(l_idx).edesc"

                    PlSqlElementTypes.SEQUENCE_EXPR,
                    PlSqlElementTypes.RECORD_ITEM,
                    PlSqlElementTypes.COLUMN_SPEC,

                    PlSqlElementTypes.FUNCTION_SPEC,
                    PlSqlElementTypes.FUNCTION_BODY,
                    PlSqlElementTypes.PROCEDURE_SPEC,
                    PlSqlElementTypes.PROCEDURE_BODY,
                    PlSqlElementTypes.PACKAGE_SPEC,
                    PlSqlElementTypes.PACKAGE_BODY,
                    PlSqlElementTypes.VARIABLE_DECLARATION,

                    PlSqlElementTypes.RECORD_TYPE_DECL,
                    PlSqlElementTypes.VARRAY_COLLECTION,
                    PlSqlElementTypes.OBJECT_TYPE_DEF,
                    PlSqlElementTypes.TABLE_COLLECTION,

                    PlSqlElementTypes.CREATE_SEQUENCE,
                    PlSqlElementTypes.CREATE_VIEW,
                    PlSqlElementTypes.TABLE_DEF,
                    PlSqlElementTypes.COLUMN_DEF,

                    // elements to break claiming for elements elegable for search usages
                    PlSqlElementTypes.INSERT_COMMAND,
                    PlSqlElementTypes.DELETE_COMMAND,
                    PlSqlElementTypes.UPDATE_COMMAND,
                    PlSqlElementTypes.MERGE_COMMAND,
                    PlSqlElementTypes.SELECT_EXPRESSION,
                    PlSqlElementTypes.SELECT_EXPRESSION_UNION
            );


    private static PsiElement findAncestorFor(PsiElement element) {
        if (element == null) {
            return null;
        }

        final PsiElement[] etype = new PsiElement[]{null};
        ASTTreeProcessor treeProcessor = new ASTTreeProcessor();
        treeProcessor.add(new ASTNodeHandler() {
            @NotNull
            public TokenSet getTokenSet() {
                return eligableForSearchUsages;
            }

            public boolean handleNode(@NotNull ASTNode node) {
                etype[0] = node.getPsi();
                return true;
            }
        });

        treeProcessor.process(element.getNode(), true /* break on first hit*/);
        return etype[0];
    }

    public static boolean canApplyFindUsagesTo(PsiElement element) {
        return findAncestorFor(element) != null;
    }

    public static SqlSearchOptions applyFindUsagesTo(PsiElement element) {

        PsiElement[] etype = new PsiElement[]{null};
        etype[0] = findAncestorFor(element);
        if (etype[0] == null || etype[0].getNode() == null) {
            return null;
        }

        final IElementType ietype = etype[0].getNode().getElementType();

        if (ietype == PlSqlElementTypes.PACKAGE_NAME) {
            PsiElement parent = etype[0].getParent();
            return createSearchOptionsFor(parent);

        } else if (ietype == PlSqlElementTypes.NAME_FRAGMENT) {
            PsiElement referenced = ((NameFragmentRef) etype[0]).resolve();
            return createSearchOptionsFor(referenced);

        } else if (ietype == PlSqlElementTypes.C_RECORD_ITEM_REF) {
            PsiElement referenced = ((CRecordItemRef) etype[0]).resolve();
            return createSearchOptionsFor(referenced);

        } else if (ietype == PlSqlElementTypes.TABLE_REF) {
            PsiElement psi = ((TableRef) etype[0]).resolve();
            if (psi instanceof CreateView) {
                return new ViewSearchOptions((CreateView) psi);
            } else if (psi instanceof TableDefinition) {
                return new TableSearchOptions((TableDefinition) psi);
            }
        } else if (ietype == PlSqlElementTypes.VIEW_NAME_DDL) {
            CreateView view = ((DDLView) etype[0]).getView();
            return new ViewSearchOptions(view);

        } else if (ietype == PlSqlElementTypes.TABLE_NAME_DDL) {
            TableDefinition psi = ((DDLTable) etype[0]).getTableDef();
            return new TableSearchOptions(psi);

        } else if (ietype == PlSqlElementTypes.COLUMN_NAME_DDL) {
            ColumnDefinition columnDef = ((ColumnNameDDL) etype[0]).getColumnDefinition();
            // Make sure that syntax is correct
            if(columnDef != null && columnDef.getTableDefinition() != null)
                return new TableColumnSearchOptions(columnDef);

        } else if (ietype == PlSqlElementTypes.V_COLUMN_DEF) {
            return new ViewColumnSearchOptions((VColumnDefinition) etype[0]);

        } else if (ietype == PlSqlElementTypes.COLUMN_NAME_REF) {
            PsiElement referenced = ((ColumnNameRef) etype[0]).resolve();
            return createSearchOptionsFor(referenced);

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
            if (parent instanceof Function) {
                PsiElement spec = ((Function) parent).getSpecification();
                if (spec == null) {
                    // package scope object, search for body
                } else {
                    // specification for body found - search for spec
                    parent = spec;
                }

            } else if (parent instanceof Procedure) {
                PsiElement spec = ((Procedure) parent).getSpecification();
                if (spec == null) {
                    // package scope object, search for body
                } else {
                    // specification for body found - search for spec
                    parent = spec;
                }
            }

            return createSearchOptionsFor(parent);

        } else if (ietype == PlSqlElementTypes.VAR_REF) {
            ObjectReference varRef = (ObjectReference) etype[0];
            PsiElement target = ((ResolveProvider) varRef.getContainingFile()).getResolver().resolveObjectReference(varRef);
            return createSearchOptionsFor(target);

        } else if (ietype == PlSqlElementTypes.TYPE_NAME) {
            PsiElement typeDecl = etype[0].getParent();
            return createSearchOptionsFor(typeDecl);

        } else if (ietype == PlSqlElementTypes.TYPE_NAME_REF) {
            // todo -- implement me
        } else if (ietype == PlSqlElementTypes.RECORD_ITEM) {
            RecordTypeItem ri = (RecordTypeItem) etype[0];
            return new RecordItemSearchOptions(ri);

        } else if (ietype == PlSqlElementTypes.CALLABLE_NAME_REF) {
            Callable ref = ((CallableCompositeName) etype[0]).getCallable();
            PsiElement target = ((ResolveProvider) ref.getContainingFile()).getResolver().resolveCallable(ref);
            return createSearchOptionsFor(target);

        } else if (ietype == PlSqlElementTypes.SEQUENCE_EXPR) {
            PsiElement resolved = ((SequenceExpr) etype[0]).getSequence().resolve();
            return createSearchOptionsFor(resolved);

        } else if (ietype == PlSqlElementTypes.COLUMN_SPEC) {
            // todo -- implement me

        } else if (ietype == PlSqlElementTypes.PLSQL_VAR_REF) {
            ObjectReference varRef = (ObjectReference) etype[0];
            PsiElement target = ((ResolveProvider) varRef.getContainingFile()).getResolver().resolveObjectReference(varRef);
            return createSearchOptionsFor(target);

        } else if (ietype == PlSqlElementTypes.TABLE_DEF) {
            return new TableSearchOptions((TableDefinition) etype[0]);

        } else if (ietype == PlSqlElementTypes.COLUMN_DEF) {
            return new TableColumnSearchOptions((ColumnDefinition) etype[0]);

        } else if (ietype == PlSqlElementTypes.CREATE_VIEW) {
            return new ViewSearchOptions((CreateView) etype[0]);

        } else if (ietype == PlSqlElementTypes.FUNCTION_SPEC) {
            return createSearchOptionsFor(etype[0]);

        } else if (ietype == PlSqlElementTypes.FUNCTION_BODY) {
            PsiElement target = etype[0];
            PsiElement spec = ((Function) etype[0]).getSpecification();
            if (spec == null) {
                // package scope object, search for body
            } else {
                // specification for body found - search for spec
                target = spec;
            }
            return createSearchOptionsFor(target);

        } else if (ietype == PlSqlElementTypes.PROCEDURE_SPEC) {
            return createSearchOptionsFor(etype[0]);

        } else if (ietype == PlSqlElementTypes.PROCEDURE_BODY) {
            PsiElement target = etype[0];
            PsiElement spec = ((Procedure) etype[0]).getSpecification();
            if (spec == null) {
                // package scope object, search for body
            } else {
                // specification for body found - search for spec
                target = spec;
            }
            return createSearchOptionsFor(target);
        } else if (ietype == PlSqlElementTypes.PACKAGE_SPEC) {
            return createSearchOptionsFor(etype[0]);
        } else if (ietype == PlSqlElementTypes.PACKAGE_BODY) {
            return createSearchOptionsFor(etype[0]);
        } else if (ietype == PlSqlElementTypes.VARIABLE_DECLARATION) {
            return createSearchOptionsFor(etype[0]);
        } else if (ietype == PlSqlElementTypes.RECORD_TYPE_DECL) {
            return createSearchOptionsFor(etype[0]);
        } else if (ietype == PlSqlElementTypes.VARRAY_COLLECTION) {
            return createSearchOptionsFor(etype[0]);
        } else if (ietype == PlSqlElementTypes.TABLE_COLLECTION) {
            return createSearchOptionsFor(etype[0]);
        } else if (ietype == PlSqlElementTypes.OBJECT_TYPE_DEF) {
            return createSearchOptionsFor(etype[0]);
        } else if (ietype == PlSqlElementTypes.CREATE_SEQUENCE) {
            return createSearchOptionsFor(etype[0]);
        } else if (ietype == PlSqlElementTypes.SEQUENCE_NAME) {
            return createSearchOptionsFor(etype[0].getParent());
        }

        Messages.showInfoMessage("Could not search for usages, selected symbol should be a valid reference", "Find Usages");
        return null;
    }

    private static SqlSearchOptions createSearchOptionsFor(PsiElement element) {
        if (element == null) {
            //
        } else if (element instanceof VColumnDefinition) {
            VColumnDefinition vcolumnDef = (VColumnDefinition) element;
            return new ViewColumnSearchOptions(vcolumnDef);

        } else if (element instanceof ColumnDefinition) {
            ColumnDefinition columnDef = (ColumnDefinition) element;
            return new TableColumnSearchOptions(columnDef);

        } else if (element instanceof VariableDecl) {
            VariableDecl var = (VariableDecl) element;
            return new VariableSearchOptions(var);

        } else if (element instanceof Argument) {
            Argument argument = (Argument) element;
            return new ArgumentSearchOptions(argument);

        } else if (element instanceof TableCollectionDecl) {
            return new TableCollSearchOptions((TableCollectionDecl) element);

        } else if (element instanceof VarrayCollectionDecl) {
            return new VarraySearchOptions((VarrayCollectionDecl) element);

        } else if (element instanceof RecordTypeDecl) {
            return new RecordTypeSearchOptions((RecordTypeDecl) element);

        } else if (element instanceof ObjectTypeDecl) {
            return new ObjectTypeSearchOptions((ObjectTypeDecl) element);

        } else if (element instanceof RefCursorDecl) {
            // todo -- implement me

        } else if (element instanceof Function) {
            return new FunctionSearchOptions((Function) element);

        } else if (element instanceof Procedure) {
            return new ProcedureSearchOptions((Procedure) element);

        } else if (element instanceof FunctionSpec) {
            return new FunctionSearchOptions((FunctionSpec) element);

        } else if (element instanceof ProcedureSpec) {
            return new ProcedureSearchOptions((ProcedureSpec) element);

        } else if (element instanceof RecordTypeItem) {
            // FUNCTION flow_status_tp_to_r (a_flwst_tp ota_flow_status_payload_tp) ...
            // ... a_flwst_tp.job_type
            RecordTypeItem ri = (RecordTypeItem) element;
            // Find USages: Field name 'job_type' of Record Type 'ota_flow_status_payload_tp'
            // or Argument a_flwst_tp of type 'ota_flow_status_payload_tp'
            return new RecordItemSearchOptions(ri);
        } else if (element instanceof PackageSpec) {
            return new PackageSearchOptions((PackageSpec) element);
        } else if (element instanceof PackageBody) {
            PackageSpec spec = ((PackageBody) element).getPackageSpecification();
            if(spec != null){
                return new PackageSearchOptions(spec);
            } else {
                Messages.showInfoMessage("Could not find Package Specification", "Find Usages");
            }
        } else if (element instanceof CreateSequence) {
            return new SequenceSearchOptions((CreateSequence) element);

        } else if (element instanceof CreateView) {
            return new ViewSearchOptions((CreateView) element);
        } else if (element instanceof SysFuncPsiFile) {
            // todo -- implement me
            Messages.showInfoMessage("Find Usages of system functions not implemented yet", "Find Usages");
        } else {
            Messages.showInfoMessage("Could not search for usages, selected symbol should be a valid reference", "Find Usages");
        }

        return null;
    }


}
