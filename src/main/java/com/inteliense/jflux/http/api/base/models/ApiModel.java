package com.inteliense.jflux.http.api.base.models;

import com.inteliense.zeta.api.prereqs.ApiService;
import com.inteliense.zeta.utils.SHA;
import org.extendedweb.aloft.utils.encryption.A32;

import java.util.ArrayList;

public abstract class ApiModel {

    protected String modelName;
    protected String modelId;
    protected Class<? extends ApiModelField>[] fields;

    public ApiModel(String modelName, ApiService service) {
        this.modelName = modelName;
        String key = SHA.getHmac384(this.modelName, service.getAppKey());
        assert key != null;
        this.modelId = A32.casified(key);
        ArrayList<Class<? extends ApiModelField>> list = build(new ArrayList<>());
        Class<?>[] fields = new Class<?>[list.size()];
        list.toArray(fields);
        this.fields = (Class<? extends ApiModelField>[]) fields;
    }

    protected abstract ArrayList<Class<? extends ApiModelField>> build(ArrayList<Class<? extends ApiModelField>>list);

}
