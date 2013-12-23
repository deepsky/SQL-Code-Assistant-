/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * SQL CODE ASSISTANT PLUG-IN FOR INTELLIJ IDEA IS PROVIDED BY SERHIY KULYK
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL SERHIY KULYK BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package test.deepsky.lang.plsql.parser;

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.ColumnDefinition;
import com.deepsky.lang.plsql.psi.GenericConstraint;
import com.deepsky.lang.plsql.psi.ddl.CreateTempTable;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import junit.framework.TestCase;
import test.deepsky.lang.plsql.PlSqlParser_AbstractTest;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class PlSqlParser_TableTest extends PlSqlParser_AbstractTest {

    String undo$ = "undo$.sql";
    String XDV_TAM_1HR_MEDTTA_FT = "XDV_TAM_1HR_MEDTTA_FT.SQL";
    String XDV_ASM_TRN_MEDSES_FT = "XDV_ASM_TRN_MEDSES_FT.SQL";
    String XDV_SINGTEL_AMA_PS_ET = "XDV_SINGTEL_AMA_PS_ET.SQL";
    String create_table_A1 = "create table A1 (id number)";
    String XDV_AAI_15M_INTAPA_FT = "XDV_AAI_15M_INTAPA_FT.SQL";
    String XDV_TTS_TRN_STGTKT_T = "XDV_TTS_TRN_STGTKT_T.SQL";
    String XDV_AMS_TRN_STGMES_T = "XDV_AMS_TRN_STGMES_T.SQL";
    String airport = "airport.sql";
    String create_tables = "create_tables.sql";

    String XDV_STG_PRP_WAP = "XDV_STG_PRP_WAP.SQL";
    String XSL_ALT_ALERT_SLA_TMP_T = "XSL_ALT_ALERT_SLA_TMP_T.SQL";


//    File base;

    public PlSqlParser_TableTest() {
        super("table_definitions");
    }

//    public void setUp() throws URISyntaxException {
//        base = new File(this.getClass().getClassLoader().getResource("table_definitions").toURI());
//    }

    public void test_XDV_STG_PRP_WAP() throws IOException {
        ASTNode root = parseScript(XDV_STG_PRP_WAP);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.CREATE_TEMP_TABLE));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof TableDefinition);

        TableDefinition tab = (TableDefinition) nodes[0].getPsi();
        assertEquals("XDV_STG_PRP_WAP", tab.getTableName().toUpperCase());

        ColumnDefinition[] columns =  tab.getColumnDefs();
        assertEquals(2, columns.length);

        assertEquals(TableDefinition.TEMPORARY, tab.getTableType());
        assertTrue(tab.getPartitionType() == TableDefinition.PARTITION_NONE);
    }

    public void test_XSL_ALT_ALERT_SLA_TMP_T() throws IOException {
        ASTNode root = parseScript(XSL_ALT_ALERT_SLA_TMP_T);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.CREATE_TEMP_TABLE));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof TableDefinition);

        TableDefinition tab = (TableDefinition) nodes[0].getPsi();
        assertEquals("XSL_ALT_ALERT_SLA_TMP_T", tab.getTableName().toUpperCase());

        ColumnDefinition[] columns =  tab.getColumnDefs();
        assertEquals(2, columns.length);

        assertEquals(TableDefinition.TEMPORARY, tab.getTableType());
        assertTrue(tab.getPartitionType() == TableDefinition.PARTITION_NONE);
    }

    public void test_XDV_TTS_TRN_STGTKT_T() throws IOException {
        ASTNode root = parseScript(XDV_TTS_TRN_STGTKT_T);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.TABLE_DEF));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof TableDefinition);

        TableDefinition tab = (TableDefinition) nodes[0].getPsi();
        assertEquals("XDV_TTS_TRN_STGTKT_T", tab.getTableName().toUpperCase());

        ColumnDefinition[] columns =  tab.getColumnDefs();
        assertEquals(70, columns.length);

        assertEquals(TableDefinition.REGULAR, tab.getTableType());
        assertTrue(tab.getPartitionType() == TableDefinition.PARTITION_BY_HASH);
    }

    public void test_XDV_AMS_TRN_STGMES_T() throws IOException {
        ASTNode root = parseScript(XDV_AMS_TRN_STGMES_T);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.TABLE_DEF));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof TableDefinition);

        TableDefinition tab = (TableDefinition) nodes[0].getPsi();
        assertEquals("XDV_AMS_TRN_STGMES_T", tab.getTableName().toUpperCase());

        ColumnDefinition[] columns =  tab.getColumnDefs();
        assertEquals(61, columns.length);

        assertEquals(TableDefinition.REGULAR, tab.getTableType());
        assertTrue(tab.getPartitionType() == TableDefinition.PARTITION_BY_HASH);
    }

    public void test_XDV_AAI_15M_INTAPA_FT() throws IOException {
        ASTNode root = parseScript(XDV_AAI_15M_INTAPA_FT);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.TABLE_DEF));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof TableDefinition);

        TableDefinition tab = (TableDefinition) nodes[0].getPsi();
        assertEquals("XDV_AAI_15M_INTAPA_FT", tab.getTableName().toUpperCase());

        GenericConstraint[] constr =  tab.getConstraints();
        assertEquals(22, constr.length);
        
        assertEquals(TableDefinition.REGULAR, tab.getTableType());
        assertTrue(tab.getPartitionType() == TableDefinition.PARTITION_BY_RANGE);
    }


    public void test_create_table(){
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode root = generator.parse(create_table_A1);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.TABLE_DEF));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof TableDefinition);

        TableDefinition tab = (TableDefinition) nodes[0].getPsi();
        assertEquals("A1", tab.getTableName().toUpperCase());
        assertEquals(TableDefinition.REGULAR, tab.getTableType());

        assertEquals(1,tab.getColumnDefs().length);
        assertEquals("id",tab.getColumnDefs()[0].getColumnName());
        assertEquals(Type.NUMBER_TYPE,tab.getColumnDefs()[0].getType());
        assertEquals(create_table_A1, tab.getText());

    }
    

    public void test_undo$() throws IOException {
//        String content = StringUtils.file2string(new File(base, undo$));
//        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
//        ASTNode root = generator.parse(content);
        ASTNode root = parseScript(undo$);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.TABLE_DEF));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof TableDefinition);

        TableDefinition tab = (TableDefinition) nodes[0].getPsi();
        assertEquals("UNDO$", tab.getTableName().toUpperCase());
        assertEquals(22, tab.getColumnDefs().length);
        assertEquals(TableDefinition.REGULAR, tab.getTableType());
    }


    public void test_XDV_SINGTEL_AMA_PS_ET() throws IOException {
        ASTNode root = parseScript(XDV_SINGTEL_AMA_PS_ET);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.TABLE_DEF));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof TableDefinition);

        TableDefinition tab = (TableDefinition) nodes[0].getPsi();
        assertEquals("XDV_SINGTEL_AMA_PS_ET", tab.getTableName().toUpperCase());
        assertEquals(TableDefinition.EXTERNAL_ORGANIZED, tab.getTableType());

    }


    public void test_XDV_ASM_TRN_MEDSES_FT() throws IOException {
        ASTNode root = parseScript(XDV_ASM_TRN_MEDSES_FT);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.TABLE_DEF));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof TableDefinition);

        TableDefinition tab = (TableDefinition) nodes[0].getPsi();
        assertEquals("XDV_ASM_TRN_MEDSES_FT", tab.getTableName().toUpperCase());
        assertEquals(TableDefinition.REGULAR, tab.getTableType());
        assertTrue(tab.getPartitionType() == TableDefinition.PARTITION_BY_RANGE);
    }

    public void test_XDV_TAM_1HR_MEDTTA_FT() throws IOException {
        ASTNode root = parseScript(XDV_TAM_1HR_MEDTTA_FT);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.TABLE_DEF));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof TableDefinition);

        TableDefinition tab = (TableDefinition) nodes[0].getPsi();
        assertEquals("XDV_TAM_1HR_MEDTTA_FT", tab.getTableName().toUpperCase());
        assertEquals(TableDefinition.HEAP_ORGANIZED, tab.getTableType());
        assertTrue(tab.getPartitionType() == TableDefinition.PARTITION_BY_RANGE);

    }


    public void test_airport() throws IOException {
        ASTNode root = parseScript(airport);

        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.TABLE_DEF));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof TableDefinition);

        TableDefinition tab = (TableDefinition) nodes[0].getPsi();
        assertEquals("AIRPORT", tab.getTableName().toUpperCase());
        assertEquals(TableDefinition.REGULAR, tab.getTableType());

        GenericConstraint[] constr =  tab.getConstraints();
        assertEquals(1, constr.length);

        assertEquals(13,tab.getColumnDefs().length);
        assertEquals("ID",tab.getColumnDefs()[0].getColumnName());
        assertEquals(Type.NUMBER_TYPE,tab.getColumnDefs()[0].getType());

        assertEquals("LOCATIONCODE",tab.getColumnDefs()[1].getColumnName());
        assertEquals(Type.VARCHAR2,tab.getColumnDefs()[1].getType().typeId());

        assertEquals("CITYCODE",tab.getColumnDefs()[2].getColumnName());
        assertEquals(Type.VARCHAR2,tab.getColumnDefs()[2].getType().typeId());
    }



    public void test_create_tables() throws IOException {
        ASTNode root = parseScript(create_tables);

        // Temporary Tables
        ASTNode[] nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.CREATE_TEMP_TABLE));
        assertEquals(1, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof CreateTempTable);

        // Regular Tables
        nodes = root.getChildren(TokenSet.create(PlSqlElementTypes.TABLE_DEF));
        assertEquals(11, nodes.length);
        assertTrue(nodes[0].getPsi() instanceof TableDefinition);

        // EMPLOYEES_DEMO table
        TableDefinition tab = (TableDefinition) nodes[0].getPsi();
        assertEquals("EMPLOYEES_DEMO", tab.getTableName().toUpperCase());
        assertEquals(TableDefinition.REGULAR, tab.getTableType());

        GenericConstraint[] constr =  tab.getConstraints();
        assertEquals(2, constr.length);

        assertEquals(12,tab.getColumnDefs().length);

        assertEquals("employee_id",tab.getColumnDefs()[0].getColumnName().toLowerCase());
        assertEquals(Type.NUMBER,tab.getColumnDefs()[0].getType().typeId());

        assertEquals("first_name",tab.getColumnDefs()[1].getColumnName().toLowerCase());
        assertEquals(Type.VARCHAR2,tab.getColumnDefs()[1].getType().typeId());

        assertEquals("last_name",tab.getColumnDefs()[2].getColumnName().toLowerCase());
        assertEquals(Type.VARCHAR2,tab.getColumnDefs()[2].getType().typeId());
        assertTrue(tab.getColumnDefs()[2].isNotNull());

        assertEquals("email",tab.getColumnDefs()[3].getColumnName().toLowerCase());
        assertEquals(Type.VARCHAR2,tab.getColumnDefs()[3].getType().typeId());
        assertTrue(tab.getColumnDefs()[3].isNotNull());

        assertEquals("phone_number",tab.getColumnDefs()[4].getColumnName().toLowerCase());
        assertEquals(Type.VARCHAR2,tab.getColumnDefs()[4].getType().typeId());
        assertFalse(tab.getColumnDefs()[4].isNotNull());

        assertEquals("hire_date",tab.getColumnDefs()[5].getColumnName().toLowerCase());
        assertEquals(Type.DATE,tab.getColumnDefs()[5].getType().typeId());
        assertTrue(tab.getColumnDefs()[5].isNotNull());

        assertEquals("job_id",tab.getColumnDefs()[6].getColumnName().toLowerCase());
        assertEquals(Type.VARCHAR2,tab.getColumnDefs()[6].getType().typeId());
        assertTrue(tab.getColumnDefs()[6].isNotNull());

        assertEquals("salary",tab.getColumnDefs()[7].getColumnName().toLowerCase());
        assertEquals(Type.NUMBER,tab.getColumnDefs()[7].getType().typeId());
        assertTrue(tab.getColumnDefs()[7].isNotNull());

        assertEquals("commission_pct",tab.getColumnDefs()[8].getColumnName().toLowerCase());
        assertEquals(Type.NUMBER,tab.getColumnDefs()[8].getType().typeId());
        assertFalse(tab.getColumnDefs()[8].isNotNull());

        assertEquals("manager_id",tab.getColumnDefs()[9].getColumnName().toLowerCase());
        assertEquals(Type.NUMBER,tab.getColumnDefs()[9].getType().typeId());
        assertFalse(tab.getColumnDefs()[9].isNotNull());

        assertEquals("department_id",tab.getColumnDefs()[10].getColumnName().toLowerCase());
        assertEquals(Type.NUMBER,tab.getColumnDefs()[10].getType().typeId());
        assertFalse(tab.getColumnDefs()[10].isNotNull());

        assertEquals("dn",tab.getColumnDefs()[11].getColumnName().toLowerCase());
        assertEquals(Type.VARCHAR2,tab.getColumnDefs()[11].getType().typeId());
        assertFalse(tab.getColumnDefs()[11].isNotNull());

        // PRINT_MEDIA nested table
        tab = (TableDefinition) nodes[6].getPsi();
        assertEquals("PRINT_MEDIA", tab.getTableName().toUpperCase());
        assertEquals(TableDefinition.REGULAR, tab.getTableType());

    }
}
