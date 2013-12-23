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

package com.deepsky.lang.plsql.psi.impl.expr_eval;

import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.ArithmeticExpression;
import com.deepsky.lang.plsql.psi.Expression;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.validation.TypeCastException;
import com.deepsky.lang.validation.TypeValidationHelper;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

import java.util.Iterator;

public class ArithmeticExprTypeEvaluator {

//    private Expr root;
    private TreeIterator ite;
    private TypeValidationHelper validator;

    private Type evaluate(Type l, Type r, int opType) {
        int resultType = Type.UNKNOWN;
        try {
            resultType = validator.evaluate(l, r, opType);
//            resultType = TypeValidationHelper.evaluate(root.getPsi(), l, r, opType);

        } catch (Throwable e) {
            int hh =0;
        }

        if (resultType != Type.UNKNOWN) {
            // todo
            return TypeFactory.createTypeById(resultType);
        } else {
            throw new TypeCastException("Operation ... is not applicable for " + l.typeName() + " and " + r.typeName() + " types");
        }
    }


    public ArithmeticExprTypeEvaluator(TypeValidationHelper validator, Expr root) {
        this.validator = validator;
        ite = new TreeIterator(root);
//        this.root = root;
    }

    public Type calc() {
        return getOperand(getNextExpr());
    }

    private Type getOperand(Type l) {
        int op = getNextOp();
        if (op == -1) {
            return l;
        }
        if (isAddSubtr(op)) {
            // + OR -
            return evaluate(l, getOperand(getNextExpr()), op);
        } else {
            // * OR /
            Type r = getNextExpr();
            Type e = evaluate(l, r, op);
            return getOperand(e);
        }
    }

    private int getNextOp() {
        if (!ite.hasNext()) {
            return -1;
        }
        IElementType op = ite.next().getElementType();
        int _op = type2op(op);
        if (_op < 0) {
            throw new ValidationException("It is not an operation");
        }
        return _op;
    }

    private int type2op(IElementType itype) {
        if (itype == PlSqlTokenTypes.MINUS) {
            return PLSqlTokenTypes.MINUS_OP;
        } else if (itype == PlSqlTokenTypes.PLUS) {
            return PLSqlTokenTypes.PLUS_OP;
        } else if (itype == PlSqlTokenTypes.ASTERISK) {
            return PLSqlTokenTypes.MULTIPLICATION_OP;
        } else if (itype == PlSqlTokenTypes.DIVIDE) {
            return PLSqlTokenTypes.DIVIDE_OP;
        } else if (itype == PlSqlTokenTypes.CONCAT) {
            return PLSqlTokenTypes.CONCAT_OP;
        }
        return -1;
    }

    private Type getNextExpr() {
        if (!ite.hasNext()) {
            return null;
        }

        Expr node = ite.next();
        IElementType op = node.getElementType();
        int _op = type2op(op);
        if (isAddSubtr(_op)) {
            // sign: PLUS or MINUS
            // because it does not change type of expression, just skip it
            node = ite.next();
            op = node.getElementType();
            _op = type2op(op);
        }

        if (_op > 0) {
            throw new ValidationException("It is not an operation");
        }

        if (node.getElementType() == PlSqlElementTypes.PARENTHESIZED_EXPR) {
            //Expr nestedExpr = node.findChildByType(PlSqlElementTypes.PLSQL_EXPRESSION);
            Expr nestedExpr = node.findChildByType(PlSqlElementTypes.EXPR_TYPES);

            if (nestedExpr != null) {
                if (nestedExpr.node instanceof ArithmeticExpression) {
                    Expr child = nestedExpr.getFirstChildNode();
                    return new ArithmeticExprTypeEvaluator(validator, child).calc();
                } else {
                    return ((Expression) nestedExpr.node.getPsi()).getExpressionType();
                }
            } else {
                throw new ValidationException("PARENTHESIZED_EXPR issue!");
            }
        } else {
            try {
                return ((Expression) node.getPsi()).getExpressionType();
            } catch (ClassCastException e) {
                throw new ValidationException("INTERNAL ERROR! (ClassCastException)", node.getPsi());
            }
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


    class TreeIterator implements Iterator {
        Expr root;
        Expr cur;

        public TreeIterator(Expr node) {
            this.root = node;
            this.cur = node;
            locate();
        }

        public Expr locate() {
            if (cur == null) {
                return null;
            } else {
                while (cur != null) {
                    IElementType itype = cur.getElementType();
                    if(PlSqlTokenTypes.WS_TOKENS.contains(itype)){
                        // skip
                        cur = cur.getTreeNext();
/*
                    }
                    if (itype == TokenType.WHITE_SPACE
                            || itype == PlSqlTokenTypes.WS
                            || itype == PlSqlTokenTypes.ML_COMMENT
                            || itype == PlSqlTokenTypes.SL_COMMENT
                            || itype == PlSqlTokenTypes.LF) {
                        // skip
                        cur = cur.getTreeNext();
*/
                    } else {
                        break;
                    }
                }

                return cur;
            }
        }

        public boolean hasNext() {
            return cur != null;
        }

        public Expr next() {
            Expr node = cur;
            cur = cur.getTreeNext();
            locate();
            return node;
        }

        public void remove() {
        }
    }

}
