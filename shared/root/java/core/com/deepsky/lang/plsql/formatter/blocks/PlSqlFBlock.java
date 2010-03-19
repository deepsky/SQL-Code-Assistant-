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

package com.deepsky.lang.plsql.formatter.blocks;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.PsiErrorElement;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.diagnostic.Logger;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.formatter.PlSqlSpacingProcessor;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlSqlFBlock implements Block {

    private static final Logger log = Logger.getInstance("#PlSqlFBlock");

    private ASTNode myNode;

    private final CodeStyleSettings mySettings;

    private Alignment myAlignment;
    private Indent myIndent;
    private Wrap myWrap;
    private List<Block> mySubBlocks = null;

    public PlSqlFBlock(final ASTNode node, final Alignment alignment, final Indent indent, final Wrap wrap, final CodeStyleSettings settings) {
        myAlignment = alignment;
        myIndent = indent;
        myNode = node;
        myWrap = wrap;
        mySettings = settings;
    }

    public ASTNode getNode() {
        return myNode;
    }

    @NotNull
    public TextRange getTextRange() {
        return myNode.getTextRange();
    }

    @NotNull
    public List<Block> getSubBlocks() {
        if (mySubBlocks == null) {
            SubBlockVisitor visitor = new SubBlockVisitor(getSettings());
            visitor.visit(myNode);
            mySubBlocks = visitor.getBlocks();
        }
        return mySubBlocks;
    }

    @Nullable
    public Wrap getWrap() {
        return myWrap;
    }

    @Nullable
    public Indent getIndent() {
        return myIndent;
    }

    @Nullable
    public Alignment getAlignment() {
        return myAlignment;
    }

    @Nullable
    public Spacing getSpacing(Block child1, Block child2) {
        return new PlSqlSpacingProcessor(getNode(), ((PlSqlFBlock) child1).getNode(), ((PlSqlFBlock) child2).getNode(), mySettings).getResult();
    }

    @NotNull
    public ChildAttributes getChildAttributes(final int newChildIndex) {
        Indent indent = null;
        final IElementType blockElementType = myNode.getElementType();

        log.info("#getChildAttributes, etype: " + blockElementType);
/*
    if (blockElementType == JSElementTypes.BLOCK ||
        blockElementType == JSElementTypes.CLASS ||
        blockElementType == JSElementTypes.PACKAGE_STATEMENT ||
        blockElementType == JSElementTypes.OBJECT_LITERAL_EXPRESSION
       ) {
      indent = Indent.getNormalIndent();
    }
    else if (blockElementType == JSElementTypes.FILE ||
             blockElementType == JSElementTypes.EMBEDDED_CONTENT
            ) {
      indent = Indent.getNoneIndent();
    }
    else if (JSElementTypes.SOURCE_ELEMENTS.contains(blockElementType) ||
             blockElementType == JSElementTypes.FUNCTION_EXPRESSION
            ) {
      indent = Indent.getNoneIndent();
    }
*/
        Alignment alignment = null;
        final List<Block> subBlocks = getSubBlocks();
        for (int i = 0; i < newChildIndex; i++) {
            final Alignment childAlignment = subBlocks.get(i).getAlignment();
            if (childAlignment != null) {
                alignment = childAlignment;
                break;
            }
        }

        // in for loops, alignment is required only for items within parentheses
        if (blockElementType == PlSqlElementTypes.LOOP_STATEMENT) {
            for (int i = 0; i < newChildIndex; i++) {
//        if (((PlSqlFBlock) subBlocks.get(i)).getNode().getElementType() == PlSqlElementTypes.RPAR) {
//          alignment = null;
//          break;
//        }
            }
        }

        return new ChildAttributes(indent, alignment);
    }

    public boolean isIncomplete() {
        boolean res = isIncomplete(myNode); 
        log.info("#isIncomplete, etype: " + myNode.getElementType() + ", result: " + res);
        return res;
    }

    private boolean isIncomplete(ASTNode node) {
        ASTNode lastChild = node.getLastChildNode();
        while (lastChild != null && lastChild.getPsi() instanceof PsiWhiteSpace) {
            lastChild = lastChild.getTreePrev();
        }
        if (lastChild == null) return false;
        if (lastChild.getPsi() instanceof PsiErrorElement) return true;
        return isIncomplete(lastChild);
    }

    public CodeStyleSettings getSettings() {
        return mySettings;
    }

    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }
}

