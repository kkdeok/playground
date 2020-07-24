package com.kkd.exercise.grpc.greeter;

import com.kkd.exercise.grpc.GreeterGrpc;
import com.kkd.exercise.grpc.HelloRequest;
import com.kkd.exercise.grpc.HelloResponse;
import com.google.common.collect.Maps;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Kideok Kim on 23/10/2018.
 */
public class GreeterService {
    private static final Logger logger = Logger.getLogger(GreeterService.class.getName());
    private static final int PORT = 1888;
    private Map<String, Long> accumulator;
    private Server server;

    private void start() throws IOException {
        accumulator = Maps.newHashMap();
        server = ServerBuilder.forPort(PORT)
                .addService(new GreeterImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + PORT);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("** shutting down gRPC server since JVM is shutting down");
            GreeterService.this.stop();
            System.err.println("** server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    protected class GreeterImpl extends GreeterGrpc.GreeterImplBase {
        @Override
        public void process(
                HelloRequest request,
                StreamObserver<HelloResponse> responseObserver) {
            temp1(request, responseObserver);
        }
    }

    private void temp1(HelloRequest request,
                       StreamObserver<HelloResponse> responseObserver) {
        endProcess(1, request, new StreamObserver<HelloResponse>() {
            @Override
            public void onNext(HelloResponse value) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("WOW3");
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                System.out.println("WOW4");
            }
        });
    }

    private void temp2(HelloRequest request,
                       StreamObserver<HelloResponse> responseObserver) {
        String name = request.getName();
        accumulator.put(name, accumulator.get(name) + 1L);
        endProcess(2, request, responseObserver);
    }

    private void endProcess(int n, HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println(n);
        String name = request.getName();
        HelloResponse response = HelloResponse.newBuilder()
                .setMessage(name + ": " + accumulator.get(name)).build();
        responseObserver.onNext(response);
        System.out.println("WOW1");
        responseObserver.onCompleted();
        System.out.println("WOW2");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final GreeterService greeterService = new GreeterService();
        greeterService.start();
        greeterService.blockUntilShutdown();
    }
}
