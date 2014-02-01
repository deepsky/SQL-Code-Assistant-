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

package com.deepsky.lang.plsql.completion;

import com.deepsky.integration.plsql.PlSqlElementType;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.PsiUtil;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;

import java.util.*;

public class PlSqlElementProxyFactory {

    private static final IElementType[] update = new IElementType[]{
            PlSqlTokenTypes.KEYWORD_UPDATE
    };

    private static final IElementType[] update2 = new IElementType[]{
            PlSqlTokenTypes.KEYWORD_UPDATE, PlSqlElementTypes.TABLE_ALIAS, PlSqlTokenTypes.KEYWORD_SET
    };

    private static final IElementType[] update21 = new IElementType[]{
            PlSqlTokenTypes.KEYWORD_UPDATE, PlSqlElementTypes.TABLE_REF
    };

    private static final IElementType[] update3 = new IElementType[]{
            PlSqlTokenTypes.KEYWORD_UPDATE, PlSqlElementTypes.TABLE_ALIAS, PlSqlTokenTypes.KEYWORD_SET,
            PlSqlElementTypes.COLUMN_SPEC, PlSqlTokenTypes.EQ, PlSqlElementTypes.NUMERIC_LITERAL, PlSqlTokenTypes.COMMA
    };

    static int[] update3Branches = new int[]{2, 2, 6};

    private static final IElementType[] update4 = new IElementType[]{
            PlSqlTokenTypes.KEYWORD_UPDATE, PlSqlElementTypes.TABLE_ALIAS, PlSqlTokenTypes.KEYWORD_SET,
            PlSqlElementTypes.COLUMN_SPEC, PlSqlTokenTypes.EQ, PlSqlElementTypes.STRING_LITERAL, PlSqlTokenTypes.COMMA
    };

    static int[] update4Branches = new int[]{2, 2, 6};

    private static final IElementType[] commentTable = new IElementType[]{
            PlSqlTokenTypes.KEYWORD_COMMENT, PlSqlTokenTypes.KEYWORD_ON, PlSqlTokenTypes.KEYWORD_TABLE
    };

    private static final IElementType[] commentColumn = new IElementType[]{
            PlSqlTokenTypes.KEYWORD_COMMENT, PlSqlTokenTypes.KEYWORD_ON, PlSqlTokenTypes.KEYWORD_COLUMN
    };

    private static final IElementType[] commentColumn2 = new IElementType[]{
            PlSqlTokenTypes.KEYWORD_COMMENT, PlSqlTokenTypes.KEYWORD_ON, PlSqlTokenTypes.KEYWORD_COLUMN,
            PlSqlElementTypes.TABLE_REF, PlSqlTokenTypes.DOT
    };

    private static final IElementType[] insert0 = new IElementType[]{
            PlSqlTokenTypes.KEYWORD_INSERT
    };

    private static final IElementType[] insert = new IElementType[]{
            PlSqlTokenTypes.KEYWORD_INSERT, PlSqlTokenTypes.KEYWORD_INTO, PlSqlElementTypes.TABLE_ALIAS,
            PlSqlTokenTypes.OPEN_PAREN
    };

    private static final IElementType[] insert2 = new IElementType[]{
            PlSqlTokenTypes.KEYWORD_INSERT, PlSqlTokenTypes.KEYWORD_INTO, PlSqlElementTypes.TABLE_ALIAS,
            PlSqlTokenTypes.OPEN_PAREN, PlSqlElementTypes.COLUMN_SPEC, PlSqlTokenTypes.COMMA
    };

    static int[] insert2Branches = new int[]{3, 3, 5};

    private static final IElementType[] insert3 = new IElementType[]{
            PlSqlTokenTypes.KEYWORD_INSERT, PlSqlTokenTypes.KEYWORD_INTO, PlSqlElementTypes.TABLE_ALIAS,
            PlSqlElementTypes.COLUMN_SPEC_LIST
    };

    private static List<Composite> packed = new ArrayList<Composite>();

