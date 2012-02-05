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

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.formatter2.settings.PlSqlCodeStyleSettings;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.resolver.utils.PsiUtil;
import com.intellij.formatting.Alignment;
import com.intellij.formatting.Block;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Wrap;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PlSqlBlockGenerator {
    private final PlSqlBlock myBlock;
    private final ASTNode myNode;
    private final Alignment myAlignment;
    private final Wrap myWrap;
    private final CommonCodeStyleSettings mySettings;
    private final Map<PsiElement, Alignment> myInnerAlignments;
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
        final PsiElement blockPsi = myNode.getPsi();
        if (blockPsi instanceof TableDefinition) {
            final ArrayList<Block> subBlocks = new ArrayList<Block>();
            List<ASTNode> children = PsiUtil.visibleChildren(myNode);
            if (plSqlSettings.ALIGN_DATATYPE_IN_COLUMN_DEF)
                calculateAlignments(children);
            for (ASTNode childNode : children) {
                final Indent indent = PlSqlIndentProcessor.getChildIndent(myBlock, childNode);
                Wrap wrap = PlSqlWrapProcessor.getChildWrap(myBlock, childNode, plSqlSettings);
                subBlocks.add(
                        new PlSqlBlock(childNode, wrap, indent,
                                myInnerAlignments.get(childNode.getPsi()),
                                mySettings, plSqlSettings,
                                myInnerAlignments));
            }
            return subBlocks;
        }


        if (blockPsi instanceof DeclarationList) {
            final ArrayList<Block> subBlocks = new ArrayList<Block>();
            List<ASTNode> children = PsiUtil.visibleChildren(myNode);
            if (plSqlSettings.ALIGN_DATATYPE_IN_DECL)
                calculateAlignments(children);
            for (ASTNode childNode : children) {
                final Indent indent = PlSqlIndentProcessor.getChildIndent(myBlock, childNode);
                Wrap wrap = PlSqlWrapProcessor.getChildWrap(myBlock, childNode, plSqlSettings);
                subBlocks.add(
                        new PlSqlBlock(childNode, wrap, indent,
                                myInnerAlignments.get(childNode.getPsi()),
                                mySettings, plSqlSettings,
                                myInnerAlignments));
            }
            return subBlocks;
        }

        if (blockPsi instanceof ArgumentList) {
            final ArrayList<Block> subBlocks = new ArrayList<Block>();
            List<ASTNode> children = PsiUtil.visibleChildren(myNode);
            if (plSqlSettings.ALIGN_DATATYPE_IN_ARGUMENT_LIST)
                calculateAlignments(children);
            for (ASTNode childNode : children) {
                final Indent indent = PlSqlIndentProcessor.getChildIndent(myBlock, childNode);
                Wrap wrap = PlSqlWrapProcessor.getChildWrap(myBlock, childNode, plSqlSettings);
                subBlocks.add(
                        new PlSqlBlock(childNode, wrap, indent,
                                myInnerAlignments.get(childNode.getPsi()),
                                mySettings, plSqlSettings,
                                myInnerAlignments));
            }
            return subBlocks;
        }

        // For other cases
        final ArrayList<Block> subBlocks = new ArrayList<Block>();
        for (ASTNode childNode : PsiUtil.visibleChildren(myNode)) {
            final Indent indent = PlSqlIndentProcessor.getChildIndent(myBlock, childNode);
            Wrap wrap = PlSqlWrapProcessor.getChildWrap(myBlock, childNode, plSqlSettings);
            subBlocks.add(
                    new PlSqlBlock(childNode, wrap, indent,
                            myInnerAlignments.get(childNode.getPsi()),
                            mySettings, plSqlSettings,
                            myInnerAlignments));
        }
        return subBlocks;
    }


    private void calculateAlignments(List<ASTNode> children) {
        try {
            List<Alignment> currentGroup = null;
            for (ASTNode child : children) {
                PsiElement psi = child.getPsi();
                if (psi instanceof ColumnDefinition) {
                    if (currentGroup == null) {
                        currentGroup = Arrays.asList(Alignment.createAlignment(true), Alignment.createAlignment(true));
                    }

                    ColumnDefinition column = (ColumnDefinition) psi;
                    myInnerAlignments.put(column.getPsiColumnName(), currentGroup.get(0));
                    myInnerAlignments.put(column.getTypeSpec(), currentGroup.get(1));
                } else if (psi instanceof VariableDecl) {
                    if (currentGroup == null) {
                        currentGroup = Arrays.asList(Alignment.createAlignment(true), Alignment.createAlignment(true), Alignment.createAlignment(true));
                    }

                    VariableDecl decl = (VariableDecl) psi;
                    myInnerAlignments.put(decl.getVariableName(), currentGroup.get(0));
                    myInnerAlignments.put(decl.getTypeSpec(), currentGroup.get(1));

                    ASTNode eq = child.findChildByType(PlSqlTokenTypes.ASSIGNMENT_EQ);
                    if (eq != null) {
                        myInnerAlignments.put(eq.getPsi(), currentGroup.get(2));
                    }
                } else if (psi instanceof Argument) {
                    if (currentGroup == null) {
                        currentGroup = Arrays.asList(Alignment.createAlignment(true), Alignment.createAlignment(true), Alignment.createAlignment(true));
                    }

                    Argument argument = (Argument) psi;
                    myInnerAlignments.put(argument.getPsiArgumentName(), currentGroup.get(0));
                    myInnerAlignments.put(argument.getTypeSpec(), currentGroup.get(1));
                    // If there is any parameter qualifier align the first one
                    PsiElement[] qualifiers = argument.getQualifiers();
                    if (qualifiers.length > 0) {
                        myInnerAlignments.put(qualifiers[0], currentGroup.get(2));
                    }
                }
            }
        } catch (SyntaxTreeCorruptedException e) {
            // do nothing
        }
    }
}
