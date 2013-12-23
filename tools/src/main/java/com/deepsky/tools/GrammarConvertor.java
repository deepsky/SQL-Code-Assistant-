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

package com.deepsky.tools;

import java.io.*;
import java.lang.reflect.Field;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Set;
import java.util.HashSet;
import java.net.URISyntaxException;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.TokenStream;
import antlr.collections.AST;
import com.deepsky.tools.generated.ASTStringParser;
import com.deepsky.tools.generated.ASTStringLexer;

/**
 *  1. Convert one grammar to another i.e. plsql_parser.g to plsql_parser_ex.g
 *  replacing
 *      { #datatype_param_info = #([DATATYPE_PARAM_INFO, "DATATYPE_PARAM_INFO" ], #datatype_param_info); }
 *  with
 *      {  __markRule(DATATYPE_PARAM_INFO); }
 *
 *  and replacing parser name:
 *      PLSqlParser
 *  with
 *      PLSqlParserAdopted
 *
 *  2. Generate
 *      PLSqlTypesAdopted.java
 *      ANTLRType2AdoptedType.java
 *
 */
public class GrammarConvertor {

    final static String PACKAGE_NAME = "com.deepsky.lang.parser.plsql";
    final static String TOKEN_TYPES_CLAZZ = "com.deepsky.generated.plsql.PLSqlTokenTypes";
    final static String CUSTOM_IELEMENT_TYPE = "com.deepsky.integration.plsql.PlSqlElementType";
    final static String PSI_TYPES_ADOPTED_CL = "PLSqlTypesAdopted";
    final static String ANTLR_2_ADOPTED_CL = "ANTLRType2AdoptedType";


    static private String extractCLName(String fullName){
        return fullName.substring(fullName.lastIndexOf(".") + 1, fullName.length());
    }

    public GrammarConvertor() {
        //
    }

    public void convert(String input, String output) throws IOException, URISyntaxException {

        Class PLSqlTokenTypesClass;
        try {
            PLSqlTokenTypesClass = Class.forName(TOKEN_TYPES_CLAZZ);
        } catch (ClassNotFoundException e) {
            throw new Error(e);
        }

        String packageName = "";
        Pattern packageRegExp = Pattern.compile("[^a-zA-Z0-9]package +([a-zA-Z0-9\\.]+);");
        Pattern parserRegExp = Pattern.compile("class +([_a-zA-Z0-9]+) +extends +Parser *\\;");
        Pattern lexerRegExp = Pattern.compile("class +([_a-zA-Z0-9]+) +extends +Lexer *\\;");
        Pattern astRegExp = Pattern.compile("(?s)\\{.*?\\}");

        BufferedReader reader = new BufferedReader(new FileReader(input));

        String line;
        StringBuffer b = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            // class PLSqlLexer extends Lexer;
            // class PLSqlParser extends Parser;
            // \#[_a-z0-9]+ *=.*\#\(\[.*\,.*\]\,.*\#[_a-z0-9]+ *\)
            b.append(line).append("\n");
        }
        reader.close();

        String ff = b.toString();

        // replace package name
        Matcher m1 = packageRegExp.matcher(ff);
        while (m1.find()) {
            String g = m1.group();
            int start = m1.start();
            int end = m1.end();

            String pp = g.split(" ")[1];
            packageName = pp.substring(0, pp.length()-1) + ".adopted";
            String p = "\npackage " + packageName + ";";
            String ff1 = ff.substring(0, start) + p + ff.substring(end, ff.length());
            ff = ff1;
        }

        // replace parser string
        Matcher m = parserRegExp.matcher(ff);
        while (m.find()) {
            String g = m.group();
            int start = m.start();
            int end = m.end();

            String parserName = g.split(" ")[1] + "Adopted";
            String p = "class " + parserName + " extends Parser;";
            String ff1 = ff.substring(0, start) + p + ff.substring(end, ff.length());
            ff = ff1;
            int hhg =0;
        }

        // replace lexer string
        m = lexerRegExp.matcher(ff);
        while (m.find()) {
            String g = m.group();
            int start = m.start();
            int end = m.end();

            String parserName = g.split(" ")[1] + "Adopted";
            String p = "class " + parserName + " extends Lexer;";
            String ff1 = ff.substring(0, start) + p + ff.substring(end, ff.length());
            ff = ff1;
        }

