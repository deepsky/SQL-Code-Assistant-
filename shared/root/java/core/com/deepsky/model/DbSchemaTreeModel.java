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

package com.deepsky.model;

import com.deepsky.view.schema_pane.ItemViewWrapper;

import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeModelEvent;
import java.util.List;
import java.util.ArrayList;

public class DbSchemaTreeModel implements TreeModel {

    ItemViewWrapper root;
    List<TreeModelListener> listeners = new ArrayList<TreeModelListener>();


    public void setRoot(ItemViewWrapper root) {
        this.root = root;
    }

    public Object getRoot() {
        return root;
    }

    public Object getChild(Object parent, int index) {
        ItemViewWrapper _parent = (ItemViewWrapper) parent;
        return _parent.getChildren().get(index);
    }

    public int getChildCount(Object parent) {
        ItemViewWrapper _parent = (ItemViewWrapper) parent;
        return _parent.getChildren().size();
    }

    public boolean isLeaf(Object node) {
        ItemViewWrapper _node = (ItemViewWrapper) node;
        return _node.getChildren().size() == 0;
    }

    public int getIndexOfChild(Object parent, Object child) {
        ItemViewWrapper _parent = (ItemViewWrapper) parent;
        return _parent.getChildren().indexOf(child);
    }


    /**
     * Invoked this to insert newChild at location index in parents children.
     * This will then message nodesWereInserted to create the appropriate
     * event. This is the preferred way to add children as it will create
     * the appropriate event.
     */
    public void insertNodeInto(ItemViewWrapper parent, int index) {
        int[] newIndexs = new int[1];

        newIndexs[0] = index;
        nodesWereInserted(parent, newIndexs);
    }

    public void removeNodeFromParent(ItemViewWrapper node) {
        ItemViewWrapper parent = node.getParent();

        if(parent == null)
            throw new IllegalArgumentException("node does not have a parent.");

        int[]            childIndex = new int[1];
        Object[]         removedArray = new Object[1];

        childIndex[0] = parent.getIndex(node);
        parent.remove(childIndex[0]);
        removedArray[0] = node;
        nodesWereRemoved(parent, childIndex, removedArray);
    }


    /**
      * Invoke this method after you've removed some TreeNodes from
      * node.  childIndices should be the index of the removed elements and
      * must be sorted in ascending order. And removedChildren should be
      * the array of the children objects that were removed.
      */
    public void nodesWereRemoved(ItemViewWrapper node, int[] childIndices,
                                 Object[] removedChildren) {
        if(node != null && childIndices != null) {
            fireTreeNodesRemoved(this, getPathToRoot(node, 0), childIndices, removedChildren);
        }
    }


    /**
     * Notifies all listeners that have registered interest for
     * notification on this event type.  The event instance
     * is lazily created using the parameters passed into
     * the fire method.
     *
     * @param source the node where elements are being removed
     * @param path the path to the root node
     * @param childIndices the indices of the removed elements
     * @param children the removed elements
     * @see javax.swing.event.EventListenerList
     */
    protected void fireTreeNodesRemoved(Object source, Object[] path, int[] childIndices, Object[] children) {
        // Guaranteed to return a non-null array
        Object[] listeners = this.listeners.toArray(new Object[this.listeners.size()]);//.getListenerList();
        TreeModelEvent e = null;
        
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 1; i >= 0; i--) {
            if (e == null)
                e = new TreeModelEvent(source, path, childIndices, children);
            ((TreeModelListener) listeners[i]).treeNodesRemoved(e);
        }
    }

    /**
     * Invoke this method after you've inserted some TreeNodes into
     * node.  childIndices should be the index of the new elements and
     * must be sorted in ascending order.
     */
    protected void nodesWereInserted(ItemViewWrapper parent, int[] childIndices) {
        if (listeners.size() > 0 && childIndices != null && childIndices.length > 0) {
            int cCount = childIndices.length;
            Object[] newChildren = new Object[cCount];

            for (int counter = 0; counter < cCount; counter++)
                newChildren[counter] = parent.getChildren().get(childIndices[counter]);
            fireTreeNodesInserted(this, getPathToRoot(parent, 0), childIndices, newChildren);
        }
    }


    protected void fireTreeNodesInserted(Object source, Object[] path, int[] childIndices, Object[] children) {
        // Guaranteed to return a non-null array
        Object[] listeners = this.listeners.toArray(new Object[this.listeners.size()]);//.getListenerList();
        TreeModelEvent e = null;
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 1; i >= 0; i--) {
            if (e == null)
                e = new TreeModelEvent(source, path, childIndices, children);
            ((TreeModelListener) listeners[i]).treeNodesInserted(e);
        }
    }


    public ItemViewWrapper[] getPathToRoot(ItemViewWrapper aNode, int depth) {
        ItemViewWrapper[] retNodes;
        // This method recurses, traversing towards the root in order
        // size the array. On the way back, it fills in the nodes,
        // starting from the root and working back to the original node.

        /* Check for null, in case someone passed in a null node, or
           they passed in an element that isn't rooted at root. */
        if (aNode == null) {
            if (depth == 0)
                return null;
            else
                retNodes = new ItemViewWrapper[depth];
        } else {
            depth++;
            if (aNode == root)
                retNodes = new ItemViewWrapper[depth];
            else
                retNodes = getPathToRoot(aNode.getParent(), depth);
            retNodes[retNodes.length - depth] = aNode;
        }
        return retNodes;
    }


    // ----------------------------------------------
    public void valueForPathChanged(TreePath path, Object newValue) {
        int h = 0;
    }


    public void addTreeModelListener(TreeModelListener l) {
        listeners.add(l);
    }

    public void removeTreeModelListener(TreeModelListener l) {
        listeners.remove(l);
    }

}
