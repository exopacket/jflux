package com.inteliense.jflux.crypto;

import com.inteliense.jflux.crypto.adapters.AsymmetricAlgorithm;
import com.inteliense.jflux.crypto.adapters.HashAlgorithm;
import com.inteliense.jflux.crypto.adapters.HmacAlgorithm;
import com.inteliense.jflux.crypto.adapters.SymmetricAlgorithm;
import com.inteliense.jflux.crypto.builtin.AES;
import com.inteliense.jflux.crypto.builtin.RSA;
import com.inteliense.jflux.crypto.builtin.SHA;
import com.inteliense.jflux.encoding.BaseX;
import com.inteliense.jflux.encoding.Hex;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;

public class DefaultAlgorithms {

    public static SymmetricAlgorithm aesCbc256() {
        return new SymmetricAlgorithm("aes-cbc-256", 16, 32) {
            @Override
            public byte[] encrypt(byte[] input, byte[] key, byte[]... aad) {
                byte[] iv = aad[0];
                if(aad.length > 0) iv = aad[0];
                return AES.cbc(input, key, iv, true);
            }

            @Override
            public byte[] decrypt(byte[] ciphertext, byte[] key, byte[]... aad) {
                byte[] iv = aad[0];
                if(aad.length > 0) iv = aad[0];
                return AES.cbc(ciphertext, key, iv, false);
            }
        };
    }

    public static SymmetricAlgorithm aesCbc128() {
        return new SymmetricAlgorithm("aes-cbc-128", 16, 16) {
            @Override
            public byte[] encrypt(byte[] input, byte[] key, byte[]... aad) {
                byte[] iv = new byte[16];
                if(aad.length > 0) iv = aad[0];
                return AES.cbc(input, key, iv, true);
            }

            @Override
            public byte[] decrypt(byte[] ciphertext, byte[] key, byte[]... aad) {
                byte[] iv = new byte[16];
                if(aad.length > 0) iv = aad[0];
                return AES.cbc(ciphertext, key, iv, false);
            }
        };
    }

    public static SymmetricAlgorithm aesGcm256() {
        return new SymmetricAlgorithm("aes-gcm-256", 16, 32) {
            @Override
            public byte[] encrypt(byte[] input, byte[] key, byte[]... aad) {
                byte[] iv = new byte[16];
                if(aad.length > 0) iv = aad[0];
                byte[] _aad = new byte[0];
                if(aad.length > 1) _aad = aad[1];
                return AES.gcm(input, key, iv, _aad, true);
            }

            @Override
            public byte[] decrypt(byte[] ciphertext, byte[] key, byte[]... aad) {
                byte[] iv = new byte[16];
                if(aad.length > 0) iv = aad[0];
                byte[] _aad = new byte[0];
                if(aad.length > 1) _aad = aad[1];
                return AES.gcm(ciphertext, key, iv, _aad,false);
            }
        };
    }

    public static SymmetricAlgorithm aesGcm128() {
        return new SymmetricAlgorithm("aes-gcm-128", 16, 16) {
            @Override
            public byte[] encrypt(byte[] input, byte[] key, byte[]... aad) {
                byte[] iv = new byte[16];
                if(aad.length > 0) iv = aad[0];
                byte[] _aad = new byte[0];
                if(aad.length > 1) _aad = aad[1];
                return AES.gcm(input, key, iv, _aad, true);
            }

            @Override
            public byte[] decrypt(byte[] ciphertext, byte[] key, byte[]... aad) {
                byte[] iv = new byte[16];
                if(aad.length > 0) iv = aad[0];
                byte[] _aad = new byte[0];
                if(aad.length > 1) _aad = aad[1];
                return AES.gcm(ciphertext, key, iv, _aad,false);
            }
        };
    }

    public static SymmetricAlgorithm aesEcb256() {
        return new SymmetricAlgorithm("aes-ecb-256", 16, 32) {
            @Override
            public byte[] encrypt(byte[] input, byte[] key, byte[]... aad) {
                return AES.ecb(input, key,true);
            }

            @Override
            public byte[] decrypt(byte[] ciphertext, byte[] key, byte[]... aad) {
                return AES.ecb(ciphertext, key, false);
            }
        };
    }

    public static SymmetricAlgorithm aesEcb128() {
        return new SymmetricAlgorithm("aes-ecb-128", 16, 16) {
            @Override
            public byte[] encrypt(byte[] input, byte[] key, byte[]... aad) {
                return AES.ecb(input, key,true);
            }

            @Override
            public byte[] decrypt(byte[] ciphertext, byte[] key, byte[]... aad) {
                return AES.ecb(ciphertext, key, false);
            }
        };
    }

    public static AsymmetricAlgorithm rsa() {
        return new AsymmetricAlgorithm("rsa-4096", 4096) {
            @Override
            public byte[] encrypt(byte[] input, byte[] key, byte[]... aad) {
                String _input = new String(input);
                String _key = new String(key);
                return BaseX.bytesFrom64(RSA.encrypt(_input, _key));
            }

            @Override
            public byte[] decrypt(byte[] ciphertext, byte[] key, byte[]... aad) {
                String _ciphertext = new String(ciphertext);
                String _key = new String(key);
                return RSA.decrypt(_ciphertext, _key).getBytes(StandardCharsets.UTF_8);
            }

            @Override
            public byte[][] generateKeyPair(byte[]... params) {
                KeyPair kp = RSA.generateKeyPair();
                byte[] publicKey = kp.getPublic().getEncoded();
                byte[] privateKey = kp.getPrivate().getEncoded();
                return new byte[][]{publicKey, privateKey};
            }
        };
    }

    public static HashAlgorithm sha512() {
        return new HashAlgorithm("sha3-512", 64) {
            @Override
            public byte[] hash(byte[] input) {
                return Hex.fromHex(SHA.get512(input));
            }
        };
    }

    public static HashAlgorithm sha384() {
        return new HashAlgorithm("sha3-384", 48) {
            @Override
            public byte[] hash(byte[] input) {
                return Hex.fromHex(SHA.get384(input));
            }
        };
    }

    public static HashAlgorithm sha256() {
        return new HashAlgorithm("sha3-256", 32) {
            @Override
            public byte[] hash(byte[] input) {
                return Hex.fromHex(SHA.get256(input));
            }
        };
    }

    public static HashAlgorithm sha1() {
        return new HashAlgorithm("sha1", 20) {
            @Override
            public byte[] hash(byte[] input) {
                return Hex.fromHex(SHA.getSha1(input));
            }
        };
    }

    public static HmacAlgorithm hmac512() {
        return new HmacAlgorithm("hmac-sha3-512", 64, 64) {
            @Override
            public byte[] hash(byte[] input, byte[] key) {
                String _input = new String(input);
                return Hex.fromHex(SHA.getHmac512(_input, key));
            }
        };
    }

    public static HmacAlgorithm hmac384() {
        return new HmacAlgorithm("hmac-sha3-384", 48, 48) {
            @Override
            public byte[] hash(byte[] input, byte[] key) {
                String _input = new String(input);
                return Hex.fromHex(SHA.getHmac512(_input, key));
            }
        };
    }

    public static HmacAlgorithm hmac256() {
        return new HmacAlgorithm("hmac-sha3-256", 32, 32) {
            @Override
            public byte[] hash(byte[] input, byte[] key) {
                String _input = new String(input);
                return Hex.fromHex(SHA.getHmac512(_input, key));
            }
        };
    }

}
