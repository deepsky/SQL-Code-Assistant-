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

package com.deepsky.lang.plsql.completion.syntaxTreePath.generated;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generator.EOFException;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generator.InvalidLexerStateException;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generator.TreePathIterator;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;


public class ASTNodeIterator implements TreePathIterator {

    private ASTNode parent;
    private ASTNode current;

    public ASTNodeIterator(ASTNode parent) {
        this.parent = parent;
        this.current = parent.getFirstChildNode();
    }

    @Override
    public Object next() throws EOFException {
        if (current != null) {
            ASTNode out = current;
            current = getNext(current); //.getTreeNext();
            return out;
        }
        throw new EOFException();
    }

    @Override
    public Object peek() throws EOFException {
        if (current != null) {
            return current;
        }
        throw new EOFException();
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }


    private ASTNode getNext(ASTNode node) {
        ASTNode curr = node.getTreeNext();
        while (curr != null) {
            final IElementType etype = curr.getElementType();
            if (etype != PlSqlTokenTypes.WS && etype != PlSqlTokenTypes.LF && etype != TokenType.WHITE_SPACE
                    && etype != PlSqlTokenTypes.SL_COMMENT && etype != PlSqlTokenTypes.ML_COMMENT) {
                return curr;
            }

            curr = curr.getTreeNext();
        }

        return null;
    }

    @Override
    public void setState(int lexerState) throws InvalidLexerStateException {
    }

    @Override
    public int saveState() {
        return 0;
    }

    @Override
    public <T> T next(Class<T> e) throws EOFException, ClassCastException {
        if (e != ASTNode.class) {
            throw new ClassCastException();
        }
        if (current != null) {
            ASTNode out = current;
            current = getNext(current); //current.getTreeNext();
            return (T) out;
        }
        throw new EOFException();
    }

    @Override
    public <T> T peek(Class<T> e) throws EOFException, ClassCastException {
        if (e != ASTNode.class) {
            throw new ClassCastException();
        }
        if (current != null) {
            return (T) current;
        }
        throw new EOFException();
    }

}
