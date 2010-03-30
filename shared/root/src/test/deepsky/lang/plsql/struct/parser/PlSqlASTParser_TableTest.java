package test.deepsky.lang.plsql.struct.parser;

import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.psi.Index;
import com.deepsky.lang.plsql.psi.ddl.AlterTable;
import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;

import java.net.URISyntaxException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.FileReader;

import junit.framework.TestCase;

public class PlSqlASTParser_TableTest extends TestCase {

    String XDV_TRN_STATUS_DT = "XDV_TRN_STATUS_DT.SQL";
    String XSL_RPT_PARAM_T = "XSL_RPT_PARAM_T.SQL";
    String XDV_ASM_TRN_MEDSES_FT = "XDV_ASM_TRN_MEDSES_FT.SQL";
    String XDV_NET_GRP_PATH_EXT_MT = "XDV_NET_GRP_PATH_EXT_MT.SQL";
    String XDV_PRD_LOG_T = "XDV_PRD_LOG_T.SQL";
    String XDV_SUB_GRP_NETWORK_MT = "XDV_SUB_GRP_NETWORK_MT.SQL";
    String XSL_ALT_ALERT_SLA_TMP_T = "XSL_ALT_ALERT_SLA_TMP_T.SQL";
    String table1 = "table1.sql";
    String orders = "orders.sql";
    String products = "products.sql";
    String spp_invoices = "spp_invoices.sql";

    String XDV_TAM_1HR_MEDTTA_FT = "XDV_TAM_1HR_MEDTTA_FT.SQL";
    String XDV_TKT_CAUSE_DT = "XDV_TKT_CAUSE_DT.SQL";
    String XDV_STG_PRP_WAP = "XDV_STG_PRP_WAP.SQL";
    String XDV_SINGTEL_AMA_PS_ET = "XDV_SINGTEL_AMA_PS_ET.SQL";
    String XDV_TAD_1MO_DM0TTA_FT = "XDV_TAD_1MO_DM0TTA_FT.SQL";

    String create_bitmap_index = "create_bitmap_index.sql";

    String create_tables = "create_tables.sql";

    File base;
    
    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("table_definitions").toURI());
    }

    public void test_create_tables() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, create_tables));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("create_bitmap_index", t.getTableName());
        assertEquals(1, t.getColumnDefs().length);
    }

    /*
    public void test_XDV_TAD_1MO_DM0TTA_FT() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XDV_TAD_1MO_DM0TTA_FT));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("XDV_TAD_1MO_DM0TTA_FT", t.getTableName());
        assertEquals(1, t.getColumnDefs().length);
    }
*/
    public void test_create_bitmap_index() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, create_bitmap_index));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(2, elems.length );
        assertTrue(elems[0] instanceof Index);

        Index t = (Index) elems[0];
