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
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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

package com.deepsky.lang.plsql.psi.impl.ddl;

import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.SelectFieldCommon;
import com.deepsky.lang.plsql.psi.SelectFieldExpr;
import com.deepsky.lang.plsql.psi.ddl.CreateView;
import com.deepsky.lang.plsql.psi.ddl.VColumnDefinition;
import com.deepsky.lang.plsql.psi.impl.PlSqlCompositeNameBase;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.psibased.PsiPlainViewColumnContext;
import com.deepsky.lang.plsql.struct.Type;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VColumnDefinitionImpl extends PlSqlCompositeNameBase implements VColumnDefinition {
    public VColumnDefinitionImpl(ASTNode node) {
        super(node);
    }

    @NotNull
    public CreateView getViewDefinition() {
        PsiElement psi = getParent();
        if(psi instanceof CreateView){
            return (CreateView) psi;
        }
        throw new SyntaxTreeCorruptedException();
    }


    @NotNull
    public String getColumnName() {
        return getText();
    }

//    @NotNull
//    public NameFragmentRef[] getNamePieces() {
//        return new NameFragmentRef[0];  //To change body of implemented methods use File | Settings | File Templates.
//    }

//    public int getFragmentIndex(@NotNull NameFragmentRef fragment) {
//        return 0;
//    }

    @NotNull
    public ResolveContext777 getResolveContext() throws NameNotResolvedException {
        return new PsiPlainViewColumnContext(getViewDefinition(), this);
    }

//    @NotNull
//    public VariantsProcessor777 createVariantsProcessor(PlSqlElement elem) throws NameNotResolvedException {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitVColumnDefinition(this);
        } else {
            super.accept(visitor);
        }
    }

    @Nullable
    public String getQuickNavigateInfo() {

        CreateView view = getViewDefinition();
        int pos = view.getColumnPos(getColumnName());
        if(pos >=0){
            SelectFieldCommon[] fields = view.getSelectExpr().getSelectFieldList();
            if(fields.length>=pos){
                if( fields[pos] instanceof SelectFieldExpr){
                    try {
                        Type t = ((SelectFieldExpr)fields[pos]).getExpression().getExpressionType();
                        return "[View]   " + view.getViewName().toLowerCase()
                        + "\n [Column] " + getColumnName().toLowerCase() + " "
                        + t.toString().toUpperCase();
                    } catch(Throwable e){
                    }
                }
            }

            return null;
        } else {
            // todo -- looks strange, column was not identified
            return null;
        }
    }

}
