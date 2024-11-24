package com.inteliense.jflux.shell.unix;

import com.inteliense.jflux.shell.CommandImpl;
import com.inteliense.jflux.threading.types.DetachedThread;
import com.inteliense.jflux.todash.__;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public abstract class UnixCommand implements CommandImpl {

    @Override
    public void noOut(String cmd) throws Exception {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("/bin/sh", "-c", cmd);
        builder.start();
    }

    @Override
    public int runAndWait(String cmd) throws Exception {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("/bin/sh", "-c", cmd);
        Process process = builder.start();
        return process.waitFor();
    }

    @Override
    public int execute(String cmd) throws Exception {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("/bin/sh", "-c", cmd);
        Process process = builder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while((line = reader.readLine()) != null) {
            lineRead(line, line.getBytes(StandardCharsets.UTF_8));
        }
        return process.waitFor();
    }

    @Override
    public int stream(String cmd, __.Console console) throws Exception {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("/bin/sh", "-c", cmd);
        Process process = builder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while((line = reader.readLine()) != null) {
            console.println(line);
        }
        return process.waitFor();
    }

    @Override
    public DetachedThread threaded(String cmd) throws Exception {
        DetachedThread thread = new DetachedThread() {
            @Override
            protected boolean execute() {
                try {
                    return runAndWait(cmd) == 0;
                } catch (Exception e) { e.printStackTrace(); }
                return false;
            }
        };
        thread.start();
        return thread;
    }
}
