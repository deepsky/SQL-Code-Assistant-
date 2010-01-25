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

package com.deepsky.lang.plsql.psi.impl;

import com.deepsky.database.ora.desc.VariableDescriptorImpl;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.CreateTrigger;
import com.deepsky.lang.plsql.psi.resolve.ASTTreeProcessor;
import com.deepsky.lang.plsql.psi.resolve.PackageTriggerHandler;
import com.deepsky.lang.plsql.psi.types.TypeSpec;
import com.deepsky.lang.plsql.psi.utils.ASTNodeIterator;
import com.deepsky.lang.plsql.struct.PackageDescriptor;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.VariableDescriptor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class VariableDeclImpl extends PlSqlElementBase implements VariableDecl {

    public VariableDeclImpl(ASTNode astNode) {
        super(astNode);
    }

//    public String getVariableName() {
//        PsiElement e = this.findChildByType(PLSqlTypesAdopted.VARIABLE_NAME);
//        if(e != null){
//            return e.getText();
//        } else {
//            throw new SyntaxTreeCorruptedException();
//        }
//    }

    public String getQuickNavigateInfo(){
        StringBuilder out = new StringBuilder();
        ASTNode child = getNode().getFirstChildNode();
        ASTNodeIterator iterator = new ASTNodeIterator(child);
        while(iterator.hasNext() && !iterator.peek().getText().equals(";")){
            if(out.length() > 0){
                out.append(" ");
            }
            out.append(iterator.next().getText());
        }

        return out.toString();
    }
    
    public Type getType() {
        return getTypeSpec().getType();
    }

    public TypeSpec getTypeSpec(){
        ASTNode type = getNode().findChildByType(PlSqlElementTypes.TYPES);
        if(type != null){
            return (TypeSpec)type.getPsi();
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }

    public boolean isConstant() {
        // todo --
        return false;
    }

    public boolean isNotNull() {
        // todo --
        return false;
    }

    public boolean isBeingAssigned() {
        // todo --
        return false;
    }

    public boolean isDefault() {
        // todo --
        return false;
    }

    public Expression getDefaultExpr() {
        // todo --
        return null;
    }

    @NotNull
    public VariableDescriptor describe() {

        final PackageDescriptor[] pdesc = new PackageDescriptor[]{null};
        // [start] search definition context --------------------
        ASTTreeProcessor runner = new ASTTreeProcessor();
        runner.add(new PackageTriggerHandler() {
            public void handleTriggerBody(CreateTrigger trigger) {
                // todo --
            }

            public void handlePackageBody(PackageBody pkg) {
                pdesc[0] = pkg.describe();
            }

            @Override
            public void handlePackageSpec(PackageSpec pkg) {
                pdesc[0] = pkg.describe();
            }
        });
        runner.process(getNode());
        // [end] search definition context ------------------------

        if(pdesc[0] != null){
//            return new VariableDescriptorImpl(pdesc[0], getVariableName(), getType(), false /* todo --*/);
            return new VariableDescriptorImpl(pdesc[0], getDeclName(), getType(), false /* todo --*/);
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitVariableDecl(this);
        } else {
            super.accept(visitor);
        }
    }

    public String getDeclName() {
        PsiElement e = this.findChildByType(PLSqlTypesAdopted.VARIABLE_NAME);
        if(e != null){
            return e.getText();
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }
}
