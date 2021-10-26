package br.com.robson.store.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import br.com.robson.store.model.Project;

public class ProjectDao {
	
	
	private static Map<Long, Project> banco = new HashMap<Long, Project>();
	private static AtomicLong contador = new AtomicLong(1);
	
	static {
		banco.put(1l, new Project(1l, "Minha loja", 2014));
		banco.put(2l, new Project(2l, "Alura", 2012));
	}
	
	public void add(Project project) {
		Long id = contador.incrementAndGet();
		project.setId(id);
		banco.put(id, project);
	}
	
	public Project search(Long id) {
		return banco.get(id);
	}
	
	public Project remove(Long id) {
		return banco.remove(id);
	}
	
}
