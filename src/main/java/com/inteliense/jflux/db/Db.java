package com.inteliense.jflux.db;

import com.inteliense.jflux.crypto.Rand;
import com.inteliense.jflux.crypto.builtin.SHA;
import com.inteliense.jflux.db.connectors.details.DbConnectionDetails;
import com.inteliense.jflux.db.connectors.details.DbConnectionMysqlDetails;
import com.inteliense.jflux.db.connectors.details.DbConnectionSqlLiteDetails;
import com.inteliense.jflux.db.supporting.DbDriver;
import com.inteliense.jflux.db.supporting.Query;
import com.inteliense.jflux.exceptions.types.CriticalException;

public class Db {

    private DbDriver driver;
    private String database = null;

    public Db(DbConnectionDetails details) {
        this.driver = new DbDriver(details);
        this.database = details.get("name");
    }

    public Db(DbConnectionSqlLiteDetails details) {
        this.driver = new DbDriver(details);
        this.database = details.get("name");
    }

    public static Db mysql(String db, String host, int port, String username, String password) {
        DbConnectionMysqlDetails details = new DbConnectionMysqlDetails(db, host, port, username, password);
        return new Db(details);
    }

    public void setDatabase(String database) throws CriticalException, Exception {
        this.database = dbName(database);
        this.driver.conn().changeDb(database);
    }

    public Query query() {
        if(database == null) return null;
        return this.driver.buildQuery(dbName(database));
    }

    public Query query(String database) {
        try {
            setDatabase(database);
            return this.driver.buildQuery(dbName(database));
        } catch (Exception | CriticalException e) { e.printStackTrace(); }
        return null;
    }

    private String dbName(String db) {
        return db;
    }

    public DbDriver getRawDriver() {
        return this.driver;
    }

    public static String generateId() {
        return SHA.getSha1(Rand.secure(64));
    }

}
