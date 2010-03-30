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

package com.deepsky.lang.plsql.struct.impl;

import com.deepsky.lang.plsql.psi.Expression;
import com.deepsky.lang.plsql.psi.Visitor;
import com.deepsky.lang.plsql.psi.VariableDecl;
import com.deepsky.lang.plsql.psi.types.TypeSpec;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.VariableDescriptor;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class VariableDeclImpl extends PsiElementDumb implements VariableDecl {

    private String variableName;
    private Type type;
    private boolean constant;
    private boolean notNull;
    private boolean default1;
    private boolean beingAssigned;
    private Expression expr;

    public String getDeclName() {
        return variableName;
    }

    public PsiElement getVariableName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type getType() {
        return type;
    }

    public boolean isConstant() {
        return constant;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public boolean isBeingAssigned() {
        return beingAssigned;
    }

    public boolean isDefault() {
        return default1;
    }

    @NotNull
    public VariableDescriptor describe() {
        return null;
    }

    public Expression getDefaultExpr() {
        return expr;
    }

    public TypeSpec getTypeSpec() {
        return null;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setConstant(boolean constant) {
        this.constant = constant;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public void setDefault1(boolean default1) {
        this.default1 = default1;
    }

    public void setBeingAssigned(boolean beingAssigned) {
        this.beingAssigned = beingAssigned;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
    }

    public void process(Visitor proc) {
        proc.accept(this);
    }
    
}
