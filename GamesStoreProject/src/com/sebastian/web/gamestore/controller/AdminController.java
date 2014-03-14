package com.sebastian.web.gamestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sebastian.web.gamestore.dao.Company;
import com.sebastian.web.gamestore.service.CompaniesService;

@Controller
public class AdminController {

	private CompaniesService companiesService;
	
	@Autowired
	public void setCompaniesService(CompaniesService companiesService) {
		this.companiesService = companiesService;
	}

	@RequestMapping("/create_developer")
	public String createDeveloper() {
		return "createdeveloper";
	}
	
	@RequestMapping("/do_create_developer")
	public String doCreateDeveloper(Model model, Company company) {
		
		try {
			//insert a company into the database
			companiesService.createCompany(company);
			
		} catch (DuplicateKeyException e) {
			System.out.println(e.getMessage());
			return "createdeveloper";
		}
		
		
		
		return "docreatedeveloper";
	}
}
