package com.sebastian.web.gamestore.dao;

public class User {
	String username;
	String email;
	boolean enabled;

	public User() {

	}
	
	public User(String username) {
		this.username = username;
	}

	public User(String username, String email, boolean enabled) {
		this.username = username;
		this.email = email;
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}