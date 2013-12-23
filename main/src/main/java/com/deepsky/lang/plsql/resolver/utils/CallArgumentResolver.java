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

import com.deepsky.lang.plsql.psi.Argument;
import com.deepsky.lang.plsql.psi.ArgumentList;
import com.deepsky.lang.plsql.psi.CallArgument;
import com.deepsky.lang.plsql.resolver.helpers.ExecutableResolveHelper;
import com.deepsky.lang.plsql.sqlIndex.ResolveHelper;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.types.RowtypeType;
import com.deepsky.lang.plsql.struct.types.TableColumnRefType;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.lang.validation.TypeValidationHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CallArgumentResolver {

    LoggerProxy log = LoggerProxy.getInstance("#CallArgumentResolver");

    ResolveHelper resolver;

    public CallArgumentResolver(ResolveHelper resolver) {
        this.resolver = resolver;
    }

    public boolean validate(@Nullable ArgumentList argList, CallArgument[] args, List<String> errors) {
        ArgumentSpec[] argSpec = convertArgumentList2Spec(argList);
        String ctxPath = argList==null? "": argList.getCtxPath1().getPath();
        return validate(argSpec, ctxPath, args, errors);
    }

    public boolean validate(ExecutableResolveHelper ehlp, CallArgument[] args, List<String> errors) {
        ArgumentSpec[] argList = ehlp.getArgumentSpecification();
        String ctxPath = ehlp.getCtxPath();

        return validate(argList, ctxPath, args, errors);
    }

    private boolean validate(ArgumentSpec[] argList, String argListCtxPath, CallArgument[] args, List<String> errors) {

        //ArgumentSpec[] argList = ehlp.getArgumentSpecification();
        ArgumentListHelper ahlp = new ArgumentListHelper(argList);

        if (args.length < ahlp.numberOfReqArguments()) {
            errors.add("Arguments less then expected");
            return false;
        } else if (args.length > argList.length) {
            errors.add("Too many arguments");
            return false;
        }

        if (args.length == 0) {
            if (ahlp.numberOfReqArguments() != 0) {
                errors.add("Arguments less then expected");
                return false;
            }
        } else {
            for (int i = 0; i < args.length; i++) {
                CallArgument arg = args[i];
                String var = arg.getParameterName();
                if (var != null) {
                    // binding parameter by name
                    ArgumentSpec a = ahlp.getArgumentByName(var);
                    if (a == null) {
                        errors.add("Parameter with name not found: " + var);
                        return false;
                    }
                    Type t = a.getType();
                    try {
                        if (!canBeAssigned(argListCtxPath, t, arg)) {
                            errors.add("Formal type and real type mismatched, parameter: " + i);
                            return false;
                        }
                    } catch (Throwable e) {
                        // continue;
                        //log.warn("Validation exception: " + e.getMessage());
                    }
                } else {
                    // ... by position
                    Type t = ahlp.getArgumentByPos(i).getType();
                    try {
                        if (!canBeAssigned(argListCtxPath, t, arg)) {
                            errors.add("Function: " + "!!!!!" + ", type of argument does not fit a formal parameter, position: " + (i + 1));
                            return false;
                        }
                    } catch (Throwable e) {
                        // continue;
                        //log.warn("Validation exception: " + e.getMessage());
                    }
                }
            }
        }

        return ahlp.numberOfReqArguments() == 0; //true;
    }

    private boolean canBeAssigned(String usageContext, Type t1, CallArgument arg) {
        Type t2 = arg.getExpression().getExpressionType();
        if(t2.typeId() == Type.NULL){
            // NULL can be assigned to any type
            return true;
        }
        if (t1.typeId() == Type.USER_DEFINED || t2.typeId() == Type.USER_DEFINED) {
            if (t1.typeId() != t2.typeId()) {
                return false;
            } else {
                UserDefinedType l = (UserDefinedType) t1;
                UserDefinedType r = (UserDefinedType) t2;
                if (l.getDefinitionPackage() == null && r.getDefinitionPackage() == null) {
                    return l.getTypeName2().equalsIgnoreCase(r.getTypeName2());
                } else if (l.getDefinitionPackage() != null && r.getDefinitionPackage() != null) {
                    return l.typeName().equalsIgnoreCase(r.typeName());
                } else {
                    // need type resolving
                    if (l.getDefinitionPackage() == null) {
                        // resolve l
                        l = resolver.resolveUDT(usageContext, l.getTypeName2());
                        return l != null && l.typeName().equalsIgnoreCase(r.typeName());
                    } else {
                        // resolve r
                        r = resolver.resolveUDT(arg.getCtxPath1().getPath(), r.getTypeName2());
                        return r != null && l.typeName().equalsIgnoreCase(r.typeName());
                    }
                }
            }
        } else if (t1.typeId() == Type.ROWTYPE || t2.typeId() == Type.ROWTYPE) {
            if (t1.typeId() != t2.typeId()) {
                return false;
            } else {
                RowtypeType l = (RowtypeType) t1;
                RowtypeType r = (RowtypeType) t2;
                return l.getTableName().equalsIgnoreCase(r.getTableName());
            }
        } else if (t1.typeId() == Type.TABLE_COLUMN_REF_TYPE || t2.typeId() == Type.TABLE_COLUMN_REF_TYPE) {
            Type l, r;
            if (t1.typeId() == Type.TABLE_COLUMN_REF_TYPE) {
                l = resolver.resolveType((TableColumnRefType) t1);
            } else {
                l = t1;
            }

            if (t2.typeId() == Type.TABLE_COLUMN_REF_TYPE) {
                r = resolver.resolveType((TableColumnRefType) t2);
            } else {
                r = t2;
            }

            return !(l == null || r == null) && TypeValidationHelper.canBeAssigned(l.typeId(), r.typeId());
        } else {
            return TypeValidationHelper.canBeAssigned(t1.typeId(), t2.typeId());
        }
    }

/*
    private class ArgumentListHelper {

        ArgumentSpec[] formalList;
        int numberOfReqArguments = -1;

        public ArgumentListHelper(ArgumentSpec[] formalList) {
            this.formalList = formalList;
        }

        public int numberOfReqArguments() {
            numberOfReqArguments = 0;
            for (ArgumentSpec a : formalList){
                if (a != null && !a.defaultExist()) numberOfReqArguments++;
            }
            return numberOfReqArguments;
        }

        public ArgumentSpec getArgumentByName(String name) {
            for (int i = 0; i < formalList.length; i++) {
                ArgumentSpec a = formalList[i];
                if (a != null && a.getName().equalsIgnoreCase(name)) {
                    formalList[i] = null;
                    return a;
                }
            }
            return null;
        }

        public ArgumentSpec getArgumentByPos(int pos) {
            for (int i = 0; i < formalList.length; i++) {
                ArgumentSpec a = formalList[i];
                if (a != null && i == pos) {
                    formalList[i] = null;
                    return a;
                }
            }
            return null;
        }

    }
*/

    private ArgumentSpec[] convertArgumentList2Spec(@Nullable ArgumentList argList) {
        if(argList == null){
            return new ArgumentSpec[0];    
        }
        Argument[] args = argList.getArguments();
        ArgumentSpec[] out = new ArgumentSpec[args.length];
        for(int i=0; i<args.length; i++){
            out[i] = new ArgumentSpecImpl(
                    args[i].getArgumentName(), args[i].getType(), args[i].getDefaultExpr() != null
            );
        }
        return out;
    }

    private class ArgumentSpecImpl implements ArgumentSpec {
        String name;
        Type type;
        boolean isDefault;

        public ArgumentSpecImpl(String name, Type type, boolean isDefault){
            this.name = name;
            this.type = type;
            this.isDefault = isDefault;
        }

        public String getName() {
            return name;
        }

        public Type getType() {
            return type;
        }

        public boolean defaultExist() {
            return isDefault;
        }
    }

}
