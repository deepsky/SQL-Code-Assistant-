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

package com.deepsky.lang.validation.impl;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.impl.SqlPlusPromptRem;
import com.deepsky.lang.plsql.psi.names.*;
import com.deepsky.lang.plsql.psi.ref.DDLTable;
import com.deepsky.lang.plsql.psi.ref.DDLView;
import com.deepsky.lang.plsql.psi.ref.TableRef;
import com.deepsky.lang.plsql.psi.types.DataType;
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.deepsky.lang.PsiUtil;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

import java.util.List;

public abstract class AbstractHighlightingVisitor extends PlSqlElementVisitor {

    protected abstract void annotate(PsiElement psiElement, IElementType elementType);


    public void visitErrorTokenWrapper(ErrorTokenWrapper node) {
        super.visitErrorTokenWrapper(node);
        annotate(node, PlSqlElementTypes.ERROR_TOKEN_A);
    }


    public void SqlPlusPromptRem(SqlPlusPromptRem promptRem) {
        annotate(promptRem, PlSqlTokenTypes.SL_COMMENT);
    }


    public void visitSqlPlusCommand(SqlPlusCommand command) {
        List<ASTNode> children = PsiUtil.visibleChildren(command.getNode());
        for (ASTNode node : children) {
            annotate(node.getPsi(), command.getNode().getElementType());
        }
    }

    public void visitDatatype(DataType dataType) {
        for (PsiElement typePart : dataType.getTypeName()) {
            annotate(typePart, PlSqlElementTypes.DATATYPE);
        }
    }


    public void visitTypeNameReference(TypeNameReference reference) {
        NameFragmentRef[] fragments = reference.getNamePieces();
        for (int i = 0; i < fragments.length; i++) {
            if (i == fragments.length - 1) {
                annotate(fragments[i], PlSqlElementTypes.TYPE_NAME_REF);
            } else {
                annotate(fragments[i], PlSqlElementTypes.GENERIC_REF);
            }
        }
    }

    public void visitColumnNameDDL(ColumnNameDDL name) {
        annotate(name, name.getNode().getElementType());
    }

    public void visitObjectName(ObjectName name) {
        annotate(name, name.getNode().getElementType());
    }


    private static interface CompositeNameResolveHelper {
        void processNameFragment(NameFragmentRef r, ResolveDescriptor rDesc);
    }

    private static void resolveCompositeName(CompositeName element, CompositeNameResolveHelper helper) {
        int index = 0;
        ResolveDescriptor rDesc = null;
        for (final NameFragmentRef r : element.getNamePieces()) {
            if (index == 0) {
                rDesc = r.resolveLight();
            } else if (rDesc != null) {
                final ResolveDescriptor[] rDescArr = rDesc.resolve(StringUtils.discloseDoubleQuotes(r.getText()));
                rDesc = rDescArr.length == 1 ? rDescArr[0] : null;
            }

            helper.processNameFragment(r, rDesc);
            index++;
        }
    }

    public void visitObjectReference(ObjectReference node) {
        resolveCompositeName(node, new CompositeNameResolveHelper() {
            public void processNameFragment(NameFragmentRef r, ResolveDescriptor rDesc) {
                int type = rDesc != null ? rDesc.getTargetType() : -1;
                switch (type) {
                    case ResolveDescriptor.PACKAGE_SPEC:
                    case ResolveDescriptor.PACKAGE_BODY:
                    case ResolveDescriptor.LOOP_INDEX:
                    case ResolveDescriptor.COLLECTION_METHOD:
                    case ResolveDescriptor.COLLECTION_ELEM_ACCESSOR:
                        annotate(r, PlSqlElementTypes.OBJECT_NAME);
                        break;
                    case ResolveDescriptor.ARGUMENT:
                        annotate(r, PlSqlElementTypes.PARAMETER_REF);
                        break;
                    case ResolveDescriptor.VARIABLE:
                        annotate(r, PlSqlElementTypes.PLSQL_VAR_REF);
                        break;
                    case ResolveDescriptor.TABLE_COLUMN:
                    case ResolveDescriptor.SUBQUERY_FIELD:
                        annotate(r, PlSqlElementTypes.COLUMN_NAME_REF);
                        break;
                    default:
                        annotate(r, PlSqlElementTypes.GENERIC_REF);
                        break;
                }
            }
        });

    }

    public void visitPartitionName(PartitionName name) {
        annotate(name, name.getNode().getElementType());
    }

    public void visitConstraintName(ConstraintName name) {
        annotate(name, name.getNode().getElementType());
    }

    public void visitColumnNameRef(ColumnNameRef name) {
        annotate(name, name.getNode().getElementType());
    }

    public void visitTablespaceName(TablespaceName name) {
        annotate(name, name.getNode().getElementType());
    }

    public void visitSequenceName(SequenceName name) {
        annotate(name, name.getNode().getElementType());
    }

    public void visitIndexName(IndexName name) {
        annotate(name, name.getNode().getElementType());
    }

