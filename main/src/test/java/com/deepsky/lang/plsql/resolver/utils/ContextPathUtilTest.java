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

package com.deepsky.lang.plsql.resolver.utils;

import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.struct.Type;
import junit.framework.TestCase;

import java.util.List;

public class ContextPathUtilTest extends TestCase {


    public void test_PK_FK_table_encode_decode(){
        String value = "p:xdv_ais_pk:id|f:xdv_ais_apv_fk:prv_id:xdv_adp_probe_vendor_t:id|partition=-1|tabletype=0";

        String[] columns = ContextPathUtil.extractPKColumnsFromTableValue(value);
        assertNotNull(columns);
        assertEquals(1, columns.length);
        assertEquals("id", columns[0]);

        String pkConstraintName = ContextPathUtil.extractPKConstrNameFromTableValue(value);
        assertEquals("xdv_ais_pk", pkConstraintName);

        String fkConstraintName = ContextPathUtil.extractFKConstrNameFromTableValue(value);
        assertEquals("xdv_ais_apv_fk", fkConstraintName);

        String fkRefTable = ContextPathUtil.extractFKRefTableFromTableValue(value);
        assertEquals("xdv_adp_probe_vendor_t", fkRefTable);

        String[] fkOwnColumns = ContextPathUtil.extractFKOwnColumnsFromTableValue(value);
        assertEquals(1, fkOwnColumns.length);
        assertEquals("prv_id", fkOwnColumns[0]);

        String[] fkRefColumns = ContextPathUtil.extractFKRefColumnsFromTableValue(value);
        assertEquals(1, fkRefColumns.length);
        assertEquals("id", fkRefColumns[0]);

        /////////////////////////////////////////
        String value2 = "partition=-1|tabletype=0|p:xdv_ais_pk:id,type|f:xdv_ais_apv_fp:prv_id,tab_id:xdv_adp_probe_vendor_t:id,t_id|";
        columns = ContextPathUtil.extractPKColumnsFromTableValue(value2);
        assertNotNull(columns);
        assertEquals(2, columns.length);
        assertEquals("id", columns[0]);
        assertEquals("type", columns[1]);

        pkConstraintName = ContextPathUtil.extractPKConstrNameFromTableValue(value2);
        assertEquals("xdv_ais_pk", pkConstraintName);

        fkConstraintName = ContextPathUtil.extractFKConstrNameFromTableValue(value2);
        assertEquals("xdv_ais_apv_fp", fkConstraintName);

        fkRefTable = ContextPathUtil.extractFKRefTableFromTableValue(value2);
        assertEquals("xdv_adp_probe_vendor_t", fkRefTable);

        fkOwnColumns = ContextPathUtil.extractFKOwnColumnsFromTableValue(value2);
        assertEquals(2, fkOwnColumns.length);
        assertEquals("prv_id", fkOwnColumns[0]);
        assertEquals("tab_id", fkOwnColumns[1]);

        fkRefColumns = ContextPathUtil.extractFKRefColumnsFromTableValue(value2);
        assertEquals(2, fkRefColumns.length);
        assertEquals("id", fkRefColumns[0]);
        assertEquals("t_id", fkRefColumns[1]);

        //////////////////////////
        String value3 = "partition=-1|tabletype=0";
        columns = ContextPathUtil.extractPKColumnsFromTableValue(value3);
        assertNull(columns);

        fkOwnColumns = ContextPathUtil.extractFKOwnColumnsFromTableValue(value3);
        assertNull(fkOwnColumns);
    }


    public void test_PK_FK_table_encode_decode2(){
        String value = "p:xdv_ais_pk:id|f:xdv_ais_apv:prv_id:xdv_adp_probe_dop_t:id|f:xdv_ais_apv_fk:prv_id:xdv_adp_probe_vendor_t:id|partition=-1|tabletype=0";

        List<ContextPathUtil.FKConstraintSpec> spec = ContextPathUtil.extractFKConstraintsFromTableValue(value);
        assertNotNull(spec);
        assertEquals(2, spec.size());

        assertEquals("xdv_ais_apv", spec.get(0).getName());
        assertEquals("xdv_ais_apv_fk", spec.get(1).getName());

        assertEquals("xdv_adp_probe_dop_t", spec.get(0).getRefTable());
        assertEquals("xdv_adp_probe_vendor_t", spec.get(1).getRefTable());

    }

