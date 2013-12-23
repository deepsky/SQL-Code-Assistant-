package test.deepsky.lang.plsql.completion2;

public class CtrlStmt_BaseCompletionTest   extends BaseCompletionTest {
    @Override
    public String getPath() {
        return "completion/base/ctrl_statements";
    }

    public void testTop_level_zero() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "country_id", "country_name", "currency_name", "currency_symbol", "region");
    }

    public void testTop_level_create() throws Exception {
        configureByFile(getFilePath());
        assertLookup(myItems, "country_id", "country_name", "currency_name", "currency_symbol", "region");
    }
}
