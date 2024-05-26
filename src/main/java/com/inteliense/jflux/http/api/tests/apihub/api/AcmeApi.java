package com.inteliense.jflux.http.api.tests.apihub.api;

import com.inteliense.zeta.api.ApiMain;
import com.inteliense.zeta.api.endpoints.permissions.ApiEndpointPermissions;
import com.inteliense.zeta.api.models.ApiModel;
import com.inteliense.zeta.api.models.AppModelCollection;
import com.inteliense.zeta.api.prereqs.ApiService;
import com.inteliense.zeta.server.config.APIServerConfig;
import com.inteliense.zeta.server.exceptions.APIException;

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
