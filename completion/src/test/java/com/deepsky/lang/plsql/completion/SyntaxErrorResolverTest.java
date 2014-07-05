
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

package com.deepsky.lang.plsql.completion;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.TreePath;
import com.deepsky.lang.plsql.completion.logic.TreePathBuilder;
import com.intellij.lang.ASTNode;
//import test.deepsky.lang.plsql.PlSqlParser_AbstractTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class SyntaxErrorResolverTest extends AbstractCompletionTest { //PlSqlParser_AbstractTest {

    final static String MARKER = "<caret>";

    public SyntaxErrorResolverTest() {
//        super("completion", ".test");
    }

    public void testSelect() throws Exception {
        String text = "select <caret>";
        parseScript11(text);
    }

    public void testSelect1() throws Exception {
        String text = "select a <caret>";
        parseScript11(text);
    }

    public void testSelect11() throws Exception {
        String text = "select a <caret> from g";
        parseScript11(text);
    }

    public void testSelect2() throws Exception {
        String text = "select a-b/3, as1, <caret>";
        parseScript11(text);
    }

    public void testSelect3() throws Exception {
        String text = "select 89, uui from <caret>";
        parseScript11(text);
    }

    public void testSelect4() throws Exception {
        String text = "select 1 from a <caret>";
        parseScript11(text);
    }

    public void testGrant0() throws Exception {
        String text = "g<caret>";
        parseScript11(text);
    }

    public void testGrant1() throws Exception {
        String text = "grant create session toi<caret>";
        parseScript11(text);
    }

    public void testGrant2() throws Exception {
        String text = "grant create se<caret>";
        parseScript11(text);
    }

    public void testGrant21() throws Exception {
        String text = "grant creat<caret>";
        parseScript11(text);
    }

    public void testGrant3() throws Exception {
        String text = "grant create session to boss<caret>";
        parseScript11(text);
    }

    public void testGrant01() throws Exception {
        String text = "select * grant create session toi<caret>";
        parseScript11(text);
    }

    public void testGrant02() throws Exception {
        String text = "create table grant create se<caret>";
        parseScript11(text);
    }

    public void testGrant021() throws Exception {
        String text = "create table a (d int) grant creat<caret>";
        parseScript11(text);
    }

    public void testGrant03() throws Exception {
        String text = "delete a grant create session to boss<caret>";
        parseScript11(text);
    }


    public void testUpdate1() throws Exception {
        String text = "update tab1<caret>";
        parseScript11(text);
    }

    public void testUpdate2() throws Exception {
        String text = "update tab1 set1<caret>";
        parseScript11(text);
    }

    public void testUpdate21() throws Exception {
        String text = "update tab1 s1 set1<caret>";
        parseScript11(text);
    }

    public void testUpdate3() throws Exception {
        String text = "update tab1 set a<caret>";
        parseScript11(text);
    }

    public void testUpdate4() throws Exception {
        String text = "update tab1 set a =<caret>";
        parseScript11(text);
    }

    public void testUpdate5() throws Exception {
        String text = "update tab1 set a = 2, <caret>";
        parseScript11(text);
    }


    public void _testUpdate105() throws Exception {
        String text = "select * from (select 1, 8-4 from <caret>)";
        parseScript12(text);
    }


    void print(ASTNode parent, int indent) {
        System.out.println(putIndent(indent) + parent.getElementType());
        ASTNode cur = parent.getFirstChildNode();
        while (cur != null) {
            if (cur.getElementType() != PlSqlTokenTypes.WS) {
                print(cur, indent + 1);
            }
            cur = cur.getTreeNext();
        }
    }

    String putIndent(int indent) {
        StringBuilder b = new StringBuilder(indent);
        for (int i = 0; i < indent * 2; i++) {
            b.append(' ');
        }
        return b.toString();
    }


    // --------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------
    private String recovery22(ASTNode marker) {
        TreePathBuilderImpl pathBuilder = new TreePathBuilderImpl();
        return ObjectTreeParser.parse(marker, pathBuilder).printPath();
    }

    class TreePathBuilderImpl implements TreePathBuilder {
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

            return new TreePath() {
                @Override
                public String printPath() {
                    StringBuilder b = new StringBuilder();
                    int i = 0;
                    while (stack.size() > 0) {
                        List<ASTNode> cur = stack.pop();
                        for (ASTNode node : cur) {
                            b.append(indent(i)).append(node.getElementType()).append("\n");
                        }

                        i++;
                    }
                    return b.toString();
                }

                @Override
                public Iterator<List<ASTNode>> iterator() {
                    throw new RuntimeException();
                }

            };
        }

//        public String printPath() {
//            StringBuilder b = new StringBuilder();
//            int i = 0;
//            while (stack.size() > 0) {
//                List<ASTNode> cur = stack.pop();
//                for (ASTNode node : cur) {
//                    b.append(indent(i)).append(node.getElementType()).append("\n");
//                }
//
//                i++;
//            }
//            return b.toString();
//        }

        private String indent(int offset){
            StringBuilder b = new StringBuilder();
            for(int i=0; i<offset; i++){
                b.append("  ");
            }

            return b.toString();
        }

    }



    private void parseScript11(String text) {
        System.out.println("---  " + text);
        text = text.replace(MARKER, "IntellijIdeaRulezzz");
        ASTNode root = parseString(text);
        ASTNode marker = root.findLeafElementAt(text.indexOf("IntellijIdeaRulezzz"));
        print(root, 0);

        System.out.println("###  Restored tree:");
        System.out.println(recovery22(marker));

//        System.out.println("###  Original tree:");
//        print(root, 0);
        System.out.flush();
    }


    private void parseScript12(String text) {
        System.out.println("---  " + text);
        text = text.replace(MARKER, "IntellijIdeaRulezzz");
        ASTNode root = parseString(text);
        ASTNode marker = root.findLeafElementAt(text.indexOf("IntellijIdeaRulezzz"));
        print(root, 0);

        System.out.println("###  Restored tree:");
        TreePathBuilder pathBuilder = new TreePathBuilder(){

            @Override
            public void addNode(ASTNode prev) {
                if(prev.getElementType() == PlSqlElementTypes.SELECT_EXPRESSION){
                    int o = 0;
                } else if(prev.getElementType() == PlSqlElementTypes.SELECT_EXPRESSION_UNION){
                    int o = 0;
                }
                int h =0;
            }

            @Override
            public void goUp() {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public TreePath complete() {
                return null;
            }
        };

        String out = ObjectTreeParser.parse(marker, pathBuilder).printPath();
//        System.out.println(recovery22(marker));
//        System.out.println("###  Original tree:");
//        print(root, 0);
        System.out.flush();
    }

}
