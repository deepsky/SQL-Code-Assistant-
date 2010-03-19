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

package com.deepsky.lang.plsql.psi;


import com.deepsky.lang.plsql.ReferencedName;


public interface Visitor {

    void accept(SelectStatement select);
    void accept(WhereCondition where);
    void accept(OrderByClause orderByClause);
    void accept(SelectFieldExpr fieldExpr);
    void accept(SelectFieldAsterisk fieldAsterisk);
    void accept(SelectFieldIdentAsterisk fieldAsterisk);

    void accept(FromClause fromClause);
    void accept(AnsiFromClause fromClause);

    void accept(PlainTable table);

    void accept(Literal literal);

    void accept(ParenthesizedExpr expr);

    void accept(RelationCondition condition);

    void accept(LogicalExpression expression);

    void accept(ArithmeticExpression expression);

    void accept(CaseExpression expression);

    void accept(FunctionCall call);
    void accept(CountFunction call);

    void accept(CallArgument argument);
    void accept(ExtractFuncCallArgument argument);

    void accept(ReferencedName name);

    void accept(LikeCondition condition);

    void accept(Subquery subquery);

    void accept(BetweenCondition condition);

    void accept(IsNullCondition condition);

    void accept(AtTimeZoneExpression expression);

    void accept(SubqueryExpression expression);

    void accept(SequenceExpr expr);

    void accept(IntervalExpression expression);

    void accept(CastExpression expression);

    void accept(VariableDecl expression);

    void accept(RecordTypeDecl decl);
}
