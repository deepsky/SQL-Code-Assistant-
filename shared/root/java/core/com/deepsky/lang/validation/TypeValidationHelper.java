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

package com.deepsky.lang.validation;

import com.deepsky.database.ObjectCache;
import com.deepsky.database.ObjectCacheFactory;
import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.psi.resolve.TypeNotResolvedException;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.struct.TableDescriptor;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.types.RowtypeType;
import com.deepsky.lang.plsql.struct.types.TableColumnRefType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.containers.HashMap;

import java.util.*;

public class TypeValidationHelper {

    static Map<String, OperationHolder> pp;
    static List<Set<Integer>> equaval;

    static {
        equaval = new ArrayList<Set<Integer>>();
        equaval.add(new HashSet<Integer>());
        equaval.get(0).add(Type.INTEGER);
        equaval.get(0).add(Type.NUMBER);
        equaval.get(0).add(Type.NULL);

        equaval.add(new HashSet<Integer>());
        equaval.get(1).add(Type.VARCHAR);
        equaval.get(1).add(Type.VARCHAR2);
        equaval.get(1).add(Type.CHAR);
        equaval.get(1).add(Type.NULL);

        equaval.add(new HashSet<Integer>());
        equaval.get(2).add(Type.DATE);
        equaval.get(2).add(Type.NULL);

        equaval.add(new HashSet<Integer>());
        equaval.get(3).add(Type.TIMESTAMP);
        equaval.get(3).add(Type.NULL);

        equaval.add(new HashSet<Integer>());
        equaval.get(4).add(Type.BOOLEAN);
        equaval.get(4).add(Type.NULL);

        equaval.add(new HashSet<Integer>());
        equaval.get(5).add(Type.ROWTYPE);
        equaval.get(5).add(Type.NULL);

        equaval.add(new HashSet<Integer>());
        equaval.get(6).add(Type.RECORD_TYPE);
        equaval.get(6).add(Type.NULL);

        pp = new HashMap<String, OperationHolder>();

        pp.put(pair2string(Type.RAW, Type.RAW),
                new OperationHolder(Type.RAW)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.BOOLEAN, Type.BOOLEAN),
                new OperationHolder(Type.BOOLEAN)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.OR_LOGICAL)
                        .add(PLSqlTokenTypes.AND_LOGICAL)
        );


        pp.put(pair2string(Type.INTEGER, Type.INTEGER),
                new OperationHolder(Type.INTEGER)
                        .add(PLSqlTokenTypes.PLUS_OP)
                        .add(PLSqlTokenTypes.MINUS_OP)
                        .add(PLSqlTokenTypes.MULTIPLICATION_OP)
                        .add(PLSqlTokenTypes.DIVIDE_OP)
                        .add(PLSqlTokenTypes.CONCAT_OP, Type.VARCHAR2)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LE, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GE, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.PLS_INTEGER, Type.PLS_INTEGER),
                new OperationHolder(Type.PLS_INTEGER)
                        .add(PLSqlTokenTypes.PLUS_OP, Type.INTEGER)
                        .add(PLSqlTokenTypes.MINUS_OP, Type.INTEGER)
                        .add(PLSqlTokenTypes.MULTIPLICATION_OP, Type.INTEGER)
                        .add(PLSqlTokenTypes.DIVIDE_OP, Type.INTEGER)
                        .add(PLSqlTokenTypes.CONCAT_OP, Type.VARCHAR2)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LE, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GE, Type.BOOLEAN)
        );
        pp.put(pair2string(Type.PLS_INTEGER, Type.INTEGER),
                new OperationHolder(Type.PLS_INTEGER)
                        .add(PLSqlTokenTypes.PLUS_OP, Type.INTEGER)
                        .add(PLSqlTokenTypes.MINUS_OP, Type.INTEGER)
                        .add(PLSqlTokenTypes.MULTIPLICATION_OP, Type.INTEGER)
                        .add(PLSqlTokenTypes.DIVIDE_OP, Type.INTEGER)
                        .add(PLSqlTokenTypes.CONCAT_OP, Type.VARCHAR2)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LE, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GE, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.PLS_INTEGER, Type.NUMBER),
                new OperationHolder(Type.PLS_INTEGER)
                        .add(PLSqlTokenTypes.PLUS_OP, Type.NUMBER)
                        .add(PLSqlTokenTypes.MINUS_OP, Type.NUMBER)
                        .add(PLSqlTokenTypes.MULTIPLICATION_OP, Type.NUMBER)
                        .add(PLSqlTokenTypes.DIVIDE_OP, Type.NUMBER)
                        .add(PLSqlTokenTypes.CONCAT_OP, Type.VARCHAR2)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LE, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GE, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.INTEGER, Type.VARCHAR2),
                new OperationHolder(Type.INTEGER)
                        .add(PLSqlTokenTypes.PLUS_OP)
                        .add(PLSqlTokenTypes.MINUS_OP)
                        .add(PLSqlTokenTypes.MULTIPLICATION_OP)
                        .add(PLSqlTokenTypes.DIVIDE_OP)
                        .add(PLSqlTokenTypes.CONCAT_OP, Type.VARCHAR2)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LE, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GE, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.NUMBER, Type.VARCHAR2),
                new OperationHolder(Type.NUMBER)
                        .add(PLSqlTokenTypes.PLUS_OP)
                        .add(PLSqlTokenTypes.MINUS_OP)
                        .add(PLSqlTokenTypes.MULTIPLICATION_OP)
                        .add(PLSqlTokenTypes.DIVIDE_OP)
                        .add(PLSqlTokenTypes.CONCAT_OP, Type.VARCHAR2)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LE, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GE, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.NUMBER, Type.INTEGER),
                new OperationHolder(Type.NUMBER)
                        .add(PLSqlTokenTypes.PLUS_OP)
                        .add(PLSqlTokenTypes.MINUS_OP)
                        .add(PLSqlTokenTypes.MULTIPLICATION_OP)
                        .add(PLSqlTokenTypes.DIVIDE_OP)
                        .add(PLSqlTokenTypes.CONCAT_OP, Type.VARCHAR2)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LE, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GE, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.NUMBER, Type.PLS_INTEGER),
                new OperationHolder(Type.NUMBER)
                        .add(PLSqlTokenTypes.PLUS_OP)
                        .add(PLSqlTokenTypes.MINUS_OP)
                        .add(PLSqlTokenTypes.MULTIPLICATION_OP)
                        .add(PLSqlTokenTypes.DIVIDE_OP)
                        .add(PLSqlTokenTypes.CONCAT_OP, Type.VARCHAR2)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LE, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GE, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.NUMBER, Type.NUMBER),
                new OperationHolder(Type.NUMBER)
                        .add(PLSqlTokenTypes.PLUS_OP)
                        .add(PLSqlTokenTypes.MINUS_OP)
                        .add(PLSqlTokenTypes.MULTIPLICATION_OP)
                        .add(PLSqlTokenTypes.DIVIDE_OP)
                        .add(PLSqlTokenTypes.CONCAT_OP, Type.VARCHAR2)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LE, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GE, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.INTEGER, Type.NUMBER),
                new OperationHolder(Type.NUMBER)
                        .add(PLSqlTokenTypes.PLUS_OP)
                        .add(PLSqlTokenTypes.MINUS_OP)
                        .add(PLSqlTokenTypes.MULTIPLICATION_OP)
                        .add(PLSqlTokenTypes.DIVIDE_OP)
                        .add(PLSqlTokenTypes.CONCAT_OP, Type.VARCHAR2)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LE, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GE, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.VARCHAR2, Type.NUMBER),
                new OperationHolder(Type.VARCHAR2)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
        );

        pp.put(pair2string(Type.VARCHAR2, Type.INTEGER),
                new OperationHolder(Type.VARCHAR2)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
        );

        pp.put(pair2string(Type.CHAR, Type.VARCHAR2),
                new OperationHolder(Type.CHAR)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.CHAR, Type.CHAR),
                new OperationHolder(Type.CHAR)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.CHAR, Type.NVARCHAR2),
                new OperationHolder(Type.CHAR)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.CHAR, Type.NUMBER),
                new OperationHolder(Type.CHAR)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
        );

        pp.put(pair2string(Type.CHAR, Type.ANY ),
                new OperationHolder(Type.CHAR)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.VARCHAR2, Type.VARCHAR2),
                new OperationHolder(Type.VARCHAR2)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
        );

        pp.put(pair2string(Type.NVARCHAR2, Type.VARCHAR2),
                new OperationHolder(Type.NVARCHAR2)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
        );

        pp.put(pair2string(Type.NVARCHAR2, Type.CHAR),
                new OperationHolder(Type.NVARCHAR2)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
        );

