package com.deepsky.lang.plsql.completion.syntaxTreePath.logic;


import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.SlashNode;
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
