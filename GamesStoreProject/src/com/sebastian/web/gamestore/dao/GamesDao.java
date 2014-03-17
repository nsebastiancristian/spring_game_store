package com.sebastian.web.gamestore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("gamesDao")
public class GamesDao {
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);		
	}
	
	public List<Game> getAllGames(final User user) {
			
		return jdbc.query("SELECT games.id, games.name, games.addedOn, games.releasedOn, companies.name, c.name from games left join companies on games.idDeveloper = companies.id left join companies c on games.idPublisher = c.id", new RowMapper<Game>(){

			@Override
			public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Game game = new Game();
				game.setId(rs.getInt("games.id"));
				game.setName(rs.getString("games.name"));
				
				game.setDateAdded(rs.getDate("games.addedOn"));
				game.setDateReleased(rs.getDate("games.releasedOn"));
				
				Company developer = new Company();
				developer.setName(rs.getString("companies.name"));
				developer.setPublisher(false);
				game.setDeveloper(developer);
				
				Company publisher = new Company();
				publisher.setName(rs.getString("c.name"));
				publisher.setPublisher(true);
				
				game.setPublisher(publisher);
				return game;
			}
			
		});
	}
	
	private boolean isOwned(User user, int id) {
		return false;
	}

	public void buyGame(String id, String username) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		params.addValue("username", username);
		
		jdbc.update("insert into ownedgames (games_id, username) values (:id, :username)", params);
	}

	public List<Game> getMyGames(final User user) {
		MapSqlParameterSource params = new MapSqlParameterSource("username", user.getUsername());
		return jdbc.query("SELECT ownedgames.games_id, games.name, games.id from ownedgames join games on ownedgames.games_id = games.id where ownedgames.username = :username", params, new RowMapper<Game>(){

			@Override
			public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Game game = new Game();
				game.setId(rs.getInt("games.id"));
				game.setName(rs.getString("games.name"));
				
				game.setOwner(user);
								
				return game;
			}
			
		});
	}
}
