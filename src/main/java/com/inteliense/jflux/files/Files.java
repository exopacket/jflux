package com.inteliense.jflux.files;

public class Files {

    public static FileObject create(PathObject path, String content) {
        return null;
    }

    public static FileObject create(PathObject path, String content, boolean overwrite) {
        return null;
    }

    public static FileObject overwrite(PathObject path, String content) {
        return null;
    }

    public static boolean exists(PathObject path) {
        return path.exists();
    }

    public static FileObject duplicate(FileObject file, PathObject path) {
        return null;
    }

    public static void delete(PathObject path) {

    }

    public static FileObject create(PlatformPath path, String content) {
        return null;
    }

    public static FileObject create(PlatformPath path, String content, boolean overwrite) {
        return null;
    }

    public static FileObject overwrite(PlatformPath path, String content) {
        return null;
    }

    public static boolean exists(PlatformPath path) {
        return false;
    }

    public static FileObject duplicate(FileObject file, PlatformPath path) {
        return null;
    }

    public static void delete(PlatformPath path) {

    }

}
