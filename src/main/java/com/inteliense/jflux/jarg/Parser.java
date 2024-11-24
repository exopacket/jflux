package com.inteliense.jflux.jarg;

import java.util.ArrayList;

public class Parser {

    public static Arg[] getArgs(String[] args, Keywords keywords, HandlesCommands cmd) {
        ArrayList<Arg> argList = new ArrayList<>();
        boolean needValue = false;
        int prevFlagIndex = -1;
        String prevName = "";

        ArrayList<String> _args = new ArrayList<>();
        for(String arg : args) {
            if(arg.contains("=")) {
                String[] parts = arg.split("=");
                _args.add(parts[0]);
                _args.add(parts[1]);
            } else {
                _args.add(arg);
            }
        }

        args = new String[_args.size()];
        _args.toArray(args);

        for(int i=0; i<args.length; i++) {
            String arg = args[i];
            if(flagCheck(arg) && cmd.flagExists(arg)) {
                Arg flag = cmd.getFlagArg(arg);
                if(flag == null) continue;
                argList.add(flag);
                if(flag.requiresValue()) prevFlagIndex = argList.size() - 1;
                continue;
            }
            if(!flagCheck(arg) && prevFlagIndex > 0) {
                argList.get(prevFlagIndex).setValue(arg);
                prevFlagIndex = -1;
                continue;
            }
            if(!flagCheck(arg) && needValue) {
                argList.add(new Arg(prevName, arg));
                prevName = "";
                needValue = false;
                continue;
            }
            if(!flagCheck(arg) && keywords.exists(arg)) {
                needValue = keywords.requiresValue(arg);
                prevName = (needValue) ? arg : "";
                if(!needValue) argList.add(new Arg(arg));
            }
        }
        Arg[] arr = new Arg[argList.size()];
        arr = argList.toArray(arr);
        return arr;
    }

    private static boolean flagCheck(String v) {
        return v.contains("--");
    }

}