    public void test_PK_FK_column_encode_decode(){
        String refColumn = "name";
        String refTable = "tab1";
        String value = ContextPathUtil.encodeColumnValue(Type.NUMBER_TYPE, false, true, refColumn, refTable);

        Type type = ContextPathUtil.decodeColumnTypeFromValue(value);
        boolean isNull = ContextPathUtil.decodeColumnNotNull(value);
        boolean isPK = ContextPathUtil.extractIsPKFromColumnValue(value);
        String refColumn1 = ContextPathUtil.extractRefColumnFromColumnValue(value);
        String refTable1 = ContextPathUtil.extractRefTableFromColumnValue(value);

        assertTrue(type.equals(Type.NUMBER_TYPE));
        assertFalse(isNull);
        assertTrue(isPK);
        assertEquals("name", refColumn1);
        assertEquals("tab1", refTable1);
    }


    public void test_PK_FK_column_encode_decode2(){
        String refColumn = "name";
        String refTable = "tab1";
        String value = ContextPathUtil.encodeColumnValue(Type.NUMBER_TYPE, false);

        Type type = ContextPathUtil.decodeColumnTypeFromValue(value);
        boolean isNull = ContextPathUtil.decodeColumnNotNull(value);
        boolean isPK = ContextPathUtil.extractIsPKFromColumnValue(value);
        String refColumn1 = ContextPathUtil.extractRefColumnFromColumnValue(value);
        String refTable1 = ContextPathUtil.extractRefTableFromColumnValue(value);

        assertTrue(type.equals(Type.NUMBER_TYPE));
        assertFalse(isNull);
        assertFalse(isPK);
        assertNull(refColumn1);
        assertNull(refTable1);
    }


    public void test_PK_FK_column_encode_decode3(){
        String constraintName = "EMP_CTR_1";
        String value = ContextPathUtil.encodeColumnValue(Type.NUMBER_TYPE, constraintName, false, true, null, null);

        Type type = ContextPathUtil.decodeColumnTypeFromValue(value);
        boolean isNull = ContextPathUtil.decodeColumnNotNull(value);
        boolean isPK = ContextPathUtil.extractIsPKFromColumnValue(value);
        String refColumn1 = ContextPathUtil.extractRefColumnFromColumnValue(value);
        String constraintName2 = ContextPathUtil.extractConstraintNameFromColumnValue(value);
        String refTable1 = ContextPathUtil.extractRefTableFromColumnValue(value);

        assertTrue(type.equals(Type.NUMBER_TYPE));
        assertFalse(isNull);
        assertTrue(isPK);
        assertNull(refColumn1);
        assertNull(refTable1);
        assertEquals(constraintName.toLowerCase(), constraintName2);
    }

    public void test_PK_FK_column_encode_decode4(){
        String refColumn = "name";
        String refTable = "tab1";
        String constraintName = "EMP_CTR_1";
        String value = ContextPathUtil.encodeColumnValue(Type.NUMBER_TYPE, constraintName, false, false, refColumn, refTable);

        Type type = ContextPathUtil.decodeColumnTypeFromValue(value);
        boolean isNull = ContextPathUtil.decodeColumnNotNull(value);
        boolean isPK = ContextPathUtil.extractIsPKFromColumnValue(value);
        String refColumn1 = ContextPathUtil.extractRefColumnFromColumnValue(value);
        String constraintName2 = ContextPathUtil.extractConstraintNameFromColumnValue(value);
        String refTable1 = ContextPathUtil.extractRefTableFromColumnValue(value);

        assertTrue(type.equals(Type.NUMBER_TYPE));
        assertFalse(isNull);
        assertFalse(isPK);
        assertEquals("name", refColumn1);
        assertEquals("tab1", refTable1);
        assertEquals(constraintName.toLowerCase(), constraintName2);
    }

