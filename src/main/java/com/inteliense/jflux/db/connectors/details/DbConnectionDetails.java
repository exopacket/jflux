package com.inteliense.jflux.db.connectors.details;

import com.inteliense.jflux.db.supporting.DbType;

import java.util.HashMap;

public abstract class DbConnectionDetails {

    protected DbType type;
    protected HashMap<String, DbDetailsPair> values = new HashMap<String, DbDetailsPair>();

    public DbConnectionDetails(DbType type) {
        this.type = type;
    }

    protected abstract void build();

    public <Any> Any get(String key) {
        if(!values.containsKey(key)) return null;
        return values.get(key).get();
    }

    public DbType getType() {
        return type;
    }

    protected void append(String key, Class<?> type, Object value) {
        values.put(key, new DbDetailsPair(type, value));
    }

    public static class DbDetailsPair {

        private Class<?> type;
        private Object v;

        public DbDetailsPair(Class<?> type, Object v) {
            this.type = type;
            this.v = v;
        }

        public <Any> Any get() {
            return (Any) type.cast(v);
        }

    }

}