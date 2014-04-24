package com.sebastian.web.gamestore.controller.tmp;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sebastian.web.gamestore.dao.Company;
import com.sebastian.web.gamestore.dao.Game;
import com.sebastian.web.gamestore.service.CompaniesService;

@Controller
public class AdminTmpController {
	
	private CompaniesService companiesService;

	@Autowired
	public void setCompaniesService(CompaniesService companiesService) {
		this.companiesService = companiesService;
	}

	@InitBinder(value = "game")
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping("/addGameForm")
	public String showAddGameForm(Model model) {
		Game game = new Game();
		model.addAttribute("game", game);

		//================List of Developers=====================
		List<Company> developersList = companiesService.getDevelopers();
		
		//Map the list into a map that has the developer's id as key and the developer's name as value
		Map<String, String> developersMap = new HashMap();
		
		for (Company company : developersList) {
			developersMap.put(String.valueOf(company.getId()), company.getName());
		}
		
		model.addAttribute("developers", developersMap);
		//=======================================================
		
		return "addGame";
	}

	@RequestMapping("/doAddGameForm")
	public String doAddGameForm(@ModelAttribute("game") Game game,
			BindingResult result) {
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
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
