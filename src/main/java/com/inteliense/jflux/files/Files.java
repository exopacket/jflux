package com.inteliense.jflux.files;

import com.inteliense.jflux.exceptions.types.CommonIOException;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class Files {

    public static FileObject create(PathObject path, String filename, String content) throws CommonIOException {
        FileObject file = new FileObject(path, filename);
        file.overwrite(content.getBytes(StandardCharsets.UTF_8));
        return file;
    }

    public static FileObject create(PathObject path, String filename, String content, boolean overwrite) {
        return null;
    }

    public static FileObject overwrite(PathObject path, String filename, String content) {
        return null;
    }

    public static FileObject duplicate(FileObject file, PathObject path) {
        return null;
    }

    public static void delete(PathObject path, String filename) {

    }

}
