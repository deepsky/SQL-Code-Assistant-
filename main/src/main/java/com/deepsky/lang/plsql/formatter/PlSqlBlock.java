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

import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.formatter.processors.PlSqlSpacingProcessor;
import com.deepsky.lang.plsql.formatter.processors.PlSqlSpacingProcessorBasic;
import com.deepsky.lang.plsql.formatter.settings.PlSqlCodeStyleSettings;
import com.deepsky.lang.plsql.psi.utils.PlSqlUtil;
import com.deepsky.lang.PsiUtil;
import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlSqlBlock extends AbstractBlock {

    Indent indent;
    CommonCodeStyleSettings commonSettings;
    PlSqlCodeStyleSettings customSettings;
    Map<PsiElement, Alignment> innerAlignments;

    public PlSqlBlock(
            @NotNull ASTNode node,
            @Nullable Wrap wrap,
            Indent indent,
            @Nullable Alignment alignment,
            CommonCodeStyleSettings plsqlSettings,
            PlSqlCodeStyleSettings customSettings) {

        this(node, wrap, indent, alignment, plsqlSettings, customSettings, ContainerUtil.<PsiElement, Alignment>newHashMap());//<PsiElement, Alignment>hashMap());
    }

    public PlSqlBlock(
            @NotNull ASTNode node,
            @Nullable Wrap wrap,
            Indent indent,
            @Nullable Alignment alignment,
            CommonCodeStyleSettings plsqlSettings,
            PlSqlCodeStyleSettings customSettings,
            @NotNull Map<PsiElement, Alignment> innerAlignments) {

        super(node, wrap, alignment);
        this.indent = indent;
        this.commonSettings = plsqlSettings;
        this.customSettings = customSettings;
        this.innerAlignments = innerAlignments;
    }

    @Override
    public Indent getIndent() {
        return indent;
    }

    @Override
    protected List<Block> buildChildren() {
        try {
            return new PlSqlBlockGenerator(this).generateSubBlocks();
        } catch (SyntaxTreeCorruptedException e){
            e.printStackTrace();
            return new ArrayList<Block>();
        }
    }

    /**
     * Returns spacing between neighbour elements
     *
     * @param child1 left element
     * @param child2 right element
     * @return
     */
    @Nullable
    public Spacing getSpacing(Block child1, Block child2) {
        if ((child1 instanceof PlSqlBlock) && (child2 instanceof PlSqlBlock)) {
            if (((PlSqlBlock)child1).getNode() == ((PlSqlBlock)child2).getNode()) {
                return Spacing.getReadOnlySpacing();
            }

            Spacing spacing = new PlSqlSpacingProcessor(
                    ((PlSqlBlock)child2).getNode(), customSettings
            ).getSpacing();
            return spacing != null ?
                    spacing :
                    PlSqlSpacingProcessorBasic.getSpacing(((PlSqlBlock) child1), ((PlSqlBlock) child2), customSettings);
        }
        return null;
    }

    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }

    public CommonCodeStyleSettings getSettings() {
        return commonSettings;
    }

    public PlSqlCodeStyleSettings getPlSqlSettings() {
        return customSettings;
    }

    public Map<PsiElement, Alignment> getInnerAlignments() {
        return innerAlignments;
    }

    private Boolean myIncomplete2 = null;
    @Override
    public boolean isIncomplete() {
        if (myIncomplete2 == null) {
            myIncomplete2 = isIncomplete(getNode());
        }
        return myIncomplete2;
    }


    @Override
    @NotNull
    public ChildAttributes getChildAttributes(final int newChildIndex) {
        if(getNode().getPsi() instanceof PlSqlFile){
            return new ChildAttributes(Indent.getNoneIndent(), null);
        }

        return new ChildAttributes(getChildIndent(getNode(), newChildIndex), null);
    }
    
    
    private static boolean isIncomplete(ASTNode node){
        return !PlSqlUtil.iterateOver(node, new PlSqlUtil.ASTNodeProcessorBreakable() {
            public boolean process(ASTNode node) {
                return node.getElementType() != PlSqlElementTypes.ERROR_TOKEN_A;
            }
        });
    }


    private static Indent getChildIndent(final ASTNode parent, final int newChildIndex) {
        
        final IElementType targetType = parent.getElementType();
        if (targetType == PlSqlElementTypes.COLUMN_DEF) return Indent.getNormalIndent();
        if (targetType == PlSqlElementTypes.PK_SPEC) return Indent.getContinuationWithoutFirstIndent();
        if (targetType == PlSqlElementTypes.TABLE_DEF) return Indent.getNormalIndent();
        //if (targetType == PlSqlElementTypes.STATEMENT_LIST) return Indent.getNormalIndent();
        if (targetType == PlSqlElementTypes.IF_STATEMENT) return Indent.getNormalIndent();
        if (targetType == PlSqlElementTypes.ELSE_STATEMENT) return Indent.getNormalIndent();
        if (targetType == PlSqlElementTypes.SELECT_EXPRESSION) return Indent.getNormalIndent();
        if (targetType == PlSqlElementTypes.SELECT_EXPRESSION_UNION) return Indent.getNormalIndent();
        if (targetType == PlSqlElementTypes.WHERE_CONDITION) return Indent.getNormalIndent();
        if (targetType == PlSqlElementTypes.TABLE_REFERENCE_LIST_FROM) return Indent.getNormalIndent();
        if (targetType == PlSqlElementTypes.ORDER_CLAUSE) return Indent.getNormalIndent();
        if (targetType == PlSqlElementTypes.IN_CONDITION) return Indent.getNormalIndent();
        if (targetType == PlSqlElementTypes.SIMPLE_UPDATE_COMMAND) return Indent.getNormalIndent();
        if (targetType == PlSqlElementTypes.SUBQUERY) return Indent.getNormalIndent();
        if (targetType == PlSqlElementTypes.LOOP_STATEMENT) return Indent.getNormalIndent();

        if (targetType == PlSqlElementTypes.FUNCTION_BODY){
            ASTNode target = PsiUtil.getVisibleChildByPos(parent, newChildIndex);
            return getChildIndent(target, -1);
            // return Indent.getNoneIndent(); //Indent.getNormalIndent();
        }
        if (targetType == PlSqlElementTypes.PROCEDURE_BODY) {
            ASTNode target = PsiUtil.getVisibleChildByPos(parent, newChildIndex);
            return getChildIndent(target, -1);
            // return Indent.getNoneIndent(); //Indent.getNormalIndent();
        }
        if (targetType == PlSqlElementTypes.FUNCTION_SPEC) {
            ASTNode target = PsiUtil.getVisibleChildByPos(parent, newChildIndex);
            return getChildIndent(target, -1);
            // return Indent.getNoneIndent(); //Indent.getNormalIndent();
        }
        if (targetType == PlSqlElementTypes.PROCEDURE_SPEC) {
            ASTNode target = PsiUtil.getVisibleChildByPos(parent, newChildIndex);
            return getChildIndent(target, -1);
            // return Indent.getNoneIndent(); //Indent.getNormalIndent();
        }
        if (targetType == PlSqlElementTypes.DECLARE_LIST) return Indent.getNormalIndent();
        if (targetType == PlSqlElementTypes.ARGUMENT_LIST) return Indent.getNormalIndent();
        if (targetType == PlSqlElementTypes.DATATYPE) return Indent.getNormalIndent();

        if (targetType == PlSqlElementTypes.EXCEPTION_SECTION) return Indent.getNormalIndent();
        if (targetType == PlSqlElementTypes.EXCEPTION_HANDLER) return Indent.getNormalIndent();

        if (targetType == PlSqlElementTypes.PLSQL_BLOCK) {
            if(newChildIndex != -1){
                ASTNode target = PsiUtil.getVisibleChildByPos(parent, newChildIndex);
                if(target.getElementType() == PlSqlTokenTypes.KEYWORD_BEGIN){
                    return Indent.getNoneIndent();
                }

            }
            return Indent.getNormalIndent(false);
        }

        if (targetType == PlSqlElementTypes.PACKAGE_INIT_SECTION){
            return Indent.getNormalIndent();
        }

        if (targetType == PlSqlElementTypes.PACKAGE_BODY || targetType == PlSqlElementTypes.PACKAGE_SPEC){
            List<ASTNode> children = PsiUtil.visibleChildren(parent);
            if(children.size() > newChildIndex){
                IElementType ie = children.get(newChildIndex).getElementType();
                if(ie == PlSqlTokenTypes.KEYWORD_AS || ie == PlSqlTokenTypes.KEYWORD_IS){
                    return Indent.getNoneIndent();
                }
            }
            return Indent.getNormalIndent();
        }

        // todo -- handle more cases
        return Indent.getNoneIndent();
    }


    protected boolean isAfter(final int newChildIndex, final IElementType[] elementTypes) {
        if (newChildIndex == 0) return false;
        final Block previousBlock = getSubBlocks().get(newChildIndex - 1);
        if (!(previousBlock instanceof AbstractBlock)) return false;
        final IElementType previousElementType = ((AbstractBlock)previousBlock).getNode().getElementType();
        for (IElementType elementType : elementTypes) {
            if (previousElementType == elementType) return true;
        }
        return false;
    }

}
