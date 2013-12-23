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

package com.deepsky.lang.plsql.psi.impl.ddl;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.ddl.CreateTriggerDDL;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;

public class CreateTriggerDDLImpl extends CreateTriggerGenericImpl implements CreateTriggerDDL {
    public CreateTriggerDDLImpl(ASTNode astNode) {
        super(astNode);
    }

    // "schema" | "database"
    public String getTarget() {
        ASTNode clause = getNode().findChildByType(PlSqlElementTypes.DB_EVNT_TRIGGER_CLAUSE); //.getChildren(null)
        if(clause != null){
            ASTNode target = clause.findChildByType(PlSqlElementTypes.TRIGGER_TARGET);
            if(target != null){
                ASTNode[] node = target.getChildren(targets);
                return node != null && node.length == 1? node[0].getText(): null;
            }
        }
        return null;
    }

    // "create" | "alter" | "drop" | "analyze" | ("associate" "statistics") | "audit" | "noaudit"
    // "comment" | "ddl" | ("diassociate" "statistics") | "grant" | "rename" | "revoke" | "truncate"
    public String getEventName() {
        ASTNode clause = getNode().findChildByType(PlSqlElementTypes.DB_EVNT_TRIGGER_CLAUSE); //.getChildren(null)
        if(clause != null){
            ASTNode[] node = clause.getChildren(events);
            return node != null && node.length == 1? node[0].getText(): null;
        }
        return null;
    }

    static final TokenSet targets = TokenSet.create(
            PlSqlTokenTypes.KEYWORD_SCHEMA, PlSqlTokenTypes.KEYWORD_DATABASE
    );

    static final TokenSet events = TokenSet.create(
            PlSqlTokenTypes.KEYWORD_CREATE, PlSqlTokenTypes.KEYWORD_ALTER, PlSqlTokenTypes.KEYWORD_DROP,
            PlSqlTokenTypes.KEYWORD_ANALYZE, PlSqlTokenTypes.KEYWORD_ASSOCIATE, PlSqlTokenTypes.KEYWORD_AUDIT,
            PlSqlTokenTypes.KEYWORD_NOAUDIT, PlSqlTokenTypes.KEYWORD_COMMENT, PlSqlTokenTypes.KEYWORD_DDL,
            PlSqlTokenTypes.KEYWORD_DIASSOCIATE, PlSqlTokenTypes.KEYWORD_GRANT, PlSqlTokenTypes.KEYWORD_NAME,
            PlSqlTokenTypes.KEYWORD_REVOKE, PlSqlTokenTypes.KEYWORD_TRUNCATE
    );


}
