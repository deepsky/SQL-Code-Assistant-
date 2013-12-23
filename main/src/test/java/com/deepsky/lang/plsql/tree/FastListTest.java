package com.deepsky.lang.plsql.tree;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Iterator;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FastListTest {

    @Test
    public void test_add_size(){
        FastList l = new FastList();
        assertEquals(0, l.size());

        l.addLast(new BaseMarker(0));
        assertEquals(1, l.size());

        l.addLast(new BaseMarker(1));
        assertEquals(2, l.size());

        l.addLast(new BaseMarker(2));
        assertEquals(3, l.size());
    }

    @Test
    public void test_add_delete(){
        FastList l = new FastList();
        assertEquals(0, l.size());

        l.addLast(new BaseMarker(0));
        assertEquals(1, l.size());

        l.addLast(new BaseMarker(1));
        assertEquals(2, l.size());

        l.addLast(new BaseMarker(2));
        assertEquals(3, l.size());

        l.removeLast();
        assertEquals(2, l.size());

        l.removeLast();
        assertEquals(1, l.size());

        l.removeLast();
        assertEquals(0, l.size());
    }


   @Test
    public void test_first_last(){
        FastList l = new FastList();
        assertEquals(0, l.size());
        assertNull(l.getFirst());
        assertNull(l.getLast());

        BaseMarker i0;
        BaseMarker i1;
        BaseMarker i2;

        l.addLast((i0=new BaseMarker(0)));
        assertEquals(1, l.size());
        assertEquals(i0, l.getFirst());
        assertEquals(i0, l.getLast());

        l.addLast((i1=new BaseMarker(1)));
        assertEquals(2, l.size());
        assertEquals(i0, l.getFirst());
        assertEquals(i1, l.getLast());

        l.addLast((i2=new BaseMarker(2)));
        assertEquals(3, l.size());
        assertEquals(i0, l.getFirst());
        assertEquals(i2, l.getLast());

        l.removeLast();
        assertEquals(2, l.size());
        assertEquals(i0, l.getFirst());
        assertEquals(i1, l.getLast());

        l.removeLast();
        assertEquals(1, l.size());
        assertEquals(i0, l.getFirst());
        assertEquals(i0, l.getLast());

        l.removeLast();
        assertEquals(0, l.size());
        assertNull(l.getFirst());
        assertNull(l.getLast());
    }


    @Test
    public void test_removeRange(){
        FastList l = new FastList();
        assertEquals(0, l.size());
        assertNull(l.getFirst());
        assertNull(l.getLast());

        BaseMarker i0;
        BaseMarker i1;
        BaseMarker i2;
        BaseMarker i3;
        BaseMarker i4;
        BaseMarker i5;

        l.addLast((i0=new BaseMarker(0)));
        assertEquals(1, l.size());
        assertEquals(i0, l.getFirst());
        assertEquals(i0, l.getLast());

        l.addLast((i1=new BaseMarker(1)));
        assertEquals(2, l.size());
        assertEquals(i0, l.getFirst());
        assertEquals(i1, l.getLast());

        l.addLast((i2=new BaseMarker(2)));
        assertEquals(3, l.size());
        assertEquals(i0, l.getFirst());
        assertEquals(i2, l.getLast());

        l.addLast((i3=new BaseMarker(3)));
        assertEquals(4, l.size());
        assertEquals(i0, l.getFirst());
        assertEquals(i3, l.getLast());

        l.addLast((i4=new BaseMarker(4)));
        assertEquals(5, l.size());
        assertEquals(i0, l.getFirst());
        assertEquals(i4, l.getLast());

        l.addLast((i5=new BaseMarker(4)));
        assertEquals(6, l.size());
        assertEquals(i0, l.getFirst());
        assertEquals(i5, l.getLast());

        l.removeRange(i5);
        assertEquals(5, l.size());
        assertEquals(i0, l.getFirst());
        assertEquals(i4, l.getLast());

        l.removeRange(i1);
        assertEquals(1, l.size());
        assertEquals(i0, l.getFirst());
        assertEquals(i0, l.getLast());

        l.removeRange(i0);
        assertEquals(0, l.size());
        assertNull(l.getFirst());
        assertNull(l.getLast());
    }


    @Test
    public void test_iterator(){
        FastList l = new FastList();
        assertEquals(0, l.size());
        assertNull(l.getFirst());
        assertNull(l.getLast());

        BaseMarker i0;
        BaseMarker i1;
        BaseMarker i2;
        BaseMarker i3;
        BaseMarker i4;
        BaseMarker i5;

        l.addLast((i0=new BaseMarker(0)));
        assertEquals(1, l.size());
        assertEquals(i0, l.getFirst());
        assertEquals(i0, l.getLast());

        l.addLast((i1=new BaseMarker(1)));
        assertEquals(2, l.size());
        assertEquals(i0, l.getFirst());
        assertEquals(i1, l.getLast());

        l.addLast((i2=new BaseMarker(2)));
        assertEquals(3, l.size());
        assertEquals(i0, l.getFirst());
        assertEquals(i2, l.getLast());

        l.addLast((i3=new BaseMarker(3)));
        assertEquals(4, l.size());
        assertEquals(i0, l.getFirst());
        assertEquals(i3, l.getLast());

        l.addLast((i4=new BaseMarker(4)));
        assertEquals(5, l.size());
        assertEquals(i0, l.getFirst());
        assertEquals(i4, l.getLast());

        l.addLast((i5=new BaseMarker(4)));
        assertEquals(6, l.size());
        assertEquals(i0, l.getFirst());
        assertEquals(i5, l.getLast());


        Iterator ite = l.iterator();
        assertTrue(ite.hasNext());
        Assert.assertEquals(i0, ite.next());

        assertTrue(ite.hasNext());
        Assert.assertEquals(i1, ite.next());

        assertTrue(ite.hasNext());
        Assert.assertEquals(i2, ite.next());

        assertTrue(ite.hasNext());
        Assert.assertEquals(i3, ite.next());

        assertTrue(ite.hasNext());
        Assert.assertEquals(i4, ite.next());

        assertTrue(ite.hasNext());
        Assert.assertEquals(i5, ite.next());

        assertFalse(ite.hasNext());

    }
}