//        pp.put(pair2string(Type.VARCHAR2, Type.VARCHAR),
//                new OperationHolder(Type.VARCHAR2)
//                        .add(PLSqlTokenTypes.CONCAT_OP)
//                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
//                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
//        );

        pp.put(pair2string(Type.VARCHAR2, Type.CHAR),
                new OperationHolder(Type.VARCHAR2)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
        );

        pp.put(pair2string(Type.NUMBER, Type.VARCHAR2),
                new OperationHolder(Type.VARCHAR2)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
        );


        // todo - dirty workaround should be fixed asap
        pp.put(pair2string(Type.ANY, Type.VARCHAR2),
                new OperationHolder(Type.ANY)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.VARCHAR2, Type.ANY ),
                new OperationHolder(Type.VARCHAR2)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.ANY, Type.ANY ),
                new OperationHolder(Type.ANY)
                        .add(PLSqlTokenTypes.PLUS_OP)
                        .add(PLSqlTokenTypes.MINUS_OP)
                        .add(PLSqlTokenTypes.MULTIPLICATION_OP)
                        .add(PLSqlTokenTypes.DIVIDE_OP)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.ANY, Type.INTEGER),
                new OperationHolder(Type.INTEGER)
                        .add(PLSqlTokenTypes.PLUS_OP)
                        .add(PLSqlTokenTypes.MINUS_OP)
                        .add(PLSqlTokenTypes.MULTIPLICATION_OP)
                        .add(PLSqlTokenTypes.DIVIDE_OP)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.INTEGER, Type.ANY ),
                new OperationHolder(Type.INTEGER)
                        .add(PLSqlTokenTypes.PLUS_OP)
                        .add(PLSqlTokenTypes.MINUS_OP)
                        .add(PLSqlTokenTypes.MULTIPLICATION_OP)
                        .add(PLSqlTokenTypes.DIVIDE_OP)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.ANY, Type.NUMBER),
                new OperationHolder(Type.NUMBER)
                        .add(PLSqlTokenTypes.PLUS_OP)
                        .add(PLSqlTokenTypes.MINUS_OP)
                        .add(PLSqlTokenTypes.MULTIPLICATION_OP)
                        .add(PLSqlTokenTypes.DIVIDE_OP)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
        );

        pp.put(pair2string(Type.ANY, Type.TIMESTAMP),
                new OperationHolder(Type.ANY)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.VARCHAR2, Type.NULL ),
                new OperationHolder(Type.VARCHAR2)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
        );

        pp.put(pair2string(Type.VARCHAR, Type.NULL ),
                new OperationHolder(Type.VARCHAR)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
        );

        pp.put(pair2string(Type.CHAR, Type.NULL ),
                new OperationHolder(Type.CHAR)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
        );

        pp.put(pair2string(Type.TIMESTAMP, Type.NULL ),
                new OperationHolder(Type.TIMESTAMP)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
        );

        pp.put(pair2string(Type.DATE, Type.NULL ),
                new OperationHolder(Type.DATE)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
        );

        pp.put(pair2string(Type.INTEGER, Type.NULL ),
                new OperationHolder(Type.INTEGER)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
        );

        pp.put(pair2string(Type.PLS_INTEGER, Type.NULL ),
                new OperationHolder(Type.PLS_INTEGER)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
        );

        pp.put(pair2string(Type.NUMBER, Type.NULL ),
                new OperationHolder(Type.NUMBER)
                        .add(PLSqlTokenTypes.CONCAT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
        );

        pp.put(pair2string(Type.ANY, Type.DATE),
                new OperationHolder(Type.ANY)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.ANY, Type.CHAR),
                new OperationHolder(Type.ANY)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
        );

        pp.put(pair2string(Type.NUMBER, Type.ANY ),
                new OperationHolder(Type.NUMBER)
                        .add(PLSqlTokenTypes.PLUS_OP)
                        .add(PLSqlTokenTypes.MINUS_OP)
                        .add(PLSqlTokenTypes.MULTIPLICATION_OP)
                        .add(PLSqlTokenTypes.DIVIDE_OP)
                        .add(PLSqlTokenTypes.CONCAT_OP)
        );

        pp.put(pair2string(Type.TIMESTAMP, Type.NUMBER ),
                new OperationHolder(Type.TIMESTAMP)
                        .add(PLSqlTokenTypes.PLUS_OP)
                        .add(PLSqlTokenTypes.MINUS_OP)
        );

        pp.put(pair2string(Type.TIMESTAMP, Type.INTEGER ),
                new OperationHolder(Type.TIMESTAMP)
                        .add(PLSqlTokenTypes.PLUS_OP)
                        .add(PLSqlTokenTypes.MINUS_OP)
        );

        pp.put(pair2string(Type.TIMESTAMP, Type.TIMESTAMP ),
                new OperationHolder(Type.BOOLEAN)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LE, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GE, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
        );

        pp.put(pair2string(Type.DATE, Type.DATE ),
                new OperationHolder(Type.BOOLEAN)
                        .add(PLSqlTokenTypes.MINUS_OP, Type.NUMBER)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LE, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GE, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
        );

        pp.put(pair2string(Type.DATE, Type.TIMESTAMP ),
                new OperationHolder(Type.BOOLEAN)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.NOT_EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.EQ, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GT, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.LE, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.GE, Type.BOOLEAN)
                        .add(PLSqlTokenTypes.ASSIGNMENT_OP)
        );

        pp.put(pair2string(Type.DATE, Type.NUMBER ),
                new OperationHolder(Type.DATE)
                        .add(PLSqlTokenTypes.PLUS_OP)
                        .add(PLSqlTokenTypes.MINUS_OP)
        );

        pp.put(pair2string(Type.DATE, Type.INTEGER ),
                new OperationHolder(Type.DATE)
                        .add(PLSqlTokenTypes.PLUS_OP)
                        .add(PLSqlTokenTypes.MINUS_OP)
        );
    }

    public static int evaluate(int l, int r, int op) {

        String key = l + ":" + r;
        OperationHolder holder = pp.get(key);
        if (holder != null ){ //&& holder.contains(op)) {
            return holder.resultTypeForOp(op);
//            return holder.resultType();
        } else {
            return Type.UNKNOWN;
        }
    }


    private static Type getRealType(Project project, TableColumnRefType ref){
        DbObject[] objects = PluginKeys.OBJECT_CACHE.getData(project).findByNameForType(ObjectCache.TABLE, ref.table);

        if (objects.length == 1 && objects[0] instanceof TableDescriptor) {
            TableDescriptor tdesc = (TableDescriptor) objects[0];
            Type t = tdesc.getColumnType(ref.column);
            if(t != null){
                return t;
            } else {
                throw new TypeNotResolvedException("Table " + ref.table + " has no column " + ref.column);
            }
        } else {
            throw new TypeNotResolvedException("Table not found: " + ref.table);
        }
    }

    public static int evaluate(PsiElement psi, Type l, Type r, int op) {

        String key = l.typeId() + ":" + r.typeId(); //l + ":" + r;
        OperationHolder holder = pp.get(key);
        if (holder != null ){
            return holder.resultTypeForOp(op);
        } else if( l.typeId() == Type.TABLE_COLUMN_REF_TYPE || r.typeId() == Type.TABLE_COLUMN_REF_TYPE ){

            Type _l, _r;
            if( l.typeId() == Type.TABLE_COLUMN_REF_TYPE){
                _l = getRealType(psi.getProject(), (TableColumnRefType) l); //((TableColumnRefType) l).getRealType();
            } else {
                _l = l;
            }

            if( r.typeId() == Type.TABLE_COLUMN_REF_TYPE){
                _r = getRealType(psi.getProject(), (TableColumnRefType) r); //_r = ((TableColumnRefType) r).getRealType();
            } else {
                _r = r;
            }

            return evaluate(psi, _l, _r, op);
        } else {
            return Type.UNKNOWN;
        }
    }

    public static boolean typesEquivalented(List<Integer> l ){
        for(int k=0; k<equaval.size(); k++){
            Set<Integer> s = equaval.get(k);
            int j=0;
            for(; j<l.size(); j++){
                int i = l.get(j);
                if( !s.contains(i)){
                    break;
                }
            }

            if(j == l.size()){
                return true;
            }
        }
        return false;
    }

    public static boolean canBeAssigned(int left, int right) {
        String key = left + ":" + right;
        OperationHolder holder = pp.get(key);
        if (holder != null ){
            return holder.resultTypeForOp(PLSqlTokenTypes.ASSIGNMENT_OP) != Type.UNKNOWN;
        } else {
            return false;
        }
    }

    public static boolean canBeAssigned(PsiElement psi, Type t, Type t1) {
        String key = t.typeId() + ":" + t1.typeId();
        OperationHolder holder = pp.get(key);
        if (holder != null ){
            return holder.resultTypeForOp(PLSqlTokenTypes.ASSIGNMENT_OP) != Type.UNKNOWN;
        } else if( t1.typeId() == Type.USER_DEFINED && t.typeId() == t1.typeId()){
            // NOTE: User Defined Type is expected to be a FQN - <package_name>.<type_name> 
            return t.typeName().equalsIgnoreCase(t1.typeName());
        } else if( t1.typeId() == Type.ROWTYPE && t.typeId() == t1.typeId() ){
            return ((RowtypeType)t).getTableName().equalsIgnoreCase(((RowtypeType)t1).getTableName());
        } else if( t1.typeId() == Type.TABLE_COLUMN_REF_TYPE || t.typeId() == Type.TABLE_COLUMN_REF_TYPE ){ //&& t.typeId() == t1.typeId() ){

            Type l, r;
            if( t.typeId() == Type.TABLE_COLUMN_REF_TYPE){
                l = getRealType(psi.getProject(), (TableColumnRefType) t); //((TableColumnRefType) t).getRealType();
            } else {
                l = t;
            }

            if( t1.typeId() == Type.TABLE_COLUMN_REF_TYPE){
                r = getRealType(psi.getProject(), (TableColumnRefType) t1); //((TableColumnRefType) t1).getRealType();
            } else {
                r = t1;
            }

            String key2 = l.typeId() + ":" + r.typeId();
            OperationHolder holder2 = pp.get(key2);

            return holder2 != null && holder2.resultTypeForOp(PLSqlTokenTypes.ASSIGNMENT_OP) != Type.UNKNOWN;
        } else if( t1.typeId() == Type.NULL ){
            // todo - it is supposed NULL can be assigned to any type (should be reviewed)
            return true;
        } else {
            return false;
        }
    }

