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

package com.deepsky.lang.lexer;

import com.deepsky.generated.plsql.SymbolTable;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.intellij.lang.ASTNode;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LexerPosition;
import com.intellij.lexer.LexerState;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class PlSqlHighlightLexer extends Lexer {

    private static final Logger log = Logger.getInstance("#PlSqlHighlightLexer");

    int offset; // tracks position of the lexer
    int size;
    char[] chars;
    CharSequence charSequence;
    ASTNode root;
    ASTNode currentNode;

    private void startInternal(char[] chars, int startOffset, int endOffset) {
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        root = generator.parse(String.valueOf(chars, startOffset, endOffset - startOffset));
        currentNode = findFirstLeaf(root);
    }

    private ASTNode findFirstLeaf(@NotNull ASTNode root) {
        ASTNode first = root.getFirstChildNode();
        if (first == null) {
            return root;
        }

        while (first.getFirstChildNode() != null) {
            first = first.getFirstChildNode();
        }
        return first;
    }

    private ASTNode gotoNextNode(ASTNode node) {
        if (node == null) {
            return null;
        }
        ASTNode next = node.getTreeNext();
        if (next == null) {
            ASTNode parent = node.getTreeParent();
            if (parent != null) {
                return gotoNextNode(parent);
            } else {
                return null;
            }
        }

        return findFirstLeaf(next);
    }

    private void start(char[] chars, int i, int i1) {
        this.chars = chars;
        this.offset = i;
        this.size = i1;
        startInternal(chars, i, i1);
    }

    public void start(CharSequence charSequence, int i, int i1, int i2) {
        this.charSequence = charSequence;

        String rt = charSequence.toString();
        this.chars = rt.toCharArray();
        this.offset = i;
        this.size = i1;
        startInternal(chars, i, i1);
    }

    public int getState() {
        return 0;
    }

    public java.lang.CharSequence getTokenSequence() {
        return currentNode != null ? currentNode.getText() : null;
    }

    public java.lang.String getTokenText() {
        return currentNode != null ? currentNode.getText() : null;
    }


    public IElementType getTokenType() {
        if (currentNode == null) {
            return null;
        }
        final IElementType type = currentNode.getElementType();
        final ASTNode parent = currentNode.getTreeParent();
        if (type == PlSqlTokenTypes.IDENTIFIER) {
            return analyzeParentType(currentNode);
        } else if (type == PlSqlTokenTypes.QUOTED_STR) {
            return parent.getElementType();
        } else if (type == PlSqlTokenTypes.KEYWORD_COUNT) {
            if(parent.getElementType() == PlSqlElementTypes.COUNT_FUNC){
                return PlSqlElementTypes.COUNT_FUNC;
            }
        } else if (type == PlSqlTokenTypes.NUMBER) {
            return PlSqlElementTypes.NUMERIC_LITERAL; //parent.getElementType();
        } else if (type == PlSqlTokenTypes.KEYWORD_END) {
            return type;
        } else {
            if (SymbolTable.get(currentNode.getText()) != null) {
                //
                if (!parent.getTextRange().equals(currentNode.getTextRange())) {
                    final IElementType parentType = parent.getElementType();
                    if (parentType == PlSqlElementTypes.DATATYPE) {
                        return parentType;
                    } else if (parentType == PlSqlElementTypes.SQLPLUS_SPOOL) {
//                        if(currentNode.getTreePrev() == null || currentNode.getTreePrev().getElementType() == PlSqlTokenTypes.LF)
                            return parentType;
                    } else if (parentType == PlSqlElementTypes.SQLPLUS_EXIT) {
//                        if(currentNode.getTreePrev() == null || currentNode.getTreePrev().getElementType() == PlSqlTokenTypes.LF)
                        return parentType;
                    } else if (parentType == PlSqlElementTypes.SQLPLUS_SET) {
//                        if(currentNode.getTreePrev() == null || currentNode.getTreePrev().getElementType() == PlSqlTokenTypes.LF)
                        return parentType;
                    }
                } else {
                    return analyzeParentType(parent);
                }
            }
        }
        return type;
    }

    private IElementType analyzeParentType(ASTNode node) {

        final IElementType type = node.getElementType();
        final IElementType parentType = node.getTreeParent().getElementType();
        if (parentType == PlSqlElementTypes.NAME_FRAGMENT) {
            return node.getTreeParent().getTreeParent().getElementType();
        } else if (parentType == PlSqlElementTypes.CONSUMED_TILL_EOL) {
            return type;
        }
        return parentType;
    }

    private IElementType analyze3ParentType(ASTNode node) {
        final IElementType type = node.getElementType();
        if (type == PlSqlElementTypes.NAME_FRAGMENT) {
            return node.getTreeParent().getElementType();
        }
        return type;
    }

    public int getTokenStart() {
        return currentNode != null ? currentNode.getTextRange().getStartOffset() : 0;
    }

    public int getTokenEnd() {
        return currentNode != null ? currentNode.getTextRange().getEndOffset() : 0;
    }

    public void advance() {
        currentNode = gotoNextNode(currentNode);
    }


    public LexerPosition getCurrentPosition() {
        return new LexerPositionImpl(offset, new LexerStateImpl());
    }

    public void restore(LexerPosition lexerPosition) {
        if (lexerPosition != null) {
            start(chars, lexerPosition.getOffset(), size);
        }
    }

    public CharSequence getBufferSequence() {
        return this.charSequence;
    }

    public int getBufferEnd() {
        return size; //offset + size;
    }


    class LexerStateImpl implements LexerState {
        public short intern() {
            return 0;
        }
    }

    class LexerPositionImpl implements LexerPosition {

        int offset;
        LexerState state;

        public LexerPositionImpl(int offset, LexerState state) {
            this.offset = offset;
            this.state = state;
        }

        public int getOffset() {
            return offset;
        }

        public int getState() {
            // todo
            return 0;
        }
    }

}

