package com.inteliense.jflux.http.api.base;

import com.inteliense.jflux.http.api.base.endpoints.permissions.ApiEndpointPermissions;
import com.inteliense.jflux.http.api.base.models.ApiModel;
import com.inteliense.jflux.http.api.base.models.AppModelCollection;
import com.inteliense.jflux.http.api.base.prereqs.ApiBase;
import com.inteliense.jflux.http.api.base.prereqs.ApiService;
import com.inteliense.jflux.http.api.base.users.ApiUserPermissions;
import com.inteliense.jflux.http.api.server.config.APIServerConfig;
import com.inteliense.jflux.http.api.server.exceptions.APIException;

import java.util.ArrayList;

public abstract class ApiMain {

    private String appName;
    private ApiService service;
    private APIServerConfig config;
    private ApiBase base;

    public ApiMain(String appName) throws APIException {
        this.appName = appName;
        this.base = new ApiBase(getConfig(), buildService());
    }

    protected abstract ApiService buildService();
    protected abstract APIServerConfig getConfig();
    protected abstract AppModelCollection fillModels(ArrayList<Class<ApiModel>> models);
    protected abstract ApiEndpointPermissions setEndpointPermissions();

}
