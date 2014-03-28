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

package com.deepsky.lang.plsql.completion.syntaxTreePath;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import java.lang.reflect.Field;


public class ClassUtil {

    /**
     * Getting value of public static final field/property of PlSqlElementTypes or PlSqlTokenTypes via reflection
     * @param name field name
     * @return
     */
    public static Object retrieveFieldValue(String name){
        // Check against PlSqlElementTypes
        Class clazz = PlSqlElementTypes.class;
        try {

            // Go over parent interfaces of PlSqlElementTypes
            for (Class ext : clazz.getInterfaces()) {
                for (Field field : ext.getDeclaredFields()) {
                    if (field.getName().equals(name)) {
                        return field.get(null);
                    }
                }
            }

            // Go over fields of PlSqlElementTypes
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getName().equals(name)) {
                    return field.get(null);
                }
            }

            // Check against PlSqlTokenTypes without KEYWORD prefix
            Class clazz1 = PlSqlTokenTypes.class;
            for (Class ext : clazz1.getInterfaces()) {
                for (Field field : ext.getDeclaredFields()) {
                    if (field.getName().equals(name)) {
                        return field.get(null);
                    } else if (field.getName().equals("KEYWORD_" + name)) {
                        return field.get(null);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Cannot find element: " + name);
    }


    public static String findFieldForName(String name, NameProcessor processor){
        // Check against PlSqlElementTypes
        Class clazz = PlSqlElementTypes.class;

        // Go over parent interfaces of PlSqlElementTypes
        for (Class ext : clazz.getInterfaces()) {
            for (Field field : ext.getDeclaredFields()) {
                if (field.getName().equals(name)) {
                    return processor.process("PlSqlElementTypes", "");
                }
            }
        }

        // Go over fields of PlSqlElementTypes
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equals(name)) {
                return processor.process("PlSqlElementTypes", "");
            }
        }

        // Check against PlSqlTokenTypes without KEYWORD prefix
        Class clazz1 = PlSqlTokenTypes.class;
        for (Class ext : clazz1.getInterfaces()) {
            for (Field field : ext.getDeclaredFields()) {
                if (field.getName().equals(name)) {
                    return processor.process("PlSqlTokenTypes", "");
                } else if (field.getName().equals("KEYWORD_" + name)) {
                    return processor.process("PlSqlTokenTypes", "KEYWORD_");
                }
            }
        }

        throw new RuntimeException("Cannot find element: " + name);
    }




    public static interface NameProcessor {
        String process(String types, String prefix);
    }
}
