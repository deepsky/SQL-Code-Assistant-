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

package com.deepsky.utils.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

abstract public class PersistableList<E> extends ArrayList<E> {

    PersistenceManager manager;
    PersistableHelper phelper;

    public void setManager(PersistenceManager manager) {
        this.manager = manager;
    }

    public void init() {

        phelper = new PersistableHelper();
        manager.setPersistableObject(phelper);

        String[] r = manager.getFullState();
        deserializeState(r);

        String[] updates = manager.getListOfUpdates();
        for (String cmd : updates) {
            deserializeCommand(cmd);
        }
    }

    public E remove(int index) {
        E o = super.remove(index);
        if (o != null) {
            // convert command into string
            persistCommand(Command.REMOVE, index, null);
        }
        return o;
    }

    public boolean add(E o) {
        boolean res = super.add(o);
        if (res) {
            // convert command into string
            persistCommand(Command.ADD, -1, o);
        }
        return res;
    }

    public void add(int index, E o) {
        super.add(index, o);
        // convert command into string
        persistCommand(Command.ADD_AT_INDEX, index, o);
    }

    public E set(int index, E element) {
        E o = super.set(index, element);
//        if (o != null) {
            // convert command into string
            persistCommand(Command.SET, index, element);
//        }
        return o;
    }

    public void clear() {
        super.clear();
        persistCommand(Command.CLEAR, -1, null);
    }

    public boolean addAll(Collection<? extends E> c) {

        boolean e1 = false;
        for (E e : c) {
            boolean res = super.add(e);
            if (res) {
                // convert command into string
                persistCommand(Command.ADD, -1, e);
                e1 = true;
            }
        }
        return e1;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException(
                "public boolean addAll(int index, Collection<? extends E> c) is not supported");
    }

    private void persistCommand(Command command, int index, E arg) {
        String serialized = serializeCommand(command, index, arg);
        if(isBatch){
            batchList.add(serialized);
        } else {
            manager.saveUpdate(serialized);
        }
    }


    List<String> batchList = new ArrayList<String>();
    boolean isBatch = false;
    public void startBatch(){
        isBatch = true;
    }

    public void completeBatch(){
        if(isBatch){
            manager.saveUpdate(batchList);
            isBatch = false;
        }
    }
    
//    private void persistBatchADDCommand(E[] objs) {
//        List<E> out = new ArrayList<E>();
//        for(E e: objs){
//            String serialized = serializeCommand(Command.ADD, -1, e);
//        }
//        manager.saveUpdate(serialized);
//    }

    class PersistableHelper implements Persistable {

        public String[] getFullState() {
            return serializeState();
        }

        public void updateState(String command) {
            deserializeCommand(command);
        }
    }

    private String serializeCommand(Command cmd, int index, E arg) {

        StringBuilder bld = new StringBuilder();
        bld.append(cmd.toString()).append("|");
        if (index != -1) {
            bld.append(Integer.toString(index)).append("|");
        }
        if (arg != null) {
            bld.append(valueToString(arg)).append("|");
        }
        return bld.toString();
    }

    private void deserializeCommand(String serialized) {

        String[] parts = serialized.split("\\|+");
        Command cmd = Command.valueOf(parts[0]);

        switch (cmd) {
            case ADD:
                E o = stringToValue(parts[1]);
                super.add(o);
                break;
            case ADD_AT_INDEX:
                int index = Integer.parseInt(parts[1]);
                E o2 = stringToValue(parts[2]);
                super.add(index, o2);
                break;
            case REMOVE:
                int index3 = Integer.parseInt(parts[1]);
                super.remove(index3);
                break;
            case SET:
                int index2 = Integer.parseInt(parts[1]);
                E o4 = null;
                if(parts.length == 3){
                    o4 = stringToValue(parts[2]);
                }
                super.set(index2, o4);
                break;
            case CLEAR:
                super.clear();
                break;
        }
    }

    private String[] serializeState() {

        List<String> l = new ArrayList<String>();
        for (E o : this) {
            StringBuilder bld = new StringBuilder();
            bld.append(Command.ADD.toString()).append("|");
            bld.append(valueToString(o)).append("|");
            l.add(bld.toString());
        }
        return l.toArray(new String[0]);
    }

    private void deserializeState(String[] input) {

        super.clear();
        for (String serialized : input) {
            String[] parts = serialized.split("\\|+");
            Command cmd = Command.valueOf(parts[0]);

            switch (cmd) {
                case ADD:
                    E o = stringToValue(parts[1]);
                    super.add(o);
                    break;
                default:
                    throw new RuntimeException("");
            }
        }
    }

    protected abstract String valueToString(E value);

    protected abstract E stringToValue(String value);

    enum Command {
        ADD,
        ADD_AT_INDEX,
        REMOVE,
        CLEAR,
        SET
    }
}
