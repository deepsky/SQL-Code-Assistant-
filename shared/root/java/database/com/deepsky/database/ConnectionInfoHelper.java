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

package com.deepsky.database;

import com.deepsky.database.ora.DbUrl;
import com.deepsky.utils.persistence.PersistableMap2;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;


public class ConnectionInfoHelper {

    final String COMMONS_CFG = "commons_cfg.log";
    final String ACTIVE_SESSION_KEY = "active_session_key";

    PersistableMap2 commonCfg;
    ConnectionInfo activeConnInfo;
    ConnectionInfoFactory factory;
    Map<String, ConnectionInfo> url2cinfo = new HashMap<String, ConnectionInfo>();

    public ConnectionInfoHelper(File storeDir) {
        commonCfg = new PersistableMap2(new File(storeDir.toString()), COMMONS_CFG);
    }

    public void init(ConnectionInfoFactory factory) {
        this.factory = factory;


        String active = null;
        for (PersistableMap2.Entry e : commonCfg.entrySet()) {
            if (e.key().equalsIgnoreCase(ACTIVE_SESSION_KEY)) {
                // skip
                active = e.value();
            } else {
                url2cinfo.put(e.key(), string2cInfo(e.value()));
            }
        }

/*
        if (active != null && url2cinfo.get(active) != null) {
            activeConnInfo = url2cinfo.get(active);
        }
*/
    }

    public void setActive(DbUrl key) throws HelperException {
        if (key != null) {
            String id = key.getUserHostPortServiceName().toLowerCase();
            if (url2cinfo.get(id) != null) {
                activeConnInfo = url2cinfo.get(id);
                commonCfg.put(ACTIVE_SESSION_KEY, id);
            } else {
                throw new HelperException("Session with URL :" + id + " not found");
            }
        } else {
            activeConnInfo = null;
            commonCfg.remove(ACTIVE_SESSION_KEY);
        }
    }

    public void deActivate(){
        if(activeConnInfo != null){
            activeConnInfo = null;
            commonCfg.remove(ACTIVE_SESSION_KEY);
        }
    }

    public ConnectionInfo getActive() {
        return activeConnInfo;
    }

    public ConnectionInfo get(DbUrl key) {
        return url2cinfo.get(key.getUserHostPortServiceName().toLowerCase());
    }

    public void put(ConnectionInfo cinfo) {
        String id = cinfo.getUrl().getUserHostPortServiceName().toLowerCase();
        url2cinfo.put(id, cinfo);
        commonCfg.put(id, cInfo2string(cinfo));
    }

    public ConnectionInfo remove(DbUrl key) {
        ConnectionInfo cinfo = url2cinfo.remove(key.getUserHostPortServiceName().toLowerCase());
        if (cinfo != null) {
            commonCfg.remove(key.getUserHostPortServiceName().toLowerCase());

            if (activeConnInfo != null && cinfo.equals(activeConnInfo)) {
                activeConnInfo = null;
                commonCfg.remove(ACTIVE_SESSION_KEY);
            }
        }

        return cinfo;
    }

    public ConnectionInfo[] getList() {
        return url2cinfo.values().toArray(new ConnectionInfo[url2cinfo.values().size()]);
    }

    String cInfo2string(ConnectionInfo cinfo) {
        return
                cinfo.getUrl().getFullUrl()
                        + "$" + cinfo.loginOnStart()
                        + "$" + cinfo.refreshPeriod()
                        + "$" + cinfo.repaireFailedConnection()
                        + ((cinfo.lastLoginTime() == null) ? "" : "$" + cinfo.lastLoginTime().getTime());
    }

    ConnectionInfo string2cInfo(String serialized) {
        String[] parts = serialized.split("\\$");
        switch (parts.length) {
            case 0:
                return null;
            case 1:
                return factory.create(new DbUrl(parts[0]), false, false);
            case 2: {
                DbUrl url = new DbUrl(parts[0]);
                boolean loginOnStart = Boolean.parseBoolean(parts[1]);
                return factory.create(url, loginOnStart, false);
            }
            case 3: {
                DbUrl url = new DbUrl(parts[0]);
                boolean loginOnStart = Boolean.parseBoolean(parts[1]);
                int refreshPeriod = Integer.parseInt(parts[2]);
                return factory.create(url, loginOnStart, false, refreshPeriod);
            }
            case 4: {
                DbUrl url = new DbUrl(parts[0]);
                boolean loginOnStart = Boolean.parseBoolean(parts[1]);
                int refreshPeriod = Integer.parseInt(parts[2]);
                boolean repaireFailedConnection = Boolean.parseBoolean(parts[3]);
                return factory.create(url, loginOnStart, repaireFailedConnection, refreshPeriod);
            }
            case 5: {
                DbUrl url = new DbUrl(parts[0]);
                boolean loginOnStart = Boolean.parseBoolean(parts[1]);
                int refreshPeriod = Integer.parseInt(parts[2]);
                boolean repaireFailedConnection = Boolean.parseBoolean(parts[3]);
                long lastLogin = Long.parseLong(parts[4]);
                return factory.create(url, loginOnStart, repaireFailedConnection, refreshPeriod, new Date(lastLogin));
            }
        }
        return null;
    }

    class HelperException extends Exception {
        public HelperException(String message) {
            super(message);
        }
    }

}
