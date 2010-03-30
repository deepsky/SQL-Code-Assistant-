package test.etutor.commons;

import com.deepsky.utils.persistence.PersistableList;
import com.deepsky.utils.persistence.PersistenceManager;

import java.util.ArrayList;
import java.io.File;

import junit.framework.TestCase;

public class PersistableListTest extends TestCase {

    public void setUp(){
        // todo
    }

    public void testAddAtIndex() throws Exception {
        PersistenceManager m = new PersistenceManager();
        m.setTargetDir(new File("./"));
        m.setLimit(5);
        m.init();
        m.cleanupState();

        PersistableList o = new PersistableStringList();
        o.setManager(m);
        o.init();

        o.add("abcd0");
        o.add("abcd1");
        o.add("abcd2");
        o.add("abcd3");

        assertEquals(4, o.size());

        // --------------
        m = new PersistenceManager();
        m.setTargetDir(new File("./"));
        m.setLimit(5);
        m.init();

        o = new PersistableStringList();
        o.setManager(m);
        o.init();
        assertEquals(4, o.size());
        assertEquals("abcd0", o.get(0));
        assertEquals("abcd1", o.get(1));
        assertEquals("abcd2", o.get(2));
        assertEquals("abcd3", o.get(3));

        o.add(1, "eeee0");
        o.add(3, "eeee1");
        assertEquals(6, o.size());

        // --------------
        m = new PersistenceManager();
        m.setTargetDir(new File("./"));
        m.setLimit(5);
        m.init();

        o = new PersistableStringList();
        o.setManager(m);
        o.init();
        assertEquals(6, o.size());
        assertEquals("abcd0", o.get(0));
        assertEquals("eeee0", o.get(1));
        assertEquals("abcd1", o.get(2));
        assertEquals("eeee1", o.get(3));
        assertEquals("abcd2", o.get(4));
        assertEquals("abcd3", o.get(5));
    }

    public void testAddAll() throws Exception {
        PersistenceManager m = new PersistenceManager();
        m.setTargetDir(new File("./"));
        m.setLimit(20);
        m.init();
        m.cleanupState();

        PersistableList o = new PersistableStringList();
        o.setManager(m);
        o.init();

        o.add("abcd0");
        o.add("abcd1");
        o.add("abcd2");
        o.add("abcd3");
        o.add("abcd4");
        o.add("abcd5");
        o.add("abcd6");
        o.add("abcd7");
        o.add("abcd8");
        o.add("abcd9");
        o.add("abcd10");
        o.add("abcd11");
        o.add("abcd12");
        o.add("abcd13");
        o.add("abcd14");
        o.add("abcd15");
        o.add("abcd16");

        assertEquals(17, o.size());

        // --------------
        m = new PersistenceManager();
        m.setTargetDir(new File("./"));
        m.setLimit(20);
        m.init();

        o = new PersistableStringList();
        o.setManager(m);
        o.init();
        assertEquals(17, o.size());

        ArrayList<String> c = new ArrayList<String>();
        c.add("eee1");
        c.add("eee2");
        c.add("eee3");
        c.add("eee4");
        c.add("eee5");
        o.addAll(c);
        assertEquals(22, o.size());

        // --------------
        m = new PersistenceManager();
        m.setTargetDir(new File("./"));
        m.setLimit(20);
        m.init();

        o = new PersistableStringList();
        o.setManager(m);
        o.init();
        assertEquals(o.size(), 22);
        
    }

    public void test2() throws Exception {
        PersistenceManager m = new PersistenceManager();
        m.setTargetDir(new File("./"));
        m.setLimit(5);
        m.init();
        m.cleanupState();

        PersistableList o = new PersistableStringList();
        o.setManager(m);
        o.init();

        o.add("abcd0");
        o.add("abcd1");
        o.add("abcd2");
        o.add("abcd3");
        o.add("abcd4");
        o.add("abcd5");
        o.add("abcd6");
        o.add("abcd7");

        assertEquals(8, o.size());

        // --------------
        m = new PersistenceManager();
        m.setTargetDir(new File("./"));
        m.setLimit(5);
        m.init();

        o = new PersistableStringList();
        o.setManager(m);
        o.init();
        assertEquals(8, o.size());
        assertEquals("abcd0", o.get(0));
        assertEquals("abcd1", o.get(1));
        assertEquals("abcd2", o.get(2));
        assertEquals("abcd3", o.get(3));
        assertEquals("abcd4", o.get(4));
        assertEquals("abcd5", o.get(5));
        assertEquals("abcd6", o.get(6));
        assertEquals("abcd7", o.get(7));
    }

