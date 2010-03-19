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

import com.deepsky.lang.plsql.psi.FromClause;
import com.deepsky.lang.plsql.psi.GenericTable;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class FromClauseImpl extends PlSqlElementBase implements FromClause {

    public FromClauseImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public GenericTable[] getTableList() {
        final ASTNode[] nodes = getNode().getChildren(PlSqlElementTypes.SELECT_FROM_CLAUSE);
        final ASTNode[] nodes2 = getNode().getChildren(TokenSet.create(PlSqlElementTypes.ANSI_JOIN_TAB_SPEC));

        int size = nodes.length + ((nodes2!=null)? nodes2.length: 0);
        GenericTable[] tabs = new GenericTable[size];
        int i=0;
        for(; i<nodes.length; i++){
            tabs[i]  = (GenericTableBase) nodes[i].getPsi();
        }
        for(int j=0; i<size; i++, j++){
            ASTNode[] nodes3 = nodes2[j].getChildren(PlSqlElementTypes.SELECT_FROM_CLAUSE);
            // only one table expected
            tabs[i]  = (GenericTableBase) nodes3[0].getPsi();
        }

        return tabs;
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
      if (visitor instanceof PlSqlElementVisitor) {
        ((PlSqlElementVisitor)visitor).visitFromClause(this);
      }
      else {
        super.accept(visitor);
      }
    }

}
