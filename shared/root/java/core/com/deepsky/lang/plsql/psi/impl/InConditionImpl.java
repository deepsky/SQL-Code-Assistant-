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

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.InCondition;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class InConditionImpl extends ConditionBase implements InCondition {

    public InConditionImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public Type getExpressionType() {
        return TypeFactory.createTypeById(Type.BOOLEAN);
    }

    private static TokenSet inCond = TokenSet.create(
            PlSqlElementTypes.CAST_FUNC, PlSqlElementTypes.ARITHMETIC_EXPR,
            PlSqlElementTypes.FUNCTION_CALL, PlSqlElementTypes.COUNT_FUNC,
            PlSqlElementTypes.TRIM_FUNC,
            PlSqlElementTypes.SYSTIMESTAMP_CONST,
            PlSqlElementTypes.SYSDATE_CONST, PlSqlElementTypes.DBTIMEZONE,
            PlSqlElementTypes.ROWCOUNT_EXRESSION,
            PlSqlElementTypes.EXTRACT_DATE_FUNC,
            PlSqlElementTypes.ROWNUM,
            PlSqlElementTypes.STRING_LITERAL, PlSqlElementTypes.NUMERIC_LITERAL,
            PlSqlElementTypes.BOOLEAN_LITERAL,
            PlSqlElementTypes.VAR_REF,
            PlSqlElementTypes.SEQUENCE_EXPR,
            PlSqlElementTypes.EXPR_LIST,
            PlSqlElementTypes.NULL_STATEMENT,
            PlSqlElementTypes.SUBQUERY,
            PlSqlElementTypes.PARENTHESIZED_EXPR,
            PlSqlTokenTypes.KEYWORD_IN,
            PlSqlTokenTypes.KEYWORD_NOT
    );

    public PsiElement getLeftPart() {
        ASTNode[] nodes = getNode().getChildren(inCond);
        if (nodes.length < 3) {
            throw new SyntaxTreeCorruptedException();
        }
        return nodes[0].getPsi();
    }

    public PsiElement getRightPart() {
        ASTNode[] nodes = getNode().getChildren(inCond);
        if (nodes.length < 3) {
            throw new SyntaxTreeCorruptedException();
        }

        if (nodes[1].getElementType() == PlSqlTokenTypes.KEYWORD_NOT && nodes[2].getElementType() == PlSqlTokenTypes.KEYWORD_IN) {
            return nodes[3].getPsi();
        } else if (nodes[1].getElementType() == PlSqlTokenTypes.KEYWORD_IN) {
            return nodes[2].getPsi();
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }

    public boolean notIn() {
        ASTNode[] nodes = getNode().getChildren(inCond);
        if (nodes.length < 3) {
            throw new SyntaxTreeCorruptedException();
        }

        if (nodes[1].getElementType() == PlSqlTokenTypes.KEYWORD_NOT && nodes[2].getElementType() == PlSqlTokenTypes.KEYWORD_IN) {
            return true;
        } else if (nodes[1].getElementType() == PlSqlTokenTypes.KEYWORD_IN) {
            return false;
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }
}
