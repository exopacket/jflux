package com.inteliense.jflux.crypto;

import com.inteliense.jflux.crypto.adapters.AsymmetricAlgorithm;
import com.inteliense.jflux.crypto.adapters.HashAlgorithm;
import com.inteliense.jflux.crypto.adapters.HmacAlgorithm;
import com.inteliense.jflux.crypto.adapters.SymmetricAlgorithm;

import java.util.HashMap;

public class CryptographyRegister {

    private HashMap<String, SymmetricAlgorithm> symmetricMap;
    private HashMap<String, AsymmetricAlgorithm> asymmetricMap;
    private HashMap<String, HashAlgorithm> hashMap;
    private HashMap<String, HmacAlgorithm> hmacMap;
    //TODO random number map

    public CryptographyRegister() { }

    public static CryptographyRegister fromDefaults() {
        CryptographyRegister register = new CryptographyRegister();
        register.register("aes-cbc-256", DefaultAlgorithms.aesCbc256());
        register.register("aes-cbc-128", DefaultAlgorithms.aesCbc128());
        register.register("aes-gcm-256", DefaultAlgorithms.aesGcm256());
        register.register("aes-gcm-128", DefaultAlgorithms.aesGcm128());
        register.register("aes-ecb-256", DefaultAlgorithms.aesEcb256());
        register.register("aes-ecb-128", DefaultAlgorithms.aesEcb128());
        register.register("sha3-512", DefaultAlgorithms.sha512());
        register.register("sha3-384", DefaultAlgorithms.sha384());
        register.register("sha3-256", DefaultAlgorithms.sha256());
        register.register("sha1", DefaultAlgorithms.sha1());
        register.register("hmac-sha3-512", DefaultAlgorithms.hmac512());
        register.register("hmac-sha3-256", DefaultAlgorithms.hmac384());
        register.register("hmac-sha3-128", DefaultAlgorithms.hmac256());
        register.register("rsa-4096", DefaultAlgorithms.rsa());
        return register;
    }

    public void register(String name, SymmetricAlgorithm algo) {
        symmetricMap.put(name, algo);
    }

    public void register(String name, AsymmetricAlgorithm algo) {
        asymmetricMap.put(name, algo);
    }

    public void register(String name, HashAlgorithm algo) {
        hashMap.put(name, algo);
    }

    public void register(String name, HmacAlgorithm algo) {
        hmacMap.put(name, algo);
    }

    public byte[] encrypt(String name, byte[] input, byte[] key, byte[]...aad) {
        for(String algo : symmetricMap.keySet()) {
            if(name.equals(algo)) {
                return symmetricMap.get(algo).encrypt(input, key, aad);
            }
        }
        for(String algo : asymmetricMap.keySet()) {
            if(name.equals(algo)) {
                return asymmetricMap.get(algo).encrypt(input, key, aad);
            }
        }
        return new byte[0];
    }

    public byte[] decrypt(String name, byte[] input, byte[] key, byte[]...aad) {
        for(String algo : symmetricMap.keySet()) {
            if(name.equals(algo)) {
                return symmetricMap.get(algo).decrypt(input, key, aad);
            }
        }
        for(String algo : asymmetricMap.keySet()) {
            if(name.equals(algo)) {
                return asymmetricMap.get(algo).decrypt(input, key, aad);
            }
        }
        return new byte[0];
    }

    public byte[] hash(String name, byte[] input) {
        for(String algo : hashMap.keySet()) {
            if(name.equals(algo)) {
                return hashMap.get(algo).hash(input);
            }
        }
        return new byte[0];
    }

    public byte[] hmac(String name, byte[] input, byte[] key) {
        for(String algo : hmacMap.keySet()) {
            if(name.equals(algo)) {
                return hmacMap.get(algo).hash(input, key);
            }
        }
        return new byte[0];
    }

}
