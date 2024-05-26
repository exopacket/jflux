package com.inteliense.jflux.files;

import com.inteliense.jflux.exceptions.types.CommonIOException;

import java.io.File;
import java.io.FileInputStream;

public class FileObject {

    private PathObject path;
    private File file = null;
    private byte[] data = new byte[0];

    public FileObject(PathObject path) throws CommonIOException {
        this.path = path;
        this.file = getNativeFile();
    }

    public void readToEnd() {
        try {
            FileInputStream is = new FileInputStream(file);
            this.data = is.readAllBytes();
        } catch (Exception ignored) { }
    }

    public int size() {
        return data.length;
    }

    public byte[] getBytes(int length) {
        byte[] arr = new byte[length];
        System.arraycopy(data, 0, arr, 0, length);
        return arr;
    }

    public byte[] getBytes(int start, int length) {
        byte[] arr = new byte[length];
        int limit = start + length;
        int x = 0;
        for(int i=start; i<limit; i++) {
            arr[x] = data[i];
            x++;
        }
        return arr;
    }

    public String[] getLines() {
        String str = new String(data);
        return str.split("\\n");
    }

    public void overwrite(byte[] data) {

    }

    public void append(byte[] data) {

    }

    private File getNativeFile() throws CommonIOException {
        if(path.exists()) return new File(path.getUri());
        else {
            File file = new File(path.getUri());
            try {
                file.createNewFile();
                return file;
            } catch (Exception exception) {
                e(ExceptionType.NOT_EXIST);
            }
        }
        return null;
    }

    private void e(ExceptionType type) throws CommonIOException {
        switch(type) {
            case NOT_EXIST: throw new CommonIOException("The system path '" + path.getString() + "' does not exist.");
            case NOT_WRITABLE: throw new CommonIOException("The system path '" + path.getString() + "' is not writable.");
            case NOT_EXECUTABLE: throw new CommonIOException("The system path '" + path.getString() + "' is not executable.");
            case NOT_READABLE: throw new CommonIOException("The system path '" + path.getString() + "' is not readable");
            case NOT_FILE: throw new CommonIOException("The system path '" + path.getString() + "' is not a file");
        }

    }

    private enum ExceptionType {
        NOT_EXIST,
        NOT_WRITABLE,
        NOT_EXECUTABLE,
        NOT_READABLE,
        NOT_FILE,
    }

}
