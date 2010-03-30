package test.deepsky.lang.plsql.struct.parser;

import junit.framework.TestCase;

import java.io.File;
import java.io.Reader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.ArrayList;

import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.parser.WrappedPackageException;

public class PlSqlASTParserTest extends TestCase {

    String xdv_dynamic_reference_pkg_pks = "xdv_dynamic_reference_pkg.pks";
    String xdv_data_flow_pkg_pks = "xdv_data_flow_pkg.pks";
    String xdv_data_flow_control_pkg_pks = "xdv_data_flow_control_pkg.pks";
    String xdv_agr_dt_bloat_pkg_pks = "xdv_agr_dt_bloat_pkg.pks";
    String xdv_scheduler_pkg_pks = "xdv_scheduler_pkg.pks";
    String xdv_partition_pkg_pks = "xdv_partition_pkg.pks";
    String xdv_partition_control_pks = "xdv_partition_control_pkg.pks";
    String xdv_purge_pkg_pks = "xdv_purge_pkg.pks";
    String xdv_reference_transfer_pkg_pks = "xdv_reference_transfer_pkg.pks";
    String xdv_aq_pkg_pks = "xdv_aq_pkg.pks";
    String xdv_logger_pkg_pks = "xdv_logger_pkg.pks";

    String xdv_generic_const_pkg_pks = "xdv_generic_const_pkg.pks";
    String xdv_dynamic_reference_pkg_pkb = "xdv_dynamic_reference_pkg.pkb";
    String xdv_data_flow_pkg_pkb = "xdv_data_flow_pkg.pkb";
    String xdv_data_flow_control_pkg_pkb = "xdv_data_flow_control_pkg.pkb";
    String xdv_agr_dt_bloat_pkg_pkb = "xdv_agr_dt_bloat_pkg.pkb";
    String xdv_scheduler_pkg_pkb = "xdv_scheduler_pkg.pkb";
    String xdv_partition_pkg_pkb = "xdv_partition_pkg.pkb";
    String xdv_partition_control_pkb = "xdv_partition_control_pkg.pkb";
    String xdv_purge_pkg_pkb = "xdv_purge_pkg.pkb";
    String xdv_reference_transfer_pkg_pkb = "xdv_reference_transfer_pkg.pkb";
    String xdv_aq_pkg_pkb = "xdv_aq_pkg.pkb";
    String xdv_adapter_pkg_pkb = "xdv_adapter_pkg.pkb";
    String xdv_flow_manager_pkg_pks = "xdv_flow_manager_pkg.pks";
    String xdv_path_type_pkg_pks = "xdv_path_type_pkg.pks";

    String xdv_xdr_parser_aq_pkg_pkb = "xdv_xdr_parser_aq_pkg.pkb";
    String xdv_xdr_parser_aq_pkg_pks = "xdv_xdr_parser_aq_pkg.pks";

    // !!!    String standard_pks = "standard.pks";
    String dbms_standard_pks = "dbms_standard.pks";
    String utl_encode_pks = "utl_encode.pks";
    String utl_http_pks = "utl_http.pks";
    String utl_smtp_pks = "utl_smtp.pks";
    String utl_url_pks = "utl_url.pks";
    String xdv_adapter_pkg_pks = "xdv_adapter_pkg.pks";
    String utl_raw_pks = "utl_raw.pks";
    String utl_mail_pks = "utl_mail.pks";

    String diutil_pks = "diutil.pks";
    String pidl_pks = "pidl.pks";
    String plitblm_pks = "plitblm.pks";
    String dbms_pikler_pks = "dbms_pikler.pks";
    String utl_tcp_pks = "utl_tcp.pks";
    String dbms_java_test_pks = "dbms_java_test.pks";

    String dbms_sql_pks = "dbms_sql.pks";
    String dbms_sqlplus_script_pks = "dbms_sqlplus_script.pks";
    String dbms_sql_11_pks = "dbms_sql_11.pks";
    String dbms_transaction_pks = "dbms_transaction.pks";
    String dbms_space_admin_pks = "dbms_space_admin.pks";
    String utl_nla_pks = "utl_nla.pks";
    String utl_lms_pks = "utl_lms.pks";

    String dbms_lob_pks = "dbms_lob.pks";
    String dbms_application_info_pks = "dbms_application_info.pks";
    String dbms_output_pks = "dbms_output.pks";
    String dbms_space_pks = "dbms_space.pks";

