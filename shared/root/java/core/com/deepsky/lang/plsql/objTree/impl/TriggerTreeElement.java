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

package com.deepsky.lang.plsql.objTree.impl;

import com.deepsky.lang.plsql.objTree.DbTreeElementAbstract;
import com.deepsky.lang.plsql.objTree.DetailsTableModel;
import com.deepsky.lang.plsql.objTree.ui.DbObjectTreeCellRenderer;
import com.deepsky.view.Icons;

import javax.swing.table.TableModel;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.util.ArrayList;
import java.util.List;

public class TriggerTreeElement extends DbTreeElementAbstract {
    String value;
    boolean isValid = true;

    public TriggerTreeElement(String ctxPath, String name, String value) {
        super(name);
        this.ctxPath = ctxPath;
        this.value = value;
    }


    public void render(DbObjectTreeCellRenderer renderer) {
        renderer.setText(name.toUpperCase());
        renderer.setIcon(Icons.TRIGGER);
        renderer.setIsValid(isValid);
    }

    public String getTableName(){
        if(value.startsWith("dml|")){
            return value.substring(4).split("\\|")[0];
        } else {
            return null;
        }
    }

    public String getConditionClause() {
        if(value.startsWith("dml|")){
            String[] splitted = value.split("\\|");
            if(splitted.length > 2){
                String clause = splitted[2];
                return clause.replace(".", " ");
            }
        }
        return "";
    }

    public String getEventClause() {
        if(value.startsWith("db_event|") || value.startsWith("ddl|")){
            String[] splitted = value.split("\\|");
            if(splitted.length > 2){
                String event = splitted[1];
                String target = splitted[2];
                return target + " " + event;
            }
        }
        return "";
    }

    public TableModel getProperties() {
        // build model
        DetailsTableModel model = new DetailsTableModel(new String[]{"Name", "Value"});
        List<String> out = new ArrayList<String>();
        out.add("TRIGGER TYPE");
        if(value.startsWith("db_event")){
            out.add("DATABASE EVENT");
        } else if (value.startsWith("dml")){
//            out.add("DML EVENT");
            out.add("DML EVENT (" + getConditionClause().toUpperCase() + ")");
        } else if(value.startsWith("ddl")){
            out.add("DDL EVENT");
        } else if(value.startsWith("insteadof")){
            out.add("INSTEAD OF");
        } else {
            // not supported type??
            out.add("");
        }
        model.addRow(out);

        if (value.startsWith("dml")){
            out = new ArrayList<String>();
            out.add("TABLE NAME");
            out.add(getTableName().toUpperCase());
            model.addRow(out);
        } else if (value.startsWith("db_event")){
            out = new ArrayList<String>();
            out.add("FIRED ON");
            out.add(getEventClause().toUpperCase());
            model.addRow(out);
        }

        return model;
    }

    public void setIsValid(boolean status) {
        this.isValid = status;
    }
}
