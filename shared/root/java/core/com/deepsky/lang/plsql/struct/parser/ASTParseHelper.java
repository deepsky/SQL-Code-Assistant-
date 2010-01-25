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
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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

package com.deepsky.lang.plsql.struct.parser;

import antlr.collections.AST;
import antlr.RecognitionException;
import antlr.TokenStreamException;
import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.generated.plsql.PLSqlParser;
import com.deepsky.lang.plsql.parser.ParserException;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.ArrayList;

import com.deepsky.lang.plsql.struct.parser.PLSqlFilteredLexer;

public class ASTParseHelper {


    public static TableAliasDTO parseTableAlias(String text){
        Reader in = new StringReader(text);
        PLSqlFilteredLexer lexer = new PLSqlFilteredLexerImpl(in);
        PLSqlParser parser = new PLSqlParser(lexer);

        try {
            parser.table_alias();
            AST ast = parser.getAST();
            if(ast == null){
                throw new ParserException("Could not parse table_alias: " + text);
            }
            return parseTableAlias(ast);

        } catch (RecognitionException e) {
            throw new ParserException("Could not parse sql script", e);
        } catch (TokenStreamException e) {
            throw new ParserException("Could not parse sql script", e);
        }
    }


    public static TableAliasDTO parseTableAlias(AST ast){
        if(ast.getType() != PLSqlTokenTypes.TABLE_ALIAS){
            throw new ParserException("TABLE_ALIAS not found");
        } else {
            AST ast1 = ast.getFirstChild();
            TableAliasDTO talias = new TableAliasDTO();

            while(ast1 != null){
                if(ast1.getType() == PLSqlTokenTypes.SCHEMA_NAME){
                    talias.setSchema(parseIdent(ast1.getFirstChild()));
                } else if(ast1.getType() == PLSqlTokenTypes.TABLE_NAME){
                    talias.setTable(parseIdent(ast1.getFirstChild()));
                } else if(ast1.getType() == PLSqlTokenTypes.ALIAS_NAME){
                    AST ast2 = ast1.getFirstChild();
                    if("as".equalsIgnoreCase(ast2.getText())){
                        ast2 = ast2.getNextSibling();
                    }
                    talias.setAlias(parseIdent(ast2));
                } else if(ast1.getType() == PLSqlTokenTypes.AT_SIGN){
                    AST ast2 = ast1.getNextSibling();
                    talias.setLink(parseIdent(ast2));
                }
                ast1 = ast1.getNextSibling();
            }

            return talias;
        }
    }

    private static String parseIdent(AST ast) {
        return ast.getText();
//        if (ast.getType() == PLSqlTokenTypes.IDENT) {
//            return ast.getFirstChild().getText();
//        } else {
//            throw new ParserException("IDENT node expected but was: " + ast.getText());
//        }
    }

    public static String parseSequenceExpr(String text) {
        Reader in = new StringReader(text);
        PLSqlFilteredLexer lexer = new PLSqlFilteredLexerImpl(in);
        PLSqlParser parser = new PLSqlParser(lexer);

        try {
            parser.sequence_expr();
            AST ast = parser.getAST();
            if(ast == null){
                throw new ParserException("Could not parse sequence_expr: " + text);
            }
            return parseSequenceExpr(ast);

        } catch (RecognitionException e) {
            throw new ParserException("Could not parse sql script", e);
        } catch (TokenStreamException e) {
            throw new ParserException("Could not parse sql script", e);
        }
    }

