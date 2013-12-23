package com.deepsky.database.impl;

import com.deepsky.database.ConnectionHelper;
import com.deepsky.database.DBException;
import com.deepsky.database.DbMetaInfo;
import com.deepsky.database.NetworkUtil;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.database.ora.DbUrlUtil;
import com.deepsky.database.ora.LowLevelConnectionProvider;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.jcraft.jsch.*;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;

public class ConnectionHelperImpl implements ConnectionHelper {

    static LoggerProxy log = LoggerProxy.getInstance("#ConnectionHelperImpl");

    private final static int MIN_PORT = 9001;
    private final static int MAX_PORT = 9999;

    private LowLevelConnectionProvider master;
    private boolean cachedAutoCommit = true;
    private Session jschSession = null;

    private DbUrl originalUrl = null;

    public void disconnect() {
        if (isConnected()) {
            master.disconnect();
            disposeUrl();
            master = null;
        }
    }

    public DbMetaInfo getDbMetaInfo() {
        if (isConnected()) {
            return new DbMetaInfo() {
                public String getDbVersion() {
                    return master.getDatabaseVersion();
                }
            };
        }

        return null;
    }

    @Nullable
    public DbUrl getDbUrl() {
        if (master != null) {
            return originalUrl; //master.getDbUrl();
        }
        return null;
    }

    public boolean isConnected() {
        return master != null && master.isConnected();
    }

    public Connection getConnection() throws DBException {
        if (master != null)
            return master.getConnection();
        else {
            throw new DBException("Not connected");
        }
    }

    public void connect(DbUrl url) throws DBException {
        if (master != null) {
            throw new DBException("Connection was not closed properly before opening another");
//                if (master.getDbUrl().equals(url)) {
//                    return;
//                } else {
//                    master.disconnect();
//                }
        }

        try {
            url = prepareUrl(originalUrl = url);
            master = LowLevelConnectionProvider.create(url);
        } catch (DBException e) {
            master = null;
            disposeUrl();
            throw e;
        }
    }

    public LowLevelConnectionProvider cloneConnectionHolder() throws DBException {
        if (isConnected()) {
            return LowLevelConnectionProvider.create(master.getDbUrl());
        } else {
            throw new DBException("Not connected");
        }
    }

    public void setAutoCommit(boolean state) throws DBException {
        if (isConnected()) {
            master.setAutoCommit(state);
            cachedAutoCommit = state;
        } else {
            throw new DBException("Not connected");
        }
    }

    /**
     * Quick method to get current AutoCommit state
     *
     * @return - autocommit state
     */
    public boolean getAutoCommit() {
        return cachedAutoCommit;
    }


    private DbUrl prepareUrl(DbUrl url) throws DBException {
        DbUrl.SshSettings sshSettings = url.getSshSettings();
        if(sshSettings != null){
            JSch jsch = new JSch();
            ConfigRepository config = null;
            try {
                config = OpenSSHConfig.parse("UserKnownHostsFile ~/.ssh/known_hosts");
                jsch.setConfigRepository(config);
            } catch (IOException e) {
                log.warn("Cannot set up UserKnownHostsFile parameter");
            }
            if(sshSettings.getPrivateKeyFile() != null){
                try {
                    jsch.addIdentity(sshSettings.getPrivateKeyFile());
                } catch (JSchException e) {
                    log.warn("Cannot add PK file: " + sshSettings.getPrivateKeyFile());
                }
            }

            Session session = null;
            try {
                session = jsch.getSession(sshSettings.getUserName(), sshSettings.getHost(), 22);
                UserInfo ui = new SshUserInfo();
                session.setUserInfo(ui);

                session.connect();

                DbUrl adoptedUrl = url;
                int startPort = MIN_PORT;
                for(String hostPort: DbUrlUtil.extractHostPort(url)){
                    String[] host_port = hostPort.split(":");
                    int remotePort = Integer.parseInt(host_port[1]);
                    int localPort = nextFreePort(startPort); // Choose a free port on local box

                    log.info("Try to forward port: " +
                            "localhost:" + localPort + " -> " + host_port[0] + ":" + remotePort);

                    int assigned_port = session.setPortForwardingL(localPort, host_port[0], remotePort);
                    adoptedUrl = DbUrlUtil.replaceHostPort(adoptedUrl, hostPort, "localhost:" + assigned_port);
                    startPort = assigned_port+1;
                    log.info("... done, assigned port: " + assigned_port);
                }
                jschSession = session;
                return adoptedUrl;
            } catch (JSchException e) {
                if(session != null){
                    session.disconnect();
                }
                log.warn("[JSchException] " + e.getMessage());
                if(e.getCause() instanceof UnknownHostException){
                    throw new DBException("Cannot connect to " + e.getCause().getMessage());
                } else if(e.getMessage().startsWith("Auth cancel")){
                    throw new DBException("Authentication canceled by user");
                }
                throw new DBException(e.getMessage());
            } catch (Throwable e) {
                if(session != null){
                    session.disconnect();
                }
                log.warn("[Exception] " + e.getMessage());
                throw new DBException(e.getMessage());
            }
        } else {
            return url;
        }
    }


    private void disposeUrl() {
        if(jschSession != null){
            jschSession.disconnect();
            jschSession = null;
        }
    }

    private int nextFreePort(int startNum) throws Exception {
        while(!NetworkUtil.available(startNum, MIN_PORT, MAX_PORT)) startNum++;
        if(startNum<MIN_PORT && startNum >MAX_PORT ){
            throw new Exception("Not found available ports");
        }
        return startNum;
    }
}