    String dbms_export_extension_pks = "dbms_export_extension.pks";
    String odciconst_pks = "odciconst.pks";
    String dbms_odci_pks = "dbms_odci.pks";
    String dbms_resource_manager_pks = "dbms_resource_manager.pks";
    String dbms_psp_pks = "dbms_psp.pks";
    String dbms_debug_pks = "dbms_debug.pks";
    String dbms_index_utl_pks = "dbms_index_utl.pks";

    String dbms_scheduler_pks = "dbms_scheduler.pks";
    String utl_i18n_pks = "utl_i18n.pks";
    String dbms_obfuscation_toolkit_pks = "dbms_obfuscation_toolkit.pks";
    String OWA_UTIL_PKS = "OWA_UTIL.PKS";
    String owa_pks = "owa.pks";
    String dbms_metadata_pks = "dbms_metadata.pks";
    String dbms_aw_pks = "dbms_aw.pks";

    String dbms_datapump_pks = "dbms_datapump.pks";
    String dbms_snapshot_pks = "dbms_snapshot.pks";
    String dbms_reputil2_pks = "dbms_reputil2.pks";
    String dbms_ldap_pks = "dbms_ldap.pks";
    String dbms_frequent_itemset_pks = "dbms_frequent_itemset.pks";
    String dbms_ldap_utl_pks = "dbms_ldap_utl.pks";
    String dbms_java_pks = "dbms_java.pks";
    String sqljutl2_pks = "sqljutl2.pks";
    String dbms_xmlquery_pks = "dbms_xmlquery.pks";
    String dbms_xmlsave_pks = "dbms_xmlsave.pks";
    String dbms_xqueryint_pks = "dbms_xqueryint.pks";
    String test_pkg_pks = "test_pkg.pks";
    String test_select_01 = "select_01.sql";

    String XDV_WAP_ADAPTER_PKG = "XDV_WAP_ADAPTER_PKG.PKB";

    File base;
    PlSqlASTParser parser = new PlSqlASTParser();

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("ast_processing").toURI());
    }

    public void test_XDV_WAP_ADAPTER_PKG() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XDV_WAP_ADAPTER_PKG));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageBody);
    }

    public void test_test_select_01() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, test_select_01));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof SelectStatement);
    }

    public void test_test_pkg_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, test_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_xqueryint_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_xqueryint_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_datapump_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_datapump_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_xmlsave_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_xmlsave_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_xmlquery_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_xmlquery_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_sqljutl2_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, sqljutl2_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_java_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_java_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_ldap_utl_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_ldap_utl_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_frequent_itemset_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_frequent_itemset_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_ldap_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_ldap_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_reputil2_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_reputil2_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_snapshot_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_snapshot_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_aw_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_aw_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_metadata_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_metadata_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_owa_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, owa_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_OWA_UTIL_PKS() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, OWA_UTIL_PKS));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_obfuscation_toolkit_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_obfuscation_toolkit_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_utl_i18n_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, utl_i18n_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_scheduler_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_scheduler_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_index_utl_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_index_utl_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_debug_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_debug_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }
    
    public void test_dbms_psp_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_psp_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_resource_manager_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_resource_manager_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_odci_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_odci_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_odciconst_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, odciconst_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_export_extension_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_export_extension_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }
    
    public void test_dbms_space_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_space_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_output_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_output_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_application_info_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_application_info_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_utl_lms_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, utl_lms_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_lob_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_lob_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_utl_nla_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, utl_nla_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_transaction_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_transaction_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_space_admin_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_space_admin_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_java_test_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_java_test_pks));
        try {
            PlSqlElement[] elems = parser.parseStream(r);
            assertTrue(false);
        } catch(WrappedPackageException e){
            assertTrue(true);
        }
    }

    public void test_utl_tcp_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, utl_tcp_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_pikler_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_pikler_pks));
        try {
            PlSqlElement[] elems = parser.parseStream(r);
            assertTrue(false);
        } catch(WrappedPackageException e){
            assertTrue(true);
        }
    }

    public void test_plitblm_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, plitblm_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_pidl_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, pidl_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_diutil_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, diutil_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_utl_mail_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, utl_mail_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_utl_raw_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, utl_raw_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_xdv_adapter_pkg_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_adapter_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_dbms_standard_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, dbms_standard_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_utl_http_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, utl_http_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_utl_smtp_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, utl_smtp_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_utl_url_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, utl_url_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_utl_encode_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, utl_encode_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_xdv_aq_pkg_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_aq_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_xdv_reference_transfer_pkg_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_reference_transfer_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_xdv_scheduler_pkg_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_scheduler_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_xdv_purge_pkg_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_purge_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_xdv_partition_pkg_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_partition_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_xdv_partition_control_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_partition_control_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_xdv_dynamic_reference_pkg_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_dynamic_reference_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);

        PackageSpec spec = (PackageSpec) elems[0];
        assertEquals(51, spec.getObjects().length);

        ProcedureSpec[] proc = ClassHelper.filterOutProcedureSpec(spec.getObjects());
        assertEquals(14, proc.length);

        FunctionSpec[] func = ClassHelper.filterOutFunctionSpec(spec.getObjects());
        assertEquals(18, func.length);

        TableCollectionDecl[] recTypes = ClassHelper.filterOutTableCollectionDecl(spec.getObjects()); //AssocArrayDecl(spec.getObjects());
        assertEquals(10, recTypes.length);
    }

    public void test_xdv_path_type_pkg_pks()throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_path_type_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(2, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);

        assertTrue(elems[1] instanceof PackageBody);
        
        PackageSpec spec = (PackageSpec) elems[0];
        assertEquals(52, spec.getObjects().length);

        ProcedureSpec[] proc = ClassHelper.filterOutProcedureSpec(spec.getObjects());
        assertEquals(1, proc.length);

        FunctionSpec[] func = ClassHelper.filterOutFunctionSpec(spec.getObjects());
        assertEquals(0, func.length);

