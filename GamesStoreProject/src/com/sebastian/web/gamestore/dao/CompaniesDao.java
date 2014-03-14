package com.sebastian.web.gamestore.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("companiesDao")
public class CompaniesDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	public boolean CreateCompany(Company company) {
		if(exists(company.getName())) {
			throw new DuplicateKeyException("A username with the same name allready exists in the database");
		}
				
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(company);		
		return jdbc.update("insert into companies (name) values (:name)", params) == 1;		
	}
		
	private boolean exists(String name) {
		return jdbc.queryForObject("select count(*) from companies where name=:name", new MapSqlParameterSource("name", name), Integer.class) >= 1;		
	}
}
