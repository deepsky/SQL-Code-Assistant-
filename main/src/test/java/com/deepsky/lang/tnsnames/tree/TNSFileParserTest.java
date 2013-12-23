package com.deepsky.lang.tnsnames.tree;

import com.deepsky.lang.AbstractBaseTest;
import com.deepsky.lang.common.tnsnames.TNSPsiTokenTypes;
import com.deepsky.lang.parser.tns.TNSTypesAdopted;
import com.deepsky.lang.PsiUtil;
import com.deepsky.lang.tnsnames.psi.NetServiceDesc;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TNSFileParserTest extends AbstractBaseTest {

    String tns_trivial = "tns_trivial.ora";
    String tns1 = "tns1.ora";
    String tns2 = "tns2.ora";
    String tns4 = "tns4.ora";
    String tns5 = "tns5.ora";
    String tnsnames0 = "tnsnames0.ora";

    File base;

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("tns").toURI());
    }

    public void test_tns_trivial() throws IOException {

        String content = StringUtils.file2string(new File(base, tns_trivial));
        TNSFileParser parser = new TNSFileParser();
        ASTNode root = parser.parse(content);

        assertNotNull(root);
        ASTNode[] nodes = root.getChildren(null);
        assertEquals(3, nodes.length);
        assertEquals(TNSPsiTokenTypes.LF, nodes[0].getElementType());
        assertEquals(TNSTypesAdopted.NET_SERVICE_DESC, nodes[1].getElementType());
        assertEquals(TNSPsiTokenTypes.LF, nodes[2].getElementType());

        ASTNode service_name = nodes[1].findChildByType(TNSTypesAdopted.NETWORK_ALIAS);
        assertNotNull(service_name);
        assertEquals("ORA", service_name.getText());
    }

    public void test_tns1() throws IOException {

        String content = StringUtils.file2string(new File(base, tns1));
        TNSFileParser parser = new TNSFileParser();
        ASTNode root = parser.parse(content);

        assertNotNull(root);
        ASTNode[] nodes = root.getChildren(null);
        assertEquals(4, nodes.length);
        assertEquals(TNSPsiTokenTypes.SL_COMMENT, nodes[0].getElementType());
        assertEquals(TNSPsiTokenTypes.LF, nodes[1].getElementType());
        assertEquals(TNSTypesAdopted.NET_SERVICE_DESC, nodes[2].getElementType());
        assertEquals(TNSPsiTokenTypes.LF, nodes[3].getElementType());

        ASTNode service_name = nodes[2].findChildByType(TNSTypesAdopted.NETWORK_ALIAS);
        assertNotNull(service_name);
        assertEquals("net_service_name", service_name.getText());
    }

    public void test_tns2() throws IOException {

        String content = StringUtils.file2string(new File(base, tns2));
        TNSFileParser parser = new TNSFileParser();
        ASTNode root = parser.parse(content);

        assertNotNull(root);
        ASTNode[] nodes = root.getChildren(TokenSet.create(TNSTypesAdopted.NET_SERVICE_DESC));
        assertEquals(2, nodes.length);

        assertTrue(PsiUtil.isASTValid(nodes[0]));
        assertTrue(PsiUtil.isASTValid(nodes[1]));
        assertEquals("sample1", nodes[0].findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText());
        assertEquals("sample2", nodes[1].findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText());

    }

    public void test_tns4() throws IOException {

        String content = StringUtils.file2string(new File(base, tns4));
        TNSFileParser parser = new TNSFileParser();
        ASTNode root = parser.parse(content);

        assertNotNull(root);
        ASTNode[] nodes = root.getChildren(TokenSet.create(TNSTypesAdopted.NET_SERVICE_DESC));
        assertEquals(4, nodes.length);
        assertEquals("berlin", nodes[0].findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText());
        assertTrue(PsiUtil.isASTValid(nodes[0]));
        assertEquals("ORCL.WORLD", nodes[1].findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText());
        assertTrue(PsiUtil.isASTValid(nodes[1]));
        assertEquals("donna-beq.gennick.org", nodes[2].findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText());
        assertTrue(PsiUtil.isASTValid(nodes[2]));
        assertEquals("spx2tcp", nodes[3].findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText());
        assertTrue(PsiUtil.isASTValid(nodes[3]));
    }


    public void test_tns5() throws IOException {

        String content = StringUtils.file2string(new File(base, tns5));
        TNSFileParser parser = new TNSFileParser();
        ASTNode root = parser.parse(content);

        assertNotNull(root);
        ASTNode[] nodes = root.getChildren(TokenSet.create(TNSTypesAdopted.NET_SERVICE_DESC));
        assertEquals(6, nodes.length);
        assertEquals("net_service_name", nodes[0].findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText());
        assertEquals("net_service_name2", nodes[1].findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText());
        assertEquals("net_service_name3", nodes[2].findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText());
        assertEquals("net_service_name4", nodes[3].findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText());
        assertEquals("net_service_name5", nodes[4].findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText());
        assertEquals("net_service_name6", nodes[5].findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText());

        assertTrue(PsiUtil.isASTValid(nodes[0]));
        assertTrue(PsiUtil.isASTValid(nodes[1]));
        assertTrue(PsiUtil.isASTValid(nodes[2]));
        assertTrue(PsiUtil.isASTValid(nodes[3]));
        assertTrue(PsiUtil.isASTValid(nodes[4]));
        assertTrue(PsiUtil.isASTValid(nodes[5]));
    }


    public void test_tnsnames0() throws IOException {

        String content = StringUtils.file2string(new File(base, tnsnames0));
        TNSFileParser parser = new TNSFileParser();
        ASTNode root = parser.parse(content);

        assertNotNull(root);
        ASTNode[] nodes = root.getChildren(TokenSet.create(TNSTypesAdopted.NET_SERVICE_DESC));
        assertEquals(7, nodes.length);
        assertEquals("ORCL", nodes[0].findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText());
        assertEquals("ORA", nodes[1].findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText());
        assertEquals("EXTPROC_CONNECTION_DATA", nodes[2].findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText());
        assertEquals("qaitddb1", nodes[3].findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText());
        assertEquals("qaitddb.qaitdora", nodes[4].findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText());
        assertEquals("qaitddb8000", nodes[5].findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText());
        assertEquals("sodsprd", nodes[6].findChildByType(TNSTypesAdopted.NETWORK_ALIAS).getText());

        assertTrue(PsiUtil.isASTValid(nodes[0]));
        assertTrue(PsiUtil.isASTValid(nodes[1]));
        assertTrue(PsiUtil.isASTValid(nodes[2]));
        assertTrue(PsiUtil.isASTValid(nodes[3]));
        assertTrue(PsiUtil.isASTValid(nodes[4]));
        assertTrue(PsiUtil.isASTValid(nodes[5]));
        assertTrue(PsiUtil.isASTValid(nodes[6]));
    }

    @Test
    public void test_tns_psi() throws IOException {

        String content = StringUtils.file2string(new File(base, "tns5.ora"));
        TNSFileParser parser = new TNSFileParser();
        ASTNode root = parser.parse(content);

        assertNotNull(root);
        ASTNode[] nodes = root.getChildren(TokenSet.create(TNSTypesAdopted.NET_SERVICE_DESC));
        assertEquals(6, nodes.length);

        NetServiceDesc e1 = (NetServiceDesc) nodes[0].getPsi();
        assertEquals("net_service_name", e1.getNetworkAlias());
        assertEquals(2, e1.getAddressInfos().length);
        assertEquals(3, e1.getAddressInfos()[0].getElements().length);
        assertEquals(3, e1.getAddressInfos()[1].getElements().length);

        assertEquals("tcp", e1.getAddressInfos()[0].findElementByName("PRoToCOL").getElementValue());
        assertEquals("sales1-svr", e1.getAddressInfos()[0].findElementByName("HOST").getElementValue());
        assertEquals("1521", e1.getAddressInfos()[0].findElementByName("PORT").getElementValue());

        assertEquals("tcp", e1.getAddressInfos()[1].findElementByName("PRoToCOL").getElementValue());
        assertEquals("sales2-svr", e1.getAddressInfos()[1].findElementByName("HOST").getElementValue());
        assertEquals("1521", e1.getAddressInfos()[1].findElementByName("PORT").getElementValue());

        NetServiceDesc e2 = (NetServiceDesc) nodes[1].getPsi();
        assertEquals("net_service_name2", e2.getNetworkAlias());
        assertEquals(2, e2.getAddressInfos().length);
        assertEquals(3, e2.getAddressInfos()[0].getElements().length);
        assertEquals(3, e2.getAddressInfos()[1].getElements().length);


        NetServiceDesc e3 = (NetServiceDesc) nodes[2].getPsi();
        assertEquals("net_service_name3", e3.getNetworkAlias());
        assertEquals(2, e3.getAddressInfos().length);
        assertEquals(3, e3.getAddressInfos()[0].getElements().length);
        assertEquals(3, e3.getAddressInfos()[1].getElements().length);

        assertEquals("tcp", e3.getAddressInfos()[0].findElementByName("PRoToCOL").getElementValue());
        assertEquals("127.0.0.1", e3.getAddressInfos()[0].findElementByName("HOST").getElementValue());
        assertEquals("1521", e3.getAddressInfos()[0].findElementByName("PORT").getElementValue());


        NetServiceDesc e4 = (NetServiceDesc) nodes[3].getPsi();
        assertEquals("net_service_name4", e4.getNetworkAlias());
        assertEquals(2, e4.getAddressInfos().length);
        assertEquals(4, e4.getAddressInfos()[0].getElements().length);
        assertEquals(4, e4.getAddressInfos()[1].getElements().length);

        assertEquals("tcp", e4.getAddressInfos()[0].findElementByName("PRoToCOL").getElementValue());
        assertEquals("sales1-server", e4.getAddressInfos()[0].findElementByName("HOST").getElementValue());
        assertEquals("1521", e4.getAddressInfos()[0].findElementByName("PORT").getElementValue());
        assertEquals("11784", e4.getAddressInfos()[0].findElementByName("SEND_BUF_SIZE").getElementValue());


        NetServiceDesc e5 = (NetServiceDesc) nodes[4].getPsi();

        NetServiceDesc e6 = (NetServiceDesc) nodes[5].getPsi();
        assertEquals("net_service_name6", e6.getNetworkAlias());
        assertEquals(2, e6.getAddressInfos().length);
        assertEquals(3, e6.getAddressInfos()[0].getElements().length);
        assertEquals(3, e6.getAddressInfos()[1].getElements().length);

        assertEquals("tcp", e6.getAddressInfos()[0].findElementByName("PRoToCOL").getElementValue());
        assertEquals("hr1-server", e6.getAddressInfos()[0].findElementByName("HOST").getElementValue());
        assertEquals("1521", e6.getAddressInfos()[0].findElementByName("PORT").getElementValue());


//        NetServiceDesc e7 = (NetServiceDesc) nodes[6].getPsi();
//        NetServiceDesc e8 = (NetServiceDesc) nodes[7].getPsi();
//        NetServiceDesc e9 = (NetServiceDesc) nodes[8].getPsi();

    }

}
