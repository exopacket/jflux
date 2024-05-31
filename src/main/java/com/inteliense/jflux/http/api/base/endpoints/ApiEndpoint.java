package com.inteliense.jflux.http.api.base.endpoints;

import com.inteliense.jflux.http.api.base.endpoints.expectations.ApiExpectations;
import com.inteliense.jflux.http.api.base.endpoints.permissions.ApiEndpointPermissions;
import com.inteliense.jflux.http.api.server.types.RequestType;

public abstract class ApiEndpoint {

    private int index;
    private ApiEndpointAccepts endpointAccepts;
    private ApiExpectations[] expectations;
    private ApiEndpoint[] children = null;

    public ApiEndpoint(int index, ApiEndpoint...children) {
        this.index = index;
        this.endpointAccepts = new ApiEndpointAccepts(RequestType.OPTIONS, RequestType.GET, RequestType.POST, RequestType.PUT, RequestType.DELETE);
        this.expectations = expectations();
        if(children.length > 0) this.children = children;
    }

    protected abstract ApiEndpointAccepts endpointAccepts(ApiEndpointAccepts base);
    protected abstract ApiExpectations[] expectations();

    protected ApiEndpointPermissions permissions() {
        return null;
    }

}
