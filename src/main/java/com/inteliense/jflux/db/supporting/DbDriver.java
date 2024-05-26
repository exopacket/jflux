package com.inteliense.jflux.db.supporting;

import com.inteliense.jflux.db.connectors.MysqlConnection;
import com.inteliense.jflux.db.connectors.RedisConnection;
import com.inteliense.jflux.db.connectors.SqliteConnection;
import com.inteliense.jflux.db.connectors.details.DbConnectionDetails;
import com.inteliense.jflux.db.connectors.details.DbConnectionMysqlDetails;
import com.inteliense.jflux.db.supporting.redis.RedisAdapter;

public class DbDriver {

    private DbType type;
    private DbConnection connection;

    public DbDriver(DbConnectionDetails details) {
        this.type = details.getType();
        if(type == DbType.REDIS) this.connection = new RedisConnection(details);
        else if(type == DbType.MYSQL) this.connection = new MysqlConnection((DbConnectionMysqlDetails) details);
        else if(type == DbType.SQLITE) this.connection = new SqliteConnection(details);
    }

    public DbConnection conn() {
        return connection;
    }

    public Query buildQuery() {
        if(type == DbType.REDIS) return new RedisAdapter(connection);
        else return new Query(connection);
    }

    public Query buildQuery(String db) {
        if(db == null) return buildQuery();
        if(type == DbType.REDIS) return new RedisAdapter(connection).database(db);
        else return new Query(connection).database(db);
    }

    public DbType getType() {
        return type;
    }
}
