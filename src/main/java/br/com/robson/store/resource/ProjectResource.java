package br.com.robson.store.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.thoughtworks.xstream.XStream;

import br.com.robson.store.dao.ProjectDao;
import br.com.robson.store.model.Project;


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
	@Produces(MediaType.APPLICATION_XML)
	public String addProject(String content) {
		Project project = (Project) new XStream().fromXML(content);
		new ProjectDao().add(project);
		return "<status>sucesso</status:";
	}
}

