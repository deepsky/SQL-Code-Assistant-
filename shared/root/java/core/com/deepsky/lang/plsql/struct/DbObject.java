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

package com.deepsky.lang.plsql.struct;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Date;

public interface DbObject extends Serializable {

    final int TABLE_INT        = 1;
    final int PACKAGE_INT      = 2;
    final int TYPE_INT         = 4;
    final int SEQUENCE_INT     = 8;
    final int FUNCTION_INT     = 16;
    final int PROCEDURE_INT    = 32;
    final int VIEW_INT         = 64;
    final int COLUMN_INT       = 128;
    final int VARIABLE_INT     = 254;
    final int SYNONYM_INT      = 512;
    final int PACKAGE_BODY_INT      = 1024;
    final int FUNCTION_BODY_INT      = 2048;
    final int PROCEDURE_BODY_INT      = 4096;
    final int TRIGGER_INT      = 8192;

    final String TABLE        = "TABLE";
    final String PACKAGE      = "PACKAGE";
    final String TYPE         = "TYPE";
    final String SEQUENCE     = "SEQUENCE";
    final String FUNCTION     = "FUNCTION";
    final String PROCEDURE    = "PROCEDURE";
    final String VIEW         = "VIEW";
    final String COLUMN       = "COLUMN";
    final String VARIABLE     = "VARIABLE";
    final String SYNONYM      = "SYNONYM";

    final String PACKAGE_BODY      = "PACKAGE BODY";
    // todo - not implemented yet
    final String FUNCTION_BODY     = "FUNCTION_BODY";
    final String PROCEDURE_BODY    = "PROCEDURE_BODY";
    final String TRIGGER        = "TRIGGER";

    String getName();
    String getTypeName();

    Date getLastDDLTime();
    void setLastDDLTime(Date lastDDL);

    /**
     * Get source locator
     * @return - null means there is no source for db object
     */
    @Nullable
    SqlScriptLocator getLocator();
    void setLocator(SqlScriptLocator url);

    boolean isValid();

    @NotNull
    DbObject getRootContext();
}
