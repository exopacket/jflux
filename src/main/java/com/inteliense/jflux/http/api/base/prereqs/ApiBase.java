package com.inteliense.jflux.http.api.base.prereqs;

import com.inteliense.jflux.http.api.server.API;
import com.inteliense.jflux.http.api.server.config.APIServerConfig;
import com.inteliense.jflux.http.api.server.containers.ClientSession;
import com.inteliense.jflux.http.api.server.encryption.APIKeyPair;
import com.inteliense.jflux.http.api.server.exceptions.APIException;
import com.inteliense.jflux.http.api.server.types.ContentType;

import java.util.HashMap;

public class ApiBase extends API {

    public ApiBase(APIServerConfig config, ApiService service) throws APIException {
        super(config, service);
    }

    @Override
    public boolean inTimeout(ClientSession clientSession, int perMinute) {
        return false;
    }

    @Override
    public boolean inBlacklist(ClientSession clientSession) {
        return false;
    }

    @Override
    public APIKeyPair lookupApiKey(String apiKey) {
        return null;
    }

    @Override
    public HashMap<String, String> getParameters(String body, ContentType contentType) {
        return null;
    }

    @Override
    public void addToBlacklist(ClientSession clientSession, ApiService.BlacklistEntryType entryType) {

    }

    @Override
    public void removeFromBlacklist(ClientSession clientSession) {

    }
}
