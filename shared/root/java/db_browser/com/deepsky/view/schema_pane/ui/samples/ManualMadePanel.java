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

package com.deepsky.view.schema_pane.ui.samples;

import com.deepsky.actions.PropertyToggleAction;
import com.deepsky.view.Helpers;
import com.deepsky.view.Icons;
import com.intellij.openapi.actionSystem.*;

import javax.swing.*;

public class ManualMadePanel extends JPanel {

    public ManualMadePanel(){
        

        ActionManager actionManager = ActionManager.getInstance();
        DefaultActionGroup actionGroup = new DefaultActionGroup("DBSchemaActionGroup", false);
        actionGroup.add(new PropertyToggleAction("Sort Alphabetically", "Sort Alphabetically", Helpers.getIcon("/general/autoscrollToSource.png"), this, "autoScrollToSource"));
        actionGroup.add(new PropertyToggleAction("Sort by Type", "Sort by Type", Helpers.getIcon("/general/autoscrollFromSource.png"), this, "autoScrollFromSource"));
        actionGroup.add(new LocalToggleAction("Settings", "Settings", Icons.PLUGIN_SETTINGS, -1));
        actionGroup.addSeparator();
        actionGroup.add(new LocalToggleAction("Help", "Help", Icons.HELP, -1));

        ActionToolbar toolBar = actionManager.createActionToolbar("DBSchemaActionToolbar", actionGroup, true);
        this.add(toolBar.getComponent(), "North");

    }



    public class LocalToggleAction extends ToggleAction {

        int command;

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
        }

    }

}
