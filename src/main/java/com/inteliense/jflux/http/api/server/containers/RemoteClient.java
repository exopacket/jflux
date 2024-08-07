package com.inteliense.jflux.http.api.server.containers;

import com.inteliense.jflux.http.api.server.encryption.APIKeyPair;
import com.inteliense.jflux.http.api.server.base.APIServer;
import com.inteliense.jflux.http.api.server.exceptions.APIException;
import com.inteliense.jflux.http.api.server.resources.APIResource;

public abstract class RemoteClient {

    private APIServer server;
    private APIKeyPair apiKeys;
    private ClientInfo clientInfo;

    public static final RemoteClient NONE = null;

    public RemoteClient(APIKeyPair apiKeys, APIServer server) throws APIException {
        this.server = server;
        this.apiKeys = apiKeys;
    }

    public String getApiKey() {
        return apiKeys.getKey();
    }
    public String getApiSecret() { return apiKeys.getSecret(); }

    public APIServer getServer() {
        return this.server;
    }
    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    public boolean equals(APIKeyPair apiKeys) {
        return getApiKey().equals(apiKeys.getKey());
    }

    public boolean guessSame(RemoteClient client) {

        String otherKey = client.getApiKey();
        ClientInfo info = client.getClientInfo();
        if(!this.apiKeys.getKey().equals(otherKey)) return false;
        if(!info.getRemoteIp().equals(this.clientInfo.getRemoteIp())) return false;

        return true;

    }

    public boolean isFlagged(RequestHeaders headers, String hostname) {
        if(!hostname.equals(""))
            clientInfo.verifyHostname(hostname);
        if(headers.contains("User-Agent"))
            clientInfo.verifyUserAgent(headers.getString("User-Agent"));
        return clientInfo.isFlagged();
    }

    public void newRequest() {
        clientInfo.incrementIpRequests();
    }

    public abstract boolean isLimited(int perMinute);

    public abstract boolean inBlacklist();

    public abstract boolean isAuthenticated(RequestHeaders headers, APIResource resource, Parameters params);

    public abstract boolean lookupUserInfo();



}
