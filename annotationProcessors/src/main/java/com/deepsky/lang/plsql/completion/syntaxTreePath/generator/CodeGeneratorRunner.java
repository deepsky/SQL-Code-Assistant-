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

package com.deepsky.lang.plsql.completion.syntaxTreePath.generator;

import antlr.RecognitionException;
import antlr.TokenStreamException;

public class CodeGeneratorRunner {


/*
    static String[] testSet = new String[]{
            "/#ERROR_TOKEN_A/1#C_MARKER",
            "/SelectStatement/..#EXPR_COLUMN/..#ALIAS_NAME/#ALIAS_IDENT/1#C_MARKER",
            "/SelectStatement/..1#TABLE_REFERENCE_LIST_FROM .. #ORDER #C_MARKER",
            "/SelectStatement/..#GROUP #C_MARKER",
            "/SelectStatement/#SELECT ..#GROUP #C_MARKER",
            "/#ERROR_TOKEN_A/#SELECT #C_MARKER",
            "/#ERROR_TOKEN_A/#SELECT #IDENTIFIER #C_MARKER",
            "/#UPDATE_COMMAND//#UPDATE..#TABLE_ALIAS/1#TABLE_REF",

            "//#SELECT #ASTERISK 1#C_MARKER",
            "//#ERROR_TOKEN_A/#SELECT ..#EXPR_COLUMN #COMMA #ERROR_TOKEN_A/#C_MARKER",
            "//#ERROR_TOKEN_A/#SELECT ..1#ERROR_TOKEN_A/#SUBQUERY_EXPR//#OPEN_PAREN #SELECT #C_MARKER",

            "//#SELECT ..#EXPR_COLUMN #COMMA #ERROR_TOKEN_A/#C_MARKER",
            "//#SELECT ..#EXPR_COLUMN #C_MARKER",

            "//..#EXPR_COLUMN//#SUBQUERY/..SelectStatement/..#TABLE_REFERENCE_LIST_FROM/..1#TABLE_ALIAS/#TABLE_REF/#C_MARKER",

            "//SelectStatement/..#TABLE_REFERENCE_LIST_FROM/..1#TABLE_ALIAS/#TABLE_REF/#C_MARKER",
            "//1$SelectStatement/..#TABLE_REFERENCE_LIST_FROM/..#TABLE_ALIAS/2#TABLE_REF #ALIAS_NAME//3#C_MARKER",
//            "//SelectStatement/..1#TABLE_REFERENCE_LIST_FROM ..2#ORDER_CLAUSE//#VAR_REF//#IDENTIFIER",
//            "//1$SelectStatement/..2#TABLE_REFERENCE_LIST_FROM 3#GROUP_CLAUSE/..#VAR_REF//#IDENTIFIER",
            "//..SelectStatement/..1#TABLE_REFERENCE_LIST_FROM ..2#ERROR_TOKEN_A/#ORDER #C_MARKER",
            "//SelectStatement/..1#TABLE_REFERENCE_LIST_FROM ..2#ORDER_CLAUSE/..#SORTED_DEF/#VAR_REF//#C_MARKER",
            "//SelectStatement/..1#TABLE_REFERENCE_LIST_FROM ..2#ERROR_TOKEN_A/#GROUP #C_MARKER",
            "//SelectStatement/..1#TABLE_REFERENCE_LIST_FROM ..2#GROUP_CLAUSE/..#VAR_REF//#C_MARKER",
            "//SelectStatement/..1#TABLE_REFERENCE_LIST_FROM ..2#GROUP_CLAUSE/..#VAR_REF//#C_MARKER",

            "//SelectStatement/#SELECT ..1#ERROR_TOKEN_A/#SUBQUERY_EXPR//#OPEN_PAREN #SELECT #C_MARKER",

            "//1$SelectStatement 2#C_MARKER",
//            "//1$SelectStatement 2#IDENTIFIER",
//            "//#TABLE_REF/1#IDENTIFIER",
//            "//1#TABLE_REF",
//            "//1#TABLE_REF 2#ALIAS_NAME",
//            "//..1#ALIAS_NAME",
//            "//..#VAR_REF/1#NAME_FRAGMENT",
//            "//#UPDATE 1#TABLE_ALIAS .. #VAR_REF/1#NAME_FRAGMENT",
            "//#UPDATE 1#TABLE_ALIAS #SET #ERROR_TOKEN_A/#2#C_MARKER"
    };
*/

