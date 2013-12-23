package com.deepsky.lang.plsql.resolver.utils;



import com.deepsky.lang.AbstractBaseTest;
import com.deepsky.lang.PsiUtil;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;

import java.util.ArrayList;
import java.util.List;

public class PsiUtilTest extends AbstractBaseTest {

    public void test_iterateBack_select0() {
        String sql = "select * from dual;";
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode root = generator.parse(sql);
        PsiElement psiRoot = root.getPsi();

        PsiElement[] result1 = iterateBack(psiRoot.findElementAt(sql.indexOf("*")));
        assertEquals(1, result1.length);
        assertEquals("select", result1[0].getText());

        result1 = iterateBack(psiRoot.findElementAt(sql.indexOf("from")));
        assertEquals(2, result1.length);
        assertEquals("select", result1[0].getText());
        assertEquals("*", result1[1].getText());

        result1 = iterateBack(psiRoot.findElementAt(sql.indexOf("dual")));
        assertEquals(3, result1.length);
        assertEquals("select", result1[0].getText());
        assertEquals("*", result1[1].getText());
        assertEquals("from", result1[2].getText());

        result1 = iterateBack(psiRoot.findElementAt(sql.indexOf(";")));
        assertEquals(4, result1.length);
        assertEquals("select", result1[0].getText());
        assertEquals("*", result1[1].getText());
        assertEquals("from", result1[2].getText());
        assertEquals("dual", result1[3].getText());
    }


    public void test_iterateBack_select1() {
        String sql = "select substr('abc', 15, 24) from dual;";
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode root = generator.parse(sql);
        PsiElement psiRoot = root.getPsi();

        PsiElement[] result1 = iterateBack(psiRoot.findElementAt(sql.indexOf("24")));
        assertEquals(7, result1.length);
        assertEquals("select", result1[0].getText());
        assertEquals("substr", result1[1].getText());
        assertEquals("(", result1[2].getText());
        assertEquals("'abc'", result1[3].getText());
        assertEquals(",", result1[4].getText());
        assertEquals("15", result1[5].getText());
        assertEquals(",", result1[6].getText());
    }

    public void test_iterateBack_select2() {
        String sql = "select substr('abc', 15, 24) from dual;";
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode root = generator.parse(sql);
        PsiElement psiRoot = root.getPsi();

        PsiElement[] result1 = iterateBack(psiRoot.findElementAt(sql.indexOf("15")), false);
        assertEquals(1, result1.length);
        assertEquals(",", result1[0].getText());
    }

    public void test_iterateBack_select_errored1() {
        String sql = "select substr('abc', 15, ) from dual;";
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode root = generator.parse(sql);
        PsiElement psiRoot = root.getPsi();

        PsiElement[] result1 = iterateBack(psiRoot.findElementAt(sql.indexOf(")")));
        assertEquals(7, result1.length);
        assertEquals("select", result1[0].getText());
        assertEquals("substr", result1[1].getText());
        assertEquals("(", result1[2].getText());
        assertEquals("'abc'", result1[3].getText());
        assertEquals(",", result1[4].getText());
        assertEquals("15", result1[5].getText());
        assertEquals(",", result1[6].getText());

        result1 = iterateBack(psiRoot.findElementAt(sql.indexOf(";")));
        assertEquals(10, result1.length);
        assertEquals("select", result1[0].getText());
        assertEquals("substr", result1[1].getText());
        assertEquals("(", result1[2].getText());
        assertEquals("'abc'", result1[3].getText());
        assertEquals(",", result1[4].getText());
        assertEquals("15", result1[5].getText());
        assertEquals(",", result1[6].getText());
        assertEquals(")", result1[7].getText());
        assertEquals("from", result1[8].getText());
        assertEquals("dual", result1[9].getText());
    }

