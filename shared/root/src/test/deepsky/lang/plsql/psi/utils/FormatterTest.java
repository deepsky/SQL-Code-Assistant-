package test.deepsky.lang.plsql.psi.utils;

import junit.framework.TestCase;
import com.deepsky.lang.plsql.psi.utils.Formatter;

public class FormatterTest extends TestCase {

    public void test_sql2htmlBase(){
        // case 1
        String html = Formatter.sql2htmlBase("select *\nfrom dual");
        html = html.replace("<html>", "").replace("</html>", "");
        String[] parts = html.split("<br>");

        assertEquals(2, parts.length);
        assertEquals("select *", parts[0]);
        assertEquals("from dual", parts[1]);

        // case 2
        String html2 = Formatter.sql2htmlBase("select 'asdf354', '47484jsjs', '373773 eje' \nfrom dual", 20, 2);
        html2 = html2.replace("<html>", "").replace("</html>", "");
        String[] parts2 = html2.split("<br>");

        assertEquals(2, parts2.length);
        assertEquals("select 'asdf354' ...", parts2[0]);
        assertEquals("from dual", parts2[1]);

        // case 3
        String html3 = Formatter.sql2htmlBase("select 'asdf354', '47484jsjs', '373773 eje' \nfrom dual\nwhere 1 = 3", 20, 2);
        html3 = html3.replace("<html>", "").replace("</html>", "");
        String[] parts3 = html3.split("<br>");

        assertEquals(2, parts3.length);
        assertEquals("select 'asdf354' ...", parts3[0]);
        assertEquals("from dual ...", parts3[1]);
    }
}