    static {
        packed.add(new Composite(PlSqlElementTypes.COMMENT_STMT, PlSqlElementTypes.TABLE_REF, commentTable));
        packed.add(new Composite(PlSqlElementTypes.COMMENT_STMT, PlSqlElementTypes.TABLE_REF, commentColumn));
        packed.add(new Composite(PlSqlElementTypes.COMMENT_STMT, PlSqlElementTypes.COLUMN_NAME_REF, commentColumn2));
        packed.add(new Composite(PlSqlElementTypes.UPDATE_COMMAND, PlSqlElementTypes.TABLE_REF, update));
        packed.add(new Composite(PlSqlElementTypes.UPDATE_COMMAND, PlSqlElementTypes.COLUMN_NAME_REF, update2));
        packed.add(new Composite(PlSqlElementTypes.UPDATE_COMMAND, PlSqlTokenTypes.KEYWORD_SET, update21));
        packed.add(new Composite(PlSqlElementTypes.UPDATE_COMMAND, PlSqlElementTypes.COLUMN_NAME_REF, update3, update3Branches));
        packed.add(new Composite(PlSqlElementTypes.UPDATE_COMMAND, PlSqlElementTypes.COLUMN_NAME_REF, update4, update4Branches));

        packed.add(new Composite(PlSqlElementTypes.INSERT_COMMAND, PlSqlTokenTypes.KEYWORD_INTO, insert0));
        packed.add(new Composite(PlSqlElementTypes.INSERT_COMMAND, PlSqlElementTypes.COLUMN_NAME_REF, insert));
        packed.add(new Composite(PlSqlElementTypes.INSERT_COMMAND, PlSqlElementTypes.COLUMN_NAME_REF, insert2, insert2Branches));
        packed.add(new Composite(PlSqlElementTypes.INSERT_COMMAND,
                new IElementType[]{PlSqlTokenTypes.KEYWORD_SELECT, PlSqlTokenTypes.KEYWORD_VALUES}, insert3));
    }

    static private class Composite {
        public PlSqlElementType elementType;
        public IElementType[] targetElement;
        public IElementType[] chain;
        int index = 0;
        List<ASTNode> nodes = new ArrayList<ASTNode>();
        int[] branches;

        private Composite(PlSqlElementType elementType, IElementType targetElement, IElementType[] chain, int[] branches) {
            this.targetElement = new IElementType[]{targetElement};
            this.elementType = elementType;
            this.chain = chain;
            this.index = chain.length - 1;
            this.branches = branches;
        }

        private Composite(PlSqlElementType elementType, IElementType[] targetElement, IElementType[] chain, int[] branches) {
            this.targetElement = targetElement;
            this.elementType = elementType;
            this.chain = chain;
            this.index = chain.length - 1;
            this.branches = branches;
        }

        private Composite(PlSqlElementType elementType, IElementType targetElement, IElementType[] chain) {
            this(elementType, targetElement, chain, new int[0]);
        }

        private Composite(PlSqlElementType elementType, IElementType[] targetElement, IElementType[] chain) {
            this(elementType, targetElement, chain, new int[0]);
        }

        boolean consumeNode(ASTNode node) {
            if (branches.length > 0 && index == branches[0]) {
                if (chain[branches[1]] != node.getElementType()) {
                    if (chain[branches[2]] != node.getElementType()) {
                        return false;
                    } else {
                        index = branches[2];
                    }
                } else {
                    index = branches[1];
                }
            } else if (chain[index] != node.getElementType()) {
                return false;
            }
            nodes.add(0, node);
            index--;
            return true;
        }

        public boolean isCompleted() {
            return index == -1;
        }

        public ASTNode[] getNodes() {
            return nodes.toArray(new ASTNode[nodes.size()]);
        }

        public void reset() {
            this.index = chain.length - 1;
        }
    }

    private Composite etChain;
    private PlSqlElementProxyImpl proxy;

    private PlSqlElementProxyFactory(Composite etChain) {
        this.etChain = etChain;
    }

    public boolean addNode(ASTNode node) throws NoOptionAvailableException {
        if (!etChain.consumeNode(node)) {
            throw new NoOptionAvailableException();
        }

        if (etChain.isCompleted()) {
            proxy = new PlSqlElementProxyImpl(etChain);
            return true;
        }
        return false;
    }

