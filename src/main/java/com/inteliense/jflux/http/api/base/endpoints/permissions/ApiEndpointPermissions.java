package com.inteliense.jflux.http.api.base.endpoints.permissions;

public class ApiEndpointPermissions {

    public String permissionKey;

    public ApiEndpointPermissions(String permissionName) {

    }

    public void addPermittedKey(String table, String id) {

    }

    public boolean isPermitted(String id) {
        return false;
    }

}