//        assertEquals("create_bitmap_index", t.getTableName());
//        assertEquals(1, t.getColumnDefs().length);
    }

    public void test_XDV_SINGTEL_AMA_PS_ET() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XDV_SINGTEL_AMA_PS_ET));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(2, elems.length );
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("XDV_SINGTEL_AMA_PS_ET", t.getTableName());
        assertEquals(62, t.getColumnDefs().length);
    }


    public void test_XDV_STG_PRP_WAP() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XDV_STG_PRP_WAP));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(3, elems.length );
        assertTrue(elems[0] instanceof TableDefinition);
        assertTrue(elems[1] instanceof Index);
        assertTrue(elems[2] instanceof AlterTable);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("XDV_STG_PRP_WAP", t.getTableName());
        assertEquals(2, t.getColumnDefs().length);


    }

    public void test_XDV_TAM_1HR_MEDTTA_FT() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XDV_TAM_1HR_MEDTTA_FT));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(16, elems.length );
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("XDV_TAM_1HR_MEDTTA_FT", t.getTableName());
        assertEquals(52, t.getColumnDefs().length);
    }

    public void test_XDV_TKT_CAUSE_DT() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XDV_TKT_CAUSE_DT));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("XDV_TKT_CAUSE_DT", t.getTableName());
        assertEquals(11, t.getColumnDefs().length);
    }


    public void test_table1() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, table1));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("table1", t.getTableName());
        assertEquals(1, t.getColumnDefs().length);
    }

    public void test_orders() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, orders));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("ORDERS", t.getTableName());
        assertEquals(4, t.getColumnDefs().length);

        assertEquals("order_id", t.getColumnDefs()[0].getColumnName());
        assertEquals("INTEGER", t.getColumnDefs()[0].getType().toString());
        assertFalse(t.getColumnDefs()[0].isNotNull());
        assertTrue(t.getColumnDefs()[0].isPrimaryKey());
        assertNull(t.getColumnDefs()[0].getForeignKeySpec());

        assertEquals("order_date", t.getColumnDefs()[1].getColumnName());
        assertEquals("DATE", t.getColumnDefs()[1].getType().toString());
        assertTrue(t.getColumnDefs()[1].isNotNull());
        assertFalse(t.getColumnDefs()[1].isPrimaryKey());
        assertNull(t.getColumnDefs()[1].getForeignKeySpec());

        assertEquals("customer_sid", t.getColumnDefs()[2].getColumnName());
        assertEquals("INTEGER", t.getColumnDefs()[2].getType().toString());
        assertFalse(t.getColumnDefs()[2].isNotNull());
        assertFalse(t.getColumnDefs()[2].isPrimaryKey());
        assertNotNull(t.getColumnDefs()[2].getForeignKeySpec());
        assertEquals("CUSTOMER", t.getColumnDefs()[2].getForeignKeySpec().getReferencedTable());
        assertEquals("SID", t.getColumnDefs()[2].getForeignKeySpec().getReferencedColumn());

        assertEquals("amount", t.getColumnDefs()[3].getColumnName());
        assertEquals("NUMBER", t.getColumnDefs()[3].getType().toString());
        assertFalse(t.getColumnDefs()[3].isNotNull());
        assertFalse(t.getColumnDefs()[3].isPrimaryKey());
        assertNull(t.getColumnDefs()[3].getForeignKeySpec());

    }

    public void test_products() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, products));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("products", t.getTableName());
        assertEquals(3, t.getColumnDefs().length);

        assertEquals("product_id", t.getColumnDefs()[0].getColumnName());
        assertEquals("NUMERIC(10)", t.getColumnDefs()[0].getType().toString());
        assertTrue(t.getColumnDefs()[0].isNotNull());
        assertFalse(t.getColumnDefs()[0].isPrimaryKey());
        assertNull(t.getColumnDefs()[0].getForeignKeySpec());

        assertEquals("supplier_id", t.getColumnDefs()[1].getColumnName());
        assertEquals("NUMERIC(10)", t.getColumnDefs()[1].getType().toString());
        assertTrue(t.getColumnDefs()[1].isNotNull());
        assertFalse(t.getColumnDefs()[1].isPrimaryKey());
        assertNotNull(t.getColumnDefs()[1].getForeignKeySpec());

        assertEquals("supplier_name", t.getColumnDefs()[2].getColumnName());
        assertEquals("VARCHAR2(50)", t.getColumnDefs()[2].getType().toString());
        assertTrue(t.getColumnDefs()[2].isNotNull());
        assertFalse(t.getColumnDefs()[2].isPrimaryKey());
        assertNotNull(t.getColumnDefs()[2].getForeignKeySpec());
    }

    public void test_spp_invoices() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, spp_invoices));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(3, elems.length );
        assertTrue(elems[0] instanceof TableDefinition);

/*
        CREATE TABLE Supplier (
             SupplierNumber  INTEGER NOT NULL,
             Name            VARCHAR(20) NOT NULL,
             Address         VARCHAR(50) NOT NULL,
             Type            VARCHAR(10),
             CONSTRAINT supplier_pk PRIMARY KEY(SupplierNumber)
        )
*/
        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("Supplier", t.getTableName());
        assertEquals(4, t.getColumnDefs().length);

        assertEquals("SupplierNumber", t.getColumnDefs()[0].getColumnName());
        assertEquals("INTEGER", t.getColumnDefs()[0].getType().toString());
        assertTrue(t.getColumnDefs()[0].isNotNull());
        assertTrue(t.getColumnDefs()[0].isPrimaryKey());
        assertNull(t.getColumnDefs()[0].getForeignKeySpec());

        assertEquals("Name", t.getColumnDefs()[1].getColumnName());
        assertEquals("VARCHAR(20)", t.getColumnDefs()[1].getType().toString());
        assertTrue(t.getColumnDefs()[1].isNotNull());
        assertFalse(t.getColumnDefs()[1].isPrimaryKey());
        assertNull(t.getColumnDefs()[1].getForeignKeySpec());

        assertEquals("Address", t.getColumnDefs()[2].getColumnName());
        assertEquals("VARCHAR(50)", t.getColumnDefs()[2].getType().toString());
        assertTrue(t.getColumnDefs()[2].isNotNull());
        assertFalse(t.getColumnDefs()[2].isPrimaryKey());
        assertNull(t.getColumnDefs()[2].getForeignKeySpec());

        assertEquals("Type", t.getColumnDefs()[3].getColumnName());
        assertEquals("VARCHAR(10)", t.getColumnDefs()[3].getType().toString());
        assertFalse(t.getColumnDefs()[3].isNotNull());
        assertFalse(t.getColumnDefs()[3].isPrimaryKey());
        assertNull(t.getColumnDefs()[3].getForeignKeySpec());

