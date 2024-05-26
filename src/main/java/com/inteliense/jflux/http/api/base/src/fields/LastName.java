package com.inteliense.jflux.http.api.base.src.fields;

import com.inteliense.zeta.api.models.ApiModelField;
import com.inteliense.zeta.api.models.FieldAccess;
import com.inteliense.zeta.api.models.FieldType;

public class LastName extends ApiModelField {
    @Override
    protected String name() {
        return "last_name";
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
