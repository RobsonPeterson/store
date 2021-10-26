package br.com.robson.store;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

public class ProjectTest {
	@Test
	public void projectTeste() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String content = target.path("/project").request().get(String.class);
		System.out.println(content);
		Assert.assertTrue(content.contains("name>Minha loja"));
	}
}
