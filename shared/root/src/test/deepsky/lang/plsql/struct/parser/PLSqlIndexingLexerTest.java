package test.deepsky.lang.plsql.struct.parser;

import antlr.TokenStreamException;
import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.lang.plsql.struct.parser.PLSqlIndexingLexer;
import com.deepsky.lang.plsql.struct.parser.WordProcessor;
import junit.framework.TestCase;

import java.io.Reader;
import java.io.StringReader;

public class PLSqlIndexingLexerTest extends TestCase {

    public void test0() throws TokenStreamException {
        // 1st pass
        Reader in = new StringReader("select * from dual");
        PLSqlIndexingLexer lexer = new PLSqlIndexingLexer(in);

        final StringBuilder sb = new StringBuilder();

        lexer.setWordProcessor(new WordProcessor(){
            public void process(String text) {
                sb.append(text.toLowerCase()).append(".");
            }
        });

        while(lexer.nextToken().getType() != PLSqlTokenTypes.EOF){
            // idle
        }

        String test = sb.toString();
        assertEquals("select.from.dual.", test);

        // 2nd pass
        Reader in2 = new StringReader("INSERT INTO xdv_dev_mobile_station_dt (\n" +
                "                        id,\n" +
                "                        tac,\n" +
                "                        svn,\n" +
                "                        imei,\n" +
                "                        create_date,\n" +
                "                        create_source,\n" +
                "                        group_name1,\n" +
                "                        group_name2,\n" +
                "                        group_name3)\n" +
                "SELECT\n" +
                "                        xdv_dev_mos_seq.NEXTVAL,\n" +
                "                        (CASE WHEN LENGTH(imei) >= 8 THEN SUBSTR(imei, 1,8) ELSE '-1' END),\n" +
                "                        (CASE WHEN LENGTH(imei) >= 16 THEN SUBSTR(imei, 15, 2) ELSE '-1' END),\n" +
                "                        imei,\n" +
                "                        SYSDATE,\n" +
                "                        'bulk_loader',\n" +
                "                        group_name1,\n" +
                "                        group_name2,\n" +
                "                        group_name3\n" +
                "FROM xdv_dev_mobile_station_et");

        lexer.reload(in2);

        final StringBuilder sb2 = new StringBuilder();

        lexer.setWordProcessor(new WordProcessor(){
            public void process(String text) {
                sb2.append(text.toLowerCase()).append(".");
            }
        });

        while(lexer.nextToken().getType() != PLSqlTokenTypes.EOF){
            // idle
        }

        String test2 = sb2.toString();
        assertEquals(
                "insert.into.xdv_dev_mobile_station_dt.id.tac.svn.imei.create_date.create_source" +
                ".group_name1.group_name2.group_name3.select.xdv_dev_mos_seq.nextval.case.when" +
                ".length.imei.then.substr.imei.else.end.case.when.length.imei.then.substr.imei.else" +
                ".end.imei.sysdate.group_name1.group_name2.group_name3.from.xdv_dev_mobile_station_et.",
                test2);

    }
}
