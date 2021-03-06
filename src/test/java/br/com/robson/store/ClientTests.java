package br.com.robson.store;


import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.robson.store.model.Product;
import br.com.robson.store.model.ShoppingCart;
import junit.framework.Assert;

public class ClientTests {
	
	
	private HttpServer server;
	private Client client;
	private WebTarget target;
	
	
	
	@Before
	public void startServer() {
		server = Server.bootServer();
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFeature(Logger.getLogger(getClass().getName()), Level.OFF, LoggingFeature.Verbosity.PAYLOAD_TEXT, 8192));
		this.client = ClientBuilder.newClient(config);
	    this.target = client.target("http://localhost:8080");
	}
	
	@After
	public void stopServer() {
		server.shutdown();
	}
	
	@Test
	public void testSeashShoppingCart() {
	    String content = target.path("/shoppingcart/1").request().get(String.class);    
	    ShoppingCart shoppingCart = (ShoppingCart) new XStream().fromXML(content);	    
	    Assert.assertEquals("Rua Vergueiro 3185, 8 andar", shoppingCart.getRua());
	}
	
	@Test
	public void testePostShoppingCart() {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.adiciona(new Product(314,  "Tablet", 999, 1));
		shoppingCart.setRua("Rua Vergueiro");
		shoppingCart.setCidade("Sao Paulo");
		String xml = shoppingCart.toXML();
		
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		
		Response response = target.path("/shoppingcart").request().post(entity);		
		Assert.assertEquals(201, response.getStatus());
		String location = response.getHeaderString("Location");
		String content = client.target(location).request().get(String.class);
		Assert.assertTrue(content.contains("Tablet"));
				
	}
}
