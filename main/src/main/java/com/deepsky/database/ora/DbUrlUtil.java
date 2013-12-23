package com.deepsky.database.ora;

import com.deepsky.lang.PsiUtil;
import com.deepsky.lang.parser.tns.TNSTypesAdopted;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.tnsnames.psi.Address;
import com.deepsky.lang.tnsnames.psi.AddressElement;
import com.deepsky.lang.tnsnames.psi.NetServiceDesc;
import com.deepsky.lang.tnsnames.tree.TNSFileParser;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DbUrlUtil {

    final private static Pattern DB_URL = Pattern.compile(
            "jdbc:oracle:thin:(([A-Za-z0-9\\-\\_\\$!]+)(/([A-Za-z0-9\\_\\.\\-\\$!]+))?)?\\@((?://)?[A-Za-z0-9\\_\\.\\-]+):([0-9]+)?(/|:)([A-Za-z0-9\\_\\.]+)(%([^&@]+)@([^&@]+)(&([^&]+))?)?"
//            "jdbc:oracle:thin:(([A-Za-z0-9\\_\\$]+)(/([A-Za-z0-9\\_\\.\\-\\$]+))?)?\\@((?://)?[A-Za-z0-9\\_\\.\\-]+):([0-9]+)?(/|:)([A-Za-z0-9\\_\\.]+)(%([^\\*@]+)@([^\\*@]+)(\\*[^\\*]+)?)?"
    );

    final private static Pattern TNS_URL = Pattern.compile(
            "jdbc:oracle:thin:(([A-Za-z0-9\\-\\_\\$!]+)(/([A-Za-z0-9\\_\\.\\-\\$!]+))?)?@([A-Za-z0-9\\_\\.\\-]+)#([^%]+)(%([^&@]+)@([^&@]+)(&([^&]+))?)?"
//            "jdbc:oracle:thin:(([A-Za-z0-9\\_\\$]+)(/([A-Za-z0-9\\_\\.\\-\\$]+))?)?@([A-Za-z0-9\\_\\.\\-]+)#([^%]+)(%((.*)@(.*))?)?"
    );


    /**
     * Create DbUrl from string
     *
     * jdbc:oracle:thin:test/test@localhost:1521:TEST
     * jdbc:oracle:thin:cool/coll@//localhost:1521/sid1%bob@localhost:67
     * jdbc:oracle:thin:cool/coll@//localhost:1521/sid1%bob@localhost:67&~/.ssh/id_rsa
     * jdbc:oracle:thin:tnsuser/tnsuser@win7#/home/sky/tnsnames.ora%helen@locahost
     * jdbc:oracle:thin:tnsuser/tnsuser@win7#/home/sky/tnsnames.ora%helen@locahost&~/.ssh/id_rsa
     *
     * @param dbUrl
     * @return
     * @throws ConfigurationException
     */
    public static DbUrl parse(String dbUrl) throws ConfigurationException {
        if (dbUrl == null) {
            throw new ConfigurationException("Database URL not specified");
        }

        Matcher m = DB_URL.matcher(dbUrl);
        if (!m.find()) {
            // TODO -- temp solution
            if (!dbUrl.startsWith("jdbc:oracle:thin:")) {
                // try to prefix url with driver type and parse one more time
                return parse("jdbc:oracle:thin:" + dbUrl);
            } else {
                // Check against TNS name
                Matcher m1 = TNS_URL.matcher(dbUrl);
                if (m1.find()) {
                    String user = m1.group(2);  // "test/pwd"
                    String pwd = m1.group(4);   // "/pwd"
                    String netAlias = m1.group(5);      // network alias
                    String tnsFilePath = m1.group(6);   // tnsnames.ora file path
                    final String sshUser = m1.group(8);   // ssh user name
                    final String sshHost = m1.group(9);   // ssh host
                    final String pk = m1.group(11);   // ssh host

                    if (user == null || netAlias == null || tnsFilePath == null) {
                        throw new ConfigurationException("Cannot restore Net Service URL: " + dbUrl);
                    }

                    TNSUrl url = new TNSUrl(user, pwd, netAlias, tnsFilePath);
                    if(sshUser != null && sshHost != null){
                        url.setSshSettings(new DbUrl.SshSettings() {
                            public String getHost() {
                                return sshHost;
                            }

                            public String getUserName() {
                                return sshUser;
                            }

                            @Override
                            public String getPrivateKeyFile() {
                                return pk;
                            }
                        });
                    }
                    return url;
                }

                throw new ConfigurationException("Database URL is malformed: " + dbUrl);
            }
        }
        String host = m.group(5);
        String determinator = m.group(7);

        if (host != null && host.startsWith("//") && "/".equals(determinator)) {
            // new syntax URL
            return parseServiceName(m);
        } else if (host != null && !host.startsWith("//") && ":".equals(determinator)) {
            // old syntax URL
            return parseSID(m);
        } else {
            throw new ConfigurationException("Cannot parse database URL");
        }
    }

    // jdbc:oracle:thin:[USER/PASSWORD]@//[HOST][:PORT]/SERVICE
    private static DbUrl parseServiceName(Matcher m1) {
        String user = m1.group(2);  // "test/pwd"
        String pwd = m1.group(4);   // "/pwd"
        String host = m1.group(5);      // host
        host = (host != null&& host.length()>2)?host.substring(2): host;
        String port = m1.group(6);   // port
        String dbName = m1.group(8);   // SERVICE NAME
        final String sshUser = m1.group(10);   // ssh user name
        final String sshHost = m1.group(11);   // ssh host
        final String pkFile = m1.group(13);   // ssh Private Key file

        DbUrlServiceName url = new DbUrlServiceName(user, pwd, host, port, dbName);
        if(sshUser != null && sshHost != null){
            url.setSshSettings(new DbUrl.SshSettings() {
                public String getHost() {
                    return sshHost;
                }

                public String getUserName() {
                    return sshUser;
                }

                @Override
                public String getPrivateKeyFile() {
                    return pkFile;
                }
            });
        }

        return url;
    }

    private static DbUrl parseSID(Matcher m1) {
        String user = m1.group(2);  // "test/pwd"
        String pwd = m1.group(4);   // "/pwd"
        String host = m1.group(5);      // host
        String port = m1.group(6);   // port
        String dbName = m1.group(8);   // SID
        final String sshUser = m1.group(10);   // ssh user name
        final String sshHost = m1.group(11);   // ssh host
        final String pkFile = m1.group(13);   // ssh Private Key file

        DbUrlSID url = new DbUrlSID(user, pwd, host, port, dbName);
        if(sshUser != null && sshHost != null){
            url.setSshSettings(new DbUrl.SshSettings() {
                public String getHost() {
                    return sshHost;
                }

                public String getUserName() {
                    return sshUser;
                }

                @Override
                public String getPrivateKeyFile() {
                    return pkFile;
                }
            });
        }

        return url;
    }


    /**
     * Extract host name and port from connection string
     * <p/>
     * Example:
     * "localhost:1521"
     * "qaitdora1:1521", "qaitdora2:1521"
     *
     * @param url
     * @return
     */
    public static String[] extractHostPort(@NotNull DbUrl url) {
        if (url instanceof JdbcDbUrl) {
            return new String[]{((JdbcDbUrl) url).getHost() + ":" + ((JdbcDbUrl) url).getPort()};
        } else if (url instanceof TNSUrl) {
            final List<String> out = new ArrayList<String>();
            NetServiceDesc netService = ((TNSUrl) url).getNetServiceDesc();
            iterateOverAddress(netService, new HostPortAddressElemHandler() {
                public boolean handle(ASTNode hostNode, ASTNode portNode) {
                    out.add(hostNode.getText() + ":" + (portNode != null ? portNode.getText() : "1521"));
                    return true;
                }
            });

            return out.toArray(new String[out.size()]);
        }
        return new String[0];
    }


    private static void iterateOverAddress(NetServiceDesc netService, HostPortAddressElemHandler handler) {
        for (Address address : netService.getAddressInfos()) {
            AddressElement aElement = address.findElementByName("protocol");
            if (aElement != null && "tcp".equalsIgnoreCase(aElement.getElementValue())) {
                AddressElement host = address.findElementByName("host");
                AddressElement port = address.findElementByName("port");
                if (host != null) {
                    if (!handler.handle(host.getElementNode(), port != null ? port.getElementNode() : null)) {
                        break;
                    }
                }
            }
        }
    }

    public static String serialize(TNSUrl url) {
        String sshTail = serialize(url.getSshSettings());
        return DbUrl.driverPrefix
                + url.getUser() + "/"
                + url.getPwd() + "@"
                + url.getNetworkAlias() + "#"
                + url.getTnsFilePath() + (sshTail!=null? "%" + sshTail: "");
//                + serialize((DbUrl.SshSettings)url.getSshSettings());
    }

    public static String serialize(DbUrl.SshSettings settings) {
        if(settings != null){
            boolean pk = settings.getPrivateKeyFile() != null && settings.getPrivateKeyFile().length() > 0;
            return settings.getUserName() + "@" + settings.getHost() + (pk? "&" + settings.getPrivateKeyFile(): "");
        }
        return null;
    }

    public static String serialize(DbUrlSID url) {
        String sshTail = serialize(url.getSshSettings());
        return url.getFullUrl() + (sshTail!=null? "%" + sshTail: "");
    }

    public static String serialize(DbUrlServiceName url) {
        String sshTail = serialize(url.getSshSettings());
        return url.getFullUrl() + (sshTail!=null? "%" + sshTail: "");
    }


    public static boolean equals(DbUrl.SshSettings set1, DbUrl.SshSettings set2) {
        if(set1 == null || set2 == null){
            return true;
        } else {
            String t = set1.getUserName() + "@" + set1.getHost();
            String t2 = set2.getUserName() + "@" + set2.getHost();
            if(t.equalsIgnoreCase(t2)){
                if(set1.getPrivateKeyFile() == null && set2.getPrivateKeyFile() == null){
                    return true;
                } else if(set1.getPrivateKeyFile() != null && set2.getPrivateKeyFile() != null){
                    return set1.getPrivateKeyFile().equals(set2.getPrivateKeyFile());
                } else {
                    return false;
                }
            }

            return false;
        }
    }

    private interface HostPortAddressElemHandler {
        boolean handle(ASTNode hostNode, ASTNode portNode);
    }

    /**
     * Replace host name and port in the target DbUrl with 'replacement'
     *
     * @param url
     * @param template
     * @param replacement
     * @return
     */
    public static DbUrl replaceHostPort(
            @NotNull DbUrl url, @NotNull final String template, @NotNull final String replacement)
            throws SyntaxTreeCorruptedException {

        if (url instanceof DbUrlSID) {
            DbUrlSID sid = (DbUrlSID) url;
            ReplacementHelper hlp = ReplacementHelper.create(sid.getHost(), sid.getPort(), template, replacement);
            if (hlp.match()) {
                return new DbUrlSID(sid.getUser(), sid.getPwd(), hlp.newHost(),
                        hlp.newPort(), sid.getSID_ServiceName());
            }

        } else if (url instanceof DbUrlServiceName) {
            DbUrlServiceName sn = (DbUrlServiceName) url;
            ReplacementHelper hlp = ReplacementHelper.create(sn.getHost(), sn.getPort(), template, replacement);
            if (hlp.match()) {
                return new DbUrlServiceName(sn.getUser(), sn.getPwd(), hlp.newHost(),
                        hlp.newPort(), sn.getSID_ServiceName());
            }
        } else if (url instanceof TNSUrl) {
            final TNSUrl tnsUrl = (TNSUrl) url;
            NetServiceDesc netService = tnsUrl.getNetServiceDesc();
            String content = netService.getText();

            while (true) {
                final StringBuilder b = new StringBuilder();
                ASTNode root = new TNSFileParser().parse(content);
                final ASTNode modifiedDesc = root.findChildByType(TNSTypesAdopted.NET_SERVICE_DESC);
                if(modifiedDesc == null || !PsiUtil.isASTValid(modifiedDesc)){
                    throw new SyntaxTreeCorruptedException("Cannot parse TNS Name descriptor for " + tnsUrl.getNetworkAlias());
                }
                final String finalContent = content;
                iterateOverAddress(modifiedDesc.getPsi(NetServiceDesc.class), new HostPortAddressElemHandler() {
                    public boolean handle(ASTNode hostNode, ASTNode portNode) {
                        ReplacementHelper hlp = ReplacementHelper.create(
                                hostNode.getText(), portNode.getText(), template, replacement);

                        if (hlp.match()) {
                            int start = hostNode.getStartOffset();
                            int end = start + hostNode.getTextLength();
                            int startP = portNode.getStartOffset();
                            int endP = startP + portNode.getTextLength();

                            b.append(finalContent.substring(modifiedDesc.getStartOffset(), start));
                            b.append(hlp.newHost());
                            b.append(finalContent.substring(end, startP));
                            b.append(hlp.newPort());
                            b.append(finalContent.substring(endP, modifiedDesc.getStartOffset() + modifiedDesc.getTextLength()));
                            return false;
                        }

                        return true;
                    }
                });

                if (b.length() == 0) {
                    return new TNSUrl(
                            tnsUrl.getUser(), tnsUrl.getPwd(), tnsUrl.getNetworkAlias(),
                            modifiedDesc.getPsi(NetServiceDesc.class),
                            tnsUrl.getTnsFilePath());
                }
                content = b.toString();
            }

        }
        return url;
    }


    private static class ReplacementHelper {

        private boolean isValid;
        private String newHost, newPort;

        private ReplacementHelper(String newHost, String newPort) {
            this.newHost = newHost;
            this.newPort = newPort;
            this.isValid = true;
        }

        private ReplacementHelper() {
            this.isValid = false;
        }

        private static ReplacementHelper create(String oldHost, String oldPort, String template, String replacement) {

            String[] parts = template.split(":");
            if (parts.length != 1 && parts.length != 2) {
                return new ReplacementHelper();
            } else if (parts.length == 1) {
                // Need some processing
                // Sanity check first
                if (parts[0].length() == 0) {
                    return new ReplacementHelper();
                }
                // Host only
                String host = (parts[0].charAt(parts[0].length() - 1) == ':') ?
                        parts[0].substring(0, parts[0].length() - 1) :
                        parts[0];
                if (oldHost.equalsIgnoreCase(host)) {
                    return new ReplacementHelper(replacement.split(":")[0], oldPort);
                }
            } else {
                // Two elements
                if (parts[0].length() == 0) {
                    // ":1521" case e.g. port number only
                    if (parts[1].equals(oldPort)) {
                        return new ReplacementHelper(oldHost, replacement.split(":")[1]);
                    }
                } else {
                    if (oldHost.equalsIgnoreCase(parts[0]) && oldPort.equals(parts[1])) {
                        String[] rep = replacement.split(":");
                        return new ReplacementHelper(rep[0], rep[1]);
                    }
                }
            }

            return new ReplacementHelper();
        }

        boolean match() {
            return isValid;
        }

        String newHost() {
            return newHost;
        }

        String newPort() {
            return newPort;
        }
    }


}