//        AssocArrayDecl[] recTypes = ClassHelper.filterOutAssocArrayDecl(spec.getObjects());
        TableCollectionDecl[] recTypes = ClassHelper.filterOutTableCollectionDecl(spec.getObjects()); //AssocArrayDecl(spec.getObjects());
        assertEquals(2, recTypes.length);
    }

    public void test_xdv_flow_manager_pkg_pks() throws FileNotFoundException {

        Reader r = new FileReader(new File(base, xdv_flow_manager_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);

        PackageSpec spec = (PackageSpec) elems[0];
        assertEquals(72, spec.getObjects().length);

        ProcedureSpec[] proc = ClassHelper.filterOutProcedureSpec(spec.getObjects());
        assertEquals(4, proc.length);

        FunctionSpec[] func = ClassHelper.filterOutFunctionSpec(spec.getObjects());
        assertEquals(3, func.length);

//        AssocArrayDecl[] recTypes = ClassHelper.filterOutAssocArrayDecl(spec.getObjects());
        TableCollectionDecl[] recTypes = ClassHelper.filterOutTableCollectionDecl(spec.getObjects()); //AssocArrayDecl(spec.getObjects());
        assertEquals(4, recTypes.length);
    }


    public void test_xdv_data_flow_pkg_pks() throws FileNotFoundException {

        Reader r = new FileReader(new File(base, xdv_data_flow_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);

        PackageSpec spec = (PackageSpec) elems[0];
        assertEquals(25, spec.getObjects().length);

        ProcedureSpec[] proc = ClassHelper.filterOutProcedureSpec(spec.getObjects());
        assertEquals(1, proc.length);

        FunctionSpec[] func = ClassHelper.filterOutFunctionSpec(spec.getObjects());
        assertEquals(0, func.length);

//        AssocArrayDecl[] recTypes = ClassHelper.filterOutAssocArrayDecl(spec.getObjects());
        TableCollectionDecl[] recTypes = ClassHelper.filterOutTableCollectionDecl(spec.getObjects()); //AssocArrayDecl(spec.getObjects());
        assertEquals(2, recTypes.length);
    }




    public void test_dbms_sqlplus_script_pks() throws FileNotFoundException {

        Reader r = new FileReader(new File(base, dbms_sqlplus_script_pks));
        try {
            PlSqlElement[] elems = parser.parseStream(r);
            assertTrue(false);
        }catch(WrappedPackageException e){
            assertTrue(true);
        }
    }

    public void test_dbms_sql_pks() throws FileNotFoundException {

        Reader r = new FileReader(new File(base, dbms_sql_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);

        PackageSpec spec = (PackageSpec) elems[0];
        assertEquals(209, spec.getObjects().length);

        ProcedureSpec[] proc = ClassHelper.filterOutProcedureSpec(spec.getObjects());
        assertEquals(173, proc.length);

        FunctionSpec[] func = ClassHelper.filterOutFunctionSpec(spec.getObjects());
        assertEquals(9, func.length);

//        AssocArrayDecl[] recTypes = ClassHelper.filterOutAssocArrayDecl(spec.getObjects());
        TableCollectionDecl[] recTypes = ClassHelper.filterOutTableCollectionDecl(spec.getObjects()); //AssocArrayDecl(spec.getObjects());
        assertEquals(20, recTypes.length);
    }

    public void test_dbms_sql_11_pks() throws FileNotFoundException {

        Reader r = new FileReader(new File(base, dbms_sql_11_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);

        PackageSpec spec = (PackageSpec) elems[0];
        assertEquals(244, spec.getObjects().length);

        ProcedureSpec[] proc = ClassHelper.filterOutProcedureSpec(spec.getObjects());
        assertEquals(203, proc.length);

        FunctionSpec[] func = ClassHelper.filterOutFunctionSpec(spec.getObjects());
        assertEquals(12, func.length);

//        AssocArrayDecl[] recTypes = ClassHelper.filterOutAssocArrayDecl(spec.getObjects());
        TableCollectionDecl[] recTypes = ClassHelper.filterOutTableCollectionDecl(spec.getObjects()); //AssocArrayDecl(spec.getObjects());
        assertEquals(21, recTypes.length);
    }



    public void test_xdv_aq_pkg_pkb() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_aq_pkg_pkb));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageBody);
    }

    public void test_xdv_reference_transfer_pkg_pkb() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_reference_transfer_pkg_pkb));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageBody);
    }

    public void test_xdv_scheduler_pkg_pkb() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_scheduler_pkg_pkb));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageBody);
    }

    public void test_xdv_purge_pkg_pkb() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_purge_pkg_pkb));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageBody);
    }

    public void test_xdv_partition_pkg_pkb() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_partition_pkg_pkb));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageBody);
    }

    public void test_xdv_partition_control_pkb() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_partition_control_pkb));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageBody);
    }




    public void test_xdv_generic_const_pkg_pks() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_generic_const_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);

        PackageSpec body = (PackageSpec) elems[0];
        assertEquals("xdv_generic_const_pkg",body.getPackageName().toLowerCase());
