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

package com.deepsky.view.query_pane.grid;

import com.deepsky.database.ora.types.LONGRAWType;
import com.deepsky.database.ora.types.RAWType;
import com.deepsky.view.query_pane.DataAccessor;
import com.deepsky.view.query_pane.ui.BinaryEditorDialog;
import com.deepsky.view.query_pane.ui.TextEditorDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import oracle.sql.BLOB;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class ValueEditorDialogFactory {

    public static DialogWrapper buildDialog(
            @NotNull Project project, @NotNull Class columnClazz, String columnName, DataAccessor accessor){

        try {
            if(columnClazz.isAssignableFrom(RAWType.class)){
                return new BinaryEditorDialog(project, columnName, accessor);
            } else if(columnClazz.isAssignableFrom(LONGRAWType.class)){
                return new BinaryEditorDialog(project, columnName, accessor);
            } else if(columnClazz.isAssignableFrom(BLOB.class)){
                return new BinaryEditorDialog(project, columnName, accessor);
            } else {
                return new TextEditorDialog(project, columnName, accessor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    private static class RawEditor implements ValueEditorDialog {

        BinaryEditorDialog dialog;
        public RawEditor(BinaryEditorDialog dialog){
            this.dialog = dialog;
        }
        public String getValue() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public boolean okPressed() {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public void show() {
            dialog.show();
        }
    }

    private static class TextEditor implements ValueEditorDialog {

        TextEditorDialog dialog;
        public TextEditor(TextEditorDialog dialog){
            this.dialog = dialog;
        }
        public String getValue() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public boolean okPressed() {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public void show() {
            dialog.show();
        }

        public void init(String columnName, DataAccessor accessor, boolean readWriteAccess) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }

}
