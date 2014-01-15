package com.deepsky.lang.plsql.completion.legacy.logic;

import antlr.TokenStreamException;
import com.deepsky.lang.plsql.completion.AbstractCompletionTest;
import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.TreePath;

import java.util.ArrayList;
import java.util.List;

public class TreePathImplTest extends AbstractCompletionTest {


    public void test0() throws TokenStreamException {
        String text = "<caret>";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("/#ERROR_TOKEN_A/1#IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new TreePathUtil.SyntaxTreeProcessor() {
            @Override
            public void handle(STPPattern pattern, String prefix, TreePath.ArgumentDesc[] args) {
                resList.add(_2String(pattern, prefix, args));
            }
        });

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("/#ERROR_TOKEN_A/1#IDENTIFIER--1-IDENTIFIER", resList.get(0));
    }


    public void test_select_001() throws TokenStreamException {
        String text = "se<caret>";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("/#ERROR_TOKEN_A/1#IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new TreePathUtil.SyntaxTreeProcessor() {
            @Override
            public void handle(STPPattern pattern, String prefix, TreePath.ArgumentDesc[] args) {
                resList.add(_2String(pattern, prefix, args));
            }
        });

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("/#ERROR_TOKEN_A/1#IDENTIFIER-se-1-IDENTIFIER", resList.get(0));
    }


    public void test_select_102() throws TokenStreamException {
        String text = "select * <caret>";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("//#SELECT #ASTERISK 1#IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("//#SELECT #ASTERISK 1#IDENTIFIER--1-IDENTIFIER", resList.get(0));
    }

    public void test_select_004() throws TokenStreamException {
        String text = "select <caret>";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("/#ERROR_TOKEN_A/#SELECT #IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("/#ERROR_TOKEN_A/#SELECT #IDENTIFIER-", resList.get(0));
    }

    public void test_select_005() throws TokenStreamException {
        String text = "select a <caret>";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("/#ERROR_TOKEN_A/#SELECT #IDENTIFIER #IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("/#ERROR_TOKEN_A/#SELECT #IDENTIFIER #IDENTIFIER-", resList.get(0));
    }


    public void test_select_030() throws TokenStreamException {
        String text = "select a, <caret>";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("//#ERROR_TOKEN_A/#SELECT ..#EXPR_COLUMN #COMMA #ERROR_TOKEN_A/#IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("//#ERROR_TOKEN_A/#SELECT .. #EXPR_COLUMN #COMMA #ERROR_TOKEN_A/#IDENTIFIER-", resList.get(0));
    }

    public void test_select_031() throws TokenStreamException {
        String text = "select * from (select a, <caret>)";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("//#ERROR_TOKEN_A/#SELECT ..#EXPR_COLUMN #COMMA #ERROR_TOKEN_A/#IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("//#ERROR_TOKEN_A/#SELECT .. #EXPR_COLUMN #COMMA #ERROR_TOKEN_A/#IDENTIFIER-", resList.get(0));
    }

    public void test_select_002() throws TokenStreamException {
        String text = "select a <caret> from g";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("/SelectStatement/..#EXPR_COLUMN/..#ALIAS_NAME/#ALIAS_IDENT/1#IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("/SelectStatement/.. #EXPR_COLUMN/.. #ALIAS_NAME/#ALIAS_IDENT/1#IDENTIFIER--1-IDENTIFIER", resList.get(0));
    }

    public void test_select_003() throws TokenStreamException {
        String text = "select a b<caret> from g";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("/SelectStatement/..#EXPR_COLUMN//1#ALIAS_IDENT")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("/SelectStatement/.. #EXPR_COLUMN//1#ALIAS_IDENT-b-1-ALIAS_IDENT", resList.get(0));
    }


    public void test_select_006() throws TokenStreamException {
        String text = "select (a+8)/sum(id), <caret>";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("//#SELECT ..#EXPR_COLUMN #COMMA #ERROR_TOKEN_A/#IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("//#SELECT .. #EXPR_COLUMN #COMMA #ERROR_TOKEN_A/#IDENTIFIER-", resList.get(0));
    }

    public void test_select_007() throws TokenStreamException {
        String text = "select (a+8)/sum(id), pop from tab1 <caret>";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("//SelectStatement/..#TABLE_REFERENCE_LIST_FROM/..1#TABLE_ALIAS//#ALIAS_IDENT")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("//SelectStatement/.. #TABLE_REFERENCE_LIST_FROM/.. 1#TABLE_ALIAS//#ALIAS_IDENT--1-TABLE_ALIAS", resList.get(0));
    }

    public void test_select_008() throws TokenStreamException {
        String text = "select (a+8)/sum(id), pop from tab1 order <caret>";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("/SelectStatement/..1#TABLE_REFERENCE_LIST_FROM .. #ORDER #IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("/SelectStatement/.. 1#TABLE_REFERENCE_LIST_FROM .. #ORDER #IDENTIFIER--1-TABLE_REFERENCE_LIST_FROM", resList.get(0));
    }

    public void test_select_098() throws TokenStreamException {
        String text = "select (a+8)/sum(id), pop from tab1 where k = 1 order <caret>";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("/SelectStatement/..1#TABLE_REFERENCE_LIST_FROM .. #ORDER #IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("/SelectStatement/.. 1#TABLE_REFERENCE_LIST_FROM .. #ORDER #IDENTIFIER--1-TABLE_REFERENCE_LIST_FROM", resList.get(0));
    }

    public void test_select_009() throws TokenStreamException {
        String text = "select (a+8)/sum(id), pop from tab1 order by <caret>";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("//SelectStatement/..1#TABLE_REFERENCE_LIST_FROM ..2#ORDER_CLAUSE//#VAR_REF//#IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("//SelectStatement/.. 1#TABLE_REFERENCE_LIST_FROM .. 2#ORDER_CLAUSE//#VAR_REF//#IDENTIFIER--2-TABLE_REFERENCE_LIST_FROM-ORDER_CLAUSE", resList.get(0));
    }


    public void test_select_010() throws TokenStreamException {
        String text = "select pop from tab1 group <caret>";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("/SelectStatement/..#GROUP #IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("/SelectStatement/.. #GROUP #IDENTIFIER-", resList.get(0));
    }

    public void test_select_011() throws TokenStreamException {
        String text = "select pop from tab1 group by <caret>";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("//1$SelectStatement/..2#TABLE_REFERENCE_LIST_FROM 3#GROUP_CLAUSE/..#VAR_REF//#IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("//1$SelectStatement/.. 2#TABLE_REFERENCE_LIST_FROM 3#GROUP_CLAUSE/.. #VAR_REF//#IDENTIFIER--3-SelectStatementImpl-TABLE_REFERENCE_LIST_FROM-GROUP_CLAUSE", resList.get(0));
    }

    public void test_select_012() throws TokenStreamException {
        String text = "select pop from tab1 group by pop <caret>";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("//1$SelectStatement 2#IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("//1$SelectStatement 2#IDENTIFIER--2-SelectStatementImpl-IDENTIFIER", resList.get(0));
    }


    public void test_select_020() throws TokenStreamException {
        String text = "select pop from tab1, (select <caret>) ";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("//1$SelectStatement 2#IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("//1$SelectStatement 2#IDENTIFIER--2-SelectStatementImpl-IDENTIFIER", resList.get(0));
    }

    public void test_select_021() throws TokenStreamException {
        String text = "select pop from tab1, (select * <caret>) ";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("//1$SelectStatement 2#IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("//1$SelectStatement 2#IDENTIFIER--2-SelectStatementImpl-IDENTIFIER", resList.get(0));
    }

    public void test_select_022() throws TokenStreamException {
        String text = "select pop from tab1, (select a-9, <caret>) ";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("//1$SelectStatement 2#IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("//1$SelectStatement 2#IDENTIFIER--2-SelectStatementImpl-IDENTIFIER", resList.get(0));
    }


    public void test_select_023() throws TokenStreamException {
        String text = "select pop from tab1, (select a-9, f1 from <caret>) ";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("//1$SelectStatement 2#IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("//1$SelectStatement 2#IDENTIFIER--2-SelectStatementImpl-IDENTIFIER", resList.get(0));
    }

    public void test_update000() throws TokenStreamException {
        String text = "update <caret> ";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("/#UPDATE_COMMAND//#UPDATE..#TABLE_ALIAS/1#TABLE_REF")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("/#UPDATE_COMMAND//#UPDATE .. #TABLE_ALIAS/1#TABLE_REF--1-TABLE_REF", resList.get(0));
    }


    public void test_update001() throws TokenStreamException {
        String text = "update <caret> ";
        TreePath path = parseScript1(text);

        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("//#TABLE_REF/1#IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("//#TABLE_REF/1#IDENTIFIER--1-IDENTIFIER", resList.get(0));
    }


    public void test_update002() throws TokenStreamException {
        TreePath path = parseScript1("update a<caret> ");
        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("//1#TABLE_REF")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("//1#TABLE_REF-a-1-TABLE_REF", resList.get(0));
    }


    public void test_update010() throws TokenStreamException {
        TreePath path = parseScript1("update tab1 e<caret> ");
        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("//1#TABLE_REF")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("//1#TABLE_REF-e-1-TABLE_REF", resList.get(0));
    }

    public void test_update015() throws TokenStreamException {
        TreePath path = parseScript1("update tab1 e<caret> ");
        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("//1#TABLE_REF 2#ALIAS_NAME")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("//1#TABLE_REF 2#ALIAS_NAME-e-2-TABLE_REF-ALIAS_NAME", resList.get(0));
    }

    public void test_update011() throws TokenStreamException {
        TreePath path = parseScript1("update tab1 <caret> ");
        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("//..1#ALIAS_NAME")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("//.. 1#ALIAS_NAME--1-ALIAS_NAME", resList.get(0));
    }


    public void test_update012() throws TokenStreamException {
        TreePath path = parseScript1("update tab1 set a = <caret> ");
        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("//..#VAR_REF/1#NAME_FRAGMENT")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("//.. #VAR_REF/1#NAME_FRAGMENT--1-NAME_FRAGMENT", resList.get(0));
    }


    public void test_update013() throws TokenStreamException {
        TreePath path = parseScript1("update tab1 set a = <caret> ");
        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("//#UPDATE 1#TABLE_ALIAS .. #VAR_REF/1#NAME_FRAGMENT")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("//#UPDATE 1#TABLE_ALIAS .. #VAR_REF/1#NAME_FRAGMENT--2-TABLE_ALIAS-NAME_FRAGMENT", resList.get(0));
    }


    public void test_update014() throws TokenStreamException {
        TreePath path = parseScript1("update tab1 set b<caret> ");
        STPPattern[] stxPattern = new STPPattern[]{
                STPPatternParser.parse("//#UPDATE 1#TABLE_ALIAS #SET #ERROR_TOKEN_A/#2#IDENTIFIER")};

        final List<String> resList = new ArrayList<String>();
        boolean result = TreePathUtil.processPattern(path,stxPattern, new StxPathProcessor(resList));

        assertTrue(result);
        assertEquals(1, resList.size());
        assertEquals("//#UPDATE 1#TABLE_ALIAS #SET #ERROR_TOKEN_A/2#IDENTIFIER-b-2-TABLE_ALIAS-IDENTIFIER", resList.get(0));
    }




    // ---------------------------------------------------------------------
    // ---------------------------------------------------------------------
    class StxPathProcessor implements TreePathUtil.SyntaxTreeProcessor {

        List<String> resList;
        public StxPathProcessor(List<String> resList){
            this.resList = resList;
        }
        @Override
        public void handle(STPPattern pattern, String prefix, TreePath.ArgumentDesc[] args) {
            resList.add(_2String(pattern, prefix, args));
        }
    }

    private String _2String(STPPattern pattern, String prefix, TreePath.ArgumentDesc[] args){
        StringBuilder b = new StringBuilder(pattern.toString());

        b.append("-").append(prefix);
        if(args.length > 0){
            b.append("-").append(args.length);
            for (Object arg : args) {
                b.append("-").append(arg);
            }
        }
        return b.toString();
    }

}
