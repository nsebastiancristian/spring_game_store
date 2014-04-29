package com.sebastian.web.gamestore.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String showHome(Model model, Principal principal) {
		String username = "user";
		if(principal != null)
		 username = principal.getName();
		
		model.addAttribute("username", username);
		
		return "home";
	}
	
	@RequestMapping("/userPage")
	public String showUserPage() {
		return "userPage";
	}
	
	@RequestMapping("/adminHomepage")
	public String showAdminPage() {
		return "adminHomepage";
	}
}
