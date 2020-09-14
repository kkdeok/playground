package com.kkd.study.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Kideok Kim on 17/06/2018.
 */
public class TCPServer {
    private static final int PORT = 9001;

    public void start() {
        System.out.println("TCP server is running");
        try (ServerSocket listener = new ServerSocket(PORT)) {
            while (true) {
                try (Socket socket = listener.accept();
                     InputStream is = socket.getInputStream();
                     OutputStream os = socket.getOutputStream();
                     DataInputStream dis = new DataInputStream(is);
                     DataOutputStream dos = new DataOutputStream(os)) {

                    String clientMessage = dis.readUTF();
                    if ("hello server".equals(clientMessage)) {
                        dos.writeUTF(ResponseMessage.HELLO_CLIENT.getMessage());
                    } else {
                        dos.writeUTF(ResponseMessage.UNEXPECTED.getMessage());
                    }
                    dos.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TCPServer server = new TCPServer();
        server.start();
    }
}
