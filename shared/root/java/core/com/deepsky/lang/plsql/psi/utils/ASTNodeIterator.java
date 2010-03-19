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

package com.deepsky.lang.plsql.psi.utils;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.deepsky.lang.common.PlSqlTokenTypes;

public class ASTNodeIterator {

    ASTNode first;
    ASTNode cur;
    public ASTNodeIterator(ASTNode first){
        this.first = first;
        this.cur = first;
        locate();
    }

    public boolean hasNext() {
        return cur != null;
    }

    public ASTNode next() {
        ASTNode node = cur;
        cur = cur.getTreeNext();
        locate();
        return node;
    }

    public ASTNode peek() {
        return cur;
    }

    public void remove() {
    }

    ASTNode locate() {
        if (cur == null) {
            return null;
        } else {
            while (cur != null) {
                IElementType itype = cur.getElementType();
                if (itype == TokenType.WHITE_SPACE || itype == PlSqlTokenTypes.ML_COMMENT
                    || itype == PlSqlTokenTypes.SL_COMMENT || itype == PlSqlTokenTypes.LF) {
                    // skip
                    cur = cur.getTreeNext();
                } else {
                    break;
                }
            }

            return cur;
        }
    }

}
