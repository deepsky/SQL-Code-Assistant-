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

package com.deepsky.lang.plsql.struct.types;

import com.deepsky.lang.plsql.struct.Type;

public class TypeBase implements Type {

    static final long serialVersionUID = 7326805379252559428L;
    
    int type;
    String typeName;
    int data_length;

    public TypeBase(){
        this.type = Type.UNKNOWN;
        this.typeName = "UNKNOWN";
    }

    public TypeBase(int type, String typeName){
        this.type = type;
        this.typeName = typeName;
    }


    public TypeBase(int type, String typeName, int data_length){
        this.type = type;
        this.typeName = typeName;
        this.data_length = data_length;
    }

    public int typeId() {
        return type;
    }

    public String typeName() {
        return typeName;
    }

    public String toString() {
        return typeName;
    }

    public boolean isUserDefined() {
        return type == Type.RECORD_TYPE || type == Type.ROWTYPE || type == Type.USER_DEFINED;
    }

    public boolean isTypeReference() {
        return false;
    }

    public int dataLength(){
        return data_length;
    }

    public boolean equals(Object o){
        return o instanceof Type && ((Type) o).typeId() == type;
    }
}
