package com.kkd.exercise.socket;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by Kideok Kim on 25/06/2018.
 */
public class UDPServerTest {
    private static ExecutorService serverExecutor;
    private static final String HOST = "localhost";
    private static final int PORT = 9002;

    @BeforeClass
    public static void setUpClass() {
        serverExecutor = Executors.newFixedThreadPool(1);
        serverExecutor.submit(() -> new UDPServer().start());
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        serverExecutor.shutdown();
        if (!serverExecutor.isTerminated()) {
            serverExecutor.awaitTermination(2, TimeUnit.SECONDS);
        }
    }

    @Test
    public void testConnectionWithExpectedMessage() {
        try (DatagramSocket socket = new DatagramSocket()) {
            String clientMessage = "hello server";
            byte[] sendData = clientMessage.getBytes();
            InetAddress IPAddress = InetAddress.getByName(HOST);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, PORT);
            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String response = new String(receiveData, 0, receivePacket.getLength());
            assertTrue(ResponseMessage.HELLO_CLIENT.getMessage().equals(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnectionWithUnExpectedMessage() {
        try (DatagramSocket socket = new DatagramSocket()) {
            String clientMessage = "";
            byte[] sendData = clientMessage.getBytes();
            InetAddress IPAddress = InetAddress.getByName(HOST);
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, PORT);
            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String response = new String(receiveData, 0, receivePacket.getLength());
            assertTrue(ResponseMessage.UNEXPECTED.getMessage().equals(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
