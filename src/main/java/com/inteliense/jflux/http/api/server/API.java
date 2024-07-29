package com.inteliense.jflux.http.api.server;

import com.inteliense.jflux.http.api.base.prereqs.ApiService;
import com.inteliense.jflux.http.api.server.base.APIServer;
import com.inteliense.jflux.http.api.server.config.APIServerConfig;
import com.inteliense.jflux.http.api.server.containers.ClientSession;
import com.inteliense.jflux.http.api.server.containers.Parameters;
import com.inteliense.jflux.http.api.server.containers.RequestHeaders;
import com.inteliense.jflux.http.api.server.encryption.APIKeyPair;
import com.inteliense.jflux.http.api.server.exceptions.APIException;
import com.inteliense.jflux.http.api.server.impl.APIMethods;
import com.inteliense.jflux.http.api.server.resources.APIResource;
import com.inteliense.jflux.http.api.server.types.APIServerType;
import com.inteliense.jflux.http.api.server.types.ContentType;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class API implements APIMethods {

    private APIServer server;
    private APIServerConfig serverConfig;
    private ApiService service;

    //TODO move blacklist and ratelimited clients to service


    public API(APIServerConfig config, ApiService service) throws APIException {
        this.serverConfig = config;
    }

    public void start() throws APIException {

        server = new APIServer(serverConfig) {

            @Override
            public boolean isPastRateLimit(ClientSession clientSession, int perMinute) {
                return API.this.inTimeout(clientSession, perMinute);
            }

            @Override
            public boolean inBlacklist(ClientSession clientSession) {
                return API.this.inBlacklist(clientSession);
            }

            @Override
            public boolean isAuthenticated(RequestHeaders headers, APIResource resource, Parameters params, ClientSession clientSession) {
                return API.this.isAuthenticated(headers, resource, params, clientSession);
            }

            @Override
            public boolean lookupUserInfo(ClientSession clientSession) {
                return API.this.lookupUserInfo(clientSession);
            }

            @Override
            public APIKeyPair lookupApiKeys(String apiKey) {
                return API.this.lookupApiKey(apiKey);
            }

            @Override
            public APIKeyPair lookupApiKeys(String username, String password) {
                return null;
            }


        };

    }

    public APIResource addResource(String value, String requestMethod, APIResource definition) {

        return server.addResource(value, requestMethod, definition);

    }

    public APIResource addResource(String value, String requestMethod, String[] parameters, APIResource definition) {
        return server.addResource(value, requestMethod, parameters, definition);
    }

    public APIResource addResource(String value, String requestMethod, ArrayList<String> parameters, APIResource definition) {
        return server.addResource(value, requestMethod, parameters, definition);
    }

    public APIResource addResource(String value, String requestMethod, boolean isAsync, ArrayList<String> parameters, APIResource definition) {

        if(serverConfig.getServerResponseType() == APIServerType.ZERO_TRUST_SYNC
                || serverConfig.getServerResponseType() == APIServerType.REST_SYNC) {
            return server.addResource(value, requestMethod, parameters, definition);
        }

        return server.addResource(value, requestMethod, isAsync, parameters, definition);
    }

    public APIResource addResource(String value, String requestMethod, boolean isAsync, APIResource definition) {

        if(serverConfig.getServerResponseType() == APIServerType.ZERO_TRUST_SYNC
                || serverConfig.getServerResponseType() == APIServerType.REST_SYNC) {
            return server.addResource(value, requestMethod, definition);
        }

        return server.addResource(value, requestMethod, isAsync, definition);

    }

    public APIResource addResource(String value, String requestMethod, boolean isAsync, String[] parameters, APIResource definition) {

        if(serverConfig.getServerResponseType() == APIServerType.ZERO_TRUST_SYNC
                || serverConfig.getServerResponseType() == APIServerType.REST_SYNC) {
            return server.addResource(value, requestMethod, parameters, definition);
        }

        return server.addResource(value, requestMethod, isAsync, parameters, definition);

    }

    public boolean isAuthenticated(RequestHeaders headers, APIResource resource, Parameters params, ClientSession clientSession) {

        if(headers.contains("X-Api-Session-Id")) {

            String apiKey = headers.getString("X-Api-Key");
            String sessionId = headers.getString("X-Api-Session-Id");
            String keySetId = headers.getString("X-Api-Key-Set-Id");
            //String userId = headers.getString("X-Api-User-Id");
            //String clientId = headers.getString("X-Api-Client-Id");
            String sessionAuth = headers.getString("X-Api-Session-Authorization");

            boolean flag = false;

            if(!apiKey.equals(clientSession.getClient().getApiKey()))
                flag = true;

            if(!sessionId.equals(clientSession.getSession().getSessionId()))
                flag = true;

            if(!keySetId.equals(clientSession.getSession().getKeySetId()))
                flag = true;
//
//            if(!userId.equals(clientSession.getSession().getUserId()))
//                flag = true;
//
//            if(!clientId.equals(clientSession.getSession().getClientId()))
//                flag = true;

            if(!clientSession.getSession().checkDynamicSessionAuth(sessionAuth))
                flag = true;

            if(flag) {
                APIServer server = clientSession.getClient().getServer();
                server.invalidateSession(clientSession);
            }

            return !flag;

        } else {

            String apiKey = headers.getString("X-Api-Key");

            if(!apiKey.equals(clientSession.getClient().getApiKey()))
                return false;

            return true;

        }

    }

    public boolean lookupUserInfo(ClientSession clientSession) {

        //Returns true to indicate user info was found.
        //Default value when not implemented.
        return true;

    }

    public APIServer getServer() {
        return server;
    }

    public APIServerConfig getServerConfig() {
        return serverConfig;
    }
    public ArrayList<ClientSession> getClientSessions() {
        return server.getClientSessions();
    }



}
