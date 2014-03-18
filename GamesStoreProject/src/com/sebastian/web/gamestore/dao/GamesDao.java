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
	
	/**
	 * Returns a list with all the games in the database
	 * @param user
	 * @return
	 */
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
				
				//see if this game is owned by the current user
				if(isGameOwned(user, rs.getInt("games.id")))
				{
					game.setOwner(user);
				}
				
				System.out.println("Game:" + game.getName());
				boolean owned = game.getOwner() != null ? true: false;
				System.out.println("Owned:" + owned);
				
				return game;
			}
			
		});
	}
	
	/**
	 * Returns true if the user with the specified username owns the game with the specified id
	 * @param user - a User object having the current username 
	 * @param id - id of the game
	 * @return
	 */
	private boolean isGameOwned(User user, int id) {
		
		//check to see if there is an user
		if(user == null) {
			return false;
		}
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		params.addValue("username", user.getUsername());
		
		return jdbc.queryForInt("select count(*) from ownedgames where games_id = :id and username = :username", params) >= 1;
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

	public List<Game> getMyWishlistGames(String username) {
		MapSqlParameterSource params = new MapSqlParameterSource("username", username);
		return jdbc.query("SELECT wishlistgames.games_id, games.name, games.id from wishlistgames join games on wishlistgames.games_id = games.id where wishlistgames.username = :username", params, new RowMapper<Game>(){

			@Override
			public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Game game = new Game();
				game.setId(rs.getInt("games.id"));
				game.setName(rs.getString("games.name"));
								
				return game;
			}
			
		});
	}
	
	public void addToWishlist(String id, String username) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		params.addValue("username", username);
		
		jdbc.update("insert into wishlistgames (games_id, username) values (:id, :username)", params);
	}
	
	
}
