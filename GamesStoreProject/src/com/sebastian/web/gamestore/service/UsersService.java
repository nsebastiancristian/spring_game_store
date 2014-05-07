package com.sebastian.web.gamestore.service;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.sebastian.web.gamestore.dao.User;
import com.sebastian.web.gamestore.dao.UsersDao;
import com.sebastian.web.helper.FileHandler;

@Service("usersService")
public class UsersService implements ServletContextAware {

	private ServletContext servletContext;
	
	@Autowired
	private UsersDao usersDao;

	public void createUser(User user, MultipartFile image) {
		usersDao.createUser(user);
		
		//if we have an avatar image file then save it 
		if(image != null) {
			saveImage(image, user.getUsername());
		}
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	/**
	 * Save the avatar image to the hard drive
	 * @param image - The actual image as a MultipartFile object
	 * @param username - The username of the user that will be used to name the filename of the avatar pic on the hard drive
	 */
	public void saveImage(MultipartFile image, String username) {
		
		//Construct the path to the image folder
		String webRootPath = servletContext.getRealPath("/");
		webRootPath = webRootPath + "resources\\images\\avatars\\";
		
		String imageFileName = FileHandler.stripNameForbiddenChars(username);	//this will actually be the name of the file folder (based on the user's username)
		//String ImageFolderPath = webRootPath  + imageFileName;
		
		System.out.println(webRootPath);
		
		//Make the folder if it not exists
		new File(webRootPath).mkdir();

		try {
			if (!image.isEmpty()) {
				FileHandler.validateImage(image);
				
				//add the image file name to the database
				String finalImageName = usersDao.addImageName(imageFileName, username);
				
				//save the file on the hard drive
				FileHandler.saveImage(finalImageName, image, webRootPath);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

