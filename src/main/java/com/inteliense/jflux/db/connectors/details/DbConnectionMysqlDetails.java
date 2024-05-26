package com.inteliense.jflux.db.connectors.details;

import com.inteliense.jflux.db.supporting.DbType;
import org.extendedweb.aloft.lib.lang.types.base.T;

public class DbConnectionMysqlDetails extends DbConnectionDetails {

    private String name;
    private String host;
    private int port;
    private String username;
    private String password;

    public DbConnectionMysqlDetails(String name, String host, int port, String username, String password) {
        super(DbType.MYSQL);
        this.name = name;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        build();
    }

    @Override
    protected void build() {
        append("name", T.string(), name);
        append("host", T.string(), host);
        append("port", T.number(), port);
        append("username", T.string(), username);
        append("password", T.string(), password);
    }

}
