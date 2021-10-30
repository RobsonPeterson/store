package br.com.robson.store.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.thoughtworks.xstream.XStream;

import br.com.robson.store.dao.ProjectDao;
import br.com.robson.store.dao.ShoppingCartDao;
import br.com.robson.store.model.Project;
import br.com.robson.store.model.ShoppingCart;


@Path("project")
public class ProjectResource {
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String search(@PathParam("id") long id) {
		Project project = new ProjectDao().search(id);
		return project.toXML();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response addProject(String content) {
		Project project = (Project) new XStream().fromXML(content);
		new ProjectDao().add(project);
		URI uri = URI.create("/project/" + project.getId());
		return Response.created(uri).build();
	}
	
	@Path("{id}")
	@DELETE
	public Response removeProject(@PathParam("id") long id) {
		new ProjectDao().remove(id);
		return Response.ok().build();
	}
	
//	@Path("{id}/product/{productId}")
//	@DELETE
//	public Response removeProduct(@PathParam("id") long id, @PathParam("productId") long productId) {
//		ShoppingCart shoppingCart = new ShoppingCartDao().busca(id);
//		shoppingCart.remove(productId);
//		return Response.ok().build();
//	}
}

