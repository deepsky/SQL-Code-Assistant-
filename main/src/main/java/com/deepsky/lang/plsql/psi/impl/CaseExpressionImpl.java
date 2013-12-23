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

import com.deepsky.lang.plsql.psi.CaseExpression;
import com.deepsky.lang.plsql.psi.Expression;
import com.deepsky.lang.plsql.psi.utils.ASTNodeIterator;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.validation.TypeValidationHelper;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CaseExpressionImpl extends PlSqlElementBase implements CaseExpression {
    int case_type;

    public CaseExpressionImpl(ASTNode astNode, int case_type) {
        super(astNode);
        this.case_type = case_type;
    }

    @NotNull
    public Type getExpressionType() {
/*
    ("case"! (
        // searched_case_expression
        ( "when" plsql_condition t:"then" plsql_expression )+
        // simple_case_expression
        | smpl:plsql_expression ("when" plsql_expression "then" plsql_expression)+
     )
    ( "else" plsql_expression ) ?
    "end"!)
*/
        ASTNode child = getNode().getFirstChildNode();
        ASTNodeIterator iterator = new ASTNodeIterator(child);

        List<Integer> types = new ArrayList<Integer>();
        if (case_type == CaseExpression.SEARCHED) {
            // ( "when" plsql_condition t:"then" plsql_expression )+
            ASTNode _case = iterator.next();
            while (iterator.hasNext() && iterator.peek().getText().equalsIgnoreCase("when")) {
                ASTNode when = iterator.next();
                ASTNode when_expr = iterator.next();
                ASTNode then = iterator.next();
                ASTNode then_expr = iterator.next();
                Type type = ((Expression) then_expr.getPsi()).getExpressionType();
                types.add(type.typeId());
            }

            if (iterator.hasNext() && iterator.peek().getText().equalsIgnoreCase("else")) {
                ASTNode _else = iterator.next();
                ASTNode else_expr = iterator.next();
                Type type = ((Expression) else_expr.getPsi()).getExpressionType();
                types.add(type.typeId());
            }

            ASTNode end = iterator.next();
        } else {
            // case_type == CaseExpression.SIMPLE
            // smpl:plsql_expression ("when" plsql_expression "then" plsql_expression)+
            ASTNode _case = iterator.next();
            ASTNode expr = iterator.next();
            while (iterator.hasNext() && iterator.peek().getText().equalsIgnoreCase("when")) {
                ASTNode when = iterator.next();
                ASTNode when_expr = iterator.next();
                ASTNode then = iterator.next();
                ASTNode then_expr = iterator.next();
                Type type = ((Expression) then_expr.getPsi()).getExpressionType();
                types.add(type.typeId());
            }

            if (iterator.hasNext() && iterator.peek().getText().equalsIgnoreCase("else")) {
                ASTNode _else = iterator.next();
                ASTNode else_expr = iterator.next();
                Type type = ((Expression) else_expr.getPsi()).getExpressionType();
                types.add(type.typeId());
            }

            ASTNode end = iterator.next();
        }
        // validate types
        if (!TypeValidationHelper.typesEquivalented(types)) {
            throw new ValidationException("Argument types not equvalented", this);
        }

        return TypeFactory.createTypeById(types.get(0));
    }

    public int getCaseType() {
        return case_type;
    }

    public Expression getLeadExpression() /// it is actual for SIMPLE type only
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public WhenThenPair[] getWhenThenPairs() {
        return new WhenThenPair[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Expression getElseExpression() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
