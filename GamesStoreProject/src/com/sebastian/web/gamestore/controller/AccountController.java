package com.sebastian.web.gamestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {

	@RequestMapping("loggedout")
	public String showLoggedOut() {
		return "loggedout";
	}
	
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
}
