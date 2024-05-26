package com.inteliense.jflux.http.api.base;

import com.inteliense.zeta.api.endpoints.permissions.ApiEndpointPermissions;
import com.inteliense.zeta.api.models.ApiModel;
import com.inteliense.zeta.api.models.AppModelCollection;
import com.inteliense.zeta.api.prereqs.ApiBase;
import com.inteliense.zeta.api.prereqs.ApiService;
import com.inteliense.zeta.api.users.ApiUserPermissions;
import com.inteliense.zeta.server.config.APIServerConfig;
import com.inteliense.zeta.server.exceptions.APIException;

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
