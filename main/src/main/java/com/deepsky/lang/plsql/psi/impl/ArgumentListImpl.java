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
import com.deepsky.lang.plsql.psi.Argument;
import com.deepsky.lang.plsql.psi.ArgumentList;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ArgumentListImpl extends PlSqlElementBase implements ArgumentList {

    public ArgumentListImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public Argument[] getArguments() {
        List<Argument> aa = new ArrayList<Argument>();
        ASTNode child = getNode().getFirstChildNode();
        while (child != null) {
            if (child.getElementType() == PLSqlTypesAdopted.PARAMETER_SPEC) {
                aa.add((Argument) child.getPsi());
            }
            child = child.getTreeNext();
        }

        return aa.toArray(new Argument[aa.size()]);
    }

    public int numberOfReqArguments() {
        int count = 0;
        int defaultCount = 0;
        ASTNode child = getNode().getFirstChildNode();
        while (child != null) {
            if (child.getElementType() == PLSqlTypesAdopted.PARAMETER_SPEC) {
                count++;
                Argument a = (Argument) child.getPsi();
                defaultCount = a.getDefaultExpr() != null ? defaultCount + 1 : defaultCount;
            }
            child = child.getTreeNext();
        }

        return count - defaultCount;
    }

    public Argument getArgumentByName(String var) {
        ASTNode child = getNode().getFirstChildNode();
        while (child != null) {
            if (child.getElementType() == PLSqlTypesAdopted.PARAMETER_SPEC) {
                Argument a = (Argument) child.getPsi();
                if (a.getArgumentName().equalsIgnoreCase(var)) {
                    return a;
                }
            }
            child = child.getTreeNext();
        }
        return null;
    }

    public Argument getArgumentByPos(int pos) {
        ASTNode child = getNode().getFirstChildNode();
        int count = 0;
        while (child != null) {
            if (child.getElementType() == PLSqlTypesAdopted.PARAMETER_SPEC) {
                if (count++ == pos) {
                    return (Argument) child.getPsi();
                }
            }
            child = child.getTreeNext();
        }
        return null;
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitArgumentList(this);
        } else {
            super.accept(visitor);
        }
    }

}
