package com.deepsky.database.ora;

public abstract class JdbcDbUrl implements DbUrl {

    private SshSettings sshSettings;

    public abstract String getPort();

    public abstract String getHost();

    public abstract String getSID_ServiceName();


    public abstract void setAlias(String alias);

    /**
     * Old syntax means this:
     *      jdbc:oracle:thin:@[HOST][:PORT]:SID
     * new syntax:
     *      jdbc:oracle:thin:@//[HOST][:PORT]/SERVICE
     *
     * @return - 192.168.1.23:1521:ORA1 or //192.168.1.23:1521/ORA1
     */
    public abstract String getHostPortServiceName();

    public String toString() {
        return getKey();
    }

    public Object clone() throws CloneNotSupportedException {
        String url = serialize();
        return DbUrlUtil.parse(url);
    }

    public SshSettings getSshSettings(){
        return sshSettings;
    }

    public void setSshSettings(SshSettings sshSettings){
        this.sshSettings = sshSettings;
    }

}
