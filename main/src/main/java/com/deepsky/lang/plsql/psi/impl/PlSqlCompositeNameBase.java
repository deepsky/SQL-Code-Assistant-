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

package com.deepsky.lang.plsql.psi.impl;

import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.psi.names.CompositeName;
import com.deepsky.lang.plsql.psi.NameFragmentRef;
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public abstract class PlSqlCompositeNameBase extends PlSqlElementBase implements CompositeName {

    public PlSqlCompositeNameBase(ASTNode astNode) {
        super(astNode);
    }


    public ResolveDescriptor resolveLight() {

        return getResolveFacade().getLLResolver().resolveGenericReference(
                getText(), getCtxPath1().getPath()
        );
    }


    @NotNull
    public NameFragmentRef[] getNamePieces() {
        ASTNode[] nodes = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.NAME_FRAGMENT));
        NameFragmentRef[] out = new NameFragmentRef[nodes != null ? nodes.length : 0];
        for (int i = 0; i < out.length; i++) {
            out[i] = (NameFragmentRef) nodes[i].getPsi();
        }

        return out;
    }

    public int getFragmentIndex(@NotNull NameFragmentRef fragment) {
        ASTNode[] nodes = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.NAME_FRAGMENT));
        if (nodes == null) {
            return -1;
        } else {
            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i].getPsi() == fragment) {
                    return i;
                }
            }

            return -1;
        }
    }

    public NameFragmentRef getFragmentByPos(int position) {
        ASTNode[] nodes = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.NAME_FRAGMENT));
        if (nodes.length > position && position >= 0) {
            return (NameFragmentRef) nodes[position].getPsi();
        }

        return null;
    }

}
