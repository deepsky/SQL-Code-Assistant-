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

package com.deepsky.lang.plsql.struct.parser;

import antlr.collections.AST;
import com.deepsky.generated.plsql.PLSqlTokenTypes;

public class ExprEvaluator {

    AST root;
    AST cur;
    EvalListener listener;

    public ExprEvaluator(AST root, EvalListener listener){
        this.listener = listener;
        this.root = root;
        this.cur = root;
    }

    public Expr calc(){
        return getOperand(getNextExpr());
    }

    private Expr getOperand(Expr l) {
        int op = getNextOp();
        if(op == -1){
            return l;
        }
        if (isAddSubtr(op)) {
            // + OR -
            return listener.eval1(l, getOperand(getNextExpr()), op);
        } else {
            // * OR /
            Expr r = getNextExpr();
            Expr e = listener.eval1(l, r, op);
            return getOperand(e);
        }
    }


    private int getNextOp() {
        if(cur == null){
            return -1;
        }
        int op = cur.getType();
        if(!isOperation(op)){
            throw new Error("It is not an operation");
        }
        cur = cur.getNextSibling();
        return op;
    }

    private Expr getNextExpr() {
        if(cur == null){
            return null;
        }

        String sign = "";
        if(isAddSubtr(cur.getType())){
            // unary minus OR plus
            sign = (cur.getType()== PLSqlTokenTypes.PLUS_OP)? "+": "-";
            cur = cur.getNextSibling();
        }
        if(isOperation(cur.getType())){
            throw new Error("It is not an operand");
        }
        AST ast = cur;
        cur = cur.getNextSibling();
        if(ast.getType() == PLSqlTokenTypes.PARENTHESIZED_EXPR){
            Expr e = new ExprEvaluator(ast.getFirstChild().getFirstChild(),listener).calc();
            return new Expr(sign + e.toString());
        } else {
            String n = ast.getFirstChild().getText();
            return new Expr(sign + n);
        }
    }

    private boolean isAddSubtr(int op) {
        switch (op) {
            case PLSqlTokenTypes.PLUS_OP:
            case PLSqlTokenTypes.MINUS_OP:
                return true;
            default:
                return false;
        }
    }

    private boolean isOperation(int op) {
        switch (op) {
            case PLSqlTokenTypes.PLUS_OP:
            case PLSqlTokenTypes.MINUS_OP:
            case PLSqlTokenTypes.MULTIPLICATION_OP:
            case PLSqlTokenTypes.DIVIDE_OP:
            case PLSqlTokenTypes.CONCAT_OP:
                return true;
            default:
                return false;
        }
    }



}
