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

package com.deepsky.lang.common;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class PlSqlPsiParserEx extends PlSqlPsiParser {

    @NotNull
    public ASTNode parse_datatype_param_info(IElementType root, PsiBuilder builder) {

        this.builder = builder;
//        builder.setDebugMode(true);

        long ms1 = System.currentTimeMillis();
        parser = new PLSqlParser2(new TokenStreamAdapter(builder), builder);
        ms1 = System.currentTimeMillis() - ms1;

        long ms2 = 0L, ms4 =0L;
        ASTNode astNode = null;
        long ms2_ = System.currentTimeMillis();

        PsiBuilder.Marker rootMarker = builder.mark();

        try {
            try {
                parser.datatype_param_info();
            } catch (RecognitionException e) {
                log.warn("[PARSER EXCEPTION] : " + e.getMessage());
            } catch (TokenStreamException e) {
                log.warn("[PARSER EXCEPTION] : " + e.getMessage());
            }

            rootMarker.done(root);
            ms2 = System.currentTimeMillis() - ms2_;

            long ms3_ = System.currentTimeMillis();
            astNode = builder.getTreeBuilt();
            ms4 = System.currentTimeMillis() - ms3_;

        } finally {
            log.debug("[PARSER] create parser: " + ms1 + ", parsing: " + ms2 + " build tree: " + ms4);
        }


        return astNode;
    }

}
