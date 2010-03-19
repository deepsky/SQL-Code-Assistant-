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

import com.deepsky.lang.plsql.psi.BetweenCondition;
import com.deepsky.lang.plsql.psi.Expression;
import com.deepsky.lang.plsql.psi.Visitor;
import com.deepsky.lang.plsql.psi.utils.ASTNodeIterator;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.validation.ValidationException;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class BetweenConditionImpl extends ConditionBase implements BetweenCondition {

    public BetweenConditionImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public Type getExpressionType() {
        return TypeFactory.createTypeById(Type.BOOLEAN);
    }

    public Expression getLead() {
        ASTNode[] rr = getNode().getChildren(PlSqlElementTypes.EXPR_TYPES);
        if(rr.length == 3){
            return (Expression) rr[0].getPsi();
        }
        return null;
    }

    public Expression getLeft() {
        ASTNode[] rr = getNode().getChildren(PlSqlElementTypes.EXPR_TYPES);
        if(rr.length == 3){
            return (Expression) rr[1].getPsi();
        }
        return null;
    }

    public Expression getRight() {
        ASTNode[] rr = getNode().getChildren(PlSqlElementTypes.EXPR_TYPES);
        if(rr.length == 3){
            return (Expression) rr[2].getPsi();    
        }
        return null;
    }

    public boolean isNotBetween() {
        // todo -- not supported at the moment
        return false;
    }

   
}