    public static String parseSequenceExpr(AST ast){
        if(ast.getType() != PLSqlTokenTypes.SEQUENCE_EXPR){
            throw new ParserException("SEQUENCE_EXPR not found");
        } else {
            AST ast1 = ast.getFirstChild();
            return parseIdent(ast1);
        }
    }

/*
    public static FunctionNameDTO parseFunctionName(String text) {
        Reader in = new StringReader(text);
        PLSqlFilteredLexer lexer = new PLSqlFilteredLexer(in);
        PLSqlParser parser = new PLSqlParser(lexer);

        try {
            parser.function_name();
            AST ast = parser.getAST();
            if(ast == null){
                throw new ParserException("Could not parse sequence_expr: " + text);
            }
            return parseFunctionName(ast);

        } catch (RecognitionException e) {
            throw new ParserException("Could not parse sql script", e);
        } catch (TokenStreamException e) {
            throw new ParserException("Could not parse sql script", e);
        }
    }

    public static FunctionNameDTO parseFunctionName(AST ast) {
        if(ast.getType() != PLSqlTokenTypes.FUNCTION_NAME){
            throw new ParserException("FUNCTION_NAME not found");
        } else {
            AST ast1 = ast.getFirstChild();
            List<String> out = new ArrayList<String>();
            while(ast1 != null){
                if(ast1.getType() == PLSqlTokenTypes.TABLE_REF){
                    out.add(parseIdent(ast1.getFirstChild()));
                } else if(ast1.getType() == PLSqlTokenTypes.COLUMN_NAME_REF){
                    out.add(parseIdent(ast1.getFirstChild()));
                }
                ast1 = ast1.getNextSibling();
            }
            FunctionNameDTO dto = new FunctionNameDTO();
            switch(out.size()){
                case 1:
                    dto.setName(out.get(0));
                    break;
                case 2:
                    dto.setPackage(out.get(0));
                    dto.setName(out.get(1));
                    break;
                case 3:
                    dto.setSchema(out.get(0));
                    dto.setPackage(out.get(1));
                    dto.setName(out.get(2));
                    break;
                default:
                    // todo !!!
            }
            return dto;
        }
    }

    public static FunctionBodyDTO parseFunctionBody(String text) {
        Reader in = new StringReader(text);
        PLSqlFilteredLexer lexer = new PLSqlFilteredLexer(in);
        PLSqlParser parser = new PLSqlParser(lexer);

        try {
            parser.function_name();
            AST ast = parser.getAST();
            if(ast == null){
                throw new ParserException("Could not parse sequence_expr: " + text);
            }
            return parseFunctionBody(ast);

        } catch (RecognitionException e) {
            throw new ParserException("Could not parse sql script", e);
        } catch (TokenStreamException e) {
            throw new ParserException("Could not parse sql script", e);
        }
    }
*/

    public static FunctionBodyDTO parseFunctionBody(AST ast) {
        if(ast.getType() != PLSqlTokenTypes.FUNCTION_BODY){
            throw new ParserException("FUNCTION_BODY not found");
        } else {
            AST ast1 = ast.getFirstChild();
            FunctionBodyDTO dto = new FunctionBodyDTO();
            while(ast1 != null){
                if(ast1.getType() == PLSqlTokenTypes.OBJECT_NAME){
                    dto.setFunction(parseObjectName(ast1));
                } else if(ast1.getType() == PLSqlTokenTypes.RETURN_TYPE){

                }  else if(ast1.getType() == PLSqlTokenTypes.ARGUMENT_LIST){

                }

                ast1 = ast1.getNextSibling();
            }

            return dto;
        }
    }

    private static String parseObjectName(AST ast) {
        AST ast1 = ast.getFirstChild();
        String out = "";
        while(ast1 != null){
//            if(ast1.getType() == PLSqlTokenTypes.IDENT){
                out += "." + parseIdent(ast1);
//            }
            ast1 = ast1.getNextSibling();
        }
        return out;
    }


    public static Type parseDatatype(String text) {
        Reader in = new StringReader(text);
        PLSqlFilteredLexer lexer = new PLSqlFilteredLexerImpl(in);
        PLSqlParser parser = new PLSqlParser(lexer);

        try {
            parser.datatype();
            AST ast = parser.getAST();
            if(ast == null){
                throw new ParserException("Could not parse datatype: " + text);
            }
            return PlSqlASTParser.parseDatatype(ast);

        } catch (RecognitionException e) {
            throw new ParserException("Could not parse sql script", e);
        } catch (TokenStreamException e) {
            throw new ParserException("Could not parse sql script", e);
        }
    }


/*
    public static Type parseTypeNameRef(String packageName, String text) {
        Reader in = new StringReader(text);
        PLSqlFilteredLexer lexer = new PLSqlFilteredLexerImpl(in);
        PLSqlParser parser = new PLSqlParser(lexer);

        try {
            parser.type_name_ref();
            AST ast = parser.getAST();
            if(ast == null){
                throw new ParserException("Could not parse datatype: " + text);
            }
            TypeNameRefHolder holder = PlSqlASTParser.parseTypeNameRef(ast);
            if(holder.pkgName != null){
                return new UserDefinedType(holder.pkgName, holder.typeName);
            } else {
                return new UserDefinedType(packageName, holder.typeName);
            }

        } catch (RecognitionException e) {
            throw new ParserException("Could not parse sql script", e);
        } catch (TokenStreamException e) {
            throw new ParserException("Could not parse sql script", e);
        }
    }
*/

/*
    public Type parseTypeSpec(String packageName, String text) {
        Reader in = new StringReader(text);
        PLSqlFilteredLexer lexer = new PLSqlFilteredLexerImpl(in);
        PLSqlParser parser = new PLSqlParser(lexer);

        try {
            parser.type_spec();
            AST ast = parser.getAST();
            if(ast == null){
                throw new ParserException("Could not parse datatype: " + text);
            }
            Type t = PlSqlASTParser.parseTypeSpec(ast, packageName);
            return t;
        } catch (RecognitionException e) {
            throw new ParserException("Could not parse sql script", e);
        } catch (TokenStreamException e) {
            throw new ParserException("Could not parse sql script", e);
        }
    }
*/

}

