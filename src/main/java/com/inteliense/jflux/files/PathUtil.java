package com.inteliense.jflux.files;

import com.inteliense.jflux.sys.PlatformUtils;

import java.nio.file.Paths;

public class PathUtil {

    public static PathObject cwd() {
        String cwd = Paths.get(".").toAbsolutePath().normalize().toString();
        return fromString(cwd);
    }

    public static PathObject fromString(String path) {
        return new PathObject(path);
    }

    public static PathObject fromString(String path, boolean mkdirs) {
        PathObject p = new PathObject(path);
        if(mkdirs) p.mkdirs();
        return p;
    }

    public static String fixDirectoryPath(String dir) {
        String last = dir.substring(dir.length() - 1);
        if(last.equals(PlatformUtils.fileSeparator())) return dir;
        dir += PlatformUtils.fileSeparator();
        return dir.replaceAll("[/\\\\]+", PlatformUtils.fileSeparator());
    }

    public static PathVariable createVar(String key, String value) {
        return new PathVariable(key, value);
    }

}
