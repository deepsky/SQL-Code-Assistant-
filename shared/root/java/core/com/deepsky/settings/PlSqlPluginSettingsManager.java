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

package com.deepsky.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;

import javax.swing.*;

public class PlSqlPluginSettingsManager implements Configurable {

    private Project project;
    SqlCodeAssistantSettings settings;

    public PlSqlPluginSettingsManager(Project project){
        this.project = project;
        this.settings = SqlCodeAssistantSettings.getInstance(project);
    }

    @Nls
    public String getDisplayName() {
        return "SQL Code Assistant";
    }

    public Icon getIcon() {
        return null;
    }

    public String getHelpTopic() {
        return null;
    }

    PlSqlPluginSettingsForm settingsPane;
    public JComponent createComponent() {
        if (settingsPane == null) {
          settingsPane = new PlSqlPluginSettingsForm(settings, project);
        }
        return settingsPane.getRootComponent();
    }

    public boolean isModified() {
      return settingsPane == null || settingsPane.isModified();
    }

    public void apply() throws ConfigurationException {
      if (settingsPane != null) {
        settingsPane.apply();
      }
    }

    public void reset() {
      if (settingsPane != null) {
        settingsPane.reset();
      }
    }

    public void disposeUIResources() {
      settingsPane = null;
    }
}
