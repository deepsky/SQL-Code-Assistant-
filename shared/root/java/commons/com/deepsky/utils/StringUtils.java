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

package com.deepsky.utils;

import com.deepsky.lang.plsql.ConfigurationException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.io.*;

public class StringUtils {

    public static String[] list2array(List<String> names) {
        return names.toArray(new String[names.size()]);
    }

    /**
     * Search input sting for symbol c starting with 'start' in reverse direction from end to start
     * @param input string
     * @param start - position to start
     * @param c - target symbol
     * @return - position of the found symbol or -1 if not found
     */
    public static int searchInReverse(String input, int start, char c) {
        if(input != null && input.length() > start){
            for(int i = start; i>= 0; i--){
                if(input.charAt(i) == c){
                    return i;
                }
            }
        }

        return -1;
    }


    public static String hash_MD5(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");

            byte[] buffer = input.getBytes();
            digest.update(buffer, 0, buffer.length);
            byte[] md5sum = digest.digest();
            BigInteger bigInt = new BigInteger(1, md5sum);
            return bigInt.toString(16);
        }
        catch (NoSuchAlgorithmException e) {
            throw new ConfigurationException("MD5 algorithm is not available", e);
        }
    }

    public static String trim(String input, char trimChar){
        int start = 0;
        for(int i =0; i < input.length(); i++){
            if(input.charAt(i) != trimChar){
                break;
            } else {
                start++;
            }
        }

        int end = input.length();
        for(int i =input.length()-1; i> start; i--){
            if(input.charAt(i) != trimChar){
                break;
            } else {
                end--;
            }
        }

        return input.substring(start, end);
    }

    public static String convert2listStrings(List<String> names) {
        StringBuilder b = new StringBuilder();
        for (String n : names) {
            if (b.length() > 0) {
                b.append(",");
            }

            b.append("'").append(n.toUpperCase()).append("'");
        }

        return b.toString();
    }

    public static String convert2listStrings(String[] names) {
        StringBuilder b = new StringBuilder();
        for (String n : names) {
            if (b.length() > 0) {
                b.append(",");
            }

            b.append("'").append(n.toUpperCase()).append("'");
        }

        return b.toString();
    }

    public static String convert2listStringsWoQuotes(String[] names) {
        StringBuilder b = new StringBuilder();
        for (String n : names) {
            if (b.length() > 0) {
                b.append(",");
            }

            b.append(n.toUpperCase());
        }

        return b.toString();
    }

    public static String file2string(File f) throws IOException {
        // load file into string
        FileInputStream in = new FileInputStream(f);
        byte[] temp = new byte[1000];
        int s = 0;
        StringBuffer bld = new StringBuffer();
        while((s= in.read(temp)) > 0){
            String chunk = new String(temp, 0, s);
            bld.append(chunk);
        }
        in.close();

        return bld.toString();
    }

    public static String instream2string(InputStream in) throws IOException {
        // load file into string
        byte[] temp = new byte[1000];
        int s = 0;
        StringBuffer bld = new StringBuffer();
        while((s= in.read(temp)) > 0){
            String chunk = new String(temp, 0, s);
            bld.append(chunk);
        }
        in.close();

        return bld.toString();
    }

    public static void string2file(String content, File f) throws IOException {
        // save string in the file
        Writer out = null;
        try {
            out = new FileWriter(f);
            out.write(content);
        } finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static String discloseDoubleQuotes(String text) {
        int pos = text.length()-1;
        if(text.charAt(0) == '"' && text.charAt(pos) == '"'){
            return text.substring(1, pos);
        } else {
            return text;
        }
    }

    public static String trimDoubleQuites(String name) {
        if(name.charAt(0) == '"' && name.charAt(name.length()-1) == '"'){
            return name.substring(1, name.length()-1);
        } else {
            return name;
        }
    }

    public static void stream2file(Reader r, File f) throws IOException {
        // save stream in the file
        Writer out = null;
        try {
            out = new FileWriter(f);
            char[] buf = new char[1000];
            int size;
            while((size=r.read(buf)) > 0){
                out.write(buf, 0, size);
            }
        } finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    public static void stream2file(InputStream r, File f) throws IOException {
        // save stream in the file
        OutputStream out = null;
        try {
            out = new FileOutputStream(f);
            byte[] buf = new byte[1000];
            int size;
            while((size=r.read(buf)) > 0){
                out.write(buf, 0, size);
            }
        } finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
