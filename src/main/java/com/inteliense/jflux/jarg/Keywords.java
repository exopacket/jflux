package com.inteliense.jflux.jarg;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("ALL")
public class Keywords {

    private String pkg = "";
    private ArrayList<Class<?>> commands = new ArrayList<>();
    private ArrayList<Class<?>> required = new ArrayList<>();

    private HashMap<String, Object[]> flags = new HashMap<String, Object[]>();

    public void setPackage(String name) {
        pkg = name;
    }

    public void register(boolean requiresValue, Class<?>... classes) {
        for(Class<?> c : classes) commands.add(c);
        if(requiresValue) for(Class<?> c : classes) required.add(c);
    }

    public Class getClass(String cmd) {
        try {
            return Class.forName(pkg + cmd.substring(0, 1).toUpperCase() + cmd.substring(1));
        } catch (Exception ignored) {}
        return null;
    }

    public boolean exists(String cmd) {
        try {
            return commands.contains(getClass(cmd));
        } catch (Exception e) {e.printStackTrace();}
        return false;
    }

    public boolean requiresValue(String cmd) {
        try {
            return required.contains(getClass(cmd));
        } catch (Exception ignored) {}
        return false;
    }

    public boolean flagExists(String className, String flag) {
        try {
            Class<?> c = getClass(className);
            if(flags.containsKey(flag.toLowerCase())) {
                Object[] v = flags.get(flag.toLowerCase());
                if(v[1].getClass() == Array.class) {
                    for (Class _c : (Class[]) v[1]) {
                        if(_c == c) return true;
                    }
                } else {
                    if(((Class) v[1]) == c) return true;
                }
            }
        } catch (Exception ignored) {}
        return false;
    }

    public Arg getFlagArg(String flag) {
        try {
            if(flags.containsKey(flag.toLowerCase())) {
                Object[] v = flags.get(flag.toLowerCase());
                return (Arg) v[0];
            }
        } catch (Exception ignored) {}
        return null;
    }

}
