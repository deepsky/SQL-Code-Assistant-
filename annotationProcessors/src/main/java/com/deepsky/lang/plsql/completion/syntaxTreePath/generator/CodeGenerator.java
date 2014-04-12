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
import com.deepsky.lang.plsql.completion.syntaxTreePath.structures.*;

import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeGenerator {

    private MyRootNode masterNode = new MyRootNode();
    private NamePosChain namePosChain = null;
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

        //pairs = new ArrayList<NamePosPair>();
        namePosChain = new NamePosChain();
        StringNode node = parseStartRule(ast);

        // Check added tree on duplication
        boolean duplicateFound = false;
        String purePath = namePosChain.printOut(false);
        for(NamePosChain p: masterNode.pairMap){
            if(purePath.equals(p.printOut(false))){
                // Duplicate found!
                // TODO - report error
                duplicateFound = true;
                break;
            }
        }

        if(!duplicateFound){
            node.setMetaInfoRef(masterNode.pairMap.size());
            masterNode.pairMap.add(namePosChain);
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
                namePosChain.add(new NamePosPair("//"));
                AST two = cur.getFirstChild();
                currentNode = parseInner(currentNode, two.getNextSibling());
            } else {
                // SyntaxTreePathTokenTypes.STARTER_ONE
                currentNode = currentNode.findOrAddSingleSlash();
                namePosChain.add(new NamePosPair("/"));
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
            } else if (node.getType() == SyntaxTreePathTokenTypes.SUB_NODE) {
                AST cur = node.getFirstChild();
                currentNode = parseSubNodeSymbol(currentNode, cur);
                int hh =0;
            } else if (node.getType() == SyntaxTreePathTokenTypes.LEFT_OPEN){
                AST cur = node.getFirstChild();
                currentNode = currentNode.findOrAddDoubleDot();
                namePosChain.add(new NamePosPair(".."));
                currentNode = parseInner(currentNode, cur.getNextSibling());
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

        namePosChain.add(new NamePosPair("ANY", pos));
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

        namePosChain.add(new NamePosPair(ident, pos, isDollar, isExcl));
        return tnode.findOrAdd(isDollar, pos, ident, isExcl);
    }


    private TNode parseSubNodeSymbol(TNode tNode, AST node) {
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
            } else if (cur.getType() == SyntaxTreePathTokenTypes.OPEN_PAREN) {
                cur = cur.getNextSibling();
                if(cur == null || cur.getType() != SyntaxTreePathTokenTypes.SLASH)
                    throw new RuntimeException("SUBNODE - not correct syntax");
                namePosChain.add(new NamePosPair(ident, pos, isDollar, isExcl));
                namePosChain.goDown();
                SubNode subNode = new SubNode();
                parseInner(subNode, cur);
                namePosChain.goUp();
                return tNode.findOrAdd(subNode, ident, isDollar, pos, isExcl);
            }
            cur = cur.getNextSibling();
        }

        throw new RuntimeException();
    }

    public void generate(Writer writer) {
        masterNode.accept(new CodeGeneratorVisitor(writer));
    }

    public void generate() {
        masterNode.accept(new CodeGeneratorVisitor());
    }

    private class MyRootNode extends RootNode {

        List<NamePosChain> pairMap = new ArrayList<NamePosChain>();
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
                return pairMap.get(pathIndex).printOut(true);
            } else {
                throw new RuntimeException();
            }
        }

        @Override
        public int[] getParameterIndexList(int pathIndex) {
            if(pathIndex >= 0 && pathIndex < pairMap.size()){
                return pairMap.get(pathIndex).getParameterIndexList();
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

}
