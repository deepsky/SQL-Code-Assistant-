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

package test.deepsky.lang.plsql.struct.parser;

import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.TableCollectionDecl;
import com.deepsky.lang.plsql.psi.ddl.CreateSequence;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.URISyntaxException;

public class PlSqlASTParser_SequenceTest extends TestCase {

    String sequences0 = "sequences0.sql";

    File base;
    PlSqlASTParser parser = new PlSqlASTParser();

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("sequences").toURI());
    }

    public void test_sequences0() throws FileNotFoundException {
        Reader r = new FileReader(new File(base, sequences0));
        PlSqlElement[] elems = parser.parseStream(r);

        assertNotNull(elems);
        assertEquals(9, elems.length );
        assertTrue(elems[0] instanceof CreateSequence);
        assertEquals("XSL_CON_ID_SEQ", ((CreateSequence)elems[0]).getSequenceName());
        assertEquals("XSL_UMA_ID_SEQ", ((CreateSequence)elems[1]).getSequenceName());
        assertEquals("xsl_bcast_msg_seq", ((CreateSequence)elems[2]).getSequenceName());
        assertEquals("xsl_audit_seq_seq", ((CreateSequence)elems[3]).getSequenceName());

        assertEquals("xsl_purge_seq", ((CreateSequence)elems[4]).getSequenceName());
        assertEquals("xsl_pdc_reg_seq", ((CreateSequence)elems[5]).getSequenceName());
        assertEquals("xsl_nsdbaggr_seq", ((CreateSequence)elems[6]).getSequenceName());
        assertEquals("XTL_PDC_ID_SEQ", ((CreateSequence)elems[7]).getSequenceName());
        assertEquals("xsl_sonet_seq", ((CreateSequence)elems[8]).getSequenceName());
    }

}
