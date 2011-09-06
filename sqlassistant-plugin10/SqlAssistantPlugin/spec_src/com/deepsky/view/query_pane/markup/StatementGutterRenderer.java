/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
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

package com.deepsky.view.query_pane.markup;


import com.deepsky.lang.common.PluginKeys;
import com.deepsky.view.query_pane.QueryResultWindow;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Comparing;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class StatementGutterRenderer extends GutterIconRenderer {

    //String markerName;
    SqlStatementMarker marker;
    Icon icon;

//    public StatementGutterRenderer(Icon icon, String markerName) {
//        this.markerName = markerName;
//        this.icon = icon;
//    }

    public StatementGutterRenderer(Icon icon, SqlStatementMarker marker) {
        this.marker = marker;
        this.icon = icon;
    }

    @NotNull
    public Icon getIcon() {
        return icon;
    }

    public String getTooltipText() {
      return "Go to Result Pane";
    }

    @Nullable
    public AnAction getClickAction() {
        return  new LocalToggleAction("Create Script", "Create Script");
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == getClass()
             && marker.equals(((StatementGutterRenderer)obj).marker);
    }

    @Override
    public int hashCode() {
        return marker.hashCode();
    }

    class LocalToggleAction extends ToggleAction {
        public LocalToggleAction(String actionName, String toolTip) {
            super(actionName, toolTip, null);
            this.getTemplatePresentation().setEnabled(true);
        }

        public boolean isSelected(AnActionEvent event) {
            return false;
        }

        public void setSelected(AnActionEvent event, boolean b) {
            // show content pane
//            QueryResultWindow.getInstance().showContent(markerName);
            Project project = event.getData(LangDataKeys.PROJECT);
            PluginKeys.QR_WINDOW.getData(project).showContent(marker);
//            QueryResultWindow.getInstance().showContent(marker);
        }
    }

}
