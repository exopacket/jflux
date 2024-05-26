package com.inteliense.jflux.http.api.base.src;

import com.inteliense.zeta.api.models.ApiModel;
import com.inteliense.zeta.api.models.ApiModelField;
import com.inteliense.zeta.api.prereqs.ApiService;
import com.inteliense.zeta.api.src.fields.FirstName;
import com.inteliense.zeta.api.src.fields.LastName;
import com.inteliense.zeta.api.src.fields.UserId;

import java.util.ArrayList;

public class UserModel extends ApiModel {

    public UserModel(ApiService service) {
        super("user", service);
    }

    @Override
    protected ArrayList<Class<? extends ApiModelField>> build(ArrayList<Class<? extends ApiModelField>> list) {
        list.add(UserId.class);
        list.add(FirstName.class);
        list.add(LastName.class);
        return list;
    }
}
