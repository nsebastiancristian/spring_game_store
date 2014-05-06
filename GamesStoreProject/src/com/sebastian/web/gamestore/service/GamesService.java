package com.sebastian.web.gamestore.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.sebastian.web.gamestore.dao.Game;
import com.sebastian.web.gamestore.dao.GamesDao;
import com.sebastian.web.gamestore.dao.User;
import com.sebastian.web.helper.FileHandler;

@Service("gamesService")
public class GamesService  implements ServletContextAware {
	
	private GamesDao gamesDao;
	
	private ServletContext servletContext;
	
	public GamesService() {
		
	}

	@Autowired
	public void setGamesDao(GamesDao gamesDao) {
		this.gamesDao = gamesDao;
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	public List<Game> getCurrent() {
		
		return gamesDao.getAllGames();
	}

	public List<Game> getCurrent(User user) {
		
		return gamesDao.getAllGames(user);
	}

	public void buyGame(String id, String username) {
		gamesDao.buyGame(id, username);
	}

	public List<Game> getMyGames(User user) {
		return gamesDao.getMyGames(user);
	}

	public void addToWishlist(String id, String username) {
		gamesDao.addToWishlist(id, username);
	}
	
	public List<Game> getMyWishlistGames(String username) {
		return gamesDao.getMyWishlistGames(username);
	}

	public Game getGameDetails(String id, User user) {
		
		Game game = gamesDao.getGameDetails(id);
		
		String username = null;
		if(user != null) {
			username = user.getUsername();
		}
		
		User owner = gamesDao.getGameOwner(id, username);
		game.setOwner(owner);
		
		game.setWishlisted(gamesDao.isGameWishlisted(user, Integer.parseInt(id)));
		
				
		return game;
	}

	public void addGame(Game game) {
		gamesDao.addGame(game);
	}

	public String getGameName(int gameId) {
		
		return gamesDao.getGameName(gameId);
	}
	
	/**
	 * Saves an image for the home page of the game with the 'id' gameId
	 * @param image
	 * @param gameId
	 */
	public void saveImage(MultipartFile image, int gameId) {
		saveImage(image, gameId, 0);
	}
	
	public void saveImage(MultipartFile image, int gameId, int userId) {
		System.out.println("The value of the id of the game is " + gameId );
		
		//Construct the path to the image folder
		String webRootPath = servletContext.getRealPath("/");
		webRootPath = webRootPath + "resources\\images\\";
		
		String imageFileName = FileHandler.stripNameForbiddenChars(getGameName(gameId));	//this will actually be the name of the file folder
		String ImageFolderPath = webRootPath  + imageFileName;
		
		System.out.println(ImageFolderPath);
		
		//Make the folder if it not exists
		new File(ImageFolderPath).mkdir();

		try {
			if (!image.isEmpty()) {
				FileHandler.validateImage(image);
				
				//add the image file name to the database
				String finalImageName = gamesDao.addImageName(imageFileName, gameId, userId);
				
				FileHandler.saveImage(finalImageName, image, ImageFolderPath);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public List<String> getPicsForGame(int gameId) {
		String gameName = gamesDao.getGameName(gameId);
		gameName = FileHandler.stripNameForbiddenChars(gameName);
		
		//get a list with the file names of the photos
		List<String> pics = gamesDao.getPicsForGame(gameId);
		//reconstruct the strings with the file names by appending the path to them before the name of each file
		for(int i = 0; i < pics.size(); i++) {
			String fullPath = "/static/images/" + gameName + "/" + pics.get(i);
			pics.set(i, fullPath);
		}
		
		return pics;
	}
	
}
