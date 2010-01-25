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

package com.deepsky.lang.plsql.struct.impl;

import com.deepsky.lang.plsql.ReferencedName;
import com.deepsky.lang.plsql.psi.Visitor;
import com.deepsky.lang.plsql.struct.Type;
import org.jetbrains.annotations.NotNull;

public class ReferencedNameImpl extends PsiElementDumb implements ReferencedName {

    String name;
    String tab;
    boolean outerJoin = false;
    String[] ext;


    public ReferencedNameImpl(String tab, String name){
        this.tab = tab;
        this.name = name;
    }

    public ReferencedNameImpl(String tab, String name, String[] ext){
        this.tab = tab;
        this.name = name;
        this.ext = ext;
    }

    public ReferencedNameImpl(String tab, String name, boolean outerJoin){
        this.tab = tab;
        this.name = name;
        this.outerJoin = outerJoin;
    }

    public ReferencedNameImpl(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        String full = "";
        if(ext != null){
            for(int i=0; i<ext.length; i++){
                if(i>0){
                    full += ".";
                }
                full += ext[i];
            }
            return (tab != null)? full + "." + tab + "." + name: full + "." + name;
        } else {
            return (tab != null)? tab + "." + name: name;
        }
    }

    public String getTab(){
        return tab;
    }

    public boolean isOuterJoin() {
        return outerJoin;
    }

    public void setOuterJoin(boolean outerJoin) {
        this.outerJoin = outerJoin;
    }

    @NotNull
    public Type getExpressionType() {
        // todo
        return null;
    }

    public void process(Visitor visitor){
        visitor.accept(this);
    }

}
