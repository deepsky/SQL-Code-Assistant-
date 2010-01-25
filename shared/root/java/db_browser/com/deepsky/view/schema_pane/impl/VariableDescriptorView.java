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

package com.deepsky.view.schema_pane.impl;

import com.deepsky.view.schema_pane.ItemViewWrapper;
import com.deepsky.view.Icons;
import com.deepsky.view.schema_pane.ItemViewListener;
import com.deepsky.lang.plsql.struct.VariableDescriptor;
import com.deepsky.lang.plsql.struct.Type;
import com.intellij.openapi.actionSystem.ToggleAction;

import javax.swing.tree.DefaultTreeCellRenderer;
import java.util.List;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

public class VariableDescriptorView extends ItemViewWrapperBase {

    String name;
    Type type;
    ItemViewWrapper parent;

    public VariableDescriptorView(ItemViewWrapper parent, VariableDescriptor var){
        this.name = var.getName().toUpperCase();
        this.type = var.getType();

        this.parent = parent;
    }

    public ItemViewWrapper getParent() {
        return parent;
    }

    public List<ItemViewWrapper> getChildren() {
        return new ArrayList<ItemViewWrapper>();
    }

    public Object[][] getTabularData() {
        List<Object[]> out = new ArrayList<Object[]>();

        Object[] params = new Object[2];
        params[0] = "Variable name";
        params[1] = name;
        out.add(params);

        params = new Object[2];
        params[0] = "Variable type";
        params[1] = type;
        out.add(params);
        return out.toArray(new Object[out.size()][]);
    }

    public Object[] getColumnNames() {
        return new Object[]{"Parameters", "Values"};
    }

    public void render(DefaultTreeCellRenderer renderer) {
        renderer.setText(name);
        renderer.setIcon(Icons.VARIABLE_DECL);
    }

    @NotNull
    public ToggleAction[] getActions() {
        return new ToggleAction[0];
    }

    public void setListener(ItemViewListener listener) {

    }
}
