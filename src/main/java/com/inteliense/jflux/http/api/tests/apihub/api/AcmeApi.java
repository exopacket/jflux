package com.inteliense.jflux.http.api.tests.apihub.api;

import com.inteliense.jflux.http.api.base.ApiMain;
import com.inteliense.jflux.http.api.base.endpoints.permissions.ApiEndpointPermissions;
import com.inteliense.jflux.http.api.base.models.ApiModel;
import com.inteliense.jflux.http.api.base.models.AppModelCollection;
import com.inteliense.jflux.http.api.base.prereqs.ApiService;
import com.inteliense.jflux.http.api.server.config.APIServerConfig;
import com.inteliense.jflux.http.api.server.exceptions.APIException;

import java.util.ArrayList;

public class AcmeApi {

    private ApiMain api;
    public AcmeApi() {

        try {
            this.api = new ApiMain("Acme") {
                @Override
                protected ApiService buildService() {
                    return null;
                }

                @Override
                protected APIServerConfig getConfig() {
                    return null;
                }

                @Override
                protected AppModelCollection fillModels(ArrayList<Class<ApiModel>> models) {
                    return null;
                }

                @Override
                protected ApiEndpointPermissions setEndpointPermissions() {
                    return null;
                }
            };

        } catch (APIException e) {
            e.printStackTrace();
        }

    }

}
