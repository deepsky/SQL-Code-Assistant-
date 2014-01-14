package com.deepsky.lang.plsql.completion.syntaxTreePath.generated;

import antlr.Token;
import antlr.TokenStreamException;
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

