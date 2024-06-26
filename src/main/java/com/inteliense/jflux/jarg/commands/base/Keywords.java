package com.inteliense.jflux.jarg.commands.base;

import org.extendedweb.aloft.server.run.cli.commands.keywords.Build;
import org.extendedweb.aloft.server.run.cli.commands.keywords.Debug;
import org.extendedweb.aloft.server.run.cli.commands.keywords.Extension;
import org.extendedweb.aloft.server.run.cli.commands.keywords.Serve;

import java.sql.Array;
import java.util.Arrays;
import java.util.HashMap;

@SuppressWarnings("ALL")
public class Keywords {

    private static final Class[] commands = new Class[]{
        Serve.class,
        Build.class,
        Debug.class,
        Extension.class
    };

    private static final Class[] required = new Class[]{
        Build.class
    };

    private static final HashMap<String, Object[]> flags = new HashMap<String, Object[]>() {{
            put("--localhost", new Object[]{ new Arg("--localhost"), Serve.class });
            put("--ipv6", new Object[]{ new Arg("--ipv6"), Serve.class });
            put("--balanced", new Object[]{ (new Arg("--balanced")), Serve.class });
            put("--public", new Object[]{ (new Arg("--public")), Debug.class });
            put("--secure", new Object[]{ (new Arg("--secure")), Debug.class });
            put("--port", new Object[]{ (new Arg("--port", true)), Debug.class });
            put("--src", new Object[]{ (new Arg("--src", true)), Debug.class });
            put("--config", new Object[]{ (new Arg("--config", true)), Debug.class });
//            put("--src", new Object[]{ (new Arg("--src", true)), Migrate.class });
//            put("--config", new Object[]{ (new Arg("--config", true)), Migrate.class });
    }};

    public static Class getClass(String cmd) {
        try {
            return Class.forName("org.extendedweb.aloft.server.run.cli.commands.keywords." + cmd.substring(0, 1).toUpperCase() + cmd.substring(1));
        } catch (Exception ignored) {}
        return null;
    }

    public static boolean exists(String cmd) {
        try {
            return Arrays.asList(commands).contains(getClass(cmd));
        } catch (Exception e) {e.printStackTrace();}
        return false;
    }

    public static boolean requiresValue(String cmd) {
        try {
            return Arrays.asList(required).contains(getClass(cmd));
        } catch (Exception ignored) {}
        return false;
    }

    public static boolean flagExists(String className, String flag) {
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

    public static Arg getFlagArg(String flag) {
        try {
            if(flags.containsKey(flag.toLowerCase())) {
                Object[] v = flags.get(flag.toLowerCase());
                return (Arg) v[0];
            }
        } catch (Exception ignored) {}
        return null;
    }

}
