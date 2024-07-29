package com.inteliense.jflux.files;

import com.inteliense.jflux.exceptions.types.CommonIOException;
import com.inteliense.jflux.regex.RegexUtil;
import com.inteliense.jflux.sys.PlatformUtils;
import com.inteliense.jflux.todash.__;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PathObject {

    private String value;
    private ArrayList<PathVariable> variables = new ArrayList<>();

    public PathObject(String value) {
        this.value = PathUtil.fixDirectoryPath(value);
        this.variables = parseVariables(value);
    }

    public PathObject(String value, ArrayList<PathVariable> variables) {
        this.value = PathUtil.fixDirectoryPath(value);
        this.variables = variables;
    }

    public PathObject(String value, ArrayList<PathVariable> variables, String...append) {
        this.value = PathUtil.fixDirectoryPath(value).substring(0, value.length() - 1 );
        this.variables = variables;
        for(String part : append) {
            this.value += PlatformUtils.fileSeparator() + part;
        }
    }

    private static ArrayList<PathVariable> parseVariables(String value) {
        String[] keys = RegexUtil.getPathVariables(value);
        ArrayList<PathVariable> list = new ArrayList<>();
        for(String key : keys) list.add(new PathVariable(key));
        return list;
    }

    public PathObject setVariable(String key, String value) {
        for(PathVariable var : variables) {
            if(__.same(var.getKey(), key)) {
                var.set(value);
                break;
            }
        }
        return this;
    }

    public String getString() {
        String str = this.value;
        if(!variables.isEmpty()) {
            for(PathVariable var : variables) {
                str = str.replace("{" + var.getKey() + "}", var.getValue());
            }
        }
        return str;
    }

    public URI getUri() {
        return URI.create(getString());
    }

    public void mkdirs() {
        String str = getString();
        File file = new File(str);
        file.mkdirs();
    }

    public PathObject clone() {
        return new PathObject(this.value, this.variables);
    }

    public PathObject clone(String...append) {
        return new PathObject(this.value, this.variables, append);
    }

    public PathObject cloneAndCreate(String...append) {
        PathObject path = new PathObject(this.value, this.variables, append);
        path.mkdirs();
        return path;
    }

}
