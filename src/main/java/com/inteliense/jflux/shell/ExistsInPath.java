package com.inteliense.jflux.shell;

import com.inteliense.jflux.sys.PlatformUtils;

public class ExistsInPath {

    public static boolean existsInPath(String executable) throws Exception {
        PlatformUtils.OpSys os = PlatformUtils.getOsType(false);
        if(os == PlatformUtils.OpSys.WINDOWS) return win_commandWasSuccessful(executable);
        else return unix_commandWasSuccessful(executable);
    }

    private static boolean win_commandWasSuccessful(String executable) throws Exception {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("where.exe", executable);
        Process p = builder.start();
        int v = p.waitFor();
        return v == 0;
    }

    private static boolean unix_commandWasSuccessful(String executable) throws Exception {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("/bin/bash", "-c", "which " + executable);
        Process p = builder.start();
        int v = p.waitFor();
        return v == 0;
    }
}
