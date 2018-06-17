package com.doubleknd26.exercise.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Kideok Kim on 17/06/2018.
 */
public class Server {
    private static final int PORT = 9001;

    public void start() {
        System.out.println("server is running");
        try (ServerSocket listener = new ServerSocket(PORT)) {
            while (true) {
                try (Socket socket = listener.accept();
                     InputStream is = socket.getInputStream();
                     OutputStream os = socket.getOutputStream();
                     DataInputStream dis = new DataInputStream(is);
                     DataOutputStream dos = new DataOutputStream(os)) {

                    String clientMessage = dis.readUTF();
                    if ("hello server".equals(clientMessage)) {
                        dos.writeUTF("hello client");
                    } else {
                        dos.writeUTF("unexpected message");
                    }
                    dos.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
