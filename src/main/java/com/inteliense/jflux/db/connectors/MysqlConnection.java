package com.inteliense.jflux.db.connectors;

import com.inteliense.jflux.db.connectors.details.DbConnectionMysqlDetails;
import com.inteliense.jflux.db.supporting.DbConnection;
import com.inteliense.jflux.db.supporting.ExecutesQueries;
import com.inteliense.jflux.db.supporting.QueryParams;
import com.inteliense.jflux.db.supporting.QueryResults;
import com.inteliense.jflux.db.supporting.sql.Condition;
import com.inteliense.jflux.db.supporting.sql.Field;
import com.inteliense.jflux.db.supporting.sql.SQLBuilder;
import com.inteliense.jflux.exceptions.types.CriticalException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MysqlConnection extends DbConnection implements ExecutesQueries  {

    private Connection conn = null;

    public MysqlConnection(DbConnectionMysqlDetails details) {
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

        String jdbc = "jdbc:mysql://" + details.get("host") + ":" + details.get("port") + "/" + details.get("name");

        conn = DriverManager.getConnection(jdbc, details.get("username"), details.get("password"));
        if(conn == null) throw new CriticalException("Failed to connect to mysql.");

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
            String preparedSql = builder.getPreparedString(false);
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
            e.printStackTrace();
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
            String preparedSql = builder.getPreparedString(false);
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
