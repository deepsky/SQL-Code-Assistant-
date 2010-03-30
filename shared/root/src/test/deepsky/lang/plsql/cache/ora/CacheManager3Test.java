package test.deepsky.lang.plsql.cache.ora;

import com.deepsky.database.CacheManager;
import junit.framework.TestCase;

import java.util.Properties;

import com.deepsky.database.ora.*;
import com.deepsky.database.DBException;
import com.deepsky.database.cache.Cache;
import com.deepsky.database.CacheManagerListener;
import com.deepsky.lang.plsql.struct.TableDescriptor;
import com.deepsky.lang.plsql.struct.DbObject;

public class CacheManager3Test extends TestCase {

    Properties props;
    DbUrl dbUrl;
    ConnectionHolder conn;

    public void setUp() throws DBException {

        System.setProperty("log.type", "log4j");
        System.setProperty("oracle.lib", "../shared/lib/ojdbc14.jar");
        System.setProperty("oracle.jdbc.driver", "oracle.jdbc.driver.OracleDriver");

//        dbUrl = new DbUrl("xdvmed", "xdvmed", "jdbc:oracle:thin:@pollux.lab254.telcordia.com:1521:xdvmed");
//        dbUrl = new DbUrl("pln", "pln", "jdbc:oracle:thin:@192.168.132.1:1521:ora");
        dbUrl = new DbUrl("pln", "pln", "jdbc:oracle:thin:@192.168.132.1:1521:ora9");

        conn = new ConnectionHolder(dbUrl);
        conn.getConnection();
    }

    public void test0() throws DBException {

        CacheManager cacheManager = new CacheManager3();

//        CacheManager3 cacheManager = CacheManager3.getInstance();
//        cacheManager.init(new File("./"));

        final boolean[] updated = new boolean[]{false};
        cacheManager.addListener(new CacheManagerListener(){
            public void handleUpdate(int state) {
                updated[0] = CacheManagerListener.CACHE_UPDATED == state;
            }
        });

        // add specific type processors
//        cacheManager.addHandler(new TableHandler());
//        cacheManager.addHandler(new SequenceHandler());
//        cacheManager.addHandler(new TypeHandler());
//        cacheManager.addHandler(new PackageSpecHandler());

        // start cache manager
        cacheManager.start(conn);

        Cache cache = cacheManager.getCaches().get(dbUrl.getUser().toUpperCase());

        try {
            while(!updated[0]){
                Thread.sleep(100);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        assertEquals(817, cache.getTableNameList().length);

        assertNotNull(cache.get("xdv_trn_app_content_type_dt", DbObject.TABLE));

        TableDescriptor tdesc = (TableDescriptor) cache.get("xdv_trn_app_content_type_dt", DbObject.TABLE);
        assertTrue("xdv_trn_app_content_type_dt".equalsIgnoreCase(tdesc.getName()));


        try {
            Thread.sleep(100);
            cacheManager.preloadTable(null, "xdv_trn_status_dt");

            Thread.sleep(10);
            cacheManager.preloadTable(null, "xdv_trn_causes_dt");

            Thread.sleep(10);
            cacheManager.preloadTable(null, "xdv_trn_app_type_dt");

            Thread.sleep(100000);
            cacheManager.preloadTable(null, "xdv_adp_import_status_t");

            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
