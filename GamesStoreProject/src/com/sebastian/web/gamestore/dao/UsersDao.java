package com.sebastian.web.gamestore.dao;

import java.sql.Types;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component("usersDao")
public class UsersDao {

private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);		
	}
	
	public void createUser(User user) {
		//BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", user.getUsername());
		params.addValue("password", user.getPassword());
		params.addValue("authority", user.isAdmin() == true ?"ROLE_ADMIN":"ROLE_USER");
		params.addValue("name", user.getName());
		params.addValue("email", user.getEmail());
		
		jdbc.update("insert into users (username, password, authority, name, email) values (:username, :password, :authority, :name, :email)", params);
	}

	public String addImageName(String imageFileName, String username) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", username);
		params.addValue("avatar", imageFileName);
		
		jdbc.update("insert into avatars (username, avatar) values (:username, :avatar)", params);
		
		return imageFileName + ".jpg";
	}

}
