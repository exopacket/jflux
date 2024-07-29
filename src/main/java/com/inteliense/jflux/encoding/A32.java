package com.inteliense.jflux.encoding;

import java.util.zip.Adler32;

public class A32 {

    public static String get(String input) {
        Adler32 a32 = new Adler32();
        byte[] bites = input.getBytes();
        a32.update(bites, 0, bites.length);
        long v = a32.getValue();
        return Long.toHexString(v);
    }

}
