package com.inteliense.jflux.http.api.base.src.fields;

import com.inteliense.jflux.http.api.base.models.ApiModelField;
import com.inteliense.jflux.http.api.base.models.FieldAccess;
import com.inteliense.jflux.http.api.base.models.FieldType;

public class FirstName extends ApiModelField {
    @Override
    protected String name() {
        return "first_name";
    }

    @Override
    protected FieldType type() {
        return FieldType.STRING;
    }

    @Override
    protected FieldAccess access() {
        return FieldAccess.PUBLIC;
    }
}
