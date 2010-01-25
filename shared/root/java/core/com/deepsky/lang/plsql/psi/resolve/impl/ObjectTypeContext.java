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

package com.deepsky.lang.plsql.psi.resolve.impl;

import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.VariantsProcessor777;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import com.deepsky.database.SqlScriptManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class ObjectTypeContext  implements ResolveContext777 {
    ObjectTypeDescriptor tdesc;
    Project project;

    public ObjectTypeContext(Project project, ObjectTypeDescriptor tdesc) {
        this.project = project;
        this.tdesc = tdesc;
    }

    @NotNull
    public VariantsProcessor777 create(int narrow_type) {
        return null;
    }

    public PsiElement getDeclaration() {
//        try {
//            PlSqlFile file = ShortNamesCache.findDbObjectDefinition(project, tdesc);
//            if (file != null) {
//                return file.findDeclaration(tdesc);
//            }
//        } catch (Throwable e) {
//            int hh = 0;
//        }
        return SqlScriptManager.mapToPsiTree(project, tdesc);
    }

    public ResolveContext777 resolve(PsiElement elem) throws NameNotResolvedException {

        RecordTypeItemDescriptor r =  tdesc.findItem(elem.getText());
        if (r == null) {
            throw new NameNotResolvedException("Type definition does not have " + elem.getText() + " item");
        }

        return new RecordTypeItemContext(project, tdesc, r);
    }

    public Type getType() throws NameNotResolvedException {
        String packageName = tdesc.getPackage() == null? null: tdesc.getPackage().getName();
        return new UserDefinedType(packageName, tdesc.getName());
    }

}
