/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
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

package com.deepsky.lang.plsql.formatter2;

import com.deepsky.lang.plsql.formatter2.settings.PlSqlCodeStyleSettings;
import com.intellij.formatting.Alignment;
import com.intellij.formatting.Block;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Wrap;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlSqlBlockGenerator {
    private final PlSqlBlock myBlock;
    private final ASTNode myNode;
    private final Alignment myAlignment;
    private final Wrap myWrap;
    private final CommonCodeStyleSettings mySettings;
    private final Map<PsiElement,Alignment> myInnerAlignments;
    private final PlSqlCodeStyleSettings plSqlSettings;

    public PlSqlBlockGenerator(PlSqlBlock block) {
        this.myBlock = block;
        myNode = myBlock.getNode();
        myAlignment = myBlock.getAlignment();
        myWrap = myBlock.getWrap();
        mySettings = myBlock.getSettings();
        myInnerAlignments = myBlock.getInnerAlignments();
        plSqlSettings = myBlock.getPlSqlSettings();
    }

    public List<Block> generateSubBlocks() {

        //For binary expressions
        PsiElement blockPsi = myNode.getPsi();

        // For other cases
        final ArrayList<Block> subBlocks = new ArrayList<Block>();
        for (ASTNode childNode : visibleChildren(myNode)) {
            final Indent indent = PlSqlIndentProcessor.getChildIndent(myBlock, childNode);
            Wrap wrap = PlSqlWrapProcessor.getChildWrap(myBlock, childNode);
            subBlocks.add(
                    new PlSqlBlock(childNode, wrap, indent,
                            myInnerAlignments.get(childNode.getPsi()),
                            mySettings, plSqlSettings,
                            myInnerAlignments));
        }
        return subBlocks;
    }

    private static List<ASTNode> visibleChildren(ASTNode node) {
        ArrayList<ASTNode> list = new ArrayList<ASTNode>();
        for (ASTNode astNode : node.getChildren(null)) {
            if (canBeCorrectBlock(astNode)) {
                list.add(astNode);
            }
        }
        return list;
    }

    /**
     * @param node Tree node
     * @return true, if the current node can be myBlock node, else otherwise
     */
    private static boolean canBeCorrectBlock(final ASTNode node) {
        return (node.getText().trim().length() > 0);
    }

}
