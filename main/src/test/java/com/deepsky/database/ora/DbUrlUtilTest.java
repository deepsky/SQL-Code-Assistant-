package com.deepsky.database.ora;

import com.deepsky.lang.parser.tns.TNSTypesAdopted;
import com.deepsky.lang.tnsnames.psi.AddressElement;
import com.deepsky.lang.tnsnames.psi.NetServiceDesc;
import com.deepsky.lang.tnsnames.tree.TNSFileParser;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DbUrlUtilTest {

    File base;

    @Before
    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("tns").toURI());
    }

    @Test
    public void test_parseSID() {
        DbUrl url1 = new DbUrlSID("test", "pwd", "localhost", "1521", "ORA1");
        Assert.assertEquals(url1, DbUrlUtil.parse(url1.serialize()));

        url1 = new DbUrlSID("test", "pwd#", "localhost", "1521", "ORA1");
        Assert.assertEquals(url1, DbUrlUtil.parse(url1.serialize()));

        url1 = new DbUrlSID("test", "pwd#a", "localhost", "1521", "ORA1");
        Assert.assertEquals(url1, DbUrlUtil.parse(url1.serialize()));

        url1 = new DbUrlSID("test", "pwd##", "localhost", "1521", "ORA1");
        Assert.assertEquals(url1, DbUrlUtil.parse(url1.serialize()));
    }

    @Test
    public void test_parseSERVICE_NAME() {
        DbUrl url1 = new DbUrlServiceName("test", "test", "localhost", "1521", "ORA1");
        Assert.assertEquals(url1, DbUrlUtil.parse(url1.serialize()));

        url1 = new DbUrlServiceName("test", "te#st", "localhost", "1521", "ORA1");
        Assert.assertEquals(url1, DbUrlUtil.parse(url1.serialize()));

        url1 = new DbUrlServiceName("test", "te#s#t#", "localhost", "1521", "ORA1");
        Assert.assertEquals(url1, DbUrlUtil.parse(url1.serialize()));
    }

    @Test
    public void test_parseTNS() {
        URL url = this.getClass().getClassLoader().getResource("tns/tns_factory_test.ora");
        DbUrl dbUrl = new TNSUrl("test", "test", "berlin", url.getPath());
        Assert.assertEquals(dbUrl, DbUrlUtil.parse(dbUrl.serialize()));
    }


    @Test
    public void test_parseSID_ssh() {
        JdbcDbUrl url1 = new DbUrlSID("test", "test", "localhost", "1521", "ORA1");
        url1.setSshSettings(new DbUrl.SshSettings() {
            public String getHost() {
                return "n21.sales.force";
            }

            public String getUserName() {
                return "kevin";
            }
            @Override
            public String getPrivateKeyFile() {
                // TODO - implement me
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        assertEquals(url1, DbUrlUtil.parse(url1.serialize()));
    }

    @Test
    public void test_parseSID_ssh_pk() {
        JdbcDbUrl url1 = new DbUrlSID("test", "test", "localhost", "1521", "ORA1");
        url1.setSshSettings(new DbUrl.SshSettings() {
            public String getHost() {
                return "n21.sales.force";
            }

            public String getUserName() {
                return "kevin";
            }
            @Override
            public String getPrivateKeyFile() {
                return "~/.ssh/id_rsa";
            }
        });

        assertEquals(url1, DbUrlUtil.parse(url1.serialize()));
    }

    @Test
    public void test_parseSERVICE_NAME_ssh() {
        JdbcDbUrl url1 = new DbUrlServiceName("test", "test", "localhost", "1521", "ORA1");
        url1.setSshSettings(new DbUrl.SshSettings() {
            public String getHost() {
                return "n21.sales.force";
            }

            public String getUserName() {
                return "kevin";
            }
            @Override
            public String getPrivateKeyFile() {
                return null;
            }
        });

        assertEquals(url1, DbUrlUtil.parse(url1.serialize()));
    }

    @Test
    public void test_parseSERVICE_NAME_ssh_pk() {
        JdbcDbUrl url1 = new DbUrlServiceName("test", "test", "localhost", "1521", "ORA1");
        url1.setSshSettings(new DbUrl.SshSettings() {
            public String getHost() {
                return "n21.sales.force";
            }

            public String getUserName() {
                return "kevin";
            }
            @Override
            public String getPrivateKeyFile() {
                return "~/.ssh/id_rsa";
            }
        });

        assertEquals(url1, DbUrlUtil.parse(url1.serialize()));
    }

    @Test
    public void test_parseTNS_ssh() {
        new TNSUrl("test", "test", "win7", "/home/sky/abc.ora");

        URL url = this.getClass().getClassLoader().getResource("tns/tns_factory_test.ora");
        TNSUrl dbUrl = new TNSUrl("test", "test", "berlin", url.getPath());
        dbUrl.setSshSettings(new DbUrl.SshSettings() {
            public String getHost() {
                return "n21.sales.force";
            }

            public String getUserName() {
                return "kevin";
            }

            @Override
            public String getPrivateKeyFile() {
                return null;
            }
        });

        assertEquals(dbUrl, DbUrlUtil.parse(dbUrl.serialize()));
    }

    @Test
    public void test_parseTNS_name() {

        URL url = this.getClass().getClassLoader().getResource("tns/tns_factory_test.ora");
        TNSUrl dbUrl = new TNSUrl("test-2", "test$2", "berlin", url.getPath());

        assertEquals(dbUrl, DbUrlUtil.parse(dbUrl.serialize()));
    }

    @Test
    public void test_parseTNS_ssh_pk() {
        URL url = this.getClass().getClassLoader().getResource("tns/tns_factory_test.ora");
        TNSUrl dbUrl = new TNSUrl("test", "test", "berlin", url.getPath());
        dbUrl.setSshSettings(new DbUrl.SshSettings() {
            public String getHost() {
                return "n21.sales.force";
            }

            public String getUserName() {
                return "kevin";
            }

            @Override
            public String getPrivateKeyFile() {
                return "~/.ssh/id_rsa";
            }
        });

        assertEquals(dbUrl, DbUrlUtil.parse(dbUrl.serialize()));
    }

    @Test
    public void testSID_replacement(){

        DbUrl url1 = new DbUrlSID("test", "test", "localhost", "1521", "ORA1");
        Assert.assertEquals(url1, DbUrlUtil.parse(url1.serialize()));

        DbUrl url2 = DbUrlUtil.replaceHostPort(url1, "localhost:1521", "itx:8021");
        Assert.assertEquals(
                DbUrlUtil.parse(new DbUrlSID("test", "test", "itx", "8021", "ORA1").serialize()),
                url2);

        DbUrl url3 = DbUrlUtil.replaceHostPort(url1, "localhost", "itx:8021");
        Assert.assertEquals(
                DbUrlUtil.parse(new DbUrlSID("test", "test", "itx", "1521", "ORA1").serialize()),
                url3);

        DbUrl url4 = DbUrlUtil.replaceHostPort(url1, "", "itx:8021");
        Assert.assertEquals(
                DbUrlUtil.parse(new DbUrlSID("test", "test", "localhost", "1521", "ORA1").serialize()),
                url4);

        DbUrl url5 = DbUrlUtil.replaceHostPort(url1, ":1521", "itx:8021");
        Assert.assertEquals(
                DbUrlUtil.parse(new DbUrlSID("test", "test", "localhost", "8021", "ORA1").serialize()),
                url5);
    }


    @Test
    public void testNameService_replacement(){

        DbUrl url1 = new DbUrlServiceName("test", "test", "localhost", "1521", "ORA1");
        Assert.assertEquals(url1, DbUrlUtil.parse(url1.serialize()));

        DbUrl url2 = DbUrlUtil.replaceHostPort(url1, "localhost:1521", "itx:8021");
        Assert.assertEquals(
                DbUrlUtil.parse(new DbUrlServiceName("test", "test", "itx", "8021", "ORA1").serialize()),
                url2);

        DbUrl url3 = DbUrlUtil.replaceHostPort(url1, "localhost", "itx:8021");
        Assert.assertEquals(
                DbUrlUtil.parse(new DbUrlServiceName("test", "test", "itx", "1521", "ORA1").serialize()),
                url3);

        DbUrl url4 = DbUrlUtil.replaceHostPort(url1, "", "itx:8021");
        Assert.assertEquals(
                DbUrlUtil.parse(new DbUrlServiceName("test", "test", "localhost", "1521", "ORA1").serialize()),
                url4);

        DbUrl url5 = DbUrlUtil.replaceHostPort(url1, ":1521", "itx:8021");
        Assert.assertEquals(
                DbUrlUtil.parse(new DbUrlServiceName("test", "test", "localhost", "8021", "ORA1").serialize()),
                url5);
    }


    @Test
    public void test_jdbc_extractHostPort(){

        DbUrl url1 = new DbUrlServiceName("test", "test", "localhost", "1521", "ORA1");
        Assert.assertEquals(1, DbUrlUtil.extractHostPort(url1).length);
        Assert.assertEquals("localhost:1521", DbUrlUtil.extractHostPort(url1)[0]);

        url1 = new DbUrlSID("test", "test", "127.0.0.1", "1521", "ORA1");
        Assert.assertEquals(1, DbUrlUtil.extractHostPort(url1).length);
        Assert.assertEquals("127.0.0.1:1521", DbUrlUtil.extractHostPort(url1)[0]);
    }


    @Test
    public void test_tns_extractHostPort() throws IOException {

        File f = new File(base, "tns_extract_host_port.ora");
        String content = StringUtils.file2string(f);
        TNSFileParser parser = new TNSFileParser();
        ASTNode root = parser.parse(content);

        assertNotNull(root);
        ASTNode[] nodes = root.getChildren(TokenSet.create(TNSTypesAdopted.NET_SERVICE_DESC));
        assertEquals(9, nodes.length);

        TNSUrl url1 = new TNSUrl("test", "test", "net_service_name", (NetServiceDesc) nodes[0].getPsi(), f.getPath());
        String[] pairs = DbUrlUtil.extractHostPort(url1);
        assertEquals(2, pairs.length);
        assertEquals("sales1-svr:1521", pairs[0]);
        assertEquals("sales2-svr:1521", pairs[1]);

        url1 = new TNSUrl("test", "test", "net_service_name2", (NetServiceDesc) nodes[1].getPsi(), f.getPath());
        pairs = DbUrlUtil.extractHostPort(url1);
        assertEquals(2, pairs.length);
        assertEquals("sales1-svr:1521", pairs[0]);
        assertEquals("sales2-svr:1521", pairs[1]);

        url1 = new TNSUrl("test", "test", "net_service_name3", (NetServiceDesc) nodes[2].getPsi(), f.getPath());
        pairs = DbUrlUtil.extractHostPort(url1);
        assertEquals(2, pairs.length);
        assertEquals("127.0.0.1:1521", pairs[0]);
        assertEquals("sales2-svr:1521", pairs[1]);


        url1 = new TNSUrl("test", "test", "net_service_name4", (NetServiceDesc) nodes[3].getPsi(), f.getPath());
        pairs = DbUrlUtil.extractHostPort(url1);
        assertEquals(2, pairs.length);
        assertEquals("sales1-server:1521", pairs[0]);
        assertEquals("sales2-server:1521", pairs[1]);

        url1 = new TNSUrl("test", "test", "ORA", (NetServiceDesc) nodes[8].getPsi(), f.getPath());
        pairs = DbUrlUtil.extractHostPort(url1);
        assertEquals(1, pairs.length);
        assertEquals("bob-black.kaunas.acme.com:1521", pairs[0]);

    }

    @Test
    public void testNameService_tns_replacement() throws IOException {

        File f = new File(base, "tns_extract_host_port.ora");
        String content = StringUtils.file2string(f);
        TNSFileParser parser = new TNSFileParser();
        ASTNode root = parser.parse(content);

        assertNotNull(root);
        ASTNode[] nodes = root.getChildren(TokenSet.create(TNSTypesAdopted.NET_SERVICE_DESC));
        assertEquals(9, nodes.length);

        // Case 1
        TNSUrl url1 = new TNSUrl("test", "test", "net_service_name", (NetServiceDesc) nodes[0].getPsi(), f.getPath());
        String[] pairs = DbUrlUtil.extractHostPort(url1);
        assertEquals(2, pairs.length);
        assertEquals("sales1-svr:1521", pairs[0]);
        assertEquals("sales2-svr:1521", pairs[1]);
        // Replace
        DbUrl _url1 = DbUrlUtil.replaceHostPort(url1, "sales1-svr:1521", "localhost:8021");
        String[] _pairs = DbUrlUtil.extractHostPort(_url1);
        assertEquals(2, _pairs.length);
        assertEquals("localhost:8021", _pairs[0]);
        assertEquals("sales2-svr:1521", _pairs[1]);

        _url1 = DbUrlUtil.replaceHostPort(_url1, "sales2-svr:1521", "localhost:9021");
        _pairs = DbUrlUtil.extractHostPort(_url1);
        assertEquals(2, _pairs.length);
        assertEquals("localhost:8021", _pairs[0]);
        assertEquals("localhost:9021", _pairs[1]);


        // Case 2
        TNSUrl url4 = new TNSUrl("test", "test", "net_service_name4", (NetServiceDesc) nodes[3].getPsi(), f.getPath());
        String[] pairs4 = DbUrlUtil.extractHostPort(url4);
        assertEquals(2, pairs4.length);
        assertEquals("sales1-server:1521", pairs4[0]);
        assertEquals("sales2-server:1521", pairs4[1]);
        // Replace  - unsuccessful
        DbUrl _url4 = DbUrlUtil.replaceHostPort(url4, "sales1-svr:1521", "127.0.0.1:8021");
        String[] _pairs4 = DbUrlUtil.extractHostPort(_url4);
        assertEquals(2, _pairs4.length);
        assertEquals("sales1-server:1521", _pairs4[0]);
        assertEquals("sales2-server:1521", _pairs4[1]);

        // Replace - successful
        _url4 = DbUrlUtil.replaceHostPort(url4, "sales1-server:1521", "127.0.0.1:8021");
        _pairs4 = DbUrlUtil.extractHostPort(_url4);
        assertEquals(2, _pairs4.length);
        assertEquals("127.0.0.1:8021", _pairs4[0]);
        assertEquals("sales2-server:1521", _pairs4[1]);

        _url4 = DbUrlUtil.replaceHostPort(_url4, "sales2-server:1521", "localhost:9021");
        _pairs4 = DbUrlUtil.extractHostPort(_url4);
        assertEquals(2, _pairs4.length);
        assertEquals("127.0.0.1:8021", _pairs4[0]);
        assertEquals("localhost:9021", _pairs4[1]);

    }
}
