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

package com.deepsky.lang.plsql.processors;

import java.io.IOException;
import java.io.OutputStream;

public class WriterHelper {

    OutputStream ps;
    int indent = 0;
    int indentSize = 2;
    StringBuilder bld = new StringBuilder();

    int blockLineSize;

    public WriterHelper(OutputStream ps){
        this.ps = ps;
    }

    public void incrementIndent(){
        indent++;
    }

    public int getBlockLineSize(){
        return blockLineSize;
    }

    public void decrementIndent(){
        if(bld.length() > 0){
            print(getBlanks(indent*indentSize));
            print(bld.toString());
            bld = new StringBuilder();
        }
        indent--;
    }

    public void setIndentSize(int size){
        this.indentSize = size;
    }


    public WriterHelper out(String s){
        bld.append(s);
        return this;
    }

    public WriterHelper outln(){
        print(getBlanks(indent*indentSize));
        print(bld.toString());
        println();
        bld = new StringBuilder();

        blockLineSize++;
        return this;
    }


    String getBlanks(int size){
        StringBuilder b = new StringBuilder();
        for(int i=0; i<size; i++){
            b.append(' ');
        }

        return b.toString();
    }

    void print(String s){
        try {
            ps.write(s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void println(){
        try {
            ps.write("\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flush() {
        print(getBlanks(indent*indentSize));
        print(bld.toString());
        bld = new StringBuilder();
    }
}
