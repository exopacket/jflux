package com.inteliense.jflux.http.api.server.encryption;

<<<<<<< HEAD
import com.inteliense.jflux.crypto.Rand;
import com.inteliense.jflux.crypto.builtin.AES;
import com.inteliense.jflux.crypto.builtin.SHA;
import com.inteliense.jflux.encoding.Hex;
=======
import com.inteliense.jflux.http.api.utils.AES;
import com.inteliense.jflux.http.api.utils.EncodingUtils;
import com.inteliense.jflux.http.api.utils.Random;
import com.inteliense.jflux.http.api.utils.SHA;
>>>>>>> 50fdb4c525291b03960283dca73966876f808659

public class APIKeyPair {

    private String key;
    private String secret;
    private String sentSecret;
    private String outboundSecret;
    private String outboundSigningKey;
    private String outboundIv;
    private byte[] random;

    private long clientTimestamp;

    public APIKeyPair(String key, String secret) {
        this.key = key;
        this.secret = secret;
    }

    public static APIKeyPair generateNewPair() {
        return new APIKeyPair(
                Rand.str(72, "apikey"),
                Rand.str(56, "secret")
        );
    }

    public String getOutboundIv() {
        return outboundIv;
    }

    public String getOutboundSigningKey() {
        return outboundSigningKey;
    }

    public void responseSent() {
        this.sentSecret = this.outboundSecret;
        this.outboundSecret = "";
    }

    public String getClientTimestamp() {
        return String.valueOf(clientTimestamp);
    }

    public boolean initialInbound(String received, String receivedRand) {

        byte[][] splitRes = getInitialClientRand(Hex.fromHex(receivedRand));
        byte[] receivedCipherText = Hex.fromHex(received);
        byte[] receivedKey = splitRes[0];
        byte[] receivedIv = splitRes[1];
        this.random = receivedKey;
        byte[] calculatedHash = Hex.fromHex(SHA.getHmac512(secret, key));
        byte[] actualCipherText = subtractBytes(receivedCipherText, calculatedHash);
        byte[] decryptedSecretKeyBytes = AES.cbc(actualCipherText, random, receivedIv, false);
        String decryptedSecretKey = new String(decryptedSecretKeyBytes);
        if(!this.secret.equals(decryptedSecretKey)) return false;
        String nextHash = SHA.getHmac512(decryptedSecretKey, key);
        String serverSecretKey = Rand.str(56, "secret");
        byte[] newIv = Rand.generateIv(128);
        byte[] nextSecretCipherText = AES.cbc(serverSecretKey.getBytes(), random, newIv, true);
        byte[] nextHashBytes = Hex.fromHex(nextHash);
        byte[] signingKey = addBytes(nextSecretCipherText, nextHashBytes);
        this.secret = decryptedSecretKey;
        this.outboundSigningKey = Hex.getHex(signingKey);
        this.outboundIv = Hex.getHex(newIv);
        this.outboundSecret = serverSecretKey;

        return true;

    }

    public boolean inbound(String received, String iv) {

        byte[] receivedCipherText = Hex.fromHex(received);
        byte[] receivedIv = Hex.fromHex(iv);
        byte[] calculatedHash = Hex.fromHex(SHA.getHmac512(outboundSecret, key));
        byte[] actualCipherText = subtractBytes(receivedCipherText, calculatedHash);
        byte[] decryptedSecretKeyBytes = AES.cbc(actualCipherText, random, receivedIv, false);
        String decryptedSecretKey = new String(decryptedSecretKeyBytes);
        String secretKeyPrefix = (decryptedSecretKey.length() > 1) ? decryptedSecretKey.substring(0, 7) : "";
        if(!secretKeyPrefix.equals("secret_")) return false;
        String nextHash = SHA.getHmac512(decryptedSecretKey, key);
        String serverSecretKey = Rand.str(56, "secret");
        byte[] newIv = Rand.generateIv(128);
        byte[] nextSecretCipherText = AES.cbc(serverSecretKey.getBytes(), random, newIv, true);
        byte[] nextHashBytes = Hex.fromHex(nextHash);
        byte[] signingKey = addBytes(nextSecretCipherText, nextHashBytes);
        this.secret = decryptedSecretKey;
        this.outboundSigningKey = Hex.getHex(signingKey);
        this.outboundIv = Hex.getHex(newIv);
        this.outboundSecret = serverSecretKey;

        return true;

    }

    private byte[][] getInitialClientRand(byte[] input) {

        if(input.length == 48) {

            byte[] keyArr = new byte[32];
            byte[] ivArr = new byte[16];

            int keyIndex = 0;
            int ivIndex = 0;
            for (int i = 0; i < 48; i++) {

                if(i < 32) {
                    keyArr[keyIndex] = input[i];
                    keyIndex++;
                } else {
                    ivArr[ivIndex] = input[i];
                    ivIndex++;
                }

            }

            return new byte[][] {
              keyArr,
              ivArr
            };

        }

        return null;

    }

    private byte[] addBytes(byte[] arr1, byte[] arr2) {

        if(arr1.length == arr2.length) {

            byte[] outArr = new byte[arr1.length];

            for(int i=0; i<outArr.length; i++) {
                outArr[i] = (byte) (arr1[i] + arr2[i]);
            }

            return outArr;

        }

        return null;

    }

    private byte[] subtractBytes(byte[] inputArr, byte[] byArr) {

        if(inputArr.length == byArr.length) {

            byte[] outArr = new byte[inputArr.length];

            for(int i=0; i<outArr.length; i++) {
                outArr[i] = (byte) (inputArr[i] - byArr[i]);
            }

            return outArr;

        }

        return null;

    }

    public String getKey() {
        return key;
    }

    public String getSecret() {
        return secret;
    }

}
