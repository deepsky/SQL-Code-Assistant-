package com.deepsky.lang.plsql.completion.syntaxTreePath.generated;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.*;
import com.intellij.lang.ASTNode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

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
    protected abstract String[] getMethodParamClasses(int index);
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
                public String[] getArgTypes() {
                    return new String[0];
                }
            };
        }

        @Override
        public void setMetaInfoRef(int ref) {
            this.metaInfoRef = ref;
        }

        @Override
        public Object[] getMethodHandlerParameters() {
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
