package com.deepsky.lang.plsql.tree;

import com.deepsky.lang.AbstractBaseTest;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.struct.parser.SyntaxErrorException;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


public class MarkupGeneratorEx2Test extends AbstractBaseTest {

    String select_from_dual = "select 1 from dual";
    String select_from_dual2 = " select  1 from  dual ";
    String select_from_dual3 = " select  1, 'abc', 8-9/4 from  dual d";
    String select_from_dual4 = "select  1, 'abc',";  // example syntax error
    String insert_statement = "insert into tab1 (id) values ('d')";
    String insert_statement2 = " insert  into tab1  (id) values ('d') ";
    String select_insert_mix = " select  1 from  dual  insert  into tab1  (id) values ('d') ";
    String select01 = "select01.sql";
    String select02 = "select02.sql";
    String test = "test.sql";
    String create_ws_tokens = "create ";

    String expression_1 = "1+1";

    MarkupGeneratorEx2 generator;
    File base;

    public void setUp() throws URISyntaxException {
        generator = new MarkupGeneratorEx2();
        base = new File(this.getClass().getClassLoader().getResource("psiParser").toURI());

    }

    public void test_parseExpr() {
//        ASTNode root = generator.parseExpr(expression_1);

    }

    public void test_create_ws_tokens() {

        try {
            ASTNode root = generator.parse(create_ws_tokens);
            assertTrue(true);
        } catch (SyntaxErrorException e) {
            assertTrue(false);
        }
    }

    public void test_select_from_dual4() {

        try {
            ASTNode root = generator.parse(select_from_dual4);
            assertTrue(true);
        } catch (SyntaxErrorException e) {
            assertTrue(false);
        }
    }

    public void test_select02() throws IOException {
        String text = StringUtils.file2string(new File(base, select02));
        ASTNode root = generator.parse(text);
        assertNotNull(root);

        assertEquals(PlSqlTokenTypes.FILE, root.getElementType());
        assertEquals(3, root.getChildren(null).length);
        ASTNode[] nodes = root.getChildren(TokenSet.create(PLSqlTypesAdopted.SELECT_EXPRESSION)); //COMMAND);
        assertEquals(1, nodes.length);

        ASTNode[] nodes2 = nodes[0].getChildren(TokenSet.create(
                PLSqlTypesAdopted.EXPR_COLUMN,
                PLSqlTypesAdopted.ASTERISK_COLUMN,
                PLSqlTypesAdopted.IDENT_ASTERISK_COLUMN)
        );

        assertNotNull(nodes2);
        assertEquals(55, nodes2.length);
    }

    public void test_select01() throws IOException {
        String text = StringUtils.file2string(new File(base, select01));
        ASTNode root = generator.parse(text);
//        Node root = builder.buildASTTree();
        assertNotNull(root);

        assertEquals(PlSqlTokenTypes.FILE, root.getElementType());
        assertEquals(5, root.getChildren(null).length);
        ASTNode node = root.findChildByType(PLSqlTypesAdopted.SELECT_EXPRESSION); //COMMAND);
//        assertEquals(1, nodes.length);

        ASTNode[] nodes2 = node.getChildren(TokenSet.create(
                PLSqlTypesAdopted.EXPR_COLUMN,
                PLSqlTypesAdopted.ASTERISK_COLUMN,
                PLSqlTypesAdopted.IDENT_ASTERISK_COLUMN)
        );

        assertNotNull(nodes2);
        assertEquals(4, nodes2.length);
        assertEquals(PLSqlTypesAdopted.EXPR_COLUMN, nodes2[0].getElementType());
        assertEquals(PLSqlTypesAdopted.EXPR_COLUMN, nodes2[1].getElementType());
        assertEquals(PLSqlTypesAdopted.EXPR_COLUMN, nodes2[2].getElementType());
        assertEquals(PLSqlTypesAdopted.EXPR_COLUMN, nodes2[3].getElementType());

        assertEquals("agg.dde_id AS dde_id", nodes2[0].getText());
        assertEquals("agg.ivl AS ivl", nodes2[1].getText());
        assertEquals("agg.cnt AS cnt", nodes2[2].getText());
        assertEquals("agg.rnum", nodes2[3].getText());

        // get subquery
        ASTNode[] from = node.getChildren(TokenSet.create(PLSqlTypesAdopted.TABLE_REFERENCE_LIST_FROM));
        assertNotNull(from);
        assertEquals(1, from.length);
        assertEquals(PLSqlTypesAdopted.TABLE_REFERENCE_LIST_FROM, from[0].getElementType());

        ASTNode from_subquery = from[0].findChildByType(PLSqlTypesAdopted.FROM_SUBQUERY);
        assertNotNull(from_subquery);
//        assertEquals(1, from_subquery.length);

        ASTNode subquery = from_subquery.findChildByType(PLSqlTypesAdopted.SUBQUERY);
        assertNotNull(subquery);
//        assertEquals(1, subquery.length);

        ASTNode select = subquery.findChildByType(PLSqlTypesAdopted.SELECT_EXPRESSION); //COMMAND);
        assertNotNull(select);
//        assertEquals(1, select.length);

        ASTNode[] columns = select.getChildren(TokenSet.create(
                PLSqlTypesAdopted.EXPR_COLUMN,
                PLSqlTypesAdopted.ASTERISK_COLUMN,
                PLSqlTypesAdopted.IDENT_ASTERISK_COLUMN)
        );
        assertNotNull(columns);
        assertEquals(2, columns.length);
        assertEquals(PLSqlTypesAdopted.IDENT_ASTERISK_COLUMN, columns[0].getElementType());
        assertEquals(PLSqlTypesAdopted.EXPR_COLUMN, columns[1].getElementType());

        assertEquals("t.*", columns[0].getText());
        assertEquals("rownum AS rnum", columns[1].getText());

    }

    public void test_select_from_dual() {
        ASTNode root = generator.parse(select_from_dual);
        assertNotNull(root);

        assertEquals(PlSqlTokenTypes.FILE, root.getElementType());
        assertEquals(1, root.getChildren(null).length);
        ASTNode node = root.findChildByType(PLSqlTypesAdopted.SELECT_EXPRESSION); //COMMAND);
        //assertEquals(1, node);
    }

    public void test_select_from_dual2() {
        ASTNode root = generator.parse(select_from_dual2);
        assertNotNull(root);

        assertEquals(3, root.getChildren(null).length);
        // todo -
    }

    public void test_select_from_dual3() {
        ASTNode root = generator.parse(select_from_dual3);
        assertNotNull(root);

        assertEquals(2, root.getChildren(null).length);
        // todo -
    }

    public void test_insert_statement() {
        ASTNode root = generator.parse(insert_statement);
        assertNotNull(root);

        assertEquals(1, root.getChildren(null).length);
        // todo -
    }

    public void test_insert_statement2() {
        ASTNode root = generator.parse(insert_statement2);
        assertNotNull(root);

        assertEquals(3, root.getChildren(null).length);
        // todo -
    }


    public void test_select_insert_mix() {
        ASTNode root = generator.parse(select_insert_mix);
        assertNotNull(root);

        assertEquals(5, root.getChildren(null).length);
        // todo -
    }

}
