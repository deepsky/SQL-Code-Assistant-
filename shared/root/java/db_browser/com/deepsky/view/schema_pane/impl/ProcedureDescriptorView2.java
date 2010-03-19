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

package com.deepsky.view.schema_pane.impl;

import com.deepsky.database.SqlScriptManager;
import com.deepsky.database.cache.Cache;
import com.deepsky.lang.plsql.struct.FunctionDescriptor;
import com.deepsky.lang.plsql.struct.ProcedureDescriptor;
import com.deepsky.view.schema_pane.ItemViewWrapper;
import com.deepsky.view.Icons;
import com.deepsky.lang.plsql.struct.ExecutableDescriptor;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.lang.plsql.psi.utils.Formatter;
import com.intellij.openapi.project.Project;

import javax.swing.tree.DefaultTreeCellRenderer;

public class ProcedureDescriptorView2 extends ItemViewWrapperBase {

    Cache c0;
    String name;
    String signature;

    public ProcedureDescriptorView2(Project project, ItemViewWrapper parent, Cache c0, String name){
        super(project, parent);
        this.c0 = c0;
        this.name = name;

        ExecutableDescriptor exec = (ExecutableDescriptor) c0.get(name, DbObject.PROCEDURE);
        signature = Formatter.formatHtmlBasedSignature(exec);
        exec = null;
    }

    public void render(DefaultTreeCellRenderer renderer) {
        renderer.setText(signature);
        renderer.setIcon(Icons.PROCEDURE_BODY);
    }

    public void runDefaultAction() {
        ProcedureDescriptor p = (ProcedureDescriptor) c0.get(name, DbObject.PROCEDURE);
        if (signature.equals(Formatter.formatHtmlBasedSignature(p))) {
            boolean result = SqlScriptManager.openFileInEditor(project, p);
        }
    }


}

