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
import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.util.containers.CollectionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

        this(node, wrap, indent, alignment, plsqlSettings, customSettings, CollectionFactory.<PsiElement, Alignment>hashMap());
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
        return new PlSqlBlockGenerator(this).generateSubBlocks();
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

            Spacing spacing = new PlSqlSpacingProcessor(((PlSqlBlock)child2).getNode(), commonSettings, customSettings).getSpacing();
            return spacing != null ? spacing : PlSqlSpacingProcessorBasic.getSpacing(((PlSqlBlock)child1), ((PlSqlBlock)child2), commonSettings);
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
}
