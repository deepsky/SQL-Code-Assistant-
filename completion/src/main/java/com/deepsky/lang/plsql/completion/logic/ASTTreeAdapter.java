package com.deepsky.lang.plsql.completion.logic;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.TreePath;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ASTTreeAdapter {

    public static TreePath recovery2(ASTNode marker) {
        TreePathBuilderImpl pathBuilder = new TreePathBuilderImpl();
        recovery2(marker, pathBuilder);
        return pathBuilder.complete();
    }

    private static class TreePathBuilderImpl implements TreePathBuilder {
        Stack<List<ASTNode>> stack = new Stack<List<ASTNode>>();

        List<ASTNode> cur = new ArrayList<ASTNode>();

        public void addNode(ASTNode prev) {
            cur.add(0, prev);
        }

        public void goUp() {
            assert cur.size() != 0;
            stack.add(cur);
            cur = new ArrayList<ASTNode>();
        }

        public TreePath complete() {
            if (cur.size() != 0) {
                stack.add(cur);
                cur = new ArrayList<ASTNode>();
            }

            return new TreePathImpl(stack);
        }

        private String indent(int offset){
            StringBuilder b = new StringBuilder();
            for(int i=0; i<offset; i++){
                b.append("  ");
            }

            return b.toString();
        }

    }


//    public static TreePath parse(ASTNode marker, TreePathBuilder pathBuilder) {
//        recovery(marker, pathBuilder);
//        return pathBuilder.complete();
//    }
//
//
//    public static TreePath parse2(ASTNode marker, TreePathBuilder pathBuilder) {
//        recovery2(marker, pathBuilder);
//        return pathBuilder.complete();
//    }

    public static void recovery(ASTNode node, TreePathBuilder nodeProcessor) {
        ASTNode prev = node;
        while (prev != null) {
            if (prev.getElementType() != PlSqlTokenTypes.WS) {
                if (prev.getElementType() == PlSqlElementTypes.ERROR_TOKEN_A) {
                    recoveryDown(prev.getLastChildNode(), nodeProcessor);
                } else {
                    nodeProcessor.addNode(prev);
                }
            }
            prev = prev.getTreePrev();
        }

        // Check parent node
        recoveryUp(node, nodeProcessor);
    }

    private static void recoveryDown(ASTNode node, TreePathBuilder nodeProcessor) {
        ASTNode prev = node;
        while (prev != null) {
            final IElementType type = prev.getElementType();
            if (type != PlSqlTokenTypes.WS && type != PlSqlTokenTypes.LF && type != TokenType.WHITE_SPACE
                    && type != PlSqlTokenTypes.SL_COMMENT && type != PlSqlTokenTypes.ML_COMMENT) {
                nodeProcessor.addNode(prev);
            }
            prev = prev.getTreePrev();
        }

    }

    private static void recoveryErrorNode(ASTNode node, TreePathBuilder nodeProcessor) {
        ASTNode prev = node.getTreePrev();
        if (prev != null) {
            recovery(prev, nodeProcessor);
        } else {
            recoveryUp(node, nodeProcessor);
        }
    }

    private static void recoveryUp(ASTNode node, TreePathBuilder nodeProcessor) {
        // Check parent node
        ASTNode parent = node != null ? node.getTreeParent() : null;
        if (parent != null && parent.getElementType() != PlSqlTokenTypes.FILE) {
            if (parent.getTreeParent().getElementType() != PlSqlTokenTypes.FILE) {
                if (parent.getElementType() == PlSqlElementTypes.ERROR_TOKEN_A) {
                    recoveryErrorNode(parent, nodeProcessor);
                } else {
                    nodeProcessor.goUp();
                    recovery(parent, nodeProcessor);
                }
            } else {
                if (parent.getElementType() == PlSqlElementTypes.ERROR_TOKEN_A) {
                    //recoveryErrorNode(parent, nodeProcessor);
                } else {
                    nodeProcessor.goUp();
                    nodeProcessor.addNode(parent);
                }
            }
        }
    }


    private static void recovery2(ASTNode node, TreePathBuilder nodeProcessor) {
        ASTNode prev = node;
        while (prev != null) {
            final IElementType type = prev.getElementType();
            if (type != PlSqlTokenTypes.WS && type != PlSqlTokenTypes.LF && type != TokenType.WHITE_SPACE
                    && type != PlSqlTokenTypes.SL_COMMENT && type != PlSqlTokenTypes.ML_COMMENT) {
                if (prev.getElementType() == PlSqlElementTypes.ERROR_TOKEN_A) {
                    recoveryDown(prev.getLastChildNode(), nodeProcessor);
                } else {
                    nodeProcessor.addNode(prev);
                }
            }
            prev = prev.getTreePrev();
        }

        // Check parent node
        recoveryUp2(node, nodeProcessor);
    }


    private static void recovery21(ASTNode node, TreePathBuilder nodeProcessor) {
        ASTNode prev = node;
        while (prev != null) {
            final IElementType type = prev.getElementType();
            if (type != PlSqlTokenTypes.WS && type != PlSqlTokenTypes.LF && type != TokenType.WHITE_SPACE
                    && type != PlSqlTokenTypes.SL_COMMENT && type != PlSqlTokenTypes.ML_COMMENT) {
                nodeProcessor.addNode(prev);
            }
            prev = prev.getTreePrev();
        }

        // Check parent node
        recoveryUp2(node, nodeProcessor);
    }

    private static void recoveryUp2(ASTNode node, TreePathBuilder nodeProcessor) {
        // Check parent node
        ASTNode parent = node != null ? node.getTreeParent() : null;
        if (parent != null && parent.getElementType() != PlSqlTokenTypes.FILE) {
            nodeProcessor.goUp();
            recovery21(parent, nodeProcessor);
        }
    }

}
