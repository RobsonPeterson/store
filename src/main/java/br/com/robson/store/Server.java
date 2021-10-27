package br.com.robson.store;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;


public class Server {


	public static void main(String[] args) throws IOException{
		HttpServer server = bootServer();
		System.out.println("Server run");
		System.in.read();
		server.shutdownNow();
	}

	static HttpServer bootServer() {
		ResourceConfig config = new ResourceConfig().packages("br.com.robson.store");
		URI uri = URI.create("http://localhost:8080");
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		return server;
	}
}
