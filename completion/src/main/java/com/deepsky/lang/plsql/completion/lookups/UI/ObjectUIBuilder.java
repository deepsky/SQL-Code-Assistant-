/*
 * Copyright (c) 2009,2014 Serhiy Kulyk
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *      1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
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

package com.deepsky.lang.plsql.completion.lookups.UI;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.*;
import com.intellij.openapi.ui.popup.util.MinimizeButton;
import com.intellij.openapi.util.Computable;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ObjectUIBuilder {

    private JBPopup popup;
    private boolean result = false;

    public boolean show(Component c){
        popup.show(c);
        return result;
    }

    public ObjectUIBuilder(Project project, ParamProviderPopup uiForm) {

        uiForm.addCloseEventLister(new ParamProviderPopup.CloseEventListener() {
            @Override
            public void close(boolean isOk) {
                result = isOk;
                if(isOk) popup.closeOk(null);
                else popup.cancel();
            }
        });

        final ComponentPopupBuilder builder = JBPopupFactory.getInstance()
                .createComponentPopupBuilder(uiForm.getRootComponent(), uiForm.getFocusedComponent());

        popup = builder
                //.setKeyboardActions()
                .setProject(project)
                .setCancelOnClickOutside(true)
                .setCancelOnOtherWindowOpen(true)
                .setCancelOnWindowDeactivation(true)
                .setBelongsToGlobalPopupStack(true)
                .setFocusable(true)
                .setMovable(true)
                .setResizable(true)
                .setCancelButton(new MinimizeButton("Hide"))
                .setDimensionServiceKey(project, uiForm.getDimensionServiceKey(), true)
                .setTitle(uiForm.getTitle())
                .setModalContext(false)
                .setRequestFocus(true)
                .setLocateByContent(true)
                .setMayBeParent(true)
                .setCancelCallback(new Computable<Boolean>() {
                    @Override
                    public Boolean compute() {
                        System.out.println("onClosed");
                        result = false;
                        return Boolean.TRUE;
                    }
                })
                .setShowShadow(true)
                .createPopup();

    }

}
