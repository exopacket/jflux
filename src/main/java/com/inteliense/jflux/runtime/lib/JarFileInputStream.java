package com.inteliense.jflux.runtime.lib;

import java.io.*;

public class JarFileInputStream extends InputStream {

    private ByteArrayInputStream bis;
    private byte[] data;

    public JarFileInputStream(String path) throws Exception {

        bis = new ByteArrayInputStream(new byte[]{});
        FileInputStream in = new FileInputStream(path);
        BufferedInputStream bin = new BufferedInputStream(in);
        ByteArrayOutputStream ba = new ByteArrayOutputStream();

        byte[] bite = new byte[1];
        while(bin.read(bite, 0, 1) > 0) {
            ba.write(bite[0]);
            bite = new byte[1];
        }
        bis = new ByteArrayInputStream(ba.toByteArray());
    }

    @Override
    public int read() throws IOException {
        return this.bis.read();
    }

    @Override
    public int read(byte[] toStore) throws IOException {
        return this.bis.read(toStore);
    }

    @Override
    public int read(byte[] toStore, int off, int len) throws IOException {
        return this.bis.read(toStore, off, len);
    }

}
