package com.inteliense.jflux.http.api.base.endpoints;

import com.inteliense.jflux.files.PathVariable;
import com.inteliense.jflux.http.api.base.endpoints.expectations.ApiExpectations;
import com.inteliense.jflux.http.api.base.endpoints.permissions.ApiEndpointPermissions;
import com.inteliense.jflux.http.api.base.prereqs.ApiBase;
import com.inteliense.jflux.http.api.server.containers.APIResponse;
import com.inteliense.jflux.http.api.server.containers.ClientSession;
import com.inteliense.jflux.http.api.server.containers.Parameters;
import com.inteliense.jflux.http.api.server.containers.RequestHeaders;
import com.inteliense.jflux.http.api.server.resources.APIResource;

public abstract class ApiResource {

    private int index;
    private String path;
    private EndpointType type;
    private ApiEndpointAccepts endpointAccepts;
    private ApiExpectations expectations;
    private ApiEndpointPermissions permissions;

    public ApiResource(int index, String path, EndpointType type, ApiBase api) {
        this.index = index;
        this.path = path;
        this.type = type;
        this.endpointAccepts = endpointAccepts();
        this.expectations = expectations();
        this.permissions = permissions();
    }

    public ApiResource(int index, String path, ApiBase api) {
        this.index = index;
        this.path = path;
        this.type = EndpointType.RESOURCE;
        this.endpointAccepts = endpointAccepts();
        this.expectations = expectations();
        this.permissions = permissions();
    }

    protected abstract ApiEndpointAccepts endpointAccepts();
    protected abstract ApiExpectations expectations();
    protected abstract ApiEndpointPermissions permissions();

    public void __index(ApiBase base) {
        new APIResource("GET") {
            @Override
            public APIResponse execute(InboundRequest request) throws Exception {
                return null;
            }
        };
    }

    public void __update(ApiBase base) {
        new APIResource("PUT") {
            @Override
            public APIResponse execute(InboundRequest request) throws Exception {
                return null;
            }
        };
    }

    public void __create(ApiBase base) {
        new APIResource("POST") {
            @Override
            public APIResponse execute(InboundRequest request) throws Exception {
                return null;
            }
        };
    }

    public void __delete(ApiBase base) {
        new APIResource("DELETE") {
            @Override
            public APIResponse execute(InboundRequest request) throws Exception {
                return null;
            }
        };
    }

    public abstract APIResponse index(InboundRequest request);
    public abstract APIResponse update(InboundRequest request);
    public abstract APIResponse create(InboundRequest request);
    public abstract APIResponse delete(InboundRequest request);

    public enum EndpointType {
        RESOURCE,
        ENDPOINT
    }

}
