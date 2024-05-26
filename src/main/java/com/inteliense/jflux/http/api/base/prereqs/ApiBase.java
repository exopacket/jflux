package com.inteliense.jflux.http.api.base.prereqs;

import com.inteliense.zeta.server.API;
import com.inteliense.zeta.server.config.APIServerConfig;
import com.inteliense.zeta.server.encryption.APIKeyPair;
import com.inteliense.zeta.server.exceptions.APIException;

public class ApiBase extends API {

    public ApiBase(APIServerConfig config, ApiService service) throws APIException {
        super(config, service);
    }

    @Override
    public APIKeyPair lookupApiKey(String apiKey) {
        return null;
    }

}
