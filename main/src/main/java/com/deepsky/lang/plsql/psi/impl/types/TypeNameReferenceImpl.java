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

package com.deepsky.lang.plsql.psi.impl.types;

import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.impl.PlSqlCompositeNameBase;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;


public class TypeNameReferenceImpl extends PlSqlCompositeNameBase implements TypeNameReference {

    public TypeNameReferenceImpl(ASTNode astNode) {
        super(astNode);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitTypeNameReference(this);
        } else {
            super.accept(visitor);
        }
    }


    public Type getType() {
        ASTNode[] nodes = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.NAME_FRAGMENT));
        if (nodes == null || nodes.length < 1 || nodes.length > 2) {
            throw new SyntaxTreeCorruptedException();
        } else {
            int len = nodes.length;
            String typeName = (len == 1) ? nodes[0].getText() : nodes[len - 1].getText();
            boolean isValid = typeName.matches("[a-zA-Z0-9_\\$]+");
            if (!isValid) {
                throw new ValidationException("Type name not valid: " + typeName);
            }

            if (nodes.length == 1) {
                return new UserDefinedType(null, typeName);
            } else {
                return new UserDefinedType(nodes[len - 2].getText(), typeName);
            }
        }
    }

    public boolean isTypeValid() {
        // todo -- implement me
        return true;
    }

}
