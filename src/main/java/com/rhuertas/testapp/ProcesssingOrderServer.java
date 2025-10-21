package com.rhuertas.testapp;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;

public class ProcesssingOrderServer {
  @Value("${app.grpcPort}")
  private int grpcPort;
  public static void main(String[] args) throws IOException,
    InterruptedException {
      int port = 50052;
      Server server = ServerBuilder.forPort(port)
        .addService(new ProcessingOrderImpl()).build();
      server.start();
      System.out.println("server started. Listening on port : " +
        port);
      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        System.out.println("Received shutdown request.");
        server.shutdown();
        System.out.println("server stopped.");
      }));
      server.awaitTermination();
    }
}


