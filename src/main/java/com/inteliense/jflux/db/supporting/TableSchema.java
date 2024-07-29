package com.inteliense.jflux.db.supporting;

public class TableSchema {

    private String col;
    private String type;
    private boolean nullable;
    private boolean id;
    private String defaultValue;

    public TableSchema(String col, String type, boolean nullable, boolean id, String defaultValue) {
        this.col = col;
        this.type = type;
        this.nullable = nullable;
        this.id = id;
        this.defaultValue = defaultValue;
    }

    public static TableSchema id() {
        return new TableSchema("id", "text", false, true, null);
    }

    public static TableSchema col(String col, String type, boolean nullable, String defaultValue) {
        return new TableSchema(col, type, nullable, false, defaultValue);
    }

    public static TableSchema col(String col, String type, boolean nullable) {
        return new TableSchema(col, type, nullable, false, null);
    }

    public String getCol() {
        return col;
    }

    public boolean isId() {
        return id;
    }

    public boolean isNullable() {
        return nullable;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getType() {
        return type;
    }
}
