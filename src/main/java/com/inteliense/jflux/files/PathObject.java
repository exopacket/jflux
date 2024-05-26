package com.inteliense.jflux.files;

import com.inteliense.jflux.exceptions.types.CommonIOException;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PathObject {

    private String value;
    private ArrayList<PathVariable> variables;

    public PathObject(String value) {
        this.value = value;
    }

    private static ArrayList<PathVariable> parseVariables(String value) {
        ArrayList<String> keys = new ArrayList<>();
        return null;
    }

    public PathObject setVariable(String key, String value) {
        return this;
    }

    public String getString() {
        return "";
    }

    public URI getUri() {
        return URI.create(getString());
    }

    public boolean exists() {
        Path p = Paths.get(getUri());
        return Files.exists(p);
    }

    public void mkdirs() {

    }

}
