/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * SQL CODE ASSISTANT PLUG-IN FOR INTELLIJ IDEA IS PROVIDED BY SERHIY KULYK
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL SERHIY KULYK BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.deepsky.lang.integration;

import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.plsql.indexMan.IndexBulkChangeListener;
import com.deepsky.lang.plsql.sqlIndex.WordIndexChangeListener;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBus;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CodeChangeEventAggregator {

    private Project project;
    private MessageBus bus1;

    private Thread dispatcher = new Thread(new UpdateEventProcessor());
    private final Object synch = new Object();
    private boolean stop = false;
    private long timeout = 3 * 1000;

    private final Map<DbUrl, Set<String>> dbUrl2types = new HashMap<DbUrl, Set<String>>();
    private final Map<DbUrl, Set<String>> dbUrl2files = new HashMap<DbUrl, Set<String>>();

    public CodeChangeEventAggregator(Project project){
        this.project = project;
    }

    public static CodeChangeEventAggregator getInstance(Project project) {
      return ServiceManager.getService(project, CodeChangeEventAggregator.class);
    }

    public void start(){
        bus1 = project.getMessageBus();
        stop = false;
        dispatcher.setDaemon(true);
        dispatcher.setPriority(2);
        dispatcher.start();
    }

    public void stop(){
        stop = true;
        synchronized (synch) {
            synch.notify();
        }

        if (dispatcher != null) {
            try {
                dispatcher.join();
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void updateIndex(@NotNull DbUrl dbUrl, @NotNull Set<String> types){
        updateIndex(dbUrl, types.toArray(new String[types.size()]));
    }

    public void updateIndex(@NotNull DbUrl dbUrl, @NotNull String[] types){
        synchronized (dbUrl2types){
            Set<String> typeSet = dbUrl2types.get(dbUrl);
            if(typeSet == null){
                typeSet = new HashSet<String>();
                dbUrl2types.put(dbUrl, typeSet);
            }
            typeSet.addAll(Arrays.asList(types));
        }
    }

    public void updateWordIndex(DbUrl dbUrl, String filePath){
        synchronized (dbUrl2files){
            Set<String> typeSet = dbUrl2files.get(dbUrl);
            if(typeSet == null){
                typeSet = new HashSet<String>();
                dbUrl2files.put(dbUrl, typeSet);
            }
            typeSet.add(filePath);
        }
    }


    private class UpdateEventProcessor implements Runnable {
        public void run() {
            synchronized (synch) {
                while (!stop) {
                    try {
                        synchronized (dbUrl2types){
                            for(Map.Entry<DbUrl, Set<String>> e: dbUrl2types.entrySet()){
                                String[] types = e.getValue().toArray(new String[0]);
                                bus1.syncPublisher(IndexBulkChangeListener.TOPIC).handleUpdate(e.getKey(), types);
                            }
                            dbUrl2types.clear();
                        }

                        synchronized (dbUrl2files){
                            for(Map.Entry<DbUrl, Set<String>> e: dbUrl2files.entrySet()){
                                for(String filePath: e.getValue()){
                                    bus1.syncPublisher(WordIndexChangeListener.TOPIC).handleUpdate(e.getKey(), filePath);
                                }
                            }
                            dbUrl2files.clear();
                        }

                        synch.wait(timeout);
                    } catch (InterruptedException e) {
                        // todo
                    }
                }
            }
        }
    }

}
