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

package com.deepsky.navigation;

import com.deepsky.lang.plsql.struct.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DbObjectUtil {

    static private Pattern pkg_obj_rest = Pattern.compile("([a-zA-Z0-9_\\$]*):([a-zA-Z0-9_ ]*)\\.([a-zA-Z0-9_\\$]*):([a-zA-Z0-9_ ]*)\\?([a-zA-Z0-9_\\$:\\.!,]*)");
    static private Pattern args_ret_type = Pattern.compile("([a-zA-Z0-9_:\\.\\$,]*)!([a-zA-Z0-9_]*)");

    // any object except function/procedure
    // [pkg-name]:[pkg-type].[object-name]:[object-type]?

    // procedure case
    // [pkg-name]:[pkg-type].[object-name]:[object-type]?[arg-name]:[arg-type],...,[arg-name]:[arg-type]

    // function case
    // [pkg-name]:[pkg-type].[object-name]:[object-type]?[arg-name]:[arg-type],...,[arg-name]:[arg-type]![ret-type]

    // converting DbObject to UID and vice verse
    public static String dbo2UID(DbObject dbo) {
        StringBuilder bld = new StringBuilder();
        DbObject root = dbo.getRootContext();
        if (root == dbo) {
            // not package scope
            bld.append(":").append(".");
        } else if (root instanceof PackageBodyDescriptor) {
            bld.append(root.getName().toLowerCase()).append(":").append(DbObject.PACKAGE_BODY).append(".");
        } else if (root instanceof PackageDescriptor) {
            bld.append(root.getName().toLowerCase()).append(":").append(DbObject.PACKAGE).append(".");
        } else {
            // todo -- looks strange
            bld.append(":").append(".");
        }

        bld.append(dbo.getName().toLowerCase()).append(":").append(dbo.getTypeName()).append("?");
        if (dbo instanceof FunctionDescriptor) {
            FunctionDescriptor f = (FunctionDescriptor) dbo;
            bld.append(encodeArgs(f)).append("!").append(f.getReturnType().typeName());
        } else if (dbo instanceof ProcedureDescriptor) {
            bld.append(encodeArgs((ExecutableDescriptor) dbo)).append("!");
        } else {
        }

        return bld.toString();
    }


    private static String encodeArgs(ExecutableDescriptor edesc) {
        StringBuilder bld = new StringBuilder();
        for (String name : edesc.getArgumentNames()) {
            Type t = edesc.getArgumentType(name);
            if (bld.length() > 0) {
                bld.append(",");
            }

            bld.append(name.toLowerCase()).append(":").append(t.typeName());
        }
        return bld.toString();
    }

    public static ArgumentSpec[] extractArguments(String uid) {
        Matcher m = pkg_obj_rest.matcher(uid);
        if (m.find()) {
            if (m.groupCount() == 5) {
                String last = m.group(5);
                if (last.length() > 0) {
                    Matcher m2 = args_ret_type.matcher(last);
                    if (m2.find() && m2.groupCount() > 0) {
                        String args = m2.group(1);
                        if(args.length() > 0){
                            String[] pairs = m2.group(1).split(",");
                            ArgumentSpec[] out = new ArgumentSpec[pairs.length];
                            for(int i =0; i<pairs.length; i++){
                                String[] name_type = pairs[i].split(":");
                                out[i] = new ArgumentSpecImpl(name_type[0], TypeFactory.createTypeByName(name_type[1]));    
                            }
                            return out;
                        }
                    }
                }
            }
        }

        return new ArgumentSpec[0];
    }

    public static String extractObjectName(String uid) {
        Matcher m = pkg_obj_rest.matcher(uid);
        if (m.find()) {
            if (m.groupCount() > 4) {
                return m.group(3);
            }
        }

        return null;
    }

    public static String extractObjectType(String uid) {
        Matcher m = pkg_obj_rest.matcher(uid);
        if (m.find()) {
            if (m.groupCount() > 4) {
                return m.group(4);
            }
        }

        return null;
    }

    public static String extractPackageName(String uid) {
        Matcher m = pkg_obj_rest.matcher(uid);
        if (m.find()) {
            if (m.groupCount() > 2) {
                String text = m.group(1);
                return text.length() > 0 ? text : null;
            }
        }

        return null;
    }

    public static String extractPackageType(String uid) {
        Matcher m = pkg_obj_rest.matcher(uid);
        if (m.find()) {
            if (m.groupCount() > 2) {
                String text = m.group(2);
                return text.length() > 0 ? text : null;
            }
        }

        return null;
    }

    public static Type extractFuncReturnType(String uid) {
        Matcher m = pkg_obj_rest.matcher(uid);
        if (m.find()) {
            if (m.groupCount() == 5) {
                String last = m.group(5);
                if (last.length() > 0) {
                    Matcher m2 = args_ret_type.matcher(last);
                    if (m2.find() && m2.groupCount() == 2) {
                        String type = m2.group(2);
                        if(type.length() > 0){
                            return TypeFactory.createTypeByName(type);
                        }
                    }
                }
            }
        }

        return null;
    }

    public static String createUID(String name, String type) {
        return ":." + name.toLowerCase() + ":" + type.toUpperCase() + "?";
    }

    public interface ArgumentSpec {
        String getName();
        Type getType();
    }

    private static class ArgumentSpecImpl implements ArgumentSpec {
        String name;
        Type type;
        public ArgumentSpecImpl(String name, Type type){
            this.name = name;
            this.type = type;
        }
        public String getName() {
            return name;
        }

        public Type getType() {
            return type;
        }
    }

}
