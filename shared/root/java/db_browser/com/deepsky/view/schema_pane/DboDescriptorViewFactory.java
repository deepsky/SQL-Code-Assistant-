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

package com.deepsky.view.schema_pane;

import com.deepsky.database.cache.Cache;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.view.schema_pane.impl.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DboDescriptorViewFactory {

    static LoggerProxy log = LoggerProxy.getInstance(DboDescriptorViewFactory.class.getName());

    public static List<ItemViewWrapper> findByType(ItemViewWrapper parent, Cache c0, String type) {
        List<ItemViewWrapper> out = new ArrayList<ItemViewWrapper>();
        if(c0 == null){
            return out;
        }
        List<String> l0 = c0.getNames(type);

        // sort by name
        Collections.sort(l0, new Comparator<String>(){
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        for (String name : l0) {
            if (DbObject.TABLE.equalsIgnoreCase(type)) {
                out.add(new TableDescriptorView(parent.getProject(), parent, c0, name));
            } else if (DbObject.VIEW.equalsIgnoreCase(type)) {
                out.add(new ViewDescriptorView(parent.getProject(), parent, c0, name));
            } else if (DbObject.PACKAGE.equalsIgnoreCase(type)) {
                out.add(new PackageDescriptorView(parent.getProject(), parent, c0, name));
            } else if (DbObject.SEQUENCE.equalsIgnoreCase(type)) {
                out.add(new SequenceDescriptorView(parent.getProject(), parent, c0, name));
            } else if (DbObject.TYPE.equalsIgnoreCase(type)) {
                out.add(new TypeDescriptorView(parent.getProject(), c0, name));
            } else if (DbObject.FUNCTION.equalsIgnoreCase(type)) {
                out.add(new FunctionDescriptorView2(parent.getProject(), parent, c0, name));
            } else if (DbObject.PROCEDURE.equalsIgnoreCase(type)) {
                out.add(new ProcedureDescriptorView2(parent.getProject(), parent, c0, name));
            } else if (DbObject.TRIGGER.equalsIgnoreCase(type)) {
                out.add(new TriggerDescriptorView(parent.getProject(), parent, c0, name));
            } else {
                log.warn("View Type descriptor: " + type + " not defined");
            }
        }

        return out;
    }

    public static ItemViewWrapper createPackageItemView(ItemViewWrapper parent, DbObject dbo, Cache cache) {
        if (DbObject.TABLE.equalsIgnoreCase(dbo.getTypeName())) {
            // todo -
            return new GenericDbObjectDescriptorView(parent.getProject(), parent, dbo);
        } else if (DbObject.PACKAGE.equalsIgnoreCase(dbo.getTypeName())) {
            // todo -
            return new GenericDbObjectDescriptorView(parent.getProject(), parent, dbo);
        } else if (DbObject.SEQUENCE.equalsIgnoreCase(dbo.getTypeName())) {
            // todo -
            return new GenericDbObjectDescriptorView(parent.getProject(), parent, dbo);
        } else if (DbObject.TYPE.equalsIgnoreCase(dbo.getTypeName())) {
            return new TypeDescriptorView(parent.getProject(), parent, (UserDefinedTypeDescriptor) dbo, cache);
        } else if (DbObject.FUNCTION.equalsIgnoreCase(dbo.getTypeName())) {
            return new FunctionDescriptorView(parent.getProject(), parent, (FunctionDescriptor) dbo, cache);
        } else if (DbObject.PROCEDURE.equalsIgnoreCase(dbo.getTypeName())) {
            return new ProcedureDescriptorView(parent.getProject(), parent, (ProcedureDescriptor) dbo, cache);
        } else if (DbObject.VARIABLE.equalsIgnoreCase(dbo.getTypeName())) {
            return new VariableDescriptorView(parent.getProject(), parent, (VariableDescriptor) dbo, cache);
        } else {
            // todo -
            return new GenericDbObjectDescriptorView(parent.getProject(), parent, dbo);
        }
    }
}
