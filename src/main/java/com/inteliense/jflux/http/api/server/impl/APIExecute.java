package com.inteliense.jflux.http.api.server.impl;


import com.inteliense.jflux.http.api.server.containers.APIResponse;
import com.inteliense.jflux.http.api.server.containers.ClientSession;
import com.inteliense.jflux.http.api.server.containers.Parameters;
import com.inteliense.jflux.http.api.server.containers.RequestHeaders;

public interface APIExecute {
    APIResponse execute(ClientSession clientSession, Parameters params, RequestHeaders headers) throws Exception;

}
