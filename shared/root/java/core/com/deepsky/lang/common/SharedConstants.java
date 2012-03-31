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

package com.deepsky.lang.common;


public interface SharedConstants {

    String ORACLE_JDBC_JAR_PATH = "ORACLE_JDBC_JAR_PATH";
    String ORACLE_JDBC_DRIVER = "ORACLE_JDBC_DRIVER";
    String ORACLE_JDBC_AUTOCOMMIT = "ORACLE_JDBC_AUTOCOMMIT";

//    String DATE_FORMAT = "DATE_FORMAT";
//    String TIME_FORMAT = "TIME_FORMAT";
    //    String RS_CHUNK_SIZE = "RS_CHUNK_SIZE";
//    String FETCH_RECORDS = "FETCH_RECORDS";
//    String QR_TABS = "QR_TABS";
    String PLUGIN_SETTINGS = "PLUGIN_SETTINGS";

    String CACHE_MANAGER = "cache_manager";
    String CONNECTION_MANAGER = "connection_manager";
    String OBJECT_CACHE = "object_cache";
    String DB_OBJECT_CONTR = "db_object_contributor";
    String QR_WINDOW = "QUERY_RESULT_WINDOW";
    String SEARCH_PARAMS = "SEARCH_PARAMS";
    String SQL_RESOLVER = "SQL_RESOLVER";
    String FS_SQL_INDEX = "FS_SQL_INDEX";
    String DB_NAMES_INDEX = "DB_NAMES_INDEX";
    String EVNT_CHANNEL = "EVNT_CHANNEL";

    String LOCAL_FS = "local_fs";

    String SCHEMA_FACADE_PROVIDER = "SCHEMA_FACADE_PROVIDER";
    String SQL_INDEX_MANAGER = "SQL_INDEX_MANAGER";
    String PLSQLFILE_CHANGE_TRACKER = "PLSQLFILE_CHANGE_TRACKER"; 
    String NAME_LOOKUP = "NAME_LOOKUP";
    String WORD_INDEX_MAN = "WORD_INDEX_MAN";
    String LOCAL_FS_URL = "LOCAL_FS_URL";
    String TIMESTAMP_ValueConvertor = "TIMESTAMP_ValueConvertor";
    String TIMESTAMPTZ_ValueConvertor = "TIMESTAMPTZ_ValueConvertor";
    String TIMESTAMPLTZ_ValueConvertor = "TIMESTAMPLTZ_ValueConvertor";

    String ACTIVE_FILE_PATTERNS = "ACTIVE_FILE_PATTERNS";
}
