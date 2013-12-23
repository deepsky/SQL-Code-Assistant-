package com.deepsky.lang.lexer;

import antlr.Token;
import antlr.TokenStream;
import antlr.TokenStreamException;
import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.integration.PLSqlLexerEx;
import com.deepsky.lang.AbstractBaseTest;
import com.deepsky.lang.plsql.psi.SelectFieldExpr;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.deepsky.lang.plsql.psi.StringLiteral;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.intellij.lang.ASTNode;

import java.io.FileNotFoundException;
import java.io.StringReader;

public class QuotedString10gTest extends AbstractBaseTest {

    public void test_brackets() throws TokenStreamException, FileNotFoundException {
        String str = "select q'[Isn't this cool]' from dual";

        Token tok = null;
        TokenStream lexer = new PLSqlLexerEx(new StringReader(str));

        tok = lexer.nextToken();
        assertEquals("select", tok.getText());
        assertEquals(PLSqlTokenTypes.LITERAL_select, tok.getType());

        tok = lexer.nextToken();
        assertEquals(" ", tok.getText());
        assertEquals(PLSqlTokenTypes.WS, tok.getType());

        tok = lexer.nextToken();
        assertEquals("q'", tok.getText());
        assertEquals(PLSqlTokenTypes.QUOTED_STR_START, tok.getType());

        tok = lexer.nextToken();
        assertEquals("[Isn't this cool]'", tok.getText());
        assertEquals(PLSqlTokenTypes.QUOTED_STR_END, tok.getType());

        tok = lexer.nextToken();
        assertEquals(" ", tok.getText());
        assertEquals(PLSqlTokenTypes.WS, tok.getType());

        tok = lexer.nextToken();
        assertEquals("from", tok.getText());
        assertEquals(PLSqlTokenTypes.LITERAL_from, tok.getType());

        tok = lexer.nextToken();
        assertEquals(" ", tok.getText());
        assertEquals(PLSqlTokenTypes.WS, tok.getType());

        tok = lexer.nextToken();
        assertEquals("dual", tok.getText());
        assertEquals(PLSqlTokenTypes.IDENTIFIER, tok.getType());

        tok = lexer.nextToken();
        assertNull(tok.getText());
        assertEquals(PLSqlTokenTypes.EOF, tok.getType());
    }


    public void test_parentheses() throws TokenStreamException, FileNotFoundException {
        String str = "select q'(Isn't this cool)' from dual";

        Token tok = null;
        TokenStream lexer = new PLSqlLexerEx(new StringReader(str));

        tok = lexer.nextToken();
        assertEquals("select", tok.getText());
        assertEquals(PLSqlTokenTypes.LITERAL_select, tok.getType());

        tok = lexer.nextToken();
        assertEquals(" ", tok.getText());
        assertEquals(PLSqlTokenTypes.WS, tok.getType());

        tok = lexer.nextToken();
        assertEquals("q'", tok.getText());
        assertEquals(PLSqlTokenTypes.QUOTED_STR_START, tok.getType());

        tok = lexer.nextToken();
        assertEquals("(Isn't this cool)'", tok.getText());
        assertEquals(PLSqlTokenTypes.QUOTED_STR_END, tok.getType());

        tok = lexer.nextToken();
        assertEquals(" ", tok.getText());
        assertEquals(PLSqlTokenTypes.WS, tok.getType());

        tok = lexer.nextToken();
        assertEquals("from", tok.getText());
        assertEquals(PLSqlTokenTypes.LITERAL_from, tok.getType());

        tok = lexer.nextToken();
        assertEquals(" ", tok.getText());
        assertEquals(PLSqlTokenTypes.WS, tok.getType());

        tok = lexer.nextToken();
        assertEquals("dual", tok.getText());
        assertEquals(PLSqlTokenTypes.IDENTIFIER, tok.getType());

        tok = lexer.nextToken();
        assertNull(tok.getText());
        assertEquals(PLSqlTokenTypes.EOF, tok.getType());
    }


    public void test1() {
        String str = "select q'[Isn't this cool]' from dual";
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode root = generator.parse(str);
        assertNotNull(root);
        assertNotNull(root.getFirstChildNode());
        assertTrue(root.getFirstChildNode().getPsi() instanceof SelectStatement);

        SelectStatement select = (SelectStatement) root.getFirstChildNode().getPsi();
        assertEquals(1, select.getSelectFieldList().length);
        assertTrue(((SelectFieldExpr) select.getSelectFieldList()[0]).getExpression() instanceof StringLiteral);

        StringLiteral lit = (StringLiteral) ((SelectFieldExpr) select.getSelectFieldList()[0]).getExpression();
        assertEquals("q'[Isn't this cool]'", lit.getText());

    }

}
