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

package com.deepsky.lang.plsql.completion.syntaxTreePath.generated;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generator.*;
import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.*;
import com.intellij.lang.ASTNode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

// TODO should be removed out of annotationProcessor
public abstract class CompletionProcessorBase {

    private TreePath treePath;
    private TreePathIterator pathIterator;

    public CompletionProcessorBase(TreePath treePath){
        this.treePath = treePath;
        this.pathIterator = new TreePathIteratorImpl(treePath){
            protected Object adoptCompletionMarker(Object o){
                // TODO check text of the node against IntellijJRulezz
                return Proxy.newProxyInstance(
                        ASTNode.class.getClassLoader(),
                        new Class[]{ASTNode.class},
                        new ASTNodeProxy((ASTNode) o));
            }
        };
    }


    private class ASTNodeProxy implements InvocationHandler {

        ASTNode target;
        public ASTNodeProxy(ASTNode target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method.getName().equals("getElementType") && (args == null || args.length == 0)){
                return PlSqlTokenTypes.C_MARKER;
            }
            return method.invoke(target, args);
        }
    }

    protected Object next() throws EOFException{
        Object o = pathIterator.next();
        if(o == null){
            throw new EOFException();
        }
        return o;
    }

    protected Object peek() throws EOFException{
        Object o = pathIterator.peek();
        if(o == null){
            throw new EOFException();
        }
        return o;
    }

    protected void setState(int lexerState) throws InvalidLexerStateException {
        pathIterator.setState(lexerState);
    }

    protected int saveState(){
        return pathIterator.saveState();
    }

    protected <T> T next(Class<T> e) throws EOFException, ClassCastException {
        Object o = pathIterator.next();
        if(o == null){
            throw new EOFException();
        }

        if(e.isInstance(o)){
            return (T)o;
        }

        throw new ClassCastException("Target: " + e + " Object from lexer: " + o);
    }

    protected <T> T peek(Class<T> e) throws EOFException, ClassCastException {
        Object o = pathIterator.peek();
        if(o == null){
            throw new EOFException();
        }

        if(e.isInstance(o)){
            return (T)o;
        }

        throw new ClassCastException("Target: " + e + " Object from lexer: " + o);
    }

    protected <T> void consume(Class<T> e) throws EOFException {
        Object o = pathIterator.next();
        if(o == null){
            throw new EOFException();
        }
    }

    protected abstract String getTreePath(int index);
    protected abstract String[] getClassPlusMethod(int index);
    protected abstract Class[] getMethodParamClasses(int index);
    protected abstract int[] getMethodParamIndexes(int index);

    // -----------------------------

    protected class TreePathContextImpl implements TreePathContext {

        private MyArray<MarkerImpl> markerList = new MyArray<MarkerImpl>();
        private int metaInfoRef;

        @Override
        public String getTreePath() {
            return CompletionProcessorBase.this.getTreePath(metaInfoRef);
        }

        @Override
        public Marker createMarker(String token) {
            MarkerImpl m = new MarkerImpl(token, markerList.size());
            markerList.add(m);
            return m;
        }

        @Override
        public CallMetaInfo getMeta() {
            return new CallMetaInfo(){

                @Override
                public String getClassName() {
                    return CompletionProcessorBase.this.getClassPlusMethod(metaInfoRef)[0];
                }

                @Override
                public String getMethodName() {
                    return CompletionProcessorBase.this.getClassPlusMethod(metaInfoRef)[1];
                }

                @Override
                public Class[] getArgTypes() {
                    return CompletionProcessorBase.this.getMethodParamClasses(metaInfoRef);
                }
            };
        }

        @Override
        public void setMetaInfoRef(int ref) {
            this.metaInfoRef = ref;
        }

        @Override
        public Object[] getHandlerParameters() {
            int[] indices = CompletionProcessorBase.this.getMethodParamIndexes(metaInfoRef);
            if(indices.length == 0)
                return new Object[0];
            else {
                Object[] out = new Object[indices.length];
                int indexPos = 0;
                for(int i = 0; i<markerList.size() && indexPos < indices.length; i++){
                    if(i == indices[indexPos]){
                        MarkerImpl m = markerList.get(i);
                        out[indexPos] = m.isPsi? m.node.getPsi(): m.node;
                        indexPos++;
                    }
                }

                return out;
            }
        }


        private class MarkerImpl implements Marker {

            private String element;
            private int index;
            private ASTNode node;
            private boolean isPsi;

            public MarkerImpl(String element, int index){
                this.element = element;
                this.index = index;
            }

            @Override
            public void rollbackTo() {
                if(markerList.size() > index){
                    markerList.removeAfter(index+1);
                }
            }

            @Override
            public void discard() {
                markerList.removeAfter(index);
            }

            @Override
            public void setASTNode(ASTNode node, boolean isPsi) {
                this.node = node;
                this.isPsi = isPsi;
            }
        }

        private  class MyArray<T> extends ArrayList<T> {
            public void removeAfter(int index){
                removeRange(index, size());
            }
        }
    }
}
