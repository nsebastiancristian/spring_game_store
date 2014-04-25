package com.sebastian.web.gamestore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
				else
				{
					//the game is not owned so we can check if it was wishlisted
					if (isGameWishlisted(user, rs.getInt("games.id"))) {
						game.setWishlisted(true);
					}
				}
				
				
				
				return game;
			}
			
		});
	}
	
	/**
	 * Returns a list of games with all the games from the database for the admin page
	 * @return a list of games
	 */
	public List<Game> getAllGames() {
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

	public void buyGame(String id, String username) {
		//insert into ownedgames table
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		params.addValue("username", username);
		
		jdbc.update("insert into ownedgames (games_id, username) values (:id, :username)", params);
		
		//delete from wishlistgames table (if it was previously wishlisted)
		jdbc.update("delete from wishlistgames where games_id = :id and username = :username", params);
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
	
	/**
	 * Returns true if the user with the specified username wishlisted the game with the specified id, if the function returns false the game is either owned by the respective user or the user hasn't wishlisted that game
	 * @param user - a User object having the current username 
	 * @param id - id of the game
	 * @return
	 */
	public boolean isGameWishlisted(User user, int id) {
		
		//check to see if there is an user
		if(user == null) {
			return false;
		}
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		params.addValue("username", user.getUsername());
		
		return jdbc.queryForInt("select count(*) from wishlistgames where games_id = :id and username = :username", params) >= 1;
	}

	public Game getGameDetails(String id) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		List<Game> game =  jdbc.query("SELECT games.id, games.name, games.addedOn, games.releasedOn, games.description, companies.name, c.name from games left join companies on games.idDeveloper = companies.id left join companies c on games.idPublisher = c.id where games.id = :id", params, new RowMapper<Game>(){

			@Override
			public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Game game = new Game();
				game.setId(rs.getInt("games.id"));
				game.setName(rs.getString("games.name"));
				game.setDateAdded(rs.getDate("games.addedOn"));
				game.setDateReleased(rs.getDate("games.releasedOn"));
				game.setDescription(rs.getString("games.description"));
				
				Company developer = new Company(rs.getString("companies.name"));
				Company publisher = new Company(rs.getString("c.name"));
				
				game.setDeveloper(developer);
				game.setPublisher(publisher);
								
				return game;
			}
			
		});
		
		return game.get(0);
	}
	
	/**
	 * Return the user bean of a owner of a game with the specified id. 
	 * @param id : the id of the game
	 * @param username : the username of the owner
	 * @return : Returns null if there is no owner of the specified game with the specified username, otherwise it returns the owner's bean
	 */
	public User getGameOwner(String id, String username) {
		if(username == null) {
			return null;
		}
		else {
				if(isGameOwned(new User(username), Integer.parseInt(id))) {
					MapSqlParameterSource params = new MapSqlParameterSource("username", username);
					List<User> user = jdbc.query("select username, name, email from users where username = :username ", params, new RowMapper<User>(){
						
						@Override
						public User mapRow(ResultSet rs, int rowNum) throws SQLException {
							
							User user = new User();
							user.setUsername(rs.getString("username"));
							user.setName(rs.getString("name"));
							user.setEmail(rs.getString("email"));
							
							return user;
						}
						
					});
					return user.get(0);	
				}
				else {
					return null;
				}
				
			}
		}

	public void addGame(Game game) {
		//BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(game);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", game.getName());
		params.addValue("description", game.getDescription());
		params.addValue("addedOn", game.getDateAdded(), Types.DATE);
		params.addValue("releasedOn", game.getDateReleased(), Types.DATE);
		params.addValue("idDeveloper", game.getDeveloper().getId(), Types.INTEGER);
		params.addValue("idPublisher", game.getPublisher().getId(), Types.INTEGER);
		
		jdbc.update("insert into games (name,  addedOn, releasedOn, description, idDeveloper, idPublisher) values (:name, :addedOn, :releasedOn, :description, :idDeveloper, :idPublisher)", params);
	}
		
}
