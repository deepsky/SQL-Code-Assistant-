package test.deepsky.lang.plsql.struct.parser;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.FileReader;
import java.net.URISyntaxException;

import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.plsql.struct.types.*;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.TableCollectionDecl;
import com.deepsky.lang.plsql.psi.ObjectTypeDecl;
import com.deepsky.lang.plsql.psi.VarrayCollectionDecl;

public class PlSqlASTParser_TypesTest extends TestCase {

    String tn_med_load_msgs = "tn_med_load_msgs.sql";
    String xdv_arr_tag_value_tp = "xdv_arr_tag_value_tp.sql";
    String xdv_flow_status_payload_tp = "xdv_flow_status_payload_tp.sql";
    String xdv_sch_message_tp = "xdv_sch_message_tp.sql";
    String xdv_tag_value_tp = "xdv_tag_value_tp.sql";

    String XSL_AGGRS_TP = "XSL_AGGRS_TP.SQL";
    String XSL_TRANSLATION_TBL_TP = "XSL_TRANSLATION_TBL_TP.SQL";
    String xsl_top_serialized_layout_tp = "xsl_top_serialized_layout_tp.sql";
    String xsl_mtr_ivl_list_tp = "xsl_mtr_ivl_list_tp.sql";
    String tva_xdv_id2str256 = "TVA_XDV_ID2STR256.SQL";

    String tn_job_arguments = "tn_job_arguments.sql";


