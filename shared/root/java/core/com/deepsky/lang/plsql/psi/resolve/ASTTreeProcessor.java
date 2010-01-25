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

package com.deepsky.lang.plsql.psi.resolve;

import com.intellij.lang.ASTNode;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;

import java.util.List;
import java.util.ArrayList;

public class ASTTreeProcessor {

    List<ASTNodeHandler> hndlrs = new ArrayList<ASTNodeHandler>();

    public void add(ASTNodeHandler handler) {
        hndlrs.add(handler);
    }

    public void process(ASTNode startNode) {
        process(startNode, false);
    }

    public void process(ASTNode startNode, boolean breakOnFirstHit) {

        ASTNodeHandler[] array = hndlrs.toArray(new ASTNodeHandler[hndlrs.size()]);

        // todo - algorith of traversing of a tree must be optimized 
        int size = array.length;
        while (startNode != null && size > 0) {
            size = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    if (array[i].getTokenSet().contains(startNode.getElementType())) {
                        try {
                            boolean res = array[i].handleNode(startNode);
                            if(res){
                                array[i] = null;
                            }
                            if (breakOnFirstHit) {
                                return;
                            }
                        } catch (SyntaxTreeCorruptedException e) {
                            // todo --
//                            size++;
                        }
                        size++;
                    } else {
                        size++;
                    }
                }
            }

            startNode = startNode.getTreeParent();
        }
    }

}
