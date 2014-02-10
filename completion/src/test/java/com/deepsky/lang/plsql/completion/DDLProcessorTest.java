/*
 * Copyright (c) 2009,2014 Serhiy Kulyk
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *      1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
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

import antlr.RecognitionException;
import antlr.TokenStreamException;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.CompletionProcessor2;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generator.TreePathContext;
import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.TreePath;
import com.intellij.lang.ASTNode;


public class DDLProcessorTest extends AbstractCompletionTest {

    public void test4_17() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1(" create table abc( id number)\n <caret> ");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();

        assertEquals("/ .. 1#TABLE_DEF / #CREATE #ERROR_TOKEN_A / #TABLE 2#TABLE_NAME_DDL #OPEN_PAREN .. #COLUMN_DEF #CLOSE_PAREN #C_MARKER", context.getDesc(0).getTreePath());
        assertEquals(2, context.getDesc(0).getHandlerParameters().length);
        assertTrue(context.getDesc(0).getHandlerParameters()[0] instanceof ASTNode);
        assertEquals("com.deepsky.lang.plsql.completion.processors.DDLProcessor", context.getDesc(0).getMeta().getClassName());
        assertEquals("process$Table", context.getDesc(0).getMeta().getMethodName());
    }

    public void test4_18() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("create sequence te_seq;\n create table abc( id number)\n <caret> ");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();

        assertEquals("/ .. 1#TABLE_DEF / #CREATE #ERROR_TOKEN_A / #TABLE 2#TABLE_NAME_DDL #OPEN_PAREN .. #COLUMN_DEF #CLOSE_PAREN #C_MARKER", context.getDesc(0).getTreePath());
        assertEquals(2, context.getDesc(0).getHandlerParameters().length);
        assertTrue(context.getDesc(0).getHandlerParameters()[0] instanceof ASTNode);
        assertEquals("com.deepsky.lang.plsql.completion.processors.DDLProcessor", context.getDesc(0).getMeta().getClassName());
        assertEquals("process$Table", context.getDesc(0).getMeta().getMethodName());
    }


    public void test4_19() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("create table abc( id number)\n create sequence te_seq\n <caret> ");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();

        assertEquals("/ .. #CREATE_SEQUENCE / #CREATE #ERROR_TOKEN_A / #SEQUENCE 1#IDENTIFIER #C_MARKER", context.getDesc(0).getTreePath());
        assertEquals(1, context.getDesc(0).getHandlerParameters().length);
        assertTrue(context.getDesc(0).getHandlerParameters()[0] instanceof ASTNode);
        assertEquals("com.deepsky.lang.plsql.completion.processors.DDLProcessor", context.getDesc(0).getMeta().getClassName());
        assertEquals("process$Sequence", context.getDesc(0).getMeta().getMethodName());
    }

}
