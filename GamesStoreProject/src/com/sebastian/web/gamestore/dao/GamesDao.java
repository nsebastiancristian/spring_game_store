package com.sebastian.web.gamestore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
	 * 
	 * @param user
	 * @return
	 */
	public List<Game> getAllGames(final User user) {

		return jdbc
				.query("SELECT games.id, games.name, games.addedOn, games.releasedOn, companies.name, c.name from games left join companies on games.idDeveloper = companies.id left join companies c on games.idPublisher = c.id",
						new RowMapper<Game>() {

							@Override
							public Game mapRow(ResultSet rs, int rowNum)
									throws SQLException {

								Game game = new Game();
								game.setId(rs.getInt("games.id"));
								game.setName(rs.getString("games.name"));

								game.setDateAdded(rs.getDate("games.addedOn"));
								game.setDateReleased(rs
										.getDate("games.releasedOn"));

								Company developer = new Company();
								developer.setName(rs
										.getString("companies.name"));
								developer.setPublisher(false);
								game.setDeveloper(developer);

								Company publisher = new Company();
								publisher.setName(rs.getString("c.name"));
								publisher.setPublisher(true);

								game.setPublisher(publisher);

								// see if this game is owned by the current user
								if (isGameOwned(user, rs.getInt("games.id"))) {
									game.setOwner(user);
								} else {
									// the game is not owned so we can check if
									// it was wishlisted
									if (isGameWishlisted(user,
											rs.getInt("games.id"))) {
										game.setWishlisted(true);
									}
								}

								return game;
							}

						});
	}

	/**
	 * Returns a list of games with all the games from the database for the
	 * admin page
	 * 
	 * @return a list of games
	 */
	public List<Game> getAllGames() {
		return jdbc
				.query("SELECT games.id, games.name, games.addedOn, games.releasedOn, companies.name, c.name from games left join companies on games.idDeveloper = companies.id left join companies c on games.idPublisher = c.id",
						new RowMapper<Game>() {

							@Override
							public Game mapRow(ResultSet rs, int rowNum)
									throws SQLException {

								Game game = new Game();
								game.setId(rs.getInt("games.id"));
								game.setName(rs.getString("games.name"));

								game.setDateAdded(rs.getDate("games.addedOn"));
								game.setDateReleased(rs
										.getDate("games.releasedOn"));

								Company developer = new Company();
								developer.setName(rs
										.getString("companies.name"));
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
	 * Returns true if the user with the specified username owns the game with
	 * the specified id
	 * 
	 * @param user
	 *            - a User object having the current username
	 * @param id
	 *            - id of the game
	 * @return
	 */
	private boolean isGameOwned(User user, int id) {

		// check to see if there is an user
		if (user == null) {
			return false;
		}

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		params.addValue("username", user.getUsername());

		return jdbc
				.queryForInt(
						"select count(*) from ownedgames where games_id = :id and username = :username",
						params) >= 1;
	}

	public List<Game> getMyGames(final User user) {
		MapSqlParameterSource params = new MapSqlParameterSource("username",
				user.getUsername());
		return jdbc
				.query("SELECT ownedgames.games_id, games.name, games.id from ownedgames join games on ownedgames.games_id = games.id where ownedgames.username = :username",
						params, new RowMapper<Game>() {

							@Override
							public Game mapRow(ResultSet rs, int rowNum)
									throws SQLException {

								Game game = new Game();
								game.setId(rs.getInt("games.id"));
								game.setName(rs.getString("games.name"));

								game.setOwner(user);

								return game;
							}

						});
	}

	public void buyGame(String id, String username) {
		// insert into ownedgames table
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		params.addValue("username", username);

		jdbc.update(
				"insert into ownedgames (games_id, username) values (:id, :username)",
				params);

		// delete from wishlistgames table (if it was previously wishlisted)
		jdbc.update(
				"delete from wishlistgames where games_id = :id and username = :username",
				params);
	}

	public List<Game> getMyWishlistGames(String username) {
		MapSqlParameterSource params = new MapSqlParameterSource("username",
				username);
		return jdbc
				.query("SELECT wishlistgames.games_id, games.name, games.id from wishlistgames join games on wishlistgames.games_id = games.id where wishlistgames.username = :username",
						params, new RowMapper<Game>() {

							@Override
							public Game mapRow(ResultSet rs, int rowNum)
									throws SQLException {

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

		jdbc.update(
				"insert into wishlistgames (games_id, username) values (:id, :username)",
				params);
	}

	/**
	 * Returns true if the user with the specified username wishlisted the game
	 * with the specified id, if the function returns false the game is either
	 * owned by the respective user or the user hasn't wishlisted that game
	 * 
	 * @param user
	 *            - a User object having the current username
	 * @param id
	 *            - id of the game
	 * @return
	 */
	public boolean isGameWishlisted(User user, int id) {

		// check to see if there is an user
		if (user == null) {
			return false;
		}

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		params.addValue("username", user.getUsername());

		return jdbc
				.queryForInt(
						"select count(*) from wishlistgames where games_id = :id and username = :username",
						params) >= 1;
	}

	public Game getGameDetails(String id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		List<Game> game = jdbc
				.query("SELECT games.id, games.name, games.addedOn, games.releasedOn, games.description, companies.name, c.name from games left join companies on games.idDeveloper = companies.id left join companies c on games.idPublisher = c.id where games.id = :id",
						params, new RowMapper<Game>() {

							@Override
							public Game mapRow(ResultSet rs, int rowNum)
									throws SQLException {

								Game game = new Game();
								game.setId(rs.getInt("games.id"));
								game.setName(rs.getString("games.name"));
								game.setDateAdded(rs.getDate("games.addedOn"));
								game.setDateReleased(rs
										.getDate("games.releasedOn"));
								game.setDescription(rs
										.getString("games.description"));

								Company developer = new Company(rs
										.getString("companies.name"));
								Company publisher = new Company(rs
										.getString("c.name"));

								game.setDeveloper(developer);
								game.setPublisher(publisher);

								return game;
							}

						});

		return game.get(0);
	}

	/**
	 * Return the user bean of a owner of a game with the specified id.
	 * 
	 * @param id
	 *            : the id of the game
	 * @param username
	 *            : the username of the owner
	 * @return : Returns null if there is no owner of the specified game with
	 *         the specified username, otherwise it returns the owner's bean
	 */
	public User getGameOwner(String id, String username) {
		if (username == null) {
			return null;
		} else {
			if (isGameOwned(new User(username), Integer.parseInt(id))) {
				MapSqlParameterSource params = new MapSqlParameterSource(
						"username", username);
				List<User> user = jdbc
						.query("select username, name, email from users where username = :username ",
								params, new RowMapper<User>() {

									@Override
									public User mapRow(ResultSet rs, int rowNum)
											throws SQLException {

										User user = new User();
										user.setUsername(rs
												.getString("username"));
										user.setName(rs.getString("name"));
										user.setEmail(rs.getString("email"));

										return user;
									}

								});
				return user.get(0);
			} else {
				return null;
			}

		}
	}

	public void addGame(Game game) {
		// BeanPropertySqlParameterSource params = new
		// BeanPropertySqlParameterSource(game);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", game.getName());
		params.addValue("description", game.getDescription());
		params.addValue("addedOn", game.getDateAdded(), Types.DATE);
		params.addValue("releasedOn", game.getDateReleased(), Types.DATE);
		params.addValue("idDeveloper", game.getDeveloper().getId(),
				Types.INTEGER);
		params.addValue("idPublisher", game.getPublisher().getId(),
				Types.INTEGER);
		params.addValue("price", game.getPrice(), Types.FLOAT);

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update(
				"insert into games (name,  addedOn, releasedOn, description, idDeveloper, idPublisher, price) values (:name, NOW(), :releasedOn, :description, :idDeveloper, :idPublisher, :price)",
				params, keyHolder, new String[] { "id" });

		// get the id of the newly inserted row
		int id = keyHolder.getKey().intValue();

		// TABLE
		// games_genres----------------------------------------------------
		// Update the games_genres table
		for (String genre : game.getGenres()) {
			params = new MapSqlParameterSource();
			params.addValue("id", id, Types.INTEGER);
			params.addValue("genre", genre);
			// insert into the genre table the various genres of the game
			jdbc.update(
					"insert into games_genres (id, genre) values (:id, :genre)",
					params);
		}

		// TABLE
		// games_platforms----------------------------------------------------
		// Update the games_platforms table
		for (String platform : game.getPlatforms()) {
			params = new MapSqlParameterSource();
			params.addValue("gameId", id, Types.INTEGER);
			params.addValue("platform", platform);

			// insert into the platform table the various platforms on which the
			// game runs
			jdbc.update(
					"insert into games_platforms (gameId, platform) values (:gameId, :platform)",
					params);
		}

		// TABLE
		// games_themes-------------------------------------------------------
		// Update the games_themes table
		for (Theme theme : game.getThemes()) {
			params = new MapSqlParameterSource();
			params.addValue("gameId", id, Types.INTEGER);
			params.addValue("themeId", theme.getId(), Types.INTEGER);

			// insert into the games_themes table the id of the game and id's of
			// the various themes
			jdbc.update(
					"insert into games_themes (gameId, themeId) values (:gameId, :themeId)",
					params);
		}

		// TABLE
		// games_tags-------------------------------------------------------
		// Update the games_tags table
		for (Tag tag : game.getTags()) {
			params = new MapSqlParameterSource();
			params.addValue("gameId", id, Types.INTEGER);
			params.addValue("tagId", tag.getId(), Types.INTEGER);

			// insert into the games_themes table the id of the game and id's of
			// the various themes
			jdbc.update(
					"insert into games_tags (gameId, tagId) values (:gameId, :tagId)",
					params);
		}

	}

	public String getGameName(int gameId) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", gameId);
		String name = (String) jdbc.queryForObject(
				"select name from games where id = :id", params, String.class);

		return name;
	}

	/**
	 * Adds an image filename to the database. The difference between this
	 * version of the method and the 3 paramether version is that this can be
	 * called if we want to save a picture that will be featured on the game's
	 * home page
	 * 
	 * @param name
	 *            - The name of the game that will be the first part of the file
	 *            name
	 * @param gameId
	 *            - The id of the game which the picture contains
	 * 
	 * @return - A string that contains the complete filename of the pic that
	 *         was just inserted to the database (the complete name consists of
	 *         the name of the game stripped of forbidden chars and suffixed by
	 *         '_x' where x is the id of the row that was just inserted
	 */
	public String addImageName(String name, int gameId) {
		return addImageName(name, gameId, 0);
	}

	/**
	 * Adds an image filename to the database
	 * 
	 * @param name
	 *            - The name of the game that will be the first part of the file
	 *            name
	 * @param gameId
	 *            - The id of the game which the picture contains
	 * @param userId
	 *            - The id of the user that posted the pic. If the pic will be
	 *            on the home page of the game then the userId is 0
	 * 
	 * @return - A string that contains the complete filename of the pic that
	 *         was just inserted to the database (the complete name consists of
	 *         the name of the game stripped of forbidden chars and suffixed by
	 *         '_x' where x is the id of the row that was just inserted
	 */
	public String addImageName(String name, int gameId, int userId) {
		MapSqlParameterSource params = new MapSqlParameterSource("name", name);
		params.addValue("gameId", gameId);
		params.addValue("userId", userId);
		params.addValue("dateAdded", new java.sql.Date(new Date().getTime()),
				Types.TIMESTAMP);

		// we need the id of the newly inserted row so we use GeneratedKeyHolder
		// for this
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(
				"insert into pics (filename, gameId, userId, dateAdded) values (:name, :gameId, :userId, :dateAdded)",
				params, keyHolder, new String[] { "id" });

		// int id = jdbc.queryForObject( "select last_insert_id()", new
		// MapSqlParameterSource(), Integer.class );

		// construct the name of the file based on the name of the game combined
		// with the id
		int id = keyHolder.getKey().intValue();
		name = name + "_" + String.valueOf(id);

		// update the database with the constructed filename
		params = new MapSqlParameterSource("name", name);
		params.addValue("id", id);
		jdbc.update("update pics set filename=:name where id=:id", params);

		return name + ".jpg";
	}

	public List<String> getPicsForGame(int gameId) {
		return getPicsForGame(gameId, false);
	}

	/**
	 * Get a list of filenames for the game with the given id, ordered asc or
	 * desc by the date when they were added
	 * 
	 * @param gameId
	 *            - Id of the game
	 * @param ascDateAdded
	 *            - If it's true then the list will be ordered based on the date
	 *            the pictures were added in ASC order, if it is false the list
	 *            will be ordered in DESC order
	 * @return - List of strings that represent the filenames of the pics for
	 *         that game
	 */
	public List<String> getPicsForGame(int gameId, boolean ascDateAdded) {
		final List<String> pictureNames = new ArrayList<String>();
		MapSqlParameterSource params = new MapSqlParameterSource("gameId",
				gameId);

		String sql = "select * from pics where gameId=:gameId order by dateAdded "
				+ (ascDateAdded == true ? "ASC" : "DESC");
		jdbc.query(sql, params, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String filename = rs.getString("filename") + ".jpg";
				pictureNames.add(filename);
			}
		});

		return pictureNames;
	}

	public List<String> getGenres() {
		final List<String> genres = new ArrayList<String>();

		jdbc.query("select * from genres", new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				genres.add(rs.getString("genre"));
			}
		});
		return genres;
	}

	public List<Theme> getThemes() {
		final List<Theme> themes = new ArrayList();

		jdbc.query("select * from themes", new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Theme theme = new Theme();
				theme.setId(Integer.parseInt(rs.getString("id")));
				theme.setTheme(rs.getString("theme"));

				themes.add(theme);
			}
		});

		return themes;
	}

	public Theme getThemeFromId(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("select * from themes where id=:id", params,
				new RowMapper<Theme>() {

					public Theme mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Theme theme = new Theme();

						theme.setId(rs.getInt("id"));
						theme.setTheme(rs.getString("theme"));

						return theme;
					}
				});
	}

	public List<Tag> getTags() {

		final List<Tag> tags = new ArrayList();

		jdbc.query("select * from tags", new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Tag tag = new Tag();
				tag.setId(Integer.parseInt(rs.getString("id")));
				tag.setTag(rs.getString("tag"));

				tags.add(tag);
			}
		});

		return tags;
	}

	public Tag getTagFromId(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("select * from tags where id=:id", params,
				new RowMapper<Tag>() {

					public Tag mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Tag tag = new Tag();

						tag.setId(rs.getInt("id"));
						tag.setTag(rs.getString("tag"));

						return tag;
					}
				});
	}

	public boolean addTheme(Theme theme) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("theme", theme.getTheme());

		System.out.println("We are inside the game DAO trying to add a theme");
		System.out.println(theme);

		try {
			jdbc.queryForObject("select * from themes where theme=:theme",
					params, new RowMapper<Theme>() {

						public Theme mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Theme theme = new Theme();

							theme.setId(rs.getInt("id"));
							theme.setTheme(rs.getString("theme"));

							return theme;
						}
					});
			
		} catch (EmptyResultDataAccessException e) {
			// you can add the new theme

			params = new MapSqlParameterSource();
			params.addValue("theme", theme.getTheme());

			jdbc.update("insert into themes (theme) values (:theme)", params);

			return true;
			
		}

		// there is allready a theme inside the database like the one  mentioned
		return false;
		
	}

}
