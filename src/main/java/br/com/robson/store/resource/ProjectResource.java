package br.com.robson.store.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.robson.store.dao.ProjectDao;
import br.com.robson.store.model.Project;


@Path("project")
public class ProjectResource {
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String search() {
		Project project = new ProjectDao().search(1l);
		return project.toXML();
	}
}

