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

import com.deepsky.lang.plsql.objTree.DbElementRoot;
import com.deepsky.lang.plsql.objTree.DbTreeElement;
import com.deepsky.lang.plsql.objTree.DbTypeElementAction;
import com.deepsky.lang.plsql.objTree.ui.DbObjectTreeCellRenderer;
import com.intellij.openapi.actionSystem.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;


public abstract class TabFormBase {
    final static public String TEST501_COMPONENT = "TEST501_COMPONENT";

    protected Set<Test501Listener> listeners = new HashSet<Test501Listener>();
    protected boolean locked = false;

    public void addListener(Test501Listener l) {
        listeners.add(l);
    }

    public void removeListener(Test501Listener l) {
        listeners.remove(l);
    }


    protected void init(DbElementRoot elem) {
        DbTypeElementAction[] actions = elem.getActions();
        if (actions.length > 0) {
            ActionManager actionManager = ActionManager.getInstance();
            DefaultActionGroup actionGroup = new DefaultActionGroup("DBSchemaActionGroup", false);
            for (final DbTypeElementAction action : actions) {
                if (action.getName().equals("SEPARATOR")) {
                    actionGroup.add(Separator.getInstance());
                } else {
                    actionGroup.add(
                            new LocalToggleAction(action.getName(), action.getTooltip(), action.getIcon(), action.getCommand()) {
                                @Override
                                public void setSelected(AnActionEvent e, boolean state) {
                                    TreePath path = getCentralTree().getSelectionModel().getSelectionPath();
                                    String selectedCtxPath = null;
                                    if (path != null) {
                                        Object elem[] = path.getPath();
                                        selectedCtxPath = ((DbTreeElement) elem[elem.length - 1]).getCtxPath();
                                    }
                                    action.setSelected(command, selectedCtxPath);
                                }

                                public boolean isSelected(AnActionEvent event) {
                                    return action.isSelected(command);
                                }
                            }
                    );
                }
            }
            ActionToolbar toolBar = actionManager.createActionToolbar("DBSchemaActionToolbar", actionGroup, true);
            //JComponent toolBarComponent = toolBar.getComponent();
            //toolBarComponent.setBorder(new EtchedBorder(EtchedBorder.RAISED));
            getTopPanel().add(toolBar.getComponent(), BorderLayout.NORTH);
        }

        getCentralTree().setCellRenderer(new DbObjectTreeCellRenderer());
        TreeModel treeModel = new DefaultTreeModel(elem);
        getCentralTree().setModel(treeModel);

        getCentralTree().getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                updatePropertySheet(getCentralTree().getLastSelectedPathComponent());
            }
        });

        getCentralTree().addMouseListener(new MouseEventHandler());
/*
                new MouseAdapter() {
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
        };
*/


        getSplitPane().addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals(JSplitPane.DIVIDER_LOCATION_PROPERTY) && !locked) {
                    int location = (Integer) evt.getNewValue();
                    for (Test501Listener l : listeners) {
                        l.dividerLoactionChanged(getRootPanel(), location);
                    }
                }
            }
        });
    }

    public abstract JComponent getRootPanel();

    public abstract JPanel getTopPanel();

    public abstract JTree getCentralTree();

    public abstract JSplitPane getSplitPane();

    protected abstract void updatePropertySheet(Object lastSelectedComponent);


    public void setDividerLocation(int location) {
        try {
            locked = true;
            getSplitPane().setDividerLocation(location);
        } finally {
            locked = false;
        }
    }

    public int getDividerLocation() {
        return getSplitPane().getDividerLocation();
    }


/*
    protected class DbObjectTreeCellRenderer extends DefaultTreeCellRenderer {

        public DbObjectTreeCellRenderer() {
            setOpaque(false);
        }

        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean isSelected, boolean isExpanded, boolean isLeaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, isSelected, isExpanded, isLeaf, row, hasFocus);
            if (value instanceof DbTreeElement) {
                DbTreeElement el = (DbTreeElement) value;
                el.render(this);
            }
            return this;
        }

        public void paint(Graphics g) {
            super.paint(g);

            int xOffset = 0;
            final Icon icon = getIcon();
            if(icon != null){
                Insets myIpad = new Insets(0, 0, 0, 0);
                int iconTextGap = getIconTextGap();
                xOffset += myIpad.left + icon.getIconWidth() + iconTextGap;

            }
            g.setColor(Color.RED);
            Font font = getFont();
            String fragment = getText();
            final FontMetrics metrics = getFontMetrics(font);
            final int fragmentWidth = metrics.stringWidth(fragment);
            final int textBaseline = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            final int wavedAt = textBaseline + 1;
            for (int x = xOffset; x <= xOffset + fragmentWidth; x += 4) {
              UIUtil.drawLine(g, x, wavedAt, x + 2, wavedAt + 2);
              UIUtil.drawLine(g, x + 3, wavedAt + 1, x + 4, wavedAt);
            }

        }
    }
*/


    public abstract class LocalToggleAction extends ToggleAction {
        protected int command;

        public LocalToggleAction(String actionName, String toolTip, Icon icon, int command) {
            super(actionName, toolTip, icon);
            boolean enabled = true;
            this.getTemplatePresentation().setEnabled(enabled);
            this.command = command;
        }

        public boolean isSelected(AnActionEvent event) {
            return false;
        }

/*
        public void setSelected(AnActionEvent event, boolean b) {
// todo --
//            handleAction(command);
        }
*/
    }

}
