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

package com.deepsky.view.schema_pane;

import com.deepsky.actions.PropertyToggleAction;
import com.deepsky.gui.PluginSettingsBase;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.conf.PluginSettingsBean;
import com.deepsky.lang.conf.ValidationSettingsListener;
import com.deepsky.view.Helpers;
import com.deepsky.view.Icons;
import com.deepsky.view.schema_pane.impl.ToggleActionListener;
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;

import javax.swing.*;

public class DBBrowserWindow implements ToggleActionListener {

    private final Logger log = Logger.getInstance("#DBBrowserWindow");

    public static final String DB_BROWSER_PANE = "DatabaseBrowser";

    Project project;
    DbSchemaPanel _viewerPanel;

    private int splitDividerLocation;

    public DBBrowserWindow(Project project){
        this.project = project;

        _viewerPanel = new DbSchemaPanel(this);

        ActionManager actionManager = ActionManager.getInstance();
        DefaultActionGroup actionGroup = new DefaultActionGroup("DBSchemaActionGroup", false);
        actionGroup.add(new PropertyToggleAction("Sort Alphabetically", "Sort Alphabetically", Helpers.getIcon("/general/autoscrollToSource.png"), this, "autoScrollToSource"));
        actionGroup.add(new PropertyToggleAction("Sort by Type", "Sort by Type", Helpers.getIcon("/general/autoscrollFromSource.png"), this, "autoScrollFromSource"));
        actionGroup.add(new LocalToggleAction("Settings", "Settings", Icons.PLUGIN_SETTINGS, 1, this));

        ActionToolbar toolBar = actionManager.createActionToolbar("DBSchemaActionToolbar", actionGroup, true);
        _viewerPanel.add(toolBar.getComponent(), "North");

        ToolWindow toolWindow = getToolWindow();
        toolWindow.setIcon(Icons.DB_BROWSER);
        _viewerPanel.setToolWindow(toolWindow);
    }


    private ToolWindow getToolWindow() {
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        if (isToolWindowRegistered())
            return toolWindowManager.getToolWindow(DB_BROWSER_PANE);
        else
            return toolWindowManager.registerToolWindow(DB_BROWSER_PANE, _viewerPanel, ToolWindowAnchor.RIGHT);
    }


    private boolean isToolWindowRegistered() {
        return ToolWindowManager.getInstance(project).getToolWindow(DB_BROWSER_PANE) != null;
    }

    public void setSplitDividerLocation(int splitDividerLocation) {
        this.splitDividerLocation = splitDividerLocation;
    }

    public int getSplitDividerLocation() {
        return splitDividerLocation;
    }

    public void handle(int command) {
        PluginSettingsBase dialog = new PluginSettingsBase(project);

        PluginSettingsBean settings = PluginKeys.PLUGIN_SETTINGS.getData();

        dialog.setDateFormat(settings.getDateFormat());
        dialog.setTimeFormat(settings.getTimeFormat());
        dialog.setFetchRecords(settings.getFetchRecords());
        dialog.setNumberOfTabs(settings.getNumberOfTabs());

        dialog.setValidateSyntax(settings.getHighlightSyntaxErrors());
        dialog.setResolveReference(settings.getResolveReference());
        dialog.setResolveUdt(settings.getResolveUdt());
        dialog.setValidateFunc(settings.getValidateFunc());
        dialog.setValidateTables(settings.getValidateTables());
        dialog.setValidateInsert(settings.getValidateInsert());

        dialog.show();
        if (dialog.isOK()) {
            final boolean[] validationChanged = new boolean[]{false};
            settings.setValidationSettingsListener(new ValidationSettingsListener() {
                public void settingsUpdated() {
                    validationChanged[0] = true;
                }
            });

            settings.setDateFormat(dialog.getDateFormat());
            settings.setTimeFormat(dialog.getTimeFormat());
            settings.setFetchRecords(dialog.getFetchRecords());
            settings.setNumberOfTabs(dialog.getNumberOfTabs());

            settings.setHighlightSyntaxErrors(dialog.getValidateSyntax());
            settings.setResolveReference(dialog.getResolveReference());
            settings.setResolveUdt(dialog.getResolveUdt());
            settings.setValidateFunc(dialog.getValidateFunc());
            settings.setValidateTables(dialog.getValidateTables());
            settings.setValidateInsert(dialog.getValidateInsert());

            settings.setValidationSettingsListener(null);

            if (validationChanged[0]) {
//                updateAnnotator(settings);
                // run annotator
                runCodeAnalyzer();
            }

            // save settings
            PluginKeys.PLUGIN_SETTINGS.putData(settings, project);
        }
    }

    private void runCodeAnalyzer() {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            public void run() {
                if (!project.isDisposed()) {
                    DaemonCodeAnalyzer.getInstance(project).restart();
                    log.info("#RESTART code analyzer");
                }
            }
        });
    }

//    private void updateAnnotator(PluginSettingsBean bean) {
//        PlSqlAnnotatingVisitor ann = ReleaseSpecificCode.getAnnotator(); //PlSqlAnnotatingVisitor) lang.getAnnotator();
//        if (ann != null) {
//            ann.setResolveReference(bean.getResolveReference());
//            ann.setResolveUdt(bean.getResolveUdt());
//            ann.setValidateFunc(bean.getValidateFunc());
//            ann.setValidateTables(bean.getValidateTables());
//            ann.setValidateInsert(bean.getValidateInsert());
//        }
//    }


    public void close() {
        _viewerPanel = null;

        if (isToolWindowRegistered())
            ToolWindowManager.getInstance(project).unregisterToolWindow(DB_BROWSER_PANE);
    }

    public class LocalToggleAction extends ToggleAction {

        int command;
        ToggleActionListener listener;

        public LocalToggleAction(String actionName, String toolTip, Icon icon, int command, ToggleActionListener listener) {
            super(actionName, toolTip, icon);
            boolean enabled = true;
            this.getTemplatePresentation().setEnabled(enabled);
            this.command = command;
            this.listener = listener;
        }

        public LocalToggleAction(String actionName, String toolTip, Icon icon, int command) {
            super(actionName, toolTip, icon);
            boolean enabled = true;
            this.getTemplatePresentation().setEnabled(enabled);
            this.command = command;
        }

        public boolean isSelected(AnActionEvent event) {
            return false;
        }

        public void setSelected(AnActionEvent event, boolean b) {
            if (listener != null) {
                listener.handle(command);
            }
        }

        public void setListener(ToggleActionListener listener) {
            this.listener = listener;
        }
    }
}
