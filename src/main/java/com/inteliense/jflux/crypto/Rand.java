package com.inteliense.jflux.crypto;

import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

public class Rand {

    public static byte[] secure(int len) {

        byte[] bytes = new byte[len];
        new SecureRandom().nextBytes(bytes);
        return bytes;

    }

    public static byte[] secure(int len, String seed) {

        byte[] bytes = new byte[len];
        SecureRandom rand = new SecureRandom();
        rand.setSeed(seed.getBytes(StandardCharsets.UTF_8));
        rand.nextBytes(bytes);
        return bytes;

    }

    public static String letter(String seed) {
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int s = ((int) seed.charAt(0)) + ((int) seed.charAt(seed.length() - 1));
        Random r = new Random(s);
        int i = r.nextInt(52);
        return String.valueOf(letters.charAt(i));
}

    public static String str(int len) {

        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = len;

        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;

    }

    public static byte[] generateKey(int bits) {

        byte[] key = new byte[bits / 8];
        new SecureRandom().nextBytes(key);
        return key;

    }

    public static byte[] generateIv(int bits) {

        byte[] iv = new byte[bits / 8];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        return ivParameterSpec.getIV();

    }

    public static String randomCase(String input) {
        input = input.toLowerCase();
        StringBuilder ret = new StringBuilder();
        for(int i=0; i<input.length(); i++) {
            byte res = secure(1)[0];
            int bool = res % 2;
            boolean capitalize = bool == 1;
            String c = input.substring(i, i + 1);
            if(capitalize) c = c.toUpperCase();
            ret.append(c);
        }
        return ret.toString();
    }

}
