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

package com.deepsky.lang.plsql.psi.resolve.collection;

import com.deepsky.database.SqlScriptManager;
import com.deepsky.lang.plsql.psi.Argument;
import com.deepsky.lang.plsql.psi.VariableDecl;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.ResolveHelper;
import com.deepsky.lang.plsql.psi.resolve.VariantsProcessor777;
import com.deepsky.lang.plsql.psi.resolve.impl.PlainTableColumnContext;
import com.deepsky.lang.plsql.psi.resolve.impl.UserDefinedTypeHelper;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.lang.plsql.struct.types.RowtypeType;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class VarrayItemAccessorCtx  implements ResolveContext777 {

    PsiElement decl;
    VarrayCollectionDescriptor vdesc;
    Project project;
    VariableDescriptor vardesc;

    public VarrayItemAccessorCtx(VariableDecl var, VarrayCollectionDescriptor vdesc) {
        this.decl = var;
        this.vdesc = vdesc;
        this.project = var.getProject();
    }

    public VarrayItemAccessorCtx(Argument arg, VarrayCollectionDescriptor vdesc) {
        this.decl = arg;
        this.vdesc = vdesc;
        this.project = arg.getProject();
    }

    public VarrayItemAccessorCtx(Project project, VariableDescriptor vardesc, VarrayCollectionDescriptor vdesc) {
        this.vardesc = vardesc;
        this.vdesc = vdesc;
        this.project = project;
    }

    @NotNull
    public VariantsProcessor777 create(int narrow_type) throws NameNotResolvedException {
        return null;
    }

    public PsiElement getDeclaration() {
        return decl != null? decl: getDecl(vardesc);
    }

    private PsiElement getDecl(VariableDescriptor vdesc) {
//        Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
        return SqlScriptManager.mapToPsiTree(project, vdesc);
    }


    public ResolveContext777 resolve(PsiElement elem) throws NameNotResolvedException {
        Type t = vdesc.getBaseType();
        if(t instanceof UserDefinedType){
            UserDefinedTypeDescriptor udesc = ResolveHelper.resolve_Type(elem.getProject(), (UserDefinedType) t);
            return UserDefinedTypeHelper.createResolveContext(project, udesc).resolve(elem);
        } else if(t instanceof RowtypeType){
            RowtypeType rowtype = (RowtypeType) t;
            TableDescriptor tdesc = ResolveHelper.describeTable(project, rowtype.getTableName());
            if (tdesc != null) {
                return new PlainTableColumnContext(elem.getProject(), tdesc, elem.getText());
            }
        }

        throw new NameNotResolvedException("Cannot resolve name " + elem.getText());
    }

    public Type getType() throws NameNotResolvedException {
        return vdesc.getBaseType();
    }
}
