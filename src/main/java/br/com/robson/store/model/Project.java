package br.com.robson.store.model;

import com.thoughtworks.xstream.XStream;

public class Project {
	String name;
	Long id;
	int beginningYear;
	
	public Project(Long id, String name, int beginningYear) {	
		this.name = name;
		this.id = id;
		this.beginningYear = beginningYear;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBeginningYear() {
		return beginningYear;
	}

	public void setBeginningYear(int beginningYear) {
		this.beginningYear = beginningYear;
	}

	@Override
	public String toString() {
		return "Project [name=" + name + ", id=" + id + ", beginningYear=" + beginningYear + "]";
	}

	public String toXML() {
		return new XStream().toXML(this);
	}
}
