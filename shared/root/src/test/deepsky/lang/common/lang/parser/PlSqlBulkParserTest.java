package test.deepsky.lang.common.lang.parser;

import junit.framework.TestCase;

import java.net.URISyntaxException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.Reader;

import com.deepsky.generated.plsql.PLSqlParser;
import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.utils.StringUtils;
import com.deepsky.integration.PLSqlLexerEx;
import com.deepsky.lang.lexer.PlSqlBaseLexer;
import org.apache.log4j.Logger;
import antlr.collections.AST;
import antlr.TokenStreamException;
import antlr.Token;
import antlr.RecognitionException;

public class PlSqlBulkParserTest extends TestCase {

    Logger log = Logger.getLogger(this.getClass());

    String insert_test_func2 = "insert_test_func2.sql";
    String xdv_scheduler_pkg = "xdv_scheduler_pkg.pkb";
    String xdv_adapter_pkg = "xdv_adapter_pkg.pkb";
    String test_adapter_pkg1 = "test_adapter_pkg1.pkb";
    String xdv_flow_manager_control_pkg = "xdv_flow_manager_control_pkg.pkb";

    File base;
    PlSqlBaseLexer lexer;

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("plsql_samples").toURI());
        lexer = new PlSqlBaseLexer();
    }

    public void test_test_adapter_pkg1() throws IOException {
        // load file into string
        log.info("Parse " + test_adapter_pkg1);
        String content = StringUtils.file2string(new File(base, test_adapter_pkg1));

        StringReader r = new StringReader(content);
        PLSqlLexerFiltered lexer = new PLSqlLexerFiltered(r);
        PLSqlParser parser = new PLSqlParser(lexer);

        long ms = System.currentTimeMillis();
        try {

            parser.start_rule();

            AST ast = parser.getAST();
            assert ast != null: "AST tree is null!";
            int hh=0;
        } catch (RecognitionException e) {
            e.printStackTrace();
        } catch (TokenStreamException e) {
            e.printStackTrace();
        }

        ms = System.currentTimeMillis() - ms;
        log.info("Time spend on parsing, ms: " + ms);
    }

    public void test_xdv_adapter_pkg() throws IOException {
        // load file into string
        log.info("Parse " + xdv_adapter_pkg);
        String content = StringUtils.file2string(new File(base, xdv_adapter_pkg));

        StringReader r = new StringReader(content);
        PLSqlLexerFiltered lexer = new PLSqlLexerFiltered(r);
        PLSqlParser parser = new PLSqlParser(lexer);

        long ms = System.currentTimeMillis();
        try {

            parser.start_rule();

            AST ast = parser.getAST();
            assert ast != null: "AST tree is null!";
            int hh=0;
        } catch (RecognitionException e) {
            e.printStackTrace();
        } catch (TokenStreamException e) {
            e.printStackTrace();
        }

        ms = System.currentTimeMillis() - ms;
        log.info("Time spend on parsing, ms: " + ms);
    }


    public void test_insert_test_func2() throws IOException {
        // load file into string
        log.info("Parse " + insert_test_func2);
        String content = StringUtils.file2string(new File(base, insert_test_func2));

        StringReader r = new StringReader(content);
        PLSqlLexerFiltered lexer = new PLSqlLexerFiltered(r);
        PLSqlParser parser = new PLSqlParser(lexer);

        long ms = System.currentTimeMillis();
        try {

            parser.start_rule();

            AST ast = parser.getAST();
            assert ast != null: "AST tree is null!";

            int hh=0;
        } catch (RecognitionException e) {
            e.printStackTrace();
        } catch (TokenStreamException e) {
            e.printStackTrace();
        }

        ms = System.currentTimeMillis() - ms;
        log.info("Time spend on parsing, ms: " + ms);
    }


    public void test_xdv_scheduler_pkg() throws IOException {
        // load file into string
        log.info("Parse " + xdv_scheduler_pkg + " ...");
        String content = StringUtils.file2string(new File(base, xdv_scheduler_pkg));

        StringReader r = new StringReader(content);
        PLSqlLexerFiltered lexer = new PLSqlLexerFiltered(r);
        PLSqlParser parser = new PLSqlParser(lexer);

        long ms = System.currentTimeMillis();
        try {

            parser.start_rule();

            AST ast = parser.getAST();
            assert ast != null: "AST tree is null!";
            int hh=0;
        } catch (RecognitionException e) {
            e.printStackTrace();
        } catch (TokenStreamException e) {
            e.printStackTrace();
        }

        ms = System.currentTimeMillis() - ms;
        log.info("Time spend on parsing, ms: " + ms);
    }



    class PLSqlLexerFiltered extends PLSqlLexerEx {

        public PLSqlLexerFiltered(Reader in) {
            super(in);
        }

        public Token nextToken() throws TokenStreamException{
            Token t = super.nextToken();
            if(t != null){
                if(t.getType() == PLSqlTokenTypes.WS || t.getType() == PLSqlTokenTypes.LF
                    || t.getType() == PLSqlTokenTypes.SL_COMMENT
                    || t.getType() == PLSqlTokenTypes.ML_COMMENT
                    || t.getType() == PLSqlTokenTypes.BAD_ML_COMMENT){
                    return nextToken();
                }
            } else {
                int hh=0;
            }

            return t;
        }

        public void rewind(int pos){
            super.rewind(pos);
        }


        public int mark() {
            int mark = super.mark();
            return mark;
        }
    }

}
