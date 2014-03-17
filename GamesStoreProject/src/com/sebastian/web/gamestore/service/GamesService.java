package com.sebastian.web.gamestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastian.web.gamestore.dao.Game;
import com.sebastian.web.gamestore.dao.GamesDao;
import com.sebastian.web.gamestore.dao.User;

@Service("gamesService")
public class GamesService {
	
	private GamesDao gamesDao;
	
	public GamesService() {
		
	}

	@Autowired
	public void setGamesDao(GamesDao gamesDao) {
		this.gamesDao = gamesDao;
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
}
