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

import com.deepsky.lang.plsql.psi.ArithmeticExpression;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.impl.expr_eval.ArithmeticExprTypeEvaluator;
import com.deepsky.lang.plsql.psi.impl.expr_eval.Expr;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.validation.ValidationException;
import com.deepsky.lang.validation.TypeCastException;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArithmeticExpressionImpl extends PlSqlElementBase implements ArithmeticExpression {

    static final Logger log = Logger.getInstance("#ArithmeticExpressionImpl");

    public ArithmeticExpressionImpl(ASTNode astNode) {
        super(astNode);
    }

    Type _cachedType = null;
    String _name = "";

    @NotNull
    public Type getExpressionType() {
//        String tt = this.getText();
//        if (tt != null && tt.length() > 40) {
//            tt = tt.substring(0, 40) + " ...";
//        }
//        log.info("[" + tt + "]");

        if(_cachedType != null){
            if(!getText().equals(_name)){
                // clean the cache
                _cachedType = null;
                _name = "";
            } else {
                return _cachedType;
            }
        }

        ASTNode node = this.getNode().getFirstChildNode();
        try {
        _cachedType = new ArithmeticExprTypeEvaluator(new Expr(node)).calc();
        } catch(TypeCastException e){
            throw new ValidationException(e.getMessage(), this);
        }
        _name = getText();
        return _cachedType;
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitArithmeticExpression(this);
        } else {
            super.accept(visitor);
        }
    }

    public List<Object> getExpressionItemList() {
        // todo --
        return null;
    }
}
