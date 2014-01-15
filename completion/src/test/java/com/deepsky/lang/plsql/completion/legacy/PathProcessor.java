package com.deepsky.lang.plsql.completion.legacy;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.completion.VariantsProvider;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;

import java.util.ArrayList;
import java.util.List;


public class PathProcessor implements Visitor {

    private Project project;
    private ASTNode target;
    private VariantsProvider provider;
    private ObjectTree objectTree;

    private PathProcessor(){
    }

    public PathProcessor create(Project project, ASTNode target, VariantsProvider provider){
        PathProcessor processor = new PathProcessor();
        processor.project = project;
        processor.target = target;
        processor.provider = provider;
        processor.objectTree = buildPath(target);

        return processor;
    }

    public List<LookupElement> process(){

        SyntaxTreeLookupService lookup = SyntaxTreeLookupService.getInstance(project);
        for(SyntaxTreePathWrap node: lookup.getPaths()){
            node.process(this);
        }

        // TODO - implement me
        return null;
    }

    @Override
    public boolean accept(SyntaxTreePathWrap node) {

        //node.
        // TODO - implement me
        return false;
    }


/*
    void processRoot(SyntaxTreePathWrap node){

        List<SyntaxTreePathWrap> cur = new ArrayList<SyntaxTreePathWrap>();
        for(int i =0; i< objectTree.length();i++){

            List<SyntaxTreePathWrap> cur1 = cur;
            cur.clear();
            if(i==0){
                ASTNode top = objectTree.getSingle(i);
                if(!node.matchOne(top)){
                    return;
                } else {
                    cur.addAll(node.getChildren());
                }
            } else {
                Iterator<SyntaxTreePathWrap> ite = cur1.iterator();
                while(ite.hasNext()){
                    SyntaxTreePathWrap c = ite.next();
                    if(c.doMatchSingle()){
                        if(c.matchOne(objectTree.getSingle(i))){
                            cur.addAll(c.getChildren());
                        }
                    } else {

                    }


                }
            }
        }

        if(objectTree.length() > 0){
            if(!node.match(objectTree.getTop())){
                return;
            }
        }
    }
*/


    private ObjectTree buildPath(ASTNode marker) {
        TreePathBuilder pathBuilder = new TreePathBuilder();

        ASTNode prev = marker;
        while (prev != null) {
            if (prev.getElementType() == PlSqlElementTypes.ERROR_TOKEN_A) {
                recoveryErr(prev.getLastChildNode(), pathBuilder);
            } else {
                if (prev.getElementType() != PlSqlTokenTypes.WS) {
                    pathBuilder.addNode(prev);
                }
            }
            prev = prev.getTreePrev();
        }

        // Check parent node
        recoveryUp(marker, pathBuilder);

        return pathBuilder.build();
    }

    private void recoveryErr(ASTNode node, TreePathBuilder nodeProcessor) {
        ASTNode prev = node;
        while (prev != null) {
            if (prev.getElementType() != PlSqlTokenTypes.WS
                    && prev.getElementType() != PlSqlTokenTypes.LF
                    && prev.getElementType() != PlSqlTokenTypes.ML_COMMENT
                    && prev.getElementType() != PlSqlTokenTypes.SL_COMMENT) {
                nodeProcessor.addNode(prev);
            }
            prev = prev.getTreePrev();
        }
    }

    private void recoveryUp(ASTNode node, TreePathBuilder nodeProcessor) {
        // Check parent node
        ASTNode parent = node != null ? node.getTreeParent() : null;
        if (parent != null && parent.getElementType() != PlSqlTokenTypes.FILE) {
            nodeProcessor.goUp();
            nodeProcessor.addNode(parent);
            recoveryUp(parent, nodeProcessor);
        }
    }


    private class TreePathBuilder {
        List<ASTNode> stack = new ArrayList<ASTNode>();

        List<ASTNode> cur = new ArrayList<ASTNode>();

        void addNode(ASTNode prev) {
            cur.add(0, prev);
        }

        void goUp() {
            assert cur.size() != 0;
            // TODO - for now, take the last element in collection
            stack.add(0, cur.get(cur.size() - 1));
            cur = new ArrayList<ASTNode>();
        }

        public ObjectTree build() {
            if (cur.size() != 0) {
                // TODO - for now, take the last element in collection
                stack.add(0, cur.get(cur.size()-1));
                cur = new ArrayList<ASTNode>();
            }

            return new ObjectTree(stack);
        }

//        public String printPath() {
//            StringBuilder b = new StringBuilder();
//            for(List<ASTNode> cur : stack){
//                b.append("/");
//                for (ASTNode node : cur) {
//                    b.append(node.getElementType()).append(" ");
//                }
//            }
//            return b.toString();
//        }

    }


}
