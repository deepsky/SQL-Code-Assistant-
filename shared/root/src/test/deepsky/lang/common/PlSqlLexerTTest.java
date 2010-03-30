package test.deepsky.lang.common;


import com.deepsky.lang.lexer.PlSqlBaseLexer;
import junit.framework.TestCase;

import java.io.*;
import java.net.URISyntaxException;

public class PlSqlLexerTTest extends TestCase {

    PlSqlBaseLexer lexer;

    public void setUp() throws URISyntaxException {
        lexer = new PlSqlBaseLexer();
    }

    public void test_corrupted_QUOTED_STR() throws IOException {
        String content = "ss'asd";

        lexer.start(content.toCharArray());

        assertEquals("PLSQL:IDENTIFIER",lexer.getTokenType().toString());
        assertEquals(0, lexer.getTokenStart());
        assertEquals(2, lexer.getTokenEnd());
        assertEquals("ss", content.substring(lexer.getTokenStart(), lexer.getTokenEnd()));
        advance_lexer(lexer);

        assertEquals("PLSQL:BAD_CHAR_LITERAL",lexer.getTokenType().toString());
        assertEquals(2, lexer.getTokenStart());
        assertEquals(6, lexer.getTokenEnd());
        assertEquals("'asd", content.substring(lexer.getTokenStart(), lexer.getTokenEnd()));
        advance_lexer(lexer);

        assertNull(lexer.getTokenType());
        assertEquals(6, lexer.getTokenStart());
        assertEquals(6, lexer.getTokenEnd());
        assertEquals("", content.substring(lexer.getTokenStart(), lexer.getTokenEnd()));
    }

    public void test_corrupted_DOUBLE_QUOTED_STRING() throws IOException {
        String content = "ss\" a091";

        lexer.start(content.toCharArray());

        assertEquals("PLSQL:IDENTIFIER",lexer.getTokenType().toString());
        assertEquals(0, lexer.getTokenStart());
        assertEquals(2, lexer.getTokenEnd());
        assertEquals("ss", content.substring(lexer.getTokenStart(), lexer.getTokenEnd()));
        advance_lexer(lexer);

        assertEquals("PLSQL:BAD_CHAR_LITERAL",lexer.getTokenType().toString());
        assertEquals(2, lexer.getTokenStart());
        assertEquals(8, lexer.getTokenEnd());
        assertEquals("\" a091", content.substring(lexer.getTokenStart(), lexer.getTokenEnd()));
        advance_lexer(lexer);

        assertNull(lexer.getTokenType());
        assertEquals(8, lexer.getTokenStart());
        assertEquals(8, lexer.getTokenEnd());
        assertEquals("", content.substring(lexer.getTokenStart(), lexer.getTokenEnd()));
/*
        // load file into string
        String content = Helpers.loadFile(new File(base, corrupted_DOUBLE_QUOTED_STRING));

        lexer.start(content.toCharArray()); //, 0, 19);
        int diff;
        do {
            int end = lexer.getBufferEnd();

            IElementType elem = lexer.getTokenType();
            int tstart = lexer.getTokenStart();
            int tend = lexer.getTokenEnd();
            diff = tend - tstart;
            System.out.println("start: " + tstart + " end: "  + tend + ", text: '" + content.substring(tstart, tend) + "' " + elem);

            lexer.advance();

            int hh=0;
        } while(diff > 0 );
*/
    }

