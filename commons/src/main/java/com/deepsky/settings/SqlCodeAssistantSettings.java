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

package com.deepsky.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import oracle.sql.ArrayDescriptor;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

@State(
        name = "SqlCodeAssistantSettings",
        storages = {@Storage(
                id = "other",
                file = "$WORKSPACE_FILE$")})
public class SqlCodeAssistantSettings implements PersistentStateComponent<SqlCodeAssistantSettings> {

    private Font gridFont = Font.decode("Monospaced");
    private boolean serviceNameSelected;
    private String defaultTNSFilePath;
    private String pkFilePath;

    public static SqlCodeAssistantSettings getInstance(Project project) {
        return ServiceManager.getService(project, SqlCodeAssistantSettings.class);
    }

    public static final int TZNAME_NONE = 0;
    public static final int TZNAME_FORMAT = 1;
    public static final int DTH_DTM_FORMAT = 2;

    private String dateFormat = "yyyy-MM-dd";
    private String timeFormat = "HH:mm:ss";
    private String timeZoneFormat = "z";
    private int timeZoneFormat2 = DTH_DTM_FORMAT;
    private int fetchRecords = 200;
    private int numberOfTabs = 5;
    private boolean showRowCounting = true;

    private boolean autoCommit = true;
    private String connection;
    private String dbBrowserSelectedConnection;

    private int dbBrowserSplitDividerLocation;

    private boolean resolveReference = false;
    private boolean resolveUdt = false;
    private boolean validateFunc = false;
    private boolean validateTables = false;
    private boolean validateInsert = false;

    private boolean highlightSyntaxErrors = true;

    private boolean accessOfflineEnabled = false;
    private boolean isJDBCSettingsSelected = true;


    @SuppressWarnings({"ConstantConditions"})
    public SqlCodeAssistantSettings getState() {
        SqlCodeAssistantSettings out = new SqlCodeAssistantSettings();
        XmlSerializerUtil.copyBean(this, out);
        return out;
    }

    public void loadState(@NotNull final SqlCodeAssistantSettings settings) {
        XmlSerializerUtil.copyBean(settings, this);
    }


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

    public String getTimeZoneFormat() {
        return timeZoneFormat;
    }

    public int getTimeZoneFormat2() {
        return timeZoneFormat2;
    }

    public void setTimeZoneFormat(String timeZoneFormat) {
        this.timeZoneFormat = timeZoneFormat;
    }


    public int getNumberOfTabs() {
        return numberOfTabs;
    }

    public void setNumberOfTabs(int numberOfTabs) {
        this.numberOfTabs = numberOfTabs;
    }

    public boolean isHighlightSyntaxErrors() {
        return highlightSyntaxErrors;
    }

    public void setHighlightSyntaxErrors(boolean highlightSyntaxErrors) {
        this.highlightSyntaxErrors = highlightSyntaxErrors;
    }

    public boolean isAutoCommit() {
        return autoCommit;
    }

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getDbBrowserSelectedConnection() {
        return dbBrowserSelectedConnection;
    }

    public void setDbBrowserSelectedConnection(String dbBrowserSelectedConnection) {
        this.dbBrowserSelectedConnection = dbBrowserSelectedConnection;
    }

    public int getDbBrowserSplitDividerLocation() {
        return dbBrowserSplitDividerLocation;
    }

    public void setDbBrowserSplitDividerLocation(int dbBrowserSplitDividerLocation) {
        this.dbBrowserSplitDividerLocation = dbBrowserSplitDividerLocation;
    }

    public boolean isResolveReference() {
        return resolveReference;
    }

    public void setResolveReference(boolean resolveReference) {
        this.resolveReference = resolveReference;
    }

    public boolean isResolveUdt() {
        return resolveUdt;
    }

    public void setResolveUdt(boolean resolveUdt) {
        this.resolveUdt = resolveUdt;
    }

    public boolean isValidateFunc() {
        return validateFunc;
    }

    public void setValidateFunc(boolean validateFunc) {
        this.validateFunc = validateFunc;
    }

    public boolean isValidateTables() {
        return validateTables;
    }

    public void setValidateTables(boolean validateTables) {
        this.validateTables = validateTables;
    }

    public boolean isValidateInsert() {
        return validateInsert;
    }

    public void setValidateInsert(boolean validateInsert) {
        this.validateInsert = validateInsert;
    }

    public boolean isAccessOfflineEnabled() {
        return accessOfflineEnabled;
    }

    public void setAccessOfflineEnabled(boolean accessOfflineEnabled) {
        this.accessOfflineEnabled = accessOfflineEnabled;
    }

    public boolean getShowRowCounting() {
        return showRowCounting;
    }

    public void setShowRowCounting(boolean showRowCounting) {
        this.showRowCounting = showRowCounting;
    }

    public Font getGridFont() {
        return gridFont;
    }

    public boolean isJDBCSettingsSelected() {
        return isJDBCSettingsSelected;
    }

    public void setJDBCSettingsSelected(boolean JDBCSettingsSelected) {
        isJDBCSettingsSelected = JDBCSettingsSelected;
    }

    public boolean isServiceNameSelected() {
        return serviceNameSelected;
    }

    public void setServiceNameSelected(boolean serviceNameSelected) {
        this.serviceNameSelected = serviceNameSelected;
    }

    public String getDefaultTNSFilePath() {
        return defaultTNSFilePath;
    }

    public void setDefaultTNSFilePath(String defaultTNSFilePath) {
        this.defaultTNSFilePath = defaultTNSFilePath;
    }

    public void setPKFilePath(String PKFilePath) {
        this.pkFilePath = PKFilePath;
    }

    public String getPKFilePath() {
        return pkFilePath;
    }

    @NotNull
    public String getDefaultPKFilePath() {
        String userHome = System.getProperty("user.home");
        if(userHome != null){
            return userHome + "/.ssh/id_rsa";
        }
        return ".";
    }
}
