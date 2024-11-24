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

public abstract class ApiEndpoint extends ApiResource {

    public ApiEndpoint(int index, String path, ApiBase api) {
        super(index, path, EndpointType.ENDPOINT, api);
    }

    @Override
    public APIResponse index(InboundRequest request) {
        return get(request);
    }

    @Override
    public APIResponse update(InboundRequest request) {
        return put(request);
    }

    @Override
    public APIResponse create(InboundRequest request) {
        return post(request);
    }

    public abstract APIResponse get(InboundRequest request);
    public abstract APIResponse put(InboundRequest request);
    public abstract APIResponse post(InboundRequest request);

}
