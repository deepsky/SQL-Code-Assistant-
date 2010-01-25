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

package com.deepsky.lang.plsql.tree;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.ASTNode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

public class ASTNodeInvocationHandler implements InvocationHandler {

        public static final String ASTNODE_PROXY_KEY = "ASTNODE_PROXY_KEY";
        public static final String PSI_PROXY_KEY = "PSI_PROXY_KEY";

        Node node;

        public ASTNodeInvocationHandler(){
        }

        public void setNode(Node node){
            this.node = node;
        }

//        interface Converter<T> {
//            T convert(Object source);
//        }
//        <T> T[] wrapArray(Object[] target, Converter<T> con){
//            List<T> out = new ArrayList<T>();
//            for(int i=0; i<target.length; i++){
//                out.add(con.convert(target[i]));
//            }
//            return (T[]) out.toArray();
//        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("getText")) {
                return node.getText();
            } else if (method.getName().equals("getElementType")) {
                return node.getElementType();
            } else if (method.getName().equals("getPsi")) {
                return node.getUserData(PSI_PROXY_KEY);
            } else if (method.getName().equals("getChildren")) {
                TokenSet types = (TokenSet) args[0];
                Node[] children = node.getChildren(types);
                ASTNode[] out = new ASTNode[children.length];
                for(int i=0; i<children.length; i++){
                    out[i] = children[i].getUserData(ASTNODE_PROXY_KEY);
                }

                return out;
            } else if (method.getName().equals("findChildByType")) {
                if(args[0] instanceof IElementType){
                    Node[] node2 = node.findChildrenByType((IElementType) args[0]);
                    if(node2.length == 1){
                        return node2[0].getUserData(ASTNODE_PROXY_KEY);
                    } else {
                        return null;
                    }
                } else {
                    // args[0] instanceof TokenSet
                    Node[] node2 = node.findChildrenByTypes((TokenSet) args[0]);
                    if(node2.length == 1){
                        return node2[0].getUserData(ASTNODE_PROXY_KEY);
                    } else {
                        return null;
                    }
                }
            } else {
                System.out.println("Method not implemented: " + method.getName());
                return null; //method.invoke(node, args);
            }
        }
}
