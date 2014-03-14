package com.sebastian.web.gamestore.dao;

public class Company {

	private int id;
	private String name;
	private boolean isPublisher = false;

	public Company() {

	}

	public Company(String name) {
		super();
		this.name = name;
	}

	public Company(String name, boolean isPublisher) {
		super();
		this.name = name;
		this.isPublisher = isPublisher;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPublisher() {
		return isPublisher;
	}

	public void setPublisher(boolean isPublisher) {
		this.isPublisher = isPublisher;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", isPublisher="
				+ isPublisher + "]";
	}

}
