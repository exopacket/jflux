package com.inteliense.jflux.sockets;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketServer {

    private ArrayList<SocketClient> clients;
    private ServerSocket serverSocket;

    private InetSocketAddress bindAddr;

    private Thread thread;

    public SocketServer(String addr, int port) throws IOException {
        bindAddr = new InetSocketAddress(InetAddress.getByName(addr), port);
        start();
    }

    private void start() {

        thread = new Thread(() -> {

           boolean created = false;
           try {
               create();
               created = true;
           } catch (Exception e) {
               e.printStackTrace();
           }

           if(created) {

               while(true) {

                   try {

                       SocketClient client = accept();
                       clients.add(client);
                       client.parse(client.read());
                       client.destroy();
                       clients.remove(client);

                   } catch (Exception e) {
                       e.printStackTrace();
                       break;
                   }

               }

               try {
                   this.destroy();
               } catch (Exception e) {
                   e.printStackTrace();
               }

           }

        });
        thread.start();

    }

    private void create() throws Exception {
        serverSocket = new ServerSocket(bindAddr.getPort(), 5000, bindAddr.getAddress());
    }

    private void requestIn(SocketClient client, String[] lines) {

    }

    private SocketClient accept() throws Exception {
        Socket socket = serverSocket.accept();
        SocketClient client = new SocketClient(socket) {
            @Override
            public void newRequest(String[] lines) {
                requestIn(this, lines);
            }
        };
        return client;
    }

    public void interrupt() {
        thread.interrupt();
        try {
            destroy();
        } catch (Exception ignored) { }
    }

    private void destroy() throws Exception {
        serverSocket.close();
        for(SocketClient client : clients) {
            client.destroy();
            clients.remove(client);
        }
    }

}