    public void test_iterateBack_select_errored2() {
        String sql = "select substr('abc', length('mnj' || 689 || % ), ) from dual;";
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode root = generator.parse(sql);
        PsiElement psiRoot = root.getPsi();

        PsiElement[] result1 = iterateBack(psiRoot.findElementAt(sql.indexOf(")")));
        assertEquals(12, result1.length);
        assertEquals("select", result1[0].getText());
        assertEquals("substr", result1[1].getText());
        assertEquals("(", result1[2].getText());
        assertEquals("'abc'", result1[3].getText());
        assertEquals(",", result1[4].getText());
        assertEquals("length", result1[5].getText());
        assertEquals("(", result1[6].getText());
        assertEquals("'mnj'", result1[7].getText());
        assertEquals("||", result1[8].getText());
        assertEquals("689", result1[9].getText());
        assertEquals("||", result1[10].getText());
        assertEquals("%", result1[11].getText());

        result1 = iterateBack(psiRoot.findElementAt(sql.indexOf("||")));
        assertEquals(8, result1.length);
        assertEquals("select", result1[0].getText());
        assertEquals("substr", result1[1].getText());
        assertEquals("(", result1[2].getText());
        assertEquals("'abc'", result1[3].getText());
        assertEquals(",", result1[4].getText());
        assertEquals("length", result1[5].getText());
        assertEquals("(", result1[6].getText());
        assertEquals("'mnj'", result1[7].getText());
    }


    public void test_nextLeaf_select0() {
        String sql = "select * from dual;";
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode root = generator.parse(sql);
        PsiElement psiRoot = root.getPsi();

        PsiElement result1 = PsiUtil.nextNonWSLeaf(psiRoot.findElementAt(sql.indexOf("select")));
        assertNotNull(result1);
        assertEquals("*", result1.getText());

        result1 = PsiUtil.nextNonWSLeaf(psiRoot.findElementAt(sql.indexOf("*")));
        assertNotNull(result1);
        assertEquals("from", result1.getText());

        result1 = PsiUtil.nextNonWSLeaf(psiRoot.findElementAt(sql.indexOf("from")));
        assertNotNull(result1);
        assertEquals("dual", result1.getText());

        result1 = PsiUtil.nextNonWSLeaf(psiRoot.findElementAt(sql.indexOf("dual")));
        assertNotNull(result1);
        assertEquals(";", result1.getText());

        result1 = PsiUtil.nextNonWSLeaf(psiRoot.findElementAt(sql.indexOf(";")));
        assertNull(result1);
    }


    public void test_nextLeaf_select_errored0() {
        String sql = "select * from ;";
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode root = generator.parse(sql);
        PsiElement psiRoot = root.getPsi();

        PsiElement result1 = PsiUtil.nextNonWSLeaf(psiRoot.findElementAt(sql.indexOf("select")));
        assertNotNull(result1);
        assertEquals("*", result1.getText());

        result1 = PsiUtil.nextNonWSLeaf(psiRoot.findElementAt(sql.indexOf("*")));
        assertNotNull(result1);
        assertEquals("from", result1.getText());

        result1 = PsiUtil.nextNonWSLeaf(psiRoot.findElementAt(sql.indexOf("from")));
        assertNotNull(result1);
        assertEquals(";", result1.getText());

        result1 = PsiUtil.nextNonWSLeaf(psiRoot.findElementAt(sql.indexOf(";")));
        assertNull(result1);
    }


    public void test_nextLeaf_select_errored1() {
        String sql = "select 2 def top1 r$ ;";
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        ASTNode root = generator.parse(sql);
        PsiElement psiRoot = root.getPsi();

        PsiElement result1 = PsiUtil.nextNonWSLeaf(psiRoot.findElementAt(sql.indexOf("select")));
        assertNotNull(result1);
        assertEquals("2", result1.getText());

        result1 = PsiUtil.nextNonWSLeaf(psiRoot.findElementAt(sql.indexOf("2")));
        assertNotNull(result1);
        assertEquals("def", result1.getText());

        result1 = PsiUtil.nextNonWSLeaf(psiRoot.findElementAt(sql.indexOf("def")));
        assertNotNull(result1);
        assertEquals("top1", result1.getText());

        result1 = PsiUtil.nextNonWSLeaf(psiRoot.findElementAt(sql.indexOf("r$")));
        assertNotNull(result1);
        assertEquals(";", result1.getText());

        result1 = PsiUtil.nextNonWSLeaf(psiRoot.findElementAt(sql.indexOf(";")));
        assertNull(result1);
    }

    private PsiElement[] iterateBack(PsiElement element) {
        return iterateBack(element, true);
    }

    private PsiElement[] iterateBack(PsiElement element, final boolean ret) {
        final List<PsiElement> out = new ArrayList<PsiElement>();
        PsiUtil.iterateBackOverNonWSLeafs(element, new PsiUtil.PsiElementHandler() {
            public boolean handle(PsiElement e) {
                out.add(0, e);
                return ret;
            }
        });

        return out.toArray(new PsiElement[out.size()]);
    }
}
