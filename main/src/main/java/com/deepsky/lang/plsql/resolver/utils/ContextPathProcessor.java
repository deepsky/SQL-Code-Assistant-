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

package com.deepsky.lang.plsql.resolver.utils;

import com.deepsky.lang.plsql.resolver.ContextPath;

public class ContextPathProcessor {
    ContextPathVisitor visitor;

    public ContextPathProcessor(ContextPathVisitor visitor) {
        this.visitor = visitor;
    }

    public void process(String ctxPath) {
        ContextPathUtil.CtxPathIterator ite = new ContextPathUtil.CtxPathIterator(ctxPath);
        while(ite.next()){
            String currentCtxPath = ite.getPath();
            String name = ite.getName();
            int ctxType = ite.getType();
            switch (ctxType) {
                case ContextPath.FILE_CTX:
                    break;
                case ContextPath.PACKAGE_SPEC:
                    visitor.visitPackageSpec(currentCtxPath, ctxType, name);
                    break;
                case ContextPath.PACKAGE_BODY:
                    visitor.visitPackageBody(currentCtxPath, ctxType, name);
                    break;
                case ContextPath.TABLE_DEF:
                    break;
                case ContextPath.COLUMN_DEF:
                    break;
                case ContextPath.VARIABLE_DECL:
                    visitor.visitVariable(currentCtxPath, ctxType, name);
                    break;
                case ContextPath.ARGUMENT:
                    visitor.visitArgument(currentCtxPath, ctxType, name);
                    break;
                case ContextPath.COLLECTION_TYPE:
                case ContextPath.RECORD_TYPE:
                case ContextPath.VARRAY_TYPE:
                case ContextPath.OBJECT_TYPE:
                    break;
                case ContextPath.FUNCTION_SPEC:
                    visitor.visitFunctionSpec(currentCtxPath, ctxType, name);
                    break;
                case ContextPath.FUNCTION_BODY:
                    visitor.visitFunctionBody(currentCtxPath, ctxType, name);
                    break;
                case ContextPath.PROCEDURE_SPEC:
                    visitor.visitProcedureSpec(currentCtxPath, ctxType, name);
                    break;
                case ContextPath.PROCEDURE_BODY:
                    visitor.visitProcedureBody(currentCtxPath, ctxType, name);
                    break;
                case ContextPath.PLSQL_BLOCK:
                    visitor.visitPlSqlBlock(currentCtxPath, ctxType, name);
                    break;
                case ContextPath.SEQUENCE:
                    break;
                case ContextPath.VIEW_DEF:
                    break;
                case ContextPath.SYNONYM:
                    break;
                case ContextPath.CREATE_TRIGGER:
                    break;
                default:
                    break;
            }
        }
    }
}
