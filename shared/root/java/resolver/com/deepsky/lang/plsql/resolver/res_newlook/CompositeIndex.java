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

package com.deepsky.lang.plsql.resolver.res_newlook;

import com.deepsky.lang.plsql.resolver.index.IndexTreeBase;
import com.deepsky.lang.plsql.resolver.index.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CompositeIndex extends IndexTreeBase {

    protected Map<String, List<TreeNodeImpl>> childsEx = new HashMap<String, List<TreeNodeImpl>>();

    public CompositeIndex(IndexTreeBase delegate) {
        this.root = new TreeNodeComposite(delegate.root);
    }

    public void setAdditionalIndex(IndexTreeBase itree) {
        if (itree == null) {
            childsEx = new HashMap<String, List<TreeNodeImpl>>();
        } else {
            childsEx = itree.getRootNode().getChilds();
        }
    }


    private class TreeNodeComposite extends TreeNodeRootImpl {

        public TreeNodeComposite(TreeNodeImpl subst) {
            super("");
            childs = subst.getChilds();
        }

        protected TreeNodeTop findChildByEncodedName(String encodedName, String filePath) {
            String name = encodedName.substring(7, encodedName.length());
            List<TreeNodeImpl> lst = childs.get(name);
            if (lst != null) {
                for (TreeNodeImpl node : lst) {
                    TreeNodeTop top = (TreeNodeTop) node;
                    if (top.getName().equals(encodedName) && top.getParentPath().equals(filePath)) {
                        return top;
                    }
                }
            }

            lst = childsEx.get(name);
            if (lst != null) {
                for (TreeNodeImpl node : lst) {
                    TreeNodeTop top = (TreeNodeTop) node;
                    if (top.getName().equals(encodedName) && top.getParentPath().equals(filePath)) {
                        return top;
                    }
                }
            }
            return null;
        }

        public List<TreeNode> getChildren() {
            List<TreeNode> lst = new ArrayList<TreeNode>();
            for (List<TreeNodeImpl> lst1 : childs.values()) {
                lst.addAll(lst1);
            }
            for (List<TreeNodeImpl> lst1 : childsEx.values()) {
                lst.addAll(lst1);
            }
            return lst;
        }

        public TreeNode findChildByEncodedName(String encodedName) {
            String name = encodedName.substring(7, encodedName.length());
            List<TreeNodeImpl> lst = childs.get(name);
            if (lst != null) {
                for (TreeNode node : lst) {
                    if (node.getName().equals(encodedName)) {
                        return node;
                    }
                }
            }

            lst = childsEx.get(name);
            if (lst != null) {
                for (TreeNode node : lst) {
                    if (node.getName().equals(encodedName)) {
                        return node;
                    }
                }
            }
            return null;
        }


        public void findChildrenBySuffix(String suffix, List<TreeNode> addTo) {
            if (suffix == null) {
                for (List<TreeNodeImpl> lst1 : childs.values()) {
                    for (TreeNodeImpl n : lst1) addTo.add(n);
                }
                for (List<TreeNodeImpl> lst1 : childsEx.values()) {
                    for (TreeNodeImpl n : lst1) addTo.add(n);
                }
            } else {
                List<TreeNodeImpl> lst = childs.get(suffix);
                if (lst != null) {
                    for (TreeNodeImpl n : lst) addTo.add(n);
                }
                lst = childsEx.get(suffix);
                if (lst != null) {
                    for (TreeNodeImpl n : lst) addTo.add(n);
                }
            }
        }


        public TreeNode[] findChildrenBySuffix(String suffix) {
            if (suffix == null) {
                List<TreeNode> lst = new ArrayList<TreeNode>();
                for (List<TreeNodeImpl> lst1 : childs.values()) {
                    lst.addAll(lst1);
                }

                for (List<TreeNodeImpl> lst1 : childsEx.values()) {
                    lst.addAll(lst1);
                }

                return lst.toArray(new TreeNode[lst.size()]);
            } else {
                List<TreeNode> out = new ArrayList<TreeNode>();
                List<TreeNodeImpl> lst = childs.get(suffix);
                if (lst != null) {
                    out.addAll(lst);
                }

                lst = childsEx.get(suffix);
                if (lst != null) {
                    out.addAll(lst);
                }
                return out.toArray(new TreeNode[out.size()]);
            }
        }

        public void iterateOverChildrenWithSuffix(String suffix, TreeNodeHandler handler) {
            if (suffix == null) {
                for (List<TreeNodeImpl> lst1 : childs.values()) {
                    for (TreeNodeImpl n : lst1) {
                        handler.handleNode(n);
                    }
                }
                for (List<TreeNodeImpl> lst1 : childsEx.values()) {
                    for (TreeNodeImpl n : lst1) {
                        handler.handleNode(n);
                    }
                }
            } else {
                List<TreeNodeImpl> lst = childs.get(suffix);
                if (lst != null) {
                    for (TreeNodeImpl n : lst) handler.handleNode(n);
                }
                lst = childsEx.get(suffix);
                if (lst != null) {
                    for (TreeNodeImpl n : lst) handler.handleNode(n);
                }
            }
        }


        public TreeNode[] findChildrenByTypes(int[] types) {
            List<TreeNode> out = new ArrayList<TreeNode>();
            for (List<TreeNodeImpl> lst : childs.values()) {
                for (TreeNodeImpl node : lst) {
                    if (hitted(node.getType(), types)) {
                        out.add(node);
                    }
                }
            }

            for (List<TreeNodeImpl> lst : childsEx.values()) {
                for (TreeNodeImpl node : lst) {
                    if (hitted(node.getType(), types)) {
                        out.add(node);
                    }
                }
            }
            return out.toArray(new TreeNode[out.size()]);
        }
    }

}
