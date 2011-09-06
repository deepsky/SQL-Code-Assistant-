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

package com.deepsky.lang.plsql.resolver.legacy;

import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.castor.mapping.ArgumentType;
import com.deepsky.lang.plsql.castor.mapping.Function;
import com.deepsky.lang.plsql.castor.mapping.FunctionList;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.FunctionValidator;
import com.deepsky.lang.plsql.resolver.index.*;
import com.deepsky.lang.plsql.resolver.utils.SysFuncCtxPathBuilder;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RefResolverExpandable extends RefResolverImpl {

    static LoggerProxy log = LoggerProxy.getInstance("RefResolverExpandable");

    IndexTreeBaseExp exp = null;

    public RefResolverExpandable(DbUrl dbUrl, IndexTreeBase itree) {
//    public RefResolverExpandable(String userName, IndexTreeBase itree) {
        super(dbUrl, new IndexTreeBaseExp(itree));
        exp = (IndexTreeBaseExp) this.getItree(); //itree;

        // load SYSTEM functions
        InputStream istream = this.getClass().getClassLoader().getResourceAsStream("resources/sysfunc_list.xml");
        if (istream != null) {
            Reader r = new InputStreamReader(istream);
            loadSystemFunctions(r, itree);
        }
    }

    public void setSynonymTree(IndexTreeBase itree){
        // verify absent of context path collisions
        exp.setAdditionalIndex(itree);
    }


    private static class IndexTreeBaseExp extends IndexTreeBase {

        protected Map<String, List<TreeNodeImpl>> childs2 = new HashMap<String, List<TreeNodeImpl>>();

        // idle by default
        //TreeNodeImpl rootExt = new TreeNodeImpl("");

        public IndexTreeBaseExp(IndexTreeBase itreeBase){
            this.root = new TreeNodeComposite(itreeBase.getRootNode());
        }

        public void setAdditionalIndex(IndexTreeBase itree){
            childs2 = itree.getRootNode().getChilds();
        }

        class TreeNodeComposite extends TreeNodeRootImpl implements TreeNodeRoot {

            public TreeNodeComposite(TreeNodeImpl subst) {
                super("");
                childs = subst.getChilds();
            }
            private TreeNodeComposite(String encodedName) {
                super(encodedName);
            }


            public List<TreeNode> getChildren(){
                List<TreeNode> lst = new ArrayList<TreeNode>();
                for(List<TreeNodeImpl> lst1: childs.values()){
                    lst.addAll(lst1);
                }
                for(List<TreeNodeImpl> lst1: childs2.values()){
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

                lst = childs2.get(name);
                if (lst != null) {
                    for (TreeNode node : lst) {
                        if (node.getName().equals(encodedName)) {
                            return node;
                        }
                    }
                }
                return null;
            }

/*
            public void findChildByEncodedName(String encodedName, List<TreeNode> addTo) {
                String name = encodedName.substring(7, encodedName.length());
                List<TreeNodeImpl> lst = childs.get(name);
                if (lst != null) {
                    for (TreeNode node : lst) {
                        if (node.getName().equals(encodedName)) {
                            addTo.add(node);
                        }
                    }
                }

                lst = childs2.get(name);
                if (lst != null) {
                    for (TreeNode node : lst) {
                        if (node.getName().equals(encodedName)) {
                            addTo.add(node);
                        }
                    }
                }
            }
*/

            public TreeNode[] findChildrenBySuffix(String suffix) {
                if(suffix == null){
                    List<TreeNode> lst = new ArrayList<TreeNode>();
                    for(List<TreeNodeImpl> lst1: childs.values()){
                        lst.addAll(lst1);
                    }

                    for(List<TreeNodeImpl> lst1: childs2.values()){
                        lst.addAll(lst1);
                    }

                    return lst.toArray(new TreeNode[lst.size()]);
                } else {
                    List<TreeNode> out = new ArrayList<TreeNode>();
                    List<TreeNodeImpl> lst = childs.get(suffix);
                    if (lst != null) {
                        out.addAll(lst);
                    }

                    lst = childs2.get(suffix);
                    if (lst != null) {
                        out.addAll(lst);
                    }
                    return out.toArray(new TreeNode[out.size()]);
                }
            }

            public TreeNode[] findChildrenByTypes(int[] types) {
                List<TreeNode> out = new ArrayList<TreeNode>();
                for(List<TreeNodeImpl> lst: childs.values()){
                    for(TreeNodeImpl node: lst){
                        if(hitted(node.getType(), types)){
                            out.add(node);
                        }
                    }
                }

                for(List<TreeNodeImpl> lst: childs2.values()){
                    for(TreeNodeImpl node: lst){
                        if(hitted(node.getType(), types)){
                            out.add(node);
                        }
                    }
                }
                return out.toArray(new TreeNode[out.size()]);
            }

        }

    }


    private void loadSystemFunctions(Reader reader, IndexTree itree) {
        try {
            FunctionList lst = FunctionList.unmarshal(reader);
            SysFuncCtxPathBuilder ctxBld = new SysFuncCtxPathBuilder(ContextPath.FILE_CTX_PRX + "..$system");

            for (Function f : lst.getFunction()) {
                ctxBld.start();
                String name = f.getName();
                ctxBld.setName(name);
                String returnType = f.getReturnType();
                Type type = TypeFactory.createTypeByName(returnType);
                ctxBld.setReturnType(type);

                for (ArgumentType a : f.getArg()) {
                    Type arg_type = TypeFactory.createTypeByName(a.getType());
                    String arg_name = a.getName();
                    boolean isDef = a.isOpt();

                    ctxBld.addArgParams(arg_name, arg_type, isDef);
                }

                String ctxPath = ctxBld.buildPath();

//                String validatorClass = null; // todo -- f.getValidatorClass();
                String validatorClass = f.getValidatorClass();
                // try to instantiate the validator
                if (validatorClass != null) {
                    try {
                        Class validatorClazz = Class.forName(validatorClass);
                        Object o = validatorClazz.newInstance();
                        if (o instanceof FunctionValidator) {
                            sysFuncValidators.put(ctxPath, (FunctionValidator) o);
                        } else {
                            log.warn("Configuration exception: validator class is not instance of SystemFunctionValidator, " + o.getClass());
                        }
                    } catch (ClassNotFoundException e) {
                        log.warn("Could not find validator class: " + e.getMessage());
                    } catch (IllegalAccessException e) {
                        log.warn("Could not find validator class: " + e.getMessage());
                    } catch (InstantiationException e) {
                        log.warn("Could not instantiate validator class: " + e.getMessage());
                    }
                } else {
                    // use Generic Validator
                }

                itree.addContextPath(ctxPath, ctxBld.getEncodedArguments());
            }

        } catch (MarshalException e) {
            throw new ConfigurationException("Could not load system function list!", e);
        } catch (ValidationException e) {
            throw new ConfigurationException("Could not load system function list!", e);
        }
    }
}
