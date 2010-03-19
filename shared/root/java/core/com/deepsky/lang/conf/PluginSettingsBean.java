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

package com.deepsky.lang.conf;


public class PluginSettingsBean {

    private String dateFormat = "yyyy-MM-dd";
    private String timeFormat = "HH:mm:ss";
    private int fetchRecords = 200;
//    private int chunkSize = 200;
    private int numberOfTabs = 5;
    private boolean resolveReference = false;
    private boolean resolveUdt = true;
    private boolean validateFunc = false;
    private boolean validateTables = true;
    private boolean validateInsert = false;
    private boolean highlightSyntaxErrors = true;

    private boolean autoCommit = true;

    private String connection;

    private int dbBrowserSplitDividerLocation;

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public int getFetchRecords() {
        return fetchRecords;
    }

    public void setFetchRecords(int fetchRecords) {
        this.fetchRecords = fetchRecords;
    }

//    public int getChunkSize() {
//        return chunkSize;
//    }
//
//    public void setChunkSize(int chunkSize) {
//        this.chunkSize = chunkSize;
//    }

    public int getNumberOfTabs() {
        return numberOfTabs;
    }

    public void setNumberOfTabs(int numberOfTabs) {
        this.numberOfTabs = numberOfTabs;
    }

    public boolean getResolveReference() {
        return resolveReference;
    }

    public boolean getResolveUdt() {
        return resolveUdt;
    }

    public boolean getValidateFunc() {
        return validateFunc;
    }

    public boolean getValidateTables() {
        return validateTables;
    }

    public void setResolveReference(boolean resolveReference) {
        if(this.resolveReference != resolveReference){
            this.resolveReference = resolveReference;
            fireEvent();
        } else {
            this.resolveReference = resolveReference;
        }
    }

    public void setResolveUdt(boolean resolveUdt) {
        if(this.resolveUdt != resolveUdt){
            this.resolveUdt = resolveUdt;
            fireEvent();
        } else {
            this.resolveUdt = resolveUdt;
        }
    }

    public void setValidateFunc(boolean validateFunc) {
        if(this.validateFunc != validateFunc){
            this.validateFunc = validateFunc;
            fireEvent();
        } else {
            this.validateFunc = validateFunc;
        }
    }

    public void setValidateTables(boolean validateTables) {
        if(this.validateTables != validateTables){
            this.validateTables = validateTables;
            fireEvent();
        } else {
            this.validateTables = validateTables;
        }
    }

    private void fireEvent(){
        if(validationSettingsListener != null){
            validationSettingsListener.settingsUpdated();
        }
    }

    ValidationSettingsListener validationSettingsListener;
    public void setValidationSettingsListener(ValidationSettingsListener validationSettingsListener) {
        this.validationSettingsListener = validationSettingsListener;
    }

    public String getLastConnection() {
        return connection;
    }

    public void setLastConnection(String connection) {
        this.connection = connection;
    }

    public boolean isValidateInsert() {
        return validateInsert;
    }

    public void setValidateInsert(boolean validateInsert) {
        if(this.validateInsert != validateInsert){
            this.validateInsert = validateInsert;
            fireEvent();
        } else {
            this.validateInsert = validateInsert;
        }
    }

    public boolean getValidateInsert() {
        return validateInsert;
    }

    public boolean getHighlightSyntaxErrors() {
        return highlightSyntaxErrors;
    }

    public void setHighlightSyntaxErrors(boolean highlightSyntaxErrors) {
        if(this.highlightSyntaxErrors != highlightSyntaxErrors){
            this.highlightSyntaxErrors = highlightSyntaxErrors;
            fireEvent();
        } else {
            this.highlightSyntaxErrors = highlightSyntaxErrors;
        }
    }

    public int getDbBrowserSplitDividerLocation() {
        return dbBrowserSplitDividerLocation;
    }

    public void setDbBrowserSplitDividerLocation(int dbBrowserSplitDividerLocation) {
        this.dbBrowserSplitDividerLocation = dbBrowserSplitDividerLocation;
    }

    public boolean isAutoCommit() {
        return autoCommit;
    }

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }
}
