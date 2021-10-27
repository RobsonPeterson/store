package br.com.robson.store;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.robson.store.model.ShoppingCart;
import junit.framework.Assert;

public class ClientTests {
	
	private HttpServer server;
	
	@Before
	public void startServer() {
		server = Server.bootServer();  
	}
	
	@After
	public void stopServer() {
		server.shutdown();
	}
	
	@Test
	public void testSeashShoppingCart() {
		
		Client client = ClientBuilder.newClient();
	    WebTarget target = client.target("http://localhost:8080");
	    String content = target.path("/shoppingcart").request().get(String.class);    
	    ShoppingCart shoppingCart = (ShoppingCart) new XStream().fromXML(content);	    
	    Assert.assertEquals("Rua Vergueiro 3185, 8 andar", shoppingCart.getRua());
	}
}
