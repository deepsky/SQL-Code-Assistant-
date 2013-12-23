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

package com.deepsky.view.utils;

import com.deepsky.database.ConnectionManager;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;


public class TestConnectionProgressIndicator {

    DbUrl url;
    final Project project;

    public TestConnectionProgressIndicator(Project project, DbUrl url){
        this.url = url;
        this.project = project;
    }

    public void checkConnection(){

        ProgressIndicatorHelper progress = new ProgressIndicatorHelper(project, "Test Connection");
        boolean res = progress.run(new ProgressIndicatorListener() {
            boolean result;

            public boolean isComplete() {
                try {
                    ConnectionManager manager = PluginKeys.CONNECTION_MANAGER.getData(project);
                    result = manager.checkConnection(url).isConnected();
                } catch (ConfigurationException e1) {
                    result = false;
                }
                return true;
            }

            public boolean getResult() {
                return result;
            }

            public String getText() {
                return "Testing " + url.getKey() + " ...";
            }

            public void updated(int step) {
            }
        });

        if (res) {
            // progress was NOT interrupted
            if (progress.getResult()) {
                Messages.showInfoMessage("Connection available", "Test Connection Status");
            } else {
                Messages.showErrorDialog("Could not establish connection, check parameters", "Test Connection Status");
            }
        }
    }
}
