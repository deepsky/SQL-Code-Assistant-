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

package com.deepsky.database.fs;

import com.deepsky.database.fs.DbOriginatedSqlFile;
import com.deepsky.database.ora.SysDbObjectScriptLocator;

public class SysObjectSqlFile extends DbOriginatedSqlFile {

//    String content;
//    SysDbObjectScriptLocator locator;

    public SysObjectSqlFile(String content, SysDbObjectScriptLocator locator) {
//        super(locator.getScriptName() + ".sql", PlSqlSupportLoader.PLSQL, content);
        super(content, locator);
//        this.content = content;
//        this.locator = locator;
//        this.setWritable(false);
    }

//    public String getPresentableName() {
//        return locator.getScriptName();
//    }
//
//    public InputStream getInputStream() throws IOException {
//        return new ByteArrayInputStream(content.getBytes());
//    }

    public String getPath() {
//        String path = super.getPath();
//        return "user@10.252.1.1:sd1//sys." + objectName.toLowerCase();
//        return url + "//" + objectName.toLowerCase();
//            String path = getPresentableName() + " [" + url + "]";
        return locator.getPresentableUrl();
    }

//    public boolean isValid() {
//        return true;
//    }
//
//    public OutputStream getOutputStream(Object requestor, final long newModificationStamp, long newTimeStamp) throws IOException {
//        return super.getOutputStream(requestor, newModificationStamp, newTimeStamp);
//    }
//
//    public long getModificationStamp() {
//        return super.getModificationStamp();
//    }
//
//    @NotNull
//    public FileType getFileType(){
//        return PlSqlSupportLoader.PLSQL;
//    }
//
//
//    public int hashCode(){
//        return getPath().hashCode();
//    }
//
//    public boolean equals(Object o){
//        if(o instanceof SysObjectSqlFile){
//            return getPath().equals(((SysObjectSqlFile)o).getPath());
//        } else {
//            return false;
//        }
//    }
}