/*
        CREATE TABLE Invoices (
             InvoiceNumber   INTEGER NOT NULL,
             SupplierNumber  INTEGER NOT NULL,
             Text            VARCHAR(4096),
             CONSTRAINT invoice_pk PRIMARY KEY(InvoiceNumber),
             CONSTRAINT supplier_fk FOREIGN KEY(SupplierNumber)
                REFERENCES Supplier(SupplierNumber)
                ON UPDATE CASCADE ON DELETE RESTRICT
        )
*/
        t = (TableDefinition) elems[1];
        assertEquals("Invoices", t.getTableName());
        assertEquals(3, t.getColumnDefs().length);

        assertEquals("InvoiceNumber", t.getColumnDefs()[0].getColumnName());
        assertEquals("INTEGER", t.getColumnDefs()[0].getType().toString());
        assertTrue(t.getColumnDefs()[0].isNotNull());
        assertTrue(t.getColumnDefs()[0].isPrimaryKey());
        assertNull(t.getColumnDefs()[0].getForeignKeySpec());

        assertEquals("SupplierNumber", t.getColumnDefs()[1].getColumnName());
        assertEquals("INTEGER", t.getColumnDefs()[1].getType().toString());
        assertTrue(t.getColumnDefs()[1].isNotNull());
        assertFalse(t.getColumnDefs()[1].isPrimaryKey());
        assertNotNull(t.getColumnDefs()[1].getForeignKeySpec());

        assertEquals("Supplier",t.getColumnDefs()[1].getForeignKeySpec().getReferencedTable());
// todo --        assertEquals("SupplierNumber",t.getColumnDefs()[1].getForeignKeySpec().getReferencedColumn());

        assertEquals("Text", t.getColumnDefs()[2].getColumnName());
        assertEquals("VARCHAR(4096)", t.getColumnDefs()[2].getType().toString());
        assertFalse(t.getColumnDefs()[2].isNotNull());
        assertFalse(t.getColumnDefs()[2].isPrimaryKey());
        assertNull(t.getColumnDefs()[2].getForeignKeySpec());

    }


    public void test_XDV_TRN_STATUS_DT() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XDV_TRN_STATUS_DT));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("XDV_TRN_STATUS_DT", t.getTableName());
        assertEquals(14, t.getColumnDefs().length);
    }

    public void test_XSL_RPT_PARAM_T() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XSL_RPT_PARAM_T));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(24, elems.length );
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("XSL_RPT_PARAM_T", t.getTableName());
        assertEquals(14, t.getColumnDefs().length);
    }

    public void test_XDV_ASM_TRN_MEDSES_FT() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XDV_ASM_TRN_MEDSES_FT));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(16, elems.length );
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("XDV_ASM_TRN_MEDSES_FT", t.getTableName());
        assertEquals(58, t.getColumnDefs().length);
    }

    public void test_XDV_NET_GRP_PATH_EXT_MT() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XDV_NET_GRP_PATH_EXT_MT));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("XDV_NET_GRP_PATH_EXT_MT", t.getTableName());
        assertEquals(14, t.getColumnDefs().length);
    }

    public void test_XDV_PRD_LOG_T() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XDV_PRD_LOG_T));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(4, elems.length );
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("XDV_PRD_LOG_T", t.getTableName());
        assertEquals(9, t.getColumnDefs().length);
    }


    public void test_XDV_SUB_GRP_NETWORK_MT() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XDV_SUB_GRP_NETWORK_MT));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(1, elems.length );
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("XDV_SUB_GRP_NETWORK_MT", t.getTableName());
        assertEquals(5, t.getColumnDefs().length);
    }

    public void test_XSL_ALT_ALERT_SLA_TMP_T() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, XSL_ALT_ALERT_SLA_TMP_T));
        PlSqlElement[] elems = new PlSqlASTParser().parseStream(r);

        assertNotNull(elems);
        assertEquals(2, elems.length );
        assertTrue(elems[0] instanceof TableDefinition);

        TableDefinition t = (TableDefinition) elems[0];
        assertEquals("XSL_ALT_ALERT_SLA_TMP_T", t.getTableName());
        assertEquals(2, t.getColumnDefs().length);

        assertEquals("MONITOR_ID", t.getColumnDefs()[0].getColumnName());
        assertEquals("NUMBER", t.getColumnDefs()[0].getType().toString());
        assertTrue(t.getColumnDefs()[0].isNotNull());
        assertFalse(t.getColumnDefs()[0].isPrimaryKey());
        assertNull(t.getColumnDefs()[0].getForeignKeySpec());

        assertEquals("VALUE", t.getColumnDefs()[1].getColumnName());
        assertEquals("NUMBER", t.getColumnDefs()[1].getType().toString());
        assertFalse(t.getColumnDefs()[1].isNotNull());
        assertFalse(t.getColumnDefs()[1].isPrimaryKey());
        assertNull(t.getColumnDefs()[1].getForeignKeySpec());

    }

}
