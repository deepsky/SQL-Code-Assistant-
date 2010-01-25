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

package com.deepsky.findUsages;

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.resolve.ASTNodeHandler;
import com.deepsky.lang.plsql.psi.resolve.ASTTreeProcessor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import com.intellij.usages.impl.rules.UsageType;
import com.intellij.usages.impl.rules.UsageTypeProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SqlUsageTypeProvider implements UsageTypeProvider {

    public SqlUsageTypeProvider(){
        int hh =0;
    }

    @Nullable
    public UsageType getUsageType(PsiElement element) {

        final UsageType[] ret = {null};
        ASTTreeProcessor treeProcessor = new ASTTreeProcessor();
        treeProcessor.add(new ASTNodeHandler(){
            @NotNull
            public TokenSet getTokenSet() {
                return TokenSet.create(
                        PlSqlElementTypes.COLUMN_TYPE_REF,
                        PlSqlElementTypes.TABLE_TYPE_REF,
                        PlSqlElementTypes.SELECT_EXPRESSION,
                        PlSqlElementTypes.INSERT_COMMAND,
                        PlSqlElementTypes.UPDATE_COMMAND,
                        PlSqlElementTypes.DELETE_COMMAND,
                        PlSqlElementTypes.CREATE_VIEW,
                        PlSqlElementTypes.CREATE_TEMP_TABLE,
                        PlSqlElementTypes.CREATE_INDEX,
                        PlSqlElementTypes.CREATE_TRIGGER,
                        PlSqlElementTypes.ALTER_TABLE,
                        PlSqlElementTypes.ALTER_TRIGGER,
                        PlSqlElementTypes.TABLE_DEF
                );
            }

            public boolean handleNode(@NotNull ASTNode node) {
                if(node.getElementType() == PlSqlElementTypes.SELECT_EXPRESSION){
                    // todo -- handle possible cursor or View or Table declaration
                    if(node.getTreeParent().getElementType() == PlSqlElementTypes.TABLE_DEF){
                        ret[0] = SqlUsageType.USAGE_IN_DDL;
                    } else if(node.getTreeParent().getElementType() == PlSqlElementTypes.CREATE_VIEW){
                        ret[0] = SqlUsageType.USAGE_IN_DDL;
                    } else {
                        ret[0] = SqlUsageType.USAGE_IN_SELECT;
                    }
                } else if(node.getElementType() == PlSqlElementTypes.INSERT_COMMAND){
                    ret[0] = SqlUsageType.USAGE_IN_DML;
                } else if(node.getElementType() == PlSqlElementTypes.DELETE_COMMAND){
                    ret[0] = SqlUsageType.USAGE_IN_DML;
                } else if(node.getElementType() == PlSqlElementTypes.UPDATE_COMMAND){
                    ret[0] = SqlUsageType.USAGE_IN_DML;
                } else if(node.getElementType() == PlSqlElementTypes.TABLE_DEF){
                    ret[0] = SqlUsageType.USAGE_IN_DDL;
                } else if(node.getElementType() == PlSqlElementTypes.CREATE_VIEW){
                    ret[0] = SqlUsageType.USAGE_IN_DDL;
                } else if(node.getElementType() == PlSqlElementTypes.CREATE_TEMP_TABLE){
                    ret[0] = SqlUsageType.USAGE_IN_DDL;
                } else if(node.getElementType() == PlSqlElementTypes.CREATE_INDEX){
                    ret[0] = SqlUsageType.USAGE_IN_DDL;
                } else if(node.getElementType() == PlSqlElementTypes.CREATE_TRIGGER){
                    ret[0] = SqlUsageType.USAGE_IN_DDL;
                } else if(node.getElementType() == PlSqlElementTypes.ALTER_TABLE){
                    ret[0] = SqlUsageType.USAGE_IN_DDL;
                } else if(node.getElementType() == PlSqlElementTypes.ALTER_TRIGGER){
                    ret[0] = SqlUsageType.USAGE_IN_DDL;
                } else if(node.getElementType() == PlSqlElementTypes.COLUMN_TYPE_REF){
                    ret[0] = SqlUsageType.USAGE_IN_FIELDTYPE_DEF;
                } else if(node.getElementType() == PlSqlElementTypes.TABLE_TYPE_REF){
                    ret[0] = SqlUsageType.USAGE_IN_RECORDTYPE_DEF;
                }
                return true;
            }
        });

        treeProcessor.process(element.getNode(), true /* break on first hit*/);
        return ret[0];
    }
}
