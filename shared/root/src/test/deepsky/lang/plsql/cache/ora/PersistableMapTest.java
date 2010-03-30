package test.deepsky.lang.plsql.cache.ora;

import junit.framework.TestCase;

import java.io.File;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

import com.deepsky.database.ora.DbObjectKey;
import com.deepsky.utils.persistence.PersistableMap;

public class PersistableMapTest extends TestCase {

    public void test1() throws ParseException {
        PersistableMap map = new PersistableMap(new File("."), "test1log.txt");

        map.clear();
        map.put(new DbObjectKey("PACKAGE", "testme"), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"));

        map = new PersistableMap(new File("."), "test1log.txt");

        Date d = map.get(new DbObjectKey("PACKAGE", "testme"));
        assertNotNull(d);
    }
    

    public void testBatch7() throws ParseException {
        PersistableMap map = new PersistableMap(new File("."), "test2log.txt");
        map.clear();
        map.startBatch();
        map.put(new DbObjectKey("PACKAGE", "testme"), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"));
        map.put(new DbObjectKey("PACKAGE", "testme"), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"));
        map.put(new DbObjectKey("PACKAGE", "testme"), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"));
        map.put(new DbObjectKey("PACKAGE", "testme"), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"));
        map.put(new DbObjectKey("PACKAGE", "testme"), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"));
        map.put(new DbObjectKey("PACKAGE", "testme"), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"));
        map.put(new DbObjectKey("PACKAGE", "testme"), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"));
        map.completeBatch();

        map = new PersistableMap(new File("."), "test2log.txt");
        assertEquals(7, map.size());

        Date d = map.get(new DbObjectKey("PACKAGE", "testme"));
        assertNotNull(d);
        assertEquals(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"), d);

        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme")));
        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme")));
        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme")));
        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme")));
        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme")));
        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme")));
    }

    public void testUpdate() throws ParseException {
        PersistableMap map = new PersistableMap(new File("."), "test2log.txt");

        map.clear();
        map.startBatch();
        for(int i= 0; i< 10; i++){
            map.put(new DbObjectKey("PACKAGE", "testme" + i), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"));
        }
        map.completeBatch();
        assertEquals(10, map.size());

        // make sure data exists
        for(int i= 0; i< 10; i++){
            Date d = map.get(new DbObjectKey("PACKAGE", "testme"+i));
            assertNotNull(d);
            assertEquals(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"), d);
        }

        map = null;
        
        // reinitialize the map, make sure data exists and update the data
        PersistableMap map2 = new PersistableMap(new File("."), "test2log.txt");
        assertEquals(10, map2.size());

        for(int i= 0; i< 10; i++){
            Date d = map2.get(new DbObjectKey("PACKAGE", "testme"+1));
            assertNotNull(d);
            assertEquals(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"), d);
        }

        map2.put(new DbObjectKey("PACKAGE", "testme"+1), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:01"));
        map2.put(new DbObjectKey("PACKAGE", "testme"+4), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:34"));
        map2.put(new DbObjectKey("PACKAGE", "testme"+9), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:55"));

        assertEquals(10, map2.size());
        
        Date d = map2.get(new DbObjectKey("PACKAGE", "testme"+1));
        assertNotNull(d);
        assertEquals(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:01"), d);

        d = map2.get(new DbObjectKey("PACKAGE", "testme"+4));
        assertNotNull(d);
        assertEquals(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:34"), d);

        d = map2.get(new DbObjectKey("PACKAGE", "testme"+9));
        assertNotNull(d);
        assertEquals(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:55"), d);

        map2 = null;

        // reinitialize the map one more time, make sure data exists and update the data
        map2 = new PersistableMap(new File("."), "test2log.txt");
        assertEquals(10, map2.size());

//        for(int i= 0; i< 100; i++){
//            Date d2 = map2.get(new DbObjectKey("test" + i, "PACKAGE", "testme"));
//            assertNotNull(d2);
//            assertEquals(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"), d2);
//        }

        d = map2.get(new DbObjectKey("PACKAGE", "testme"+1));
        assertNotNull(d);
        assertEquals(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:01"), d);

        d = map2.get(new DbObjectKey("PACKAGE", "testme"+4));
        assertNotNull(d);
        assertEquals(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:34"), d);

        d = map2.get(new DbObjectKey("PACKAGE", "testme"+9));
        assertNotNull(d);
        assertEquals(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:55"), d);
    }


    public void testDelete() throws ParseException {
        PersistableMap map = new PersistableMap(new File("."), "test3log.txt");

        map.clear();
        map.startBatch();
        for(int i= 0; i< 10; i++){
            map.put(new DbObjectKey("PACKAGE", "testme"+i), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"));
        }
        map.completeBatch();
        assertEquals(10, map.size());

        // make sure data exists
        for(int i= 0; i< 10; i++){
            Date d = map.get(new DbObjectKey("PACKAGE", "testme"+i));
            assertNotNull(d);
            assertEquals(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"), d);
        }

        map = new PersistableMap(new File("."), "test3log.txt");
        assertEquals(10, map.size());
        map.remove(new DbObjectKey("PACKAGE", "testme"+1));
        map.remove(new DbObjectKey("PACKAGE", "testme"+2));
        map.remove(new DbObjectKey("PACKAGE", "testme"+9));
        
        map = new PersistableMap(new File("."), "test3log.txt");
        assertEquals(7, map.size());
        assertNull(map.get(new DbObjectKey("PACKAGE", "testme"+1)));
        assertNull(map.get(new DbObjectKey("PACKAGE", "testme"+2)));
        assertNull(map.get(new DbObjectKey("PACKAGE", "testme"+9)));

        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme"+0)));
        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme"+3)));
        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme"+4)));
        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme"+5)));
        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme"+6)));
        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme"+7)));
        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme"+8)));
    }


    public void testMix() throws ParseException {
        PersistableMap map = new PersistableMap(new File("."), "test3log.txt");

        map.clear();
        map.startBatch();
        for(int i= 0; i< 10; i++){
            map.put(new DbObjectKey("PACKAGE", "testme"+i), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"));
        }
        map.completeBatch();
        assertEquals(10, map.size());

        // make sure data exists
        for(int i= 0; i< 10; i++){
            Date d = map.get(new DbObjectKey("PACKAGE", "testme"+i));
            assertNotNull(d);
            assertEquals(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"), d);
        }

        map = new PersistableMap(new File("."), "test3log.txt");
        assertEquals(10, map.size());
        map.remove(new DbObjectKey("PACKAGE", "testme"+1));
        map.remove(new DbObjectKey("PACKAGE", "testme"+2));
        map.remove(new DbObjectKey("PACKAGE", "testme"+9));

        // check delete
        map = new PersistableMap(new File("."), "test3log.txt");
        assertEquals(7, map.size());
        assertNull(map.get(new DbObjectKey("PACKAGE", "testme"+1)));
        assertNull(map.get(new DbObjectKey("PACKAGE", "testme"+2)));
        assertNull(map.get(new DbObjectKey("PACKAGE", "testme"+9)));

        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme"+0)));
        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme"+3)));
        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme"+4)));
        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme"+5)));
        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme"+6)));
        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme"+7)));
        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme"+8)));

        // add items
        map = new PersistableMap(new File("."), "test3log.txt");
        assertEquals(7, map.size());

        map.put(new DbObjectKey("PACKAGE", "testme"+10), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:01"));
        map.put(new DbObjectKey("PACKAGE", "testme"+40), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:34"));
        map.put(new DbObjectKey("PACKAGE", "testme"+90), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:55"));
        map.put(new DbObjectKey("PACKAGE", "testme"+91), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:55"));

        assertEquals(11, map.size());

        // check after add
        map = new PersistableMap(new File("."), "test3log.txt");
        assertEquals(11, map.size());

        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme"+10)));
        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme"+10)));
        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme"+40)));
        assertNotNull(map.get(new DbObjectKey("PACKAGE", "testme"+91)));
    }

/*
        map.startBatch();
        for(int i= 0; i< 100; i++){
            map.put(new DbObjectKey("test" + i, "PACKAGE", "testme"), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"));
        }
        map.completeBatch();

        map.startBatch();
        for(int i= 0; i< 100; i++){
            map.put(new DbObjectKey("test" + i, "PACKAGE", "testme"), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"));
        }
        map.completeBatch();

        map.startBatch();
        for(int i= 0; i< 100; i++){
            map.put(new DbObjectKey("test" + i, "PACKAGE", "testme"), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"));
        }
        map.completeBatch();

        map.startBatch();
        for(int i= 0; i< 150; i++){
            map.put(new DbObjectKey("test" + i, "PACKAGE", "testme"), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2009/12/03 12:34:15"));
        }
        map.completeBatch();


        map = new PersistableMap(new File("."), "test2log.txt");
        assertEquals(150, map.size());
        
        Date d = map.get(new DbObjectKey("test1", "PACKAGE", "testme"));

        assertNotNull(d);
*/        

}
