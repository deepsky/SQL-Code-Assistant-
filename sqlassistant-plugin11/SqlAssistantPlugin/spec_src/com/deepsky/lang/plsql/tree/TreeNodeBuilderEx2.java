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

package com.deepsky.lang.plsql.tree;

import com.deepsky.lang.common.PlSqlParserDefinition;
import com.deepsky.lang.common.ResolveProvider;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.ResolveFacade;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.intellij.lang.*;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.tree.FileElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TreeNodeBuilderEx2 implements PsiBuilder {

    int sequencer = 0;

    Lexer lexer;
    TightList prod = new TightList();

    String fsPath = null;
    VirtualFile virtualFile = null;

    public void handle_ws(int i) {
    }


    public TreeNodeBuilderEx2(VirtualFile virtualFile, String input) {
        this(virtualFile.getPath(), input);
        this.virtualFile = virtualFile;
    }

    public TreeNodeBuilderEx2(String fsPath, String input) {
        ParserDefinition parserDefinition = new PlSqlParserDefinition();
        lexer = lexer != null ? lexer : parserDefinition.createLexer(null);
        myWhitespaces = parserDefinition.getWhitespaceTokens();
        myComments = parserDefinition.getCommentTokens();

        this.fsPath = fsPath == null ? "" : fsPath.replace(' ', '?').replace('\\', '|').replace('/', '|');

        lexer.start(input);
        myText = input;
        cacheLexems();
    }

    private final TokenSet myWhitespaces;
    private TokenSet myComments;

    private int[] myLexStarts;
    private IElementType[] myLexTypes;
    private CharSequence myText;
    private int myLexemCount = 0;
    private int myCurrentLexem = 0;

    private void resizeLexems(final int newSize) {
        int count = Math.min(newSize, myLexTypes.length);
        int[] newStarts = new int[newSize + 1];
        System.arraycopy(myLexStarts, 0, newStarts, 0, count);
        myLexStarts = newStarts;

        IElementType[] newTypes = new IElementType[newSize];
        System.arraycopy(myLexTypes, 0, newTypes, 0, count);
        myLexTypes = newTypes;
    }

    private void cacheLexems() {
        int approxLexCount = Math.max(10, myText.length() / 5);

        myLexStarts = new int[approxLexCount];
        myLexTypes = new IElementType[approxLexCount];

//      lexer.start(myText);
        int i = 0;
        while (true) {
            IElementType type = lexer.getTokenType();
            if (type == null) break;

            if (i >= myLexTypes.length - 1) {
                resizeLexems(i * 3 / 2);
            }
            myLexStarts[i] = lexer.getTokenStart();
            myLexTypes[i] = type;
            i++;
            lexer.advance();
        }

        myLexStarts[i] = myText.length();
        myLexemCount = i;
    }

    public Project getProject() {
        // todo -- idea10
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public CharSequence getOriginalText() {
        return myText;
//        return null;
    }

    public void advanceLexer() {
//        tokenEnd = lexer.getTokenEnd();
//        lexer.advance();

        myCurrentLexem++;
    }

    @Nullable
    public IElementType getTokenType() {
        if (eof()) return null;

//        if (myRemapper != null) {
//          IElementType type = myLexTypes[myCurrentLexem];
//          type = myRemapper.filter(type, myLexStarts[myCurrentLexem], myLexStarts[myCurrentLexem + 1], myLexer.getBufferSequence());
//          myLexTypes[myCurrentLexem] = type; // filter may have changed the type
//          return type;
//        }
        return myLexTypes[myCurrentLexem];
//        return lexer.getTokenType();
    }

    public void setTokenTypeRemapper(ITokenTypeRemapper iTokenTypeRemapper) {
    }

    public void remapCurrentToken(IElementType iElementType) {
        // idea 10.0.3(103.255)
    }

    public void setWhitespaceSkippedCallback(WhitespaceSkippedCallback whitespaceSkippedCallback) {
        // idea 10.0.3(103.255)
    }

    public IElementType lookAhead(int i) {
        // idea 10.0.3(103.255)
        return null;
    }

    public IElementType rawLookup(int i) {
        // idea 10.0.3(103.255)
        return null;
    }

    public int rawTokenTypeStart(int i) {
        // idea 10.0.3(103.255)
        return 0;
    }


    @Nullable
    public String getTokenText() {
        if (eof()) return null;
        final IElementType type = getTokenType();
        if (type instanceof TokenWrapper) {
            return ((TokenWrapper) type).getValue();
        }
        return myText.subSequence(myLexStarts[myCurrentLexem], myLexStarts[myCurrentLexem + 1]).toString();
//        char[] seq = lexer.getBuffer();
//        String text = new String(seq, lexer.getTokenStart(), lexer.getTokenEnd() - lexer.getTokenStart());
//        return text;
    }

    public int getCurrentOffset() {
        if (eof()) return getOriginalText().length();
        return myLexStarts[myCurrentLexem];
//        return lexer.getTokenStart();
    }


    public Marker mark() {
//        if (!myProduction.isEmpty()) {
        skipWhitespace();
//        }
        StartMarker marker = new StartMarker(myCurrentLexem); //createMarker(myCurrentLexem);

        prod.add(marker);
//        myProduction.add(marker);
        return marker;
/*
        Marker2 m = new Marker2();
        seq.add(m);

        return m;
*/
    }


    public void error(String s) {
    }

    public final boolean eof() {
//      markTokenTypeChecked();
        skipWhitespace();
        return myCurrentLexem >= myLexemCount;
    }

    private void skipWhitespace() {
        while (myCurrentLexem < myLexemCount && whitespaceOrComment(myLexTypes[myCurrentLexem])) myCurrentLexem++;
    }

    private boolean whitespaceOrComment(IElementType token) {
        return myWhitespaces.contains(token) || myComments.contains(token);
    }

//    public boolean eof() {
//        return false;
//    }

    public ASTNode getTreeBuilt() {
        lexemStart = 0;
        if (prod.size() == 2) {
            // empty file
            StartMarker start = (StartMarker) prod.get(0);
            FileElement fileEl = new FileElement(start.type, null);

            InvocationHandler psiFileHandler = new PsiFileProxy(fileEl);
            PsiFile f0 = (PsiFile) Proxy.newProxyInstance(
                    PlSqlElement.class.getClassLoader(),
//                    new Class[]{PsiFile.class, PlSqlElement.class},
                    new Class[]{PsiFile.class, PlSqlElement.class, ResolveProvider.class},
                    psiFileHandler);

            fileEl.setPsi(f0);
            return fileEl;
        } else if (prod.size() > 2) {
            StartMarker start = (StartMarker) prod.get(0);
            LightFileElement fileEl = new LightFileElement(start.type);

            int startPos = 1;
            int stop = prod.size() - 1;
            while ((startPos = buildASTTree(startPos, fileEl) + 1) < stop) {
                // loop over all top nodes
            }

            if (lexemStart < myLexemCount) {
                lexemStart = createTerminalNodes(lexemStart, myLexemCount, fileEl);
            }

            InvocationHandler psiFileHandler = new PsiFileProxy(fileEl);
            PsiFile f0 = (PsiFile) Proxy.newProxyInstance(
                    PlSqlElement.class.getClassLoader(),
                    new Class[]{PsiFile.class, PlSqlElement.class, ResolveProvider.class},
                    psiFileHandler);

            fileEl.setPsi(f0);
            return fileEl;
        } else {
            return null;
        }

/*
        InvocationHandler psiFileHandler = new PsiFileProxy(top);
        PsiFile f0 = (PsiFile) Proxy.newProxyInstance(
                PlSqlElement.class.getClassLoader(),
                new Class[]{PsiFile.class, PlSqlElement.class},
                psiFileHandler);

        top.setPsi(f0);
        return top.getASTNode();
*/
    }


    int lexemStart = 0;

    private int buildASTTree(int elem, LightweightElement parent) {
        StartMarker start = (StartMarker) prod.get(elem);
        LightweightElement child = null;

        int i = elem + 1;
        for (; i < prod.size(); i++) {
            Marker21 m = prod.get(i);

            if (child == null) {
// CGLIB
//                child = (CompositeElement) createProxy(start.type);
//                child = new CompositeElementExt2(start.type);
                child = new LightweightElement(start.type) {
                    public PsiElement getPsi() {
                        if (myPsi == null) {
                            myPsi = ASTNodeProxy.createPsiElement(this);
                        }

                        return myPsi;
                    }
                };
            }

            if (m instanceof DoneMarker) {
                // the node does not have any subnodes except leafs
                __assert__true__(((DoneMarker) m).start == start);
                if (lexemStart < ((DoneMarker) m).lexemEnd) {
                    lexemStart = createTerminalNodes(lexemStart, ((DoneMarker) m).lexemEnd, child);
                }
                parent.rawAddChildren(child);
                return i;
            } else {
                if (lexemStart < ((StartMarker) m).lexemStart) {
                    lexemStart = createTerminalNodes(lexemStart, ((StartMarker) m).lexemStart, child);
                }

                i = buildASTTree(i, child);
            }
        }

        return i;
    }

    private void __assert__true__(boolean r) {
        if (!r)
            // todo -- ERROR -not balanced tree?
            throw new Error();
    }

    private int createTerminalNodes(int previousStart, int startLexem, LightweightElement parent) {
        for (int i = previousStart; i < startLexem; i++) {
            LightLeafElement child = new LightPsiLeafElement(myLexTypes[i], myText.subSequence(myLexStarts[i], myLexStarts[i + 1]));
            parent.rawAddChildren(child);
        }

        return startLexem;
    }

    public <T> T getUserDataUnprotected(@NotNull Key<T> key) {
        // todo -- idea10
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public <T> void putUserDataUnprotected(@NotNull Key<T> key, @Nullable T value) {
        // todo -- idea10
    }


    private class PsiFileProxy implements InvocationHandler {

        ASTNode top;
        ResolveFacade domainResilver;
        String ctxPath = ContextPathUtil.encodeCtx(ContextPath.FILE_CTX, "..$" + fsPath);

        public PsiFileProxy(ASTNode top) {
            this.top = top;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("accept") && args[0] instanceof PlSqlElementVisitor) {
                ASTNode child = top.getFirstChildNode();
                while (child != null) {
                    child.getPsi().accept((PsiElementVisitor) args[0]);
                    child = child.getTreeNext();
                }
            } else if (method.getName().equals("getCtxPath1")) {
                return new PlSqlElement.CtxPath() {
                    public String getPath() {
                        return ctxPath;
                    }

                    public String getSeqNEXT() {
                        return "";
                    }
                };
            } else if (method.getName().equals("getText")) {
                return top.getText();
            } else if (method.getName().equals("getNode")) {
                return top;
            } else if (method.getName().equals("getContainingFile")) {
                return top.getPsi();
            } else if (method.getName().equals("getVirtualFile")) {
                return virtualFile;
            } else if (method.getName().equals("getResolver")) {
                return domainResilver;
            } else if (method.getName().equals("setResolver")) {
                domainResilver = (ResolveFacade) args[0];
            } else if (method.getName().equals("getModificationStamp")) {
                return 0L;
            }

            return null;
        }
    }

    public FlyweightCapableTreeStructure<LighterASTNode> getLightTree() {
        return null;
    }

    public void setDebugMode(boolean b) {
    }

    public void enforceCommentTokens(TokenSet tokenSet) {
    }

    public LighterASTNode getLatestDoneMarker() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public <T> T getUserData(Key<T> tKey) {
        return null;
    }

    public <T> void putUserData(Key<T> tKey, T t) {

    }


    private class Marker21 implements Marker {

        int local;

        public Marker21() {
            local = sequencer++;
        }

        public Marker precede() {
            return null;
        }

        public void drop() {
            // todo
        }

        public void rollbackTo() {
            //todo
        }

        public void done(IElementType type) {
            // todo
        }

        public void collapse(IElementType type) {
            // todo -- idea10
        }

        public void doneBefore(IElementType type, Marker before) {
        }

        public void doneBefore(IElementType type, Marker before, String errorMessage) {
        }

        public void error(String message) {
        }

        public void errorBefore(String message, Marker before) {
            // todo -- idea10
        }

        public void setCustomEdgeTokenBinders(@Nullable WhitespacesAndCommentsBinder left, @Nullable WhitespacesAndCommentsBinder right) {
            // todo -- idea10
        }
    }


    private class StartMarker extends Marker21 {
        DoneMarker done;
        IElementType type;
        int lexemStart;

        public StartMarker(int start) {
            this.lexemStart = start;
        }

        public void done(IElementType type) {
            done = new DoneMarker(this, myCurrentLexem);
            this.type = type;

            prod.add(done);
        }

        public void drop() {
            if (this.local == prod.get(prod.size() - 1).local) {
                prod.remove(prod.size() - 1);
            } else {
                int index = Collections.binarySearch(prod, this, new Comparator<Marker21>() {
                    public int compare(Marker21 o1, Marker21 o2) {
                        return o1.local - o2.local;
                    }
                });
                prod.remove(index);

                if (done != null) {
                    index = Collections.binarySearch(prod, done, new Comparator<Marker21>() {
                        public int compare(Marker21 o1, Marker21 o2) {
                            return o1.local - o2.local;
                        }
                    });
                    prod.remove(index);
                }
            }
        }

        public void rollbackTo() {
            myCurrentLexem = lexemStart;

            int start = prod.indexOf(this);
            prod.rollbackTo(start);
        }
    }

    private class DoneMarker extends Marker21 {
        StartMarker start;
        int lexemEnd;

        public DoneMarker(StartMarker start, int endOffset) {
            this.lexemEnd = endOffset;
            this.start = start;
        }
    }


    private class TightList extends ArrayList<Marker21> {
        public void rollbackTo(int startElem) {
            removeRange(startElem, this.size());
        }
    }


/*
    public Object createProxy(IElementType type) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CompositeElementExt.class);
        enhancer.setCallback(new CompositElementExtInterceptor()); //NoOp.INSTANCE);
        return enhancer.create(new Class[]{IElementType.class}, new Object[]{type});
   }

    private class CompositElementExtInterceptor implements MethodInterceptor {
        PsiElement psi = null;
        public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            if( method.getName().equals("getPsi")){
                if (psi == null) {
                    psi = ASTNodeProxy.createPsiElement((ASTNode) o);
                }
                return psi;
            }
            return methodProxy.invokeSuper(o, args);
        }
    }
*/


/*
    private class CompositeElementExt2 extends CompositeElementExt {

        PsiElement psi = null;

        public CompositeElementExt2(@NotNull IElementType type) {
            super(type);
        }

        public PsiElement getPsi() {
            if (psi == null) {
                psi = ASTNodeProxy.createPsiElement(this);
            }
            return psi;
        }
    }
*/


//    LightweightElement createLeaf(IElementType type, CharSequence text){
//
//        InvocationHandler h = new LightLeafElementInvocationHandler(
//            new LightLeafElement(type, text)
//        );
//
//        LightweightElement f0 = (LightweightElement) Proxy.newProxyInstance(
//                PlSqlElement.class.getClassLoader(),
//                new Class[]{PsiElement.class, ASTNode.class},
//                h);
//
//
//        return f0;
//    }


//    class LightLeafElementInvocationHandler implements InvocationHandler {
//
//        LightLeafElement leaf;
//        public LightLeafElementInvocationHandler(LightLeafElement e){
//            this.leaf = leaf;
//        }
//
//        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            if()
//            return method.invoke(leaf, args);
//        }
//    }

}

