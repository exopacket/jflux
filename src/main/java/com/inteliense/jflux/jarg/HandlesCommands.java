package com.inteliense.jflux.jarg;


import com.inteliense.jflux.todash.__;

import java.lang.reflect.Constructor;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class HandlesCommands {

    protected Command command;
    private List<Arg> args = new ArrayList<>();
    private ArrayList<Arg> availableArgs = new ArrayList<>();

    public static HandlesCommands create(Command command) {
        try {
            Class<?> _class = command.getCommandClass();
            Constructor<?> construct = _class.getConstructor(Command.class);
            Object __class = construct.newInstance(command);
            return (HandlesCommands) __class;
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public String getName() {
        return null;
    }

    public HandlesCommands(Command command) {
        this.command = command;
        availableArgs(this.availableArgs);
    }

    public void printHelp() {

    }

    protected Arg requiredFlag(String flag) {
        Arg arg = getFlagArg(flag);
        if(arg.isFlag() && hasFlag(flag) && ((arg.requiresValue() && !__.empty(flagValue(flag))) || !arg.requiresValue()))
            return arg;
        command.exit("[--" + flag + "] is required for this command.", 1);
        return null;
    }

    protected Arg orOptionalFlag(String ...flags) {
        for(int i=0; i<flags.length; i++) {
            Arg flag = getFlagArg(flags[i]);
            if(!__.isset(flag)) continue;
            if(!flag.isFlag()) continue;
            if(hasFlag(flags[i]) && ((flag.requiresValue() && !__.empty(flagValue(flags[i]))) || !flag.requiresValue()))
                return flag;
        }
        return null;
    }

    protected Arg orRequiredFlag(String ...flags) {
        for(int i=0; i<flags.length; i++) {
            Arg flag = getFlagArg(flags[i]);
            if(!__.isset(flag)) continue;
            if(!flag.isFlag()) continue;
            if(hasFlag(flags[i]) && ((flag.requiresValue() && !__.empty(flagValue(flags[i])) || !flag.requiresValue())))
                return flag;
        }
        String error = "[";
        for(int i=0; i<flags.length; i++) {
            if(i > 0) error += " | ";
            error += "--" + flags[i];
        }
        error += "] One of these flags are required for this command.";
        command.exit(error, 1);
        return null;
    }

    protected boolean hasFlag(String flag) {
        return findFlag(flag) != null;
    }

    protected String flagValue(String flag) {
        Arg arg = findFlag(flag);
        if(arg == null) return "";
        return arg.getValue();
    }

    public void parsedArgs(Arg[] args) {
        this.args = Arrays.asList(args);
    }

    protected Arg getArg() {
        return getArg(0);
    }

    protected Arg getArg(int index) {
        return args.get(index);
    }

    private Arg findFlag(String flag) {
        flag = flag.replace("-", "");
//        Arg[] args = command.getArgs();
//        for(int i=0; i<args.length; i++) {
//            if(__.same(args[i].getName(), flag)) return args[i];
//        }
        return null;
    }

    protected abstract Help help();
    public abstract void run() throws Exception;
    public abstract void availableArgs(ArrayList<Arg> args);

    public boolean flagExists(String flag) {
        try {
            for(Arg arg : availableArgs) {
                if(arg.isFlag()) {
                    if(flag.length() == 1) {
                        if(__.same(arg.getOption(), flag.replaceAll("-", ""))) return true;
                    } else {
                        if(__.same(arg.getName(), flag.replaceAll("-", ""))) return true;
                    }
                }
            }
            return false;
        } catch (Exception ignored) {}
        return false;
    }

    public Arg getFlagArg(String flag) {
        try {
            for(Arg arg : availableArgs) {
                if(arg.isFlag()) {
                    if(flag.length() == 1) {
                        if(__.same(arg.getOption(), flag.replaceAll("-", ""))) return arg;
                    } else {
                        if(__.same(arg.getName(), flag.replaceAll("-", ""))) return arg;
                    }
                }
            }
        } catch (Exception ignored) {}
        return null;
    }

}
