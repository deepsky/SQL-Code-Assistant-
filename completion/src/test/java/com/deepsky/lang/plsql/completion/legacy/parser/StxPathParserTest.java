package com.deepsky.lang.plsql.completion.legacy.parser;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.completion.syntaxTreePath.Step;
import com.deepsky.lang.plsql.completion.legacy.steps.ASTNodeStep;
import com.deepsky.lang.plsql.completion.legacy.steps.AllNodesStep;
import com.deepsky.lang.plsql.completion.legacy.steps.PsiElementStep;
import com.deepsky.lang.plsql.psi.SystemPrivilege;
import com.deepsky.lang.plsql.psi.ctrl.GrantCommand;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class StxPathParserTest {

    @Test
    public void test_PsiElementStep() throws StxParseException {

        Step step = StxPathParser.parse("/GrantCommand");
        assertNotNull(step);
        assertNull(step.getChild());
        assertTrue(step instanceof PsiElementStep);
        assertEquals(GrantCommand.class, ((PsiElementStep) step).getPsiElementClass());
    }


    @Test
    public void test_ASTNodeStep() throws StxParseException {

        Step step = StxPathParser.parse("/#GRANT_COMMAND");
        assertNotNull(step);
        assertNull(step.getChild());
        assertTrue(step instanceof ASTNodeStep);
        assertEquals(PlSqlElementTypes.GRANT_COMMAND, ((ASTNodeStep) step).getElementType());
    }

    @Test
    public void test_ASTNodeStep2() throws StxParseException {

        Step step = StxPathParser.parse("/1#IDENTIFIER");
        assertNotNull(step);
        assertNull(step.getChild());
        assertTrue(step instanceof ASTNodeStep);
        assertEquals(PlSqlTokenTypes.IDENTIFIER, ((ASTNodeStep) step).getElementType());
    }

    @Test
    public void test_mix() throws StxParseException {

        Step step = StxPathParser.parse("//1$GrantCommand/SystemPrivilege/2#IDENTIFIER");
        assertNotNull(step);
        assertTrue(step instanceof AllNodesStep);

        Step child1 = step.getChild();
        assertNotNull(child1);
        assertTrue(child1 instanceof PsiElementStep);
        assertEquals(GrantCommand.class, ((PsiElementStep) child1).getPsiElementClass());

        Step child2 = child1.getChild();
        assertNotNull(child2);
        assertTrue(child2 instanceof PsiElementStep);
        assertEquals(SystemPrivilege.class, ((PsiElementStep) child2).getPsiElementClass());

        Step child3 = child2.getChild();
        assertNotNull(child3);
        assertTrue(child3 instanceof ASTNodeStep);
        assertEquals(PlSqlTokenTypes.IDENTIFIER, ((ASTNodeStep) child3).getElementType());

        Assert.assertNull(child3.getChild());
    }

}
