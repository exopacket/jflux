package com.inteliense.jflux.http.api.base.models;

import com.inteliense.jflux.crypto.builtin.SHA;
import com.inteliense.jflux.encoding.A32;
import com.inteliense.jflux.http.api.base.prereqs.ApiService;


import java.util.ArrayList;

public abstract class ApiModel {

    protected String modelName;
    protected String modelId;
    protected Class<? extends ApiModelField>[] fields;

    public ApiModel(String modelName, ApiService service) {
        this.modelName = modelName;
        String key = SHA.getHmac384(this.modelName, service.getAppKey());
        assert key != null;
        this.modelId = A32.get(key);
        ArrayList<Class<? extends ApiModelField>> list = build(new ArrayList<>());
        Class<?>[] fields = new Class<?>[list.size()];
        list.toArray(fields);
        this.fields = (Class<? extends ApiModelField>[]) fields;
    }

    protected abstract ArrayList<Class<? extends ApiModelField>> build(ArrayList<Class<? extends ApiModelField>>list);

    public void migrate() {

    }

}
