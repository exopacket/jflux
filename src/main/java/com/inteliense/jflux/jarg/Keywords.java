package com.inteliense.jflux.jarg;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("ALL")
public class Keywords {

    private String pkg;
    private ArrayList<Class<?>> commands = new ArrayList<>();
    private ArrayList<Class<?>> required = new ArrayList<>();

    public Keywords(String pkg) {
        this.pkg = pkg;
    }

    public void register(boolean requiresValue, Class<?>... classes) {
        for(Class<?> c : classes) commands.add(c);
        if(requiresValue) for(Class<?> c : classes) required.add(c);
    }

    public Class getClass(String cmd) {
        String[] parts = cmd.split("-");
        String command = "";
        for(int i=0; i<parts.length; i++) {
            command += parts[i].substring(0, 1).toUpperCase() + parts[i].substring(1);
        }
        try {
            return Class.forName(pkg + "." + command);
        } catch (Exception ignored) { }
        return null;
    }

    public boolean exists(String cmd) {
        try {
            return commands.contains(getClass(cmd));
        } catch (Exception ignored) { }
        return false;
    }

    public boolean requiresValue(String cmd) {
        try {
            return required.contains(getClass(cmd));
        } catch (Exception ignored) {}
        return false;
    }


}
