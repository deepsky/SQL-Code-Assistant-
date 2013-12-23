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

package com.deepsky.lang.plsql.formatter;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.formatter.processors.PlSqlIndentProcessor;
import com.deepsky.lang.plsql.formatter.processors.PlSqlWrapProcessor;
import com.deepsky.lang.plsql.formatter.settings.PlSqlCodeStyleSettings;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.AlterTable;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.psi.types.TypeSpec;
import com.deepsky.lang.PsiUtil;
import com.intellij.formatting.Alignment;
import com.intellij.formatting.Block;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Wrap;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiComment;
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
        if (blockPsi instanceof TableDefinition || blockPsi instanceof AlterTable) {
            final ArrayList<Block> subBlocks = new ArrayList<Block>();
            List<ASTNode> children = PsiUtil.visibleChildren(myNode);
            calculateColumnDefAlignments(children, plSqlSettings.ALIGNMENT_IN_COLUMN_DEF);
            for (ASTNode childNode : children) {
                final Indent indent = PlSqlIndentProcessor.getChildIndent(myBlock, childNode, plSqlSettings);
                Wrap wrap = PlSqlWrapProcessor.getChildWrap(myBlock, childNode, plSqlSettings);
                subBlocks.add(
                        new PlSqlBlock(childNode, wrap, indent,
                                myInnerAlignments.get(childNode.getPsi()),
                                mySettings, plSqlSettings,
                                myInnerAlignments));
            }
            return subBlocks;
        }

        if (blockPsi instanceof SelectStatement) {
            final ArrayList<Block> subBlocks = new ArrayList<Block>();
            List<ASTNode> children = PsiUtil.visibleChildren(myNode);
            if (plSqlSettings.ALIGN_ALIAS_NAME_IN_SELECT)
                calculateAliasNameAlignments(children);
            for (ASTNode childNode : children) {
                final Indent indent = PlSqlIndentProcessor.getChildIndent(myBlock, childNode, plSqlSettings);
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
                final Indent indent = PlSqlIndentProcessor.getChildIndent(myBlock, childNode, plSqlSettings);
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
                final Indent indent = PlSqlIndentProcessor.getChildIndent(myBlock, childNode, plSqlSettings);
                Wrap wrap = PlSqlWrapProcessor.getChildWrap(myBlock, childNode, plSqlSettings);
                subBlocks.add(
                        new PlSqlBlock(childNode, wrap, indent,
                                myInnerAlignments.get(childNode.getPsi()),
                                mySettings, plSqlSettings,
                                myInnerAlignments));
            }
            return subBlocks;
        }

        if (blockPsi instanceof ObjectTypeDecl || blockPsi instanceof RecordTypeDecl) {
            final ArrayList<Block> subBlocks = new ArrayList<Block>();
            List<ASTNode> children = PsiUtil.visibleChildren(myNode);
            if (plSqlSettings.ALIGNMENT_IN_COLUMN_DEF != 1)
                calculateAlignments(children);
            for (ASTNode childNode : children) {
                final Indent indent = PlSqlIndentProcessor.getChildIndent(myBlock, childNode, plSqlSettings);
                Wrap wrap = PlSqlWrapProcessor.getChildWrap(myBlock, childNode, plSqlSettings);
                subBlocks.add(
                        new PlSqlBlock(childNode, wrap, indent,
                                myInnerAlignments.get(childNode.getPsi()),
                                mySettings, plSqlSettings,
                                myInnerAlignments));
            }
            return subBlocks;
        }

        if (blockPsi instanceof PackageSpec || blockPsi instanceof PackageBody) {
            final ArrayList<Block> subBlocks = new ArrayList<Block>();
            List<ASTNode> children = PsiUtil.visibleChildren(myNode);
            if (plSqlSettings.ALIGN_DATATYPE_IN_DECL)
                calculatePackageAlignments(children);
            for (ASTNode childNode : children) {
                final Indent indent = PlSqlIndentProcessor.getChildIndent(myBlock, childNode, plSqlSettings);
                Wrap wrap = PlSqlWrapProcessor.getChildWrap(myBlock, childNode, plSqlSettings);
                subBlocks.add(
                        new PlSqlBlock(childNode, wrap, indent,
                                myInnerAlignments.get(childNode.getPsi()),
                                mySettings, plSqlSettings,
                                myInnerAlignments));
            }
            return subBlocks;
        }

        if (blockPsi.getNode().getElementType() == PlSqlElementTypes.STATEMENT_LIST) {
            final ArrayList<Block> subBlocks = new ArrayList<Block>();
            List<ASTNode> children = PsiUtil.visibleChildren(myNode);
            if (plSqlSettings.ALIGN_ASSIGNMENTS)
                calculateAlignments(children);
            for (ASTNode childNode : children) {
                final Indent indent = PlSqlIndentProcessor.getChildIndent(myBlock, childNode, plSqlSettings);
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
            final Indent indent = PlSqlIndentProcessor.getChildIndent(myBlock, childNode, plSqlSettings);
            Wrap wrap = PlSqlWrapProcessor.getChildWrap(myBlock, childNode, plSqlSettings);
            subBlocks.add(
                    new PlSqlBlock(childNode, wrap, indent,
                            myInnerAlignments.get(childNode.getPsi()),
                            mySettings, plSqlSettings,
                            myInnerAlignments));
        }
        return subBlocks;
    }

    private void calculatePackageAlignments(List<ASTNode> children) {
        try {
            List<Alignment> currentGroup = null;
            PsiElement prevChild = null;
            for (ASTNode child : children) {
                PsiElement psi = child.getPsi();
                if (psi instanceof VariableDecl) {
                    if (currentGroup == null || !checkElem(prevChild, VariableDecl.class)){
                        currentGroup = Arrays.asList(
                                Alignment.createAlignment(true),
                                Alignment.createAlignment(true),
                                Alignment.createAlignment(true),
                                Alignment.createAlignment(true));
                    }

                    VariableDecl decl = (VariableDecl) psi;
                    myInnerAlignments.put(decl.getVariableName(), currentGroup.get(0));
                    myInnerAlignments.put(decl.getTypeSpec(), currentGroup.get(1));

                    ASTNode eq = child.findChildByType(PlSqlTokenTypes.ASSIGNMENT_EQ);
                    if (eq != null) {
                        myInnerAlignments.put(eq.getPsi(), currentGroup.get(2));
                    }

                    PsiElement _const = decl.getConstant();
                    if (_const != null) {
                        myInnerAlignments.put(_const, currentGroup.get(3));
                    }

                } else if (psi instanceof VarrayCollectionDecl || psi instanceof TableCollectionDecl) {
                    if (currentGroup == null || !checkElem(prevChild, VarrayCollectionDecl.class, TableCollectionDecl.class)){
                        currentGroup = Arrays.asList(
                                Alignment.createAlignment(true),
                                Alignment.createAlignment(true),
                                Alignment.createAlignment(true),
                                Alignment.createAlignment(true));
                    }

                    TypeDeclaration decl = (TypeDeclaration) psi;
                    ASTNode is = child.findChildByType(PlSqlTokenTypes.KEYWORD_IS);
                    ASTNode of = child.findChildByType(PlSqlTokenTypes.KEYWORD_OF);
                    ASTNode _index = child.findChildByType(PlSqlTokenTypes.KEYWORD_INDEX);
                    int index = 0;
                    myInnerAlignments.put(decl.getPsiDeclName(), currentGroup.get(index++));
                    if(is != null) myInnerAlignments.put(is.getPsi(), currentGroup.get(index++));
                    if(of != null) myInnerAlignments.put(of.getPsi(), currentGroup.get(index++));
                    if(_index != null) myInnerAlignments.put(_index.getPsi(), currentGroup.get(index++));
                } else if(psi instanceof PsiComment){
                    continue;
                }

                prevChild = psi;
            }
        } catch (SyntaxTreeCorruptedException e) {
            // do nothing
        }
    }

    private boolean checkElem(PsiElement element, Class ... classes) {
        if(plSqlSettings.KEEP_ALIGNMENT_IN_GROUP && element != null){
            for(Class clazz : classes){
                if (clazz.isInstance(element)) {
                    return true;
                }
            }
        }

        return false;
    }

    private void calculateAliasNameAlignments(List<ASTNode> children) {
        try {
            List<Alignment> currentGroup = null;
            for (ASTNode child : children) {
                PsiElement psi = child.getPsi();
                if (psi instanceof SelectFieldExpr) {
                    if (currentGroup == null) {
                        currentGroup = Arrays.asList(Alignment.createAlignment(true), Alignment.createAlignment(true));
                    }

                    SelectFieldExpr column = (SelectFieldExpr) psi;
                    PsiElement alias = column.getAliasName();
                    if (alias != null) {
                        myInnerAlignments.put(column.getExpression(), currentGroup.get(0));
                        myInnerAlignments.put(alias, currentGroup.get(1));
                    }
                }
            }
        } catch (SyntaxTreeCorruptedException e) {
            // do nothing
        }
    }


    private void calculateAlignments(List<ASTNode> children) {
        try {
            PsiElement prevChild = null;
            List<Alignment> currentGroup = null;
            for (ASTNode child : children) {
                PsiElement psi = child.getPsi();
                if (psi instanceof VariableDecl) {
                    if (currentGroup == null || !checkElem(prevChild, VariableDecl.class)) {
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
                } else if (psi instanceof AssignmentStatement) {
                    if (currentGroup == null || !checkElem(prevChild, AssignmentStatement.class)) {
                        currentGroup = Arrays.asList(Alignment.createAlignment(true), Alignment.createAlignment(true), Alignment.createAlignment(true));
                    }

                    AssignmentStatement assignment = (AssignmentStatement) psi;
                    myInnerAlignments.put(assignment.getLValue(), currentGroup.get(0));
                    myInnerAlignments.put(assignment.getEQ_Sign(), currentGroup.get(1));
                    myInnerAlignments.put(assignment.getRValue(), currentGroup.get(2));
                } else if (psi instanceof RecordTypeItem) {
                    if (currentGroup == null) {
                        currentGroup = Arrays.asList(Alignment.createAlignment(true), Alignment.createAlignment(true));
                    }

                    RecordTypeItem item = (RecordTypeItem) psi;
                    myInnerAlignments.put(item.getPsiRecordItemName(), currentGroup.get(0));
                    myInnerAlignments.put(item.getTypeSpec(), currentGroup.get(1));
                } else if(psi instanceof PsiComment){
                    continue;
                }

                prevChild = psi;
            }
        } catch (SyntaxTreeCorruptedException e) {
            // do nothing
        }
    }

    private void calculateColumnDefAlignments(List<ASTNode> children, int type) {
        List<Alignment> currentGroup = new ArrayList<Alignment>();
        switch (type) {
            case 1: // Don't change
                return;
            case 3: // Type & NOT NULL
                currentGroup.add(Alignment.createAlignment(true));
            case 2: // Type
                currentGroup.add(Alignment.createAlignment(true));
                currentGroup.add(Alignment.createAlignment(true));
                break;
        }
        try {
            for (final ASTNode child : children) {
                PsiElement psi = child.getPsi();
                if (psi instanceof ColumnDefinition) {
                    ColumnDefinition column = (ColumnDefinition) psi;
                    myInnerAlignments.put(column.getPsiColumnName(), currentGroup.get(0));
                    myInnerAlignments.put(column.getTypeSpec(), currentGroup.get(1));
                    GenericConstraint constr = column.getNotNullConstraint();
                    // Collect NULL/NOT NULL only if
                    // 1. Type & NOT NULL configured
                    // 2. NULL/NOT NULL constraint goes after type immediately
                    if (constr != null && currentGroup.size() == 3) {
                        final PsiElement datatype = PsiUtil.prevVisibleSibling(constr.getNode()).getPsi();
                        if (datatype instanceof TypeSpec) {
                            myInnerAlignments.put(constr, currentGroup.get(2));
                        }
                    }
                }
            }
        } catch (SyntaxTreeCorruptedException e) {
            // do nothing
        }
    }

}
