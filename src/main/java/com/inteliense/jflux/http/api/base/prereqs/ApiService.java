package com.inteliense.jflux.http.api.base.prereqs;

import com.inteliense.jflux.http.api.base.endpoints.permissions.ApiEndpointPermissions;
import com.inteliense.jflux.http.api.base.models.AppModelCollection;
import com.inteliense.jflux.http.api.base.users.ApiUserPermissions;

public class ApiService {

    private AppModelCollection appModels;
    private ApiUserPermissions userPermissions;
    private ApiEndpointPermissions endpointPermissions;

    public String getAppKey() {
        return "Hello World!";
    }

    public String getSession() {
        return "";
    }

    public String createSession() {
        return "";
    }

}
