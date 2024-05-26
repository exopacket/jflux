package com.inteliense.jflux.http.api.base.models;

public abstract class ApiModelField {

    private String fieldName;
    private FieldType type;
    private FieldAccess access;
    private Object value;

    public ApiModelField() {
        this.fieldName = name();
        this.type = type();
        this.access = access();
    }

    public ApiModelField(Object value) {
        this.fieldName = name();
        this.type = type();
        this.access = access();
        this.value = value;
    }

    protected abstract String name();
    protected abstract FieldType type();
    protected abstract FieldAccess access();

}
