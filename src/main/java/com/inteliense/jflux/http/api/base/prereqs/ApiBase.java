package com.inteliense.jflux.http.api.base.prereqs;

import com.inteliense.jflux.http.api.server.API;
import com.inteliense.jflux.http.api.server.config.APIServerConfig;
import com.inteliense.jflux.http.api.server.encryption.APIKeyPair;
import com.inteliense.jflux.http.api.server.exceptions.APIException;

public class ApiBase extends API {

    public ApiBase(APIServerConfig config, ApiService service) throws APIException {
        super(config, service);
    }

    @Override
    public APIKeyPair lookupApiKey(String apiKey) {
        return null;
    }

}
