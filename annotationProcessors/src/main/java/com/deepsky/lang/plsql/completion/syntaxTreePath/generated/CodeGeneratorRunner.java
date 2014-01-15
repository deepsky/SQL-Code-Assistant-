package com.deepsky.lang.plsql.completion.syntaxTreePath.generated;

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

        CodeGenerator treeBuilder = new CodeGenerator("CompletionProcessor2");
        for (String text : testSet) {
            treeBuilder.buildTree("", "", text);
        }


        treeBuilder.generate();
//        treeBuilder.masterNode.accept(new CodeGeneratorVisitor());
//        root.accept(new PrintOutVisitor());
//        root.printOut(0, System.out);
        System.out.println();
        int hh = 0;

    }


}
