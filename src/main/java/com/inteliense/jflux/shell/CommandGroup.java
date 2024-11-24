package com.inteliense.jflux.shell;

import com.inteliense.jflux.sys.PlatformUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandGroup {

    private HashMap<PlatformUtils.OpSys, ArrayList<Group>> groups = new HashMap<>();

    public CommandGroup() { }

    public ArrayList<Group> get(PlatformUtils.OpSys os) {
        if(!groups.containsKey(os)) return new ArrayList<>();
        return groups.get(os);
    }

    public Group windowsGroup(Shell shell) {
        return new Group(shell);
    }

    public Group macOsGroup() {
        return new Group(Shell.UNIX_SHELL);
    }

    public Group linuxGroup() {
        return new Group(Shell.UNIX_SHELL);
    }

    public void appendGroup(Group group, PlatformUtils.OpSys os) {
        if(!groups.containsKey(os)) groups.put(os, new ArrayList<>());
        groups.get(os).add(group);
    }

    public static class Group {
        private Shell shell;
        private ArrayList<String> commands = new ArrayList<>();
        private String directory = ".";

        public Group(Shell shell) {
            this.shell = shell;
        }

        public Group(Shell shell, String directory) {
            this.shell = shell;
            setDirectory(directory);
        }

        public void setDirectory(String directory) {
            this.directory = directory;
        }

        public void appendCommand(String cmd) {
            commands.add(cmd);
        }

        public ArrayList<String> getCommands() {
            return commands;
        }

        public Shell getShell() {
            return shell;
        }

        public String getDirectory() {
            return directory;
        }

    }

    public static String[] getProcessBuilderArgs(Shell shell) {
        if(shell == Shell.COMMAND_PROMPT) {
            return new String[]{"cmd.exe", "\\c"};
        } else if(shell == Shell.UNIX_SHELL) {
            return new String[]{"/bin/bash", "-c"};
        }
        return new String[]{"", ""};
    }

    public enum Shell {
        POWERSHELL,
        COMMAND_PROMPT,
        UNIX_SHELL
    }

}
