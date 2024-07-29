package com.inteliense.jflux.shell;

import com.amazonaws.util.Platform;
import com.inteliense.jflux.http.api.utils.Exec;
import com.inteliense.jflux.sys.PlatformUtils;
import com.inteliense.jflux.threading.types.JoinedThread;
import com.inteliense.jflux.todash.__;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public abstract class Run {

    public abstract void lineRead(String str, byte[] bytes);

    public static JoinedThread group(CommandGroup group) {
        JoinedThread thread = new JoinedThread() {
            @Override
            protected boolean execute() {
                System.out.println("EXECUTE");
                ArrayList<CommandGroup.Group> groups = group.get(PlatformUtils.getOsType());
                for(CommandGroup.Group group : groups) {
                    System.out.println("group");
                    try {
                        String dir = group.getDirectory();
                        CommandGroup.Shell shell = group.getShell();
                        ArrayList<String> commands = group.getCommands();
                        for (String command : commands) {
                            runFromGroup(shell, dir, command);
                            System.out.println(command);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("DONE");
                return false;
            }
        };
        thread.start();
        return thread;
    }

    public static void noOut(String unixCmd, String windowsCmd, String powerShellCmd) {

    }

    private static int runFromGroup(CommandGroup.Shell shell, String directory, String command) {
        try {
            String[] processBuilderArgs = CommandGroup.getProcessBuilderArgs(shell);
            String cd = (directory.equals(".") ? "" : "cd " + directory + " && ");
            ProcessBuilder builder = new ProcessBuilder(
                    processBuilderArgs[0], processBuilderArgs[1], cd + command);
            builder.redirectErrorStream(true);
            Process p = builder.start();
            p.waitFor();
            return p.exitValue();
        } catch (Exception e) {
            return -1;
        }
    }

    public static int runAndWait(String unixCmd, String windowsCmd, String powerShellCmd) {
        return -1;
    }

    public static int stream(String unixCmd, String windowsCmd, String powerShellCmd, __.Console console) {
        return -1;
    }

    public static class Linux {
        public static String withOut(String cmd) {
            try {
                ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", cmd);
                Process process = pb.start();
                process.waitFor();
                return new String(process.getInputStream().readAllBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
