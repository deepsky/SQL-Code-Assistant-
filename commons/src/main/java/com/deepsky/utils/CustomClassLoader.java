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

import org.jetbrains.annotations.NotNull;

import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import java.util.Enumeration;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;

public class CustomClassLoader extends ClassLoader {
    private static final int BUFFER_SIZE = 8192;

    JarFile zip;

    public CustomClassLoader(@NotNull ClassLoader parent, String path) throws IOException {
        super(parent);
        this.zip = new JarFile(path);
    }

    private InputStream getStream(String className) {
        if(zip == null){
            return null;
        }

        Enumeration enumm = zip.entries();
        String clsFile = className.replace('.', '/') + ".class";
        while (enumm.hasMoreElements()) {
            JarEntry o = (JarEntry) enumm.nextElement();
            if (!o.isDirectory() && o.getName().equals(clsFile)) {
                try {
                    return zip.getInputStream(o);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

        return null;
    }

    protected synchronized Class loadClass(String className, boolean resolve) throws ClassNotFoundException {
        // 1. is this class already loaded?
        Class cls = findLoadedClass(className);
        if (cls != null) {
            return cls;
        }

        // 2. get bytes for class
        byte[] classBytes = null;
        InputStream in = getStream(className);
        if (in != null) {
            try {
                byte[] buffer = new byte[BUFFER_SIZE];
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                int n = -1;
                while ((n = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
                    out.write(buffer, 0, n);
                }
                classBytes = out.toByteArray();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (classBytes == null) {
            return getParent().loadClass(className);
        }

        // 3. turn the byte array into a Class
        try {
            cls = defineClass(className, classBytes, 0, classBytes.length);
            if (resolve) {
                resolveClass(cls);
            }
        }
        catch (SecurityException e) {
            // loading core java classes such as java.lang.String
            // is prohibited, throws java.lang.SecurityException.
            // delegate to parent if not allowed to load class
            cls = super.loadClass(className, resolve);
        }

        return cls;
    }

}

