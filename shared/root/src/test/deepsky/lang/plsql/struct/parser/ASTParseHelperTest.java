package test.deepsky.lang.plsql.struct.parser;

import com.deepsky.lang.plsql.struct.parser.PLSqlFilteredLexerImpl;
import junit.framework.TestCase;
import com.deepsky.lang.plsql.struct.parser.ASTParseHelper;
import com.deepsky.lang.plsql.struct.parser.TableAliasDTO;
import com.deepsky.lang.plsql.parser.ParserException;
import com.deepsky.generated.plsql.PLSqlParser;
import com.deepsky.lang.plsql.struct.parser.PLSqlFilteredLexer;
import antlr.collections.AST;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;


public class ASTParseHelperTest extends TestCase {

    public void test0(){
        AST ast = parseString("abc as r", "table_alias");
        TableAliasDTO dto = ASTParseHelper.parseTableAlias(ast);
        assertEquals("abc", dto.getTable());
        assertEquals("", dto.getSchema());
        assertEquals("", dto.getLink());
        assertEquals("r", dto.getAlias());

        AST ast1 = parseString("abc r", "table_alias");
        TableAliasDTO dto1 = ASTParseHelper.parseTableAlias(ast1);
        assertEquals("abc", dto1.getTable());
        assertEquals("", dto1.getSchema());
        assertEquals("", dto1.getLink());
        assertEquals("r", dto1.getAlias());

        AST ast2 = parseString("xdv.abc r", "table_alias");
        TableAliasDTO dto2 = ASTParseHelper.parseTableAlias(ast2);
        assertEquals("abc", dto2.getTable());
        assertEquals("xdv", dto2.getSchema());
        assertEquals("", dto2.getLink());
        assertEquals("r", dto2.getAlias());

        AST ast3 = parseString("abc@remote_dot1 r", "table_alias");
        TableAliasDTO dto3 = ASTParseHelper.parseTableAlias(ast3);
        assertEquals("abc", dto3.getTable());
        assertEquals("", dto3.getSchema());
        assertEquals("remote_dot1", dto3.getLink());
        assertEquals("r", dto3.getAlias());

        AST ast4 = parseString("xdv.abc@remote_dot1 AS r0", "table_alias");
        TableAliasDTO dto4 = ASTParseHelper.parseTableAlias(ast4);
        assertEquals("abc", dto4.getTable());
        assertEquals("xdv", dto4.getSchema());
        assertEquals("remote_dot1", dto4.getLink());
        assertEquals("r0", dto4.getAlias());

        AST ast5 = parseString("abc", "table_alias");
        TableAliasDTO dto5 = ASTParseHelper.parseTableAlias(ast5);
        assertEquals("abc", dto5.getTable());
        assertEquals("", dto5.getSchema());
        assertEquals("", dto5.getLink());
        assertEquals("", dto5.getAlias());
    }

    public void test1(){
        TableAliasDTO dto = ASTParseHelper.parseTableAlias("abc as r");
        assertEquals("abc", dto.getTable());
        assertEquals("", dto.getSchema());
        assertEquals("", dto.getLink());
        assertEquals("r", dto.getAlias());

        TableAliasDTO dto1 = ASTParseHelper.parseTableAlias("abc r");
        assertEquals("abc", dto1.getTable());
        assertEquals("", dto1.getSchema());
        assertEquals("", dto1.getLink());
        assertEquals("r", dto1.getAlias());

        TableAliasDTO dto2 = ASTParseHelper.parseTableAlias("xdv.abc r");
        assertEquals("abc", dto2.getTable());
        assertEquals("xdv", dto2.getSchema());
        assertEquals("", dto2.getLink());
        assertEquals("r", dto2.getAlias());

        TableAliasDTO dto3 = ASTParseHelper.parseTableAlias("abc@remote_dot1 r");
        assertEquals("abc", dto3.getTable());
        assertEquals("", dto3.getSchema());
        assertEquals("remote_dot1", dto3.getLink());
        assertEquals("r", dto3.getAlias());

        TableAliasDTO dto4 = ASTParseHelper.parseTableAlias("xdv.abc@remote_dot1 AS r0");
        assertEquals("abc", dto4.getTable());
        assertEquals("xdv", dto4.getSchema());
        assertEquals("remote_dot1", dto4.getLink());
        assertEquals("r0", dto4.getAlias());

        TableAliasDTO dto5 = ASTParseHelper.parseTableAlias("abc");
        assertEquals("abc", dto5.getTable());
        assertEquals("", dto5.getSchema());
        assertEquals("", dto5.getLink());
        assertEquals("", dto5.getAlias());
    }


    AST parseString(String text, String method){
        Reader in = new StringReader(text);
        PLSqlFilteredLexer lexer = new PLSqlFilteredLexerImpl(in);
        PLSqlParser parser = new PLSqlParser(lexer);

        try {
            Method m = PLSqlParser.class.getMethod(method);
            m.invoke(parser);
            return parser.getAST();
//        } catch (RecognitionException e) {
//            throw new ParserException("Could not parse sql script", e);
//        } catch (TokenStreamException e) {
//            throw new ParserException("Could not parse sql script", e);
        } catch (NoSuchMethodException e) {
            throw new ParserException("Could not parse sql script", e);
        } catch (InvocationTargetException e) {
            throw new ParserException("Could not parse sql script", e);
        } catch (IllegalAccessException e) {
            throw new ParserException("Could not parse sql script", e);
        }
    }
}
