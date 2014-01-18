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
import com.deepsky.lang.plsql.completion.legacy.logic.ObjectTreeParser;
import com.deepsky.lang.plsql.completion.logic.ASTTreeAdapter;
import com.deepsky.lang.plsql.completion.logic.TreePathBuilder;
import com.deepsky.lang.plsql.completion.logic.TreePathImpl;
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

public abstract class AbstractCompletionTest extends TestCase {
    final static String MARKER = "<caret>";

    public AbstractCompletionTest() {
//        super("completion", ".test");
    }

    // --------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------
    private TreePath recovery(ASTNode marker) {
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
        TreePath path = ASTTreeAdapter.recovery2(marker);
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