    File base;
    PlSqlASTParser parser = new PlSqlASTParser();

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("udt").toURI());
    }

    public void test_tn_job_arguments() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, tn_job_arguments));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof TableCollectionDecl);
        assertEquals("tn_job_arguments", ((TableCollectionDecl)elems[0]).getDeclName());

        Type t = ((TableCollectionDecl)elems[0]).getBaseType();
        assertTrue(t instanceof UserDefinedType);
    }

    public void test_tva_xdv_id2str256() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, tva_xdv_id2str256));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof VarrayCollectionDecl);
        assertEquals("tva_xdv_id2str256", ((VarrayCollectionDecl)elems[0]).getDeclName());

        Type t = ((VarrayCollectionDecl)elems[0]).getBaseType();
        assertTrue(TypeFactory.createTypeById(Type.VARCHAR2).equals(t));
        assertEquals("48", ((VarrayCollectionDecl)elems[0]).getCollectionSize().getText());
    }

    public void test_xsl_mtr_ivl_list_tp() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_mtr_ivl_list_tp));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof VarrayCollectionDecl);
        assertEquals("xsl_mtr_ivl_list_tp", ((VarrayCollectionDecl)elems[0]).getDeclName());

        Type t = ((VarrayCollectionDecl)elems[0]).getBaseType();
        assertTrue(TypeFactory.createTypeById(Type.INTEGER).equals(t));
        assertEquals("1000000", ((VarrayCollectionDecl)elems[0]).getCollectionSize().getText());
    }

    public void test_xsl_top_serialized_layout_tp() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xsl_top_serialized_layout_tp));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof VarrayCollectionDecl);
        assertEquals("xsl_top_serialized_layout_tp", ((VarrayCollectionDecl)elems[0]).getDeclName());

        Type t = ((VarrayCollectionDecl)elems[0]).getBaseType();
        assertTrue(TypeFactory.createTypeById(Type.NVARCHAR2).equals(t));
        assertEquals("1000000", ((VarrayCollectionDecl)elems[0]).getCollectionSize().getText());
    }

    public void test_XSL_AGGRS_TP() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XSL_AGGRS_TP));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof TableCollectionDecl);
        assertEquals("XSL_AGGRS_TP", ((TableCollectionDecl)elems[0]).getDeclName());

        Type t = ((TableCollectionDecl)elems[0]).getBaseType();
        assertTrue(t instanceof UserDefinedType);
        assertNull(((UserDefinedType)t).getDefinitionPackage());
        assertEquals("xsl_aggregate_ref_tp", t.typeName());
    }

    public void test_XSL_TRANSLATION_TBL_TP() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XSL_TRANSLATION_TBL_TP));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof TableCollectionDecl);
        assertEquals("XSL_TRANSLATION_TBL_TP", ((TableCollectionDecl)elems[0]).getDeclName());

        Type t = ((TableCollectionDecl)elems[0]).getBaseType();
        assertTrue(t instanceof UserDefinedType);
        assertNull(((UserDefinedType)t).getDefinitionPackage());
        assertEquals("xsl_translation_tp", t.typeName());
    }

    public void test_tn_med_load_msgs() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, tn_med_load_msgs));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof TableCollectionDecl);
        assertEquals("tn_med_load_msgs", ((TableCollectionDecl)elems[0]).getDeclName());

        Type t = ((TableCollectionDecl)elems[0]).getBaseType();
        assertTrue(t instanceof UserDefinedType);
        assertNull(((UserDefinedType)t).getDefinitionPackage());
        assertEquals("med_load_message", ((UserDefinedType)t).typeName());
    }

    public void test_xdv_arr_tag_value_tp() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_arr_tag_value_tp));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof TableCollectionDecl);
        assertEquals("xdv_arr_tag_value_tp", ((TableCollectionDecl)elems[0]).getDeclName());

        Type t = ((TableCollectionDecl)elems[0]).getBaseType();
        assertTrue(t instanceof UserDefinedType);
        assertNull(((UserDefinedType)t).getDefinitionPackage());
        assertEquals("xdv_tag_value_tp", ((UserDefinedType)t).typeName());
    }


    public void test_xdv_flow_status_payload_tp() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_flow_status_payload_tp));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof ObjectTypeDecl);
        assertEquals("xdv_flow_status_payload_tp", ((ObjectTypeDecl)elems[0]).getDeclName());

        ObjectTypeDecl otype = (ObjectTypeDecl)elems[0];
        assertEquals(12, otype.getItems().length);

        assertEquals("sender_id", otype.getItems()[0].getRecordItemName());
        assertTrue(otype.getItems()[0].getType() instanceof Varchar2Type);
        assertEquals(32, otype.getItems()[0].getType().dataLength());

        assertEquals("job_id", otype.getItems()[1].getRecordItemName());
        assertTrue(otype.getItems()[1].getType() instanceof IntegerType);

        assertEquals("job_type", otype.getItems()[2].getRecordItemName());
        assertTrue(otype.getItems()[2].getType() instanceof IntegerType);

        assertEquals("load_id", otype.getItems()[3].getRecordItemName());
        assertTrue(otype.getItems()[3].getType() instanceof NumberType);

        assertEquals("total_rowcount", otype.getItems()[4].getRecordItemName());
        assertTrue(otype.getItems()[4].getType() instanceof IntegerType);

        assertEquals("rdu_rowcount", otype.getItems()[5].getRecordItemName());
        assertTrue(otype.getItems()[5].getType() instanceof IntegerType);

        assertEquals("load_st_dt", otype.getItems()[6].getRecordItemName());
        assertTrue(otype.getItems()[6].getType() instanceof DateType);

        assertEquals("load_st_dde_id", otype.getItems()[7].getRecordItemName());
        assertTrue(otype.getItems()[7].getType() instanceof IntegerType);

        assertEquals("load_st_dte_id", otype.getItems()[8].getRecordItemName());
        assertTrue(otype.getItems()[8].getType() instanceof IntegerType);

        assertEquals("load_end_dde_id", otype.getItems()[9].getRecordItemName());
        assertTrue(otype.getItems()[9].getType() instanceof IntegerType);

        assertEquals("load_end_dte_id", otype.getItems()[10].getRecordItemName());
        assertTrue(otype.getItems()[10].getType() instanceof IntegerType);

        assertEquals("message", otype.getItems()[11].getRecordItemName());
        assertTrue(otype.getItems()[11].getType() instanceof Varchar2Type);
        assertEquals(256, otype.getItems()[11].getType().dataLength());

    }

    public void test_xdv_sch_message_tp() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_sch_message_tp));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof ObjectTypeDecl);
        assertEquals("xdv_sch_message_tp", ((ObjectTypeDecl)elems[0]).getDeclName());
    }

    public void test_xdv_tag_value_tp() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, xdv_tag_value_tp));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof ObjectTypeDecl);
        assertEquals("xdv_tag_value_tp", ((ObjectTypeDecl)elems[0]).getDeclName());
    }
}
