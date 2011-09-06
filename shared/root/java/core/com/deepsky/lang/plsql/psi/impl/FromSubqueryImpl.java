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

package com.deepsky.lang.plsql.psi.impl;

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.struct.TableDescriptorForSubquery;
import com.deepsky.lang.plsql.struct.TableDescriptorLegacy;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class FromSubqueryImpl extends GenericTableBase implements FromSubquery {

    static final Logger log = Logger.getInstance("#FromSubqueryImpl");

    public FromSubqueryImpl(ASTNode astNode) {
        super(astNode);
    }

/*
    @NotNull
    public SelectStatement getSelectStatement() {
        final ASTNode node = getNode().findChildByType(PlSqlElementTypes.SUBQUERY);
        if (node != null) {
            final ASTNode select = node.findChildByType(PlSqlElementTypes.SUBQUERY_SELECTS);
            if (select != null) {
                return (SelectStatement) select.getPsi();
            }
        }
        throw new SyntaxTreeCorruptedException();
    }
*/

    @NotNull
    public Subquery getSubquery() {
        final ASTNode node = getNode().findChildByType(PlSqlElementTypes.SUBQUERY);
        if (node != null) {
            return (Subquery) node.getPsi();
        }
        throw new SyntaxTreeCorruptedException();
    }

    public String getQuickNavigateInfo() {
        return "[Subquery]";
    }

    public String getAlias() {
        final ASTNode node = getNode().findChildByType(PlSqlElementTypes.ALIAS_NAME);
        if (node != null) {
            String text = node.getText();
            String[] splitted = text.split(" ");
            if (splitted.length > 1) {
                return splitted[1];
            } else {
                return splitted[0];
            }
        }

        return null;
    }


    protected TableDescriptorLegacy describeInternal() {
        List<String> columns = new ArrayList<String>();
        List<Type> types = new ArrayList<Type>();

        SelectStatement stmt = getSubquery().getSelectStatement();
        if (stmt != null) {
            for (SelectFieldCommon f : stmt.getSelectFieldList()) {
                if (f instanceof SelectFieldExpr) {
                    SelectFieldExpr s = (SelectFieldExpr) f;
                    String aliasName = s.getAlias();
                    Expression expr = s.getExpression();

                    if (aliasName != null) {
                        columns.add(aliasName);
                    } else {
                        // special case - alias not specified!
                        String columnRef = expr.getText();
                        if (isColumnNameCorrect(columnRef)) {
                            columns.add(columnRef);
                        } else {
                            // name is not plain, needs some special handling ...
                            // 1. name may be composed of several parts: "tab1.name"
                            if (expr instanceof CompositeName) {
                                NameFragmentRef[] names = ((CompositeName) expr).getNamePieces();
                                columns.add(names[names.length - 1].getText());
                            } else {
                                // 2. it looks this is not a valid name (might be a numeric/string literal, arithmetic expression)
                                // ORA-00904: : invalid identifier
                                int i = 0;
                                continue;
                            }
                        }
                    }
                    Type t = null;
                    try {
                        t = expr.getExpressionType();
                    } catch (ValidationException e) {
                        t = TypeFactory.createTypeById(Type.ANYDATA);
                    }
                    types.add(t);

                } else if (f instanceof SelectFieldIdentAsterisk) {
                    SelectFieldIdentAsterisk a = (SelectFieldIdentAsterisk) f;
                    String alias = a.getTableRef();
                    for (GenericTable t1 : stmt.getFromClause().getTableList()) {
                        if (alias == null || (t1.getAlias() != null && t1.getAlias().equalsIgnoreCase(alias))) {
                            TableDescriptorLegacy tdesc = t1.describe(); ///SupportStuff.describeTable(t1);
                            if (tdesc == null) {
                                // todo - cache is not fresh??
                                break;
                            }
                            for (String s : tdesc.getColumnNames()) {
                                columns.add(s);
                                types.add(tdesc.getColumnType(s));
                            }

                            break;
                        }
                    }
                } else {
                    // TODO not supported !
                }
            }
            TableDescriptorLegacy tdesc = new TableDescriptorForSubquery(
                    "",
                    getAlias(),
                    columns.toArray(new String[columns.size()]),
                    types.toArray(new Type[types.size()])
            );

            return tdesc;
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }

/*
    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitSubquery(this);
        } else {
            super.accept(visitor);
        }
    }
*/

}
