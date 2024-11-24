package com.inteliense.jflux.db.connectors.details;

import com.inteliense.jflux.db.supporting.DbType;

public class DbConnectionSqlLiteDetails extends DbConnectionDetails {

    private String filepath;

    public DbConnectionSqlLiteDetails(String filepath) {
        super(DbType.SQLITE);
        this.filepath = filepath;
        build();
    }

    @Override
    protected void build() {
        append("filepath", String.class, filepath);
        append("name", String.class, filepath);

    }

}
