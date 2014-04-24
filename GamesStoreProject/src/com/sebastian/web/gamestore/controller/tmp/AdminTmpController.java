package com.sebastian.web.gamestore.controller.tmp;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sebastian.web.gamestore.dao.Game;

@Controller
public class AdminTmpController {

	@InitBinder(value = "game")
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	@RequestMapping("/addGameForm")
	public String showAddGameForm(Model model) {
		Game game = new Game();
		model.addAttribute("game", game);
		
		
		return "addGame";
	}

	@RequestMapping("/doAddGameForm")
	public String doAddGameForm(@ModelAttribute("game") Game game, BindingResult result) {
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error: errors) {
				System.out.println(error);
			}
		}
		
		System.out.println("Name:" + game.getName());
		System.out.println("Description:" + game.getDescription());
		System.out.println("Added On:" + game.getDateAdded());
		System.out.println("Released On:" + game.getDateReleased());
		return "doAddGame";
	}
}
