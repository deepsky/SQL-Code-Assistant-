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

public class ErrorNodeProcessorTest extends AbstractCompletionTest {


    public void test4_210() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("create table abc( id number)\n delete from tab2 where id > 67\n <caret> ");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();

        assertEquals("/ .. #ERROR_TOKEN_A / #DELETE #FROM #TABLE_ALIAS #WHERE_CONDITION 1#C_MARKER", context.getDesc(0).getTreePath());
        assertEquals(1, context.getDesc(0).getHandlerParameters().length);
        assertEquals("com.deepsky.lang.plsql.completion.processors.ErrorNodeProcessor", context.getDesc(0).getMeta().getClassName());
        assertEquals("process$Start5", context.getDesc(0).getMeta().getMethodName());
    }


    public void test2() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1(" <caret> ");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();

        assertEquals("/ .. #ERROR_TOKEN_A / 1#C_MARKER", context.getDesc(0).getTreePath());
        assertEquals(1, context.getDesc(0).getHandlerParameters().length);
        assertTrue(context.getDesc(0).getHandlerParameters()[0] instanceof ASTNode);
        assertEquals("com.deepsky.lang.plsql.completion.processors.ErrorNodeProcessor", context.getDesc(0).getMeta().getClassName());
        assertEquals("process$Start", context.getDesc(0).getMeta().getMethodName());
    }

    public void test21() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("se<caret>");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();
        assertEquals("/ .. #ERROR_TOKEN_A / 1#C_MARKER", context.getDesc(0).getTreePath());
        assertEquals(1, context.getDesc(0).getHandlerParameters().length);
        assertTrue(context.getDesc(0).getHandlerParameters()[0] instanceof ASTNode);
        assertEquals("com.deepsky.lang.plsql.completion.processors.ErrorNodeProcessor", context.getDesc(0).getMeta().getClassName());
        assertEquals("process$Start", context.getDesc(0).getMeta().getMethodName());
    }

    public void test4_12() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select 1 from dual;\n--- create table abc( id number);\n <caret> ");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();

        assertEquals("/ .. #ERROR_TOKEN_A / 1#C_MARKER", context.getDesc(0).getTreePath());
        assertEquals(1, context.getDesc(0).getHandlerParameters().length);
        assertEquals("com.deepsky.lang.plsql.completion.processors.ErrorNodeProcessor", context.getDesc(0).getMeta().getClassName());
        assertEquals("process$Start", context.getDesc(0).getMeta().getMethodName());
    }


    public void test4_13() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("select 1 from dual;\n create table abc( id number);\n <caret> ");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();

        assertEquals("/ .. #ERROR_TOKEN_A / .. #SEMI #C_MARKER", context.getDesc(0).getTreePath());
        assertEquals(0, context.getDesc(0).getHandlerParameters().length);
        assertEquals("com.deepsky.lang.plsql.completion.processors.ErrorNodeProcessor", context.getDesc(0).getMeta().getClassName());
        assertEquals("process$Start3", context.getDesc(0).getMeta().getMethodName());
    }

    public void test4_14() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("-- select 1 from dual;\n--- create table abc( id number);\n <caret> ");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();

        assertEquals("/ .. #ERROR_TOKEN_A / 1#C_MARKER", context.getDesc(0).getTreePath());
        assertEquals(1, context.getDesc(0).getHandlerParameters().length);
        assertEquals("com.deepsky.lang.plsql.completion.processors.ErrorNodeProcessor", context.getDesc(0).getMeta().getClassName());
        assertEquals("process$Start", context.getDesc(0).getMeta().getMethodName());
    }

    public void test4_15() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1(" create table abc( id number);\n <caret> ");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();

        assertEquals("/ .. #ERROR_TOKEN_A / .. #SEMI #C_MARKER", context.getDesc(0).getTreePath());
        assertEquals(0, context.getDesc(0).getHandlerParameters().length);
        assertEquals("com.deepsky.lang.plsql.completion.processors.ErrorNodeProcessor", context.getDesc(0).getMeta().getClassName());
        assertEquals("process$Start3", context.getDesc(0).getMeta().getMethodName());
    }

    public void test4_16() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("-- create table abc( id number);\n <caret> ");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();

        assertEquals("/ .. #ERROR_TOKEN_A / 1#C_MARKER", context.getDesc(0).getTreePath());
        assertEquals(1, context.getDesc(0).getHandlerParameters().length);
        assertEquals("com.deepsky.lang.plsql.completion.processors.ErrorNodeProcessor", context.getDesc(0).getMeta().getClassName());
        assertEquals("process$Start", context.getDesc(0).getMeta().getMethodName());
    }


    public void test4_190() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("create table abc( id number)\n create sequence te_seq INCREMENT BY 2;\n <caret> ");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();

        assertEquals("/ .. #ERROR_TOKEN_A / .. #SEMI #C_MARKER", context.getDesc(0).getTreePath());
        assertEquals(0, context.getDesc(0).getHandlerParameters().length);
        assertEquals("com.deepsky.lang.plsql.completion.processors.ErrorNodeProcessor", context.getDesc(0).getMeta().getClassName());
        assertEquals("process$Start3", context.getDesc(0).getMeta().getMethodName());
    }

    public void test4_191() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("create table abc( id number)\n create sequence te_seq INCREMENT BY 2\n <caret> ");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();

        assertEquals("/ .. #ERROR_TOKEN_A / #CREATE #SEQUENCE 1#SEQUENCE_NAME .. #C_MARKER", context.getDesc(0).getTreePath());
        assertEquals(1, context.getDesc(0).getHandlerParameters().length);
        assertEquals("com.deepsky.lang.plsql.completion.processors.ErrorNodeProcessor", context.getDesc(0).getMeta().getClassName());
        assertEquals("process$Start6", context.getDesc(0).getMeta().getMethodName());
    }

    public void test4_20() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("create table abc( id number)\n alter table tab1 add name varchar2(20)\n <caret> ");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();

        assertEquals("/ .. #ERROR_TOKEN_A / .. 1$AlterTable #C_MARKER", context.getDesc(0).getTreePath());
        assertEquals(1, context.getDesc(0).getHandlerParameters().length);
        assertEquals("com.deepsky.lang.plsql.completion.processors.ErrorNodeProcessor", context.getDesc(0).getMeta().getClassName());
        assertEquals("process$Start4", context.getDesc(0).getMeta().getMethodName());
    }


    public void test4_201() throws TokenStreamException, RecognitionException {
        TreePath path = parseScript1("create table abc( id number)\n alter table tab1 add name varchar2(20);\n <caret> ");
        CompletionProcessor2 proc = new CompletionProcessor2(path);
        assertTrue(proc.process());

        TreePathContext context = proc.getContext();

        assertEquals("/ .. #ERROR_TOKEN_A / .. 1$AlterTable #C_MARKER", context.getDesc(0).getTreePath());
        assertEquals(1, context.getDesc(0).getHandlerParameters().length);
        assertEquals("com.deepsky.lang.plsql.completion.processors.ErrorNodeProcessor", context.getDesc(0).getMeta().getClassName());
        assertEquals("process$Start4", context.getDesc(0).getMeta().getMethodName());
    }

}
