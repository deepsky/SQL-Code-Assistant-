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

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;


public abstract class TableAliasHandler implements ASTNodeHandler {

    PlSqlElement alias;
    public TableAliasHandler(PlSqlElement alias){
        this.alias = alias;
    }

    @NotNull
    public TokenSet getTokenSet() {
        return PlSqlElementTypes.SQL_STATEMENTS;
    }

    public boolean handleNode(@NotNull ASTNode node) {
        PsiElement stmt = node.getPsi();

        GenericTable[] tables = new GenericTable[0];
        if (stmt != null) {
            if (stmt instanceof SelectStatement) {
                FromClause from = ((SelectStatement) stmt).getFromClause();
                tables = from.getTableList();
            } else if (stmt instanceof InsertSubquery) {
                FromClause from = ((InsertSubquery) stmt).getFromClause();
                if (from != null) {
                    tables = from.getTableList();
                } else {
                    // TODO - FROM not found,syntax tree CORRUPTED ?!!
                    throw new SyntaxTreeCorruptedException();
                }

            } else if (stmt instanceof InsertStatement) {
                tables = new GenericTable[1];
                tables[0] = ((InsertStatement) stmt).getIntoTable();
            } else if (stmt instanceof UpdateStatement) {
                tables = new GenericTable[1];
                tables[0] = ((UpdateStatement) stmt).getTargetTable();
            } else if (stmt instanceof DeleteStatement) {
                tables = new GenericTable[1];
                tables[0] = ((DeleteStatement) stmt).getTargetTable();
            } else {
                // TODO - what is the statement ?!!
                throw new SyntaxTreeCorruptedException();
            }
        }

        String text = alias.getStrippedText();
        // 1. is this a table alias?
        for (GenericTable t : tables) {
            if (t.getAlias() != null) {
                if (text.equalsIgnoreCase(t.getAlias())) {
                    // prefix matches to alias
                    handleTable(t);
                }
            }

            // 2. is this a table name?
            if (t instanceof PlainTable) {
                if (((PlainTable) t).getTableName().equalsIgnoreCase(text)) {
                    // prefix matches to table name
                    handleTable(t);
                }
            }
        }

        return false;
    }

    public abstract void handleTable(GenericTable t);
}
