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

package com.deepsky.lang.plsql.formatter;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.ColumnSpecList;

public class PlSqlNodeVisitor {

    public final void visit(ASTNode node) {
        final IElementType type = node.getElementType();
        if (type == PlSqlTokenTypes.FILE) {
            visitFile(node);
        } else if (type == PlSqlElementTypes.PACKAGE_BODY) {
            visitPackageBody(node);
        } else if (type == PlSqlElementTypes.PACKAGE_SPEC) {
            visitPackageSpec(node);
        } else if (type == PlSqlElementTypes.PROCEDURE_BODY) {
            visitProcedure(node);
        } else if (type == PlSqlElementTypes.FUNCTION_BODY) {
            visitFunction(node);
        } else if (type == PlSqlElementTypes.SELECT_EXPRESSION) { //COMMAND) {
            visitSelectStatement(node);
        } else if (type == PlSqlElementTypes.INSERT_COMMAND) {
            visitInsertStatement(node);
//        } else if (type == PlSqlElementTypes.INSERT_SUBQUERY) {
//            visitInsertSubquery(node);
        } else if (type == PlSqlElementTypes.DELETE_COMMAND) {
            visitDeleteStatement(node);
        } else if (type == PlSqlElementTypes.UPDATE_COMMAND) {
            visitUpdateStatement(node);
        } else if (type == PlSqlElementTypes.TABLE_REFERENCE_LIST_FROM) {
            visitFromClause(node);
        } else if (type == PlSqlElementTypes.PLSQL_BLOCK) {
            visitPlSqlBlock(node);
//        } else if (type == PlSqlElementTypes.PLSQL_EXPRESSION) {
//            visitExpression(node);
        } else if (type == PlSqlElementTypes.COLUMN_SPEC_LIST) {
            visitColumnSpecList(node);
//        } else if (type == PlSqlElementTypes.PAREN_EXPR_LIST) {
//            visitParentesizedExprList(node);
        } else {
            //visitElement(node);
        }
    }

    public void visitParentesizedExprList(final ASTNode node) {
        visitElement(node);
    }


    public void visitColumnSpecList(final ASTNode node) {
        visitElement(node);
    }

    public void visitInsertSubquery(final ASTNode node) {
        visitElement(node);
    }

    public void visitInsertStatement(final ASTNode node) {
        visitElement(node);
    }

    public void visitSelectStatement(final ASTNode node) {
        visitElement(node);
    }

    public void visitFunction(final ASTNode node) {
        visitElement(node);
    }

    public void visitProcedure(final ASTNode node) {
        visitElement(node);
    }

    public void visitPackageSpec(final ASTNode node) {
        visitElement(node);
    }

    public void visitDeleteStatement(final ASTNode node) {
        visitElement(node);
    }

    public void visitUpdateStatement(final ASTNode node) {
        visitElement(node);
    }

    public void visitFromClause(final ASTNode node) {
        visitElement(node);
    }

    public void visitPlSqlBlock(final ASTNode node) {
        visitElement(node);
    }

    public void visitExpression(final ASTNode node) {
        visitElement(node);
    }

    public void visitPackageBody(final ASTNode node) {
        visitElement(node);
    }

    public void visitFile(final ASTNode node) {
        visitElement(node);
    }

    public void visitElement(final ASTNode node) {
        //To change body of created methods use File | Settings | File Templates.
    }
}
