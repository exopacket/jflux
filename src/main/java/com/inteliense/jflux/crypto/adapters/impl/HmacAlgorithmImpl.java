package com.inteliense.jflux.crypto.adapters.impl;

public abstract class HmacAlgorithmImpl {
    public abstract byte[] hash(byte[] input, byte[] key);
}
