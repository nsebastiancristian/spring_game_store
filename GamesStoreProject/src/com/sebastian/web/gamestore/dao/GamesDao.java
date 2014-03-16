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
		
		return jdbc.query("SELECT games.name, companies.name, isPublisher FROM games, companies WHERE games.idDeveloper = companies.id", new RowMapper<Game>(){

			@Override
			public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Game game = new Game();				
				game.setName(rs.getString("games.name"));
				
				Company developer = new Company();
				developer.setName(rs.getString("companies.name"));
				developer.setPublisher(rs.getBoolean("isPublisher"));
				
				game.setDeveloper(developer);
				return game;
			}
			
		});
	}
}
