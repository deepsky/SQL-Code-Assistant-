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

package com.deepsky.lang.plsql.psi.utils;

import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.resolver.utils.ArgumentSpec;
import com.deepsky.lang.plsql.struct.FunctionDescriptor;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.ExecutableDescriptor;

import java.awt.*;

public class Formatter {

    public static String sql2htmlBase(String element){
        String text = element.trim();
        text = text.replace("\n", "<br>");
        return "<html>" + text + "</html>";
    }

    public static String sql2htmlBase(String element, int maxWidth, int maxHeight){
        String text = element.trim();
        String[] parts = text.split("\n");
        StringBuilder bld = new StringBuilder();
        for(int i =0;i<parts.length; i++){
            boolean truncated = false;
            if(parts[i].length() > maxWidth){
                parts[i] = parts[i].substring(0, maxWidth-4) + " ...";
                truncated = true;
            }
            if(i+1 == maxHeight && i+1 < parts.length){
                if(!truncated){
                    parts[i] = parts[i] + " ...";
                }
                if(i>0) bld.append("<br>");
                bld.append(parts[i]);
                break;
            }
            if(i>0) bld.append("<br>");
            bld.append(parts[i]);
        }

        return "<html>" + bld.toString() + "</html>";
    }

    /**
     * Building the argument list for an executable object based on DEFINITION of the executable object
     * @param exec
     * @return - argument spec
     */
    public static String formatArgList(Executable exec) {
        return formatArgList(exec, true);
    }

    public static String formatArgList(Executable exec, boolean encloseInParen) {
        Argument[] args = exec.getArguments();
        return argument2str(args, encloseInParen);
    }

    /**
     * Building the argument list for an executable object based on DEFINITION of the executable object
     * and using argument names
     * @param exec
     * @return - argument spec
     */
    public static String formatArgListLong(Executable exec, boolean encloseInParen) {
        Argument[] args = exec.getArguments();
        return argument2strLong(args, encloseInParen);
    }

    /**
     * Building the argument list for an executable object based on SPECIFICATION of the executable object
     * @param exec
     * @return - argument spec
     */
    public static String formatArgList(ExecutableSpec exec, boolean encloseInParen) {
        ArgumentList alist = exec.getArgumentList();
        if(alist != null){
            Argument[] args = alist.getArguments();
            return argument2str(args, encloseInParen);
        }
        return "";
    }

    public static String formatArgList(ExecutableSpec exec) {
        return formatArgList(exec, true);
    }

    private static String argument2str(Argument[] args){
        return argument2str(args, true);
    }

    private static String argument2str(Argument[] args, boolean encloseInParen){
        StringBuilder bld = new StringBuilder();
        for(Argument a: args){
            if(bld.length() > 0){
                bld.append(", ");
            }
            bld.append(a.getType().typeName().toLowerCase());
            if(a.isIn()){
                bld.append(" ").append("in");
            }

            if(a.isOut()){
                bld.append(" ").append("out");
            }
        }

        if(encloseInParen){
            return bld.length()>0? "(" + bld.toString() + ")": "";
        } else {
            return bld.length()>0? bld.toString(): "";
        }
    }

    public static String argument2strLong(Argument[] args, boolean encloseInParen){
        StringBuilder bld = new StringBuilder();
        for(Argument a: args){
            if(bld.length() > 0){
                bld.append(", ");
            }
            bld.append(a.getType().typeName().toLowerCase());
            if(a.isIn()){
                bld.append(" ").append("in");
            }

            if(a.isOut()){
                bld.append(" ").append("out");
            }
            bld.append(" ").append(a.getArgumentName());
        }

        if(encloseInParen){
            return bld.length()>0? "(" + bld.toString() + ")": "";
        } else {
            return bld.length()>0? bld.toString(): "";
        }
    }

    public static String formatArgList(ExecutableDescriptor dbo) {
        StringBuilder bld = new StringBuilder();
        for(String name: dbo.getArgumentNames()){
            Type t = dbo.getArgumentType(name);
            if(bld.length() > 0){
                bld.append(", ");
            }

            bld.append(name).append(" ").append(t.typeName());
        }

        return bld.length()>0? "(" + bld.toString() + ")": "";
    }

    private static String formatHtmlBasedArgList(ExecutableDescriptor dbo) {
        StringBuilder bld = new StringBuilder();
        for(String name: dbo.getArgumentNames()){
            Type t = dbo.getArgumentType(name);
            if(bld.length() > 0){
                bld.append(", ");
            }

            bld.append(name).append(" ").append("<b>").append(t.typeName()).append("</b>");
        }

        return bld.length()>0? "(" + bld.toString() + ")": "";
    }

    public static String formatHtmlBasedSignature(ExecutableDescriptor dbo) {

        String argList = formatHtmlBasedArgList(dbo);

        String out;
        if(dbo instanceof FunctionDescriptor){
            FunctionDescriptor f = (FunctionDescriptor) dbo;
            out = "<html>" + dbo.getName() + argList + ":<b>" + f.getReturnType().typeName() + "</b>";
        } else {
            out = "<html>" + dbo.getName() + argList;
        }

        return out.toLowerCase();
    }

