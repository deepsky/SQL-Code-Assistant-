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

import antlr.Token;
import antlr.TokenStreamException;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.SyntaxTreePathLexer;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.SyntaxTreePathTokenTypes;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class SyntaxTreePathLexerTest {

    @Test
    public void test_1() throws TokenStreamException {

        String test = "//";
        Reader r = new StringReader(test);
        SyntaxTreePathLexer lexer = new SyntaxTreePathLexer(r);
        Token t = lexer.nextToken();

        assertEquals("//", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.DOUBLE_SLASH, t.getType());

        assertEquals(SyntaxTreePathTokenTypes.EOF, lexer.nextToken().getType());
    }


    @Test
    public void test_2() throws TokenStreamException {

        String test = "//Hello";
        Reader r = new StringReader(test);
        SyntaxTreePathLexer lexer = new SyntaxTreePathLexer(r);

        Token t = lexer.nextToken();
        assertEquals("//", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.DOUBLE_SLASH, t.getType());

        t = lexer.nextToken();
        assertEquals("Hello", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.IDENTIFIER, t.getType());

        assertEquals(SyntaxTreePathTokenTypes.EOF, lexer.nextToken().getType());
    }


    @Test
    public void test_3() throws TokenStreamException {

        String test = "/1$Hello";
        Reader r = new StringReader(test);
        SyntaxTreePathLexer lexer = new SyntaxTreePathLexer(r);

        Token t = lexer.nextToken();
        assertEquals("/", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.SLASH, t.getType());

        t = lexer.nextToken();
        assertEquals("1", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.NUMBER, t.getType());

        t = lexer.nextToken();
        assertEquals("$", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.DOLLAR, t.getType());

        t = lexer.nextToken();
        assertEquals("Hello", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.IDENTIFIER, t.getType());

        assertEquals(SyntaxTreePathTokenTypes.EOF, lexer.nextToken().getType());
    }


    @Test
    public void test_4() throws TokenStreamException {

        String test = "/1#Hello";
        Reader r = new StringReader(test);
        SyntaxTreePathLexer lexer = new SyntaxTreePathLexer(r);

        Token t = lexer.nextToken();
        assertEquals("/", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.SLASH, t.getType());

        t = lexer.nextToken();
        assertEquals("1", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.NUMBER, t.getType());

        t = lexer.nextToken();
        assertEquals("#", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.SHARP, t.getType());

        t = lexer.nextToken();
        assertEquals("Hello", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.IDENTIFIER, t.getType());

        assertEquals(SyntaxTreePathTokenTypes.EOF, lexer.nextToken().getType());
    }


    @Test
    public void test_5() throws TokenStreamException {

        String test = "//1$SelectStatement//#QUERY_PARTITION_CLAUSE/ObjectReference/2$NameFragmentRef";
        Reader r = new StringReader(test);
        SyntaxTreePathLexer lexer = new SyntaxTreePathLexer(r);

        Token t = lexer.nextToken();
        assertEquals("//", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.DOUBLE_SLASH, t.getType());

        t = lexer.nextToken();
        assertEquals("1", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.NUMBER, t.getType());

        t = lexer.nextToken();
        assertEquals("$", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.DOLLAR, t.getType());

        t = lexer.nextToken();
        assertEquals("SelectStatement", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.IDENTIFIER, t.getType());

        t = lexer.nextToken();
        assertEquals("//", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.DOUBLE_SLASH, t.getType());

        t = lexer.nextToken();
        assertEquals("#", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.SHARP, t.getType());

        t = lexer.nextToken();
        assertEquals("QUERY_PARTITION_CLAUSE", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.IDENTIFIER, t.getType());

        t = lexer.nextToken();
        assertEquals("/", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.SLASH, t.getType());

        t = lexer.nextToken();
        assertEquals("ObjectReference", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.IDENTIFIER, t.getType());

        t = lexer.nextToken();
        assertEquals("/", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.SLASH, t.getType());

        t = lexer.nextToken();
        assertEquals("2", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.NUMBER, t.getType());

        t = lexer.nextToken();
        assertEquals("$", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.DOLLAR, t.getType());

        t = lexer.nextToken();
        assertEquals("NameFragmentRef", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.IDENTIFIER, t.getType());

        assertEquals(SyntaxTreePathTokenTypes.EOF, lexer.nextToken().getType());
    }

    @Test
    public void test_6() throws TokenStreamException {

        String test = "/#KEYWORD_GRANT,#SYSTEM_PRIVILEGE,1#IDENTIFIER";
        Reader r = new StringReader(test);
        SyntaxTreePathLexer lexer = new SyntaxTreePathLexer(r);

        Token t = lexer.nextToken();
        assertEquals("/", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.SLASH, t.getType());

        t = lexer.nextToken();
        assertEquals("#", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.SHARP, t.getType());

        t = lexer.nextToken();
        assertEquals("KEYWORD_GRANT", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.IDENTIFIER, t.getType());

        t = lexer.nextToken();
        assertEquals(",", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.COMMA, t.getType());

        t = lexer.nextToken();
        assertEquals("#", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.SHARP, t.getType());

        t = lexer.nextToken();
        assertEquals("SYSTEM_PRIVILEGE", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.IDENTIFIER, t.getType());

        t = lexer.nextToken();
        assertEquals(",", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.COMMA, t.getType());

        t = lexer.nextToken();
        assertEquals("1", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.NUMBER, t.getType());

        t = lexer.nextToken();
        assertEquals("#", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.SHARP, t.getType());

        t = lexer.nextToken();
        assertEquals("IDENTIFIER", t.getText());
        assertEquals(SyntaxTreePathTokenTypes.IDENTIFIER, t.getType());
    }
}