//    public static Type evaluate(Type l, Type r, int type) {
//        int resultType = Type.UNKNOWN;
//        try {
//            resultType = TypeValidationHelper
//                .evaluate( l.typeId(), r.typeId(), type
//                );
//        } catch(Throwable e){
//        }
//
//        if(resultType != Type.UNKNOWN ){
//            return TypeFactory.createTypeById(resultType);
//        } else {
//            throw new TypeCastException("Operation ... is not applicable for " + l.typeName() + " and " + r.typeName() + " types");
//        }
//    }


    static class OperationHolder {

        Pair[] operations = new Pair[0];
        int result;

        public OperationHolder(int result) {
            this.result = result;
        }

        OperationHolder add(int op) {
            Pair[] t = new Pair[operations.length + 1];
            System.arraycopy(operations, 0, t, 0, operations.length);
            t[operations.length] = new Pair(op, result);
            operations = t;
            return this;
        }

        OperationHolder add(int op, int resultType) {
            Pair[] t = new Pair[operations.length + 1];
            System.arraycopy(operations, 0, t, 0, operations.length);
            t[operations.length] = new Pair(op, resultType);
            operations = t;
            return this;
        }

//        public boolean contains(int op) {
//            for (Pair _op : operations) {
//                if (_op.op == op) {
//                    return true;
//                }
//            }
//            return false;
//        }

        public int resultTypeForOp(int op) {
            for (Pair _op : operations) {
                if (_op.op == op) {
                    return _op.type;
                }
            }
            return Type.UNKNOWN;
        }

        public int resultType() {
            return result;
        }
    }

    static class Pair {
        public int op;
        public int type;

        public Pair(int op, int type){
            this.op = op;
            this.type = type;
        }
    }

    static String pair2string(int l, int r) {
        return l + ":" + r;
    }
}
