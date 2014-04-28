package com.sebastian.web.gamestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastian.web.gamestore.dao.User;
import com.sebastian.web.gamestore.dao.UsersDao;

@Service("usersService")
public class UsersService {

	@Autowired
	private UsersDao usersDao;

	public void createUser(User user) {
		usersDao.createUser(user);
	}
}
