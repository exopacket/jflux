package com.inteliense.jflux.http.api.server.impl;


import com.inteliense.zeta.server.containers.APIResponse;
import com.inteliense.zeta.server.containers.ClientSession;
import com.inteliense.zeta.server.containers.Parameters;
import com.inteliense.zeta.server.containers.RequestHeaders;

public interface APIExecute {
    APIResponse execute(ClientSession clientSession, Parameters params, RequestHeaders headers) throws Exception;

}
