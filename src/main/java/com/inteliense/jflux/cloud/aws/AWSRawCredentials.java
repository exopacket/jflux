package com.inteliense.jflux.cloud.aws;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

public class AWSRawCredentials {

    private String keyId;
    private String accessKey;
    private Regions region;

    public AWSRawCredentials(String keyId, String accessKey, Regions region) {
        this.keyId = keyId;
        this.accessKey = accessKey;
        this.region = region;
    }

    public String getKeyId() {
        return keyId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public Region getRegion() {
        return Region.getRegion(this.region);
    }
}
