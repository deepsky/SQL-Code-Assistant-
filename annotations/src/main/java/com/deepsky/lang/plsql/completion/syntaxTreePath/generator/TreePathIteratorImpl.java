/*
 * Copyright (c) 2009,2014 Serhiy Kulyk
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *      1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
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

package com.deepsky.lang.plsql.completion.syntaxTreePath.generator;


import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.TreePath;
import com.intellij.lang.ASTNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreePathIteratorImpl implements TreePathIterator {

    private TreePath path;
    private List<State> stateQueue = new ArrayList<State>();

    public TreePathIteratorImpl(TreePath path){
        this.path = path;
        this.stateQueue.add(new State(path.iterator()));
    }

    @Override
    public Object next() {
        return stateQueue.get(stateQueue.size()-1).next();
    }

    @Override
    public Object peek() {
        return stateQueue.get(stateQueue.size()-1).peek();
    }

    @Override
    public boolean hasNext() {
        return stateQueue.get(stateQueue.size()-1).hasNext();
    }

    @Override
    public void setState(int lexerState) throws InvalidLexerStateException {
        if(lexerState >= stateQueue.size()){
            throw new InvalidLexerStateException();
        }

        List<State> newArray = new ArrayList<State>();
        for(int i = 0; i <= lexerState; i++){
            newArray.add(stateQueue.get(i));
        }
        stateQueue = newArray;
    }

    @Override
    public int saveState() {
        int _state = stateQueue.size()-1;
        State state = stateQueue.get(stateQueue.size()-1);
        stateQueue.add(state.cloneState());
        return _state;
    }


    protected Object adoptCompletionMarker(Object intelliJRulezzzzz){
        return intelliJRulezzzzz;
    }

    private class State {
        Iterator<List<ASTNode>> iterator;
        List<ASTNode> current;
        int posInList;

        private State(Iterator<List<ASTNode>> iterator, List<ASTNode> current, int posInList){
            this.iterator = iterator;
            this.current = current;
            this.posInList = posInList;
        }

        public State(Iterator<List<ASTNode>> iterator){
            this.iterator = iterator;
            this.current = iterator.next();
            this.posInList = 0;
        }

        public Object next() {
            if(posInList == 0){
                posInList++;
                return new SlashNode();
            }

            if(posInList > current.size()){
                if(iterator.hasNext()){
                    current = iterator.next();
                    posInList = 0;
                    return next();
                }
            } else {
                int index = posInList-1;
                posInList++;
                Object o = current.get(index);
                if(!hasNext()){
                    // Override last element if needed
                    return adoptCompletionMarker(o);
                }
                return o;
            }

            return null;
        }

        public Object peek() {
            if(posInList == 0){
                return new SlashNode();
            }

            if(posInList > current.size()){
                if(iterator.hasNext()){
                    return new SlashNode();
                }
            } else {
                Object o = current.get(posInList-1);
                if(isLast(posInList+1)){
                    // Override last element if needed
                    return adoptCompletionMarker(o);
                }
                return o;
            }

            return null;
        }


        public boolean hasNext() {
            if(posInList == 0){
                return true;
            }

            if(posInList > current.size()){
                if(iterator.hasNext()){
                    return true;
                }
            } else {
                return true;
            }
            return false;
        }

        private boolean isLast(int pos) {
            if(pos == 0){
                return false;
            }

            if(pos > current.size()){
                if(iterator.hasNext()){
                    return false;
                }
            } else {
                return false;
            }
            return true;
        }

        public State cloneState(){
            Iterator<List<ASTNode>> iterator = path.iterator();
            while(iterator.hasNext()){
                List<ASTNode> current = iterator.next();
                if(State.this.current == current){
                    return new State(iterator, current, State.this.posInList);
                }
            }

            throw new RuntimeException();
        }
    }
}