//        assertEquals(51, body..getObjects().length);
//
//        ProcedureSpec[] proc = ClassHelper.filterOutProcedureSpec(spec.getObjects());
//        assertEquals(14, proc.length);
//
//        FunctionSpec[] func = ClassHelper.filterOutFunctionSpec(spec.getObjects());
//        assertEquals(18, func.length);
//
////        AssocArrayDecl[] recTypes = ClassHelper.filterOutAssocArrayDecl(spec.getObjects());
//        TableCollectionDecl[] recTypes = ClassHelper.filterOutTableCollectionDecl(spec.getObjects()); //AssocArrayDecl(spec.getObjects());
//        assertEquals(10, recTypes.length);
    }

    public void test_xdv_dynamic_reference_pkg_pkb() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_dynamic_reference_pkg_pkb));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageBody);

        PackageBody body = (PackageBody) elems[0];
        assertEquals("xdv_dynamic_reference_pkg",body.getPackageName().toLowerCase());
//        assertEquals(51, body..getObjects().length);
//
//        ProcedureSpec[] proc = ClassHelper.filterOutProcedureSpec(spec.getObjects());
//        assertEquals(14, proc.length);
//
//        FunctionSpec[] func = ClassHelper.filterOutFunctionSpec(spec.getObjects());
//        assertEquals(18, func.length);
//
////        AssocArrayDecl[] recTypes = ClassHelper.filterOutAssocArrayDecl(spec.getObjects());
//        TableCollectionDecl[] recTypes = ClassHelper.filterOutTableCollectionDecl(spec.getObjects()); //AssocArrayDecl(spec.getObjects());
//        assertEquals(10, recTypes.length);
    }


    public void test_xdv_data_flow_pkg_pkb() throws FileNotFoundException {

        Reader r = new FileReader(new File(base, xdv_data_flow_pkg_pkb));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageBody);

        PackageBody body = (PackageBody) elems[0];
//        assertEquals(25, body.getObjects().length);

