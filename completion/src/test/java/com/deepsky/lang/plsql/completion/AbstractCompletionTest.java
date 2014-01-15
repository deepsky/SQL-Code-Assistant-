package com.deepsky.lang.plsql.completion;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.plsql.completion.legacy.logic.ObjectTreeParser;
import com.deepsky.lang.plsql.completion.legacy.logic.TreePathBuilder;
import com.deepsky.lang.plsql.completion.legacy.logic.TreePathImpl;
import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.*;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class AbstractCompletionTest extends TestCase { //PlSqlParser_AbstractTest {
    final static String MARKER = "<caret>";

    public AbstractCompletionTest() {
//        super("completion", ".test");
    }

    // --------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------
    private TreePath recovery22(ASTNode marker) {
        TreePathBuilderImpl pathBuilder = new TreePathBuilderImpl();
        return ObjectTreeParser.parse(marker, pathBuilder);
    }

    private TreePath recovery2(ASTNode marker) {
        TreePathBuilderImpl pathBuilder = new TreePathBuilderImpl();
        return ObjectTreeParser.parse2(marker, pathBuilder);
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



    public TreePath parseScript1(String text) {
        System.out.println("---  " + text);
        text = text.replace(MARKER, "IntellijIdeaRulezzz");
        ASTNode root = parseString(text);
        ASTNode marker = root.findLeafElementAt(text.indexOf("IntellijIdeaRulezzz"));
        print(root, 0);

        System.out.println("###  Restored tree:");
//        TreePath path = recovery22(marker);
        TreePath path = recovery2(marker);
        System.out.println(path.printPath());

//        System.out.println("###  Original tree:");
//        print(root, 0);
        System.out.flush();
        return path;
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

//    protected ASTNode parseScript(String file) throws IOException {
//        String content = StringUtils.file2string(new File(getBase(), file));
//        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
//        return generator.parse(content);
//    }

    protected ASTNode parseScript(File file) throws IOException {
        String content = StringUtils.file2string(file);
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        return generator.parse(content);
    }

    protected ASTNode parseString(String content) {
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        return generator.parse(content);
    }



}
