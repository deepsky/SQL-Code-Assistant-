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

package com.deepsky.lang.plsql.resolver.index;

import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.validation.ValidationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.*;

public class IndexTreeBase implements IndexTree {

    //protected TreeNodeImpl root = new TreeNodeImpl("");

    public TreeNodeRootImpl root = new TreeNodeRootImpl("");
    int entryCounter = 0;
    long modCount = 0;
    Writer logger;

    Map<String, FileEntitiesHolder> files = new HashMap<String, FileEntitiesHolder>();


    public IndexTreeBase(Writer logger) {
        this.logger = logger;
    }

    public IndexTreeBase() {
    }

    private void println(String s) {
        if (logger != null) {
            try {
                logger.append(s).append("\n");
                logger.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public TreeNodeRoot getRoot() {
        return root;
    }

    public void cleanUp() {
        files.clear();
        root.getChilds().clear();
    }

    public String _getContextPathValue(String ctxPath) {
        String[] path = ctxPath.split("/");

        int startWith = ("/" + path[1]).startsWith(ContextPath.FILE_CTX_PRX) ? 2 : 1;
        if (startWith == path.length) {
            FileEntitiesHolder hld = files.get(path[1].substring(6));
            if (hld != null) {
                return hld.value; //Long.toString(hld.timeStamp); //
            }

            return null;
        }

        TreeNode cycled = root;
//        List<TreeNode> addTo = new ArrayList<TreeNode>();
        for (int i = startWith; i < path.length; i++) {
            String encodedName = "/" + path[i];
/*
todo - should be implemented along with fixing of the addContextPath
            cycled.findChildByEncodedName(encodedName, addTo);
            switch(addTo.size()){
                case 0: return null;
                case 1:
                    cycled = addTo.get(0);
                    break;
                default:
                    cycled = addTo.get(0);
                    break;
            }

            addTo.clear();
*/
            cycled = cycled.findChildByEncodedName(encodedName);
            if (cycled == null) {
                return null;
            }
        }

        return cycled.getValue();
    }


    public String getContextPathValue(String ctxPath) {
        // todo -- replace split("/") with Pattern.split("/")
        String[] path = ctxPath.split("/");

        int startWith = ("/" + path[1]).startsWith(ContextPath.FILE_CTX_PRX) ? 2 : 1;
        if (startWith == path.length) {
            FileEntitiesHolder hld = files.get(path[1].substring(6));
            if (hld != null) {
                return hld.value; //Long.toString(hld.timeStamp); //
            }

            return null;
        }

        // expected: startWith == 2
        String filePath = path[1].substring(6);

        // find top nodes the specified name
        // "/" + path[2]
        String encodedName = "/" + path[2];
        String name = path[2].substring(6);
        List<TreeNode> addTo = new ArrayList<TreeNode>();
        root.findChildrenBySuffix(name, addTo);

        for (TreeNode node : addTo) {
            // find among top nodes the one which belongs to the correct file
            final TreeNodeTop top = (TreeNodeTop) node;
            if( encodedName.equals(top.encodedName) && filePath.equals(top.owner.filePath)){
                // the found top node is considered as a root of target hierarhy
                TreeNode cycled = node;
                for (int i = startWith + 1; i < path.length; i++) {
                    encodedName = "/" + path[i];
                    cycled = cycled.findChildByEncodedName(encodedName);
                    if (cycled == null) {
                        return null;
                    }
                }

                return cycled.getValue();
            } else {
                // todo -- investige the case
                int hh = 0;
            }
        }

        return null;
    }


    //  /FL!..$ota_flow_manager_pkg.pks/PS!..$ota_flow_manager_pkg/Va!..$pc_bs_15m_dflt [Value] 1|INTEGER
    public void addContextPath(String ctxPath, String value) {
        String[] path = ctxPath.split("/");

        // validate root context
        if (!("/" + path[1]).startsWith(ContextPath.FILE_CTX_PRX) || ctxPath.indexOf(' ') != -1) {
            throw new ValidationException("Broken Context Path occurred: FILE CTX Path must be first!");
        } else {
            switch (path.length) {
                case 0:
                case 1:
                    throw new ValidationException("Broken Context Path occurred");
                case 2: // load file attributes
                    String fileName = path[1].substring(6);
                    FileEntitiesHolder fileHolder = files.get(fileName);
                    if (fileHolder == null) {
                        fileHolder = new FileEntitiesHolder(fileName);
                        files.put(fileName, fileHolder);
                    }
                    value = (value == null || value.length() == 0) ? "" : value;
                    fileHolder.value = value;
                    return;
                default:
                    break;
            }
        }

        String encodedName = "/" + path[2];
        String filePath = path[1].substring(6);
        String name = path[2].substring(6); //, path[2].length());
        // try to find existing top node first
        List<TreeNode> addTo = new ArrayList<TreeNode>();
        root.findChildrenBySuffix(name, addTo);
        for (TreeNode node : addTo) {
            final TreeNodeTop top = (TreeNodeTop) node;
            if( encodedName.equals(top.encodedName) && filePath.equals(top.owner.filePath)){
                // top node found
                addCtxValue(top, path, value);
                return;
            }
        }

        // top node not found, so create one
        int type = ContextPathUtil.prefix2type(encodedName.substring(0, 4));
        TreeNode top = addTopNode(root, type, encodedName, filePath);
        addCtxValue(top, path, value);

        // log adding entry
        println("[Path] " + ctxPath + " [Value] " + value);
    }

    public boolean updateCtxPathValue(String ctxPath, String value) {
        String[] path = ctxPath.split("/");

        // validate root context
        if (!("/" + path[1]).startsWith(ContextPath.FILE_CTX_PRX) || ctxPath.indexOf(' ') != -1) {
            throw new ValidationException("Broken Context Path occurred: FILE CTX Path must be first!");
        } else {
            switch (path.length) {
                case 0:
                case 1:
                    throw new ValidationException("Broken Context Path occurred");
                case 2: // load file attributes
                    String fileName = path[1].substring(6);
                    FileEntitiesHolder fileHolder = files.get(fileName);
                    if (fileHolder == null) {
                        fileHolder = new FileEntitiesHolder(fileName);
                        files.put(fileName, fileHolder);
                    }
                    value = (value == null || value.length() == 0) ? "" : value;
                    fileHolder.value = value;
                    return true;
                default:
                    break;
            }
        }

        String encodedName = "/" + path[2];
        String filePath = path[1].substring(6);
        String name = path[2].substring(6); //, path[2].length());
        // try to find existing top node first
        List<TreeNode> addTo = new ArrayList<TreeNode>();
        root.findChildrenBySuffix(name, addTo);
        for (TreeNode node : addTo) {
            final TreeNodeTop top = (TreeNodeTop) node;
            if( encodedName.equals(top.encodedName) && filePath.equals(top.owner.filePath)){
                // top node found
                return updateCtxValue(top, path, value);
            }
        }

        // top node not found
        return false;
    }

    private boolean updateCtxValue(TreeNode cycled, String[] pathParts, String value){
        for (int i = 2+1; i < pathParts.length; i++) {
            String encodedName = "/" + pathParts[i];
            TreeNode child = cycled.findChildByEncodedName(encodedName);
            if (child == null) {
                return false;
            } else {
                cycled = child;
            }
        }

        cycled.addValue(value);
        return true;
    }

    private void addCtxValue(TreeNode cycled, String[] pathParts, String value){
        boolean pathCreated = false;
        for (int i = 2+1; i < pathParts.length; i++) {
            String encodedName = "/" + pathParts[i];
            TreeNode child = cycled.findChildByEncodedName(encodedName);
            if (child == null) {
                int type = ContextPathUtil.prefix2type(encodedName.substring(0, 4));
                cycled = cycled.addNode(type, encodedName);
                pathCreated = true;
            } else {
                cycled = child;
            }
        }


        if (pathCreated) {
            entryCounter++;
        }
        if (value != null) {
            cycled.addValue(value);
            modCount++;
        } else {
            // check whether the path was really created
            modCount = (pathCreated) ? modCount + 1 : modCount;
        }
    }


    //  /FL!..$ota_flow_manager_pkg.pks/PS!..$ota_flow_manager_pkg/Va!..$pc_bs_15m_dflt [Value] 1|INTEGER
    public void _addContextPath(String ctxPath, String value) {
        String[] path = ctxPath.split("/");

        // validate root context
        if (!("/" + path[1]).startsWith(ContextPath.FILE_CTX_PRX) || ctxPath.indexOf(' ') != -1) {
            throw new ValidationException("Broken Context Path occurred: FILE CTX Path must be first!");
        } else {
            switch (path.length) {
                case 0:
                case 1:
                    throw new ValidationException("Broken Context Path occurred");
                case 2: // load file attributes
                    String fileName = path[1].substring(6);
                    FileEntitiesHolder fileHolder = files.get(fileName);
                    if (fileHolder == null) {
                        fileHolder = new FileEntitiesHolder(fileName);
                        files.put(fileName, fileHolder);
                    }
                    value = (value == null || value.length() == 0) ? "" : value;
                    fileHolder.value = value;
                    return;
                default:
                    break;
            }
        }

        TreeNode cycled = root;
        boolean pathCreated = false;
        for (int i = 2; i < path.length; i++) {
            String encodedName = "/" + path[i];
            int type = ContextPathUtil.prefix2type(encodedName.substring(0, 4));
            TreeNode child = cycled.findChildByEncodedName(encodedName);
            if (child == null) {
                if (i == 2) {
                    // add node level 1 -
                    cycled = addTopNode(root, type, encodedName, path[1].substring(6));
                } else {
                    cycled = cycled.addNode(type, encodedName);
                }
                pathCreated = true;
            } else {
                cycled = child;
            }
        }

/*      todo -- entryCounter not correct any longer, FIXME 
        if (cycled.getValue() == null) {
            entryCounter++;
        }
        cycled.addValue(value == null ? "" : value);
*/
        if (pathCreated) {
            entryCounter++;
        }
        if (value != null) {
            cycled.addValue(value);
            modCount++;
        } else {
            // check whether the path was really created
            modCount = (pathCreated) ? modCount + 1 : modCount;
        }

        // log adding entry
        println("[Path] " + ctxPath + " [Value] " + value);
    }


    public String getFileAttribute(String _fileName, String attributeName) {
        String fileName = _fileName.replace(' ', '?').replace('\\', '|').replace('/', '|');
        FileEntitiesHolder fileHolder = files.get(fileName);
        if (fileHolder != null && fileHolder.value != null) {
            String[] attrs = fileHolder.value.split("\\|");
            for (String a : attrs) {
                String[] name_value_pair = a.split("\\=");
                if (name_value_pair[0].equals(attributeName)) {
                    return (name_value_pair.length > 1) ? name_value_pair[1] : null;
                }
            }
        }
        return null;
    }

    public void setFileAttribute(String _fileName, String attributeName, String value) {
        String fileName = _fileName.replace(' ', '?').replace('\\', '|').replace('/', '|');
        FileEntitiesHolder fileHolder = files.get(fileName);
        if (fileHolder == null) {
            fileHolder = new FileEntitiesHolder(fileName);
            files.put(fileName, fileHolder);
        }

        if (fileHolder.value != null) {
            String[] attrs = fileHolder.value.split("\\|");
            int index = -1, i = 0;
            for (String a : attrs) {
                String[] name_value_pair = a.split("\\=");
                if (name_value_pair[0].equals(attributeName)) {
                    if (name_value_pair.length > 1) {
                        String updatedPair = name_value_pair[0] + "=" + ((value == null) ? "" : value);
                        attrs[i] = updatedPair;
                    }
                    index = i;
                    break;
                }
                i++;
            }

            if (index == -1) {
                fileHolder.value = fileHolder.value + "|" + attributeName + "=" + ((value == null) ? "" : value);
            } else {
                StringBuffer buff = new StringBuffer();
                for (String a : attrs) {
                    if (buff.length() > 0) {
                        buff.append("|");
                    }
                    buff.append(a);
                }

                fileHolder.value = buff.toString();
            }
        } else {
            fileHolder.value = attributeName + "=" + ((value == null) ? "" : value);
        }

        modCount++;
    }

    public void changeFileName(String oldFileName, String newFileName) {
        String fileName = oldFileName.replace(' ', '?').replace('\\', '|').replace('/', '|');
        FileEntitiesHolder fileHolder = files.remove(fileName);
        if (fileHolder == null) {
            // file not found
            return;
        }

        String _newfileName = newFileName.replace(' ', '?').replace('\\', '|').replace('/', '|');
        fileHolder.filePath = _newfileName;
        files.put(_newfileName, fileHolder);
    }

    public boolean setContextPathAttr(String startCtxPath, String attrName, String value) {
        String[] path = startCtxPath.split("/");
        int startWith = ("/" + path[1]).startsWith(ContextPath.FILE_CTX_PRX) ? 2 : 1;

        TreeNode cycled = root;
        for (int i = startWith; i < path.length; i++) {
            String encodedName = "/" + path[i];
            cycled = cycled.findChildByEncodedName(encodedName);
            if (cycled == null) {
                return false;
            }
        }

        TreeNodeImpl nodeImpl = (TreeNodeImpl) cycled;
        int i = 0;
        for (; i < nodeImpl.extAttr.length; i++) {
            String _attrName = decodeAttrName(nodeImpl.extAttr[i]);
            if (_attrName.equals(attrName)) {
                // replace existing attribute with new one
                nodeImpl.extAttr[i] = encodeAttrValue(attrName, value);
                return true;
            }
        }
        nodeImpl.extAttr = increaseArrayByOne(nodeImpl.extAttr);
        // replace existing attribute with new one
        nodeImpl.extAttr[i] = encodeAttrValue(attrName, value);
        return true;
    }

    // <attr_name_size><attr_name>|<value>
    // <attr_name_size> is size of two digits
    // example: "09timestamp|....."
    private String decodeAttrName(String value) {
        int len = value.length();
        if (len < 4) {
            return ""; // not valid attribute
        }

        try {
            int size = Integer.parseInt(value.substring(0, 2));
            return len >= size + 2 + 1 ? value.substring(2, 2 + size) : "";
        } catch (NumberFormatException e) {
            // ignore
        }
        return "";
    }

    private String[] increaseArrayByOne(String[] extAttr) {
        String[] newExtAttr = new String[extAttr.length + 1];
        System.arraycopy(extAttr, 0, newExtAttr, 0, extAttr.length);
        return newExtAttr;
    }

    private String encodeAttrValue(String attrName, String value) {
        String attrLen;
        if (attrName.length() > 99) {
            throw new ConfigurationException("Attribute name is too long: " + attrName);
        } else if (attrName.length() <= 9) {
            attrLen = "0" + Integer.toString(attrName.length());
        } else {
            // attribute name exceed 9 characters
            attrLen = Integer.toString(attrName.length());
        }

        return attrLen + attrName + "|" + value;
    }

    private String decodeAttrValue(String value) {
        int len = value.length();
        if (len < 4) {
            return ""; // not valid attribute
        }

        try {
            int size = Integer.parseInt(value.substring(0, 2));
            return len >= size + 2 + 1 ? value.substring(2 + size + 1) : "";
        } catch (NumberFormatException e) {
            // ignore
        }
        return "";
    }

    public String getContextPathAttr(String startCtxPath, String attrName) {
        String[] path = startCtxPath.split("/");
        int startWith = ("/" + path[1]).startsWith(ContextPath.FILE_CTX_PRX) ? 2 : 1;

        TreeNode cycled = root;
        for (int i = startWith; i < path.length; i++) {
            String encodedName = "/" + path[i];
            cycled = cycled.findChildByEncodedName(encodedName);
            if (cycled == null) {
                return null;
            }
        }

        TreeNodeImpl nodeImpl = (TreeNodeImpl) cycled;
        int i = 0;
        for (; i < nodeImpl.extAttr.length; i++) {
            String _attrName = decodeAttrName(nodeImpl.extAttr[i]);
            if (_attrName.equals(attrName)) {
                // replace existing attribute with new one
                return decodeAttrValue(nodeImpl.extAttr[i]);
            }
        }
        return null;
    }


    private TreeNode addTopNode(TreeNodeImpl _root, int type, String encodedName, String filePath) {
        String name = encodedName.substring(7, encodedName.length());

        List<TreeNodeImpl> lst = _root.childs.get(name);
        if (lst == null) {
            lst = new ArrayList<TreeNodeImpl>();
            _root.childs.put(name, lst);
        }

        FileEntitiesHolder fh = files.get(filePath);
        if (fh == null) {
            fh = new FileEntitiesHolder(filePath);
            files.put(filePath, fh);
        }

        TreeNodeTop node = new TreeNodeTop(_root, encodedName, fh);
        node.type = type;
        lst.add(node);

        return node;
    }

    public int getEntriesCount() {
        return entryCounter;
    }


    public TreeNode[] findNodeInContext(String startCtxPath, @Nullable final String name) {
        final List<TreeNode> out = new ArrayList<TreeNode>();
        root.iterateThruContext(startCtxPath, new TreeNodeRoot.TreeNodeHandler2(){
            public boolean handleNode(int ctxPathCounter, TreeNode node) {
                if(name == null || node.getName().equalsIgnoreCase(name)){
                    out.add(node);
                }
                return true;
            }
        });

        return out.toArray(new TreeNode[out.size()]);
/*
        String[] path = startCtxPath.split("/");
        int startWith = ("/" + path[1]).startsWith(ContextPath.FILE_CTX_PRX) ? 2 : 1;

        TreeNode cycled = root;
        for (int i = startWith; i < path.length; i++) {
            String encodedName = "/" + path[i];
            cycled = cycled.findChildByEncodedName(encodedName);
            if (cycled == null) {
                return new TreeNode[0];
            }
        }

        return cycled.findChildrenBySuffix(name);
*/
    }

    private List<Integer> primitiveArrayToObjectList(int[] ctxTypes){
        List<Integer> out = new ArrayList<Integer>(ctxTypes!= null? ctxTypes.length: 0);
        for(int i=0; i<out.size(); i++){
            out.add(ctxTypes[i]);
        }
        return out;
    }

    public TreeNode[] findNodeInContext(final String ctxPath, final int[] ctxTypes){
//        final List<Integer>  types = primitiveArrayToObjectList(ctxTypes);
        final List<TreeNode> out = new ArrayList<TreeNode>();
        root.iterateThruContext(ctxPath, new TreeNodeRoot.TreeNodeHandler2(){
            public boolean handleNode(int ctxPathCounter, TreeNode node) {
                if(node.getPath().equals(ctxPath)){
                    out.addAll(Arrays.asList(node.findChildrenByTypes(ctxTypes)));
                    return false;
                }
//                if(types.size() == 0 || types.contains(node.getType())){
//                    out.add(node);
//                }
                return true;
            }
        });

        return out.toArray(new TreeNode[out.size()]);
    }


    public TreeNode[] findNodeInRelContext(String startCtxPath, String name) {
        String[] path = startCtxPath.split("/");

        TreeNode cycled = root;
        for (int i = 1; i < path.length; i++) {
            String encodedName = "/" + path[i];
            cycled = cycled.findChildByEncodedName(encodedName);
            if (cycled == null) {
                return new TreeNode[0];
            }
        }

        return cycled.findChildrenBySuffix(name);
    }

    public void findNodesInContextCommon(String startCtxPath, String name, @NotNull TreeNode.TreeNodeHandler handler) {
        if (startCtxPath != null) {
            String[] path = startCtxPath.split("/");
            int startWith = ("/" + path[1]).startsWith(ContextPath.FILE_CTX_PRX) ? 2 : 1;
            TreeNode cycled = root;
            for (int i = startWith; i < path.length; i++) {
                String encodedName = "/" + path[i];
                cycled = cycled.findChildByEncodedName(encodedName);
                if (cycled == null) {
                    return; // new TreeNode[0];
                }
            }
            cycled.iterateOverChildrenWithSuffix(name, handler);
        } else {
            root.iterateOverChildrenWithSuffix(name, handler);
        }
    }


    public TreeNode[] findNodeInRootContext(String name) {
        return root.findChildrenBySuffix(name);
    }


    public ContextItem[] findInRootContext(int[] types) {
        List<ContextItem> out = new ArrayList<ContextItem>();
        for (TreeNode n : root.findChildrenByTypes(types)) {
            // todo -- revise to replace using value directly with a TreeNode instance
            out.add(new ContextItemImpl(n.getPath(), n.getValue()));
        }

        return out.toArray(new ContextItem[out.size()]);
    }

    public void searchContextTree_DownUp(String startCtxPath, String name, int refType, TreeNodeHandler handler) {
        String[] path = startCtxPath.split("/");

        int _1st_ctxType = -1;
        int startWith = ("/" + path[1]).startsWith(ContextPath.FILE_CTX_PRX) ? 2 : 1;
        List<TreeNode> out = new ArrayList<TreeNode>();

        TreeNode cycled = root;
        for (int i = startWith; i < path.length; i++) {
            String encodedName = "/" + path[i];
            int ctxType = ContextPathUtil.prefix2type(encodedName.substring(0, 4));
            if (i == startWith) {
                // save the type of the first context
                _1st_ctxType = ctxType;
            }

            cycled = cycled.findChildByEncodedName(encodedName);
            if (cycled == null) {
                // todo -- is it suggested?
                break;
            } else {
                if (i == path.length - 1) {
                    // check type of the last context
                    switch (refType) {
                        case ContextPath.NESTED_CALL:
                            // search for the name in the context only
                            for (TreeNode d : cycled.findChildrenBySuffix(name)) {
                                handler.handleNode(d);
                            }
                            return;
                        default:
                            switch (ctxType) {
                                case ContextPath.COLUMN_DEF:
                                case ContextPath.RECORD_ITEM:
                                case ContextPath.VARIABLE_DECL:
                                case ContextPath.ARGUMENT:
                                case ContextPath.REF_CURSOR:
                                    // skip search in the context
                                    // todo -- more types expected
                                    continue;
                                default:
                                    break;
                            }
                    }
                } else {
                    // intermediate context, add to list
                }

                out.add(cycled);
            }
        }

        for (int i = out.size() - 1; i >= 0; i--) {
            TreeNode n = out.get(i);
            for (TreeNode foundNode : n.findChildrenBySuffix(name)) {
                if (handler.handleNode(foundNode)) {
                    // iteration terminated by listener
                    return;
                }
            }

//            switch(n.getType()){
//                case ContextPath.CURSOR_DECL:
//                    // Cursor decl: closed scope
//                    return;
//            }
        }

        if (_1st_ctxType == ContextPath.PACKAGE_BODY) {
            // search package spec for name
            String spec = ContextPath.PACKAGE_SPEC_PRX + path[startWith].substring(3, path[startWith].length());
            for (TreeNode pks : findNodeInRelContext(spec, name)) {//findInContext2(spec, name)) {
                if (handler.handleNode(pks)) {
                    // iteration terminated by listener
                    return;
                }
            }
        }

        switch (refType) {
            case ContextPath.FUNC_CALL:
            case ContextPath.PROC_CALL:
            case ContextPath.TABLE_REF:
            case ContextPath.TYPE_REF:
            case ContextPath.GENERIC_NAME_REF: // package name (coming from sql code)
            case ContextPath.PLSQL_NAME_REF: // package name (coming from pl/sql code)
                // todo -- do we need to limit target ref types?
                // search in the root context
                for (TreeNode d : findNodeInRootContext(name)) {
                    if (handler.handleNode(d)) {
                        // iteration terminated by listener
                        return;
                    }
                }
                break;
        }
    }

    final private static String NO_VAL = "$empty$";

    private String buildAppendix(String value, String[] attrs) {

        StringBuilder sb = new StringBuilder();
        if (value == null || value.length() == 0) {
            if (attrs.length == 0) {
                // neighter value nor attributes
                return "";
            }
            sb.append(NO_VAL);
        } else {
            sb.append(value);
        }

        for (String attr : attrs) {
            sb.append(" ").append(attr);
        }
        return sb.toString();
    }

    public int dumpNames(String fileName) throws IOException {
        File f = new File(fileName);

        final int[] entries = {0};
        final Writer out = new FileWriter(f);
        iterateThru(new IndexEntriesWalker() {
            public void process(String ctxPath, String value, String[] attrs) {
                try {
                    out.write(ctxPath + " " + buildAppendix(value, attrs) + "\n");
                    entries[0]++;
                } catch (IOException e) {
                    throw new Error(e);
                }
            }
        });

        iterateFileNames(new IndexEntriesWalkerInterruptable() {
            public boolean process(String ctxPath, String value) {
                try {
                    out.write(ctxPath + " " + (value == null || value.length() == 0 ? NO_VAL : value) + "\n");
                    entries[0]++;
                    return true;
                } catch (IOException e) {
                    throw new Error(e);
                }
            }
        });

        out.close();
        return entries[0];
    }


    public int dumpNames(String fileName, final IndexEntryFilter filter) throws IOException {
        if (filter == null) {
            return dumpNames(fileName);
        }

        File f = new File(fileName);

        final int[] entries = {0};
        final Writer out = new FileWriter(f);
        iterateThru(new IndexEntriesWalker() {
            public void process(String ctxPath, String value, String[] attrs) {
                try {
                    if (filter.accept(ctxPath, value)) {
                        out.write(ctxPath + " " + buildAppendix(value, attrs) + "\n");
                        entries[0]++;
                    }
                } catch (IOException e) {
                    throw new Error(e);
                }
            }
        });

        iterateFileNames(new IndexEntriesWalkerInterruptable() {
            public boolean process(String ctxPath, String value) {
                try {
                    out.write(ctxPath + " " + (value == null || value.length() == 0 ? NO_VAL : value) + "\n");
                    entries[0]++;
                    return true;
                } catch (IOException e) {
                    throw new Error(e);
                }
            }
        });

        out.close();
        return entries[0];
    }


    public int loadNames(String fileName) throws IOException {
        File f = new File(fileName);
        int entries = 0;
        long modCountLocal = modCount;
        BufferedReader r = new BufferedReader(new FileReader(f));
        String line;
        while ((line = r.readLine()) != null) {
            String[] path_value = line.split(" +");
            try {
                switch (path_value.length) {
                    case 2:
                        addContextPath(path_value[0], NO_VAL.equals(path_value[1]) ? "" : path_value[1]);
                        entries++;
                        break;
                    case 1:
                        addContextPath(path_value[0], "");
                        entries++;
                        break;
                    case 0:
                        break;
                    default:
                        addContextPath(path_value[0], NO_VAL.equals(path_value[1]) ? "" : path_value[1]);
                        // ext attributes assumed
                        for (int i = 2; i < path_value.length; i++) {
                            String name = decodeAttrName(path_value[i]);
                            if (name.length() > 0) {
                                setContextPathAttr(path_value[0], name, decodeAttrValue(path_value[i]));
                            }
                        }
                        break;
                }
            } catch (Throwable e) {
                // todo
                int kk = 0;
            }
        }

        modCount = modCountLocal;
        r.close();
        return entries;
    }

    public int loadNames(InputStream in) throws IOException {
        int entries = 0;
        long modCountLocal = modCount;
        BufferedReader r = new BufferedReader(new InputStreamReader(in, "US-ASCII"));
        String line;
        while ((line = r.readLine()) != null) {
            String[] path_value = line.split(" +");
            try {
                switch (path_value.length) {
                    case 2:
                        addContextPath(path_value[0], NO_VAL.equals(path_value[1]) ? "" : path_value[1]);
                        entries++;
                        break;
                    case 1:
                        addContextPath(path_value[0], "");
                        entries++;
                        break;
                    case 0:
                        break;
                    default:
                        addContextPath(path_value[0], NO_VAL.equals(path_value[1]) ? "" : path_value[1]);
                        // ext attributes assumed
                        for (int i = 2; i < path_value.length; i++) {
                            String name = decodeAttrName(path_value[i]);
                            if (name.length() > 0) {
                                setContextPathAttr(path_value[0], name, decodeAttrValue(path_value[i]));
                            }
                        }
                        break;
                }
            } catch (Throwable e) {
                // todo
                int kk = 0;
            }
        }

        modCount = modCountLocal;
        r.close();
        return entries;
    }

    public synchronized void clear() {
        this.root.childs.clear();
        this.files.clear();
    }


    public void iterateTopNodes(IndexEntriesWalkerInterruptable iproc) {
        // iterate thru children
        for (Map.Entry<String, List<TreeNodeImpl>> item : root.childs.entrySet()) {
            for (TreeNode n : item.getValue()) {
                if (!iproc.process(n.getPath(), n.getValue())) {
                    return;
                }
            }
        }
    }


    public void iterateTopNodes(String name, IndexEntriesWalkerInterruptable iproc) {
        List<TreeNodeImpl> list = root.childs.get(name.toLowerCase());
        if (list != null) {
            for (TreeNode n : list) {
                if (!iproc.process(n.getPath(), n.getValue())) {
                    return;
                }
            }
        }
    }


    public ContextItem[] findCtxItems(int[] ctxTypes, String name) {
        List<ContextItem> out = new ArrayList<ContextItem>();
        // todo -- subject to simplify
        if (name == null) {
            for (List<TreeNodeImpl> children : root.childs.values()) {
                for (TreeNode n : children) {
                    if (hitted(n.getType(), ctxTypes)) {
                        out.add(new ContextItemImpl(n.getPath(), n.getValue()));
                    }
                }
            }
        } else {
            List<TreeNodeImpl> children = root.childs.get(name);
            if (children != null) {
                for (TreeNode n : children) {
                    if (hitted(n.getType(), ctxTypes)) {
                        out.add(new ContextItemImpl(n.getPath(), n.getValue()));
                    }
                }
            }
        }

        return out.toArray(new ContextItem[out.size()]);
    }


    @NotNull
    public ContextItem[] findCtxItems(String startCtxPath, int[] ctxTypes) {
        String[] path = startCtxPath.split("/");
        int startWith = ("/" + path[1]).startsWith(ContextPath.FILE_CTX_PRX) ? 2 : 1;

        TreeNode cycled = root;
        for (int i = startWith; i < path.length; i++) {
            String encodedName = "/" + path[i];
            cycled = cycled.findChildByEncodedName(encodedName);
            if (cycled == null) {
                return new ContextItem[0];
            }
        }

        List<ContextItem> out = new ArrayList<ContextItem>();
        for (TreeNode n : cycled.findChildrenByTypes(ctxTypes)) {
            // todo -- revise to replace using value directly with a TreeNode instance
            out.add(new ContextItemImpl(n.getPath(), n.getValue()));
        }

        return out.toArray(new ContextItem[out.size()]);
    }

    @NotNull
    public ContextItem[] findCtxItems(String startCtxPath, String name) {
        String[] path = startCtxPath.split("/");
        int startWith = ("/" + path[1]).startsWith(ContextPath.FILE_CTX_PRX) ? 2 : 1;

        TreeNode cycled = root;
        for (int i = startWith; i < path.length; i++) {
            String encodedName = "/" + path[i];
            cycled = cycled.findChildByEncodedName(encodedName);
            if (cycled == null) {
                return new ContextItem[0];
            }
        }

        List<ContextItem> out = new ArrayList<ContextItem>();
        TreeNode[] children = name == null ?
                cycled.getChildren().toArray(new TreeNode[0]) :
                cycled.findChildrenBySuffix(name);
        for (TreeNode n : children) {
            // todo -- revise to replace using value directly with a TreeNode instance
            out.add(new ContextItemImpl(n.getPath(), n.getValue()));
        }

        return out.toArray(new ContextItem[out.size()]);
    }

    public TreeNodeImpl getRootNode() {
        return root;
    }

    private class ContextItemImpl implements ContextItem {

        String ctxPath;
        String value;

        public ContextItemImpl(String ctxPath, String value) {
            this.ctxPath = ctxPath;
            this.value = value;
        }

        public String getCtxPath() {
            return ctxPath;
        }

        public String getValue() {
            return value;
        }
    }

    public void iterateTopNodesForFile(String _filePath, IndexEntriesWalkerInterruptable iproc) {
        String filePath = _filePath.replace(' ', '?').replace('\\', '|').replace('/', '|');
        FileEntitiesHolder fileHolder = files.get(filePath);
        if (fileHolder != null) {
            for (TreeNodeTop d : fileHolder.nodes) {
                if (!iproc.process(d.getPath(), d.getValue())) {
                    return;
                }
            }
        }
    }


    public boolean fileExists(String _filePath){
        String filePath = _filePath.replace(' ', '?').replace('\\', '|').replace('/', '|');
        return files.get(filePath) != null;
    }

    public void iterateThru(IndexEntriesWalker iproc) {
        findValuedNode(root.childs, iproc);
    }


    public void iterateFileNames(IndexEntriesWalkerInterruptable iproc) {
        for (FileEntitiesHolder d : files.values()) {
//            iproc.process(d.getPath(), Long.toString(d.timeStamp));
            if (!iproc.process(d.getPath(), d.value)) {
                return;
            }
        }
    }


    public void iterateOverChildren(String ctxPath, IndexEntriesWalkerInterruptable iterator) {
        String[] path = ctxPath.split("/");
        int startWith = ("/" + path[1]).startsWith(ContextPath.FILE_CTX_PRX) ? 2 : 1;

        TreeNode cycled = root;
        for (int i = startWith; i < path.length; i++) {
            String encodedName = "/" + path[i];
            cycled = cycled.findChildByEncodedName(encodedName);
            if (cycled == null) {
                return;
            }
        }

        for (List<TreeNodeImpl> siblings : ((TreeNodeImpl) cycled).childs.values()) {
            for (TreeNodeImpl n : siblings) {
                if (!iterator.process(n.getPath(), n.value)) {
                    return;
                }
            }
        }
    }


/*
    public void iterateOverChildren(String ctxPath, IndexEntriesWalker2 iterator) {
        String[] path = ctxPath.split("/");
        int startWith = ("/" + path[1]).startsWith(ContextPath.FILE_CTX_PRX) ? 2 : 1;

        TreeNode cycled = root;
        for (int i = startWith; i < path.length; i++) {
            String encodedName = "/" + path[i];
            cycled = cycled.findChildByEncodedName(encodedName);
            if (cycled == null) {
                return;
            }
        }

        for (List<TreeNodeImpl> siblings : ((TreeNodeImpl) cycled).childs.values()) {
            for (TreeNodeImpl n : siblings) {
                iterator.process(n.getPath(), n.type, n.value, cycled.getPath());
            }
        }
    }
*/

    public void iterateOverChildren(String ctxPath, String name, IndexEntriesWalkerInterruptable iterator) {
        String[] path = ctxPath.split("/");
        int startWith = ("/" + path[1]).startsWith(ContextPath.FILE_CTX_PRX) ? 2 : 1;

        TreeNode cycled = root;
        for (int i = startWith; i < path.length; i++) {
            String encodedName = "/" + path[i];
            cycled = cycled.findChildByEncodedName(encodedName);
            if (cycled == null) {
                return;
            }
        }

        List<TreeNodeImpl> list = ((TreeNodeImpl) cycled).childs.get(name);
        if (list != null) {
            for (TreeNodeImpl n : list) {
                if (!iterator.process(n.getPath(), n.value)) {
                    return;
                }
            }
        }
    }


    private void findValuedNode(Map<String, List<TreeNodeImpl>> children, IndexEntriesWalker iproc) {
        for (Map.Entry<String, List<TreeNodeImpl>> e : children.entrySet()) {
            for (TreeNode n : e.getValue()) {
                findValuedNode((TreeNodeImpl) n, iproc);
            }
        }
    }


    private void findValuedNode(TreeNodeImpl node, IndexEntriesWalker iproc) {
        if (node.childs.size() == 0) {
            iproc.process(node.getPath(), node.value, node.extAttr);
        } else {
            // first call handler for root node then for children
            iproc.process(node.getPath(), node.value, node.extAttr);

            // iterate thru children
            for (String name : node.childs.keySet()) {
                for (TreeNode n : node.childs.get(name)) {
                    findValuedNode((TreeNodeImpl) n, iproc);
                }
            }
        }
    }

    public boolean remove(String ctxPath) {
        String[] path = ctxPath.split("/");

        boolean result = false;
        // validate root context
        if (!("/" + path[1]).startsWith(ContextPath.FILE_CTX_PRX)) {
            throw new ValidationException("Broken Context Path occurred: FILE CTX Path must be first!");
        } else {
            switch (path.length) {
                case 0:
                case 1:
                    throw new ValidationException("Broken Context Path occurred");
                case 2: // remove the file and all its children
                    String fileName = path[1].substring(6);
                    FileEntitiesHolder fileHolder = files.remove(fileName);
                    if (fileHolder != null) {
                        fileHolder.removeChildren();
                        result = true; //root.removeNodeByEncodedName(fileHolder.node.getName());
                    }
                    break;
                default:
                    TreeNode parent = root;
                    TreeNode target = null;
                    int i = 2;
                    while (true) {
                        target = parent.findChildByEncodedName("/" + path[i]);
                        if (++i < path.length && target != null) {
                            parent = target;
                            target = null;
                        } else {
                            break;
                        }
                    }

                    if (target != null) {
                        result = parent.removeNodeByEncodedName(target.getName());
                    } else {
                        // todo -- handle internal error
                    }
                    break;
            }
        }

        // NOTE: do not simplify! modCOunter should be incremented!
        return result ? ++modCount > 0 : false;
    }

    public long getModificationCount() {
        return modCount;
    }

    private final static String[] EMPTY_ATTR_ARRAY = new String[0];

    protected class TreeNodeImpl implements TreeNode {

        protected TreeNodeImpl parent;
        protected String encodedName;
        protected int type;
        protected String value;
        protected String[] extAttr = EMPTY_ATTR_ARRAY;
        protected Map<String, List<TreeNodeImpl>> childs = new HashMap<String, List<TreeNodeImpl>>();

        public TreeNodeImpl(String encodedName) {
            this.encodedName = encodedName;
        }

        public Map<String, List<TreeNodeImpl>> getChilds() {
            return childs;
        }

        public TreeNodeImpl(TreeNodeImpl parent, String encodedName) {
            this.parent = parent;
            this.encodedName = encodedName;
        }

        public TreeNode addNode(int type, String encodedName) {
            String name = encodedName.substring(7, encodedName.length());

            List<TreeNodeImpl> lst = childs.get(name);
            if (lst == null) {
                lst = new ArrayList<TreeNodeImpl>();
                childs.put(name, lst);
            }
            TreeNodeImpl node = new TreeNodeImpl(this, encodedName);
            node.type = type;
            lst.add(node);

            return node;
        }

        public boolean removeNodeByEncodedName(String encodedName) {
            List<TreeNodeImpl> lst = childs.get(encodedName.substring(7, encodedName.length()));
            if (lst != null) {
                Iterator<TreeNodeImpl> iterator = lst.iterator();
                while (iterator.hasNext()) {
                    TreeNodeImpl n = iterator.next();
                    if (n.getName().equals(encodedName)) {
                        n.dispose();
                        iterator.remove();
                        return true;
                    }
                }
            }
            return false;
        }

        protected void dispose() {
            // do nothing by default for non-top nodes
        }

        public void addValue(String value) {
            this.value = value;
        }

        public List<TreeNode> getChildren() {
            List<TreeNode> lst = new ArrayList<TreeNode>();
            for (List<TreeNodeImpl> lst1 : childs.values()) {
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
            return null;
        }

        public void findChildrenBySuffix(String suffix, List<TreeNode> addTo) {
            if (suffix == null) {
                for (List<TreeNodeImpl> lst1 : childs.values()) {
                    for (TreeNodeImpl n : lst1) addTo.add(n);
                }
            } else {
                List<TreeNodeImpl> lst = childs.get(suffix);
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
                return lst.toArray(new TreeNode[lst.size()]);
            } else {
                List<TreeNodeImpl> lst = childs.get(suffix);
                if (lst != null) {
                    return lst.toArray(new TreeNode[lst.size()]);
                }
            }
            return new TreeNode[0];
        }


        public void iterateOverChildrenWithSuffix(String suffix, TreeNodeHandler handler) {
            if (suffix == null) {
                for (List<TreeNodeImpl> lst1 : childs.values()) {
                    for (TreeNodeImpl n : lst1) {
                        handler.handleNode(n);
                    }
                }
            } else {
                List<TreeNodeImpl> lst = childs.get(suffix);
                if (lst != null) {
                    for (TreeNodeImpl n : lst) handler.handleNode(n);
                }
            }
        }

        public TreeNode[] findChildrenByTypes(int[] types) {
            List<TreeNode> out = new ArrayList<TreeNode>();
            for (List<TreeNodeImpl> lst : childs.values()) {
                for (TreeNodeImpl node : lst) {
                    if (hitted(node.type, types)) {
                        out.add(node);
                    }
                }
            }
            return out.toArray(new TreeNode[out.size()]);
        }

        public String getName() {
            return encodedName;
        }

        public String getPath() {
            return (parent != null ? parent.getPath() : "") + encodedName;
        }

        public String getParentPath() {
            return parent.getPath();
        }

        public String getValue() {
            return value;
        }

        public int getType() {
            return type;
        }

        public TreeNodeTop getTopNode(){
            TreeNodeImpl current = this;
            while(current != null){
                if(current instanceof TreeNodeTop){
                    return (TreeNodeTop) current;
                }
                current = current.parent;
            }
            return null;
        }
    }


    protected class TreeNodeTop extends TreeNodeImpl {
        FileEntitiesHolder owner;

        public TreeNodeTop(TreeNodeImpl parent, String encodedName, FileEntitiesHolder owner) {
            super(parent, encodedName);
            this.owner = owner;
            owner.addChild(this);
        }

        protected void dispose() {
            owner.removeChild(this);
        }

        public String getPath() {
            return owner.getPath() + encodedName;
        }

        public String getParentPath() {
            return owner.getPath();
        }
    }


    protected class TreeNodeRootImpl extends TreeNodeImpl implements TreeNodeRoot {
        public TreeNodeRootImpl(String encodedName) {
            super(encodedName);
        }

        public void iterateThruContext(String ctxPath, TreeNodeHandler2 handler) {
            String[] path = ctxPath.split("/");
            final String filePath = "/" + path[1];
            int startWith = filePath.startsWith(ContextPath.FILE_CTX_PRX) ? 2 : 1;
            if(startWith == 1){
                return;
            }

            TreeNode cycled = null;
            int cnt = 0;
            boolean ret = true;
            for (int i = 2; i < path.length && ret; i++) {
                String encodedName = "/" + path[i];
                if(i == 2){
                    cycled = findChildByEncodedName(encodedName, filePath);
                } else {
                    cycled = cycled.findChildByEncodedName(encodedName);
                }

                if(cycled != null){
                    ret = handler.handleNode(cnt++, cycled);
                } else {
                    // todo - ctx path looks not valid, handle it?
                    return;
                }
            }
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
            return null;
        }
    }




    private class FileEntitiesHolder {
        String filePath;
        public List<TreeNodeTop> nodes = new ArrayList<TreeNodeTop>();
        public String value;

        public FileEntitiesHolder(String filePath) {
            this.filePath = filePath;
        }

        public String getPath() {
            return ContextPath.FILE_CTX_PRX + "..$" + filePath;
        }

        public void addChild(TreeNodeTop topNode) {
            nodes.add(topNode);
        }

        public void removeChildren() {
            TreeNodeTop[] subjectToDelete = nodes.toArray(new TreeNodeTop[nodes.size()]);
            removeNodeByEncodedName(subjectToDelete);
//            for (TreeNodeTop n : subjectToDelete) {
//                root.removeNodeByEncodedName(n.getName());
//            }

//            nodes.clear();
        }

        public boolean removeChild(TreeNodeTop treeNodeTop) {
            return nodes.remove(treeNodeTop);
        }
    }


    public void removeNodeByEncodedName(TreeNodeTop[] toDelete) {
        for(TreeNodeTop top: toDelete){
            String name = top.getName().substring(7);
            List<TreeNodeImpl> lst = root.childs.get(name);
            if (lst != null) {
                Iterator<TreeNodeImpl> iterator = lst.iterator();
                while (iterator.hasNext()) {
                    TreeNodeTop n = (TreeNodeTop) iterator.next();
                    if (n.getName().equals(top.getName()) && n.owner.filePath.equals(top.owner.filePath)) {
                        n.dispose();
                        iterator.remove();
                    }
                }
            }
        }
    }


    protected boolean hitted(int type, int[] types) {
        for (int t : types) {
            if (t == type) return true;
        }
        return false;
    }
}
