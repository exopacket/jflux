package com.inteliense.jflux.crypto.adapters.impl;

public abstract class RandomGeneratorImpl {
    public abstract byte[] generate(int size, byte[]...params);
}