    public void visitRecordItemName(RecordItemName name) {
        annotate(name, name.getNode().getElementType());
    }

    public void visitNameFragmentRef(NameFragmentRef name) {
        ResolveDescriptor rhlp = name.resolveLight();
        if (rhlp != null) {
            switch (rhlp.getTargetType()) {
                case ResolveDescriptor.PACKAGE_BODY:
                case ResolveDescriptor.PACKAGE_SPEC:
                    annotate(name, PlSqlElementTypes.OBJECT_NAME);
                    return;
            }
        }
        annotate(name, PlSqlElementTypes.GENERIC_REF);
    }

    public void visitAliasName(AliasName aliasName) {
        annotate(aliasName.getAliasIdent(), PlSqlElementTypes.ALIAS_IDENT);
    }

    public void visitDDLTable(DDLTable table) {
        annotate(table, table.getNode().getElementType());
    }

    public void visitDDLView(DDLView view) {
        annotate(view, view.getNode().getElementType());
    }

    public void visitVariableName(VariableName name) {
        annotate(name, name.getNode().getElementType());
    }

    public void visitParameterName(ParameterName name) {
        annotate(name, name.getNode().getElementType());
    }

/*
    public void visitSystemFunctionCall(FunctionCall function) {
        annotate(function.getCompositeName(), PlSqlElementTypes.BUILT_IT_FUNCTION_CALL);
    }
*/
    public void visitCollectionMethodCall(CollectionMethodCall methodCall) {
        final ResolveDescriptor[] _rDesc = new ResolveDescriptor[1];
        int index = 0;
        for(PlSqlElement e: methodCall.getNames()){
            if(index == 0){
                resolveCompositeName(((Callable) e).getCompositeName(), new CompositeNameResolveHelper() {
                    public void processNameFragment(NameFragmentRef r, ResolveDescriptor rDesc) {
                        int type = rDesc != null ? rDesc.getTargetType() : -1;
                        switch (type) {
                            case ResolveDescriptor.PACKAGE_SPEC:
                            case ResolveDescriptor.PACKAGE_BODY:
                                annotate(r, PlSqlElementTypes.OBJECT_NAME);
                                break;
                            case ResolveDescriptor.ARGUMENT:
                                annotate(r, PlSqlElementTypes.PARAMETER_REF);
                                break;
                            case ResolveDescriptor.VARIABLE:
                                annotate(r, PlSqlElementTypes.PLSQL_VAR_REF);
                                break;
                            case ResolveDescriptor.TABLE_COLUMN:
                            case ResolveDescriptor.SUBQUERY_FIELD:
                                annotate(r, PlSqlElementTypes.COLUMN_NAME_REF);
                                break;
                            default:
                                annotate(r, PlSqlElementTypes.GENERIC_REF);
                                break;
                        }
                        _rDesc[0] = rDesc;
                    }
                });
            } else {
                if(_rDesc[0] != null){
                    final ResolveDescriptor[] rDescArr = _rDesc[0].resolve(StringUtils.discloseDoubleQuotes(e.getText()));
                    _rDesc[0] = rDescArr.length == 1 ? rDescArr[0] : null;
                }
            }
            index++;
        }
    }


    public void visitCollectionMethodCall2(CollectionMethodCall2 methodCall2) {
        final ResolveDescriptor[] _rDesc = new ResolveDescriptor[1];
        int index = 0;
        for(PlSqlElement e: methodCall2.getNames()){
            if(index == 0){
                resolveCompositeName(((Callable) e).getCompositeName(), new CompositeNameResolveHelper() {
                    public void processNameFragment(NameFragmentRef r, ResolveDescriptor rDesc) {
                        int type = rDesc != null ? rDesc.getTargetType() : -1;
                        switch (type) {
                            case ResolveDescriptor.PACKAGE_SPEC:
                            case ResolveDescriptor.PACKAGE_BODY:
                                annotate(r, PlSqlElementTypes.OBJECT_NAME);
                                break;
                            case ResolveDescriptor.ARGUMENT:
                                annotate(r, PlSqlElementTypes.PARAMETER_REF);
                                break;
                            case ResolveDescriptor.VARIABLE:
                                annotate(r, PlSqlElementTypes.PLSQL_VAR_REF);
                                break;
                            case ResolveDescriptor.TABLE_COLUMN:
                            case ResolveDescriptor.SUBQUERY_FIELD:
                                annotate(r, PlSqlElementTypes.COLUMN_NAME_REF);
                                break;
                            default:
                                annotate(r, PlSqlElementTypes.GENERIC_REF);
                                break;
                        }
                    }
                });
            } else {
                if(_rDesc[0] != null){
                    final ResolveDescriptor[] rDescArr = _rDesc[0].resolve(StringUtils.discloseDoubleQuotes(e.getText()));
                    _rDesc[0] = rDescArr.length == 1 ? rDescArr[0] : null;
                }
            }
            index++;
        }
    }


