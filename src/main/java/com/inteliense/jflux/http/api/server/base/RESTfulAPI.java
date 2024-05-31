package com.inteliense.jflux.http.api.server.base;

import com.inteliense.jflux.http.api.base.prereqs.ApiService;
import com.inteliense.jflux.http.api.server.API;
import com.inteliense.jflux.http.api.server.config.APIServerConfig;
import com.inteliense.jflux.http.api.server.encryption.APIKeyPair;
import com.inteliense.jflux.http.api.server.exceptions.APIException;
import com.inteliense.jflux.http.api.server.types.APIServerType;
import com.inteliense.jflux.http.api.server.types.CORSPolicy;

public class RESTfulAPI extends API {

    public RESTfulAPI(APIServerConfig config, ApiService service) throws APIException {
        super(config, service);
    }

    public static RESTfulAPI fromDefault(String keystorePath, String keystorePassword) throws APIException {

        APIServerConfig config = new APIServerConfig(443, "/api")
                .setServerType(APIServerType.REST)
                .setServerResponseType(APIServerType.REST_SYNC)
                .setMaxSessions(10)
                .setMinutesTillInvalid(15)
                .setResponseServerPath("/response")
                .setResponsePort(8080)
                .setRateLimit(60)
                .setApiServerKeystorePath(keystorePath)
                .setResponseServerKeystorePath(keystorePath)
                .setApiServerKeyPassword(keystorePassword)
                .setResponseServerKeyPassword(keystorePassword);

        CORSPolicy corsPolicy = new CORSPolicy(false);
        config.setCorsPolicy(corsPolicy);

        return new RESTfulAPI(config, null);

    }

    public static RESTfulAPI asyncServer(String keystorePath, String keystorePassword, boolean corsPermitted) throws APIException {

        APIServerConfig config = new APIServerConfig(443, "/api")
                .setServerType(APIServerType.REST)
                .setServerResponseType(APIServerType.REST_SYNC)
                .setMaxSessions(10)
                .setMinutesTillInvalid(15)
                .setRateLimit(60)
                .setApiServerKeystorePath(keystorePath)
                .setApiServerKeyPassword(keystorePassword);

        config.setResponseServerPath("/responses")
                    .setResponsePort(8080)
                    .setResponseServerKeystorePath(keystorePath)
                    .setResponseServerKeyPassword(keystorePassword);



        CORSPolicy corsPolicy = new CORSPolicy(corsPermitted);

        if(corsPermitted) {
            String[] headers = new String[]{"Origin", "X-Requested-With",
                    "Content-Type", "Accept",
                    "X-Api-Key", "X-Request-Timestamp", "X-Request-Signature",
            };
            corsPolicy.setHeaders(headers);
        }

        config.setCorsPolicy(corsPolicy);

        return new RESTfulAPI(config, null);

    }

    public static RESTfulAPI withCORS(String keystorePath, String keystorePassword) throws APIException {

        APIServerConfig config = new APIServerConfig(443, "/api")
                .setServerType(APIServerType.REST)
                .setServerResponseType(APIServerType.REST_SYNC)
                .setMaxSessions(10)
                .setMinutesTillInvalid(15)
                .setRateLimit(60)
                .setApiServerKeystorePath(keystorePath)
                .setApiServerKeyPassword(keystorePassword);

        String[] headers = new String[]{"Origin", "X-Requested-With",
                "Content-Type", "Accept",
                "X-Api-Key", "X-Request-Timestamp", "X-Request-Signature",
        };

        CORSPolicy corsPolicy = new CORSPolicy(true);
        corsPolicy.setHeaders(headers);

        config.setCorsPolicy(corsPolicy);

        return new RESTfulAPI(config, null);

    }

    public static RESTfulAPI withCORS(String keystorePath, String keystorePassword, APIServerType responseServerType) throws APIException {

        APIServerConfig config = new APIServerConfig(443, "/api")
                .setServerType(APIServerType.REST)
                .setServerResponseType(APIServerType.REST_SYNC)
                .setMaxSessions(10)
                .setMinutesTillInvalid(15)
                .setRateLimit(60)
                .setApiServerKeystorePath(keystorePath)
                .setApiServerKeyPassword(keystorePassword);

        if(responseServerType == APIServerType.REST_HYBRID || responseServerType == APIServerType.REST_ASYNC) {
            config.setResponseServerPath("/responses")
                    .setResponsePort(8080)
                    .setResponseServerKeystorePath(keystorePath)
                    .setResponseServerKeyPassword(keystorePassword);
        }

        String[] headers = new String[]{"Origin", "X-Requested-With",
                "Content-Type", "Accept",
                "X-Api-Key", "X-Request-Timestamp", "X-Request-Signature",
        };

        CORSPolicy corsPolicy = new CORSPolicy(true);
        corsPolicy.setHeaders(headers);

        config.setCorsPolicy(corsPolicy);

        return new RESTfulAPI(config, null);

    }

    @Override
    public APIKeyPair lookupApiKey(String apiKey) {
        return null;
    }
}