package com.sebastian.web.gamestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastian.web.gamestore.dao.Game;
import com.sebastian.web.gamestore.dao.GamesDao;

@Service("gamesService")
public class GamesService {
	
	private GamesDao gamesDao;
	
	public GamesService() {
		
	}

	@Autowired
	public void setGamesDao(GamesDao gamesDao) {
		this.gamesDao = gamesDao;
	}
	
	public List<Game> getCurrent() {
		
		return gamesDao.getAllGames();
	}
}
