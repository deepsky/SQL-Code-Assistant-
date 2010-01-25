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

package com.deepsky.actions;

import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;

import javax.swing.*;
import java.beans.PropertyDescriptor;

public class PropertyToggleAction extends ToggleAction {

    private static final Logger LOG = Logger.getInstance("#PropertyToggleAction");
//    private final Object _target;
//    private PropertyDescriptor _property;

    public PropertyToggleAction(String actionName, String toolTip, Icon icon, Object target, String property) {

        super(actionName, toolTip, icon);
        this.getTemplatePresentation().setEnabled(false);

//        _target = target;
//        _property = IntrospectionUtil.getProperty(target.getClass(), property);

//        if (!isPropertyValid(property))
//            _property = null;
    }

/*
    private boolean isPropertyValid(String property) {

        if (_property == null) {
            LOG.warn("Could not find " + getPropertyName(property));
            return false;
        }

        if (_property.getReadMethod() == null) {
            LOG.warn("Could not find getter for " + getPropertyName(property));
            return false;
        }

        if (_property.getWriteMethod() == null) {
            LOG.warn("Could not find setter for " + getPropertyName(property));
            _property = null;
            return false;
        } else {
            return true;
        }
    }

    private String getPropertyName(String property) {
        return "property " + property + " in class " + _target.getClass();
    }
*/
    public boolean isSelected(AnActionEvent anactionevent) {
//        if(_property == null)
//        LOG.info("AnAction : isSelected, place " + anactionevent.getPlace());
        return false;
//        else
//            return ((Boolean)IntrospectionUtil.getValue(_target, _property)).booleanValue();
    }

    public void setSelected(AnActionEvent anactionevent, boolean flag) {
        LOG.info("AnAction Place: " + anactionevent.getPlace());
//        IntrospectionUtil.setValue(_target, _property, new Boolean(flag));
    }

}
