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

package com.deepsky.conf;

import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.utils.PluginPathProcessor;
import com.deepsky.utils.PluginPathProcessorException;
import com.intellij.util.lang.UrlClassLoader;

import java.io.File;
import java.net.URL;
import java.util.List;

public class CacheLocator {

    static final LoggerProxy log = LoggerProxy.getInstance("#CacheLocator");

    private static final String DEFAULT_ROOT_PATH = ".";
    public static final String TEST_ROOT_PATH_PROP = "test.root.path";

    private static final String IDEA_PLUGINS_PATH = "idea.plugins.path";
    private static final String USER_HOME_DIR = "user.home";
    private static final String PROBE_JAR_TEMPL = "const_jjar.*";

    // todo -- IMPORTANT !!! This is a name of the bundled plugin, it should be in sync with the build scripts !!!
    private static final String SQL_ASSISTANT_PLUGIN_BUNDLED_NAME = "sql_assistant";

    private static File cacheDir;

    public static File getCacheDirectory() {
        if(cacheDir == null){
            cacheDir = getCacheDirectoryInternal();
        }

        return cacheDir;
    }
    
    private static File getCacheDirectoryInternal() {

        File cacheDir;

        String path = System.getProperty(TEST_ROOT_PATH_PROP);
        if (path == null) {
            log.warn("test.root.path not defined, try another alternatives ...");
            path = tryIdeaPluginsPath();
            if (path == null) {
                path = tryClassesDir();
//                URL url = CacheLocator.class.getClassLoader().getResource(DEFAULT_ROOT_PATH);
                if (path == null) {
                    log.warn("Default root path not defined, try to discover a cache dir based on list URLs ...");
                    if (CacheLocator.class.getClassLoader() instanceof UrlClassLoader) {
                        UrlClassLoader cloader = (UrlClassLoader) CacheLocator.class.getClassLoader();
                        List<URL> urls = cloader.getUrls();
                        for (URL _url : urls) {
                            log.info("... URL: " + _url);
                            try {
                                PluginPathProcessor proc = new PluginPathProcessor(_url.getPath());
                                if (proc.getJarName().matches(PROBE_JAR_TEMPL)) {
//                                if (proc.getJarName().matches("echache.*") || proc.getJarName().matches("const_storage.*")) {
                                    path = proc.getPluginHomeDir();
                                    path = path.replace("%20", " ");
                                    if(!canWrite(path)){
                                        path = null;
                                        continue;
                                    }
                                    log.info("The plugin cache dir discovered: " + path);
                                    break;
                                }
                            } catch (PluginPathProcessorException e) {
                                // todo --
                                int hh =0;
                            }
                        }
                    }

                    if (path == null) {
                        // try to use home directory
                        path = tryUserHomeDir();
                        if(path == null){
                            throw new ConfigurationException("Could not access a cache directory");
                        }
                    }
                } else {
                    // it supposed the url references <plugin_home_dir>/classes folder
//                    path = new File(url.getPath()).getParent();
                }
            }
        }

        cacheDir = new File(path, "caches");
        if (cacheDir.exists()) {
            if (!cacheDir.isDirectory()) {
                throw new ConfigurationException(cacheDir.toString() + " supposed to be a directory.");
            }
        } else {
            if (!cacheDir.mkdir()) {
                throw new ConfigurationException("Could not create " + cacheDir.toString() + " cache directory.");
            }
        }

        return cacheDir;
    }


    private static boolean canWrite(String path){
        return new File(path).exists() && new File(path).canWrite();
    }

    private static String tryUserHomeDir(){
        // it supposed the url references <plugin_home_dir>/classes folder
        log.info("Try user home dir ...");
        String path = System.getProperty(USER_HOME_DIR);
        if(path != null){
            log.info("user.home defined [" + path + "], check write permission");
            if (new File(path).canWrite()) {
                log.info("The path " + path + " exists and has write permission [done]");
                // create plugin directory
                boolean res = new File(path, SQL_ASSISTANT_PLUGIN_BUNDLED_NAME).mkdir();
                if(res){
                    return new File(path, SQL_ASSISTANT_PLUGIN_BUNDLED_NAME).getAbsolutePath();
                }
                log.warn("Could not create directory: " + new File(path, SQL_ASSISTANT_PLUGIN_BUNDLED_NAME).getAbsolutePath());
            } else {
                log.warn("The path " + path + " has no write permission");
            }

        } else {
            log.warn("Default path [./] not defined");
        }
        return null;
    }

    private static String tryClassesDir(){
        // it supposed the url references <plugin_home_dir>/classes folder
        URL url = CacheLocator.class.getClassLoader().getResource(DEFAULT_ROOT_PATH);
        if(url != null){
            log.info("Default path [./] defined, check write permission");
            String path = new File(url.getPath()).getParent();
            if (new File(path).canWrite()) {
                log.info("The path " + path + " exists and has write permission [done]");
                return path;
            } else {
                log.warn("The path " + path + " has no write permission");
            }

        } else {
            log.warn("Default path [./] not defined");
        }
        return null;
    }

    private static String tryIdeaPluginsPath() {
        log.info("Try the " + IDEA_PLUGINS_PATH + " property ...");
        String path = System.getProperty(IDEA_PLUGINS_PATH);
        if(path == null){
            log.info("... " + IDEA_PLUGINS_PATH + " not set up");
            path = System.getProperty("user.home");
            if(path != null){
                log.info("Try to use user home directory to store plugin's data: " + path);
                // Try to use user home directory to store plugin's data
                File base = new File(path);
                if(base.exists()){
                    File pluginPath = new File(base, SQL_ASSISTANT_PLUGIN_BUNDLED_NAME);
                    if(!pluginPath.exists()){
                        if(pluginPath.mkdir()){
                            log.error("Could not create plugin directory: " + pluginPath.getAbsolutePath());
                        }
                    }
                }
            }
        }
        if (new File(path).exists()) {
            log.info("The path pointed by " + IDEA_PLUGINS_PATH + " property, exists, try to locate SQL Assistant plugin ...");
            File pluginPath = new File((path), SQL_ASSISTANT_PLUGIN_BUNDLED_NAME);
            if (new File(pluginPath.getAbsolutePath()).exists()) {
                if( new File(pluginPath.getAbsolutePath()).canWrite()){
                    log.info("The path " + pluginPath.getAbsolutePath() + " exists and has write permission [done]");
                    return new File(pluginPath.getAbsolutePath()).getAbsolutePath();
                } else {
                    log.warn("The path " + pluginPath.getAbsolutePath() + " has no write permission!");
                }
            } else {
                log.warn("The path " + pluginPath.getAbsolutePath() + " does not exist");
            }
        } else {
            log.warn(IDEA_PLUGINS_PATH + " property not defined or pointed path does not exist.");
        }
        return null;
    }

}
