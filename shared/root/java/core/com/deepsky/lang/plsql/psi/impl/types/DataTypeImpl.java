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

import com.deepsky.lang.plsql.psi.impl.PlSqlElementBase;
import com.deepsky.lang.plsql.psi.types.DataType;
import com.deepsky.lang.plsql.psi.utils.ASTNodeIterator;
import com.deepsky.lang.plsql.psi.resolve.ResolveHelper;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class DataTypeImpl extends PlSqlElementBase implements DataType {

    public DataTypeImpl(ASTNode astNode) {
        super(astNode);
    }

    public Type getType() {

        ASTNodeIterator iterator = new ASTNodeIterator(getNode().getFirstChildNode());

        String typeName = null;
        String length = null;
        while(iterator.hasNext()){
            ASTNode node = iterator.next();
            if(typeName == null){
                typeName = node.getText();
            } else if(node.getElementType() == PlSqlTokenTypes.NUMBER){
                //
                length = node.getText();
                node = iterator.next();
                if(node.getText().equalsIgnoreCase("char")){
                    // todo - process 'char'
                } else if(node.getText().equalsIgnoreCase("byte")){
                    // todo - process 'byte'
                }
            }
        }

        if(length != null){
            return TypeFactory.createTypeByName(typeName, Integer.parseInt(length));
        } else {
            return TypeFactory.createTypeByName(typeName);
        }
    }

    public void validate() {
        // valid always by syntax 
    }

    @NotNull
    public ResolveContext777 resolveType() throws NameNotResolvedException {
        throw new NameNotResolvedException("");
    }


    public PsiElement getTypeName(){
        return this.getFirstChild();
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitDatatype(this);
        } else {
            super.accept(visitor);
        }
    }

}
