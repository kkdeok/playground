package com.doubleknd26.exercise.grpc.greeter;

import com.doubleknd26.exercise.grpc.GreeterGrpc;
import com.doubleknd26.exercise.grpc.HelloRequest;
import com.doubleknd26.exercise.grpc.HelloResponse;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kideok Kim on 23/10/2018.
 */
public class GreeterClient {
    private static final Logger logger = Logger.getLogger(GreeterClient.class.getName());
    private static final int PORT = 1888;
    private String address;
    private int port;
    private ManagedChannel channel;

    public GreeterClient(String address, int port) {
        this.address = address;
        this.port = port;
        this.channel = ManagedChannelBuilder.forAddress(address, port)
                .usePlaintext(true)
                .build();
    }

    private HelloResponse getResponseUsingBlockingStub(HelloRequest request) {
        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
        HelloResponse response = null;
        try {
            response = stub.process(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
        return response;
    }

    private HelloResponse getResponseUsingFutureStub(HelloRequest request) {
        GreeterGrpc.GreeterFutureStub stub = GreeterGrpc.newFutureStub(channel);
        HelloResponse response = null;
        try {
            ListenableFuture<HelloResponse> future = stub.process(request);
            response = future.get();
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }

    private void run() throws Exception {
        HelloRequest request = HelloRequest.newBuilder()
                .setName("doubleknd26")
                .build();
        ScheduledExecutorService se = Executors.newScheduledThreadPool(1);
        se.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        HelloResponse response = getResponseUsingFutureStub(request);
                        logger.info("Greeting: " + response.getMessage());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                , 0
                , 1
                , TimeUnit.SECONDS);
    }

    private void shutdown() throws InterruptedException {
        if (channel != null) {
            channel.shutdown();
            if (!channel.awaitTermination(5, TimeUnit.SECONDS)) {
                channel.shutdownNow();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        GreeterClient client = new GreeterClient("localhost", PORT);
        client.run();
//        client.shutdown();
    }
}
