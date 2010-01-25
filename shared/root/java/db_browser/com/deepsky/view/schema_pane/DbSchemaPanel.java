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

import com.intellij.psi.PsiElement;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.wm.ToolWindow;
import com.deepsky.lang.common.PlSqlProjectComponent;
import com.deepsky.model.DbSchemaTreeModel;
import com.deepsky.view.schema_pane.impl.ConnectionItemListView;
//import com.deepsky.view.obsoleted.PropertySheetHeaderRenderer;

import javax.swing.*;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

public class DbSchemaPanel extends JPanel {

//    private static final Logger LOG = Logger.getInstance(DbSchemaPanel.class.getClass().getName());

    private String _actionTitle;
    private DbSchemaTree _tree;
    private DbSchemaTreeModel _model;
    private PsiElement _rootElement; //, _selectedElement;
    private DbSchemaItemPropertySheetPanel _propertyPanel;
    private ToolWindow _toolWindow;
    private JSplitPane _splitPane;
    private final ViewerTreeSelectionListener _treeSelectionListener = new ViewerTreeSelectionListener();
//    private final PlSqlProjectComponent _projectComponent;
    DBBrowserWindow window;

    public DbSchemaPanel(DBBrowserWindow window) {
        this.window = window;
        buildGUI();
    }

    public void selectRootElement(PsiElement element, String actionTitle) {
        _actionTitle = actionTitle;
        setRootElement(element);
    }

    public void refreshRootElement() {
        selectRootElement(getRootElement(), _actionTitle);
    }

    private void showRootElement() {
//        getToolWindow().setTitle(_actionTitle + " " + getRootElement());
//        resetTree();
    }

    /*
        private void resetTree() {
            Enumeration expandedDescendants = null;
            TreePath path = null;
            if (_model.getRoot() != null) {
                expandedDescendants = _tree.getExpandedDescendants(new TreePath(_model.getRoot()));
                path = _tree.getSelectionModel().getSelectionPath();
            }
            _model = new PsiViewerTreeModel(_projectComponent);
            _model.setRoot(getRootElement());
            _tree.setModel(_model);
            if (expandedDescendants == null)
                return;
            TreePath treePath;
            for (; expandedDescendants.hasMoreElements(); _tree.expandPath(treePath))
                treePath = (TreePath) expandedDescendants.nextElement();

            path = resetSelectionPath(path);
            _tree.setSelectionPath(path);
            _tree.scrollPathToVisible(path);
        }
    */
    private TreePath resetSelectionPath(TreePath path) {
        Object parent = _model.getRoot();
        java.util.List pathList = new ArrayList();
        if (path != null) {
            Object pathArray[] = path.getPath();
            for (int i = 1; i < pathArray.length; i++) {
                Object child = pathArray[i];
                int index = _model.getIndexOfChild(parent, child);
                if (index < 0)
                    if (pathList.size() > 0) {
                        Object newPath[] = pathList.toArray();
                        return new TreePath(newPath);
                    } else {
                        return null;
                    }
                pathList.add(child);
                parent = child;
            }

        }
        return null;
    }

    public void showProperties(boolean showProperties) {
        _splitPane.getBottomComponent().setVisible(showProperties);
        updatePropertySheet();
    }

    private ToolWindow getToolWindow() {
        return _toolWindow;
    }


    public void setToolWindow(ToolWindow toolWindow) {
        this.addAncestorListener(new AncestorListenerImpl());
        _toolWindow = toolWindow;
    }

