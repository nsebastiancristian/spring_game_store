package com.sebastian.web.gamestore.dao;

import java.sql.Date;

public class Game {

	private int id;
	private String name;
	private Company developer;
	private Company publisher;
	private Date dateAdded;
	private Date dateReleased;
	
	private User owner;

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

	public Game(int id, String name, Company developer, Company publisher) {
		super();
		this.id = id;
		this.name = name;
		this.developer = developer;
		this.publisher = publisher;
	}

	public Game(int id, String name, Company developer, Company publisher,
			Date dateAdded, Date dateReleased) {
		super();
		this.id = id;
		this.name = name;
		this.developer = developer;
		this.publisher = publisher;
		this.dateAdded = dateAdded;
		this.dateReleased = dateReleased;
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

	public Company getPublisher() {
		return publisher;
	}

	public void setPublisher(Company publisher) {
		this.publisher = publisher;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getDateReleased() {
		return dateReleased;
	}

	public void setDateReleased(Date dateReleased) {
		this.dateReleased = dateReleased;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	
}
