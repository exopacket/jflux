package com.inteliense.jflux.http.api.server.impl;

import com.inteliense.jflux.http.api.server.*;
import com.inteliense.jflux.http.api.server.containers.ClientSession;
import com.inteliense.jflux.http.api.server.containers.Parameters;
import com.inteliense.jflux.http.api.server.containers.RequestHeaders;
import com.inteliense.jflux.http.api.server.encryption.APIKeyPair;
import com.inteliense.jflux.http.api.server.resources.APIResource;
import com.inteliense.jflux.http.api.server.types.ContentType;

import java.util.HashMap;

public interface APIMethods {
    boolean isAuthenticated(RequestHeaders headers, APIResource resource, Parameters params, ClientSession clientSession);
    boolean inTimeout(ClientSession clientSession, int perMinute);
    boolean inBlacklist(ClientSession clientSession);
    APIKeyPair lookupApiKey(String apiKey);
    boolean lookupUserInfo(ClientSession session);
    HashMap<String, String> getParameters(String body, ContentType contentType);
    void addToBlacklist(ClientSession clientSession, API.BlacklistEntryType entryType);
    void removeFromBlacklist(ClientSession clientSession);

}
