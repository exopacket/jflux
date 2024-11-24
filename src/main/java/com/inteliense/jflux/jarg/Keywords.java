package com.inteliense.jflux.jarg;

import com.inteliense.jflux.todash.__;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("ALL")
public class Keywords {

    private String pkg;
    private String executable;
    private HashMap<Class<?>, String> commands = new HashMap<>();
    private ArrayList<Class<?>> required = new ArrayList<>();

    public Keywords(String pkg, String exectuable) {
        this.pkg = pkg;
        this.executable = exectuable;
    }

    public void register(boolean requiresValue, Class<?> c, String helpText) {
        if(requiresValue) {
            required.add(c);
            commands.put(c, helpText);
        } else {
            commands.put(c, helpText);
        }
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
            boolean exits = false;
            for(Class<?> c : commands.keySet()) {
                if(c.equals(getClass(cmd))) return true;
            }
        } catch (Exception ignored) { }
        return false;
    }

    public boolean requiresValue(String cmd) {
        try {
            return required.contains(getClass(cmd));
        } catch (Exception ignored) {}
        return false;
    }

    public String getExecutable() {
        return executable;
    }

    public ArrayList<String> commandHelp() {
        ArrayList<String> lines = new ArrayList<>();
        for(Class<?> c : commands.keySet()) {
            String cmd = __.classToSnake(c.getSimpleName().replace("Keyword", "")).replace('_', '-');
            System.out.println("    " + cmd + "       " + commands.get(c));
        }
        return lines;
    }

}
