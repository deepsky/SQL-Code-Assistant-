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

import com.deepsky.lang.common.PlSqlLanguage;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.formatter2.settings.PlSqlCodeStyleSettings;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.types.DataType;
import com.deepsky.lang.plsql.psi.types.TypeSpec;
import com.intellij.formatting.Spacing;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.formatter.FormatterUtil;
import com.intellij.psi.impl.source.SourceTreeToPsiMap;
import com.intellij.psi.impl.source.tree.CompositeElement;
import org.jetbrains.annotations.Nullable;

public class PlSqlSpacingProcessor extends PlSqlElementVisitor {
    private PsiElement myParent;
    private final CommonCodeStyleSettings commonSettings;

    private Spacing myResult;
    private ASTNode leftChild;
    private ASTNode rightChild;
    private PlSqlCodeStyleSettings plSqlCodeStyleSettings;

    public PlSqlSpacingProcessor(ASTNode node, CommonCodeStyleSettings settings, PlSqlCodeStyleSettings plSqlSettings) {
        commonSettings = settings;
        plSqlCodeStyleSettings = plSqlSettings;

        _init(node);

        if (leftChild == null || rightChild == null) {
            return;
        }

        PsiElement psi1 = leftChild.getPsi();
        PsiElement psi2 = rightChild.getPsi();
        if (psi1 == null || psi2 == null) return;
        if (psi1.getLanguage() != Language.findInstance(PlSqlLanguage.class)
                || psi2.getLanguage() != Language.findInstance(PlSqlLanguage.class)) {
            return;
        }

        ASTNode prev = getPrevElementType(rightChild);
        if (prev != null && prev.getElementType() == PlSqlTokenTypes.LF) {
            prev = getPrevElementType(prev);
        }

        if (rightChild != null && commonSettings.KEEP_FIRST_COLUMN_COMMENT
                && PlSqlTokenTypes.COMMENTS.contains(rightChild.getElementType())) {
            return;
        }

        if (prev != null && prev.getElementType() == PlSqlTokenTypes.SL_COMMENT) {
            myResult = Spacing.createSpacing(0, Integer.MAX_VALUE, 1, commonSettings.KEEP_LINE_BREAKS, commonSettings.KEEP_BLANK_LINES_IN_CODE);
/*
//            myResult = Spacing.createSpacing(0, Integer.MAX_VALUE, 0, commonSettings.KEEP_LINE_BREAKS, commonSettings.KEEP_BLANK_LINES_IN_CODE);
            myResult = Spacing.createSpacing(0, Integer.MAX_VALUE, 0, false, 1);
*/
            return;
        }

        if (myParent instanceof PlSqlElement) {
            ((PlSqlElement) myParent).accept(this);
        }
    }

    private void _init(final ASTNode child) {
        if (child != null) {
            ASTNode treePrev = child.getTreePrev();
            while (treePrev != null && isWhiteSpace(treePrev)) {
                treePrev = treePrev.getTreePrev();
            }
            if (treePrev == null) {
                _init(child.getTreeParent());
            } else {
                rightChild = child;
                leftChild = treePrev;
                final CompositeElement parent = (CompositeElement) treePrev.getTreeParent();
                myParent = SourceTreeToPsiMap.treeElementToPsi(parent);
            }
        }
    }

    @Nullable
    static ASTNode getPrevElementType(final ASTNode child) {
        return FormatterUtil.getPreviousNonWhitespaceLeaf(child);
    }

    static boolean isWhiteSpace(final ASTNode node) {
        return node != null && (node.getPsi() instanceof PsiWhiteSpace || node.getTextLength() == 0);
    }

    public Spacing getSpacing() {
        return myResult;
    }

    private void createSpaceInCode(final boolean space) {
        createSpaceProperty(space, commonSettings.KEEP_BLANK_LINES_IN_CODE);
    }

    private void createSpaceProperty(boolean space, int keepBlankLines) {
        createSpaceProperty(space, commonSettings.KEEP_LINE_BREAKS, keepBlankLines);
    }

    private void createSpaceProperty(boolean space, boolean keepLineBreaks, final int keepBlankLines) {
        myResult = Spacing.createSpacing(space ? 1 : 0, space ? 1 : 0, 0, keepLineBreaks, keepBlankLines);
    }

    private void createSpaceProperty(int spaces, boolean keepLineBreaks, final int keepBlankLines) {
        myResult = Spacing.createSpacing(spaces, spaces, 0, keepLineBreaks, keepBlankLines);
    }

    public void visitCallArgumentList(CallArgumentList node) {
        if (leftChild.getElementType() == PlSqlTokenTypes.OPEN_PAREN
                || rightChild.getElementType() == PlSqlTokenTypes.CLOSE_PAREN) {
            createSpaceInCode(commonSettings.SPACE_WITHIN_BRACKETS);
        }
    }

    public void visitDatatype(DataType dataType) {
        if (rightChild.getElementType() == PlSqlTokenTypes.OPEN_PAREN) {
            createSpaceInCode(plSqlCodeStyleSettings.SPACE_BEFORE_OPEN_PAREN_IN_DATATYPE);
        } else if (leftChild.getElementType() == PlSqlTokenTypes.OPEN_PAREN
                || rightChild.getElementType() == PlSqlTokenTypes.CLOSE_PAREN) {
            createSpaceProperty(0, false, commonSettings.KEEP_BLANK_LINES_IN_CODE);
        }
    }


    public void visitVariableDecl(VariableDecl node) {
        if (leftChild.getElementType() == PlSqlElementTypes.VARIABLE_NAME
                && rightChild.getPsi() instanceof TypeSpec) {

            int len = leftChild.getTextLength();
            int spaces = len % (plSqlCodeStyleSettings.TAB_SIZE * 3);
            spaces = plSqlCodeStyleSettings.TAB_SIZE * 3 - spaces;
            createSpaceProperty(spaces, false, commonSettings.KEEP_BLANK_LINES_IN_CODE);
        } else {
            createSpaceProperty(1, false, commonSettings.KEEP_BLANK_LINES_IN_CODE);
        }
    }

    public void visitColumnDefinition(ColumnDefinition definition) {
        if (leftChild.getElementType() == PlSqlElementTypes.COLUMN_NAME_DDL
                && rightChild.getPsi() instanceof TypeSpec) {

            int len = leftChild.getTextLength();
            int spaces = len % (plSqlCodeStyleSettings.TAB_SIZE * 3);
            spaces = plSqlCodeStyleSettings.TAB_SIZE * 3 - spaces;
            createSpaceProperty(spaces, false, commonSettings.KEEP_BLANK_LINES_IN_CODE);
        } else {
            createSpaceProperty(1, false, commonSettings.KEEP_BLANK_LINES_IN_CODE);
        }
    }

    public void visitColumnFKSpec(ColumnFKSpec columnFKSpec) {
        createSpaceProperty(1, false, commonSettings.KEEP_BLANK_LINES_IN_CODE);
    }

    public void visitColumnPKSpec(ColumnPKSpec columnPKSpec) {
        createSpaceProperty(1, false, commonSettings.KEEP_BLANK_LINES_IN_CODE);
    }

    public void visitColumnCheckConstraint(ColumnCheckConstraint checkConstraint) {
        createSpaceProperty(1, false, commonSettings.KEEP_BLANK_LINES_IN_CODE);
    }

    public void visitColumnNotNullConstraint(ColumnNotNullConstraint nullConstraint) {
        createSpaceProperty(1, false, commonSettings.KEEP_BLANK_LINES_IN_CODE);
    }

}
