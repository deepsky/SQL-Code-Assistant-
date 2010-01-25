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
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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

package com.deepsky.database.cache.impl;

import com.deepsky.database.cache.UpdatableSourceTextProvider;
import com.deepsky.database.cache.Utils;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.utils.FileUtils;
import com.deepsky.utils.StringUtils;

import java.io.File;
import java.io.IOException;

public class UpdatableSourceTextProviderImpl implements UpdatableSourceTextProvider {

    File store;
    public UpdatableSourceTextProviderImpl(String storeDir){
        store = new File(storeDir);
        if( store.exists()){
            if(store.isDirectory()){
                if(!store.canWrite()){
                    // directory exists but not writable
                    throw new ConfigurationException("Directory is not writable: " + storeDir);
                }
            } else {
                // path is not directory
                throw new ConfigurationException("Specified path is not directory: " + storeDir);
            }
        } else {
            // try to create a directory
            if( !FileUtils.createDir(store) ){
                throw new ConfigurationException("Cannot create directory: " + storeDir);
            }
        }
    }

    public String getText(String name, String type) {
        File f = new File(store, Utils.encryptFileName(type, name));
        if (f.exists()) {
            try {
                String content = StringUtils.file2string(f);
                // todo -- maybe need to return file modification time
                return content;
            } catch (IOException e) {
                // ignore so far
            }
        }
        return null;
    }

    public void put(String name, String type, String text) {
        try {
            StringUtils.string2file(
                    text,
                    new File(store, Utils.encryptFileName(type, name))
            );
        } catch (IOException e) {
        }
    }

    public void remove(String name, String type) {
        new File(store, Utils.encryptFileName(type, name)).delete();
    }
}