    public void test_corrupted_ML_COMMENT() throws IOException {
        String content = "ss /* ueie";

        lexer.start(content.toCharArray());

        assertEquals("PLSQL:IDENTIFIER",lexer.getTokenType().toString());
        assertEquals(0, lexer.getTokenStart());
        assertEquals(2, lexer.getTokenEnd());
        assertEquals("ss", content.substring(lexer.getTokenStart(), lexer.getTokenEnd()));
        advance_lexer(lexer);

        assertEquals("PLSQL:WS",lexer.getTokenType().toString());
        assertEquals(2, lexer.getTokenStart());
        assertEquals(3, lexer.getTokenEnd());
        assertEquals(" ", content.substring(lexer.getTokenStart(), lexer.getTokenEnd()));
        advance_lexer(lexer);

        assertEquals("PLSQL:BAD_ML_COMMENT",lexer.getTokenType().toString());
        assertEquals(3, lexer.getTokenStart());
        assertEquals(10, lexer.getTokenEnd());
        assertEquals("/* ueie", content.substring(lexer.getTokenStart(), lexer.getTokenEnd()));
        advance_lexer(lexer);

        assertNull(lexer.getTokenType());
        assertEquals(10, lexer.getTokenStart());
        assertEquals(10, lexer.getTokenEnd());
        assertEquals("", content.substring(lexer.getTokenStart(), lexer.getTokenEnd()));
    }


    public void test_number() throws IOException {
        String content = "234 99e456 90E+78 0.99e-89 0.99ei78 5e-1";

        lexer.start(content.toCharArray());

        assertEquals("PLSQL:NUMBER",lexer.getTokenType().toString());
//        assertEquals(0, lexer.getTokenStart());
//        assertEquals(2, lexer.getTokenEnd());
//        assertEquals("ss", content.substring(lexer.getTokenStart(), lexer.getTokenEnd()));
        advance_lexer(lexer);

        assertEquals("PLSQL:WS",lexer.getTokenType().toString());
        advance_lexer(lexer);

        assertEquals("PLSQL:NUMBER",lexer.getTokenType().toString());
//        assertEquals(0, lexer.getTokenStart());
//        assertEquals(2, lexer.getTokenEnd());
//        assertEquals("ss", content.substring(lexer.getTokenStart(), lexer.getTokenEnd()));
        advance_lexer(lexer);

        assertEquals("PLSQL:WS",lexer.getTokenType().toString());
        advance_lexer(lexer);

        assertEquals("PLSQL:NUMBER",lexer.getTokenType().toString());
//        assertEquals(0, lexer.getTokenStart());
//        assertEquals(2, lexer.getTokenEnd());
//        assertEquals("ss", content.substring(lexer.getTokenStart(), lexer.getTokenEnd()));
        advance_lexer(lexer);

        assertEquals("PLSQL:WS",lexer.getTokenType().toString());
        advance_lexer(lexer);
        
        assertEquals("PLSQL:NUMBER",lexer.getTokenType().toString());
//        assertEquals(0, lexer.getTokenStart());
//        assertEquals(2, lexer.getTokenEnd());
//        assertEquals("ss", content.substring(lexer.getTokenStart(), lexer.getTokenEnd()));
        advance_lexer(lexer);

        assertEquals("PLSQL:WS",lexer.getTokenType().toString());
        advance_lexer(lexer);

        assertEquals("PLSQL:BAD_NUMBER_LITERAL",lexer.getTokenType().toString());
//        assertEquals(0, lexer.getTokenStart());
//        assertEquals(2, lexer.getTokenEnd());
//        assertEquals("ss", content.substring(lexer.getTokenStart(), lexer.getTokenEnd()));
        advance_lexer(lexer);

        assertEquals("PLSQL:IDENTIFIER",lexer.getTokenType().toString());
//        assertEquals(0, lexer.getTokenStart());
//        assertEquals(2, lexer.getTokenEnd());
        assertEquals("i78", content.substring(lexer.getTokenStart(), lexer.getTokenEnd()));
        advance_lexer(lexer);

        assertEquals("PLSQL:WS",lexer.getTokenType().toString());
        advance_lexer(lexer);

        assertEquals("PLSQL:NUMBER",lexer.getTokenType().toString());
//        assertEquals(0, lexer.getTokenStart());
//        assertEquals(2, lexer.getTokenEnd());
//        assertEquals("ss", content.substring(lexer.getTokenStart(), lexer.getTokenEnd()));
        advance_lexer(lexer);
    }

    void advance_lexer(PlSqlBaseLexer lex){
        lex.advance();
    }
}
