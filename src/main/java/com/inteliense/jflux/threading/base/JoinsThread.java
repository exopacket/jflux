package com.inteliense.jflux.threading.base;

public abstract class JoinsThread extends ThreadImpl {
    @Override
    public boolean doesJoin() { return true; }
}
