package com.sebastian.web.gamestore.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.sebastian.web.gamestore.dao.Company;
import com.sebastian.web.gamestore.dao.Game;
import com.sebastian.web.gamestore.service.CompaniesService;
import com.sebastian.web.gamestore.service.GamesService;
import com.sebastian.web.helper.FileHandler;

@Controller
public class AdminController implements ServletContextAware {

	private CompaniesService companiesService;

	private ServletContext servletContext;
	
	private GamesService gamesService;

	@Autowired
	public void setCompaniesService(CompaniesService companiesService) {
		this.companiesService = companiesService;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Autowired
	public void setGamesService(GamesService gamesService) {
		this.gamesService = gamesService;
	}

	@RequestMapping(value = "/adminAddDeveloperForm", method = RequestMethod.GET)
	public String adminAddDeveloperForm() {
		return "adminAddDeveloperForm";
	}

	@RequestMapping(value = "/adminAddDeveloperForm", method = RequestMethod.POST)
	public String doAdminAddDeveloperForm(Model model, Company company) {

		try {
			// insert a company into the database
			companiesService.createCompany(company);

		} catch (DuplicateKeyException e) {
			System.out.println(e.getMessage());
			return "adminAddDeveloperForm";
		}

		return "doAdminAddDeveloperForm";
	}
	
	@RequestMapping("/adminShowGames")
	public String adminShowGames(Model model) {
		List<Game> games = gamesService.getCurrent();
		model.addAttribute("games", games);
		
		return "adminShowGames";
	}

	@RequestMapping(value = "/adminAddPictureForm", method = RequestMethod.GET)
	public String adminAddPictureForm(Model model, @RequestParam(value = "gameId") int gameId) {
		List<String> pics = gamesService.getPicsForGame(gameId);
		
		model.addAttribute("pics", pics);
		
		return "adminAddPictureForm";
	}

	@RequestMapping(value = "/adminAddPictureForm", method = RequestMethod.POST)
	public String doAdminAddPictureForm(Model model,	@RequestParam(value = "image") MultipartFile[] images, @RequestParam(value = "gameId") int gameId) {
		//save the images to the database (and hard drive) first
		for (MultipartFile image : images) {
			gamesService.saveImage(image, gameId);
		}
		
		//than get the pics 
		List<String> pics = gamesService.getPicsForGame(gameId);
		
		model.addAttribute("pics", pics);
		

		return "adminAddPictureForm";
	}
	
}
