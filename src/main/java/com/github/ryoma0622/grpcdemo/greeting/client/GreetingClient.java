package com.github.ryoma0622.grpcdemo.greeting.client;


import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {
    public static void main(String[] args) {
        System.out.println("Hello I'm a gRPC client");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        // created a greet service client ( blocking - synchronous )
        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);

        // created a protocol buffer greeting message
        Greeting greeting = com.proto.greet.Greeting.newBuilder()
                .setFirstName("Ryoma")
                .setLastName("Jodoi")
                .build();

        // do the same for a GreetRequest
        GreetRequest greetRequest = com.proto.greet.GreetRequest.newBuilder()
                .setGreeting(greeting)
                .build();

        // call the RPC and get back a GreetResponse ( protocol buffers )
        GreetResponse greetResponse =  greetClient.greet(greetRequest);

        System.out.println(greetResponse.getResult());

        System.out.println("Shutting down channel");
        channel.shutdown();
    }
}