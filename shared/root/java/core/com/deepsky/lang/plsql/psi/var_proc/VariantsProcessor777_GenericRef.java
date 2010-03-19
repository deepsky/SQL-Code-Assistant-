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

package com.deepsky.lang.plsql.psi.var_proc;

import com.deepsky.lang.plsql.psi.resolve.VariantsProcessor777;
import com.deepsky.lang.plsql.psi.resolve.ASTTreeProcessor;
import com.deepsky.lang.plsql.psi.resolve.ContextEnumerator;
import com.deepsky.lang.plsql.psi.ObjectReference;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.ASTNode;


import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class VariantsProcessor777_GenericRef implements VariantsProcessor777 {

    ObjectReference reference;
    public VariantsProcessor777_GenericRef(ObjectReference reference) {
        this.reference = reference;
    }

    public String[] getVariants(String prefix) {

        final List<String> out = new ArrayList<String>();

        String[] out1 = VariantsProcessorHelpers.getTableColumnVariants(reference, prefix);
        if(out1.length > 0){
            out.addAll(Arrays.asList(out1));
        }

        String[] out2 = VariantsProcessorHelpers.getVariableVariants(reference, prefix);
        if(out2.length > 0){
            out.addAll(Arrays.asList(out2));    
        }
        return out.toArray(new String[0]);
    }
}
