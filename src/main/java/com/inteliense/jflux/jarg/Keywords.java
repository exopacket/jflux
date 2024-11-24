package com.inteliense.jflux.jarg;

<<<<<<< HEAD
import com.inteliense.jflux.todash.__;

=======
>>>>>>> 50fdb4c525291b03960283dca73966876f808659
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("ALL")
public class Keywords {

    private String pkg;
<<<<<<< HEAD
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
=======
    private ArrayList<Class<?>> commands = new ArrayList<>();
    private ArrayList<Class<?>> required = new ArrayList<>();

    public Keywords(String pkg) {
        this.pkg = pkg;
    }

    public void register(boolean requiresValue, Class<?>... classes) {
        for(Class<?> c : classes) commands.add(c);
        if(requiresValue) for(Class<?> c : classes) required.add(c);
>>>>>>> 50fdb4c525291b03960283dca73966876f808659
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
<<<<<<< HEAD
            boolean exits = false;
            for(Class<?> c : commands.keySet()) {
                if(c.equals(getClass(cmd))) return true;
            }
=======
            return commands.contains(getClass(cmd));
>>>>>>> 50fdb4c525291b03960283dca73966876f808659
        } catch (Exception ignored) { }
        return false;
    }

    public boolean requiresValue(String cmd) {
        try {
            return required.contains(getClass(cmd));
        } catch (Exception ignored) {}
        return false;
    }

<<<<<<< HEAD
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
=======
>>>>>>> 50fdb4c525291b03960283dca73966876f808659

}
