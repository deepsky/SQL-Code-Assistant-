package com.deepsky.gui2;

import com.deepsky.database.ora.DbUrl;
import com.deepsky.database.ora.JdbcDbUrl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class ConnectionSettingsDialog extends DialogWrapper {

    private ConnectionSettings4 settings4;
    private Project project;
    private DbUrl[] dbUrls;
    private boolean loginOnStartup = true;
    private boolean repairIfDropped = true;
    private int refreshPeriod = 60;
    private boolean initial = true;


    public ConnectionSettingsDialog(
            Project project,
            DbUrl url,
            int refreshPeriod, boolean loginOnStartup, boolean repairIfDropped){

        super(project, false);
        this.project = project;

        dbUrls = new DbUrl[]{url};
        this.refreshPeriod = refreshPeriod;
        this.loginOnStartup = loginOnStartup;
        this.repairIfDropped = repairIfDropped;
        this.initial = false;

        init();
        setTitle("Setup Connection");
    }

    public ConnectionSettingsDialog(Project project, DbUrl[] dbUrls){
        super(project, false);
        this.project = project;
        this.dbUrls = dbUrls;

        init();
        setTitle("Setup Connection");
    }

    @Override
    protected JComponent createCenterPanel() {
        if(initial){
            String[] hosts1 = new String[0];
            if(dbUrls != null && dbUrls.length > 0){
                Set<String> hosts = new HashSet<String>();
                for(DbUrl url:dbUrls){
                    if(url instanceof JdbcDbUrl)
                    hosts.add(((JdbcDbUrl)url).getHost().toLowerCase());
                }
                hosts1 = hosts.toArray(new String[hosts.size()]);
            }
            settings4 = new ConnectionSettings4(project, hosts1);
        } else {
            settings4 = new ConnectionSettings4(project, dbUrls, loginOnStartup, repairIfDropped, refreshPeriod);
        }
        return settings4.$$$getRootComponent$$$();
    }

    public void doOKAction(){
        if(settings4.doOKAction()){
            loginOnStartup = settings4.getLoginOnStartup();
            repairIfDropped = settings4.getRepairIfDropped();
            refreshPeriod = settings4.getRefreshPeriod();

            super.doOKAction();
        }
    }

    public DbUrl getDbUrl() {
        return settings4.getDbUrl();
    }

    public boolean getLoginOnStartup() {
        return loginOnStartup;
    }

    public boolean getRepairIfDropped() {
        return repairIfDropped;
    }

    public int getRefreshPeriod() {
        return refreshPeriod;
    }
}
