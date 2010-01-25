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

package com.deepsky.lang.plsql.psi.impl;

import com.deepsky.lang.plsql.psi.GenericTable;
import com.deepsky.lang.plsql.struct.TableDescriptorLegacy;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;

import java.util.Map;
import java.util.HashMap;

abstract public class GenericTableBase extends PlSqlElementBase implements GenericTable {

    static final LoggerProxy log = LoggerProxy.getInstance("#GenericTableBase");

    public static Map<String, Holder> cache = new HashMap<String, Holder>();

    static class Holder {
        public TableDescriptorLegacy tdesc;
        public int descSeq = -1;
    }

    String cachedText;
    int textSeq = 0;

    public TableDescriptorLegacy describe() {

        Holder holder = cache.get(this.toString());
        if(holder == null){
            holder = new Holder();
            cache.put(this.toString(), holder);
        }

        if(holder.descSeq != textSeq){
            holder.tdesc = describeInternal();
            holder.descSeq = textSeq;
            return holder.tdesc;
        } else {
            return holder.tdesc;
        }
    }

    public GenericTableBase(ASTNode astNode) {
        super(astNode);
        cachedText = getText();
    }


    abstract public String getAlias();
    abstract protected TableDescriptorLegacy describeInternal();


    public boolean isColumnNameCorrect(String columnRef) {
        return columnRef.matches("^[a-zA-Z][a-zA-Z0-9_]*");
    }

    public void subtreeChanged(){
        if(!cachedText.equals(getText())){
            cachedText = getText();
            textSeq++;
            log.info("Subtree changed!        " + " == " + this);
        }
    }

}
