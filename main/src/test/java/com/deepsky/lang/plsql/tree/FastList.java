package com.deepsky.lang.plsql.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class FastList {

    private int size = 0;
    private BaseMarker last;
    private BaseMarker first;

    public int size(){
        return size;
    }

    public BaseMarker getFirst(){
        return first;
    }

    public BaseMarker getLast(){
        return last;
    }

    public Iterator iterator(){

        return new Iterator(){
            BaseMarker cur = first;
            @Override
            public boolean hasNext() {
                return cur != null;
            }

            @Override
            public Object next() {
                final BaseMarker out = cur;
                cur = cur != null? cur.next: null;
                return out;
            }

            @Override
            public void remove() {
            }
        };
    }


    /**
     * Links e as first element.
     */
    private void linkFirst(BaseMarker e) {
        final BaseMarker f = first;
        first = e;
        if (f == null)
            last = e;
        else
            f.prev = e;
        size++;
    }


    /**
     * Links e as last element.
     */
    public void addLast(BaseMarker e) {
        final BaseMarker l = last;
        last = e;
        if (l == null)
            first = e;
        else {
            l.next = e;
            e.prev = l;
        }
        size++;
    }

    public void add(BaseMarker e) {
        final BaseMarker l = last;
        last = e;
        if (l == null)
            first = e;
        else {
            l.next = e;
            e.prev = l;
        }
        size++;
    }

    public BaseMarker removeLast() {
        final BaseMarker l = last;
        if (l == null)
            throw new NoSuchElementException();
        unlinkLast(l);
        return l;
    }

    /**
     * Unlinks non-null last node l.
     */
    private void unlinkLast(BaseMarker l) {
        // assert l == last && l != null;
        final BaseMarker prev = l.prev;
        l.prev = null; // help GC
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
    }


    /**
     * Unlinks non-null node x.
     */
    void unlink(BaseMarker x) {
        // assert x != null;
        final BaseMarker next = x.next;
        final BaseMarker prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        size--;
    }

    /**
     * Remove all markers from the list starting from 'start'
     * @param start
     */
    public void removeRange(BaseMarker start){
        final BaseMarker prev = start.prev;

        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
            start.prev = null;
        }

        BaseMarker next = start;
        do {
            size--;
            next = next.next;
        } while(next != null);
    }

    public BaseMarker get(int index) {
        if (!(index >= 0 && index < size))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));

        if (index < (size >> 1)) {
            BaseMarker x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            BaseMarker x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    /**
     * Constructs an IndexOutOfBoundsException detail message.
     * Of the many possible refactorings of the error handling code,
     * this "outlining" performs best with both server and client VMs.
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    public void rollbackTo(BaseMarker marker) {
        removeRange(marker);
    }

    public void remove(BaseMarker marker) {
        unlink(marker);
    }


//    public static class Node {
//        private Node next;
//        private Node prev;
//
//        Node(Node prev, Node next) {
//            this.next = next;
//            this.prev = prev;
//        }
//    }

}
