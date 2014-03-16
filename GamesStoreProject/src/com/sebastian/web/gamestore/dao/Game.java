package com.sebastian.web.gamestore.dao;

public class Game {

	private int id;
	private String name;
	private Company developer;

	public Game() {

	}

	public Game(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Game(int id, String name, Company developer) {
		super();
		this.id = id;
		this.name = name;
		this.developer = developer;
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

	public Company getDeveloper() {
		return developer;
	}

	public void setDeveloper(Company developer) {
		this.developer = developer;
	}

}
