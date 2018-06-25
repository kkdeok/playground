package com.doubleknd26.exercise.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Kideok Kim on 25/06/2018.
 */
public class UDPServer {
    private static final int PORT = 9002;

    public void start() {
        System.out.println("UDP server is running");
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            byte[] receiveData = new byte[1024];
            while(true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String clientMessage = new String(receiveData, 0, receivePacket.getLength());
                String serverMessage = "";
                if ("hello server".equals(clientMessage)) {
                    serverMessage += ResponseMessage.HELLO_CLIENT.getMessage();
                } else {
                    serverMessage += ResponseMessage.UNEXPECTED.getMessage();
                }
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                byte[] sendData = serverMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(
                        sendData, sendData.length, IPAddress, port);
                socket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UDPServer server = new UDPServer();
        server.start();
    }
}
