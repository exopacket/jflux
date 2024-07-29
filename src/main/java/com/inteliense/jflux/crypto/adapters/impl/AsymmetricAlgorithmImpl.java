package com.inteliense.jflux.crypto.adapters.impl;

public abstract class AsymmetricAlgorithmImpl {
    public abstract byte[] encrypt(byte[] input, byte[] key, byte[]...aad);
    public abstract byte[] decrypt(byte[] ciphertext, byte[] key, byte[]...aad);
    public abstract byte[][] generateKeyPair(byte[]...params);
}
