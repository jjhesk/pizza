package edu.colostate.cs414.d.pizza.net;

import java.io.IOException;
import java.net.URI;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

public class Server {

    private static final URI BASE_URI = URI.create("http://localhost:8080/");

    public static void main(String[] args) throws IOException {
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
                BASE_URI,
                new PizzaServerConfig());

        System.out.println("Running, press enter to stop.");
        System.in.read();
        
        server.shutdownNow();
    }

}
