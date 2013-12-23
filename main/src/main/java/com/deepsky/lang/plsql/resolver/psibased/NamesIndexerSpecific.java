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

package com.deepsky.lang.plsql.resolver.psibased;

import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.completion.Constants;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.resolver.utils.PsiElementUtil;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

public class NamesIndexerSpecific extends NamesIndexerWithChangesCollecting {
    final private String COMPL_IDENTIFIER_ADOPTED = Constants.COMPL_IDENTIFIER.toLowerCase();

    public void visitElement(PsiElement element) {
        PsiElement child = element.getFirstChild();
        while (child != null) {
            IElementType itype = child.getNode().getElementType();
            if (!PsiElementUtil.toSkip.contains(itype)) {
                try {
                    child.accept(this);
                } catch (SyntaxTreeCorruptedException e) {
                    // just skip failed element
                    int h = 1;
                } catch (ValidationException e) {
                    // just skip failed element
                    int h = 1;
                } catch (CompletionDummyIdent e){
                    int h = 1;
                } catch (Throwable e) {
                    int h = 1;
                }
            }
            child = child.getNextSibling();
        }
    }

    // Small trick, do not index entities which contains completion identifier
    protected void saveCtxValue(String ctxPath, final String value){
        int start = ctxPath.indexOf(COMPL_IDENTIFIER_ADOPTED);
        if(start != -1){
            // Try to fix Context path ....
            ContextPathUtil.CtxPathIterator ite = new ContextPathUtil.CtxPathIterator(ctxPath);
            // if the name of the path is COMPL_IDENTIFIER_ADOPTED (i.e. completion started on ws),
            // then exclude this element from indexing, otherwise find actual name of the path
            while(ite.next()){
                String name = ite.getName();
                if(name.contains(COMPL_IDENTIFIER_ADOPTED))
                    if(name.length() == COMPL_IDENTIFIER_ADOPTED.length()){
                        throw new CompletionDummyIdent();
                    } else {
                        break;
                    }
            }
            ctxPath = ctxPath.substring(0, start) + ctxPath.substring(start+COMPL_IDENTIFIER_ADOPTED.length());
        } else {
            // Check ctx path value on intrusion of COMPL_IDENTIFIER_ADOPTED
            start = value == null? -1: value.indexOf(COMPL_IDENTIFIER_ADOPTED);
            switch(start){
                case -1: // absent
                    break;
//                case 0:
//                    if(value.length() == COMPL_IDENTIFIER_ADOPTED.length()){
//                        throw new CompletionDummyIdent();
//                    }
                default:
                    throw new CompletionDummyIdent();
//                    value = value.substring(0, start) + value.substring(start+COMPL_IDENTIFIER_ADOPTED.length());
//                    break;
            }
        }

        itree.addContextPath(ctxPath, value);
    }

    private class CompletionDummyIdent extends Error {
    }
}
