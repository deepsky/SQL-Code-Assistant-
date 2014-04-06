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
import antlr.collections.AST;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.SyntaxTreePathLexer;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.SyntaxTreePathParser;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.SyntaxTreePathTokenTypes;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import static junit.framework.Assert.assertEquals;

public class SyntaxTreePathParserTest {

    //@Test
    public void test_parse() throws RecognitionException, TokenStreamException {
        AST ast = parse("/..1#ALTER_TABLE(/ALTER TABLE TABLE_REF ADD A_COLUMN_DEF(/COLUMN_NAME_DDL DATATYPE ERROR_TOKEN_A)) #C_MARKER");

        assertEquals(SyntaxTreePathTokenTypes.START_RULE, ast.getType());
        AST node1 = ast.getFirstChild();
        assertEquals(SyntaxTreePathTokenTypes.STARTER_ONE, node1.getType());

        AST node2 = node1.getFirstChild();
        assertEquals(SyntaxTreePathTokenTypes.SLASH, node2.getType());

        AST node3 = node2.getFirstChild();
        assertEquals(SyntaxTreePathTokenTypes.SLASH, node3.getType());
    }

    private AST parse(String text) throws TokenStreamException, RecognitionException {
        Reader r = new StringReader(text);
        SyntaxTreePathLexer lexer = new SyntaxTreePathLexer(r);
        SyntaxTreePathParser parser = new SyntaxTreePathParser(lexer);
        parser.start_rule();
        return parser.getAST();
    }


}
