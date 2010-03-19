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

package com.deepsky.lang.plsql.struct.parser;

import com.deepsky.lang.plsql.resolver.ResolveUtils;

import java.util.ArrayList;
import java.util.List;


public class ContextTrackerImpl implements ContextTracker {

    List<ContextAttr> paths = new ArrayList<ContextAttr>();

    String schemaName;
    public ContextTrackerImpl(String schemaName){
        this.schemaName = schemaName.toLowerCase();

        // create Root context
        paths.add( new ContextAttr(""));
    }

    public ContextTrackerImpl(){
        // create Root context
        paths.add( new ContextAttr(""));
    }

    public void __init__() {
        paths.clear();
        // create Root context
        paths.add( new ContextAttr(""));

//        if(schemaName != null){
//            paths.add(ResolveUtils.encodeCtx(ContextTracker.SCHEMA_CTX,schemaName));
//        }
    }

    public void __openContext__(int type, String name) {

        switch(type){
            case ContextPath.PROCEDURE_SPEC:
            case ContextPath.PROCEDURE_BODY:
            case ContextPath.FUNCTION_SPEC:
            case ContextPath.FUNCTION_BODY:
                ContextAttr ctx = paths.get(paths.size()-1);
                paths.add(
                        new ContextAttr(ResolveUtils.encodeCtx(type,ctx.getSeq() + "$" + name.toLowerCase()))
                );
                break;
            default:
                paths.add(
                        new ContextAttr(ResolveUtils.encodeCtx(type,"..$" + name.toLowerCase()))
                );
                break;
        }
//        ContextAttr ctx = paths.get(paths.size()-1);
//        paths.add(
//                new ContextAttr(ResolveUtils.encodeCtx(type,ctx.getSeq() + "$" + name.toLowerCase()))
//        );
    }

    public void __openContext__(int type) {
        ContextAttr ctx = paths.get(paths.size()-1);
        paths.add(
                new ContextAttr(ResolveUtils.encodeCtx(type, ctx.getSeq() + "$"))
        );
    }

    public void __closeContext__() {
        paths.remove(paths.size()-1);
    }

    public String getPath() {
        StringBuilder b = new StringBuilder();
        for(ContextAttr s: paths){
            b.append(s.type);
        }
        return b.toString();
    }


    final static String[] ids = {
            "00","01","02","03","04","05","06","07","08","09", "0A", "0B", "0C", "0D", "0E", "0F",
            "10","11","12","13","14","15","16","17","18","19", "1A", "1B", "1C", "1D", "1E", "1F",
            "20","21","22","23","24","25","26","27","28","29", "2A", "2B", "2C", "2D", "2E", "2F",
            "30","31","32","33","34","35","36","37","38","39", "3A", "3B", "3C", "3D", "3E", "3F",
            "40","41","42","43","44","45","46","47","48","49", "4A", "4B", "4C", "4D", "4E", "4F",
            "50","51","52","53","54","55","56","57","58","59", "5A", "5B", "5C", "5D", "5E", "5F",
            "60","61","62","63","64","65","66","67","68","69", "0A", "0B", "0C", "0D", "0E", "0F",
            "70","71","72","73","74","75","76","77","78","79", "7A", "7B", "7C", "7D", "7E", "7F",
            "80","81","82","83","84","85","86","87","88","89", "8A", "8B", "8C", "8D", "8E", "8F",
            "90","91","92","93","94","95","96","97","98","99", "9A", "9B", "9C", "9D", "9E", "9F",
            "A0","A1","A2","A3","A4","A5","A6","A7","A8","A9", "AA", "AB", "AC", "AD", "AE", "AF",
            "B0","B1","B2","B3","B4","B5","B6","B7","B8","B9", "BA", "BB", "BC", "BD", "BE", "BF",
            "C0","C1","C2","C3","C4","C5","C6","C7","C8","C9", "CA", "CB", "CC", "CD", "CE", "CF",
            "D0","D1","D2","D3","D4","D5","D6","D7","D8","D9", "DA", "DB", "DC", "DD", "DE", "DF",
            "E0","E1","E2","E3","E4","E5","E6","E7","E8","E9", "EA", "EB", "EC", "ED", "EE", "EF",
            "F0","F1","F2","F3","F4","F5","F6","F7","F8","F9", "FA", "FB", "FC", "FD", "FE", "FF"
    };
    
    class ContextAttr {
        private int sequence = 0;
        public String type;

        public ContextAttr(String type){
            this.type = type;
        }

        public String getSeq(){
            // todo-- may throw ArrayIndexOutoufBoundException --
            return ids[0xff & sequence++];
        }
    }
}
