package test.deepsky.lang.plsql.completion2;

public class CaseGenericCompletionTest extends BaseCompletionTest {

    @Override
    public String getPath() {
        return "completion";
    }

    public void test_select_case() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select ca<caret>\n");
        assertLookupFilterOutFunc(myItems, "case");
    }

    public void test_select_case2() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select case <caret>\n");
        assertLookupFilterOutFunc(myItems, "when");
    }

    public void test_select_case3() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select case when <caret>\n");
        assertLookupFilterOutFunc(myItems, "sessiontimezone", "systimestamp", "sysdate", "dbtimezone");
    }

    public void test_select_case30() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select sysdate, case when <caret>\n");
        assertLookupFilterOutFunc(myItems, "sessiontimezone", "systimestamp", "sysdate", "dbtimezone");
    }

    public void test_select_case4() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select case when 1=2 <caret>\n");
        assertLookupFilterOutFunc(myItems, "then");
    }

    public void test_select_case40() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select substr('', 2), '123'||'4', 'oi' || case when 1=2 <caret>\n");
        assertLookupFilterOutFunc(myItems, "then");
    }

    public void test_select_case5() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select case when 1=2 then <caret>\n");
        assertLookupFilterOutFunc(myItems, "systimestamp", "sysdate", "dbtimezone", "sessiontimezone");
    }

    public void test_select_case6() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select case when 1=2 then 1 <caret>\n");
        assertLookupFilterOutFunc(myItems, "end", "else", "when");
    }

    public void test_select_case60() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select case when 1=2 then 123+1 ||'897' <caret>\n");
        assertLookupFilterOutFunc(myItems, "end", "else", "when");
    }

    public void test_select_case7() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select case when 1=2 then 123 else <caret>\n");
        assertLookupFilterOutFunc(myItems, "systimestamp", "sysdate", "dbtimezone", "sessiontimezone");
    }

    public void test_select_case8() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select case when 1=2 then 123 else -1 end <caret>\n");
        assertLookupFilterOutFunc(myItems, "from");
    }

    ////////////
    public void test_select_case11() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select c<caret> from tab1\n");
        assertLookupFilterOutFunc(myItems, "case");
    }
    public void test_select_case21() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select case <caret> from tab1\n");
        assertLookupFilterOutFunc(myItems, "when");
    }

    public void test_select_case31() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select case when <caret>  from tab1\n");
        assertLookupFilterOutFunc(myItems, "systimestamp", "sysdate", "dbtimezone", "sessiontimezone");
    }

    public void test_select_case41() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select case when 1=2 <caret>  from tab1\n");
        assertLookupFilterOutFunc(myItems, "then");
    }

    public void test_select_case51() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select case when 1=2 then <caret>\n  from tab1");
        assertLookupFilterOutFunc(myItems, "systimestamp", "sysdate", "dbtimezone", "sessiontimezone", "when");
    }

    public void test_select_case52() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select sum(case when 1=2 then <caret>)\n  from tab1");
        assertLookupFilterOutFunc(myItems, "when", "systimestamp", "sysdate", "dbtimezone", "sessiontimezone");
    }

    public void test_select_case53() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select substr('', 2), '123'||'4', 'oi' || sum(case when 1=2 then <caret>)\n  from tab1");
        assertLookupFilterOutFunc(myItems, "when", "systimestamp", "sysdate", "dbtimezone", "sessiontimezone");
    }

    public void test_select_case54() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select sum(case when 1=2 then a when <caret>)\n  from tab1");
        assertLookupFilterOutFunc(myItems, "systimestamp", "sysdate", "dbtimezone", "sessiontimezone");
    }

    public void test_select_case55() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select substr('', 2), '123'||'4', 'oi' || sum(case when 1=2 then 123 when <caret>)\n  from tab1");
        assertLookupFilterOutFunc(myItems, "systimestamp", "sysdate", "dbtimezone", "sessiontimezone");
    }

    public void test_select_case61() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select case when 1=2 then 123 <caret>\nfrom tab1");
        assertLookupFilterOutFunc(myItems, "end", "else", "when");
    }

    public void test_select_case71() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select case when 1=2 then 123 else <caret>\nfrom tab1");
        assertLookupFilterOutFunc(myItems, "systimestamp", "sysdate", "dbtimezone", "sessiontimezone");
    }

    public void test_select_case81() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select case when 1=2 then 123 else -1 end <caret>\nfrom tab1");
        assertLookupFilterOutFunc(myItems, "");
    }

    public void test_select_case9() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select case when <caret> then 123 else -1 end as c1\nfrom tab1");
        assertLookupFilterOutFunc(myItems, "a", "event_date", "name", "systimestamp", "sysdate", "dbtimezone", "sessiontimezone");
    }

    public void test_select_case91() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select case when 1 <> 3 then <caret> else -1 end as c1\nfrom tab1");
        assertLookupFilterOutFunc(myItems, "a", "event_date", "name", "systimestamp", "sysdate", "dbtimezone", "sessiontimezone");
    }

    public void test_select_case92() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select case when 1 <> 3 then 12 else <caret> end as c1\nfrom tab1");
        assertLookupFilterOutFunc(myItems, "a", "event_date", "name", "systimestamp", "sysdate", "dbtimezone", "sessiontimezone");
    }

    public void test_select_case93() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select case when 1 <> 3 then 12 when <caret> else 1 end as c1\nfrom tab1");
        assertLookupFilterOutFunc(myItems, "a", "event_date", "name", "systimestamp", "sysdate", "dbtimezone", "sessiontimezone");
    }

    public void test_select_case94() throws Exception {
        configureByText("create table tab1 (a integer, event_date timestamp, name varchar2(20)); \n" +
                "select case when 1 <> 3 then 12 when <caret> then 189 end as c1\nfrom tab1");
        assertLookupFilterOutFunc(myItems, "a", "event_date", "name");
    }
}
