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

package com.deepsky.database.exec.impl;

import com.deepsky.integration.PlSqlElementType;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class RespMessageTable {

    private static Map<IElementType, String> stmt2Message = null;
    private static Set<IElementType> ddlStmts;

    static {
        ddlStmts = new HashSet<IElementType>();
        stmt2Message = new HashMap<IElementType, String>();

        createEntry(PLSqlTypesAdopted.SELECT_EXPRESSION, "");
        createEntry(PLSqlTypesAdopted.SELECT_EXPRESSION_UNION, "");
        createEntry(PLSqlTypesAdopted.SUBQUERY, "");
        createEntry(PLSqlTypesAdopted.DELETE_COMMAND, "rows deleted");
        createEntry(PLSqlTypesAdopted.INSERT_COMMAND, "rows inserted");
        createEntry(PLSqlTypesAdopted.UPDATE_COMMAND, "rows updated");
        createEntry(PLSqlTypesAdopted.MERGE_COMMAND, "rows updated");

        createEntry(PLSqlTypesAdopted.TRUNCATE_TABLE, "Table truncated");

        createEntry(PLSqlTypesAdopted.TABLE_DEF, "Table created", true);
        createEntry(PLSqlTypesAdopted.CREATE_TEMP_TABLE, "Table created", true);
        createEntry(PLSqlTypesAdopted.CREATE_VIEW, "View created", true);

        createEntry(PLSqlTypesAdopted.OBJECT_TYPE_DEF, "Type created", true);
        createEntry(PLSqlTypesAdopted.VARRAY_COLLECTION, "Type created", true);
        createEntry(PLSqlTypesAdopted.TABLE_COLLECTION, "Type created", true);

        createEntry(PLSqlTypesAdopted.CREATE_INDEX, "Index created", true);
        createEntry(PLSqlTypesAdopted.FUNCTION_BODY, "Function created", true);
        createEntry(PLSqlTypesAdopted.PROCEDURE_BODY, "Procedure created", true);
        createEntry(PLSqlTypesAdopted.CREATE_SEQUENCE, "Sequence created", true);
        createEntry(PLSqlTypesAdopted.CREATE_DIRECTORY, "Directory created", true);
        createEntry(PLSqlTypesAdopted.CREATE_DB_LINK, "Database Link created", true);
        createEntry(PLSqlTypesAdopted.CREATE_TRIGGER, "Trigger created", true);
        createEntry(PLSqlTypesAdopted.CREATE_SYNONYM, "Synonym created", true);
        createEntry(PLSqlTypesAdopted.CREATE_TABLESPACE, "Tablespace created", true);

        createEntry(PLSqlTypesAdopted.ALTER_TABLE, "Table altered", true);
        createEntry(PLSqlTypesAdopted.ALTER_TRIGGER, "Trigger altered", true);
        createEntry(PLSqlTypesAdopted.ALTER_GENERIC, "Altered", true);
        createEntry(PLSqlTypesAdopted.ALTER_TABLESPACE, "Altered", true);

        createEntry(PLSqlTypesAdopted.PACKAGE_SPEC, "Package Specification created", true);
        createEntry(PLSqlTypesAdopted.PACKAGE_BODY, "Package Body created", true);

        createEntry(PLSqlTypesAdopted.DROP_TABLE, "Table dropped", true);
        createEntry(PLSqlTypesAdopted.DROP_VIEW, "View dropped", true);
        createEntry(PLSqlTypesAdopted.DROP_TYPE, "Type dropped", true);
        createEntry(PLSqlTypesAdopted.DROP_INDEX, "Index dropped", true);
        createEntry(PLSqlTypesAdopted.DROP_FUNCTION, "Function dropped", true);
        createEntry(PLSqlTypesAdopted.DROP_PROCEDURE, "Procedure dropped", true);
        createEntry(PLSqlTypesAdopted.DROP_SEQUENCE, "Sequence dropped", true);
        createEntry(PLSqlTypesAdopted.DROP_DIRECTORY, "Directory dropped", true);
        createEntry(PLSqlTypesAdopted.DROP_DB_LINK, "Database Link dropped", true);
        createEntry(PLSqlTypesAdopted.DROP_TRIGGER, "Trigger dropped", true);
        createEntry(PLSqlTypesAdopted.DROP_SYNONYM, "Synonym dropped", true);
        createEntry(PLSqlTypesAdopted.DROP_TABLESPACE, "Tablespace dropped", true);

        createEntry(PLSqlTypesAdopted.DROP_PACKAGE, "Package dropped", true);

        createEntry(PLSqlTypesAdopted.COMMIT_STATEMENT, "Commited");
        createEntry(PLSqlTypesAdopted.ROLLBACK_STATEMENT, "Rollbacked");

        // more checks needed 
        createEntry(PLSqlTypesAdopted.SQLPLUS_COMMAND, "");
        createEntry(PLSqlTypesAdopted.PLSQL_BLOCK, "");
    }

    private static void createEntry(PlSqlElementType elementType, String respMessage, boolean isDDL) {
        stmt2Message.put(elementType, respMessage);
        if(isDDL)
            ddlStmts.add(elementType);
    }

    private static void createEntry(PlSqlElementType elementType, String respMessage) {
        stmt2Message.put(elementType, respMessage);
    }

    public static boolean isStatementDDL(IElementType elementType) {
        return ddlStmts.contains(elementType);
    }

    public static String getResponseMessage(IElementType ietype){
        return stmt2Message.get(ietype);
    }

    public static TokenSet getExecutableStmts(){
        return TokenSet.create(stmt2Message.keySet().toArray(new IElementType[0]));
    }

}
