package com.deepsky.lang.plsql.resolver;

import com.deepsky.database.ora.DbUrl;
import com.deepsky.database.ora.DbUrlSID;
import com.deepsky.database.ora.DbUrlUtil;
import com.deepsky.lang.AbstractBaseTest;
import com.deepsky.lang.common.PlSqlFileType;
import com.deepsky.lang.common.PlSqlParserDefinition;
import com.deepsky.lang.plsql.indexMan.IndexManagerImpl;
import com.deepsky.lang.plsql.objTree.*;
import com.deepsky.lang.plsql.resolver.factory.SqlASTFactory;
import com.deepsky.lang.plsql.sqlIndex.impl.DbSchemaIndex;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.objTree.DbElementRoot;
import com.deepsky.view.schema_pane.tree.DbElementRootAbstract;
import com.intellij.lang.LanguageASTFactory;
import com.intellij.lang.LanguageParserDefinitions;
import com.intellij.openapi.project.Project;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SchemaTreeBuilderTest extends AbstractBaseTest {

    File base;

    IndexManagerImpl indexManager;
    DbUrl dbUrl;

    @Before
    public void setUp() throws URISyntaxException, IOException {
        base = new File(this.getClass().getClassLoader().getResource("schemaTreeBuilder").toURI());

        LanguageASTFactory.INSTANCE.addExplicitExtension(PlSqlFileType.FILE_TYPE.getLanguage(), new SqlASTFactory());
        LanguageParserDefinitions.INSTANCE.addExplicitExtension(PlSqlFileType.FILE_TYPE.getLanguage(), new PlSqlParserDefinition());

        indexManager = new IndexManagerImpl(){};

        dbUrl = DbUrlUtil.parse("jdbc:oracle:thin:xdvmed@localhost:1521:ora");
    }


    @Test
    public void test_populate_all_index() throws IOException {
        DbSchemaIndex cindex = new DbSchemaIndex(base, dbUrl.getDbUID()){};
        cindex.addIndex(dbUrl.getUser(), new File(base, "xdvmed.idx").getAbsolutePath());
        indexManager.addIndex(dbUrl, cindex);

        SchemaTreeBuilder bld = new SchemaTreeBuilderImpl(new DbTypeElementFactory() {
            public DbElementRoot create(final String type) {
                return new DbElementRootAbstract() {
                    public String getName() {
                        return type;
                    }

                    public Project getProject() {
                        return null;
                    }

                    public DbUrl getDbUrl() {
                        return null;
                    }
                };
            }
        });
        indexManager.populateSchemaTree(dbUrl, bld);

        DbElementRoot[] topElems = bld.getTreeBuilt();
        assertNotNull(topElems);

//        SchemaTreeElement[] topElems = root.getChildren();
        assertEquals(9, topElems.length);

        assertEquals(DbObject.TABLE, topElems[0].getName());
        assertEquals(DbObject.VIEW, topElems[1].getName());
        assertEquals(DbObject.PACKAGE, topElems[2].getName());
        assertEquals(DbObject.SEQUENCE, topElems[3].getName());
        assertEquals(DbObject.FUNCTION, topElems[4].getName());
        assertEquals(DbObject.PROCEDURE, topElems[5].getName());
        assertEquals(DbObject.TRIGGER, topElems[6].getName());
        assertEquals(DbObject.TYPE, topElems[7].getName());
        assertEquals(DbObject.SYNONYM, topElems[8].getName());
    }


    @Test
    public void test_table_with_implicit_trigger() throws IOException {
        DbSchemaIndex cindex = new DbSchemaIndex(base, dbUrl.getDbUID()){};
        cindex.addIndex(dbUrl.getUser(), new File(base, "xdvmed.idx").getAbsolutePath());
        indexManager.addIndex(dbUrl, cindex);

        SchemaTreeBuilder bld = new SchemaTreeBuilderImpl(
                new String[]{DbObject.TABLE, DbObject.VIEW},
                new DbTypeElementFactory() {
                    public DbElementRoot create(final String type) {
                        return new DbElementRootAbstract() {
                            public String getName() {
                                return type;
                            }

                            public Project getProject() {
                                return null;
                            }

                            public DbUrl getDbUrl() {
                                return null;
                            }
                        };
                    }
                });
        indexManager.populateSchemaTree(dbUrl, bld);

        DbElementRoot[] topElems = bld.getTreeBuilt();
        assertNotNull(topElems);

        assertEquals(3, topElems.length);

        assertEquals(DbObject.TABLE, topElems[0].getName());
        assertEquals(DbObject.VIEW, topElems[1].getName());
        assertEquals(DbObject.TRIGGER, topElems[2].getName());
    }


    @Test
    public void test_tab_case_sensitive() throws IOException {
        DbSchemaIndex cindex = new DbSchemaIndex(base, dbUrl.getDbUID()){};
        cindex.addIndex(dbUrl.getUser(), new File(base, "tab_case-sensitive.idx").getAbsolutePath());
        indexManager.addIndex(dbUrl, cindex);

        SchemaTreeBuilder bld = new SchemaTreeBuilderImpl(
                new DbTypeElementFactory() {
                    public DbElementRoot create(final String type) {
                        return new DbElementRootAbstract() {
                            public String getName() {
                                return type;
                            }

                            public Project getProject() {
                                return null;
                            }

                            public DbUrl getDbUrl() {
                                return null;
                            }
                        };
                    }
                });
        indexManager.populateSchemaTree(dbUrl, bld);

        DbElementRoot[] topElems = bld.getTreeBuilt();
        assertNotNull(topElems);

// todo       assertEquals(1, topElems.length);

        assertEquals(DbObject.TABLE, topElems[0].getName());
        assertEquals(3, topElems[0].getChildren().size());
        assertEquals("Xdv_Net_Grp_Path_ext_dt", topElems[0].getChildren().get(0).getName());
        assertEquals("xdv_prd_fmn2fmn_at", topElems[0].getChildren().get(1).getName());
        assertEquals("xdv_trn_app_content_type_dt", topElems[0].getChildren().get(2).getName());
    }

}
