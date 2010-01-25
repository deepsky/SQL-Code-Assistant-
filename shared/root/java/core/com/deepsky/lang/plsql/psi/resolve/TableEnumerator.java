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
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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

package com.deepsky.lang.plsql.psi.resolve;

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;


public abstract class TableEnumerator implements ASTNodeHandler {

    @NotNull
    public TokenSet getTokenSet() {
        return PlSqlElementTypes.SQL_STATEMENTS;
    }

    public boolean handleNode(@NotNull ASTNode node) {
        IElementType itype = node.getElementType();

        try {
            if (itype == PlSqlElementTypes.SELECT_EXPRESSION) {
                PsiElement stmt = node.getPsi();
                FromClause from = ((SelectStatement) stmt).getFromClause();
                for (GenericTable t : from.getTableList()) {
                    handleTable(t);
                }
//            } else if (itype == PlSqlElementTypes.INSERT_SUBQUERY) {
//                PsiElement stmt = node.getPsi();
//                FromClause from = ((InsertSubquery) stmt).getFromClause();
//                for (GenericTable t : from.getTableList()) {
//                    handleTable(t);
//                }

            } else if (itype == PlSqlElementTypes.INSERT_COMMAND) {
                PsiElement stmt = node.getPsi();
                handleTable(((InsertStatement) stmt).getIntoTable());
            } else if (itype == PlSqlElementTypes.UPDATE_COMMAND) {
                PsiElement stmt = node.getPsi();
                handleTable(((UpdateStatement) stmt).getTargetTable());
            } else if (itype == PlSqlElementTypes.DELETE_COMMAND) {
                PsiElement stmt = node.getPsi();
                handleTable(((DeleteStatement) stmt).getTargetTable());
/*
            } else if (itype == PlSqlElementTypes.SELECT_SUBSEQ) {
                // todo --
                PsiElement stmt = node.getPsi();
                FromClause from = ((SelectStatement) stmt).getFromClause();
                for (GenericTable t : from.getTableList()) {
                    handleTable(t);
                }
*/
            }
        } catch (SyntaxTreeCorruptedException e) {
        }

        return true;
    }

    public abstract void handleTable(GenericTable t);

}
