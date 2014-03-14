package com.sebastian.web.gamestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String showHome(Model model) {
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
