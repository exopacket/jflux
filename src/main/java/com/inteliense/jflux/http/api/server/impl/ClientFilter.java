package com.inteliense.jflux.http.api.server.impl;

import com.inteliense.jflux.http.api.server.resources.APIResource;
import com.inteliense.jflux.http.api.server.containers.ClientSession;
import com.inteliense.jflux.http.api.server.containers.Parameters;
import com.inteliense.jflux.http.api.server.containers.RequestHeaders;

public interface ClientFilter {

    boolean isPastRateLimit(ClientSession clientSession, int perMinute);
    boolean inBlacklist(ClientSession clientSession);
    boolean isAuthenticated(RequestHeaders headers, APIResource resource, Parameters params, ClientSession clientSession);
    boolean lookupUserInfo(ClientSession clientSession);

}
