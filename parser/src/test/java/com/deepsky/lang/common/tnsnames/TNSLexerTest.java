package com.deepsky.lang.common.tnsnames;

import antlr.Token;
import antlr.TokenStreamException;
import com.deepsky.generated.tns.TNSLexer;
import com.deepsky.generated.tns.TNSTokenTypes;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static junit.framework.Assert.assertEquals;

public class TNSLexerTest {

    @Test
    public void test0() throws TokenStreamException {
        String text = "ORA11 =\n" +
                "(DESCRIPTION =\n" +
                "   (ADDRESS_LIST =\n" +
                "     (ADDRESS = (PROTOCOL = TCP)(HOST = 127.0.0.1)(PORT = 1521))\n" +
                "   )\n" +
                "(CONNECT_DATA =\n" +
                "   (SERVICE_NAME = ORA11)\n" +
                ")\n" +
                ")";

        InputStream in = new ByteArrayInputStream(text.getBytes());

        TNSLexer lexer = new TNSLexer(in);
        int i=0;
        while(true) {
            Token t = lexer.nextToken();
            if (t.getType() == TNSTokenTypes.EOF) {
                break;
            } else if (t.getType() == TNSTokenTypes.WS
                    || t.getType() == TNSTokenTypes.LF){
                continue;
            }
            switch (i) {
                case 0:
                    assertEquals(TNSTokenTypes.IDENTIFIER, t.getType());
                    assertEquals("ORA11", t.getText());
                    break;
                case 1:
                    assertEquals(TNSTokenTypes.EQ, t.getType());
                    break;
                case 2:
                    assertEquals(TNSTokenTypes.OPEN_PAREN, t.getType());
                    break;

                case 38:
                    assertEquals(TNSTokenTypes.LITERAL_service_name, t.getType());
                    assertEquals("SERVICE_NAME", t.getText());
                    break;
                case 39:
                    assertEquals(TNSTokenTypes.EQ, t.getType());
                    break;
                case 40:
                    assertEquals(TNSTokenTypes.IDENTIFIER, t.getType());
                    assertEquals("ORA11", t.getText());
                    break;
                case 41:
                    assertEquals(TNSTokenTypes.CLOSE_PAREN, t.getType());
                    break;
                case 42:
                    assertEquals(TNSTokenTypes.CLOSE_PAREN, t.getType());
                    break;
                case 43:
                    assertEquals(TNSTokenTypes.CLOSE_PAREN, t.getType());
                    break;
            }
            i++;
        }

        assertEquals(44, i);
    }


    @Test
    public void test1() throws TokenStreamException {
        String text = "donna-beq.gennick.org =\n" +
                "  (DESCRIPTION =\n" +
                "    (ADDRESS_LIST =\n" +
                "      (ADDRESS =\n" +
                "          (PROTOCOL = BEQ)(PROGRAM = oracle)\n" +
                "          (ARGV0 = oracleDONNA)\n" +
                "          (ARGS = '(DESCRIPTION=(LOCAL=YES)(ADDRESS=(PROTOCOL=BEQ)))')\n" +
                "      )\n" +
                "    )\n" +
                "    (CONNECT_DATA = (SERVICE_NAME = DONNA.GENNICK.ORG))\n" +
                "  )";

        InputStream in = new ByteArrayInputStream(text.getBytes());

        TNSLexer lexer = new TNSLexerNoWS(in);

        Token t;
        assertEquals(TNSTokenTypes.SERVICE_NAME, (t = lexer.nextToken()).getType());
        assertEquals("donna-beq.gennick.org", t.getText());
        assertEquals(TNSTokenTypes.EQ, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.OPEN_PAREN, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.LITERAL_description, (t = lexer.nextToken()).getType());
        assertEquals("DESCRIPTION", t.getText());
        assertEquals(TNSTokenTypes.EQ, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.OPEN_PAREN, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.LITERAL_address_list, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.EQ, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.OPEN_PAREN, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.LITERAL_address, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.EQ, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.OPEN_PAREN, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.LITERAL_protocol, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.EQ, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.IDENTIFIER, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.CLOSE_PAREN, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.OPEN_PAREN, lexer.nextToken().getType());
        lexer.nextToken() ;
        assertEquals(TNSTokenTypes.EQ, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.IDENTIFIER, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.CLOSE_PAREN, lexer.nextToken().getType());
//        assertEquals(TNSTokenTypes.LITERAL_protocol, lexer.nextToken().getType());

    }


    @Test
    public void test2() throws TokenStreamException {
        String text = "donna-beq.gennick.org =\n" +
                "   (DESCRIPTION =\n" +
                "       (ADDRESS = (PROTOCOL = TCP)(HOST = qaitdora1.pts.sita.aero)(PORT = 1521))\n" +
                "       (CONNECT_DATA =\n" +
                "           (SERVER = DEDICATED)\n" +
                "           (SID = qaitddb1)\n" +
                "       )" +
                "  )";

        InputStream in = new ByteArrayInputStream(text.getBytes());

        TNSLexer lexer = new TNSLexerNoWS(in);

        Token t;
        assertEquals(TNSTokenTypes.SERVICE_NAME, (t = lexer.nextToken()).getType());
        assertEquals("donna-beq.gennick.org", t.getText());
        assertEquals(TNSTokenTypes.EQ, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.OPEN_PAREN, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.LITERAL_description, (t = lexer.nextToken()).getType());
        assertEquals("DESCRIPTION", t.getText());
        assertEquals(TNSTokenTypes.EQ, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.OPEN_PAREN, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.LITERAL_address, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.EQ, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.OPEN_PAREN, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.LITERAL_protocol, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.EQ, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.IDENTIFIER, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.CLOSE_PAREN, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.OPEN_PAREN, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.LITERAL_host, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.EQ, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.SERVICE_NAME, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.CLOSE_PAREN, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.OPEN_PAREN, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.LITERAL_port, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.EQ, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.NUMBER, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.CLOSE_PAREN, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.CLOSE_PAREN, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.OPEN_PAREN, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.LITERAL_connect_data, lexer.nextToken().getType());
        assertEquals(TNSTokenTypes.EQ, lexer.nextToken().getType());

    }


    private class TNSLexerNoWS extends TNSLexer {
        public TNSLexerNoWS(InputStream in){
            super(in);
        }

        public Token nextToken() throws TokenStreamException {
            Token t = super.nextToken();
            while(t.getType() == TNSTokenTypes.WS || t.getType() == TNSTokenTypes.LF){
                t = super.nextToken();
            }
            return t;
        }

    }

}
