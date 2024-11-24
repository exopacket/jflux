package com.inteliense.jflux.shell;

<<<<<<< HEAD
=======
import com.amazonaws.util.Platform;
import com.inteliense.jflux.http.api.utils.Exec;
>>>>>>> 50fdb4c525291b03960283dca73966876f808659
import com.inteliense.jflux.sys.PlatformUtils;
import com.inteliense.jflux.threading.types.JoinedThread;
import com.inteliense.jflux.todash.__;

<<<<<<< HEAD
=======
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
>>>>>>> 50fdb4c525291b03960283dca73966876f808659
import java.util.ArrayList;

public abstract class Run {

    public abstract void lineRead(String str, byte[] bytes);

    public static JoinedThread group(CommandGroup group) {
        JoinedThread thread = new JoinedThread() {
            @Override
            protected boolean execute() {
<<<<<<< HEAD
                ArrayList<CommandGroup.Group> groups = group.get(PlatformUtils.getOsType());
                for(CommandGroup.Group group : groups) {
=======
                System.out.println("EXECUTE");
                ArrayList<CommandGroup.Group> groups = group.get(PlatformUtils.getOsType());
                for(CommandGroup.Group group : groups) {
                    System.out.println("group");
>>>>>>> 50fdb4c525291b03960283dca73966876f808659
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
<<<<<<< HEAD
=======
                System.out.println("DONE");
>>>>>>> 50fdb4c525291b03960283dca73966876f808659
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

<<<<<<< HEAD
    public static int runAndWait(String unixCmd, String windowsCmd, WindowsShell shell) {
        PlatformUtils.OpSys os = PlatformUtils.getOsType(false);
        if(os == PlatformUtils.OpSys.WINDOWS) {
            if(shell == WindowsShell.POWERSHELL) {
                try {
                    ProcessBuilder pb = new ProcessBuilder("powershell.exe", "/c", windowsCmd);
                    Process process = pb.start();
                    return process.waitFor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if(shell == WindowsShell.COMMAND_PROMPT) {
                try {
                    ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", windowsCmd);
                    Process process = pb.start();
                    return process.waitFor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if(os == PlatformUtils.OpSys.LINUX || os == PlatformUtils.OpSys.MAC_OSX) {
            try {
                ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", unixCmd);
                Process process = pb.start();
                return process.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
=======
    public static int runAndWait(String unixCmd, String windowsCmd, String powerShellCmd) {
>>>>>>> 50fdb4c525291b03960283dca73966876f808659
        return -1;
    }

    public static int stream(String unixCmd, String windowsCmd, String powerShellCmd, __.Console console) {
        return -1;
    }

    public static class Linux {
<<<<<<< HEAD
        public static String runAndWait(String cmd) {
=======
        public static String withOut(String cmd) {
>>>>>>> 50fdb4c525291b03960283dca73966876f808659
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
