
package com.deepsky.lang.plsql.completion;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.ObjectTreeParser;
import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.STPPattern;
import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.TreePath;
import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.TreePathBuilder;
import com.intellij.lang.ASTNode;
import test.deepsky.lang.plsql.PlSqlParser_AbstractTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SyntaxErrorResolverTest extends PlSqlParser_AbstractTest {

    final static String MARKER = "<caret>";

    public SyntaxErrorResolverTest() {
        super("completion", ".test");
    }

    public void testSelect() throws Exception {
        String text = "select <caret>";
        parseScript1(text);
    }

    public void testSelect1() throws Exception {
        String text = "select a <caret>";
        parseScript1(text);
    }

    public void testSelect11() throws Exception {
        String text = "select a <caret> from g";
        parseScript1(text);
    }

    public void testSelect2() throws Exception {
        String text = "select a-b/3, as1, <caret>";
        parseScript1(text);
    }

    public void testSelect3() throws Exception {
        String text = "select 89, uui from <caret>";
        parseScript1(text);
    }

    public void testSelect4() throws Exception {
        String text = "select 1 from a <caret>";
        parseScript1(text);
    }

    public void testGrant0() throws Exception {
        String text = "g<caret>";
        parseScript1(text);
    }

    public void testGrant1() throws Exception {
        String text = "grant create session toi<caret>";
        parseScript1(text);
    }

    public void testGrant2() throws Exception {
        String text = "grant create se<caret>";
        parseScript1(text);
    }

    public void testGrant21() throws Exception {
        String text = "grant creat<caret>";
        parseScript1(text);
    }

    public void testGrant3() throws Exception {
        String text = "grant create session to boss<caret>";
        parseScript1(text);
    }

    public void testGrant01() throws Exception {
        String text = "select * grant create session toi<caret>";
        parseScript1(text);
    }

    public void testGrant02() throws Exception {
        String text = "create table grant create se<caret>";
        parseScript1(text);
    }

    public void testGrant021() throws Exception {
        String text = "create table a (d int) grant creat<caret>";
        parseScript1(text);
    }

    public void testGrant03() throws Exception {
        String text = "delete a grant create session to boss<caret>";
        parseScript1(text);
    }


    public void testUpdate1() throws Exception {
        String text = "update tab1<caret>";
        parseScript1(text);
    }

    public void testUpdate2() throws Exception {
        String text = "update tab1 set1<caret>";
        parseScript1(text);
    }

    public void testUpdate21() throws Exception {
        String text = "update tab1 s1 set1<caret>";
        parseScript1(text);
    }

    public void testUpdate3() throws Exception {
        String text = "update tab1 set a<caret>";
        parseScript1(text);
    }

    public void testUpdate4() throws Exception {
        String text = "update tab1 set a =<caret>";
        parseScript1(text);
    }

    public void testUpdate5() throws Exception {
        String text = "update tab1 set a = 2, <caret>";
        parseScript1(text);
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
//        return pathBuilder.printPath();
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
                public boolean processPattern(STPPattern[] pattern, SyntaxTreeProcessor processor) {
                    return false;  //To change body of implemented methods use File | Settings | File Templates.
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



    private void parseScript1(String text) {
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


}
