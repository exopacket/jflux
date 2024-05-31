package com.inteliense.jflux.http.api.base.src.fields;

import com.inteliense.jflux.http.api.base.models.ApiModelField;
import com.inteliense.jflux.http.api.base.models.FieldAccess;
import com.inteliense.jflux.http.api.base.models.FieldType;

public class UserId extends ApiModelField {
    @Override
    protected String name() {
        return "id";
    }

    @Override
    protected FieldType type() {
        return FieldType.ID;
    }

    @Override
    protected FieldAccess access() {
        return FieldAccess.PROTECTED;
    }
}
