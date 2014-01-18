package com.deepsky.lang.plsql.completion.legacy.logic;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.completion.logic.TreePathBuilder;
import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.TreePath;
import com.intellij.lang.ASTNode;


public class ObjectTreeParser {

//    public static ObjectTree parse(ASTNode marker) {
//        TreePathBuilderImpl pathBuilder = new TreePathBuilderImpl();
//        parse(marker, pathBuilder);
//        return pathBuilder.getTreeBuilt();
//    }


    public static TreePath parse(ASTNode marker, TreePathBuilder pathBuilder) {
        recovery(marker, pathBuilder);
        return pathBuilder.complete();
    }


    public static TreePath parse2(ASTNode marker, TreePathBuilder pathBuilder) {
        recovery2(marker, pathBuilder);
        return pathBuilder.complete();
    }

    private static void recovery(ASTNode node, TreePathBuilder nodeProcessor) {
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
            if (prev.getElementType() != PlSqlTokenTypes.WS) {
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
        recoveryUp2(node, nodeProcessor);
    }


    private static void recovery21(ASTNode node, TreePathBuilder nodeProcessor) {
        ASTNode prev = node;
        while (prev != null) {
            if (prev.getElementType() != PlSqlTokenTypes.WS) {
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

//            if (parent.getTreeParent().getElementType() != PlSqlTokenTypes.FILE) {
//                if (parent.getElementType() == PlSqlElementTypes.ERROR_TOKEN_A) {
//                    recoveryErrorNode(parent, nodeProcessor);
//                } else {
//                    nodeProcessor.goUp();
//                    recovery(parent, nodeProcessor);
//                }
//            } else {
//                if (parent.getElementType() != PlSqlElementTypes.ERROR_TOKEN_A) {
//                    nodeProcessor.goUp();
//                    nodeProcessor.addNode(parent);
//                }
//            }
        }
    }


//    private static class TreePathBuilderImpl implements TreePathBuilder{
//        List<ASTNode> stack = new ArrayList<ASTNode>();
//
//        List<ASTNode> cur = new ArrayList<ASTNode>();
//
//        public void addNode(ASTNode prev) {
//            cur.add(0, prev);
//        }
//
//        public void goUp() {
//            assert cur.size() != 0;
//            // TODO - for now, take the last element in collection
//            stack.add(0, cur.get(cur.size() - 1));
//            cur = new ArrayList<ASTNode>();
//        }
//
//        ObjectTree getTreeBuilt() {
//            if (cur.size() != 0) {
//                // TODO - for now, take the last element in collection
//                stack.add(0, cur.get(cur.size() - 1));
//                cur = new ArrayList<ASTNode>();
//            }
//
//            return new ObjectTree(stack);
//        }
//
//        public TreePath complete() {
//            if (cur.size() != 0) {
//                // TODO - for now, take the last element in collection
//                stack.add(0, cur.get(cur.size() - 1));
//                cur = new ArrayList<ASTNode>();
//            }
//
//            return new TreePath() {
//                @Override
//                public String printPath() {
//                    // TODO implement me
//                    return null;
//                }
//
//                @Override
//                public boolean processPattern(STPPattern[] pattern, SyntaxTreeProcessor processor) {
//                    return false;  //To change body of implemented methods use File | Settings | File Templates.
//                }
//            };
//        }
//
//    }


}
