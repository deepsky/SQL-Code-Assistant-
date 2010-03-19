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

package com.deepsky.lang.plsql.psi.resolve;

import com.deepsky.lang.plsql.struct.SequenceDescriptor;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.types.IntegerType;
import org.jetbrains.annotations.NotNull;

public class SequenceContext implements ResolveContext {

    static final String CURRVAL = "currval";
    static final String NEXTVAL = "nextval";

    SequenceDescriptor seq;

    public SequenceContext(SequenceDescriptor seq){
        this.seq = seq;
    }

    public String[] getVariants(@NotNull String prefix) {

        if(prefix.length() > 0){
            if( CURRVAL.startsWith(prefix.toUpperCase()) && NEXTVAL.startsWith(prefix.toUpperCase())){
                return new String[]{CURRVAL, NEXTVAL};
            } else if( CURRVAL.startsWith(prefix.toUpperCase())){
                return new String[]{CURRVAL};
            } else if(NEXTVAL.startsWith(prefix.toUpperCase())){
                return new String[]{NEXTVAL};
            } else {
                return new String[0];
            }
        } else {
            return new String[]{CURRVAL, NEXTVAL};
        }
    }

    public NameDescriptor describe(String name) {
        if(CURRVAL.equalsIgnoreCase(name)){
            return new NameDescriptorImpl(CURRVAL);
        } else if(NEXTVAL.equalsIgnoreCase(name)){
            return new NameDescriptorImpl(NEXTVAL);
        }
        return null;
    }

    class NameDescriptorImpl implements ExpressionDescriptor { // NameDescriptor {

        String name;
        public NameDescriptorImpl(String name){
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public Type getType() {
            return new IntegerType();
        }
    }
}
