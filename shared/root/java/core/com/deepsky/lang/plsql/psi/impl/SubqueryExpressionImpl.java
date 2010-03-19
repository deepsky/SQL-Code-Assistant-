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

import com.deepsky.lang.plsql.psi.SubqueryExpression;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.deepsky.lang.plsql.psi.SelectFieldCommon;
import com.deepsky.lang.plsql.psi.SelectFieldExpr;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class SubqueryExpressionImpl extends PlSqlElementBase implements SubqueryExpression {

    public SubqueryExpressionImpl(ASTNode astNode) {
        super(astNode);
    }

    public SelectStatement getSelectStatement() {
        final ASTNode node = getNode().findChildByType(PlSqlElementTypes.SUBQUERY);
        if(node != null){
            final ASTNode select = node.findChildByType(TokenSet.create(
                PlSqlElementTypes.SELECT_EXPRESSION, PlSqlElementTypes.SUBQUERY
            )); //PlSqlElementTypes.SELECT_COMMAND);

//            final ASTNode select = node.findChildByType(PlSqlElementTypes.SELECT_COMMAND);
            if(select != null){
                return (SelectStatement) select.getPsi();
            }
        }
        return null;
    }

    @NotNull
    public Type getExpressionType() {
        SelectFieldCommon[] fields = getSelectStatement().getSelectFieldList();

        if(fields.length != 1){
            throw new ValidationException("Error: expected only one field in a subquery expression.", getNode());
        } else if(!(fields[0] instanceof SelectFieldExpr)){
            throw new ValidationException("Error: field is not correct in the subquery expression.", getNode());
        }

        return ((SelectFieldExpr)fields[0]).getExpression().getExpressionType();
    }
}
