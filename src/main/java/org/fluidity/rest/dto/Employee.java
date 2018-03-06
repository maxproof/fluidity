package org.fluidity.rest.dto;

import java.io.Serializable;

//entity resourse for RESTful for CRUD operations
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}

}