    public void test0() throws Exception {

        PersistenceManager m = new PersistenceManager();
        m.setTargetDir(new File("./"));
        m.init();
        m.cleanupState();

        PersistableList o = new PersistableStringList();
        o.setManager(m);
        o.init();

        o.add("abcd0");
        o.add("abcd1");
        o.add("abcd2");
        o.add("abcd3");
        o.add("abcd4");
        o.add("abcd5");

        assertEquals(6, o.size());

        // --------------
        o = new PersistableStringList();
        o.setManager(m);
        o.init();
        assertEquals(6, o.size());

        o.remove(0);
        o.remove(o.size()-1);

        // --------------
        o = new PersistableStringList();
        o.setManager(m);
        o.init();
        assertEquals(4, o.size());
        assertEquals("abcd1", o.get(0));
        assertEquals("abcd2", o.get(1));
        assertEquals("abcd3", o.get(2));
        assertEquals("abcd4", o.get(3));
        
        o.clear();
        assertEquals(0, o.size());

        // --------------
        o = new PersistableStringList();
        o.setManager(m);
        o.init();
        assertEquals(0, o.size());
    }


    public void test1() throws Exception {
        PersistenceManager m = new PersistenceManager();
        m.setTargetDir(new File("./"));
        m.setLimit(5);
        m.init();
        m.cleanupState();

        PersistableList o = new PersistableStringList();
        o.setManager(m);
        o.init();

        o.add("abcd0");
        o.add("abcd1");
        o.add("abcd2");
        o.add("abcd3");
        o.add("abcd4");
        o.add("abcd5");
        o.add("abcd6");
        o.add("abcd7");

        assertEquals(8, o.size());

        // --------------
        o = new PersistableStringList();
        o.setManager(m);
        o.init();
        assertEquals(8, o.size());
        assertEquals("abcd0", o.get(0));
        assertEquals("abcd1", o.get(1));
        assertEquals("abcd2", o.get(2));
        assertEquals("abcd3", o.get(3));
        assertEquals("abcd4", o.get(4));
        assertEquals("abcd5", o.get(5));
        assertEquals("abcd6", o.get(6));
        assertEquals("abcd7", o.get(7));
    }


    public void testBatchAddAtIndex() throws Exception {
        PersistenceManager m = new PersistenceManager();
        m.setTargetDir(new File("./"));
        m.setLimit(5);
        m.init();
        m.cleanupState();

        PersistableList o = new PersistableStringList();
        o.setManager(m);
        o.init();

        o.startBatch();
        o.add("abcd0");
        o.add("abcd1");
        o.add("abcd2");
        o.add("abcd3");
        o.completeBatch();

        assertEquals(4, o.size());

        // --------------
        m = new PersistenceManager();
        m.setTargetDir(new File("./"));
        m.setLimit(5);
        m.init();

        o = new PersistableStringList();
        o.setManager(m);
        o.init();
        assertEquals(4, o.size());
        assertEquals("abcd0", o.get(0));
        assertEquals("abcd1", o.get(1));
        assertEquals("abcd2", o.get(2));
        assertEquals("abcd3", o.get(3));

        o.add(1, "eeee0");
        o.add(3, "eeee1");
        assertEquals(6, o.size());

        // --------------
        m = new PersistenceManager();
        m.setTargetDir(new File("./"));
        m.setLimit(5);
        m.init();

        o = new PersistableStringList();
        o.setManager(m);
        o.init();
        assertEquals(6, o.size());
        assertEquals("abcd0", o.get(0));
        assertEquals("eeee0", o.get(1));
        assertEquals("abcd1", o.get(2));
        assertEquals("eeee1", o.get(3));
        assertEquals("abcd2", o.get(4));
        assertEquals("abcd3", o.get(5));
    }


    class PersistableStringList extends PersistableList<String> {
        protected String valueToString(String value) {
            return value;
        }
        protected String stringToValue(String value) {
            return value;
        }
    }
}
