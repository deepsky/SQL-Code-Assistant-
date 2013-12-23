package com.deepsky.view.query_pane;

import com.deepsky.view.query_pane.markup.SqlStatementMarker;

import javax.swing.*;

public interface QueryResultWindow {

    /**
     * Show content page for current connection
     *
     * @param displayName
     */
    void showContent(String displayName);

    void showContent();

    void showContent(SqlStatementMarker marker);

    void showContent(SqlStatementMarker marker, boolean b, int i);

    QueryResultPanel createResultPanel(int panelType, SqlStatementMarker sqlMarker, Icon icon, String toolTip);

    QueryStatisticsPanel findOrCreateDMLResultPanel(String tabName, Icon icon, String toolTip);

    QueryResultPanel createResultPanel(int panelType, String tabName, Icon icon, String toolTip);
}
