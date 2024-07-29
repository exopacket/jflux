package com.inteliense.jflux.http.api.base.endpoints;

import com.inteliense.jflux.crypto.builtin.SHA;
import com.inteliense.jflux.encoding.BaseX;
import com.inteliense.jflux.encoding.Hex;

import java.util.HashMap;

public class AppEndpointCollection {

    private HashMap<Integer, AppEndpointCollection> endpoints = new HashMap<>();
    private HashMap<String, Integer> keysMap = new HashMap<>();

    public AppEndpointCollection() { }

    public AppEndpointCollection getCollectionByIndex(int index) {
        return endpoints.get(index);
    }

    public ApiResource getEndpoint(String path) {
        return null;
    }

    public void putEndpoint(String basePath, ApiResource endpoint) {
        String key = BaseX.encode64(Hex.fromHex(SHA.get512(basePath)));
        if(!keysMap.containsKey(key)) {
            int index = keysMap.size();
            keysMap.put(key, index);
            AppEndpointCollection collection = new AppEndpointCollection();
            collection.putEndpoint(basePath, endpoint);
            endpoints.put(index, collection);
        } else {
            int index = keysMap.get(key);
            AppEndpointCollection collection = endpoints.get(index);
            collection.putEndpoint(basePath, endpoint);
            endpoints.put(index, collection);
        }
    }

    public void putEndpoint(String basePath, String endpointPath, ApiResource endpoint) {
        String key = BaseX.encode64(Hex.fromHex(SHA.get512(basePath)));
        if(!keysMap.containsKey(key)) {
            int index = keysMap.size();
            keysMap.put(key, index);
            AppEndpointCollection collection = new AppEndpointCollection();
            collection.putEndpoint(basePath, endpointPath, endpoint);
            endpoints.put(index, collection);
        } else {
            int index = keysMap.get(key);
            AppEndpointCollection collection = endpoints.get(index);
            collection.putEndpoint(basePath, endpointPath, endpoint);
            endpoints.put(index, collection);
        }
    }

}
