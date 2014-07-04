package com.sebastian.web.gamestore.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Game {

	private int id;

	@NotNull
	@Size(min = 5, max = 100, message = "The title of the game must be between 5 and 100 characters long")
	private String name;

	private Company developer;
	private Company publisher;
	// @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateAdded;
	private Date dateReleased;
	boolean wishlisted = false;

	@NotNull
	@Size(min = 5, max = 100, message = "The description must be between 5 and 300 characters long")
	private String description;

	private float price;
	private int discount = 0;
	private List<String> genres = new ArrayList<String>();
	private List<String> platforms = new ArrayList<String>();
	private List<Theme> themes = new ArrayList<Theme>();
	private List<Tag> tags = new ArrayList<Tag>();
	
	
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

	public boolean isWishlisted() {
		return wishlisted;
	}

	public void setWishlisted(boolean wishlisted) {
		this.wishlisted = wishlisted;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public List<String> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<String> platforms) {
		this.platforms = platforms;
	}

	public List<Theme> getThemes() {
		return themes;
	}

	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}

	
	
	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", developer=" + developer
				+ ", publisher=" + publisher + ", dateAdded=" + dateAdded
				+ ", dateReleased=" + dateReleased + ", wishlisted="
				+ wishlisted + ", description=" + description + ", price="
				+ price + ", discount=" + discount + ", genres=" + genres
				+ ", platforms=" + platforms + ", themes=" + themes + ", tags="
				+ tags + ", owner=" + owner + "]";
	}

	
}
