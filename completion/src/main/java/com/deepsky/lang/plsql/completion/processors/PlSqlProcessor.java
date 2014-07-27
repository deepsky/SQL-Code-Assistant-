/*
 * Copyright (c) 2009,2014 Serhiy Kulyk
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *      1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
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

package com.deepsky.lang.plsql.completion.processors;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.plsql.completion.SyntaxTreePath;
import com.deepsky.lang.plsql.completion.lookups.KeywordLookupElement;
import com.deepsky.lang.plsql.completion.lookups.dml.DeleteLookupElement;
import com.deepsky.lang.plsql.completion.lookups.dml.InsertLookupElement;
import com.deepsky.lang.plsql.completion.lookups.dml.UpdateLookupElement;
import com.deepsky.lang.plsql.completion.lookups.plsql.FunctionLookupElement;
import com.deepsky.lang.plsql.completion.lookups.plsql.PlSqlBlockLookupElement;
import com.deepsky.lang.plsql.completion.lookups.plsql.CursorLookupElement;
import com.deepsky.lang.plsql.completion.lookups.plsql.ProcedureLookupElement;
import com.intellij.lang.ASTNode;

public class PlSqlProcessor extends CompletionBase {

//    @SyntaxTreePath("/..#ANONYM_PLSQL_BLOCK/#PLSQL_BLOCK//..#STATEMENT_LIST/..#PROCEDURE_CALL/#CALLABLE_NAME_REF/#EXEC_NAME_REF/#C_MARKER")
//    public void startInsideBlock(C_Context ctx) {
//        //ctx.addElement(SelectLookupElement.createSelectInto());
//        ctx.addElement(UpdateLookupElement.create());
//        ctx.addElement(DeleteLookupElement.create());
//        ctx.addElement(InsertLookupElement.create());
//
//        ctx.addElement(KeywordLookupElement.create("commit", false, true));
//        ctx.addElement(KeywordLookupElement.create("rollback", false, true));
//
//        ctx.addElement(AnonymousPlSqlBlockLookupElement.create());
//    }

    @SyntaxTreePath("//..1#PLSQL_BLOCK/..#BEGIN #STATEMENT_LIST/..#PROCEDURE_CALL/#CALLABLE_NAME_REF/#EXEC_NAME_REF/#C_MARKER")
    public void startInsideBlock(C_Context ctx, ASTNode plsqlBlock) {
        //ctx.addElement(SelectLookupElement.createSelectInto());
        ctx.addElement(UpdateLookupElement.create());
        ctx.addElement(DeleteLookupElement.create());
        ctx.addElement(InsertLookupElement.create());

        ctx.addElement(KeywordLookupElement.create("commit", false, true));
        ctx.addElement(KeywordLookupElement.create("rollback", false, true));

        ctx.addElement(PlSqlBlockLookupElement.create(false));
    }

    @SyntaxTreePath("//..1#PLSQL_BLOCK/..#ERROR_TOKEN_A/#BEGIN #STATEMENT_LIST/#ERROR_TOKEN_A/#C_MARKER")
    public void startBlock(C_Context ctx, ASTNode plsqlBlock) {
        //ctx.addElement(SelectLookupElement.createSelectInto());
        ctx.addElement(UpdateLookupElement.create());
        ctx.addElement(DeleteLookupElement.create());
        ctx.addElement(InsertLookupElement.create());

        ctx.addElement(KeywordLookupElement.create("commit", false, true));
        ctx.addElement(KeywordLookupElement.create("rollback", false, true));

        ctx.addElement(PlSqlBlockLookupElement.create(false));
        ctx.addElement(KeywordLookupElement.create("end", false, true));
    }

    @SyntaxTreePath("//..#PLSQL_BLOCK/..#DECLARE_LIST/..1#ERROR_TOKEN_A/#VARIABLE_NAME #TYPE_NAME_REF/#NAME_FRAGMENT/#C_MARKER")
    public void varTypeInDeclare(C_Context ctx, ASTNode varDecl) {
        this.collectTypeNames(ctx, !(varDecl.getLastChildNode().getElementType() == PlSqlTokenTypes.SEMI));
        ctx.addElement(CursorLookupElement.create());
    }

    @SyntaxTreePath("//..#PLSQL_BLOCK/..#DECLARE_LIST/..1#VARIABLE_DECLARATION/#VARIABLE_NAME #TYPE_NAME_REF/#NAME_FRAGMENT/#C_MARKER")
    public void varTypeInDeclare2(C_Context ctx, ASTNode varDecl) {
        this.collectTypeNames(ctx, !(varDecl.getLastChildNode().getElementType() == PlSqlTokenTypes.SEMI));
        ctx.addElement(CursorLookupElement.create());
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////
    //////////   PACKAGE/PACKAGE BODY
    /////////////////////////////////////////////////////////////////////////////////////////////////

    @SyntaxTreePath("/..#ERROR_TOKEN_A/#CREATE #OR #REPLACE #PACKAGE #IDENTIFIER #C_MARKER")
    public void createOrReplacePackage(C_Context ctx) {
        ctx.addElement(KeywordLookupElement.create("is", true, false));
        ctx.addElement(KeywordLookupElement.create("as", true, false));
    }

    @SyntaxTreePath("/..#ERROR_TOKEN_A/#CREATE #PACKAGE #IDENTIFIER #C_MARKER")
    public void createPackage(C_Context ctx) {
        ctx.addElement(KeywordLookupElement.create("is", true, false));
        ctx.addElement(KeywordLookupElement.create("as", true, false));
    }

    @SyntaxTreePath("/..#PACKAGE_SPEC/#CREATE #PACKAGE #PACKAGE_NAME/#C_MARKER")
    public void packageName(C_Context ctx) {
        // TODO generate package spec name
    }

    @SyntaxTreePath("/..#PACKAGE_BODY/#CREATE #PACKAGE #BODY #PACKAGE_NAME/#C_MARKER")
    public void packageBodyName(C_Context ctx) {
        // TODO generate package body name
    }

    @SyntaxTreePath("/..#PACKAGE_BODY/#CREATE ..#ERROR_TOKEN_A/#PACKAGE #BODY #IDENTIFIER #C_MARKER")
    public void packageBody(C_Context ctx) {
        ctx.addElement(KeywordLookupElement.create("as", true, false));
        ctx.addElement(KeywordLookupElement.create("is", true, false));
    }

    @SyntaxTreePath("/..#PACKAGE_BODY/#CREATE ..#ERROR_TOKEN_A/#PACKAGE #BODY 1#PACKAGE_NAME #AS #C_MARKER")
    public void packageBodyAsFuncProc(C_Context ctx, ASTNode pkgName) {
        ctx.addElement(ProcedureLookupElement.createBody(pkgName.getText()));
        ctx.addElement(FunctionLookupElement.createBody(pkgName.getText()));

        ctx.addElement(ProcedureLookupElement.createSpec(pkgName.getText()));
        ctx.addElement(FunctionLookupElement.createSpec(pkgName.getText()));
    }

    @SyntaxTreePath("/..#PACKAGE_BODY/#CREATE ..#ERROR_TOKEN_A/#PACKAGE #BODY 1#PACKAGE_NAME #IS #C_MARKER")
    public void packageBodyIsFuncProc(C_Context ctx, ASTNode pkgName) {
        ctx.addElement(ProcedureLookupElement.createBody(pkgName.getText()));
        ctx.addElement(FunctionLookupElement.createBody(pkgName.getText()));

        ctx.addElement(ProcedureLookupElement.createSpec(pkgName.getText()));
        ctx.addElement(FunctionLookupElement.createSpec(pkgName.getText()));
    }

    @SyntaxTreePath("/..#PACKAGE_BODY/#CREATE ..#BODY 1#PACKAGE_NAME #AS ..#ERROR_TOKEN_A/#C_MARKER")
    public void packageBodyAsFuncProc2(C_Context ctx, ASTNode pkgName) {
        ctx.addElement(ProcedureLookupElement.createBody(pkgName.getText()));
        ctx.addElement(FunctionLookupElement.createBody(pkgName.getText()));

        ctx.addElement(ProcedureLookupElement.createSpec(pkgName.getText()));
        ctx.addElement(FunctionLookupElement.createSpec(pkgName.getText()));
    }

    @SyntaxTreePath("/..#PACKAGE_BODY/..#PACKAGE_INIT_SECTION/#BEGIN #STATEMENT_LIST/..#PROCEDURE_CALL/#CALLABLE_NAME_REF/#EXEC_NAME_REF/#C_MARKER")
    public void startInPackageBodyInitSection(C_Context ctx) {
        //ctx.addElement(SelectLookupElement.createSelectInto());
        // TODO - check on the list of permissible statements in INIT section
        ctx.addElement(UpdateLookupElement.create());
        ctx.addElement(DeleteLookupElement.create());
        ctx.addElement(InsertLookupElement.create());

        ctx.addElement(KeywordLookupElement.create("commit", false, true));
        ctx.addElement(KeywordLookupElement.create("rollback", false, true));

        ctx.addElement(PlSqlBlockLookupElement.create(false));
    }


    @SyntaxTreePath("/..#PACKAGE_SPEC/#CREATE ..#ERROR_TOKEN_A/#PACKAGE 1#PACKAGE_NAME #AS #C_MARKER")
    public void packageAsFuncProc(C_Context ctx, ASTNode pkgName) {
        ctx.addElement(ProcedureLookupElement.createSpec(pkgName.getText()));
        ctx.addElement(FunctionLookupElement.createSpec(pkgName.getText()));
    }

    @SyntaxTreePath("/..#PACKAGE_SPEC/#CREATE ..#ERROR_TOKEN_A/#PACKAGE 1#PACKAGE_NAME #IS #C_MARKER")
    public void packageIsFuncProc(C_Context ctx, ASTNode pkgName) {
        ctx.addElement(ProcedureLookupElement.createSpec(pkgName.getText()));
        ctx.addElement(FunctionLookupElement.createSpec(pkgName.getText()));
    }

    @SyntaxTreePath("/..#PACKAGE_SPEC/#CREATE ..#PACKAGE 1#PACKAGE_NAME #AS ..#ERROR_TOKEN_A/#C_MARKER")
    public void packageAsFuncProc2(C_Context ctx, ASTNode pkgName) {
        ctx.addElement(ProcedureLookupElement.createSpec(pkgName.getText()));
        ctx.addElement(FunctionLookupElement.createSpec(pkgName.getText()));
    }

    @SyntaxTreePath("/..#PACKAGE_SPEC/#CREATE ..#PACKAGE 1#PACKAGE_NAME #IS ..#ERROR_TOKEN_A/#C_MARKER")
    public void packageIsFuncProc2(C_Context ctx, ASTNode pkgName) {
        ctx.addElement(ProcedureLookupElement.createSpec(pkgName.getText()));
        ctx.addElement(FunctionLookupElement.createSpec(pkgName.getText()));
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //////// Parameter Spec
    ////////////////////////////////////////////////////////////////////////////////////

    @SyntaxTreePath("//..#FUNCTION_BODY/..#ARGUMENT_LIST/..#PARAMETER_SPEC/#IDENTIFIER #TYPE_NAME_REF/#NAME_FRAGMENT/#C_MARKER")
    public void funcParameterType(C_Context ctx) {
        collectTypeNames(ctx, true, false);
   }

    @SyntaxTreePath("//..#FUNCTION_SPEC/..#ARGUMENT_LIST/..#PARAMETER_SPEC/#IDENTIFIER #TYPE_NAME_REF/#NAME_FRAGMENT/#C_MARKER")
    public void funcParameterTypeInSpec(C_Context ctx) {
        collectTypeNames(ctx, true, false);
    }

    @SyntaxTreePath("//..#PROCEDURE_BODY/..#ARGUMENT_LIST/..#PARAMETER_SPEC/#IDENTIFIER #TYPE_NAME_REF/#NAME_FRAGMENT/#C_MARKER")
    public void procParameterType(C_Context ctx) {
        collectTypeNames(ctx, true, false);
    }

    @SyntaxTreePath("//..#PROCEDURE_SPEC/..#ARGUMENT_LIST/..#PARAMETER_SPEC/#IDENTIFIER #TYPE_NAME_REF/#NAME_FRAGMENT/#C_MARKER")
    public void procParameterTypeInSpec(C_Context ctx) {
        collectTypeNames(ctx, true, false);
    }


    ////////////////////////////////////////////////////////////////////////////////////
    //////// Variable Decl
    ////////////////////////////////////////////////////////////////////////////////////

    @SyntaxTreePath("/..#PACKAGE_SPEC/..#PACKAGE_NAME #IS ..#VARIABLE_DECLARATION/#VARIABLE_NAME #TYPE_NAME_REF/#NAME_FRAGMENT/#C_MARKER")
    public void varDeclInPackageSpecIS(C_Context ctx) {
        collectTypeNames(ctx, true);
    }

    @SyntaxTreePath("/..#PACKAGE_SPEC/..#PACKAGE_NAME #AS ..#VARIABLE_DECLARATION/#VARIABLE_NAME #TYPE_NAME_REF/#NAME_FRAGMENT/#C_MARKER")
    public void varDeclInPackageSpecAS(C_Context ctx) {
        collectTypeNames(ctx, true);
    }

    @SyntaxTreePath("/..#PACKAGE_BODY/..#PACKAGE_NAME #IS ..#VARIABLE_DECLARATION/#VARIABLE_NAME #TYPE_NAME_REF/#NAME_FRAGMENT/#C_MARKER")
    public void varDeclInPackageBodyIS(C_Context ctx) {
        collectTypeNames(ctx, true);
    }

    @SyntaxTreePath("/..#PACKAGE_BODY/..#PACKAGE_NAME #AS ..#VARIABLE_DECLARATION/#VARIABLE_NAME #TYPE_NAME_REF/#NAME_FRAGMENT/#C_MARKER")
    public void varDeclInPackageBodyAS(C_Context ctx) {
        collectTypeNames(ctx, true);
    }
}
