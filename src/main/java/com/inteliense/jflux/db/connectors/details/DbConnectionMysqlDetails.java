package com.inteliense.jflux.db.connectors.details;

import com.inteliense.jflux.db.supporting.DbType;

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
        append("name", String.class, name);
        append("host", String.class, host);
        append("port", Integer.class, port);
        append("username", String.class, username);
        append("password", String.class, password);
    }

}
