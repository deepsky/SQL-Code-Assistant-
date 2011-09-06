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

package com.deepsky.lang.plsql.completion;

import javax.swing.*;

class NamedYItem {

    protected String name;
    protected Icon icon;
    protected String type;
    protected String tail;
    protected boolean strikeout = false;

    public NamedYItem(String name) {
        this.name = name;
    }

    public NamedYItem(String name, Icon icon, String type, String tail) {
        this.name = name;
        this.icon = icon;
        this.type = type!=null? type.toLowerCase(): null;
        this.tail = tail!=null? " ("+ tail + ")": null;
    }

    public String getName() {
        return name;
    }

    public String getType(){
        return type;
    }

    public String getTail(){
        return tail;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setStrikeout(boolean b) {
        this.strikeout = b;
    }

    public boolean isStrikeout() {
        return strikeout;
    }

    protected String sign(){
        return name + (tail != null? tail: "");
    }
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamedYItem that = (NamedYItem) o;

        return sign().equals(that.sign());
    }

    public int hashCode(){
        return sign().hashCode();
    }

}
