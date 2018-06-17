package com.doubleknd26.exercise.socket;

import org.junit.*;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.*;

import static org.junit.Assert.*;

/**
 * Created by Kideok Kim on 17/06/2018.
 */
public class ServerTest {
    private static ExecutorService serverExecutor;
    private static final String HOST = "localhost";
    private static final int PORT = 9001;

    @BeforeClass
    public static void setUpClass() {
        serverExecutor = Executors.newFixedThreadPool(1);
        serverExecutor.submit(() -> new Server().start());
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        if (!serverExecutor.isTerminated()) {
            serverExecutor.awaitTermination(1, TimeUnit.SECONDS);
        }
    }

    @Test
    public void testConnectionWithExpectedMessage() {
        try (Socket socket = new Socket(HOST, PORT);
             InputStream is = socket.getInputStream();
             OutputStream os = socket.getOutputStream();
             DataInputStream dis = new DataInputStream(is);
             DataOutputStream dos = new DataOutputStream(os)){
            dos.writeUTF("hello server");
            dos.flush();
            String response = dis.readUTF();
            assertTrue("hello client".equals(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnectionWithUnexpectedMessage() {
        try (Socket socket = new Socket(HOST, PORT);
             InputStream is = socket.getInputStream();
             OutputStream os = socket.getOutputStream();
             DataInputStream dis = new DataInputStream(is);
             DataOutputStream dos = new DataOutputStream(os)){
            dos.writeUTF("");
            dos.flush();
            String response = dis.readUTF();
            assertTrue("unexpected message".equals(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}