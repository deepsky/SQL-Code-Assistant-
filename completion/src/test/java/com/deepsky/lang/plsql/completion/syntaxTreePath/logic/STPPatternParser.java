package com.deepsky.lang.plsql.completion.syntaxTreePath.logic;

import antlr.Token;
import antlr.TokenStreamException;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.SyntaxTreePathLexer;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.SyntaxTreePathTokenTypes;
import org.jetbrains.annotations.NotNull;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class STPPatternParser {

    static STPPattern parse(@NotNull String syntaxTreePattern) throws TokenStreamException {

        Reader r = new StringReader(syntaxTreePattern);
        SyntaxTreePathLexer lexer = new SyntaxTreePathLexer(r);

        List<PatternElement> patterns = new ArrayList<PatternElement>();
        PatternElement last = null;
        InnerNodeImpl iNode = null;
        Token t;
        while((t = lexer.nextToken()).getType() != SyntaxTreePathLexer.EOF){
            if(t.getType() == SyntaxTreePathTokenTypes.DOUBLE_SLASH){
                if(last != null){
                    last.addNode(iNode);
                    patterns.add(last);
                }
                last = new PatternElement();
                last.setHead("//");
                iNode = new InnerNodeImpl();
            } else if(t.getType() == SyntaxTreePathTokenTypes.SLASH){
                if(last != null){
                    last.addNode(iNode);
                    patterns.add(last);
                }
                last = new PatternElement();
                last.setHead("/");
                iNode = new InnerNodeImpl();
            } else if(t.getType() == SyntaxTreePathTokenTypes.NUMBER){
                if(iNode.isComplete()){
                    last.addNode(iNode);
                    iNode = new InnerNodeImpl();
                }
                iNode.setPosition(Integer.parseInt(t.getText()));
            } else if(t.getType() == SyntaxTreePathTokenTypes.DOLLAR){
                if(iNode.isComplete()){
                    last.addNode(iNode);
                    iNode = new InnerNodeImpl();
                }
                iNode.setAST(false);
            } else if(t.getType() == SyntaxTreePathTokenTypes.SHARP){
                if(iNode.isComplete()){
                    last.addNode(iNode);
                    iNode = new InnerNodeImpl();
                }
                iNode.setAST(true);
            } else if(t.getType() == SyntaxTreePathTokenTypes.IDENTIFIER){
                if(iNode.isComplete()){
                    last.addNode(iNode);
                    iNode = new InnerNodeImpl();
                }
                iNode.setText(t.getText());
            } else if(t.getType() == SyntaxTreePathTokenTypes.DOUBLEDOT){
                if(iNode.isComplete()){
                    last.addNode(iNode);
                    iNode = new InnerNodeImpl();
                }
                iNode.setText(t.getText());
            } else {
                // TODO - throw exception
            }
        }

        if(last != null){
            last.addNode(iNode);
            patterns.add(last);
        }
        return new STPPattern(patterns);
    }


    static class InnerNodeImpl implements PatternElement.InnerNode {

        private String text;
        private boolean isAST = false;
        private int position = -1;

        boolean isComplete(){
            return text != null && text.length() > 0;
        }
        @Override
        public String getText() {
            return text;
        }

        @Override
        public boolean isASTNode() {
            return isAST;
        }

        @Override
        public int getPosition() {
            return position;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setAST(boolean AST) {
            isAST = AST;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }
}
