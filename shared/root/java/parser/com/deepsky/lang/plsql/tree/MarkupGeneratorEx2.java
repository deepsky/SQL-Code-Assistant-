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

package com.deepsky.lang.plsql.tree;

import com.deepsky.lang.common.PlSqlPsiParser;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiParser;
import com.intellij.openapi.vfs.VirtualFile;

public class MarkupGeneratorEx2 {

    //static final LoggerProxy log = LoggerProxy.getInstance("#MarkupGeneratorEx2");

    PsiParser parser = new PlSqlPsiParser();

    String filePath = null;
    VirtualFile virtualFile = null;

    public MarkupGeneratorEx2() {
    }

    public MarkupGeneratorEx2(String filePath) {
        this.filePath = filePath;
    }

    public MarkupGeneratorEx2(VirtualFile virtualFile) {
        this.filePath = virtualFile.getPath();
        this.virtualFile = virtualFile;
    }

    public ASTNode parse(String input) {
        TreeNodeBuilderEx2 builder =
                virtualFile != null ?
                        new TreeNodeBuilderEx2(virtualFile, input)
                        : new TreeNodeBuilderEx2(filePath, input);

        return parser.parse(PlSqlTokenTypes.FILE, builder);
    }

}