    public static String formatHtmlBasedDbSchema(String schemaName) {

//        String out = "<html><font color=green>" + schemaName + "</font>";
        String out = "<html><b>" + schemaName + "</b>";
        return out.toLowerCase();
    }


    /**
     * Building the argument list for an executable object based on REAL ARGUMENT TIPES of the call,
     * just types, no argument names
     * @param alist
     * @return - argument spec
     */
    public static String formatArgList(CallArgument[] alist) {
        StringBuilder bld = new StringBuilder();
        for(CallArgument arg: alist){
            if(bld.length() > 0){
                bld.append(", ");
            }
            bld.append(arg.getExpression().getExpressionType().typeName().toLowerCase());
        }

        return bld.length()>0? "(" + bld.toString() + ")": "";
    }


    public static String formatSignature(Callable exec) {
        String argList = formatArgList(exec.getCallArguments());
        String out= exec.getFunctionName() + argList;
        return out.toLowerCase();
    }


    public static String formatSignature(Executable exec) {
        String argList = formatArgList(exec);
        String out = exec.getEName() + argList;

        if(exec instanceof Function){
            String returnType = ((Function)exec).getReturnType().typeName().toLowerCase();
            out += ":" + returnType;
        } else {
            // procedure
        }
        return out.toLowerCase();
    }

    public static String formatSignature(Executable exec, int maxSize) {
        String argList = formatArgList(exec);
        String out = exec.getEName() + argList;

        if(out.length() > maxSize && maxSize > 4){
            // cutoff
            out = out.substring(0, maxSize-4) + " ..)";
        }

        if(exec instanceof Function){
            String returnType = ((Function)exec).getReturnType().typeName().toLowerCase();
            out += ":" + returnType;
        } else {
            // procedure
        }
        return out.toLowerCase();
    }

    public static String formatSignature(ExecutableSpec exec) {
        String argList = formatArgList(exec);
        String out = exec.getEName() + argList;

        if(exec instanceof FunctionSpec){
            String returnType = ((FunctionSpec)exec).getReturnType().typeName().toLowerCase();
            out += ":" + returnType;
        } else {
            // procedure
        }
        return out.toLowerCase();
    }


    public static String formatSignature(ExecutableSpec exec, int maxSize) {
        String argList = formatArgList(exec);
        String out = exec.getEName() + argList;

        if(out.length() > maxSize && maxSize > 4){
            // cutoff
            out = out.substring(0, maxSize-4) + " ..)";
        }

        if(exec instanceof FunctionSpec){
            String returnType = ((FunctionSpec)exec).getReturnType().typeName(); //.toLowerCase();
            out += ":" + returnType;
        } else {
            // procedure
        }
        return out; //.toLowerCase();
    }

    public static String formatSqlStatement(SelectStatement select){
        GenericTable[] tabs = select.getFromClause().getTableList();
        String tabList = "";
        for (int i = 0; i < 3 && i < tabs.length; i++) {
            if (i != 0) {
                tabList = tabList + ",";
            }
            String tabName = "";
            if (tabs[i] instanceof TableAlias) {
                tabName = ((TableAlias) tabs[i]).getTableName().toUpperCase();
            } else {
                tabName = "SUBQUERY";
            }

            tabList = tabList + tabName;
            if (i > 3) {
                tabList = tabList + " ...";
                break;
            }
        }
        return "SELECT [from " + tabList + "]";
    }

    public static String formatSqlStatement(InsertStatement insert){
        String tab = insert.getIntoTable().getTableName();
        return "INSERT [into " + tab.toUpperCase() + "]";
    }

    public static String formatSqlStatement(DeleteStatement delete){
        String tab = delete.getTargetTable().getTableName();
        return "DELETE [table " + tab.toUpperCase() + "]";
    }

    public static String formatSqlStatement(UpdateStatement update){
        String tab = update.getTargetTable().getTableName();
        return "UPDATE [table " + tab.toUpperCase() + "]";
    }

    public static String formatFunctionSignature(String name, ArgumentSpec[] args, Type type) {
        String argString = formatArgList(args);
        String out = name + argString + ":" + type.typeName();
        return out.toLowerCase();
    }

    public static String formatFunctionSignature2(String name, ArgumentSpec[] args, Type type) {
        String argString = formatArgListTypeOnly(args);
        String out = name + argString + ":" + type.typeName();
        return out.toLowerCase();
    }

    public static String formatFunctionSignature(String name, ArgumentSpec[] args, Type type, int maxLength) {
        String argString = formatArgList(args);
        String typeName = type.typeName();
        String out = name + argString + ":" + typeName;
        if(maxLength > out.length()){
            return out.toLowerCase();
        } else {
            int argLen = maxLength - name.length() - typeName.length();
            return (name + formatArgListTypeOnly(args, argLen) + ":" + typeName).toLowerCase();
        }
    }

