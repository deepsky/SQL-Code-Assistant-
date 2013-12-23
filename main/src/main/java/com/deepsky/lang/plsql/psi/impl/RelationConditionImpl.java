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

import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.Expression;
import com.deepsky.lang.plsql.psi.RelationCondition;
import com.deepsky.lang.plsql.resolver.ResolveFacade;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.validation.TypeCastException;
import com.deepsky.lang.validation.TypeValidationHelper;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class RelationConditionImpl extends ConditionBase implements RelationCondition {

    public RelationConditionImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public Type getExpressionType() {
        ASTNode[] node = getNode().getChildren(PlSqlElementTypes.EXPR_TYPES);
        if (node == null || node.length != 2) {
            throw new SyntaxTreeCorruptedException();
        } else {
            ASTNode op = getNode().findChildByType(PlSqlElementTypes.RELATION_OP);
            if (op != null) {
                ASTNode child = op.getFirstChildNode();
                int _op = type2op(child.getElementType());

                Type l = ((Expression) node[0].getPsi()).getExpressionType();
                Type r = ((Expression) node[1].getPsi()).getExpressionType();
                Type result = evaluate(l, r, _op);
                if (result == null) {
                    int hh = 0;
                }
                return result;
            }
        }

        throw new ValidationException("Cannot resolve type", this);
    }


    private Type evaluate(Type l, Type r, int opType) {
        int resultType = Type.UNKNOWN;
        try {
            ResolveFacade resolver = ((PlSqlFile)getContainingFile()).getResolver();
            resultType = new TypeValidationHelper(resolver).evaluate(l, r, opType);
            //resultType = TypeValidationHelper.evaluate(this, l, r, opType);
        } catch (Throwable e) {
        }

        if (resultType == Type.BOOLEAN) {
            return TypeFactory.createTypeById(resultType);
        } else {
            throw new TypeCastException("Invalid relational operator");
        }
    }

    private int type2op(IElementType itype) {
        if (itype == PlSqlTokenTypes.EQ) {
            return PLSqlTokenTypes.EQ;
        } else if (itype == PlSqlTokenTypes.NOT_EQ) {
            return PLSqlTokenTypes.NOT_EQ;
        } else if (itype == PlSqlTokenTypes.LT) {
            return PLSqlTokenTypes.LT;
        } else if (itype == PlSqlTokenTypes.GT) {
            return PLSqlTokenTypes.GT;
        } else if (itype == PlSqlTokenTypes.LE) {
            return PLSqlTokenTypes.LE;
        } else if (itype == PlSqlTokenTypes.GE) {
            return PLSqlTokenTypes.GE;
        }
        return -1;
    }

    public Expression getLeftExpr() {
        ASTNode[] node = getNode().getChildren(PlSqlElementTypes.EXPR_TYPES);
        return (Expression) node[0].getPsi();
    }

    public Expression getRightExpr() {
        ASTNode[] node = getNode().getChildren(PlSqlElementTypes.EXPR_TYPES);
        return (Expression) node[1].getPsi();
    }

    public String getRelationOp() {
        ASTNode op = getNode().findChildByType(PlSqlElementTypes.RELATION_OP);
        return op.getText();
    }
}
