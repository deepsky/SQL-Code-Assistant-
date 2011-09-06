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
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.SequenceExpr;
import com.deepsky.lang.plsql.psi.ref.SequenceRef;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class SequenceExprImpl extends PlSqlElementBase implements SequenceExpr {

    public SequenceExprImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public Type getExpressionType() {
        return TypeFactory.createTypeById(Type.INTEGER);
    }

//    @NotNull
//    public PlSqlElement getSequenceName() {
//        ASTNode[] nodes = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.SEQUENCE_REF));
//        if (nodes.length != 2) {
//            throw new SyntaxTreeCorruptedException();
//        }
//
//        return (PlSqlElement) nodes[0].getPsi();
//    }

    @NotNull
    public SequenceRef getSequence() {
        ASTNode node = getNode().findChildByType(PLSqlTypesAdopted.SEQUENCE_REF);
        if (node == null) {
            throw new SyntaxTreeCorruptedException();
        }

        return (SequenceRef) node.getPsi();
    }

    public String getMethod() {
        return null;
    }

/*
    @NotNull
    public ResolveContext777 getResolveContext() throws NameNotResolvedException {
        ASTNode[] nodes = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.NAME_FRAGMENT));

        if(nodes.length != 2){
            throw new SyntaxTreeCorruptedException();
        }

        DbObject[] objects = ResolveHelper.resolve_Sequence(getProject(), nodes[0].getText());
        if( objects.length != 0){
            return new SequenceContext(objects[0].getName());
        } else {
            throw new NameNotResolvedException("Sequence not found: " + nodes[0].getText());
        }
    }
*/

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitSequenceExpr(this);
        } else {
            super.accept(visitor);
        }
    }

}
