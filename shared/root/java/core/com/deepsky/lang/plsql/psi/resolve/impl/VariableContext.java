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

import com.deepsky.database.SqlScriptManager;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.ResolveHelper;
import com.deepsky.lang.plsql.psi.resolve.VariantsProcessor777;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.lang.plsql.struct.types.RowtypeType;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;


public class VariableContext implements ResolveContext777 {
    VariableDescriptor vdesc;
    Project project;

    public VariableContext(@NotNull VariableDescriptor vdesc, @NotNull Project project) {
        this.vdesc = vdesc;
        this.project = project;
    }

    @NotNull
    public VariantsProcessor777 create(int narrow_type) {
        return null;
    }

    public PsiElement getDeclaration() {
        return SqlScriptManager.mapToPsiTree(project, vdesc);
    }

    public ResolveContext777 resolve(PsiElement elem) throws NameNotResolvedException {
        Type type;
        String text = elem.getText();

        type = vdesc.getType();
        if (type instanceof UserDefinedType) {
            UserDefinedTypeDescriptor tdesc = ResolveHelper.resolve_Type(project, (UserDefinedType) type);
            if (tdesc == null) {
                throw new NameNotResolvedException("Type definition not found: " + type.typeName());
            }

            return UserDefinedTypeHelper.createResolveContext(project, tdesc);
//            if (tdesc instanceof RecordTypeDescriptor) {
//                return new RecordTypeContext(elem.getProject(), (RecordTypeDescriptor) tdesc).resolve(elem);
//            } else if (tdesc instanceof ObjectTypeDescriptor) {
//                ObjectTypeDescriptor otype = (ObjectTypeDescriptor) tdesc;
//                RecordTypeItemDescriptor item = otype.findItem(text);
//                if (item == null) {
//                    throw new NameNotResolvedException("Type definition does not have " + text + " item");
//                }
//
//                return new RecordTypeItemContext(project, otype, item);
//            } else if (tdesc instanceof TableCollectionDescriptor) {
//                // ...
//                return new TableCollectionTypeContext(elem.getProject(), (TableCollectionDescriptor) tdesc).resolve(elem);
//            } else {
//            }
//            throw new NameNotResolvedException("Type not supported");
        } else if (type instanceof RowtypeType) {
            RowtypeType rowtype = (RowtypeType) type;
            TableDescriptor tdesc = ResolveHelper.describeTable(project, rowtype.getTableName());
            if (tdesc != null) {
                return new PlainTableColumnContext(project, tdesc, text);
            }
            throw new NameNotResolvedException("Table " + rowtype.getTableName() + " does not exist");
        } else {
            // type is primitive
            throw new NameNotResolvedException("Operation not applicable");
        }
    }

    public Type getType() throws NameNotResolvedException {
        return vdesc.getType();
    }
}
