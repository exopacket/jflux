package com.inteliense.jflux.jarg;

import org.objectweb.asm.Handle;

import java.util.Arrays;

public abstract class Command {

    private String[] args = null;
    private Class cmdClass = null;

    public Command(String[] args, Keywords keywords) {
        System.out.println(args[0]);
        cmdClass = keywords.getClass(args[0]);
    }

    public Class<?> getCommandClass() {
        return this.cmdClass;
    }

    public String[] getArgs() {
        return this.args;
    }

    public abstract void exit(String message, int code);

}