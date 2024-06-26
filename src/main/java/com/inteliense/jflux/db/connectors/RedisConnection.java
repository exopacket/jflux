package com.inteliense.jflux.db.connectors;

import com.inteliense.jflux.db.connectors.details.DbConnectionDetails;
import com.inteliense.jflux.db.supporting.*;
import com.inteliense.jflux.exceptions.types.CriticalException;
import redis.clients.jedis.Jedis;


public class RedisConnection extends DbConnection implements ExecutesQueries  {

    private Jedis conn = null;

    public RedisConnection(DbConnectionDetails details) {
        super(details);
    }

    @Override
    public Object getConn() {
        return conn;
    }

    @Override
    public void onError(CriticalException e) {
        e.report();
    }

    @Override
    protected void connect() throws Exception, CriticalException {

        conn = new Jedis();
        if(!conn.isConnected()) throw new CriticalException("Failed to connect to redis.");

    }

    @Override
    public void close() throws Exception {
        if(conn != null && conn.isConnected()) conn.close();
    }

    @Override
    public void changeDb(String database) throws Exception, CriticalException {

    }

    @Override
    public QueryResults execute(QueryParams p) {
        return null;
    }

    @Override
    public void executeUpdate(QueryParams p) {

    }
}
