package com.deepsky.database.ora;

import com.deepsky.lang.PsiUtil;
import com.deepsky.lang.parser.tns.TNSTypesAdopted;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.tnsnames.psi.NetServiceDesc;
import com.deepsky.lang.tnsnames.tree.TNSFileParser;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class TNSUrl implements DbUrl {

    private String userName;
    private String password;
    private String networkAlias;
    private String oraFilePath;
    private long astAge;
    private NetServiceDesc netService;
    private String alias;

    private SshSettings sshSettings;

    /**
     * Database connection URL specified as Net Service Address
     *
     * @param userName    user
     * @param password    pwd
     * @param oraFilePath path of tnsnames.ora file
     */
    public TNSUrl(String userName, String password, @NotNull String networkAlias, @NotNull String oraFilePath) {
        this.userName = userName;
        this.password = password;
        this.networkAlias = networkAlias;
        this.oraFilePath = oraFilePath;
        this.alias = userName + "@" + networkAlias;
    }

    /**
     * Database connection URL specified as Net Service Address
     *
     * @param userName    user
     * @param password    pwd
     * @param netService  parsed net service descriptor
     * @param oraFilePath path of tnsnames.ora file
     */
    public TNSUrl(String userName, String password,
                  @NotNull String networkAlias, NetServiceDesc netService, @NotNull String oraFilePath) {
        this.userName = userName;
        this.password = password;
        this.networkAlias = networkAlias;
        this.netService = netService;
        this.oraFilePath = oraFilePath;
        this.astAge = System.currentTimeMillis();
        this.alias = userName + "@" + networkAlias;
    }

    public String getTnsFilePath() {
        return oraFilePath;
    }

    public String getUser() {
        return userName;
    }

    public String getPwd() {
        return password;
    }

    public String getFullUrl() {
        if (!refreshNetworkAddress()) {
            throw new ConfigurationException("Cannot open TNS file: " + oraFilePath);
        }
        return DbUrl.driverPrefix
                + userName + "/"
                + password + "@"
                + netService.getBody(); //findChildByType(TNSTypesAdopted.DESCRIPTION).getText();
    }

    private boolean refreshNetworkAddress() {
        if (netService != null) { // && System.currentTimeMillis() - astAge < 60 * 1000) {
            return true;
        }
        // Reload network service AST
        File path = new File(oraFilePath);
        if (path.exists() && path.isFile()) {
            String content = null;
            try {
                content = StringUtils.file2string(path);
                TNSFileParser parser = new TNSFileParser();
                ASTNode root = parser.parse(content);
                ASTNode[] nodes = root.getChildren(TokenSet.create(TNSTypesAdopted.NET_SERVICE_DESC));
                for (ASTNode node : nodes) {
                    if (PsiUtil.isASTValid(node)) {
                        if (networkAlias.equalsIgnoreCase(((NetServiceDesc) node.getPsi()).getNetworkAlias())) {
                            netService = (NetServiceDesc) node.getPsi();
                            astAge = System.currentTimeMillis();
                            return true;
                        }
                    }
                }

            } catch (SyntaxTreeCorruptedException e) {
                // TODO - fix logging
                e.printStackTrace();
            } catch (IOException e) {
                // TODO - fix logging
                e.printStackTrace();
            }
        }
        return false;
    }

    public String serialize() {
        return DbUrlUtil.serialize(this);
    }

    private String getEssential() {
        return userName.toLowerCase() + "@"
                + networkAlias.toLowerCase() + "#"
                + oraFilePath;
    }

    public String getKey() {
        return userName + "@" + networkAlias;
    }

    public String getAlias() {
        return alias;
    }

    public DbUID getDbUID() {
        return new DbUID() {
            public DbUrl buildDbUrl(String user) {
                return new TNSUrl(user, password, networkAlias, netService, oraFilePath);
            }

            public boolean derivedFrom(DbUrl dbUrl) {
                return dbUrl instanceof TNSUrl
                        && getEssential().equals(((TNSUrl) dbUrl).getEssential());
            }

            public String key() {
                return networkAlias;
            }
        };
    }

    public boolean equals(Object obj) {
        if(obj instanceof TNSUrl){
            if(getEssential().equals(((TNSUrl) obj).getEssential())){
                TNSUrl tnsUrl = (TNSUrl) obj;
                return DbUrlUtil.equals(tnsUrl.getSshSettings(), getSshSettings());
            }
        }
        return false;
    }


    public int hashCode() {
        return getEssential().hashCode();
    }

    @NotNull
    public NetServiceDesc getNetServiceDesc() throws ConfigurationException {
        if (!refreshNetworkAddress()) {
            throw new ConfigurationException("Issue with opening and parsing of the TNS file: " + oraFilePath);
        }

        return netService;
    }

    public Object clone() throws CloneNotSupportedException {
        String url = serialize();
        return DbUrlUtil.parse(url);
    }

    public SshSettings getSshSettings() {
        return sshSettings;
    }

    public void setSshSettings(SshSettings sshSettings) {
        this.sshSettings = sshSettings;
    }


    public void setAlias(String alias) {
        if (alias != null && alias.length() > 0) {
            this.alias = alias;
        }
    }

    public String getNetworkAlias() {
        return networkAlias;
    }
}
