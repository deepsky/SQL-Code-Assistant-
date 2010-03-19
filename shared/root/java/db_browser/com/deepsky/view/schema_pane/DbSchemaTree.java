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

package com.deepsky.view.schema_pane;

import com.deepsky.database.SqlScriptManager;
import com.deepsky.findUsages.SqlFindUsagesHelper;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.struct.DbObject;
import com.deepsky.view.Icons;
import com.deepsky.view.schema_pane.impl.LocalToggleAction;
import com.deepsky.view.schema_pane.impl.ToggleActionListener;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class DbSchemaTree extends JTree {

    final Project project;
    public DbSchemaTree(Project project, TreeModel newModel) {
        super(newModel);
        this.project = project;
//        setCellRenderer(new PsiViewerTreeCellRenderer());
        setCellRenderer(new DbSchemaTreeCellRenderer());
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        setExpandsSelectedPaths(true);

// add MouseListener to tree
        MouseAdapter ma = new MouseAdapter() {
            private void myPopupEvent(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                JTree tree = (JTree) e.getSource();
                TreePath path = tree.getPathForLocation(x, y);
                if (path == null)
                    return;

                tree.setSelectionPath(path);
                Object obj = path.getLastPathComponent();
                boolean enabled = false;
                if (obj instanceof ItemViewWrapper && ((ItemViewWrapper) obj).getPopupActions().length > 0) {
                    ItemViewWrapper item = (ItemViewWrapper) obj;
                    DbObject dbo = item.describe();

                    JPopupMenu popup = new DbBrowserPopup(dbo, item.getPopupActions());
                    popup.show(tree, x, y);
                }
            }

            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) myPopupEvent(e);
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) myPopupEvent(e);
            }
        };

        this.addMouseListener(ma);
    }


    class DbBrowserPopup extends JPopupMenu {

        final ToggleAction[] actions;

        public DbBrowserPopup(DbObject target, ToggleAction[] actions) {

            this.actions = actions;
            int menuItemsCount = actions.length + 1;

            for (int j = 0, i = 0; i < menuItemsCount; i++) {
                switch (i) {
                    case 0: {
                        JMenuItem menuItem = new JMenuItem("Find Usages");
                        boolean enabled = target != null;
                        menuItem.setEnabled(enabled);
                        menuItem.setIcon(Icons.FIND);
                        if (enabled) {
                            menuItem.addActionListener(
                                    new ActionListenerImpl(
                                            new TriggerFindUsageAction(target))
                            );
                        }

                        this.add(menuItem);
                        this.addSeparator();
                        break;
                    }
                    default: {
                        final Presentation presentation = actions[j].getTemplatePresentation();
                        String label = presentation.getText();
                        JMenuItem menuItem = new JMenuItem(label);
                        menuItem.addActionListener(new ActionListenerImpl(actions[j]));
                        this.add(menuItem);
                        j++;
                        break;
                    }
                }
            }
        }

        class ActionListenerImpl implements ActionListener {
            ToggleAction action;

            public ActionListenerImpl(ToggleAction action) {
                this.action = action;
            }

            public void actionPerformed(ActionEvent e) {
                action.actionPerformed(
                        new AnActionEvent(null, DataManager.getInstance().getDataContext(DbBrowserPopup.this),
                                ActionPlaces.UNKNOWN, action.getTemplatePresentation(),
                                ActionManager.getInstance(), 0)
                );
            }
        }


        class TriggerFindUsageAction extends LocalToggleAction {

            public TriggerFindUsageAction(@NotNull final DbObject dbo) {
                super("Find Usage", "", Icons.FIND, 1, new ToggleActionListener() {

                    public void handle(AnActionEvent event, int command) {
                        //Project project = event.getData(LangDataKeys.PROJECT);
//                        Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
                        if (project != null) {
                            PlSqlElement psi = SqlScriptManager.mapToPsiTree(project, dbo);
                            if (psi != null) {
                                SqlFindUsagesHelper.runFindUsages(project, psi, true);
                            }
                        }
                    }
                });
            }
        }

    }

/*
// add MouseListener to tree
    MouseAdapter ma = new MouseAdapter() {
        private void myPopupEvent(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            JTree tree = (JTree) e.getSource();
            TreePath path = tree.getPathForLocation(x, y);
            if (path == null)
                return;

            tree.setSelectionPath(path);

            Object obj =  path.getLastPathComponent();

            String label = "popup: " + obj;
            JPopupMenu popup = new JPopupMenu();
            popup.add(new JMenuItem(label));
            popup.show(tree, x, y);
        }

        public void mousePressed(MouseEvent e) {
            if (e.isPopupTrigger()) myPopupEvent(e);
        }

        public void mouseReleased(MouseEvent e) {
            if (e.isPopupTrigger()) myPopupEvent(e);
        }
    };
*/
}
