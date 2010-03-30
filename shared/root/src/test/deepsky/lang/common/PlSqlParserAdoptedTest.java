package test.deepsky.lang.common;

import com.deepsky.lang.plsql.struct.parser.PLSqlFilteredLexerImpl;
import junit.framework.TestCase;

import java.net.URISyntaxException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.Reader;

import com.deepsky.utils.StringUtils;
import com.deepsky.lang.plsql.struct.parser.PLSqlFilteredLexer;
import com.deepsky.generated.plsql.adopted.PLSqlParserAdopted;
import com.deepsky.generated.plsql.PLSqlParser;
import com.deepsky.integration.PLSqlLexerEx;
import antlr.collections.AST;
import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.Token;
import org.apache.log4j.Logger;

public class PlSqlParserAdoptedTest extends TestCase {

    Logger log = Logger.getLogger(this.getClass());

    String insert_test_func1 = "insert_test_func1.sql";
    String select_from_dual0 = "select_from_dual0.sql";
    String xdv_adapter_pkg_base = "xdv_adapter_pkg_base.sql";
    String xdv_adapter_pkg = "xdv_adapter_pkg.sql";
    File base;

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("plsql_level0").toURI());
//        lexer = new PlSqlLexerT();
    }


    public void testPlSqlParserAdopted() throws IOException {
        // load file into string
        String content = StringUtils.file2string(new File(base, xdv_adapter_pkg));
//        String content = Helpers.loadFile(new File(base, insert_test_func1));

        StringReader r = new StringReader(content);
        PLSqlLexerFilteredAdopted lexer = new PLSqlLexerFilteredAdopted(r);
        PLSqlParserAdopted parser = new PLSqlParserAdopted(lexer);

        long ms = System.currentTimeMillis();
        try {

            parser.start_rule();

            AST ast = parser.getAST();

            int hh=0;
        } catch (RecognitionException e) {
            e.printStackTrace();
        } catch (TokenStreamException e) {
            e.printStackTrace();
        }

        ms = System.currentTimeMillis() - ms;
        log.info("Time spend on parsing (adopted parser), ms: " + ms);
        int kk=0;
    }


    public void testPlSqlParser() throws IOException {
        // load file into string
        String content = StringUtils.file2string(new File(base, xdv_adapter_pkg));
//        String content = Helpers.loadFile(new File(base, insert_test_func1));

        StringReader r = new StringReader(content);
        PLSqlFilteredLexer lexer = new PLSqlFilteredLexerImpl(r);
        PLSqlParser parser = new PLSqlParser(lexer);

        long ms = System.currentTimeMillis();
        try {

            parser.start_rule();

            AST ast = parser.getAST();

            int hh=0;
        } catch (RecognitionException e) {
            e.printStackTrace();
        } catch (TokenStreamException e) {
            e.printStackTrace();
        }

        ms = System.currentTimeMillis() - ms;
        log.info("Time spend on parsing (std parser), ms: " + ms);
        int kk=0;
    }


    public void test0() throws IOException {
        // load file into string
        String content = StringUtils.file2string(new File(base, select_from_dual0));
//        String content = Helpers.loadFile(new File(base, insert_test_func1));

        StringReader r = new StringReader(content);
//        PLSqlFilteredLexer lexer = new PLSqlFilteredLexer(r);

/*
        try {
            PLSqlParser parser0 = new PLSqlParser(lexer);
            InvocationHandler handler = new PlSqlParserHandler(parser0);
            Class proxyClass = Proxy.getProxyClass(
                PlSqlParserGeneric.class.getClassLoader(), new Class[] { PlSqlParserGeneric.class });

            PlSqlParserGeneric parser = (PlSqlParserGeneric) proxyClass.
                getConstructor(new Class[] { InvocationHandler.class }).newInstance(new Object[] { handler });

            long ms = System.currentTimeMillis();

            try {
                parser.start_rule();
                AST ast = parser.getAST();

                int hh=0;
            } catch (RecognitionException e) {
                throw new ParserException("Could not parse sql script", e);
            } catch (TokenStreamException e) {
                throw new ParserException("Could not parse sql script", e);
            }


        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
*/

//        InterfaceExtractor extr = new InterfaceExtractor();
//        extr.extract("com.deepsky.generated.plsql.PLSqlParser", System.out);
//        Method[] meths = PLSqlFilteredLexer.class.getMethods();
/*
        PLSqlParser parser = new PlSqlParseHelper.PLSqlParser2(lexer);
        try {
            parser.start_rule();

            AST ast = parser.getAST();

            int hh=0;
        } catch (RecognitionException e) {
            e.printStackTrace();
        } catch (TokenStreamException e) {
            e.printStackTrace();
        }
*/
        //PlSqlBase[] plsqlObj = PlSqlParseHelper.parseStream(lexer);
        //assertNotNull(plsqlObj);
//        assertTrue(plsqlObj instanceof Function);

        int hh=0;
    }

    class PLSqlLexerFilteredAdopted extends PLSqlLexerEx {

        public PLSqlLexerFilteredAdopted(Reader in) {
            super(in);
        }

        public Token nextToken() throws TokenStreamException{
            Token t = super.nextToken();
            if(t != null){
                if(t.getType() == com.deepsky.generated.plsql.adopted.PLSqlTokenTypes.WS
                    || t.getType() == com.deepsky.generated.plsql.adopted.PLSqlTokenTypes.SL_COMMENT
                    || t.getType() == com.deepsky.generated.plsql.adopted.PLSqlTokenTypes.ML_COMMENT
                    || t.getType() == com.deepsky.generated.plsql.adopted.PLSqlTokenTypes.BAD_ML_COMMENT){
                    return nextToken();
                }
            } else {
                int hh=0;
            }

            return t;
        }

        public void rewind(int pos){
            super.rewind(pos);
//            log.info("[LEXER] rewind(pos): " + pos);
        }


        public int mark() {
            int mark = super.mark();
//            log.info("[LEXER] mark(): " + mark);
            return mark;
        }
    }

}
