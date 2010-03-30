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

package com.deepsky.view;

import javax.swing.*;

public class Icons {

    public static final Icon FIND;
    public static final Icon SCHEMA_LIST;

    public static final Icon DB_SCHEMA;
    public static final Icon DB_SCHEMA_DISABLED;

    public static final Icon PACKAGE_BODY;
    public static final Icon PACKAGE_SPEC;
    public static final Icon REFRESH_SESSION;

    public static final Icon FUNCTION_BODY;
    public static final Icon FUNCTION_SPEC;
    public static final Icon PROCEDURE_BODY;
    public static final Icon PROCEDURE_SPEC;
    public static final Icon CURSOR_DECL;
    public static final Icon VARIABLE_DECL;
    public static final Icon PKG_INIT_SECTION;
    public static final Icon PLSQL_BLOCK;
    public static final Icon SQL_FILE;

    public static final Icon TABLE;
    public static final Icon PARTI_TABLE;
    public static final Icon TEMP_TABLE;
    public static final Icon EXT_TABLE;

    public static final Icon VIEW;

    public static final Icon TRIGGER;
    public static final Icon ENABLE_TRIGGER;
    public static final Icon DISABLE_TRIGGER;

    public static final Icon RECORD_TYPE_DECL;
    public static final Icon TABLE_COLL_DECL;
    public static final Icon OBJECT_TYPE_DECL;
    public static final Icon VARRAY_TYPE_DECL;

    public static final Icon EXEC_SQL_STMT;
    public static final Icon SELECT_RESULTSET;
    public static final Icon DML_RESULT;

    public static final Icon CLOSE_HOVERED;
    public static final Icon CLOSE;

    public static final Icon CONNECT;
    public static final Icon ARROW_DOWN;
    public static final Icon ARROW_UP;

    public static final Icon REFRESH_RESULTSET;
    public static final Icon EXPORT_DATA;
    public static final Icon DISCONNECT;
    public static final Icon TEST_CONNECTION;
    public static final Icon NEW_CONNECTION;
    public static final Icon REMOVE_CONNECTION;
    public static final Icon EDIT_CONNECTION_PARAMS;

    public static final Icon DB_BROWSER;
    public static final Icon QUERY_RESULT_PANE;

    public static final Icon SEPARATOR;
    public static final Icon CLOSE_PANEL;
    public static final Icon HELP;

    public static final Icon GUTTER_00;
    public static final Icon GUTTER_01;
    public static final Icon GUTTER_02;
    public static final Icon GUTTER_03;
    public static final Icon GUTTER_04;
    public static final Icon GUTTER_05;
    public static final Icon GUTTER_06;
    public static final Icon GUTTER_07;
    public static final Icon GUTTER_08;
    public static final Icon GUTTER_09;

    public static final Icon COMPILE_PKG;
    public static final Icon DROP;
    public static final Icon VIEW_DEF;
    public static final Icon VIEW_PKG_SPEC;
    public static final Icon VIEW_PKG_BODY;
    public static final Icon QUERY_DATA;

    public static final Icon PLUGIN_SETTINGS;

    public static final Icon AUTOCOMMIT_IS_ON;
    public static final Icon AUTOCOMMIT_IS_OFF;

