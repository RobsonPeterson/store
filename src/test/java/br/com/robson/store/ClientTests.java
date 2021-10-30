package br.com.robson.store;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.robson.store.model.Product;
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
	    String content = target.path("/shoppingcart/1").request().get(String.class);    
	    ShoppingCart shoppingCart = (ShoppingCart) new XStream().fromXML(content);	    
	    Assert.assertEquals("Rua Vergueiro 3185, 8 andar", shoppingCart.getRua());
	}
	
	public void testePost() {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost;8080");
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.adiciona(new Product(314,  "Tablet", 999, 1));
		shoppingCart.setRua("Rua Vergueiro");
		shoppingCart.setCidade("Sao Paulo");
		String xml = shoppingCart.toXML();
		
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		
		Response response = target.path("/shoppingcart").request().post(entity);
		Assert.assertEquals("<status>sucesso</status>",response.readEntity(String.class));
						
	}
}