        Set<String> types = new HashSet<String>();
        // replace ast string
        m = astRegExp.matcher(ff);
        StringBuilder ff1 = new StringBuilder();
        int end0 = 0;
        while (m.find()) {
            String g = m.group();
            int start = m.start();
            int end = m.end();

            // #num_term = #([ARITHMETIC_EXPR, "ARITHMETIC_EXPR" ], #num_term);
            Pattern astRegExp2 = Pattern.compile("(?s)#[a-z0-9_]+ *=.*#\\(\\[.*?\\);");
            Matcher mm45 = astRegExp2.matcher(g);
            String g2 = "";
            if(mm45.find()){
                g2 = mm45.group();
                start = start + mm45.start();
                end = start + g2.length(); //end + mm45.end();
            } else {
                System.out.println("Cannot parse: " + g + ", it will be skipped");
                continue;
            }

            Reader r = new StringReader(g2.substring(0, g2.length()));
            ASTStringLexer l = new ASTStringLexer(r);
            ASTStringParser parser = new ASTStringParser2(l);
            try {
                parser.stringa();

                AST ast = parser.getAST();
                if(ast != null){
                    AST ast0 = ast.getFirstChild().getNextSibling();
                    ff1.append(ff.substring(end0, start)).append(" __markRule(" + ast0.getText() + ");");
                    end0 = end;
                    types.add(ast0.getText());
                }

            } catch (RecognitionException e) {
                e.printStackTrace();
            } catch (TokenStreamException e) {
                e.printStackTrace();
            }
        }

        // print out discovered types
        StringBuilder itf = new StringBuilder();
        itf.append("package " + PACKAGE_NAME + ";\n\n");
        itf.append("import com.intellij.psi.tree.IFileElementType;\n");
        itf.append("import com.intellij.psi.tree.TokenSet;\n");
        itf.append("import " + PLSqlTokenTypesClass.getName() + ";\n");
        itf.append("import " + CUSTOM_IELEMENT_TYPE + ";\n\n");

        itf.append("public interface " + PSI_TYPES_ADOPTED_CL + " {\n");
        for(String t: types){
            // do not echo types -- System.out.println("Type: " + exec);
            itf.append("\t").append(extractCLName(CUSTOM_IELEMENT_TYPE))
                    .append(" ").append(t)
                    .append(" = new ")
                    .append(extractCLName(CUSTOM_IELEMENT_TYPE)).append("(\"").append(t)
                    .append("\", ")
                    .append(PLSqlTokenTypesClass.getSimpleName())
                    .append(".").append(t).append(");\n"
            );

            // EXAMPLE of output:
            //
            //    PlSqlElementType WHITE_SPACE = new PlSqlElementType("WHITE_SPACE");
        }
        itf.append("}\n");

        ff1.append(ff.substring(end0, ff.length()));

        FileWriter writer = new FileWriter(output);
        writer.write(ff1.toString());
        writer.close();

        //
        // Generate PLSqlTypesAdopted.java class
        //
        FileWriter writer2 = new FileWriter(new File(new File(output).getParentFile(), PSI_TYPES_ADOPTED_CL + ".java"));
        writer2.write(itf.toString());
        writer2.close();


        // create ANTLRType2AdoptedType mapping table
        // 1. Calculate size of mapping table first
        int mapping_table_size = 0;
        for(String s: types){
            try {
                Field field = PLSqlTokenTypesClass.getDeclaredField(s);
                int tokenType = field.getInt(null);
                mapping_table_size = Math.max(mapping_table_size, tokenType);
            } catch (IllegalAccessException e) {
                throw new Error(e);
            } catch (NoSuchFieldException e) {
                throw new Error(e);
            }
        }


        mapping_table_size++;
        // 2. Populate mapping table with TOKEN_TYPES_CLAZZ_NAME constants
        StringBuilder mpp2 = new StringBuilder();
        int tableSize = types.size();
        mpp2.append("package " + PACKAGE_NAME + ";\n\n");
        mpp2.append("import com.intellij.psi.tree.IElementType;\n");
        mpp2.append("import " + PLSqlTokenTypesClass.getName() + ";\n\n");
        mpp2.append("public class " + ANTLR_2_ADOPTED_CL + " {\n");
        mpp2.append("\tstatic public IElementType[] type2etype = new IElementType[" + mapping_table_size + "];\n");
        mpp2.append("\tstatic {\n");
        for(String s: types){
            mpp2.append("\t\ttype2etype[" + PLSqlTokenTypesClass.getSimpleName() + ".").append(s)
                    .append("] = " + PSI_TYPES_ADOPTED_CL + ".").append(s).append(";\n");
        }

        mpp2.append("\t}\n");
        mpp2.append("}\n");

        File base2 = new File(new File(output).getParentFile(), ANTLR_2_ADOPTED_CL + ".java");
        FileWriter writer4 = new FileWriter(base2);
        writer4.write(mpp2.toString());
        writer4.close();

        System.out.println("Mapping was saved in the file: " + base2); //new File(base2, "mapping2.txt"));

    }



    public static void main(String[] args) throws IOException, URISyntaxException {

        System.out.println("Convert the grammar.");
        GrammarConvertor conv = new GrammarConvertor();
        conv.convert(args[0], args[1]);
        System.out.println("Done.");
    }


    class ASTStringParser2 extends ASTStringParser {

        public ASTStringParser2(TokenStream lexer) {
            super(lexer);
        }

        public void reportError(RecognitionException ex) {
            //System.err.println(ex);
        }

        /** Parser error-reporting function can be overridden in subclass */
        public void reportError(String s) {
//            if (getFilename() == null) {
//                System.err.println("error: " + s);
//            }
//            else {
//                System.err.println(getFilename() + ": error: " + s);
//            }
        }
    }
}