    public void test_CtxPathIterator(){

        String path1 = "/FL!..$sequence!xdv_trn_qos_seq.dump/Sq!00$xdv_trn_qos_seq";
        String path2 = "/FL!..$view!xdv_sta_flow_queues_stats_v.dump/Vd!00$xdv_sta_flow_queues_stats_v/Se!00$/Sb!00$/Su!00$/Se!0A$";
        String path3 = "/FL!..$package?body!xdv_logger_pkg.dump/PB!00$xdv_logger_pkg/Fb!06$parsecallstack/Bl!00$/Va!02$l_end";
        String path4 = "/FL!..$view!xdv_sta_ftrn_t_1hr_isi_v.dump/Vd!00$xdv_sta_ftrn_t_1hr_isi_v/Se!00$/Sb!00$/Se!00$/Sb!00$";

        // case 1
        ContextPathUtil.CtxPathIterator ite1 = new ContextPathUtil.CtxPathIterator(path1);
        assertTrue(ite1.next());
        assertEquals("sequence!xdv_trn_qos_seq.dump", ite1.getName());
        assertEquals("/FL!..$sequence!xdv_trn_qos_seq.dump", ite1.getPath());
        assertEquals(ContextPath.FILE_CTX, ite1.getType());

        assertTrue(ite1.next());
        assertEquals("xdv_trn_qos_seq", ite1.getName());
        assertEquals("/FL!..$sequence!xdv_trn_qos_seq.dump/Sq!00$xdv_trn_qos_seq", ite1.getPath());
        assertEquals(ContextPath.SEQUENCE, ite1.getType());
        assertFalse(ite1.next());

        // case 2
        ContextPathUtil.CtxPathIterator ite2 = new ContextPathUtil.CtxPathIterator(path2);
        assertTrue(ite2.next());
        assertEquals("view!xdv_sta_flow_queues_stats_v.dump", ite2.getName());
        assertEquals("/FL!..$view!xdv_sta_flow_queues_stats_v.dump", ite2.getPath());
        assertEquals(ContextPath.FILE_CTX, ite2.getType());

        assertTrue(ite2.next());
        assertEquals("xdv_sta_flow_queues_stats_v", ite2.getName());
        assertEquals("/FL!..$view!xdv_sta_flow_queues_stats_v.dump/Vd!00$xdv_sta_flow_queues_stats_v", ite2.getPath());
        assertEquals(ContextPath.VIEW_DEF, ite2.getType());

        assertTrue(ite2.next());
        assertEquals("", ite2.getName());
        assertEquals("/FL!..$view!xdv_sta_flow_queues_stats_v.dump/Vd!00$xdv_sta_flow_queues_stats_v/Se!00$", ite2.getPath());
        assertEquals(ContextPath.SELECT_EXPR, ite2.getType());

        assertTrue(ite2.next());
        assertEquals("", ite2.getName());
        assertEquals("/FL!..$view!xdv_sta_flow_queues_stats_v.dump/Vd!00$xdv_sta_flow_queues_stats_v/Se!00$/Sb!00$", ite2.getPath());
        assertEquals(ContextPath.SUBQUERY, ite2.getType());

        assertTrue(ite2.next());
        assertEquals("", ite2.getName());
        assertEquals("/FL!..$view!xdv_sta_flow_queues_stats_v.dump/Vd!00$xdv_sta_flow_queues_stats_v/Se!00$/Sb!00$/Su!00$", ite2.getPath());
        assertEquals(ContextPath.SELECT_EXPR_UNION, ite2.getType());

        assertTrue(ite2.next());
        assertEquals("", ite2.getName());
        assertEquals("/FL!..$view!xdv_sta_flow_queues_stats_v.dump/Vd!00$xdv_sta_flow_queues_stats_v/Se!00$/Sb!00$/Su!00$/Se!0A$", ite2.getPath());
        assertEquals(ContextPath.SELECT_EXPR, ite2.getType());

        assertFalse(ite2.next());

        // case 3  -- todo - implement me
        ContextPathUtil.CtxPathIterator ite3 = new ContextPathUtil.CtxPathIterator(path3);
        assertTrue(ite3.next());
        assertTrue(ite3.next());
        assertTrue(ite3.next());
        assertTrue(ite3.next());
        assertTrue(ite3.next());
        assertFalse(ite3.next());

        // case 4  -- todo - implement me 
        ContextPathUtil.CtxPathIterator ite4 = new ContextPathUtil.CtxPathIterator(path4);
        assertTrue(ite4.next());
        assertTrue(ite4.next());
        assertTrue(ite4.next());
        assertTrue(ite4.next());
        assertTrue(ite4.next());
        assertTrue(ite4.next());
        assertFalse(ite4.next());
    }

