package com.inteliense.jflux.db.supporting.redis;

import com.inteliense.jflux.db.supporting.DbConnection;
import com.inteliense.jflux.db.supporting.Query;

public class RedisAdapter extends Query {

    public RedisAdapter(DbConnection connection) {
        super(connection);
    }

}
