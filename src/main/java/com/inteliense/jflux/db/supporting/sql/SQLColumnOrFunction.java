package com.inteliense.jflux.db.supporting.sql;

public abstract class SQLColumnOrFunction {

    public abstract Class getType();
    public abstract String getTypeString();
    public abstract String full();
    public abstract String name();

}
