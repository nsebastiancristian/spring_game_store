package com.sebastian.web.gamestore.controller.tmp;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMethod;

import com.sebastian.web.gamestore.dao.Company;
import com.sebastian.web.gamestore.dao.Game;
import com.sebastian.web.gamestore.dao.User;
import com.sebastian.web.gamestore.service.CompaniesService;
import com.sebastian.web.gamestore.service.GamesService;
import com.sebastian.web.gamestore.service.UsersService;

@Controller
public class AdminTmpController {
	
	private CompaniesService companiesService;
	private GamesService gamesService;
	@Autowired
	private UsersService usersService;

	@Autowired
	public void setCompaniesService(CompaniesService companiesService) {
		this.companiesService = companiesService;
	}

	@Autowired
	public void setGamesService(GamesService gamesService) {
		this.gamesService = gamesService;
	}

	@InitBinder(value = "game")
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Company.class, new PropertyEditorSupport() {

			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				Company company = companiesService.getCompanyById(Integer.valueOf(text));
				setValue(company);
			}
			
		});
	}

	@RequestMapping(value = "/adminAddGameForm", method = RequestMethod.GET)
	public String adminAddGameForm(Model model) {
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

		//================List of Developers=====================
		List<Company> publishersList = companiesService.getPublishers();
		
		//Map the list into a map that has the developer's id as key and the developer's name as value
		Map<String, String> publishersMap = new HashMap();
		
		for (Company company : publishersList) {
			System.out.println(company.getName());
			publishersMap.put(String.valueOf(company.getId()), company.getName());
		}
		
		model.addAttribute("publishers", publishersMap);
		//=======================================================
		
		return "adminAddGameForm";
	}

	@RequestMapping(value = "/adminAddGameForm", method = RequestMethod.POST)
	public String doAdminAddGameForm(@ModelAttribute("game") Game game, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error);
			}
		}
		
		//insert the game into the database
		gamesService.addGame(game);
		model.addAttribute("success", true);

		System.out.println("Name:" + game.getName());
		System.out.println("Description:" + game.getDescription());
		System.out.println("Added On:" + game.getDateAdded());
		System.out.println("Released On:" + game.getDateReleased());
		System.out.println("Developer:" + game.getDeveloper());
		System.out.println("Publisher:" + game.getPublisher());
		
		return "doAdminAddGame";
	}
	
	@RequestMapping(value = "/adminAddUserForm", method = RequestMethod.GET)
	public String adminAddUserForm(Model model) {
		model.addAttribute("user", new User());
		
		return "adminAddUserForm"; 
	}
	
	@RequestMapping(value = "/adminAddUserForm", method = RequestMethod.POST)
	public String doAdminAddUserForm(@ModelAttribute("user") User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> errors   = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error);
			}
		}
		
		System.out.println(user);
		usersService.createUser(user);
		
		return "doAdminAddUserForm";
	}
}
