package com.deepsky.lang.plsql.completion.syntaxTreePath.logic;

import com.intellij.lang.ASTNode;

import java.util.Iterator;
import java.util.List;

public class PatternProcessor {

    public static boolean processPattern(TreePath path, STPPattern[] pattern, SyntaxTreeProcessor processor){

//        StringBuilder b = new StringBuilder();
//        int i = 0;
//        while (path.stack.size() > 0) {
//            List<ASTNode> cur = path.stack.pop();
//            for (ASTNode node : cur) {
//                b.append(indent(i)).append(node.getElementType()).append("\n");
//            }
//
//            i++;
//        }
//        return b.toString();

        // TODO implement me
        return true;
    }

//    public boolean processPattern(List<String> path, STPPattern pattern, SyntaxTreeProcessor processor){
//        Iterator<String> pathIt = path.iterator();
//        Iterator<PatternElement> patternIt = pattern.iterator();
//
//        while(pathIt.hasNext() && patternIt.hasNext()){
//            PatternElement pElem = patternIt.next();
//
//            while(pathIt.hasNext()){
//                String pathElem = pathIt.next();
//
//                if(pElem.getText().equals(pathElem)){
//                    // TODO
//                    break;
//                } else if(pElem.getHead().equals("//")){
//                    // go to the next element in the chain
//                } else {
//                   return false;
//                }
//            }
//        }
//
//        if(!patternIt.hasNext()){
//            processor.handle(pattern);
//            return true;
//        } else{
//            return false;
//        }
//    }

}
