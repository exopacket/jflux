package com.inteliense.jflux.sockets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public abstract class SocketClient {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public SocketClient(Socket socket) throws Exception {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void write(String output) {
        out.println(output);
    }

    public abstract void newRequest(String[] lines);
    public void parse(String[] lines) {
        newRequest(lines);
    }

    public String[] read() {
        Scanner scnr = new Scanner(in);
        StringBuilder sb = new StringBuilder();
        while (scnr.hasNextLine()) {
            sb.append(scnr.nextLine()).append('\n');
        }
        return sb.toString().split("\\n");
    }

    public void destroy() throws Exception {
        in.close();
        out.close();
        socket.close();
    }

}
