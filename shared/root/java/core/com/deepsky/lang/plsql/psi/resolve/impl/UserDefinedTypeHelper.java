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

import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.collection.PsiTableCollectionTypeContext;
import com.deepsky.lang.plsql.psi.resolve.collection.TableCollectionTypeContext;
import com.deepsky.lang.plsql.psi.resolve.psibased.PsiObjectTypeContext;
import com.deepsky.lang.plsql.psi.resolve.psibased.PsiRecordTypeContext;
import com.deepsky.lang.plsql.psi.resolve.psibased.PsiRefCursorContext;
import com.deepsky.lang.plsql.psi.resolve.psibased.PsiVarrayCollectionContext;
import com.deepsky.lang.plsql.struct.*;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;


public class UserDefinedTypeHelper {

    public static ResolveContext777 createResolveContext(Project project, UserDefinedTypeDescriptor udt) throws NameNotResolvedException {
        if(udt == null){
            throw new NameNotResolvedException("Type is null");
        }
//        Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
        if (udt instanceof RecordTypeDescriptor) {
            return new RecordTypeContext(project, (RecordTypeDescriptor) udt);
        } else if (udt instanceof TableCollectionDescriptor) {
            return new TableCollectionTypeContext(project, (TableCollectionDescriptor) udt);
        } else if (udt instanceof ObjectTypeDescriptor) {
            return new ObjectTypeContext(project, (ObjectTypeDescriptor) udt);
        } else if (udt instanceof VarrayCollectionDescriptor) {
            return new VarrayCollectionContext(project, (VarrayCollectionDescriptor) udt);
        } else if (udt instanceof RefCursorTypeDescriptor) {
            return new RefCursorContext(project, (RefCursorTypeDescriptor) udt);
        } else {
            throw new NameNotResolvedException(
                    "Type " + udt.getTypeName() + " not supported"
            );
        }
    }

    public static ResolveContext777 createResolveContext(TypeDeclaration udt) throws NameNotResolvedException {
        if(udt == null){
            throw new NameNotResolvedException("Type is null");
        }

        if (udt instanceof RecordTypeDecl) {
            return new PsiRecordTypeContext((RecordTypeDecl) udt);
        } else if (udt instanceof TableCollectionDecl) {
            return new PsiTableCollectionTypeContext((TableCollectionDecl) udt);
        } else if (udt instanceof ObjectTypeDecl) {
            return new PsiObjectTypeContext((ObjectTypeDecl) udt);
        } else if (udt instanceof VarrayCollectionDecl) {
            return new PsiVarrayCollectionContext((VarrayCollectionDecl) udt);
        } else if (udt instanceof RefCursorDecl) {
            return new PsiRefCursorContext((RefCursorDecl) udt);
        } else {
            throw new NameNotResolvedException(
                    "Type " + udt.getDeclName() + " not supported"
            );
        }
    }
}
