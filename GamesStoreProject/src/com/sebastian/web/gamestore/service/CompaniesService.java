package com.sebastian.web.gamestore.service;

import java.util.List;

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
	
	public List<Company> getDevelopers() {
		
		return companiesDao.getDevelopers();
	}

	public List<Company> getPublishers() {

		return companiesDao.getPublishers();
	}

	public Company getCompanyById(Integer id) {
	
		return companiesDao.getCompanyById(id);
	}
	
}
