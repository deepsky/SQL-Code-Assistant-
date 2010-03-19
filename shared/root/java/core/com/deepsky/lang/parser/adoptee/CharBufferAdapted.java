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

package com.deepsky.lang.parser.adoptee;

import antlr.InputBuffer;
import antlr.CharStreamException;
import antlr.CharStreamIOException;
import com.intellij.openapi.diagnostic.Logger;

import java.io.Reader;
import java.io.CharArrayReader;
import java.io.IOException;

public class CharBufferAdapted extends InputBuffer {

    static final Logger log = Logger.getInstance("#CharBufferAdapted");
    // char source
    transient Reader input;
    int start = -1;
    int length = -1;
    char[] chars = null;

    /** Create a character buffer */
    public CharBufferAdapted(Reader input_) { // SAS: for proper text i/o
        super();
        input = input_;
    }

    public CharBufferAdapted(char[] chars, int start, int length) { // SAS: for proper text i/o
        super();
        input = new CharArrayReader(chars, start, length);
        this.start = start;
        this.length = length;
        this.chars = chars;
    }



    public CharBufferAdapted(char[] chars) { // SAS: for proper text i/o
        super();
        input = new CharArrayReader(chars);
        this.chars = chars;
    }

    /** Ensure that the character buffer is sufficiently full */
    public void fill(int amount) throws CharStreamException {
        try {
            syncConsume();
            // Fill the buffer sufficiently to hold needed characters
            while (queue.getNbrEntries() < amount + markerOffset) {
                // Append the next character
                int t = input.read();
                if(t == 0){
                    log.info("##### t == 0");
                    t=-1;
                } else {
                    //log.info("#fill exec = '" + (char)exec + "'");
                }
                char tok = (char)t;
                queue.append(tok);
            }
        }
        catch (IOException io) {
            throw new CharStreamIOException(io);
        }
    }
}

