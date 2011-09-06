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
import com.deepsky.lang.plsql.resolver.utils.PsiElementUtil;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

public class NamesIndexerExists extends NamesIndexer {
    final String COMPL_IDENTIFIER_ADOPTED = Constants.COMPL_IDENTIFIER.toLowerCase();

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

    protected void saveCtxValue(String ctxPath, String value){
        if(ctxPath.endsWith(COMPL_IDENTIFIER_ADOPTED)){
            throw new CompletionDummyIdent();
        }
        itree.addContextPath(ctxPath, value);
//
//        boolean res = itree.updateCtxPathValue(ctxPath, value);
    }

    private class CompletionDummyIdent extends Error {

    }
}
