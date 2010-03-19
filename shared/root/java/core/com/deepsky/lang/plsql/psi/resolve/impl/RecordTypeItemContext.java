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

package com.deepsky.lang.plsql.psi.resolve.impl;

import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.VariantsProcessor777;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.psi.resolve.ResolveHelper;
import com.deepsky.lang.plsql.struct.UserDefinedTypeDescriptor;
import com.deepsky.lang.plsql.struct.RecordTypeItemDescriptor;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TableDescriptor;
import com.deepsky.lang.plsql.struct.types.RowtypeType;
//import com.deepsky.lang.common.PlSqlFile;
//import com.deepsky.lang.common.ShortNamesCache;
import com.deepsky.database.SqlScriptManager;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class RecordTypeItemContext implements ResolveContext777 {

    Project project;
    UserDefinedTypeDescriptor rtype;
    RecordTypeItemDescriptor item;
    Type type;

    public RecordTypeItemContext(Project project, UserDefinedTypeDescriptor rtype, RecordTypeItemDescriptor item) {
        this.project = project;
        this.rtype = rtype;
        this.item = item;
        this.type = item.getType();
    }

    public String[] getVariants(String prefix) {
        return new String[0];
    }

    @NotNull
    public VariantsProcessor777 create(int narrow_type) {
        return null;
    }

    public PsiElement getDeclaration() {
        return SqlScriptManager.mapToPsiTree(project, item);
    }

    public ResolveContext777 resolve(PsiElement elem) throws NameNotResolvedException {
        String text = elem.getText();
        if (type instanceof RowtypeType) {
            RowtypeType rowtype = (RowtypeType) item.getType();
            TableDescriptor tdesc = ResolveHelper.describeTable(project,rowtype.getTableName());
            if (tdesc != null) {
                return new PlainTableColumnContext(project, tdesc, text);
            }
        } else if (type instanceof UserDefinedType) {
            UserDefinedTypeDescriptor udtd = ResolveHelper.resolve_Type(project, (UserDefinedType) type);
            if (udtd != null) {
                return UserDefinedTypeHelper.createResolveContext(project, udtd).resolve(elem);
            }
        }
        throw new NameNotResolvedException("Not supported at the moment");
    }

    public Type getType() {
        return type;
    }
}