    static final String[] testSet = new String[]{
            "/ #ERROR_TOKEN_A / 1#C_MARKER",
            "/ #UPDATE_COMMAND // #UPDATE #TABLE_ALIAS / 1#TABLE_REF #ALIAS_NAME // #C_MARKER",
            "/ #UPDATE_COMMAND // #UPDATE #TABLE_ALIAS / 1#TABLE_REF / #C_MARKER",
            "/ #UPDATE_COMMAND // #UPDATE #TABLE_ALIAS #SET / #ERROR_TOKEN_A / #C_MARKER",
            "/ #ERROR_TOKEN_A / #SELECT #ASTERISK 1#C_MARKER",
            "/ #ERROR_TOKEN_A / #SELECT #C_MARKER",
            "/ #ERROR_TOKEN_A / #SELECT #IDENTIFIER #C_MARKER",
            "// .. #ERROR_TOKEN_A / #SELECT .. #EXPR_COLUMN #COMMA #ERROR_TOKEN_A / #C_MARKER",
            "// #SELECT .. 1#EXPR_COLUMN 2#C_MARKER",
            "// #SELECT .. 1#EXPR_COLUMN #AS 2#C_MARKER",
            "// SelectStatement / .. #TABLE_REFERENCE_LIST_FROM / .. 1#TABLE_ALIAS / #TABLE_REF / #C_MARKER",
            "// .. 1$SelectStatement / #SELECT .. 2#EXPR_COLUMN / #PARENTHESIZED_EXPR / .. #VAR_REF // #C_MARKER",
            "// 1$SelectStatement / .. #TABLE_REFERENCE_LIST_FROM / .. #TABLE_ALIAS / 2#TABLE_REF #ALIAS_NAME // 3#C_MARKER",
            "// SelectStatement / #SELECT .. 1#ERROR_TOKEN_A / #SUBQUERY_EXPR // #OPEN_PAREN #SELECT #C_MARKER",
            "// #SELECT .. 1#ERROR_TOKEN_A / #SUBQUERY_EXPR // #OPEN_PAREN #SELECT #C_MARKER",
            "// SelectStatement / .. #EXPR_COLUMN / .. #ALIAS_NAME / #ALIAS_IDENT / 1#C_MARKER",
            "// SelectStatement / .. #TABLE_REFERENCE_LIST_FROM / .. #FROM_SUBQUERY / #SUBQUERY / #OPEN_PAREN #ERROR_TOKEN_A / 1#C_MARKER",
            "// .. #EXPR_COLUMN // #SUBQUERY / .. SelectStatement / .. #TABLE_REFERENCE_LIST_FROM / .. 1#TABLE_ALIAS / #TABLE_REF / #C_MARKER",
            "// .. SelectStatement / .. 1#TABLE_REFERENCE_LIST_FROM .. 2#ERROR_TOKEN_A / #ORDER #C_MARKER",
            "// SelectStatement / .. 1#TABLE_REFERENCE_LIST_FROM .. 2#ORDER_CLAUSE / .. #SORTED_DEF / #VAR_REF // #C_MARKER",
            "// SelectStatement / .. 1#TABLE_REFERENCE_LIST_FROM .. 2#GROUP_CLAUSE / .. #VAR_REF // #C_MARKER",
            "// SelectStatement / .. 1#TABLE_REFERENCE_LIST_FROM .. 2#ERROR_TOKEN_A / #GROUP #C_MARKER",
            "// 1$SelectStatement 2#C_MARKER",
    };


    public static void main(String[] args) throws RecognitionException, TokenStreamException {

        CodeGenerator treeBuilder = new CodeGenerator("CompletionProcessor3");
        for (String text : testSet) {
            treeBuilder.buildTree("", new String[]{"", text, "com.intellij.lang.ASTNode", "com.deepsky.lang.plsql.completion.processors.C_Context"});
        }


        treeBuilder.generate();
//        treeBuilder.masterNode.accept(new CodeGeneratorVisitor());
//        root.accept(new PrintOutVisitor());
//        root.printOut(0, System.out);
        System.out.println();
        int hh = 0;

    }


}
