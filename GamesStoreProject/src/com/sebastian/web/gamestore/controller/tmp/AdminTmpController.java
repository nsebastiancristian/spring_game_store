package com.sebastian.web.gamestore.controller.tmp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sebastian.web.gamestore.dao.Custom;
import com.sebastian.web.gamestore.dao.Game;

@Controller
public class AdminTmpController {

	@RequestMapping("/addGameForm")
	public String showAddGameForm(Model model) {
		Game game = new Game();
		model.addAttribute("game", game);
		
		
		return "addGame";
	}

	@RequestMapping("/doAddGameForm")
	public String doAddGameForm( Custom game, BindingResult result) {
		
		System.out.println("Name:" + game.getName());
		System.out.println("Description:" + game.getDescription());
		//System.out.println("Added On:" + game.getDateAdded());
		return "doAddGame";
	}
}
