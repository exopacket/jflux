package com.inteliense.jflux.http.api.base.src.fields;

import com.inteliense.zeta.api.models.ApiModelField;
import com.inteliense.zeta.api.models.FieldAccess;
import com.inteliense.zeta.api.models.FieldType;

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
