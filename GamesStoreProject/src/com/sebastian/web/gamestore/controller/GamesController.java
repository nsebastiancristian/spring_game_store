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
		
		doShowMyGamesPageRoutine(model, user);
		
		return "mygames";
	}
	
	@RequestMapping("/buygame")
	public String buyGame(Model model, Principal principal, @RequestParam("id") String id) {
		
		String username = principal.getName();
		
		gamesService.buyGame(id, username);
		
		User user = new User(username);
		doShowMyGamesPageRoutine(model, user);
		
		return "mygames";
	}
	
	@RequestMapping("/addtowishlist")
	public String addToWishlist(Model model, Principal principal, @RequestParam("id") String id) {
		
		String username = principal.getName();
		
		gamesService.addToWishlist(id, username);
		
		doShowMyWishlistPageRoutine(model, username);
		return "mywishlist";
	}
	
	@RequestMapping("/mywishlist")
	public String showMyWishlistPage(Model model, Principal principal) {
		String username = principal.getName();
				
		doShowMyWishlistPageRoutine(model, username);
		return "mywishlist";
	}
	
	@RequestMapping("/game")
	public String showGamePage(Model model, Principal principal,  @RequestParam("id") String id) {
		User user = null;
		if(principal != null) {
			String username = principal.getName();
			user = new User(username);
		}
		Game game = gamesService.getGameDetails(id, user);
		model.addAttribute("game", game);
		
		return "game";
	}
	
	/**--------------------------------------------------------------------------------------------------**
	 * -------------------------ROUTINES ---------------------------------------------------------------- *
	 * -------------------------------------------------------------------------------------------------- */
	
	public void doShowMyWishlistPageRoutine(Model model, String username) {
		List<Game> wishlist = gamesService.getMyWishlistGames(username);
		model.addAttribute("wishlist", wishlist);
	}
	
	public void doShowMyGamesPageRoutine(Model model, User user) {
		List<Game> games = gamesService.getMyGames(user);
		model.addAttribute("games", games);
	}
}
