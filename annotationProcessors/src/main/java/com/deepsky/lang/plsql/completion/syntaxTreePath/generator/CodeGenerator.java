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

import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.collections.AST;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.SyntaxTreePathLexer;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.SyntaxTreePathParser;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.SyntaxTreePathTokenTypes;
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

    /**
     * @param className class name of the completion path processor
     */
    public CodeGenerator(String className){
        this.className = className;
    }


    /**
     * Add syntax tree path to the storage
     * @param path      syntax tree path
     * @param className     class where path is defined
     * @param methodName    method the path is applied for
     * @param args          method arguments (class names)
     * @throws RecognitionException
     * @throws TokenStreamException
     */
    private void addPath(String path, String className, String methodName, String[] args) throws RecognitionException, TokenStreamException {
        AST ast = parse(path);

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
            masterNode.classMethodPair.add(new String[]{
                    className==null? "": className,
                    methodName==null? "": methodName});

            // Save parameters for the method above
            String[] methodParams = new String[args.length];
            for(int i = 0; i<args.length; i++){
                methodParams[i] = args[i];
            }
            masterNode.methodParamTypes.add(methodParams);
        }
    }

    public void addPath(String className, String[] args) throws RecognitionException, TokenStreamException {
        String[] methodArgs = new String[args.length -2];
        System.arraycopy(args, 2, methodArgs, 0, args.length-2);
        addPath(args[1], className, args[0], methodArgs);
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
            } else if (node.getType() == SyntaxTreePathTokenTypes.ANY_SYMBOL) {
                currentNode = parseAnySymbol(currentNode, node);
            } else {
                //SyntaxTreePathTokenTypes.LEFT_OPEN
                AST cur = node.getFirstChild();
                currentNode = currentNode.findOrAddDoubleDot();
                pairs.add(new NamePosPair(".."));
//                currentNode = parseSymbol(currentNode, cur.getNextSibling());
                currentNode = parseInner(currentNode, cur.getNextSibling());
                //node = node.getFirstChild();
            }

            node = node.getNextSibling();
        }
        return currentNode;
    }


    private TNode parseAnySymbol(TNode tnode, AST node) {
        AST cur = node.getFirstChild();

        int pos = -1;
        while (cur != null) {
            if (cur.getType() == SyntaxTreePathTokenTypes.NUMBER) {
                pos = Integer.parseInt(cur.getText());
            }
            cur = cur.getNextSibling();
        }

        pairs.add(new NamePosPair("ANY", pos));
        return tnode.findOrAddAnySymbol(pos);
    }

    private TNode parseSymbol(TNode tnode, AST node) {
        AST cur = node.getFirstChild();

        String ident = null;
        int pos = -1;
        boolean isDollar = true;
        boolean isExcl = false;
        while (cur != null) {
            if (cur.getType() == SyntaxTreePathTokenTypes.NUMBER) {
                pos = Integer.parseInt(cur.getText());
            } else if (cur.getType() == SyntaxTreePathTokenTypes.SHARP) {
                isDollar = false;
            } else if (cur.getType() == SyntaxTreePathTokenTypes.DOLLAR) {
                isDollar = true;
            } else if (cur.getType() == SyntaxTreePathTokenTypes.IDENTIFIER) {
                ident = cur.getText();
            } else if (cur.getType() == SyntaxTreePathTokenTypes.EXCL) {
                isExcl = true;
            }
            cur = cur.getNextSibling();
        }

        pairs.add(new NamePosPair(ident, pos, isDollar, isExcl));
        return tnode.findOrAdd(isDollar, pos, ident, isExcl);
    }


    private String buildTreePath(List<NamePosPair> p, boolean withPrefix){
        StringBuilder sb = new StringBuilder();
        for(NamePosPair pair: p){
            if(pair.isExcl){
                sb.append("!");
            }
            if(withPrefix){
                if(pair.pos == -2){
                    // do nothing
                } else if(pair.pos == -1){
                    if(!pair.isDollar){
                        sb.append("#");
                    }
                } else {
                    if(!pair.isDollar){
                        sb.append(pair.pos).append("#");
                    } else {
                        sb.append(pair.pos).append("$");
                    }
                }
                sb.append(pair.name).append(" ");

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
        List<String[]> classMethodPair = new ArrayList<String[]>();
        List<String[]> methodParamTypes = new ArrayList<String[]>();

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
            return methodParamTypes.get(pathIndex);
        }

        @Override
        public String getClassFor(int pathIndex) {
            return classMethodPair.get(pathIndex)[0];
        }

        @Override
        public String getMethodFor(int pathIndex) {
            return classMethodPair.get(pathIndex)[1];
        }
    }

    private class NamePosPair {
        String name;
        int pos;
        boolean isDollar;
        boolean isExcl;

        public NamePosPair(String ident, int pos, boolean isDollar, boolean isExcl) {
            this.name = ident;
            this.pos = pos;
            this.isDollar = isDollar;
            this.isExcl = isExcl;
        }

        public NamePosPair(String ident, int pos) {
            this.name = ident;
            this.pos = pos;
        }

        public NamePosPair(String ident) {
            this.name = ident;
            this.pos = -2;
            this.isDollar = false;
        }
    }

}
