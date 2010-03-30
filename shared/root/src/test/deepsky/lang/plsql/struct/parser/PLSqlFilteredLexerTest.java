package test.deepsky.lang.plsql.struct.parser;

import junit.framework.TestCase;
import com.deepsky.lang.plsql.struct.parser.PLSqlFilteredLexer;
import com.deepsky.generated.plsql.PLSqlTokenTypes;

import java.io.StringReader;

import antlr.TokenStreamException;
import antlr.Token;

public class PLSqlFilteredLexerTest extends TestCase {

    public void setUp(){
    }

/*
    public void test_reload() throws TokenStreamException {
        StringReader r = new StringReader("abc 'dddc' 23535");
        PLSqlFilteredLexer lexer = new PLSqlFilteredLexer(r);

        Token t = lexer.nextToken();
        assertEquals("abc", t.getText());
        assertEquals(PLSqlTokenTypes.IDENTIFIER, t.getType());

        t = lexer.nextToken();
        assertEquals("'dddc'", t.getText());
        assertEquals(PLSqlTokenTypes.QUOTED_STRING, t.getType());

        t = lexer.nextToken();
        assertEquals("23535", t.getText());
        assertEquals(PLSqlTokenTypes.NUMBER, t.getType());

        t = lexer.nextToken();
        assertEquals(null, t.getText());
        assertEquals(PLSqlTokenTypes.EOF, t.getType());

        // reload
        StringReader r2 = new StringReader("ab234c 'dddc' '23535' 9903");
        lexer.reload(r2);

        Token t2 = lexer.nextToken();
        assertEquals("ab234c", t2.getText());
        assertEquals(PLSqlTokenTypes.IDENTIFIER, t2.getType());

        t2 = lexer.nextToken();
        assertEquals("'dddc'", t2.getText());
        assertEquals(PLSqlTokenTypes.QUOTED_STRING, t2.getType());

        t2 = lexer.nextToken();
        assertEquals("'23535'", t2.getText());
        assertEquals(PLSqlTokenTypes.QUOTED_STRING, t2.getType());

        t2 = lexer.nextToken();
        assertEquals("9903", t2.getText());
        assertEquals(PLSqlTokenTypes.NUMBER, t2.getType());

        t2 = lexer.nextToken();
        assertEquals(null, t2.getText());
        assertEquals(PLSqlTokenTypes.EOF, t2.getType());
    }
*/
}
