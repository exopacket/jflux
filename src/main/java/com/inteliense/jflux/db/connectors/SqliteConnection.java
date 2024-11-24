package com.inteliense.jflux.db.connectors;

import com.inteliense.jflux.db.connectors.details.DbConnectionDetails;
import com.inteliense.jflux.db.connectors.details.DbConnectionSqlLiteDetails;
import com.inteliense.jflux.db.supporting.*;
import com.inteliense.jflux.db.supporting.sql.Condition;
import com.inteliense.jflux.db.supporting.sql.Field;
import com.inteliense.jflux.db.supporting.sql.SQLBuilder;
import com.inteliense.jflux.exceptions.types.CriticalException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SqliteConnection extends DbConnection implements ExecutesQueries  {

    private Connection conn = null;

    public SqliteConnection(DbConnectionSqlLiteDetails details) {
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

        String jdbc = "jdbc:sqlite:" + details.get("filepath");
        this.conn = DriverManager.getConnection(jdbc);
        if(this.conn == null) throw new CriticalException("Failed to connect to sqlite.");

    }

    @Override
    public void close() throws Exception {
        if(conn != null) conn.close();
    }

    @Override
    public void changeDb(String database) throws Exception, CriticalException {
    }

    @Override
    public QueryResults execute(QueryParams p) {

        if(conn == null) {
            try {
                connect();
            } catch (Exception e) {
                e.printStackTrace();
            } catch (CriticalException e) {
                e.printStackTrace();
            }
        }

        try {
            SQLBuilder builder = new SQLBuilder(p);
            String preparedSql = builder.getPreparedString(true);
            PreparedStatement stmt = conn.prepareStatement(preparedSql);
            for(int i=0;i< builder.valueSize(); i++) {
                Object v = builder.next();
                if(v == null) break;
                if(v.getClass() == Field.class) v = ((Field) v).get();
                if(v.getClass() == Condition.class) v = ((Condition) v).value();
                SQLBuilder.set(stmt, i + 1, v);
            }
            ResultSet resultSet = stmt.executeQuery();
            return new QueryResults(resultSet, p.selectColumns(), p.tableName());
        } catch (Exception e) {
            onError(new CriticalException("Failed to execute query.", e));
        }

        return null;
    }

    @Override
    public void executeUpdate(QueryParams p) {

        if(conn == null) {
            try {
                connect();
            } catch (Exception e) {
                e.printStackTrace();
            } catch (CriticalException e) {
                e.printStackTrace();
            }
        }

        try {
            SQLBuilder builder = new SQLBuilder(p);
            String preparedSql = builder.getPreparedString(true);
            System.out.println(preparedSql);
            PreparedStatement stmt = conn.prepareStatement(preparedSql);
            for(int i=0;i< builder.valueSize(); i++) {
                Object v = builder.next();
                if(v.getClass() == Field.class) v = ((Field) v).get();
                if(v.getClass() == Condition.class) v = ((Condition) v).value();
                if(v == null) break;
                SQLBuilder.set(stmt, i + 1, v);
            }
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            onError(new CriticalException("Failed to execute query.", e));
        }

    }
}
