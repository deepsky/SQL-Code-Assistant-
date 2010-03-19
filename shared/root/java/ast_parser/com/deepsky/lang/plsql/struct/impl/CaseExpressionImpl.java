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

package com.deepsky.lang.plsql.struct.impl;

import com.deepsky.lang.plsql.psi.CaseExpression;
import com.deepsky.lang.plsql.psi.Expression;
import com.deepsky.lang.plsql.psi.Visitor;
import com.deepsky.lang.plsql.struct.Type;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.ArrayList;

public class CaseExpressionImpl extends PsiElementDumb implements CaseExpression  {

    int type;
    private Expression lead;
    List<WhenThenPair> pairs = new ArrayList<WhenThenPair>();
    Expression _else;

    public CaseExpressionImpl(int type){
        this.type = type;
    }

    public int getCaseType() {
        return type;
    }

    // it is actual for SIMPLE type only
    public Expression getLeadExpression() {
        return lead;
    }

    public void setLeadExpression(Expression lead) {
        this.lead = lead;
    }

    public WhenThenPair[] getWhenThenPairs() {
        return pairs.toArray(new WhenThenPair[0]);
    }

    public void setElseExpression(Expression _else) {
        this._else = _else;
    }

    public Expression getElseExpression() {
        return _else;
    }

    @NotNull
    public Type getExpressionType() {
        return null;
    }

    public void process(Visitor proc) {
        proc.accept(this);
    }

    public void addWhenThenPair(Expression when, Expression then) {
        pairs.add(new WhenThenPairImpl(when, then));
    }

    public Expression getLead() {
        return lead;
    }

    class WhenThenPairImpl implements WhenThenPair {

        Expression when;
        Expression then;
        public WhenThenPairImpl(Expression when, Expression then){
            this.when = when;
            this.then = then;
        }

        public Expression getWhen() {
            return when;
        }

        public Expression getThen() {
            return then;
        }
    }
}
