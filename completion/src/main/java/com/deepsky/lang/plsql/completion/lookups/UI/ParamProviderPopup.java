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

package com.deepsky.lang.plsql.completion.lookups.UI;

import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ParamProviderPopup {
    private String title;

    private List<CloseEventListener> listeners = new ArrayList<CloseEventListener>();

    protected static final String VALID_NAME_PATTERN = "^[a-zA-Z][a-zA-Z0-9\\_\\$\\#]*$";

    public ParamProviderPopup(String title) {
        this.title = title;

    }

    public void addCloseEventLister(CloseEventListener c) {
        if (c != null) {
            listeners.add(c);
        }
    }

    public abstract JComponent getRootComponent();

    public abstract JComponent getFocusedComponent();

    public interface CloseEventListener {
        /**
         * Notify about user initiated closing
         *
         * @param isOk - user pressed OK button
         */
        void close(boolean isOk);

    }

    public String getTitle() {
        return title;
    }

    public abstract String getName();
    public abstract String getStatementText();

    protected void fireCancelEvent() {
        for (CloseEventListener e : listeners) {
            e.close(false);
        }
    }


    protected void fireOKevent() {

        try {
            doAdditionalValidation();
        } catch (NameValidationException e) {
            // Process exception
            Messages.showErrorDialog(e.getMessage(), "Name Validation Error");
            return;
        }

        if (!getName().matches(VALID_NAME_PATTERN)) {
            // Process exception
            Messages.showErrorDialog("Entered name does not follow Oracle's guidelines for naming schema objects", "Name Validation Error");
            return;
        }
        String name = getName();
        if (name.length() > 30) {
            // Process exception
            Messages.showErrorDialog("Entered name exceeds max length of the name (30 characters)", "Name Validation Error");
            return;
        }

        // Check the name against reserved words
        if (Arrays.asList(RESRVER_WORDS).contains(name.toUpperCase())) {
            // Process exception
            Messages.showErrorDialog("Entered name was identified as Oracle's reserved word", "Name Validation Error");
            return;
        }

        for (CloseEventListener e : listeners) {
            e.close(true);
        }
    }

    protected class KeyListener extends KeyAdapter {
        public void keyPressed(@NotNull KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                fireOKevent();
            }
        }
    }

    protected void doAdditionalValidation() throws NameValidationException {
        // Do nothing by default
    }

    protected class NameValidationException extends Exception {

        public NameValidationException(String message) {
            super(message);
        }
    }


    static final protected String[] RESRVER_WORDS = {"ACCESS",
            "ADD",
            "ALL",
            "ALTER",
            "AND",
            "ANY",
            "AS",
            "ASC",
            "AUDIT",
            "BETWEEN",
            "BY",
            "CHAR",
            "CHECK",
            "CLUSTER",
            "COLUMN",
            "COMMENT",
            "COMPRESS",
            "CONNECT",
            "CREATE",
            "CURRENT",
            "DATE",
            "DECIMAL",
            "DEFAULT",
            "DELETE",
            "DESC",
            "DISTINCT",
            "DROP",
            "ELSE",
            "EXCLUSIVE",
            "EXISTS",
            "FILE",
            "FLOAT",
            "FOR",
            "FROM",
            "GRANT",
            "GROUP",
            "HAVING",
            "IDENTIFIED",
            "IMMEDIATE",
            "IN",
            "INCREMENT",
            "INDEX",
            "INITIAL",
            "INSERT",
            "INTEGER",
            "INTERSECT",
            "INTO",
            "IS",
            "LEVEL",
            "LIKE",
            "LOCK",
            "LONG",
            "MAXEXTENTS",
            "MINUS",
            "MLSLABEL",
            "MODE",
            "MODIFY",
            "NOAUDIT",
            "NOCOMPRESS",
            "NOT",
            "NOWAIT",
            "NULL",
            "NUMBER",
            "OF",
            "OFFLINE",
            "ON",
            "ONLINE",
            "OPTION",
            "OR",
            "ORDER",
            "PCTFREE",
            "PRIOR",
            "PRIVILEGES",
            "PUBLIC",
            "RAW",
            "RENAME",
            "RESOURCE",
            "REVOKE",
            "ROW",
            "ROWID",
            "ROWNUM",
            "ROWS",
            "SELECT",
            "SESSION",
            "SET",
            "SHARE",
            "SIZE",
            "SMALLINT",
            "START",
            "SUCCESSFUL",
            "SYNONYM",
            "SYSDATE",
            "TABLE",
            "THEN",
            "TO",
            "TRIGGER",
            "UID",
            "UNION",
            "UNIQUE",
            "UPDATE",
            "USER",
            "VALIDATE",
            "VALUES",
            "VARCHAR",
            "VARCHAR",
            "VIEW",
            "WHENEVER",
            "WHERE",
            "WITH",
    };


}
