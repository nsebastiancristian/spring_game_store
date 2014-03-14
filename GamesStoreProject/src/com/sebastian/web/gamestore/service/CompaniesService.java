package com.sebastian.web.gamestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sebastian.web.gamestore.dao.CompaniesDao;
import com.sebastian.web.gamestore.dao.Company;

@Component("companiesService")
public class CompaniesService {
	
	private CompaniesDao companiesDao;

	@Autowired
	public void setCompaniesDao(CompaniesDao companiesDao) {
		this.companiesDao = companiesDao;
	}
	
	public boolean createCompany(Company company) {
		return companiesDao.CreateCompany(company);
	}
	
}