    public PlSqlElementProxy getProxy() throws NoOptionAvailableException {
        if (proxy == null) {
            throw new NoOptionAvailableException();
        }
        return proxy;
    }


    static int identifyChain(ASTNode start, int pos) throws ChainNotFoundException {
        for (int i = pos; i < packed.size(); i++) {
            final Composite c = packed.get(i);
            if (c.chain[c.chain.length - 1] == start.getElementType()) {
                return i;
            }
        }
        throw new ChainNotFoundException();
    }


    private class PlSqlElementProxyImpl implements PlSqlElementProxy {
        Composite c;

        private PlSqlElementProxyImpl(Composite c) {
            this.c = c;
        }

        public PlSqlElementType getElementType() {
            return c.elementType;
        }

        public Set<IElementType> getTargets() {
            return new HashSet<IElementType>(Arrays.asList(c.targetElement));
        }

        public ASTNode findNode(PlSqlElementType type) {
            for (ASTNode n : c.getNodes()) {
                if (n.getElementType() == type) {
                    return n;
                }
            }
            return null;
        }
    }

    public static interface RestoredNodeProcessor {
        void processComment(PlSqlElementProxy elementProxy);

        void processUpdateCommand(PlSqlElementProxy elementProxy);

        void processInsertCommand(PlSqlElementProxy elementProxy);

        void processFileLevelText(ASTNode node);
    }


    public static void identifyNode(ASTNode node, RestoredNodeProcessor processor) {
        ASTNode prev = prevVisibleNode(node);
        int pos = 0;
        do {
            PlSqlElementProxyFactory it = null;
            try {
                while (prev != null) {
                    ASTNode _prev = prev;
                    if (prev.getElementType() == PlSqlElementTypes.ERROR_TOKEN_A) {
                        _prev = prev.getFirstChildNode();
                    }
                    if (it == null) {
                        pos = identifyChain(_prev, pos);
                        Composite c = packed.get(pos);
                        c.reset();
                        it = new PlSqlElementProxyFactory(c);
                    }

                    if (it.addNode(_prev)) {
                        final PlSqlElementProxy proxy = it.getProxy();
                        if (proxy.getElementType() == PlSqlElementTypes.COMMENT_STMT) {
                            processor.processComment(proxy);
                        } else if (proxy.getElementType() == PlSqlElementTypes.UPDATE_COMMAND) {
                            processor.processUpdateCommand(proxy);
                        } else if (proxy.getElementType() == PlSqlElementTypes.INSERT_COMMAND) {
                            processor.processInsertCommand(proxy);
                        }

                        break;
                    }
                    prev = prevVisibleNode(prev);
                }
                return;
            } catch (NoOptionAvailableException e) {
                // No variants
                pos++;
                prev = prevVisibleNode(node);
            } catch (ChainNotFoundException e) {
                // No registered chain found
                if (node.getTreeParent().getElementType() == PlSqlTokenTypes.FILE) {
                    // Last chance resort - file level element
                    final ASTNode prev1 = PsiUtil.prevVisibleSibling(node);
                    if (prev1 == null) {
                        processor.processFileLevelText(node);
                    } else if(prev1.getElementType() != PlSqlElementTypes.ERROR_TOKEN_A) {
                        final ASTNode last = prev1.getLastChildNode();
                        if (last == null || last.getElementType() != PlSqlElementTypes.ERROR_TOKEN_A) {
                            processor.processFileLevelText(node);
                        }
                    }
                }
                break;
            }
        } while (true);
    }


    private static ASTNode prevVisibleNode(final ASTNode node) {
        if (node != null) {
            final ASTNode prev = PsiUtil.prevVisibleSibling(node);
            if (prev != null) {
                return prev;
            }
            if (node.getTreeParent().getElementType() != PlSqlTokenTypes.FILE) {
                return prevVisibleNode(node.getTreeParent());
            }
        }
        return null;
    }

    private static class ChainNotFoundException extends Exception {
    }

    public class NoOptionAvailableException extends Exception {
    }

}
