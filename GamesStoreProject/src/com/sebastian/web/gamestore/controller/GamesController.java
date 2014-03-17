package com.sebastian.web.gamestore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sebastian.web.gamestore.dao.Game;
import com.sebastian.web.gamestore.dao.User;
import com.sebastian.web.gamestore.service.GamesService;

@Controller
public class GamesController {
	private GamesService gamesService;
	
	@Autowired
	public void setGamesService(GamesService gamesService) {
		this.gamesService = gamesService;
	}
	
	@RequestMapping("/games")
	public String showGamesPage(Model model, Principal principal) {
		User user = null;
		
		if(principal != null) {
			String username = principal.getName();
			user = new User(username);
		}
		
		List<Game> games = gamesService.getCurrent(user);
		model.addAttribute("games", games);
		
		return "games";
	}
	
	@RequestMapping("/mygames")
	public String showMyGamesPage(Model model, Principal principal) {
		
		String username = principal.getName();
		User user = new User(username);
		
		List<Game> games = gamesService.getMyGames(user);
		model.addAttribute("games", games);
		
		return "mygames";
	}
	
	@RequestMapping("/buygame")
	public String buyGame(Model model, Principal principal, @RequestParam("id") String id) {
		
		String username = principal.getName();
		
		gamesService.buyGame(id, username);
		
		return "mygames";
	}
}