    static {
        AUTOCOMMIT_IS_ON = Helpers.getIcon("/icons/autocommitOn.png");
        AUTOCOMMIT_IS_OFF = Helpers.getIcon("/icons/autocommitOff.png");

        FIND = Helpers.getIcon("/icons/find.png");
        DB_BROWSER = Helpers.getIcon("/icons/db_browser.png");
        QUERY_RESULT_PANE = Helpers.getIcon("/icons/query_result_pane.png");

        SCHEMA_LIST = Helpers.getIcon("/icons/schema_list.png");
        DB_SCHEMA = Helpers.getIcon("/icons/db_schema.png");
        DB_SCHEMA_DISABLED = Helpers.getIcon("/icons/db_schema_disabled.png");
        REFRESH_SESSION = Helpers.getIcon("/icons/sync.png"); 

        PACKAGE_BODY = Helpers.getIcon("/icons/pkg_body.png");
        PACKAGE_SPEC = Helpers.getIcon("/icons/pkg_spec.png");
        FUNCTION_SPEC = Helpers.getIcon("/icons/func_spec.png");
        FUNCTION_BODY = Helpers.getIcon("/icons/func_body.png");
        PROCEDURE_SPEC = Helpers.getIcon("/icons/proc_spec.png");
        PROCEDURE_BODY = Helpers.getIcon("/icons/proc_body.png");
        CURSOR_DECL = Helpers.getIcon("/icons/cursor.png");
        VARIABLE_DECL = Helpers.getIcon("/icons/variable.png");
        PKG_INIT_SECTION = Helpers.getIcon("/icons/pkg_init_section.png");
        PLSQL_BLOCK = Helpers.getIcon("/icons/plsql_block.png");
        SQL_FILE = Helpers.getIcon("/icons/sql_file.png");

        TABLE = Helpers.getIcon("/icons/table2.png");
        PARTI_TABLE = Helpers.getIcon("/icons/parti_table.png");
        TEMP_TABLE = Helpers.getIcon("/icons/temp_table.png");
        EXT_TABLE = Helpers.getIcon("/icons/ext_tab.png");

        VIEW = Helpers.getIcon("/icons/view.png");
        HELP = Helpers.getIcon("/icons/help.png");

        TRIGGER = Helpers.getIcon("/icons/trigger.png");
        ENABLE_TRIGGER = Helpers.getIcon("/icons/enable_trigger.png");
        DISABLE_TRIGGER = Helpers.getIcon("/icons/disable_trigger.png");

        TABLE_COLL_DECL = Helpers.getIcon("/icons/tabcoll_type.png");
        RECORD_TYPE_DECL = Helpers.getIcon("/icons/record_type.png");
        OBJECT_TYPE_DECL = Helpers.getIcon("/icons/tabcoll_type.png");
        VARRAY_TYPE_DECL = Helpers.getIcon("/icons/tabcoll_type.png"); 

        EXEC_SQL_STMT = Helpers.getIcon("/icons/exec_sql.png");
        // todo
        ARROW_DOWN = Helpers.getIcon("/icons/arrowDown.png");
        ARROW_UP = Helpers.getIcon("/icons/arrowUp.png");

        REFRESH_RESULTSET = Helpers.getIcon("/icons/refresh.png");
        EXPORT_DATA = Helpers.getIcon("/icons/copy.png");

        CONNECT = Helpers.getIcon("/icons/login.png");
        DISCONNECT = Helpers.getIcon("/icons/disconnect.png");
        TEST_CONNECTION = Helpers.getIcon("/icons/test_connection.png");
        NEW_CONNECTION = Helpers.getIcon("/icons/new_connection.png");
        REMOVE_CONNECTION = Helpers.getIcon("/icons/remove_connection.png");
        EDIT_CONNECTION_PARAMS = Helpers.getIcon("/icons/edit_connection_params.png");

        SELECT_RESULTSET =  Helpers.getIcon("/icons/select_resultset.png");
        DML_RESULT =  Helpers.getIcon("/icons/dml_result.png");

        CLOSE =  Helpers.getIcon("/icons/close.png");
        CLOSE_HOVERED =  Helpers.getIcon("/icons/closeHovered.png");
        CLOSE_PANEL =  Helpers.getIcon("/icons/cancel.png");

        SEPARATOR =  Helpers.getIcon("/icons/inactiveSeparator.png");

        GUTTER_00 = Helpers.getIcon("/icons/result00.png");
        GUTTER_01 = Helpers.getIcon("/icons/result01.png");
        GUTTER_02 = Helpers.getIcon("/icons/result02.png");
        GUTTER_03 = Helpers.getIcon("/icons/result03.png");
        GUTTER_04 = Helpers.getIcon("/icons/result04.png");
        GUTTER_05 = Helpers.getIcon("/icons/result05.png");
        GUTTER_06 = Helpers.getIcon("/icons/result06.png");
        GUTTER_07 = Helpers.getIcon("/icons/result07.png");
        GUTTER_08 = Helpers.getIcon("/icons/result08.png");
        GUTTER_09 = Helpers.getIcon("/icons/result09.png"); 

        COMPILE_PKG = Helpers.getIcon("/icons/compile_pkg.png"); 
        DROP = Helpers.getIcon("/icons/drop.png");

        VIEW_DEF = Helpers.getIcon("/icons/view_def.png");
        VIEW_PKG_SPEC = Helpers.getIcon("/icons/view_pkg_spec.png");
        VIEW_PKG_BODY = Helpers.getIcon("/icons/view_pkg_body.png"); 
        QUERY_DATA = Helpers.getIcon("/icons/query_data.png");

        PLUGIN_SETTINGS = Helpers.getIcon("/icons/properties.png"); 
    }

}
