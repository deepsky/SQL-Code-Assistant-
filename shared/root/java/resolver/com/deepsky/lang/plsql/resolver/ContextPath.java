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

package com.deepsky.lang.plsql.resolver;

public interface ContextPath {

    // Context IDs
    final int SCHEMA_CTX = 0;
    final int PACKAGE_SPEC = 1;
    final int PACKAGE_BODY = 2;
    final int FUNCTION_SPEC = 3;
    final int FUNCTION_BODY = 4;
    final int PROCEDURE_SPEC = 5;
    final int PROCEDURE_BODY = 6;

    final int RECORD_TYPE = 8;
    final int OBJECT_TYPE = 9;
    final int COLLECTION_TYPE = 10;
    final int VARRAY_TYPE = 11;
    final int RECORD_ITEM = 81;

    final int VARIABLE_DECL = 12;
    final int ARGUMENT = 13;

    final int CREATE_TRIGGER = 7;

    final int TABLE_DEF = 14;
    final int COLUMN_DEF = 15;
    final int VIEW_DEF = 16;
    final int SELECT_EXPR = 17;
    final int FROM_TABLE_REF = 18;
    final int FROM_ALIAS_REF = 19;
    final int LOOP_STMT = 20;
    final int LOOP_INDEX = 21;
    final int UPDATE_STMT = 22;
    final int DELETE_STMT = 23;
    final int INSERT_STMT = 24;
    final int MERGE_STMT = 25;
    final int PLSQL_BLOCK = 26;
    final int SUBQUERY = 27;
    final int REF_CURSOR = 28;
    final int SYSTEM_FUNC = 29;
    final int SEQUENCE = 30;
    final int FILE_CTX = 31;
    final int SYNONYM = 32;
    final int CURSOR_DECL = 33;
    final int CURSOR_LOOP_SPEC = 34;
    final int SELECT_EXPR_UNION = 35;

    // search targets
    final int GENERIC_NAME_REF = 1001;
    final int PLSQL_NAME_REF = 1002;
    final int TYPE_REF = 1003;
    final int TABLE_REF = 1004;
    final int TABLE_COLUMN_REF = 1005;
    final int RECORD_ITEM_REF = 1006;
    final int SEQUENCE_REF = 1007;
    final int FUNC_CALL = 1008;
    final int PROC_CALL = 1009;
    final int NESTED_CALL = 1010;
    final int TABLE_COLUMN_ALIAS_REF = 1011;
    final int PACKAGE_REF = 1012;


    // Context prefixes
    final String SCHEMA_CTX_PRX = "/SH!";
    final String PACKAGE_SPEC_PRX = "/PS!";
    final String PACKAGE_BODY_PRX = "/PB!";
    final String FUNCTION_SPEC_PRX = "/Fs!";
    final String FUNCTION_BODY_PRX = "/Fb!";
    final String PROCEDURE_SPEC_PRX = "/Ps!";
    final String PROCEDURE_BODY_PRX = "/Pb!";
    final String CREATE_TRIGGER_PRX = "/Tr!";
    final String SEQUENCE_PRX = "/Sq!";

    final String RECORD_TYPE_PRX = "/Rt!";
    final String OBJECT_TYPE_PRX = "/Ot!";
    final String COLLECTION_TYPE_PRX = "/Co!";
    final String VARRAY_TYPE_PRX = "/Vy!";

    final String TABLE_DEF_PRX = "/Td!";
    final String VIEW_DEF_PRX = "/Vd!";
    final String COLUMN_DEF_PRX = "/Cd!";

    final String VARIABLE_DECL_PRX = "/Va!";
    final String ARGUMENT_PRX = "/Ar!";
    final String RECORD_ITEM_PRX = "/Ti!";
    final String SELECT_EXPR_PRX = "/Se!";
    final String FROM_TABLE_REF_PRX = "/Ft!";
    final String FROM_ALIAS_REF_PRX = "/Fa!";
    final String LOOP_STMT_PRX = "/Lu!";
    final String LOOP_INDEX_PRX = "/Li!";
    final String UPDATE_STMT_PRX = "/Ud!";
    final String DELETE_STMT_PRX = "/De!";
    final String INSERT_STMT_PRX = "/In!";
    final String MERGE_STMT_PRX = "/Me!";
    final String PLSQL_BLOCK_PRX = "/Bl!";
    final String SUBQUERY_PRX = "/Sb!";
    final String REF_CURSOR_PRX = "/Rc!";
    final String SYSTEM_FUNC_PRX = "/Sf!";
    final String FILE_CTX_PRX = "/FL!";
    final String SYNONYM_PRX = "/Sy!";
    final String CURSOR_DECL_PRX = "/Cu!";
    final String CURSOR_LOOP_SPEC_PRX = "/Cs!";
    final String SELECT_EXPR_UNION_PRX = "/Su!";


    String getPath();
}
