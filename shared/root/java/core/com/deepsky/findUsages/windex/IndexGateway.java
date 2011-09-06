package com.deepsky.findUsages.windex;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;

public class IndexGateway {

    protected final String WORD_INDEX_EXT = "widx";
    protected final ReentrantLock lock = new ReentrantLockEx();
    File indexDir;

    public IndexGateway(File indexDir) {
        this.indexDir = indexDir;
    }

    public Iterator<File> getIndexIterator() {
        lock.lock();

        File[] w_indexes = indexDir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String ext = getFileExt(name);
                return ext != null && ext.equals(WORD_INDEX_EXT);
            }
        });

        if (w_indexes.length == 0) {
            lock.unlock();
        }
        return new IteratorImpl(w_indexes);
    }

    public Thread[] getQueuedThreads(){
        return ((ReentrantLockEx)lock).__getQueuedThreads().toArray(new Thread[0]);
    }

    private String getFileExt(String fileName) {
        int idx = fileName.lastIndexOf('.');
        if (idx == -1 || idx + 1 == fileName.length()) {
            return null;
        }

        return fileName.substring(idx + 1);
    }

    private class IteratorImpl implements Iterator<File> {

        Iterator<File> iterator;

        public IteratorImpl(File[] fileList) {
            this.iterator = Arrays.asList(fileList).iterator();
        }

        public boolean hasNext() {
            boolean ret = iterator.hasNext();
            if (!ret && lock.isLocked()) {
                lock.unlock();
            }
            return ret;
        }

        public File next() {
            try {
                return iterator.next();
            } catch (NoSuchElementException e) {
                if(lock.isLocked()){
                    lock.unlock();
                }
                throw e;
            }
        }

        public void remove() {
            lock.unlock();
            throw new UnsupportedOperationException();
        }
    }

    private class ReentrantLockEx extends ReentrantLock {
         Collection<Thread> __getQueuedThreads(){
            return getQueuedThreads();
         }
    }
}
