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
	
	@RequestMapping("/userpage")
	public String showUserPage() {
		return "userpage";
	}
	
	@RequestMapping("/adminpage")
	public String showAdminPage() {
		return "adminpage";
	}
}
