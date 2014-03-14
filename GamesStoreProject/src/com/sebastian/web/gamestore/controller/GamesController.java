package com.sebastian.web.gamestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sebastian.web.gamestore.dao.Game;
import com.sebastian.web.gamestore.service.GamesService;

@Controller
public class GamesController {
	private GamesService gamesService;
	
	@Autowired
	public void setGamesService(GamesService gamesService) {
		this.gamesService = gamesService;
	}
	
	@RequestMapping("/games")
	public String showGamesPage(Model model) {
		
		List<Game> games = gamesService.getCurrent();
		model.addAttribute("games", games);
		
		return "games";
	}
}
