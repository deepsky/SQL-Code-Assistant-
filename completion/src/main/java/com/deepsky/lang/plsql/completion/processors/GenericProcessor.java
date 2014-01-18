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

import com.deepsky.lang.plsql.completion.SyntaxTreePath;
import com.intellij.lang.ASTNode;

public class GenericProcessor {

    @SyntaxTreePath("/#ERROR_TOKEN_A/1#C_MARKER")
    public void process$Start(C_Context context, ASTNode node) {
        // TODO - implement me
    }

    @SyntaxTreePath("/..#ERROR_TOKEN_A/#C_MARKER")
    public void process$Start2(C_Context context, ASTNode node) {
        // TODO - implement me
    }

    @SyntaxTreePath("/..#ERROR_TOKEN_A/ ..#SEMI #C_MARKER")
    public void process$Start3(C_Context context, ASTNode node) {
        // TODO - implement me
    }

    @SyntaxTreePath("/..#ERROR_TOKEN_A/ ..#ALTER_TABLE #C_MARKER")
    public void process$Start4(C_Context context, ASTNode node) {
        // TODO - implement me
    }

    @SyntaxTreePath("/..#ERROR_TOKEN_A/ #DELETE #FROM 1#TABLE_ALIAS ..#WHERE_CONDITION #C_MARKER")
    public void process$Start5(C_Context context, ASTNode node) {
        // TODO - implement me
    }

    @SyntaxTreePath("/..#ERROR_TOKEN_A/ #CREATE #SEQUENCE 1#SEQUENCE_NAME .. #C_MARKER")
    public void process$Start6(C_Context context, ASTNode node) {
        // TODO - implement me
    }

}

