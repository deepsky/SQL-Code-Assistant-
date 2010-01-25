package com.deepsky.lang.plsql.tree;

import com.intellij.lang.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import com.intellij.openapi.util.Key;
import com.deepsky.integration.PlSqlElementType;
import com.deepsky.integration.PlSqlTokenType;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.lexer.PlSqlBaseLexer;
import com.deepsky.generated.plsql.PLSqlTokenTypes;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NonNls;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class TreeNodeBuilder implements PsiBuilder {

    static int sequencer = 0;

    PlSqlBaseLexer lexer;
    char[] input;

    int tokenEnd = 0;

    public void handle_ws(int i) {
//        seq.add(new Marker2());
//        System.out.println("number of ws: " + i);
    }

    class PlSqlLexerT2 extends PlSqlBaseLexer {

        void scroll() {
            while (true) {
                PlSqlTokenType t = (PlSqlTokenType) lexer.getTokenType();
                if (t != null) {
                    int etype = t.tokenId(); //getRawToken().getType();
                    if (etype == PLSqlTokenTypes.WS || etype == PLSqlTokenTypes.LF) {
                        lexer.advance();
                    } else if (etype == PLSqlTokenTypes.SL_COMMENT
                            || etype == PLSqlTokenTypes.ML_COMMENT
                            || etype == PLSqlTokenTypes.BAD_ML_COMMENT) {
//                        throw new ConfigurationException("SL_COMMENT/ML_COMMENT/BAD_ML_COMMENT not supported");
                        // todo - is a special tagging needed???
                        lexer.advance();
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        public void start(char[] chars, int i, int i1) {
            super.start(chars, i, i1);
            scroll();
        }

        public void start(char[] chars) {
            super.start(chars);
            scroll();
        }

        public void advance() {
            super.advance();
            scroll();
        }
    }

    class Pair {
        public int start, end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public List<Pair> tokens = new ArrayList<Pair>();


    public TreeNodeBuilder(char[] input) {
        this.input = input;
        this.lexer = new PlSqlLexerT2();
        this.lexer.start(input);

        tokens.add(new Pair(lexer.getTokenStart(), lexer.getTokenEnd()));
    }

    public CharSequence getOriginalText() {
        return null;
    }

    public void advanceLexer() {
        tokenEnd = lexer.getTokenEnd();
        lexer.advance();

        tokens.add(new Pair(lexer.getTokenStart(), lexer.getTokenEnd()));
    }

    @Nullable
    public IElementType getTokenType() {
        return lexer.getTokenType();
    }

    public void setTokenTypeRemapper(ITokenTypeRemapper iTokenTypeRemapper) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

//    public void setTokenTypeRemapper(ITokenTypeRemapper iTokenTypeRemapper) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }

    @NonNls
    @Nullable
    public String getTokenText() {
        char[] seq = lexer.getBuffer();
        String text = new String(seq, lexer.getTokenStart(), lexer.getTokenEnd() - lexer.getTokenStart());

        return text;
    }

    public int getCurrentOffset() {
        return lexer.getTokenStart();
    }


    List<Marker2> seq = new ArrayList<Marker2>();

    public Marker mark() {
        Marker2 m = new Marker2();
        seq.add(m);

        return m;
    }


    public void _drop(int id) {
        Iterator<Marker2> ite = seq.iterator();
        while (ite.hasNext()) {
            Marker2 marker = ite.next();
            if (marker.local == id) {
                ite.remove();
                return;
            }
        }
    }

    void _rollbackTo(int id) {
        boolean remove = false;
        Iterator<Marker2> ite = seq.iterator();
        while (ite.hasNext()) {
            Marker2 marker = ite.next();
            if (!remove && marker.local == id) {
                remove = true;
                ite.remove();
            } else if (remove) {
                ite.remove();
            }
        }
    }

    TreeNodeBuilder _builder() {
        return this;
    }

    public Node buildASTTree() {

        if(seq.size() > 1 && seq.get(0).etype == PlSqlTokenTypes.FILE){
            Marker2 m = seq.remove(0);

            int start = seq.get(0).start;
            Node2 root = createNode(m);

            // populate leading WS
            for(int i = 0; i<start; i++){
                root.add(new Node2(i, i + 1, PlSqlTokenTypes.WS));
            }
            int end = seq.get(seq.size()-1).end;
            populateNode(root, seq);

            // populate trailing WS
            for(int i =end; i<root.end; i++){
                root.add(new Node2(end, end + 1, PlSqlTokenTypes.WS));
            }

            return root;
        }

        return null;
    }

    Node2 createNode(Marker2 m){
        return new Node2(m.start, m.end, m.etype);
    }

    private void populateNode(Node2 m, List<Marker2> lst) {
        while (lst.size() > 0) {
            Marker2 m2 = lst.get(0);
            if (m2.getRange().inside(m.getRange())) {
                lst.remove(0);
                Node2 n2 = createNode(m2);
                populateNode(n2, lst);
                // insert WS node if requried
                int last = m.start;
                if (m.nodes.size() > 0) {
                    Node2 last2 = m.nodes.get(m.nodes.size() - 1);
                    last = last2.end;
                    if (last != n2.start) {
                        for (int i0 = last; i0 < n2.start; i0++) {
                            m.add(new Node2(i0, i0 + 1, PlSqlTokenTypes.WS));
                        }
                    }
                }

                m.add(n2);
            } else {
                break;
            }
        }
    }


    class Marker2 implements PsiBuilder.Marker {
        List<Marker2> list = new ArrayList<Marker2>();

        int start;
        int end;
        int local;
        IElementType etype;
        String text;
        boolean isTerm = false;

        public Marker2() {
            this.start = _builder().lexer.getTokenStart();
            this.local = sequencer++;
//            System.out.println("++ create mark [" + local + "]");
        }

        public Marker precede() {
            return null;
        }

        public void drop() {
//            System.out.println("---drop mark [" + local + "]");
            _drop(local);
        }

        public void rollbackTo() {
            lexer.start(input, start, input.length);
//            System.out.println("---rollback mark [" + local + "]");
            _rollbackTo(local);
            tokenEnd = findPrevTokenEnd(start);
        }

        private int findPrevTokenEnd(int offset) {
            Pair last = null;
            for (Pair p : tokens) {
                if (p.start == offset) {
                    if (last == null) {
                        return p.start;
                    } else {
                        return last.end;
                    }
                } else if (offset < p.start) {
                    break;
                } else {
                    last = p;
                }
            }
            throw new ConfigurationException("Ales!");
        }

        public void done(PlSqlElementType iElementType, int i) {
            etype = iElementType;
            end = i;

            text = new String(input, start, end - start);
//            System.out.println("---done mark [" + local + "] etype = " + iElementType + " lexer.end= " + lexer.getTokenStart());
        }

        public void done(IElementType iElementType) {
            etype = iElementType;
            if (iElementType == PlSqlTokenTypes.FILE) {
                start = 0;
                end = lexer.getBufferEnd();
            } else {
                end = tokenEnd;
            }

            text = new String(input, start, end - start);
//            System.out.println("---done mark [" + local + "] etype = " + iElementType + " lexer.end= " + lexer.getTokenStart());
        }

        public void doneBefore(IElementType iElementType, PsiBuilder.Marker marker) {
        }

        public void doneBefore(IElementType iElementType, PsiBuilder.Marker marker, String s) {
        }

        public void error(String s) {
        }

        public void add(Marker2 m) {
            list.add(m);
        }

        public Range getRange() {
            return new Range(start, end);
        }
    }

    public void error(String s) {
    }

    public boolean eof() {
        return false;
    }

    public ASTNode getTreeBuilt() {
        return null;
    }

    public FlyweightCapableTreeStructure<LighterASTNode> getLightTree() {
        return null;
    }

    public void setDebugMode(boolean b) {
    }

    public void enforceCommentTokens(TokenSet tokenSet) {
    }

    @Nullable
    public LanguageDialect getLanguageDialect() {
        return null;
    }

    public <T> T getUserData(Key<T> tKey) {
        return null;
    }

    public <T> void putUserData(Key<T> tKey, T t) {

    }


    public class Node2 implements Node {

        int start, end;
        IElementType etype;
        List<Node2> nodes = new ArrayList<Node2>();

        public Node2(int start, int end, IElementType etype) {
            this.start = start;
            this.end = end;
            this.etype = etype;
        }

        public void add(Node2 n2) {
            nodes.add(n2);
        }

        public Node[] findChildrenByType(IElementType etype){
            List<Node2> _nodes = new ArrayList<Node2>();
            for(Node2 n: nodes){
                if(n.etype == etype){
                    _nodes.add(n);
                }
            }

            return _nodes.toArray(new Node2[_nodes.size()]);
        }

        public Node[] findChildrenByTypes(TokenSet set){
            List<Node2> _nodes = new ArrayList<Node2>();
            for(Node2 n: nodes){
                if(set.contains(n.etype)){
                    _nodes.add(n);
                }
            }

            return _nodes.toArray(new Node2[_nodes.size()]);
        }

        public Node[] getChildren(){
            return nodes.toArray(new Node2[nodes.size()]);
        }

        public Node[] getChildren(TokenSet set) {
            return new Node[0];  //To change body of implemented methods use File | Settings | File Templates.
        }

        public String toString(){
            return etype.toString() + " (" + start + "," + end + ")";
        }

        public Range getRange() {
            return new Range(start, end);
        }

        public String getText(){
            return new String(input, start, end-start);
        }

        public void process(NodeVisitor visitor){
            for(Node n: nodes){
                n.process(visitor);
            }

            visitor.accept(this);
        }

        public <T> T getUserData(String tKey) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public <T> void putUserData(String tKey, T t) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public IElementType getElementType() {
            return etype;
        }
    }

}