    public static String formatFunctionSignatureHtmlBased(String name, ArgumentSpec[] args, Type type) {
        String argString = formatArgListHtmlBased(args);
        String out = "<html>" + name + argString + ":<b>" + type.typeName() + "</b>";
        return out.toLowerCase();
    }

    public static String formatFunctionSignatureHtmlBased(String name, Color c, ArgumentSpec[] args, Type type) {
        String argString = formatArgListHtmlBased(args);
        String out = "<html><font color=\"" + endcodeColor(c) + "\">" + name + argString + ":<b>" + type.typeName() + "</b></font>";
        return out.toLowerCase();
    }


    private static String endcodeColor(Color c){
        return "#" + Integer.toHexString(c.getRed())
                + Integer.toHexString(c.getGreen())
                + Integer.toHexString(c.getBlue());
    }

    public static String formatProcedureSignature(String name, ArgumentSpec[] args) {
        String argString = formatArgList(args);
        String out = name + argString;
        return out.toLowerCase();
    }

    public static String formatProcedureSignature2(String name, ArgumentSpec[] args) {
        String argString = formatArgListTypeOnly(args);
        String out = name + argString;
        return out.toLowerCase();
    }

    public static String formatProcedureSignature(String name, ArgumentSpec[] args, int maxLength) {
        String argString = formatArgList(args);
        String out = name + argString;
        if(maxLength > out.length()){
            return out.toLowerCase();
        } else {
            int argLen = maxLength - name.length();
            return (name + formatArgListTypeOnly(args, argLen)).toLowerCase();
        }
    }

    public static String formatProcedureSignatureHtmlBased(String name, ArgumentSpec[] args) {
        String argString = formatArgListHtmlBased(args);
        String out = "<html>" + name + argString;
        return out.toLowerCase();
    }

    public static String formatProcedureSignatureHtmlBased(String name, Color c, ArgumentSpec[] args) {
        String argString = formatArgListHtmlBased(args);
        String out = "<html><font color=\"" + endcodeColor(c) + "\">" + name + argString + "</font>";
        return out.toLowerCase();
    }

    public static String formatTextHtmlBased(String name, Color c) {
        String out = "<html><font color=\"" + endcodeColor(c) + "\">" + name + "</font>";
        return out.toLowerCase();
    }

    public static String formatArgList(ArgumentSpec[] args, boolean doEncloseInParen) {
        StringBuilder bld = new StringBuilder();
        for(ArgumentSpec arg: args){
            Type t = arg.getType();
            if(bld.length() > 0){
                bld.append(", ");
            }

            bld.append(arg.getName()).append(" ").append(t.typeName().toUpperCase());
        }

        if(doEncloseInParen){
            return bld.length()>0? "(" + bld.toString() + ")": "";
        } else {
            return bld.length()>0? bld.toString(): "";
        }
    }

    public static String formatArgList(ArgumentSpec[] args) {
        return formatArgList(args, true);
    }


    public static String formatArgListTypeOnly(ArgumentSpec[] args) {
        StringBuilder bld = new StringBuilder();
        for(ArgumentSpec arg: args){
            Type t = arg.getType();
            if(bld.length() > 0){
                bld.append(", ");
            }

            bld.append(t.typeName());
        }

        return bld.length()>0? "(" + bld.toString() + ")": "";
    }

    private static String formatArgList(ArgumentSpec[] args, int maxLength) {
        StringBuilder bld = new StringBuilder();
        for(ArgumentSpec arg: args){
            Type t = arg.getType();
            if(bld.length() > 0){
                bld.append(", ");
            }

            bld.append(arg.getName()).append(" ").append(t.typeName());
        }

        if(bld.length() == 0){
            return "";
        } else if(bld.length() < maxLength){
            return "(" + bld.toString() + ")";
        } else {
            // bld.length() > maxLength
            if(maxLength > "( .. )".length()){
                return "(" + bld.toString().substring(0, maxLength-5) + " .. )";
            } else {
                return "( .. )";
            }
        }
    }

    private static String formatArgListTypeOnly(ArgumentSpec[] args, int maxLength) {
        StringBuilder bld = new StringBuilder();
        for(ArgumentSpec arg: args){
            Type t = arg.getType();
            if(bld.length() > 0){
                bld.append(", ");
            }

            bld.append(t.typeName());
        }

        if(bld.length() == 0){
            return "";
        } else if(bld.length() < maxLength){
            return "(" + bld.toString() + ")";
        } else {
            // bld.length() > maxLength
            if(maxLength > "( .. )".length()){
                return "(" + bld.toString().substring(0, maxLength-5) + " .. )";
            } else {
                return "( .. )";
            }
        }
    }

    public static String formatArgListHtmlBased(ArgumentSpec[] args) {
        StringBuilder bld = new StringBuilder();
        for(ArgumentSpec arg: args){
            Type t = arg.getType();
            if(bld.length() > 0){
                bld.append(", ");
            }

            bld.append(arg.getName()).append(" <b>").append(t.typeName()).append("</b>");
        }

        return bld.length()>0? "(" + bld.toString() + ")": "";
    }
}
