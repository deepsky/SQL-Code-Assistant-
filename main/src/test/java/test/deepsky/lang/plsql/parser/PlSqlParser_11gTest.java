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
import com.deepsky.lang.plsql.psi.PackageSpec;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiFile;
import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


public class PlSqlParser_11gTest extends TestCase {

    String dbms_logmnr_pks = "dbms_logmnr.pks";
    String dbms_utility_pks = "dbms_utility.pks";
    String dbms_stats_pks = "dbms_stats.pks";
    String dbms_describe_pks = "dbms_describe.pks";
    String dbms_advisor_pks = "dbms_advisor.pks";

    String DBMS_CRYPTO_PKG = "DBMS_CRYPTO.PKS";

    File base;

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("11g").toURI());
    }

    public void test_DBMS_CRYPTO_PKG() throws IOException {

        String content = StringUtils.file2string(new File(base, DBMS_CRYPTO_PKG));
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode root = generator.parse(content);

        assertNotNull(root);
        assertTrue(root.getPsi() instanceof PsiFile);
        ASTNode pkg = root.findChildByType(PlSqlElementTypes.PACKAGE_SPEC);
        assertNotNull(pkg);
        assertEquals("DBMS_CRYPTO", ((PackageSpec) pkg.getPsi()).getPackageName());
        PlSqlElement[] dbos = ((PackageSpec) pkg.getPsi()).getObjects();
        assertEquals(40, dbos.length);
    }

    public void test_dbms_advisor_pks() throws IOException {
        String content = StringUtils.file2string(new File(base, dbms_advisor_pks));
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode root = generator.parse(content);

        assertNotNull(root);
        assertTrue(root.getPsi() instanceof PsiFile);
        ASTNode pkg = root.findChildByType(PlSqlElementTypes.PACKAGE_SPEC);
        assertNotNull(pkg);

        assertEquals("dbms_advisor", ((PackageSpec) pkg.getPsi()).getPackageName());
        PlSqlElement[] dbos = ((PackageSpec) pkg.getPsi()).getObjects();
        assertEquals(95, dbos.length);
    }

    public void test_dbms_logmnr_pks() throws IOException {
        String content = StringUtils.file2string(new File(base, dbms_logmnr_pks));
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode root = generator.parse(content);

        assertNotNull(root);
        assertTrue(root.getPsi() instanceof PsiFile);
        ASTNode pkg = root.findChildByType(PlSqlElementTypes.PACKAGE_SPEC);
        assertNotNull(pkg);

        assertEquals("dbms_logmnr", ((PackageSpec) pkg.getPsi()).getPackageName());
        PlSqlElement[] dbos = ((PackageSpec) pkg.getPsi()).getObjects();
        assertEquals(27, dbos.length);
    }

/*
TODO - fix me
    public void test_dbms_utility_pks() throws IOException {
        String content = StringUtils.file2string(new File(base, dbms_utility_pks));
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode root = generator.parse(content);

        assertNotNull(root);
        assertTrue(root.getPsi() instanceof PsiFile);
        ASTNode pkg = root.findChildByType(PlSqlElementTypes.PACKAGE_SPEC);
        assertNotNull(pkg);

        assertEquals("dbms_utility", ((PackageSpec) pkg.getPsi()).getPackageName());
        PlSqlElement[] dbos = ((PackageSpec) pkg.getPsi()).getObjects();
        assertEquals(3, dbos.length);
    }
*/

    public void test_dbms_stats_pks() throws IOException {
        String content = StringUtils.file2string(new File(base, dbms_stats_pks));
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode root = generator.parse(content);

        assertNotNull(root);
        assertTrue(root.getPsi() instanceof PsiFile);
        ASTNode pkg = root.findChildByType(PlSqlElementTypes.PACKAGE_SPEC);
        assertNotNull(pkg);

        assertEquals("dbms_stats", ((PackageSpec) pkg.getPsi()).getPackageName());
        PlSqlElement[] dbos = ((PackageSpec) pkg.getPsi()).getObjects();
        assertEquals(151, dbos.length);
    }

    public void test_dbms_describe_pks() throws IOException {
        String content = StringUtils.file2string(new File(base, dbms_describe_pks));
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode root = generator.parse(content);

        assertNotNull(root);
        assertTrue(root.getPsi() instanceof PsiFile);
        ASTNode pkg = root.findChildByType(PlSqlElementTypes.PACKAGE_SPEC);
        assertNotNull(pkg);

        assertEquals("dbms_describe", ((PackageSpec) pkg.getPsi()).getPackageName());
        PlSqlElement[] dbos = ((PackageSpec) pkg.getPsi()).getObjects();
        assertEquals(3, dbos.length);
    }
}
