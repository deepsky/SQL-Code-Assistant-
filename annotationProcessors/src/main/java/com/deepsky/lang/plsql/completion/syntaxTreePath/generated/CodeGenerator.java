package com.deepsky.lang.plsql.completion.syntaxTreePath.generated;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.collections.AST;
import com.deepsky.lang.plsql.completion.syntaxTreePath.structures.RootNode;
import com.deepsky.lang.plsql.completion.syntaxTreePath.structures.StringNode;
import com.deepsky.lang.plsql.completion.syntaxTreePath.structures.TNode;

import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeGenerator {

    private MyRootNode masterNode = new MyRootNode();
    private List<NamePosPair> pairs = null;
    private String className;

    public CodeGenerator(String className){
        this.className = className;
    }

    public void buildTree(String text) throws RecognitionException, TokenStreamException {
        AST ast = parse(text);

        pairs = new ArrayList<NamePosPair>();
        StringNode node = parseStartRule(ast);

        // Check added tree on duplication
        boolean duplicateFound = false;
        String purePath = buildTreePath(pairs, false);
        for(List<NamePosPair> p: masterNode.pairMap){
            if(purePath.equals(buildTreePath(p, false))){
                // Duplicate found!
                // TODO - report error
                duplicateFound = true;
                break;
            }
        }

        if(!duplicateFound){
            node.setMetaInfoRef(masterNode.pairMap.size());
            masterNode.pairMap.add(pairs);
        }
    }

    private AST parse(String text) throws TokenStreamException, RecognitionException {
        Reader r = new StringReader(text);
        SyntaxTreePathLexer lexer = new SyntaxTreePathLexer(r);
        SyntaxTreePathParser parser = new SyntaxTreePathParser(lexer);
        parser.start_rule();
        return parser.getAST();
    }

    private StringNode parseStartRule(AST node) {
        AST cur = node.getFirstChild();
        TNode currentNode = masterNode;
        while (cur != null) {
            if (cur.getType() == SyntaxTreePathTokenTypes.STARTER_TWO) {
                currentNode = currentNode.findOrAddDoubleSlash();
                pairs.add(new NamePosPair("//"));
                AST two = cur.getFirstChild();
                currentNode = parseInner(currentNode, two.getNextSibling());
            } else {
                // SyntaxTreePathTokenTypes.STARTER_ONE
                currentNode = currentNode.findOrAddSingleSlash();
                pairs.add(new NamePosPair("/"));
                AST one = cur.getFirstChild();
                currentNode = parseInner(currentNode, one.getNextSibling());
            }
            cur = cur.getNextSibling();
        }

        if (!(currentNode instanceof StringNode)) throw new AssertionError("SyntaxTreeParsing failed!");
        // at this point 'currentNode' is the last node
        return (StringNode) currentNode;
    }


    private TNode parseInner(TNode tNode, AST node) {
        TNode currentNode = tNode;
        while (node != null) {
            if (node.getType() == SyntaxTreePathTokenTypes.SYMBOL) {
                currentNode = parseSymbol(currentNode, node);
            } else {
                //SyntaxTreePathTokenTypes.LEFT_OPEN
                AST cur = node.getFirstChild();
                currentNode = currentNode.findOrAddDoubleDot();
                pairs.add(new NamePosPair(".."));
                currentNode = parseSymbol(currentNode, cur.getNextSibling());
            }

            node = node.getNextSibling();
        }
        return currentNode;
    }


    private TNode parseSymbol(TNode tnode, AST node) {
        AST cur = node.getFirstChild();

        String ident = null;
        int pos = -1;
        boolean isDollar = true;
        while (cur != null) {
            if (cur.getType() == SyntaxTreePathTokenTypes.NUMBER) {
                pos = Integer.parseInt(cur.getText());
            } else if (cur.getType() == SyntaxTreePathTokenTypes.SHARP) {
                isDollar = false;
            } else if (cur.getType() == SyntaxTreePathTokenTypes.DOLLAR) {
                isDollar = true;
            } else if (cur.getType() == SyntaxTreePathTokenTypes.IDENTIFIER) {
                ident = cur.getText();
            }
            cur = cur.getNextSibling();
        }

        pairs.add(new NamePosPair(ident, pos, isDollar));
        return tnode.findOrAdd(isDollar, pos, ident);
    }


    private String buildTreePath(List<NamePosPair> p, boolean withPrefix){
        StringBuilder sb = new StringBuilder();
        for(NamePosPair pair: p){
            if(withPrefix){
                if(pair.pos == -2)
                    sb.append(pair.name).append(" ");
                else if(pair.pos == -1){
                    if(!pair.isDollar){
                        sb.append("#");
                    }
                    sb.append(pair.name).append(" ");
                }
                else {
                    if(!pair.isDollar){
                        sb.append(pair.pos).append("#");
                    } else {
                        sb.append(pair.pos).append("$");
                    }

                    sb.append(pair.name).append(" ");
                }

            } else {
                sb.append(pair.name).append(" ");
            }
        }
        return sb.toString().trim();
    }

    public void generate(Writer writer) {
        masterNode.accept(new CodeGeneratorVisitor(writer));
    }

    public void generate() {
        masterNode.accept(new CodeGeneratorVisitor());
    }

    private class MyRootNode extends RootNode {

        List<List<NamePosPair>> pairMap = new ArrayList<List<NamePosPair>>();

        @Override
        public String getProcessorClassName() {
            return CodeGenerator.this.className != null? CodeGenerator.this.className: "CompletionProcessor";
        }

        @Override
        public int getTreePathListSize() {
            return pairMap.size();
        }

        @Override
        public String getTreePath(int pathIndex) {
            if(pathIndex >= 0 && pathIndex < pairMap.size()){
                return buildTreePath(pairMap.get(pathIndex), true);
            } else {
                throw new RuntimeException();
            }
        }

        @Override
        public int[] getParameterIndexList(int pathIndex) {
            if(pathIndex >= 0 && pathIndex < pairMap.size()){
                int[] out = new int[pairMap.get(pathIndex).size()];
                int idx = 0;
                int nodeIdx = 0;
                for(NamePosPair p: pairMap.get(pathIndex)){
                    if(p.pos > 0){
                        out[idx] = nodeIdx; // TODO - p.pos should be validated to be: sequentially incremented and started with 1
                        idx++;
                    }
                    nodeIdx++;
                }
                return Arrays.copyOf(out, idx);
            } else {
                throw new RuntimeException();
            }
        }

        @Override
        public String[] getParameterClassList(int pathIndex) {
            return new String[0];
        }
    }

    private class NamePosPair {
        String name;
        int pos;
        boolean isDollar;

        public NamePosPair(String ident, int pos, boolean isDollar) {
            this.name = ident;
            this.pos = pos;
            this.isDollar = isDollar;
        }

        public NamePosPair(String ident) {
            this.name = ident;
            this.pos = -2;
            this.isDollar = false;
        }
    }

}
