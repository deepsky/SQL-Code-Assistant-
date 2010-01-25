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

package com.deepsky.lang.plsql.struct.impl;

import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.struct.ExecutableDescriptor;
import org.jetbrains.annotations.NotNull;

public class ProcedureImpl extends PsiElementDumb implements Procedure {

    boolean createOrReplace;
    String name;
    Argument[] args = new Argument[0];

    public String getEName() {
        return name;
    }

    public void setEName(String name) {
        this.name = name;
    }

    @NotNull
    public Argument[] getArguments() {
        return args;
    }

    @NotNull
    public ArgumentList getArgumentList() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Argument getArgumentByName(String parameterName) {
        // todo --
        return null;
    }

    @NotNull
    public Declaration[] getDeclarationList() {
        return new Declaration[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PlSqlBlock getBlock() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean createOrReplace() {
        return createOrReplace;
    }

    public ExecutableDescriptor describe() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean equals(ExecutableDescriptor edesc) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getPackageName() {
        // todo 
        return null;
    }

    public void setCreateOrReplace(boolean createOrReplace) {
        this.createOrReplace = createOrReplace;
    }

    public void setArgumentList(ArgumentList argumentList) {
        args = argumentList.getArguments();
    }
}
