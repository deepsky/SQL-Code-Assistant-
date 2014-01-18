package com.deepsky.lang.plsql.completion.syntaxTreePath.generator;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import com.deepsky.lang.plsql.completion.AbstractCompletionTest;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.CompletionProcessor2;
import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.TreePath;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.intellij.lang.ASTNode;


public class SyntaxTreePathParserTest extends AbstractCompletionTest {


    public void test_select_3() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select * <caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("/ #ERROR_TOKEN_A / #SELECT #ASTERISK 1#C_MARKER", context.getTreePath());
        assertEquals(1, context.getHandlerParameters().length);
        assertTrue(context.getHandlerParameters()[0] instanceof ASTNode);
        assertEquals("com.deepsky.lang.plsql.completion.processors.SelectStmtProcessor", context.getMeta().getClassName());
        assertEquals("process$SelectAsterisk", context.getMeta().getMethodName());
    }

    public void test_select_31() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select <caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("/ #ERROR_TOKEN_A / #SELECT #C_MARKER", context.getTreePath());
        assertEquals(0, context.getHandlerParameters().length);
    }

    public void test_select_32() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select a <caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("/ #ERROR_TOKEN_A / #SELECT #IDENTIFIER #C_MARKER", context.getTreePath());
        assertEquals(0, context.getHandlerParameters().length);
    }

    public void test_select_33() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select a, <caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// .. #ERROR_TOKEN_A / #SELECT .. #EXPR_COLUMN #COMMA #ERROR_TOKEN_A / #C_MARKER", context.getTreePath());
        assertEquals(0, context.getHandlerParameters().length);
        CallMetaInfo call = context.getMeta();

    }

    public void test_select_34() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select a <caret> from g");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// SelectStatement / .. #EXPR_COLUMN / .. #ALIAS_NAME / #ALIAS_IDENT / 1#C_MARKER", context.getTreePath());
        assertEquals(1, context.getHandlerParameters().length);
        assertTrue(context.getHandlerParameters()[0] instanceof ASTNode);
    }


    public void test_select_35() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select a  b <caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// #SELECT .. 1#EXPR_COLUMN 2#C_MARKER", context.getTreePath());
    }

    public void test_select_351() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select a  b from <caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// .. SelectStatement / .. #TABLE_REFERENCE_LIST_FROM / .. 1#TABLE_ALIAS / #TABLE_REF / #C_MARKER", context.getTreePath());
        assertEquals(1, context.getHandlerParameters().length);
        assertTrue(context.getHandlerParameters()[0] instanceof ASTNode);
    }

    public void test_select_36() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select a b<caret> from g");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// SelectStatement / .. #EXPR_COLUMN / .. #ALIAS_NAME / #ALIAS_IDENT / 1#C_MARKER", context.getTreePath());
        assertEquals(1, context.getHandlerParameters().length);
        assertTrue(context.getHandlerParameters()[0] instanceof ASTNode);
    }

    public void test_select_361() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select (select <caret>) from g");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// SelectStatement / #SELECT .. 1#ERROR_TOKEN_A / #SUBQUERY_EXPR // #OPEN_PAREN #SELECT #C_MARKER", context.getTreePath());
        assertEquals(1, context.getHandlerParameters().length);
        assertTrue(context.getHandlerParameters()[0] instanceof ASTNode);
    }

    public void test_select_3611() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select (<caret>) from g");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// .. 1$SelectStatement / #SELECT .. 2#EXPR_COLUMN / #PARENTHESIZED_EXPR / .. #VAR_REF // #C_MARKER", context.getTreePath());
        assertEquals(2, context.getHandlerParameters().length);
        assertTrue(context.getHandlerParameters()[0] instanceof SelectStatement);
        assertTrue(context.getHandlerParameters()[1] instanceof ASTNode);
    }

    public void test_select_3612() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select a-3 from (<caret>)");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// .. SelectStatement / .. #TABLE_REFERENCE_LIST_FROM / .. #FROM_SUBQUERY / #SUBQUERY / #OPEN_PAREN #ERROR_TOKEN_A / 1#C_MARKER", context.getTreePath());
        assertEquals(1, context.getHandlerParameters().length);
    }

    public void test_select_3613() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select a-3 from tab1, (<caret>)");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// .. SelectStatement / .. #TABLE_REFERENCE_LIST_FROM / .. #FROM_SUBQUERY / #SUBQUERY / #OPEN_PAREN #ERROR_TOKEN_A / 1#C_MARKER", context.getTreePath());
    }

    public void test_select_362() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select 1-2, (select <caret>) from g");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// SelectStatement / #SELECT .. 1#ERROR_TOKEN_A / #SUBQUERY_EXPR // #OPEN_PAREN #SELECT #C_MARKER", context.getTreePath());
    }

    public void test_select_363() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select 1-2, (select <caret>) t from g");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// SelectStatement / #SELECT .. 1#ERROR_TOKEN_A / #SUBQUERY_EXPR // #OPEN_PAREN #SELECT #C_MARKER", context.getTreePath());
    }

    public void test_select_364() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select (select 1-2 from dual) asd, (select <caret>) t from g");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// SelectStatement / #SELECT .. 1#ERROR_TOKEN_A / #SUBQUERY_EXPR // #OPEN_PAREN #SELECT #C_MARKER", context.getTreePath());
    }

    public void test_select_365() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select (select 1-2 from dual) asd, (select <caret>) t ");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// #SELECT .. 1#ERROR_TOKEN_A / #SUBQUERY_EXPR // #OPEN_PAREN #SELECT #C_MARKER", context.getTreePath());
    }


    public void test_select_366() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select (select 1-2 from dual) asd, (select 12 from <caret>) t ");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// .. #EXPR_COLUMN // #SUBQUERY / .. SelectStatement / .. #TABLE_REFERENCE_LIST_FROM / .. 1#TABLE_ALIAS / #TABLE_REF / #C_MARKER", context.getTreePath());
        assertEquals(1, context.getHandlerParameters().length);
        assertTrue(context.getHandlerParameters()[0] instanceof ASTNode);
    }

    public void test_select_367() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select (select 1-2 from dual) asd, (select 12 from a order <caret>) t ");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// .. SelectStatement / .. 1#TABLE_REFERENCE_LIST_FROM .. 2#ERROR_TOKEN_A / #ORDER #C_MARKER", context.getTreePath());
        assertEquals(2, context.getHandlerParameters().length);
        assertTrue(context.getHandlerParameters()[0] instanceof ASTNode);
        assertTrue(context.getHandlerParameters()[1] instanceof ASTNode);
    }

    public void test_select_368() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select asd, t from a order by c1 <caret> ");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// 1$SelectStatement 2#C_MARKER", context.getTreePath());
        assertEquals(2, context.getHandlerParameters().length);
        assertTrue(context.getHandlerParameters()[0] instanceof SelectStatement);
        assertTrue(context.getHandlerParameters()[1] instanceof ASTNode);
    }

    public void test_select_37() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select * from (select a, <caret>)");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// .. #ERROR_TOKEN_A / #SELECT .. #EXPR_COLUMN #COMMA #ERROR_TOKEN_A / #C_MARKER", context.getTreePath());
    }

    public void test_select_38() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select (a+8)/sum(id), <caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// .. #ERROR_TOKEN_A / #SELECT .. #EXPR_COLUMN #COMMA #ERROR_TOKEN_A / #C_MARKER", context.getTreePath());
    }

    public void test_select_39() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select (a+8)/sum(id), pop from tab1 <caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// .. 1$SelectStatement / .. #TABLE_REFERENCE_LIST_FROM / .. #TABLE_ALIAS / 2#TABLE_REF #ALIAS_NAME // 3#C_MARKER", context.getTreePath());
        assertEquals(3, context.getHandlerParameters().length);
        assertTrue(context.getHandlerParameters()[0] instanceof SelectStatement);
        assertTrue(context.getHandlerParameters()[1] instanceof ASTNode);
        assertTrue(context.getHandlerParameters()[2] instanceof ASTNode);
    }

    public void test_select_391() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select (a+8)/sum(id), pop from tab1 order <caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// .. SelectStatement / .. 1#TABLE_REFERENCE_LIST_FROM .. 2#ERROR_TOKEN_A / #ORDER #C_MARKER", context.getTreePath());
    }


    public void test_select_392() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select (a+8)/sum(id), pop from tab1 where k = 1 order <caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// .. SelectStatement / .. 1#TABLE_REFERENCE_LIST_FROM .. 2#ERROR_TOKEN_A / #ORDER #C_MARKER", context.getTreePath());
    }

    public void test_select_393() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select (a+8)/sum(id), pop from tab1 order by <caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// .. SelectStatement / .. 1#TABLE_REFERENCE_LIST_FROM .. 2#ORDER_CLAUSE / .. #SORTED_DEF / #VAR_REF // #C_MARKER", context.getTreePath());
        assertEquals(2, context.getHandlerParameters().length);
        assertTrue(context.getHandlerParameters()[0] instanceof ASTNode);
        assertTrue(context.getHandlerParameters()[1] instanceof ASTNode);
    }


    public void test_select_394() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select pop from tab1 group <caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// .. SelectStatement / .. 1#TABLE_REFERENCE_LIST_FROM .. 2#ERROR_TOKEN_A / #GROUP #C_MARKER", context.getTreePath());
        assertEquals("com.deepsky.lang.plsql.completion.processors.SelectStmtProcessor", context.getMeta().getClassName());
        assertEquals("process$SelectGroupBy2", context.getMeta().getMethodName());
    }

    public void test_select_395() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select pop from tab1 group by <caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// .. SelectStatement / .. 1#TABLE_REFERENCE_LIST_FROM .. 2#GROUP_CLAUSE / .. #VAR_REF // #C_MARKER", context.getTreePath());
        assertEquals("com.deepsky.lang.plsql.completion.processors.SelectStmtProcessor", context.getMeta().getClassName());
        assertEquals("process$SelectGroupBy", context.getMeta().getMethodName());
    }

    public void test_select_396() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select pop from tab1 group by pop <caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// 1$SelectStatement 2#C_MARKER", context.getTreePath());
        assertEquals(2, context.getHandlerParameters().length);
        assertTrue(context.getHandlerParameters()[0] instanceof SelectStatement);
        assertTrue(context.getHandlerParameters()[1] instanceof ASTNode);
        assertEquals("com.deepsky.lang.plsql.completion.processors.SelectStmtProcessor", context.getMeta().getClassName());
        assertEquals("process$SelectAppender", context.getMeta().getMethodName());
    }


    public void test_select_397() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select pop from tab1 group by pop, <caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// .. SelectStatement / .. 1#TABLE_REFERENCE_LIST_FROM .. 2#GROUP_CLAUSE / .. #VAR_REF // #C_MARKER", context.getTreePath());
        assertEquals(2, context.getHandlerParameters().length);
        assertTrue(context.getHandlerParameters()[0] instanceof ASTNode);
        assertTrue(context.getHandlerParameters()[1] instanceof ASTNode);
        assertEquals("com.deepsky.lang.plsql.completion.processors.SelectStmtProcessor", context.getMeta().getClassName());
        assertEquals("process$SelectGroupBy", context.getMeta().getMethodName());
    }

    public void test_select_398() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select pop from tab1 group by pop having <caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("// .. SelectStatement / .. 1#TABLE_REFERENCE_LIST_FROM .. 2#GROUP_CLAUSE / .. #VAR_REF // #C_MARKER", context.getTreePath());
        assertEquals(2, context.getHandlerParameters().length);
        assertTrue(context.getHandlerParameters()[0] instanceof ASTNode);
        assertTrue(context.getHandlerParameters()[1] instanceof ASTNode);
        assertEquals("com.deepsky.lang.plsql.completion.processors.SelectStmtProcessor", context.getMeta().getClassName());
        assertEquals("process$SelectGroupBy", context.getMeta().getMethodName());
    }

    public void test_update_1() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("update <caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("/ #UPDATE_COMMAND // #UPDATE #TABLE_ALIAS / 1#TABLE_REF / #C_MARKER", context.getTreePath());
        assertEquals(1, context.getHandlerParameters().length);
        assertEquals("com.deepsky.lang.plsql.completion.processors.UpdateStmtProcessor", context.getMeta().getClassName());
        assertEquals("process$UpdateTabRef", context.getMeta().getMethodName());
    }

    public void test_update_11() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("update tab1 <caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("/ #UPDATE_COMMAND // #UPDATE #TABLE_ALIAS / 1#TABLE_REF #ALIAS_NAME // #C_MARKER", context.getTreePath());
        assertEquals(1, context.getHandlerParameters().length);
        assertEquals("com.deepsky.lang.plsql.completion.processors.UpdateStmtProcessor", context.getMeta().getClassName());
        assertEquals("process$UpdateTabAlias", context.getMeta().getMethodName());
    }


    public void test_update_12() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("update tab1 set <caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("/ #UPDATE_COMMAND // #UPDATE #TABLE_ALIAS #SET #ERROR_TOKEN_A / #C_MARKER", context.getTreePath());
        assertEquals(0, context.getHandlerParameters().length);
        assertEquals("com.deepsky.lang.plsql.completion.processors.UpdateStmtProcessor", context.getMeta().getClassName());
        assertEquals("process$UpdateColumnName", context.getMeta().getMethodName());
    }

    public void test_update_13() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("update tab1 set a = <caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("/ #UPDATE_COMMAND // #UPDATE 1#TABLE_ALIAS #SET .. 2#COLUMN_SPEC #EQ #VAR_REF // #C_MARKER", context.getTreePath());
        assertEquals(2, context.getHandlerParameters().length);
        assertEquals("com.deepsky.lang.plsql.completion.processors.UpdateStmtProcessor", context.getMeta().getClassName());
        assertEquals("process$UpdateColumnVar", context.getMeta().getMethodName());
    }



    public void test4_1() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select 1 from dual; create table abc( id number); <caret> ");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();

        assertEquals("/ .. #ERROR_TOKEN_A / .. #SEMI #C_MARKER", context.getTreePath());
        assertEquals(0, context.getHandlerParameters().length);
        assertEquals("com.deepsky.lang.plsql.completion.processors.GenericProcessor", context.getMeta().getClassName());
        assertEquals("process$Start3", context.getMeta().getMethodName());
    }


    public void test4_11() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("create sequence te_seq;\n" +
                "\n" +
                "create table tab1 (\n" +
                "    id number,\n" +
                "    text varchar2(23)\n" +
                ");\n" +
                "\n" +
                "select <caret> from tab1");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();

        assertEquals("/ .. 1$SelectStatement / #SELECT .. 2#EXPR_COLUMN / #VAR_REF / #NAME_FRAGMENT / #C_MARKER", context.getTreePath());
        assertEquals(2, context.getHandlerParameters().length);
        assertTrue(context.getHandlerParameters()[0] instanceof SelectStatement);
        assertEquals("com.deepsky.lang.plsql.completion.processors.SelectStmtProcessor", context.getMeta().getClassName());
        assertEquals("process$SelectVarRef", context.getMeta().getMethodName());
    }



    protected String offset(int offset) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < offset; i++)
            b.append(" ");
        return b.toString();
    }

}
