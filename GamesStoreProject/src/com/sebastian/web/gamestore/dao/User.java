package com.sebastian.web.gamestore.dao;

public class User {
	String username;
	String email;
	String password;
	boolean enabled = true;
	String name;
	boolean admin = false;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email
				+ ", password=" + password + ", enabled=" + enabled + ", name="
				+ name + ", isAdmin=" + admin + "]";
	}

}
