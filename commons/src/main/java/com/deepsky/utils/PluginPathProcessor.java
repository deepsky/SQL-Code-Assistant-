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

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PluginPathProcessor {

    static private Pattern p = Pattern.compile(".*/plugins/(.*)/lib/([^/]+\\.(?:jar|zip))$");

    String path;
    String jarName;
    String pluginName;
    String pluginHomeDir;

    public PluginPathProcessor(String path) throws PluginPathProcessorException {
        this.path = path;

        Matcher m = p.matcher(path);
        if (m.find() && m.groupCount() == 2) {
            pluginName = m.group(1);
            jarName = m.group(2);

            int end = path.indexOf("/lib/" + jarName);
            pluginHomeDir = path.substring(path.charAt(0)== '/'? 1: 0, end);
        } else {
            throw new PluginPathProcessorException();
        }
    }

    public String getJarName() {
        return jarName;
    }

    public String getPluginName() {
        return pluginName;
    }

    public String getPluginHomeDir() {
        return pluginHomeDir;
    }
}
