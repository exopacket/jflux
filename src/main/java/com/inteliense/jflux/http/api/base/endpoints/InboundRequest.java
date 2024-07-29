package com.inteliense.jflux.http.api.base.endpoints;

import com.inteliense.jflux.files.PathVariable;
import com.inteliense.jflux.http.api.server.containers.ClientSession;
import com.inteliense.jflux.http.api.server.containers.Parameters;
import com.inteliense.jflux.http.api.server.containers.RequestHeaders;
import com.sun.net.httpserver.HttpExchange;

import java.util.HashMap;

public class InboundRequest {

    ClientSession session;
    Parameters params;
    RequestHeaders headers;
    HashMap<String, String> pathVariables = new HashMap<>();

    public InboundRequest(ClientSession session, Parameters params, RequestHeaders headers, String resourcePath, HttpExchange t) {

    }

    public ClientSession getSession() {
        return session;
    }

    public Parameters getParams() {
        return params;
    }

    public RequestHeaders getHeaders() {
        return headers;
    }

    public HashMap<String, String> getPathVariables() {
        return pathVariables;
    }

}