//        ProcedureSpec[] proc = ClassHelper.filterOutProcedureSpec(spec.getObjects());
//        assertEquals(1, proc.length);
//
//        FunctionSpec[] func = ClassHelper.filterOutFunctionSpec(spec.getObjects());
//        assertEquals(0, func.length);
//
//        AssocArrayDecl[] recTypes = ClassHelper.filterOutAssocArrayDecl(spec.getObjects());
//        assertEquals(2, recTypes.length);
    }

    public void test_xdv_adapter_pkg_pkb() throws FileNotFoundException {

        Reader r = new FileReader(new File(base, xdv_adapter_pkg_pkb));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(2, elems.length );
        assertTrue(elems[0] instanceof PackageBody);
        assertTrue(elems[1] instanceof SqlPlusCommand);

//        PackageSpec spec = (PackageSpec) elems[0];
//        assertEquals(25, spec.getObjects().length);
//
//        ProcedureSpec[] proc = ClassHelper.filterOutProcedureSpec(spec.getObjects());
//        assertEquals(1, proc.length);
//
//        FunctionSpec[] func = ClassHelper.filterOutFunctionSpec(spec.getObjects());
//        assertEquals(0, func.length);
//
//        AssocArrayDecl[] recTypes = ClassHelper.filterOutAssocArrayDecl(spec.getObjects());
//        assertEquals(2, recTypes.length);
    }


    public void test_xdv_xdr_parser_aq_pkg_pkb() throws FileNotFoundException {

        Reader r = new FileReader(new File(base, xdv_xdr_parser_aq_pkg_pkb));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageBody);
    }

    public void test_xdv_xdr_parser_aq_pkg_pks() throws FileNotFoundException {

        Reader r = new FileReader(new File(base, xdv_xdr_parser_aq_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);
    }

    public void test_xdv_logger_pkg_pks() throws FileNotFoundException {

        Reader r = new FileReader(new File(base, xdv_logger_pkg_pks));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof PackageSpec);

        PackageSpec spec = (PackageSpec) elems[0];
        assertEquals(131, spec.getObjects().length);

        ProcedureSpec[] proc = ClassHelper.filterOutProcedureSpec(spec.getObjects());
        assertEquals(30, proc.length);

        FunctionSpec[] func = ClassHelper.filterOutFunctionSpec(spec.getObjects());
        assertEquals(16, func.length);

        RecordTypeDecl[] rtypes = ClassHelper.filterOutRecordTypeDecl(spec.getObjects());
        assertEquals(4, rtypes.length);

//        AssocArrayDecl[] recTypes = ClassHelper.filterOutAssocArrayDecl(spec.getObjects());
        TableCollectionDecl[] recTypes = ClassHelper.filterOutTableCollectionDecl(spec.getObjects()); //AssocArrayDecl(spec.getObjects());
        assertEquals(3, recTypes.length);

        VariableDecl[] vars = ClassHelper.filterOutVarDeclaration(spec.getObjects());
        assertEquals(78, vars.length);

    }


    static class ClassHelper<T> {
        public static ProcedureSpec[] filterOutProcedureSpec(PlSqlElement[] elems){
            List<ProcedureSpec> out = new ArrayList<ProcedureSpec>();
            for(PlSqlElement e: elems){
                if(e instanceof ProcedureSpec){
                    out.add((ProcedureSpec) e);
                }
            }
            return out.toArray(new ProcedureSpec[0]);
        }

        public static FunctionSpec[] filterOutFunctionSpec(PlSqlElement[] elems){
            List<FunctionSpec> out = new ArrayList<FunctionSpec>();
            for(PlSqlElement e: elems){
                if(e instanceof FunctionSpec){
                    out.add((FunctionSpec) e);
                }
            }
            return out.toArray(new FunctionSpec[0]);
        }


//        public static TableCollectionDecl[] filterOutAssocArrayDecl(PlSqlElement[] elems){
        public static TableCollectionDecl[] filterOutTableCollectionDecl(PlSqlElement[] elems){
            List<TableCollectionDecl> out = new ArrayList<TableCollectionDecl>();
            for(PlSqlElement e: elems){
                if(e instanceof TableCollectionDecl){ //AssocArrayDecl){
                    out.add((TableCollectionDecl) e);
                }
            }
            return out.toArray(new TableCollectionDecl[0]);
        }

        public static VariableDecl[] filterOutVarDeclaration(PlSqlElement[] elems){
            List<VariableDecl> out = new ArrayList<VariableDecl>();
            for(PlSqlElement e: elems){
                if(e instanceof VariableDecl){
                    out.add((VariableDecl) e);
                }
            }
            return out.toArray(new VariableDecl[0]);
        }

        public static RecordTypeDecl[] filterOutRecordTypeDecl(PlSqlElement[] elems){
            List<RecordTypeDecl> out = new ArrayList<RecordTypeDecl>();
            for(PlSqlElement e: elems){
                if(e instanceof RecordTypeDecl){
                    out.add((RecordTypeDecl) e);
                }
            }
            return out.toArray(new RecordTypeDecl[0]);
        }

    }
}
