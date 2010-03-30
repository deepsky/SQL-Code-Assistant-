package test.deepsky.integration;

import junit.framework.TestCase;

import java.net.URISyntaxException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import com.deepsky.utils.StringUtils;
import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.integration.PLSqlLexerEx;
import antlr.TokenStreamException;
import antlr.Token;

public class PlSqlLexerExTest extends TestCase {

    String path = "db_init/tab/hard";
    String ad_sql = "ad.sql";
    File base;

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource(path).toURI());
    }


    public void testPlSqlParserAdopted() throws IOException {
        // load file into string
        String content = StringUtils.file2string(new File(base, ad_sql));
        StringReader r = new StringReader(content);

        PLSqlLexerEx lexer = new PLSqlLexerEx(r);

        try {
            while (true) {
                Token t = null;
                t = lexer.nextToken();
                if (t.getType() == PLSqlTokenTypes.EOF) {
                    break;
                }
            }
        } catch (TokenStreamException e) {
            e.printStackTrace();
        }

    }
}
