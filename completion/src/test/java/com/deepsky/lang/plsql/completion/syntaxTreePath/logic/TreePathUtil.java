package com.deepsky.lang.plsql.completion.syntaxTreePath.logic;

import com.deepsky.lang.plsql.completion.ComplContributor;
import com.intellij.lang.ASTNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreePathUtil {


    interface SyntaxTreeProcessor {
        void handle(STPPattern patterns, String prefix, TreePath.ArgumentDesc[] args);
    }


    //@Override
    public static boolean processPattern(TreePath path, STPPattern[] patterns, SyntaxTreeProcessor processor) {

        for (STPPattern pattern : patterns) {
            Iterator<List<ASTNode>> pathIt = path.iterator();
            Iterator<PatternElement> patternIt = pattern.iterator();

            final List<TreePath.ArgumentDesc> argList = new ArrayList<TreePath.ArgumentDesc>();
            List<ASTNode> popped = null;
            while (pathIt.hasNext() && patternIt.hasNext()) {
                final PatternElement pElem = patternIt.next();
                while (pathIt.hasNext()) {
                    final List<ASTNode> pathElem = pathIt.next();
                    popped = pathElem;

                    if (equals(pElem, pathElem, new ArgumentHandler() {
                        @Override
                        public void process(final ASTNode ast, final int position, final boolean isAst) {
                            argList.add(new TreePath.ArgumentDesc() {
                                @Override
                                public int position() {
                                    return position;
                                }

                                @Override
                                public Class clazz() {
                                    return isAst ? ast.getClass() : ast.getPsi().getClass();
                                }

                                @Override
                                public Object object() {
                                    return isAst ? ast : ast.getPsi();
                                }

                                public String toString() {
                                    if (isAst) {
                                        return ast.getElementType().toString().replace("PLSQL:", "");
                                    } else {
                                        return ast.getPsi().getClass().getSimpleName();
                                    }
                                }
                            });
                        }
                    })) {

                        int h = 0;
                        break;
                    } else if (pElem.getHead().equals("//")) {
                        // go to the next element in the chain
                        if (!pathIt.hasNext()) {
                            return false;
                        }
                        continue;
                    } else {
                        return false;
                    }
                }
            }

            if (!patternIt.hasNext()) {
                // TODO - calculation of prefix may be not correct
                String prefix = ComplContributor.stripText(popped.get(popped.size() - 1).getText());
                processor.handle(pattern, prefix, argList.toArray(new TreePath.ArgumentDesc[argList.size()]));
                return true;
            }
        }


        return false;
    }


    private static boolean equals(PatternElement elem, List<ASTNode> level, ArgumentHandler handler) {

        int i = 0;
        Iterator<PatternElement.InnerNode> nodeIt = elem.iterator();
        boolean ignoreMismatch = false;
        while (nodeIt.hasNext()) {
            PatternElement.InnerNode iNode = nodeIt.next();
            if (iNode.getText().equals("..")) {
                // Keep iterating of AST nodes
                ignoreMismatch = true;
                continue;
            }

            boolean matched = false;
            top:
            for (; i < level.size(); i++) {
                ASTNode ast = level.get(i);
                if (iNode.isASTNode()) {
                    if (("PLSQL:" + iNode.getText()).equals(ast.getElementType().toString())) {
                        // Nodes match each other, go to the next node
                        if (iNode.getPosition() != -1) {
                            handler.process(ast, iNode.getPosition(), iNode.isASTNode());
                        }
                        i++;
                        ignoreMismatch = false;
                        matched = true;
                        break;
                    } else if(!ignoreMismatch){
                        return false;
                    }
                } else {
                    // PsiElement
                    for (Class clazz : ast.getPsi().getClass().getInterfaces()) {
                        if (clazz.getSimpleName().equals(iNode.getText())) {
                            if (iNode.getPosition() != -1) {
                                handler.process(ast, iNode.getPosition(), iNode.isASTNode());
                            }
                            i++;
                            ignoreMismatch = false;
                            matched = true;
                            break top;
                        }
                    }
                    if(!ignoreMismatch)
                        return false;
                }
            }

            if(!matched){
                // Running out of path list
                return false;
            }
        }

        return true;
    }


    interface ArgumentHandler {

        void process(ASTNode ast, int position, boolean isAst);
    }

/*
    boolean equals(PatternElement elem, ASTNode ast){
        if(elem.isASTNode()){
            return ("PLSQL:" + elem.getText()).equals(ast.getElementType().toString());
        } else {
            for(Class clazz: ast.getPsi().getClass().getInterfaces()){
                if(clazz.getSimpleName().equals(elem.getText())){
                    return true;
                }
            }
            return false;
        }
    }


    @Override
    public boolean processPattern(STPPattern[] patterns, SyntaxTreeProcessor processor) {

        for(STPPattern pattern: patterns){
            Iterator<List<ASTNode>> pathIt = iterator();
            Iterator<PatternElement> patternIt = pattern.iterator();

            List<ArgumentDesc> argList = new ArrayList<ArgumentDesc>();
            List<ASTNode> popped = null;
            while(pathIt.hasNext() && patternIt.hasNext()){
                final PatternElement pElem = patternIt.next();
                while(pathIt.hasNext()){
                    final List<ASTNode> pathElem = pathIt.next();
                    popped = pathElem;

                    final int last = pathElem.size()-1;
                    // TODO - for now being processed only last element in collection
                    if(equals(pElem, pathElem.get(last))){
                        if(pElem.getPosition() != -1){
                            argList.add(new ArgumentDesc(){
                                @Override
                                public int position() {
                                    return pElem.getPosition();
                                }

                                @Override
                                public Class clazz() {
                                    return pElem.isASTNode()?
                                            pathElem.get(last).getClass():
                                            pathElem.get(last).getPsi().getClass();
                                }

                                @Override
                                public Object object() {
                                    return pathElem.get(last);
                                }

                                public String toString(){
                                    if(pElem.isASTNode()){
                                        return pathElem.get(last).getElementType().toString().replace("PLSQL:", "");
                                    } else {
                                        return pathElem.get(last).getPsi().getClass().getSimpleName();
                                    }
                                }
                            });
                        }

                        break;
                    } else if(pElem.getHead().equals("//")){
                        // go to the next element in the chain
                        if(!pathIt.hasNext()){
                            return false;
                        }
                        continue;
                    } else {
                        return false;
                    }
                }
            }

            if(!patternIt.hasNext()){
                String prefix = ComplContributor.stripText(popped.get(popped.size()-1).getText());
                processor.handle(pattern, prefix, argList.toArray(new ArgumentDesc[0]));
                return true;
            }
        }


         return false;
    }

*/

//    @Override
//    public void iterate(PathIterator iterator) {
//        for(int i=stack.size()-1; i >= 0 ; i-- ){
//            iterator.nextLevel(stack.size()-i-1);
//
//            List<ASTNode> cur = stack.get(i);
//            for(ASTNode node : cur){
//                iterator.handle(node);
//            }
//        }
//    }

}
