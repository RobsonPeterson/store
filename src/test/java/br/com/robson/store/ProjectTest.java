package br.com.robson.store;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

import br.com.robson.store.dao.ShoppingCartDao;
import br.com.robson.store.model.Project;
import br.com.robson.store.model.ShoppingCart;
import junit.framework.Assert;

public class ProjectTest {
	
	private HttpServer server;
	private Client client;
	private WebTarget target;
	
	@Before
	public void startServer() {
		server = Server.bootServer();
		this.client = ClientBuilder.newClient();
		this.target = client.target("http://localhost:8080");
	}
	
	@After
	public void stopServer() {
		server.shutdown();
	}
	
	@Test
	public void projectTeste() {
		String content = target.path("/project/1").request().get(String.class);
		Project project = (Project) new XStream().fromXML(content);
		Assert.assertEquals("Minha loja", project.getName());
	}
	
	
	@Test
	public void testProject() {
		Project project = new Project();
		project.setName("Java");
		project.setBeginningYear(2021);
		String xml = project.toXML();
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		
		Response response = target.path("/project").request().post(entity);
		Assert.assertEquals(201, response.getStatus());
		String location = response.getHeaderString("Location");
		String content = client.target(location).request().get(String.class);
		Assert.assertTrue(content.contains("Java"));
	}
	
	
	
	
	
}
