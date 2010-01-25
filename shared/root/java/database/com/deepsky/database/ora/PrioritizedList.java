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
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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

package com.deepsky.database.ora;

import java.util.*;

public class PrioritizedList {

    ItemToUpdate[] queue;

    List<ItemToUpdate> forced; // = new LinkedList<ItemToUpdate>();
    int next = 0;

    public PrioritizedList(final List<ItemToUpdate> items, List<ItemToUpdate> forced) {

        List<ItemToUpdate> _queue = new ArrayList<ItemToUpdate>();
        for(ItemToUpdate item: items){
            if(!item.upToDate){
                _queue.add(item);
            }
        }
        queue = _queue.toArray(new ItemToUpdate[_queue.size()]);
        this.forced = forced;

        // todo - any sort?
    }

    
    public void force(ItemToUpdate i) {
        forced.add(i);
    }

//    public ItemToUpdate pop() {
//        if(forced.size() > 0){
//            return forced.remove(0);
//        } else if(next < queue.length){
//            return queue[next++];
//        } else {
//            return null;
//        }
//    }

    final int CHUNK_SIZE = 10;

    public Mix pop() {

        Set<String> owners = new HashSet<String>();
        Set<String> names = new HashSet<String>();
        String type = null;

        while(forced.size() > 0 ){
            if(type != null){
                // if type of current item is not equal type of the last item in the list,
                // stop and return collected items
                if(!forced.get(0).type.equals(type)){
                    return new Mix(type, owners.toArray(new String[0]), names.toArray(new String[0]));
                } else if(names.size() >= CHUNK_SIZE){
                    return new Mix(type, owners.toArray(new String[0]), names.toArray(new String[0]));
                }
            } else {
                type = forced.get(0).type;
            }
            ItemToUpdate item = forced.remove(0);
            owners.add(item.owner);
            names.add(item.name);
        }

        while(next < queue.length){
            if(type != null){
                // if type of current item is not equal type of the last item in the list,
                // stop and return collected items
                if(!queue[next].type.equals(type)){
                    return new Mix(type, owners.toArray(new String[0]), names.toArray(new String[0]));
                } else if(names.size() >= CHUNK_SIZE){
                    return new Mix(type, owners.toArray(new String[0]), names.toArray(new String[0]));
                }
            } else {
                type = queue[next].type;
            }
            ItemToUpdate item = queue[next++];
            owners.add(item.owner);
            names.add(item.name);
        }

        return new Mix(type, owners.toArray(new String[0]), names.toArray(new String[0]));
    }


    public Mix pop2() {

        Set<String> owners = new HashSet<String>();
        Set<String> names = new HashSet<String>();
        String type = null;

        ItemToUpdate i;
        while((i=peekForcedItem()) != null){
            if(type != null){
                // if type of current item is not equal type of the last item in the list,
                // stop and return collected items
                if(!i.type.equals(type)){
                    return new Mix(type, owners.toArray(new String[0]), names.toArray(new String[0]));
                } else if(names.size() >= CHUNK_SIZE){
                    return new Mix(type, owners.toArray(new String[0]), names.toArray(new String[0]));
                }
            } else {
                type = i.type;
            }
            ItemToUpdate item = popForcedItem();
            owners.add(item.owner);
            names.add(item.name);
        }

        while((i=peekStandardItem()) != null){
            if(type != null){
                // if type of current item is not equal type of the last item in the list,
                // stop and return collected items
                if(!i.type.equals(type)){
                    return new Mix(type, owners.toArray(new String[0]), names.toArray(new String[0]));
                } else if(names.size() >= CHUNK_SIZE){
                    return new Mix(type, owners.toArray(new String[0]), names.toArray(new String[0]));
                }
            } else {
                type = i.type;
            }
            ItemToUpdate item = popStandardItem();
            owners.add(item.owner);
            names.add(item.name);
        }

        return new Mix(type, owners.toArray(new String[0]), names.toArray(new String[0]));
    }

    Set<String> enqueued = new HashSet<String>();

    ItemToUpdate peekForcedItem(){

        while(forced.size() > 0 ){
            ItemToUpdate i = forced.get(0);
            if(enqueued.contains(item2id(i))){ //i.owner + ":" + i.name)){
                forced.remove(0);
            } else {
                return i;
            }
        }

        return null;
    }

    ItemToUpdate popForcedItem(){
        ItemToUpdate i = forced.remove(0);
        String name = item2id(i); //i.owner + ":" + i.type + ":" + i.name;
        enqueued.add(name);
        return i;
    }

    private String item2id(ItemToUpdate i){
        return i.owner + ":" + i.type + ":" + i.name;
    }

    ItemToUpdate peekStandardItem(){
        while(next < queue.length){
            ItemToUpdate i = queue[next];
            if(enqueued.contains(item2id(i))){ //i.owner + ":" + i.name)){
                next++;
            } else {
                return i;
            }
        }

        return null;
    }

    ItemToUpdate popStandardItem(){
        while(next < queue.length){
            ItemToUpdate i = queue[next];
            String name = item2id(i); //i.owner + ":" + i.name;
            if(enqueued.contains(name)){
                next++;
            } else {
                enqueued.add(name);
                return i;
            }
        }

        return null;
    }

    public int size() {
        return forced.size() + queue.length-next;
    }
}
