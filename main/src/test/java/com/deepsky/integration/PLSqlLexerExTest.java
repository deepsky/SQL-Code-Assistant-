package com.deepsky.integration;

import antlr.Token;
import antlr.TokenStreamException;
import com.deepsky.generated.plsql.PLSqlTokenTypes;
import org.junit.Test;

import java.io.StringReader;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class PLSqlLexerExTest {

    @Test
    public void test_mNUMBER_trivial() throws TokenStreamException {

        PLSqlLexerEx lex = new PLSqlLexerEx(new StringReader("1"));
        Token t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.NUMBER, t.getType());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.EOF, t.getType());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.EOF, t.getType());
    }


    @Test
    public void test_mNUMBER_series() throws TokenStreamException {

        PLSqlLexerEx lex = new PLSqlLexerEx(new StringReader("10 045  8974"));
        Token t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.NUMBER, t.getType());
        assertEquals("10", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.WS, t.getType());
        assertEquals(" ", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.NUMBER, t.getType());
        assertEquals("045", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.WS, t.getType());
        assertEquals("  ", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.NUMBER, t.getType());
        assertEquals("8974", t.getText());

        assertEquals(PLSqlTokenTypes.EOF, lex.nextToken().getType());
    }


    @Test
    public void test_mNUMBER_fractions() throws TokenStreamException {

        PLSqlLexerEx lex = new PLSqlLexerEx(new StringReader(".10 0.45  8974."));
        Token t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.NUMBER, t.getType());
        assertEquals(".10", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.WS, t.getType());
        assertEquals(" ", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.NUMBER, t.getType());
        assertEquals("0.45", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.WS, t.getType());
        assertEquals("  ", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.NUMBER, t.getType());
        assertEquals("8974", t.getText());

        assertEquals(PLSqlTokenTypes.DOT, lex.nextToken().getType());
        assertEquals(PLSqlTokenTypes.EOF, lex.nextToken().getType());
    }


    @Test
    public void test_STORAGE_SIZE() throws TokenStreamException {

        PLSqlLexerEx lex = new PLSqlLexerEx(new StringReader(".10 45k  8974M 9K."));
        Token t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.NUMBER, t.getType());
        assertEquals(".10", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.WS, t.getType());
        assertEquals(" ", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.STORAGE_SIZE, t.getType());
        assertEquals("45k", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.WS, t.getType());
        assertEquals("  ", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.STORAGE_SIZE, t.getType());
        assertEquals("8974M", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.WS, t.getType());
        assertEquals(" ", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.STORAGE_SIZE, t.getType());
        assertEquals("9K", t.getText());

        assertEquals(PLSqlTokenTypes.DOT, lex.nextToken().getType());
        assertEquals(PLSqlTokenTypes.EOF, lex.nextToken().getType());
    }

    @Test
    public void test_mNUMBER_mix() throws TokenStreamException {

        PLSqlLexerEx lex = new PLSqlLexerEx(new StringReader(".10 45k  8974e-34 .4e+98 .4e98"));
        Token t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.NUMBER, t.getType());
        assertEquals(".10", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.WS, t.getType());
        assertEquals(" ", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.STORAGE_SIZE, t.getType());
        assertEquals("45k", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.WS, t.getType());
        assertEquals("  ", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.NUMBER, t.getType());
        assertEquals("8974e-34", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.WS, t.getType());
        assertEquals(" ", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.NUMBER, t.getType());
        assertEquals(".4e+98", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.WS, t.getType());
        assertEquals(" ", t.getText());

        t = lex.nextToken();
        assertNotNull(t);
        assertEquals(PLSqlTokenTypes.NUMBER, t.getType());
        assertEquals(".4e98", t.getText());

        assertEquals(PLSqlTokenTypes.EOF, lex.nextToken().getType());
    }
}