    public void visitExecNameRef(ExecNameRef name) {
        ResolveDescriptor rDesc = name.resolveLight();
        if (rDesc == null) {
            annotate(name, PlSqlElementTypes.CALL_NOT_RESOLVED);
            return;
        }

        final int resolvedType = rDesc.getTargetType();
        switch (resolvedType) {
            case ResolveDescriptor.SYSTEM_FUNC:
                annotate(name, PlSqlElementTypes.BUILT_IT_FUNCTION_CALL);
                break;
            case ResolveDescriptor.FUNCTION_BODY:
            case ResolveDescriptor.FUNCTION_SPEC:
                annotate(name, PlSqlElementTypes.UDF_CALL);
                break;
            case ResolveDescriptor.PROCEDURE_BODY:
            case ResolveDescriptor.PROCEDURE_SPEC:
                annotate(name, PlSqlElementTypes.UDP_CALL);
                break;
        }
    }

    public void visitSequenceExpr(SequenceExpr ref) {
        annotate(ref.getSequence(), PlSqlElementTypes.SEQUENCE_REF);
        annotate(ref.getPsiMethod(), PlSqlElementTypes.SEQUENCE_REF);
    }

    public void visitSchemaName(SchemaName name) {
        annotate(name, name.getNode().getElementType());
    }

    final static TokenSet QUOTED_STR = TokenSet.create(PlSqlTokenTypes.QUOTED_STR);

    public void visitStringLiteral(StringLiteral literal) {
        ASTNode[] quoted = literal.getNode().getChildren(QUOTED_STR);
        for(int i=1; i<quoted.length-1; i++){
            if(quoted[i].getTreeNext() != quoted[i+1]){
                // String literal looks not correct
                annotate(literal, literal.getNode().getElementType());
                break;
            }
        }
    }

    public void visitTableRef(TableRef name) {
        ResolveDescriptor rDesc = name.resolveLight();
        if (rDesc == null) {
            annotate(name, PlSqlElementTypes.TABLE_REF_NOT_RESOLVED);
        } else {
            if (rDesc.getTargetType() == ResolveDescriptor.VIEW) {
                annotate(name, PlSqlElementTypes.VIEW_NAME_REF);
            } else if (rDesc.getTargetType() == ResolveDescriptor.TABLE) {
                annotate(name, PlSqlElementTypes.TABLE_NAME_REF);
            }
        }
    }


    public static final TokenSet BOTTOM_NODES = TokenSet.create(
            PlSqlElementTypes.SCHEMA_NAME, PlSqlElementTypes.TABLE_NAME_REF,
            PlSqlElementTypes.SEQUENCE_EXPR, PlSqlElementTypes.EXEC_NAME_REF,
            PlSqlElementTypes.PARAMETER_NAME, PlSqlElementTypes.VARIABLE_NAME,

            PlSqlElementTypes.VAR_REF, PlSqlElementTypes.PLSQL_VAR_REF,

            PlSqlElementTypes.TABLE_NAME_DDL, PlSqlElementTypes.VIEW_NAME_DDL,
            PlSqlElementTypes.ALIAS_NAME, PlSqlElementTypes.TABLE_REF,
            PlSqlElementTypes.RECORD_ITEM_NAME,
            PlSqlElementTypes.SEQUENCE_NAME, PlSqlElementTypes.INDEX_NAME,
            PlSqlElementTypes.TABLESPACE_NAME,
            PlSqlElementTypes.COLUMN_NAME_REF,
            PlSqlElementTypes.CONSTRAINT_NAME,
            PlSqlElementTypes.PARTITION_NAME,

            PlSqlElementTypes.COLLECTION_METHOD_CALL,PlSqlElementTypes.COLLECTION_METHOD_CALL2,

            PlSqlElementTypes.OBJECT_NAME, PlSqlElementTypes.PACKAGE_NAME, PlSqlElementTypes.TRIGGER_NAME,

            PlSqlElementTypes.COLUMN_NAME_DDL,
            PlSqlElementTypes.TYPE_NAME_REF,
            PlSqlElementTypes.TYPE_NAME,
            PlSqlElementTypes.DATATYPE,

            PlSqlElementTypes.SQLPLUS_SET, PlSqlElementTypes.SQLPLUS_SHOW, PlSqlElementTypes.SQLPLUS_DEFINE,
            PlSqlElementTypes.SQLPLUS_VARIABLE, PlSqlElementTypes.SQLPLUS_EXEC, PlSqlElementTypes.SQLPLUS_WHENEVER,
            PlSqlElementTypes.SQLPLUS_PROMPT, PlSqlElementTypes.SQLPLUS_COLUMN, PlSqlElementTypes.SQLPLUS_START,
            PlSqlElementTypes.SQLPLUS_SERVEROUTPUT, PlSqlElementTypes.SQLPLUS_REPFOOTER, PlSqlElementTypes.SQLPLUS_REPHEADER,
            PlSqlElementTypes.SQLPLUS_EXIT, PlSqlElementTypes.SQLPLUS_QUIT, PlSqlElementTypes.SQLPLUS_RUNSCRIPT,
            PlSqlElementTypes.SQLPLUS_SPOOL
    );


}
