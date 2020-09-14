package com.kkd.study.grpc.helloworld;

import com.kkd.study.grpc.GreeterGrpc;
import com.kkd.study.grpc.HelloRequest;
import com.kkd.study.grpc.HelloResponse;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * I referred https://github.com/grpc/grpc-java for training purpose. 
 */
public class HelloWorldServer {
	private static final Logger logger = Logger.getLogger(HelloWorldServer.class.getName());
	private Server server;

	private void start() throws IOException {
		/* The port on which the server should run */
		int port = 50051;
		server = ServerBuilder.forPort(port)
			.addService(new GreeterImpl())
			.build()
			.start();
		logger.info("Server started, listening on " + port);
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// Use stderr here since the logger may have been reset by its JVM shutdown hook.
				System.err.println("*** shutting down gRPC server since JVM is shutting down");
				try {
					HelloWorldServer.this.stop();
				} catch (InterruptedException e) {
					e.printStackTrace(System.err);
				}
				System.err.println("*** server shut down");
			}
		});
	}

	private void stop() throws InterruptedException {
		if (server != null) {
			server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
		}
	}

	/**
	 * Await termination on the main thread since the grpc library uses daemon threads.
	 */
	private void blockUntilShutdown() throws InterruptedException {
		if (server != null) {
			server.awaitTermination();
		}
	}

	/**
	 * Main launches the server from the command line.
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		final HelloWorldServer server = new HelloWorldServer();
		server.start();
		server.blockUntilShutdown();
	}

	
	private static class GreeterImpl extends GreeterGrpc.GreeterImplBase {
		@Override
		public void sayHello(HelloRequest request,
			StreamObserver<HelloResponse> responseObserver) {
			HelloResponse response = HelloResponse.newBuilder()
				.setMessage("Hello " + request.getName())
				.build();
			
			responseObserver.onNext(response);
			responseObserver.onCompleted();
		}

		@Override
		public void sayHelloAgain(HelloRequest request,
			StreamObserver<HelloResponse> responseObserver) {
			HelloResponse response = HelloResponse.newBuilder()
				.setMessage("Hello again " + request.getName())
				.build();
			
			responseObserver.onNext(response);
			responseObserver.onCompleted();
		}
	}
}
