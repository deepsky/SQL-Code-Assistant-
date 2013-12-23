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

package com.deepsky.view.query_pane.converters;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;

public class ConversionUtil {

    public static String convertBinaryStream2HEXString(@Nullable InputStream stream) throws IOException {
        if (stream != null) {
            StringBuilder builder = new StringBuilder();
            byte[] temp = new byte[1000];
            int bt;
            while((bt=stream.read()) != -1){
                String hex = String.format("%1$08x", bt);
                char c1 = hex.charAt(6);
                if(Character.isLetter(c1)){
                    c1 = Character.toUpperCase(c1);
                }
                builder.append(c1);
                char c2 = hex.charAt(7);
                if(Character.isLetter(c2)){
                    c2 = Character.toUpperCase(c2);
                }
                builder.append(c2);
            }
            stream.close();
            return builder.toString();
        }
        return "";
    }

    public static String convertByteArray2HEXString(byte[] value) {
        if (value != null) {
            char[] out = new char[value.length * 2];
            for (int i = 0; i < value.length; i++) {
                String hex = String.format("%1$08x", (int) value[i]);
                out[i * 2] = hex.charAt(6);
                if(Character.isLetter(out[i * 2])){
                    out[i * 2] = Character.toUpperCase(out[i * 2]);
                }
                out[i * 2 + 1] = hex.charAt(7);
                if(Character.isLetter(out[i * 2 +1])){
                    out[i * 2 + 1] = Character.toUpperCase(out[i * 2 + 1]);
                }
            }
            return new String(out);
        }
        return "";
    }


    public static byte[] convertHEXString2ByteArray(String value) {
        if (value == null || value.length() == 0) {
            return null;
        } else {
            char[] array = value.toCharArray();
            int ext = array.length%2; // can be 0 or 1 only!
            byte[] out = new byte[array.length / 2 + ext];
            for (int i = 0; i < array.length-ext; i += 2) {
                String part = new String(array, i, 2);
                try {
                    out[i/2] = (byte) Integer.parseInt(part, 16);
                }catch(NumberFormatException e){
                    // ignore conversion error
                    out[i/2] = 0;
                }
            }

            if(ext != 0){
                String part = String.valueOf(array[array.length-1]);
                try {
                    out[out.length-1] = (byte) Integer.parseInt(part, 16);
                }catch(NumberFormatException e){
                    // ignore conversion error
                    out[out.length-1] = 0;
                }
            }
            return out;
        }
    }

}
