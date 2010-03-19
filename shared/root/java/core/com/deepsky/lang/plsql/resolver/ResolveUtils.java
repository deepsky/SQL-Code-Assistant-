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

import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.struct.parser.ContextPath;

public class ResolveUtils {

    public static String encodeCtx(int type, String name) {
        switch (type) {
            case ContextPath.SCHEMA_CTX:
                return ContextPath.SCHEMA_CTX_PRX + name;
            case ContextPath.PACKAGE_SPEC:
                return ContextPath.PACKAGE_SPEC_PRX + name;
            case ContextPath.PACKAGE_BODY:
                return ContextPath.PACKAGE_BODY_PRX + name;
            case ContextPath.FUNCTION_SPEC:
                return ContextPath.FUNCTION_SPEC_PRX + name;
            case ContextPath.FUNCTION_BODY:
                return ContextPath.FUNCTION_BODY_PRX + name;
            case ContextPath.PROCEDURE_SPEC:
                return ContextPath.PROCEDURE_SPEC_PRX + name;
            case ContextPath.PROCEDURE_BODY:
                return ContextPath.PROCEDURE_BODY_PRX + name;
            case ContextPath.CREATE_TRIGGER:
                return ContextPath.CREATE_TRIGGER_PRX + name;

            case ContextPath.RECORD_TYPE:
                return ContextPath.RECORD_TYPE_PRX + name;
            case ContextPath.OBJECT_TYPE:
                return ContextPath.OBJECT_TYPE_PRX + name;
            case ContextPath.COLLECTION_TYPE:
                return ContextPath.COLLECTION_TYPE_PRX + name;
            case ContextPath.VARRAY_TYPE:
                return ContextPath.VARRAY_TYPE_PRX + name;

            case ContextPath.TABLE_DEF:
                return ContextPath.TABLE_DEF_PRX + name;
            case ContextPath.VIEW_DEF:
                return ContextPath.VIEW_DEF_PRX + name;
            case ContextPath.COLUMN_DEF:
                return ContextPath.COLUMN_DEF_PRX + name;

            case ContextPath.VARIABLE_DECL:
                return ContextPath.VARIABLE_DECL_PRX + name;
            case ContextPath.ARGUMENT:
                return ContextPath.ARGUMENT_PRX + name;
            case ContextPath.RECORD_ITEM:
                return ContextPath.RECORD_ITEM_PRX + name;
            case ContextPath.SELECT_EXPR:
                return ContextPath.SELECT_EXPR_PRX + name;
            case ContextPath.FROM_TABLE_REF:
                return ContextPath.FROM_TABLE_REF_PRX + name;
            case ContextPath.FROM_ALIAS_REF:
                return ContextPath.FROM_ALIAS_REF_PRX + name;
            case ContextPath.LOOP_STMT:
                return ContextPath.LOOP_STMT_PRX + name;
        }

        throw new ConfigurationException("");
    }


    private static String t =
            ContextPath.SCHEMA_CTX_PRX +
            ContextPath.PACKAGE_SPEC_PRX + ContextPath.PACKAGE_BODY_PRX +
            ContextPath.FUNCTION_SPEC_PRX + ContextPath.FUNCTION_BODY_PRX +
            ContextPath.PROCEDURE_SPEC_PRX + ContextPath.PROCEDURE_BODY_PRX +
            ContextPath.CREATE_TRIGGER_PRX +
            ContextPath.RECORD_TYPE_PRX + ContextPath.OBJECT_TYPE_PRX +
            ContextPath.COLLECTION_TYPE_PRX + ContextPath.VARRAY_TYPE_PRX +
            ContextPath.TABLE_DEF_PRX + ContextPath.VIEW_DEF_PRX +
            ContextPath.COLUMN_DEF_PRX +
            ContextPath.VARIABLE_DECL_PRX + ContextPath.ARGUMENT_PRX +
            ContextPath.RECORD_ITEM_PRX + ContextPath.SELECT_EXPR_PRX +
            ContextPath.FROM_TABLE_REF_PRX + ContextPath.FROM_ALIAS_REF_PRX +
            ContextPath.LOOP_STMT_PRX;



    public static int prefix2type(String name) {
        switch(t.indexOf(name)){
            case 0: return ContextPath.SCHEMA_CTX;
            case 4: return ContextPath.PACKAGE_SPEC;
            case 2*4: return  ContextPath.PACKAGE_BODY;
            case 3*4: return  ContextPath.FUNCTION_SPEC;
            case 4*4: return ContextPath.FUNCTION_BODY;
            case 5*4: return ContextPath.PROCEDURE_SPEC;
            case 6*4: return ContextPath.PROCEDURE_BODY;
            case 7*4: return ContextPath.CREATE_TRIGGER;

            case 8*4: return ContextPath.RECORD_TYPE;
            case 9*4: return ContextPath.OBJECT_TYPE;
            case 10*4: return ContextPath.COLLECTION_TYPE;
            case 11*4: return ContextPath.VARRAY_TYPE;

            case 12*4: return ContextPath.TABLE_DEF;
            case 13*4: return ContextPath.VIEW_DEF;
            case 14*4: return ContextPath.COLUMN_DEF;

            case 15*4: return ContextPath.VARIABLE_DECL;
            case 16*4: return ContextPath.ARGUMENT;
            case 17*4: return ContextPath.RECORD_ITEM;
            case 18*4: return ContextPath.SELECT_EXPR;

            case 19*4: return ContextPath.FROM_TABLE_REF;
            case 20*4: return ContextPath.FROM_ALIAS_REF;

            case 21*4: return ContextPath.LOOP_STMT;
            default: return -1;
        }
    }


/*
    public static String encodeType(int type){
        switch(type){
            case ContextPath.SCHEMA_CTX: return "Sh!";
            case ContextPath.PACKAGE_SPEC: return "Ps!";
            case ContextPath.PACKAGE_BODY: return "Pb!";
            case ContextPath.FUNCTION_SPEC: return "Fs!";
            case ContextPath.FUNCTION_BODY: return "Fb!";
            case ContextPath.PROCEDURE_SPEC: return "PRs!";
            case ContextPath.PROCEDURE_BODY: return "PRb!";
            case ContextPath.CREATE_TRIGGER: return "Tr!";

            case ContextPath.RECORD_TYPE: return "Rt!";
            case ContextPath.OBJECT_TYPE: return "Ot!";
            case ContextPath.COLLECTION_TYPE: return "C!";
            case ContextPath.VARRAY_TYPE: return "Vy!";

            case ContextPath.VARIABLE_DECL: return "Var!";
            case ContextPath.ARGUMENT: return "Arg!";
        }

        return "";
    }
*/
}
