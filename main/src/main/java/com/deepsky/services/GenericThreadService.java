package com.deepsky.services;

import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import java.util.Date;


public class GenericThreadService implements Runnable {

    private final LoggerProxy log = LoggerProxy.getInstance(getClass().getName());

    private final Object synch = new Object();

    private int TIMEOUT = 5 * 1000; // milliseconds

    Thread workThread;
    boolean stop = false;
    boolean forceWakeup = false;
    int priority = 4;

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void start() {
        stop = false;
        workThread = new Thread(this);
        int prior = workThread.getPriority();
        workThread.setPriority(priority);
        //workThread.setDaemon(true);
        workThread.start();
    }


    public void stop() {
        stop = true;
        synchronized (synch) {
            synch.notify();
        }

        if (workThread != null && workThread.getState() == Thread.State.RUNNABLE) {
            try {
                workThread.join();
            } catch (InterruptedException e) {
            }
        }

    }

    public void tweak() {
        synchronized (synch) {
            synch.notify();
        }
    }

    @Override
    public void run() {
        while (!stop) {

            long startTime = System.currentTimeMillis();
            try {
                runInternal();

                try {
                    long finishedTime = System.currentTimeMillis();
                    long timeout = TIMEOUT - (finishedTime - startTime);
                    if (timeout <= 0) {
                        timeout = 20;
                    }
                    synchronized (synch) {
                        synch.wait(timeout);
                    }
                } catch (InterruptedException e) {
                    // todo
                }
            } catch (Throwable e) {
                // Something wrong was happened with runInternal,
                // run it one more after pause
                //readyForCheckup(10);
            }

            System.out.println(new Date() + "  In loop");
        }
        System.out.println("Thread down!");
    }


    protected void runInternal() {
        log.info("Idle run ... ");
    }


    boolean readyForCheckup(int timeout) {
        try {
            synch.wait(timeout);

        } catch (InterruptedException e) {
            // todo
        }
        return false;
    }


}