    private void buildGUI() {
        setLayout(new BorderLayout());
        _tree = new DbSchemaTree(_model);
        _tree.getSelectionModel().addTreeSelectionListener(_treeSelectionListener);

        _tree.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                Component cmp =  e.getComponent();
                if( e.getClickCount() == 2 && cmp instanceof JTree){
                    JTree tree = (JTree) cmp;
                    Object node = tree.getLastSelectedPathComponent();

                    if(node instanceof ItemViewWrapper){
                        ItemViewWrapper treeItem = (ItemViewWrapper) node;
                        treeItem.runDefaultAction();
                    }
                }
            }
        });

        ActionMap actionMap = _tree.getActionMap();
        actionMap.put("EditSource", new AbstractAction("EditSource") {

            public void actionPerformed(ActionEvent e) {
//                DbSchemaPanel.debug("key typed " + e);
//                if (getSelectedElement() == null) {
//                    return;
//                } else {
//                    Editor editor = _caretMover.openInEditor(getSelectedElement());
//                    selectElementAtCaret(editor);
//                    editor.getContentComponent().requestFocus();
//                    return;
//                }
            }

        });
        InputMap inputMap = _tree.getInputMap();
        inputMap.put(KeyStroke.getKeyStroke(115, 0, true), "EditSource");
        _propertyPanel = new DbSchemaItemPropertySheetPanel();

        _splitPane = new JSplitPane(0, new JScrollPane(_tree), _propertyPanel) {

            public void setDividerLocation(int location) {
//                DbSchemaPanel.debug("Divider location changed to " + location + " component below " + (getRightComponent().isVisible() ? "visible" : "not visible"));
                if (getRightComponent().isVisible())
                    window.setSplitDividerLocation(location);
                super.setDividerLocation(location);
            }

        };
        _splitPane.setDividerLocation(window.getSplitDividerLocation());
        add(_splitPane);
    }


    ItemViewWrapper _selectedElement2;

    private void setSelectedElement2(Object element) {
        _selectedElement2 = (ItemViewWrapper) element;
        updatePropertySheet();
    }

    private void updatePropertySheet() {
        if (_selectedElement2 != null)
            _propertyPanel.setTarget(_selectedElement2);
        _propertyPanel.getTable().getTableHeader().setReorderingAllowed(false);
        _splitPane.setDividerLocation(window.getSplitDividerLocation());
    }

    public PsiElement getRootElement() {
        return _rootElement;
    }

    private void setRootElement(PsiElement rootElement) {
        _rootElement = rootElement;
        showRootElement();
    }

    private class ViewerTreeSelectionListener implements TreeSelectionListener {
        public void valueChanged(TreeSelectionEvent e) {
            setSelectedElement2(_tree.getLastSelectedPathComponent());
        }

        private ViewerTreeSelectionListener() {
        }
    }


    class ItemViewListenerImpl implements ItemViewListener {

        public void updated(int command, Object o) {
            if (command == ItemViewListener.ADD_NODE) {
                ItemViewWrapper node = (ItemViewWrapper) o;

                ItemViewWrapper parent = node.getParent(); //parentPath.getLastPathComponent();
                _model.insertNodeInto(parent, parent.getIndex(node)); //getChildren().size() - 1);

                TreePath path = new TreePath(_model.getPathToRoot(node, 0));
                _tree.scrollPathToVisible(path);
            } else if (command == ItemViewListener.REMOVE_NODE) {
                ItemViewWrapper parent = ((ItemViewWrapper)o).getParent();
                _model.removeNodeFromParent((ItemViewWrapper) o); //currentNode);

                TreePath parentPath = new TreePath(_model.getPathToRoot(parent, 0));
                _tree.setSelectionPath(parentPath);
            } else if (command == ItemViewListener.REFRESH_NODE_PROPS) {
                // refresh active element
                TreePath path = new TreePath(_model.getPathToRoot((ItemViewWrapper) o, 0));
                _tree.expandPath(path);// scrollPathToVisible(path);
                _tree.setSelectionPath(path); 
                setSelectedElement2(o);
            }
        }
    }

    /**
     * Tool Window listener
     */
    class AncestorListenerImpl implements AncestorListener {
        public void ancestorAdded(AncestorEvent event) {
            if (_tree.getLastSelectedPathComponent() == null) {
//                LOG.warn("ancestorAdded");
                _model = new DbSchemaTreeModel();

                ItemViewWrapper root = new ConnectionItemListView();
                root.setListener(new ItemViewListenerImpl());

                _model.setRoot(root);
                _tree.setModel(_model);
            } else {
                setSelectedElement2(_tree.getLastSelectedPathComponent());
            }
        }

        public void ancestorRemoved(AncestorEvent event) {
        }

        public void ancestorMoved(AncestorEvent event) {
        }
    }

}
