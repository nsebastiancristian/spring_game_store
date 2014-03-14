package com.sebastian.web.gamestore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("gamesDao")
public class GamesDao {
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);		
	}
	
	public List<Game> getAllGames() {
		
		return jdbc.query("select name from games", new RowMapper<Game>(){

			@Override
			public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Game game = new Game();				
				game.setName(rs.getString("name"));
				
				return game;
			}
			
		});
	}
}
