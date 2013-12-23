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

package com.deepsky.view.schema_pane.ui;

import com.deepsky.lang.plsql.objTree.DbTreeElement;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.*;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseEventHandler extends MouseAdapter {

    public void mouseClicked(MouseEvent e) {
        Component cmp = e.getComponent();
        if (e.getClickCount() == 2 && cmp instanceof JTree) {
            JTree tree = (JTree) cmp;
            Object node = tree.getLastSelectedPathComponent();

            if (node instanceof DbTreeElement) {
                // handle double click on the node
                ((DbTreeElement) node).runDefaultAction();
            }
        }
    }

    private void myPopupEvent(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        JTree tree = (JTree) e.getSource();
        TreePath path = tree.getPathForLocation(x, y);
        if (path == null)
            return;

        tree.setSelectionPath(path);
        Object node = path.getLastPathComponent();
        boolean enabled = false;
        if (node instanceof DbTreeElement) { //obj instanceof ItemViewWrapper && ((ItemViewWrapper) obj).getPopupActions().length > 0) {
            DbTreeElement item = (DbTreeElement) node;
            JPopupMenu popup = new DbBrowserPopup(item);
            popup.show(tree, x, y);
        }
    }

    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) myPopupEvent(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) myPopupEvent(e);
    }


    private class DbBrowserPopup extends JPopupMenu {

        //final AnAction[] actions;

        public DbBrowserPopup(DbTreeElement item) {

            DbTreeElement.MenuItemAction[] actions = item.getActions();
            for(int i =0; i<actions.length; i++){
                final Presentation presentation = actions[i].getTemplatePresentation();
                String label = presentation.getText();
                JMenuItem menuItem = new JMenuItem(label);
                menuItem.setIcon(presentation.getIcon());
//                menuItem.setEnabled(presentation.isEnabled());
//                menuItem.addActionListener(new ActionListenerImpl(actions[i]));
                ButtonModelImpl model = new ButtonModelImpl(actions[i]);
                menuItem.setModel(model);
                menuItem.addActionListener(model);
                this.add(menuItem);
            }
        }

        private class ButtonModelImpl extends DefaultButtonModel implements ActionListener {

            private DbTreeElement.MenuItemAction action;
            private boolean isConstructed = false;
            private long lastMs = 0;
            private boolean isEnabledCached = true;

            public ButtonModelImpl(DbTreeElement.MenuItemAction action){
                this.action = action;
                isConstructed = true;
            }

            public void actionPerformed(ActionEvent e) {
                action.actionPerformed(
                        new AnActionEvent(null, DataManager.getInstance().getDataContext(DbBrowserPopup.this),
                                ActionPlaces.UNKNOWN, action.getTemplatePresentation(),
                                ActionManager.getInstance(), 0)
                );
            }

            public boolean isEnabled(){
                if(!isConstructed){
                    return super.isEnabled();
                }

                long currentMs = System.currentTimeMillis();
                if(currentMs - lastMs >= 1000){
                    lastMs = currentMs;
                    isEnabledCached = action.isEnabled();

                }
                return isEnabledCached;
            }
        }
    }

}
