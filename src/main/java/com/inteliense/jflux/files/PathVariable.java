package com.inteliense.jflux.files;

public class PathVariable {

    private String key;
    private String value = null;

    public PathVariable(String key) {
        this.key = key;
    }

    public PathVariable(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public void set(String value) {
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return value;
    }

    public boolean isset() {
        return this.value != null;
    }

}
