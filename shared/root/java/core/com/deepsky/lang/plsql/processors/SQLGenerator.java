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

package com.deepsky.lang.plsql.processors;

import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.ReferencedName;

import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

public class SQLGenerator implements Visitor {

    WriterHelper w;

    public void printOut(PlSqlElement v, OutputStream ps) {
        w = new WriterHelper(ps);
        v.process(this);
        w.outln();
    }


    public String outToString(PlSqlElement v) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        w = new WriterHelper(out);
        v.process(this);
        w.flush();

        return new String(out.toByteArray());
    }


    public void accept(SelectStatement select) {
        // displayed column list
        w.out("SELECT").outln();
        w.incrementIndent();

        for (int i = 0; i < select.getSelectFieldList().length; i++) {
            if (i > 0) {
                w.out(",").outln();
            }
            SelectFieldCommon f = select.getSelectFieldList()[i];
            f.process(this);
        }
        w.decrementIndent();

        w.outln();

        select.getFromClause().process(this);

        if (select.getWhereCondition() != null) {
            w.outln();
            select.getWhereCondition().process(this);
//            w.outln();
//            w.out("WHERE").outln();
//            w.incrementIndent();
//            select.getWhereCondition().getCondition().process(this);
//            w.decrementIndent();
        }

//        if (select.getConnectClause() != null) {
//            // todo -
//        }

        if (select.getGroupByClause() != null) {
            w.outln();
            select.getGroupByClause().process(this);
        }

        switch (select.getFollowingSelectStatementType()) {
            case SelectStatement.NONE:
                break;
            case SelectStatement.UNION_TYPE:
                w.outln();
                w.out("union").outln();
                select.getFollowingSelectStatement().process(this);
                break;
            case SelectStatement.INTERSECT_TYPE:
                w.outln();
                w.out("intersect").outln();
                select.getFollowingSelectStatement().process(this);
                break;
            case SelectStatement.MINUS_TYPE:
                w.outln();
                w.out("minus").outln();
                select.getFollowingSelectStatement().process(this);
                break;
        }

        if (select.getOrderByClause() != null) {
            w.outln();
            select.getOrderByClause().process(this);
        }

//        if (select.getUpdateClause() != null) {
//            // todo -
//        }
    }

    public void accept(WhereCondition where) {
        w.out("WHERE").outln();
        w.incrementIndent();
        where.getCondition().process(this);
        w.decrementIndent();
    }

    public void accept(OrderByClause orderByClause) {
        if (orderByClause.getOrderPairList().length > 0) {
            w.out("ORDER BY ");
            for (int i = 0; i < orderByClause.getOrderPairList().length; i++) {
                OrderByClause.OrderPair d = orderByClause.getOrderPairList()[i];
                if (i > 0) {
                    w.out(", ");
                }

                d.getExpession().process(this);
                if (d.getSortOrder() != null && orderByClause.getOrderPairList().length > 0) {
                    w.out(" ").out(d.getSortOrder());
                }
            }
        }
    }

    public void accept(SelectFieldExpr fieldExpr) {
        fieldExpr.getExpression().process(this);
        if (fieldExpr.getAlias() != null && fieldExpr.getAlias().length() > 0) {
            w.out(" AS " + fieldExpr.getAlias());
        }
    }

    public void accept(SelectFieldAsterisk fieldAsterisk) {
        w.out("*");
    }

    public void accept(SelectFieldIdentAsterisk fieldAsterisk) {
        if (fieldAsterisk.getTableRef() != null && fieldAsterisk.getTableRef().length() > 0) {
            w.out(fieldAsterisk.getTableRef()).out(".");
        }

        w.out("*");
    }

    public void accept(FromClause from) {
        w.out("FROM").outln();
        w.incrementIndent();
        for (int i = 0; i < from.getTableList().length; i++) {
            if (i > 0) {
                w.out(",");
            }
            if (i > 0 && i < from.getTableList().length - 1) {
                w.outln();
            }

            GenericTable t = from.getTableList()[i];
            t.process(this);
        }
        w.decrementIndent();
    }

    public void accept(AnsiFromClause fromClause) {
//        XDV_STG_AMACS_AGR_15M_T EXT
//        LEFT JOIN XDV_SINGTEL_AMACS_APP_TYPE_DT AT_
//            ON EXT.SERVICE_TYPE = AT_.SERVICE_TYPE
//        LEFT JOIN XDV_SINGTEL_AMACS_ACT_DT ACT
//            ON EXT.TRAFFIC_CLASS = ACT.TRAFFIC_CLASS
//        LEFT JOIN XDV_TRN_QOS_DT QOS
//            ON EXT.CALL_SETUP_RESULT_CODE = QOS.RQOS_HEXID
//        LEFT JOIN XDV_TRN_CAUSES_DT CA
//            ON (EXT.TERMINATING_REASON = CA.PRIMARY_CAUSE_ID AND CA.INTERFACE = 'AMACS' AND CA.AMK_ID = 0)

        w.out("FROM").outln();
        w.incrementIndent();
        fromClause.getLeadTable().process(this);
        w.outln();

        for (AnsiFromClause.JoinPart join : fromClause.getJoinParts()) {
            for (String s : join.joinSpec()) {
                w.out(s).out(" ");
            }

            join.getTable().process(this);
            w.outln();
            w.incrementIndent();
            w.out("ON ");
            join.getCondition().process(this);
            w.decrementIndent();
            w.outln();
        }
        w.decrementIndent();
    }

    public void accept(PlainTable t) {
        if (t.getSchemaName() != null && t.getSchemaName().length() > 0) {
            w.out(t.getSchemaName() + ".");
        }

        w.out(t.getTableName());
        if (t.getAlias() != null && t.getAlias().length() > 0) {
            w.out(" " + t.getAlias());
        }
    }

    public void accept(Literal literal) {
        w.out(literal.valueToString());
    }

    public void accept(ParenthesizedExpr expr) {
        w.out("(");
        expr.getNestedExpr().process(this);
        w.out(")");
    }

    public void accept(RelationCondition condition) {
        condition.getLeftExpr().process(this);
        w.out(" " + condition.getRelationOp() + " ");
        condition.getRightExpr().process(this);
    }

    public void accept(LogicalExpression expression) {
        String link = expression.isOr() ? "OR" : "AND";
        for (int i = 0; i < expression.getList().size(); i++) {
            if (i > 0) {
                w.out(" ").out(link).out(" ");
            }
            expression.getList().get(i).process(this);
        }
    }

    public void accept(ArithmeticExpression expression) {
        for (Object o : expression.getExpressionItemList()) {
            if (o instanceof Expression) {
                ((Expression) o).process(this);
            } else {
                w.out(" ").out(o.toString()).out(" ");
            }
        }

    }

    public void accept(CaseExpression expression) {

        if (expression.getCaseType() == CaseExpression.SEARCHED) {
            w.out("CASE ");
            for (CaseExpression.WhenThenPair p : expression.getWhenThenPairs()) {
                w.out(" WHEN ");
                p.getWhen().process(this);
                w.out(" THEN ");
                p.getThen().process(this);
            }

            if (expression.getElseExpression() != null) {
                w.out(" ELSE ");
                expression.getElseExpression().process(this);
            }
            w.out(" END");
        } else if (expression.getCaseType() == CaseExpression.SIMPLE) {
            w.out("CASE ");

            expression.getLeadExpression().process(this);
            for (CaseExpression.WhenThenPair p : expression.getWhenThenPairs()) {
                w.out(" WHEN ");
                p.getWhen().process(this);
                w.out(" THEN ");
                p.getThen().process(this);
            }

            if (expression.getElseExpression() != null) {
                w.out(" ELSE ");
                expression.getElseExpression().process(this);
            }
            w.out(" END");
        }
    }

    public void accept(FunctionCall call) {

        w.out(call.getFunctionName());
        if (call.getCallArgumentList() != null && call.getCallArgumentList().length > 0) {
            w.out("(");
            CallArgument[] args = call.getCallArgumentList();
            for (int i = 0; i < args.length; i++) {
                if (i > 0) {
                    w.out(", ");
                }
                args[i].process(this);
            }
            w.out(")");
        }

    }

    public void accept(CountFunction call) {
        w.out("COUNT(");
        if (call.getAsterisk() != null) {
            w.out(call.getAsterisk());
        } else {
            call.getCallArgumentList()[0].process(this);
        }
        w.out(")");
    }

    public void accept(CallArgument argument) {
        if (argument.getVariableName() != null) {
            // name binding
            w.out(argument.getVariableName()).out(" => ");
        }
        argument.getExpression().process(this);
    }

    public void accept(ExtractFuncCallArgument argument) {
        w.out(argument.getLiteral()).out(" FROM ");
        argument.getExpression().process(this);
    }

    public void accept(ReferencedName name) {
        w.out(name.getFullName());
    }

    public void accept(LikeCondition condition) {
        condition.getLeftExpr().process(this);
        if (condition.isNotLike()) {
            w.out(" NOT LIKE ");
        } else {
            w.out(" LIKE ");
        }

        condition.getRightExpr().process(this);
    }

    public void accept(Subquery subquery) {
        w.out("(");
        w.incrementIndent();
        subquery.getSelectStatement().process(this);
        w.decrementIndent();
        w.out(")");
        if (subquery.getAlias() != null) {
            w.out(" ").out(subquery.getAlias());
        }
    }

    public void accept(BetweenCondition condition) {
        condition.getLead().process(this);
        if (condition.isNotBetween()) {
            w.out(" NOT");
        }
        w.out(" BETWEEN ");
        condition.getLeft().process(this);
        w.out(" AND ");
        condition.getRight().process(this);
    }

    public void accept(IsNullCondition condition) {
        condition.getExpression().process(this);
        if (condition.isNotNull()) {
            w.out(" IS NOT NULL");
        } else {
            w.out(" IS NULL");
        }
    }

    public void accept(AtTimeZoneExpression expression) {
        expression.getExpression().process(this);
        w.out(" AT TIME ZONE ").out(expression.getTimeZone());
    }

    public void accept(SubqueryExpression expression) {
        w.out("(");
        expression.getSelectStatement().process(this);
        w.out(")");
    }

    public void accept(SequenceExpr expr) {
        w.out(expr.getSequenceName().getText()).out(".").out(expr.getMethod());
    }

    public void accept(IntervalExpression expression) {
        w.out("INTERVAL ").out(expression.getIntervalValue()).out(" ").out(expression.getIntervalType());
    }

    public void accept(CastExpression expression) {
        // CAST (INT_SESSION_ID AS INTEGER)
        w.out("CAST( ");
        expression.getExpression().process(this);
        w.out(" AS ");
        w.out(expression.getTypeName());
        w.out(" )");
    }

    public void accept(VariableDecl expr) {
        w.out(expr.getDeclName()).out(" ");
        w.out(expr.getType().typeName());

        if(expr.isBeingAssigned()){
            w.out(" := ");
            expr.getDefaultExpr().process(this);
        }
        w.out(";");
    }

    public void accept(RecordTypeDecl decl) {
        //decl.
    }

}
