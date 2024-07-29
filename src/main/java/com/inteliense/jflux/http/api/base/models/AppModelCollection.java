package com.inteliense.jflux.http.api.base.models;

import java.util.List;

public abstract class AppModelCollection {

    private ApiModel[] models;

    public AppModelCollection(ApiModel[] models) { this.models = models; }

    public AppModelCollection(List<Class<ApiModel>> list) {
        ApiModel[] arr = new ApiModel[list.size()];
        this.models = list.toArray(arr);
    }

}
