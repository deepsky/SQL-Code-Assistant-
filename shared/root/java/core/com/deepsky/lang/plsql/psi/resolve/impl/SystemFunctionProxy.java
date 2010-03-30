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

import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.VariantsProcessor777;
import com.deepsky.lang.plsql.struct.SystemFunctionDescriptor;
import com.deepsky.lang.plsql.struct.Type;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SystemFunctionProxy implements ResolveContext777 {

    SystemFunctionDescriptor edesc;
    Project project;
    List<String> errors;


    public SystemFunctionProxy(SystemFunctionDescriptor edesc, Project project) {
        this.edesc = edesc;
        this.project = project;
    }

    public SystemFunctionProxy(SystemFunctionDescriptor edesc, Project project, @NotNull List<String> errors) {
        this.edesc = edesc;
        this.project = project;
        this.errors = errors;
    }

    @NotNull
    public VariantsProcessor777 create(int narrwo_type) {
        // todo -
        return null;
    }

    public PsiElement getDeclaration() {
        return null;
    }

    public ResolveContext777 resolve(PsiElement elem) throws NameNotResolvedException {
        // todo -
        return null;
    }

    public Type getType() throws NameNotResolvedException {
        return edesc.getReturnType();
    }

    public boolean isArgsValid(){
        return errors == null || errors.size() == 0;
    }

    public String getError() {
        return errors == null || errors.size() == 0? null: errors.get(0);
    }
}

