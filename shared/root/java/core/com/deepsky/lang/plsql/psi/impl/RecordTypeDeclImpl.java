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

import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.resolver.ResolveUtils;
import com.deepsky.lang.plsql.struct.parser.ContextPath;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RecordTypeDeclImpl extends PlSqlElementBase implements RecordTypeDecl {
    
    public RecordTypeDeclImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getDeclName() {
        ASTNode node = getNode().findChildByType(PLSqlTypesAdopted.TYPE_NAME);
        return node.getText();
    }

    public String getPackageName() {
        PlSqlElement context = getUsageContext(TokenSet.create(
                PlSqlElementTypes.PACKAGE_BODY, PlSqlElementTypes.PACKAGE_SPEC)
        );

        if(context instanceof PackageBody){
            return ((PackageBody)context).getPackageName();
        } else if(context instanceof PackageSpec){
            return ((PackageSpec)context).getPackageName();
        }

        return null;
    }

    public RecordTypeItem[] getItems() {
        return this.findChildrenByClass(RecordTypeItem.class);
    }


    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitRecordTypeDecl(this);
        } else {
            super.accept(visitor);
        }
    }


    @Nullable
    public String getQuickNavigateInfo(){
        return "[Record Type] " + getDeclName();
    }



    // [Contex Management Stuff] Start -------------------------------
    CtxPath cachedCtxPath = null;
    public CtxPath getCtxPath() {
        if(cachedCtxPath != null){
            return cachedCtxPath;
        } else {
            CtxPath parent = super.getCtxPath();
            cachedCtxPath = new CtxPathImpl(
                    parent.getPath() + ResolveUtils.encodeCtx(ContextPath.RECORD_TYPE,"..$" + this.getDeclName().toLowerCase()));
        }
        return cachedCtxPath;
    }
    // [Contex Management Stuff] End ---------------------------------

/*
    public TypeItem[] getTypeItems() {
        ASTNode[] nodes = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.RECORD_ITEM));

        if(nodes == null){
            return new TypeItem[0];
        } else {
            for(ASTNode node : nodes){
                //     
            }
            //
            return new TypeItem[0];
        }
    }


    class TypeItemImpl implements TypeItem {
        String name;
        Type type;
        Expression expr;

        public TypeItemImpl(String name, Type type, Expression expr){
            this.name = name;
            this.type = type;
            this.expr = expr;
        }

        public String getName() {
            return name;
        }

        public Type getType() {
            return type;
        }

        public Expression getDefault() {
            return expr;
        }
    }
*/
}