    public void test_CtxPathBackIterator(){

        String path1 = "/FL!..$sequence!xdv_trn_qos_seq.dump/Sq!00$xdv_trn_qos_seq";
        String path2 = "/FL!..$view!xdv_sta_flow_queues_stats_v.dump/Vd!00$xdv_sta_flow_queues_stats_v/Se!00$/Sb!00$/Su!00$/Se!0A$";
        String path3 = "/FL!..$package?body!xdv_logger_pkg.dump/PB!00$xdv_logger_pkg/Fb!06$parsecallstack/Bl!00$/Va!02$l_end";
        String path4 = "/FL!..$view!xdv_sta_ftrn_t_1hr_isi_v.dump/Vd!00$xdv_sta_ftrn_t_1hr_isi_v/Se!00$/Sb!00$/Se!00$/Sb!00$";

        // case 1
        ContextPathUtil.CtxPathBackIterator ite1 = new ContextPathUtil.CtxPathBackIterator(path1);
        assertTrue(ite1.next());
        assertEquals("xdv_trn_qos_seq", ite1.getName());
        assertEquals("/FL!..$sequence!xdv_trn_qos_seq.dump/Sq!00$xdv_trn_qos_seq", ite1.getPath());
        assertEquals(ContextPath.SEQUENCE, ite1.getType());
        assertTrue(ite1.next());

        assertEquals("sequence!xdv_trn_qos_seq.dump", ite1.getName());
        assertEquals("/FL!..$sequence!xdv_trn_qos_seq.dump", ite1.getPath());
        assertEquals(ContextPath.FILE_CTX, ite1.getType());
        assertFalse(ite1.next());


        // case 2
        ContextPathUtil.CtxPathBackIterator ite2 = new ContextPathUtil.CtxPathBackIterator(path2);
        assertTrue(ite2.next());
        assertEquals("", ite2.getName());
        assertEquals("/FL!..$view!xdv_sta_flow_queues_stats_v.dump/Vd!00$xdv_sta_flow_queues_stats_v/Se!00$/Sb!00$/Su!00$/Se!0A$", ite2.getPath());
        assertEquals(ContextPath.SELECT_EXPR, ite2.getType());

        assertTrue(ite2.next());
        assertEquals("", ite2.getName());
        assertEquals("/FL!..$view!xdv_sta_flow_queues_stats_v.dump/Vd!00$xdv_sta_flow_queues_stats_v/Se!00$/Sb!00$/Su!00$", ite2.getPath());
        assertEquals(ContextPath.SELECT_EXPR_UNION, ite2.getType());

        assertTrue(ite2.next());
        assertEquals("", ite2.getName());
        assertEquals("/FL!..$view!xdv_sta_flow_queues_stats_v.dump/Vd!00$xdv_sta_flow_queues_stats_v/Se!00$/Sb!00$", ite2.getPath());
        assertEquals(ContextPath.SUBQUERY, ite2.getType());

        assertTrue(ite2.next());
        assertEquals("", ite2.getName());
        assertEquals("/FL!..$view!xdv_sta_flow_queues_stats_v.dump/Vd!00$xdv_sta_flow_queues_stats_v/Se!00$", ite2.getPath());
        assertEquals(ContextPath.SELECT_EXPR, ite2.getType());

        assertTrue(ite2.next());
        assertEquals("xdv_sta_flow_queues_stats_v", ite2.getName());
        assertEquals("/FL!..$view!xdv_sta_flow_queues_stats_v.dump/Vd!00$xdv_sta_flow_queues_stats_v", ite2.getPath());
        assertEquals(ContextPath.VIEW_DEF, ite2.getType());

        assertTrue(ite2.next());
        assertEquals("view!xdv_sta_flow_queues_stats_v.dump", ite2.getName());
        assertEquals("/FL!..$view!xdv_sta_flow_queues_stats_v.dump", ite2.getPath());
        assertEquals(ContextPath.FILE_CTX, ite2.getType());

        assertFalse(ite2.next());

        // case 3  -- todo - implement me
        ContextPathUtil.CtxPathBackIterator ite3 = new ContextPathUtil.CtxPathBackIterator(path3);
        assertTrue(ite3.next());
        assertTrue(ite3.next());
        assertTrue(ite3.next());
        assertTrue(ite3.next());
        assertTrue(ite3.next());
        assertFalse(ite3.next());

        // case 4  -- todo - implement me
        ContextPathUtil.CtxPathBackIterator ite4 = new ContextPathUtil.CtxPathBackIterator(path4);
        assertTrue(ite4.next());
        assertTrue(ite4.next());
        assertTrue(ite4.next());
        assertTrue(ite4.next());
        assertTrue(ite4.next());
        assertTrue(ite4.next());
        assertFalse(ite4.next());
    }

}
