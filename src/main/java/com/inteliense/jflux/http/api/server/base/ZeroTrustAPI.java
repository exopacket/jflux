package com.inteliense.jflux.http.api.server.base;

import com.inteliense.jflux.http.api.base.prereqs.ApiService;
import com.inteliense.jflux.http.api.server.API;
import com.inteliense.jflux.http.api.server.config.APIServerConfig;
import com.inteliense.jflux.http.api.server.containers.ClientSession;
import com.inteliense.jflux.http.api.server.encryption.APIKeyPair;
import com.inteliense.jflux.http.api.server.exceptions.APIException;
import com.inteliense.jflux.http.api.server.types.APIServerType;
import com.inteliense.jflux.http.api.server.types.CORSPolicy;
import com.inteliense.jflux.http.api.server.types.ContentType;

import java.util.HashMap;

public class ZeroTrustAPI extends API {

    public ZeroTrustAPI(APIServerConfig config, ApiService service) throws APIException {
        super(config, service);
    }

    public static ZeroTrustAPI fromDefault(String keystorePath, String keystorePassword) throws APIException {

        APIServerConfig config = new APIServerConfig(8080, "/api")
                .setServerType(APIServerType.ZERO_TRUST)
                .setServerResponseType(APIServerType.ZERO_TRUST_HYBRID)
                .useDynamicApiKey(true)
                .setMaxSessions(3)
                .setMinutesTillInvalid(5)
                .setResponseServerPath("/response")
                .setSessionResourcePaths("session/init", "session/keys", "session/close")
                .setResponsePort(8181)
                .setRateLimit(60)
                .setApiServerKeystorePath(keystorePath)
                .setResponseServerKeystorePath(keystorePath)
                .setApiServerKeyPassword(keystorePassword)
                .setResponseServerKeyPassword(keystorePassword);

        CORSPolicy corsPolicy = new CORSPolicy(false);
        config.setCorsPolicy(corsPolicy);

        return new ZeroTrustAPI(config, null);
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