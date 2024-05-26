package com.inteliense.jflux.db.supporting;

public interface ExecutesQueries {

    QueryResults execute(QueryParams p);
    void executeUpdate(QueryParams p);

}
