package com.sebastian.web.gamestore.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.sebastian.web.helper.FileHandler;

@Controller
public class FormController implements ServletContextAware {

	private ServletContext servletContext;

	@RequestMapping("/addPicture")
	public String showAddPicture(HttpServletRequest request) {
		String webRootPath = servletContext.getRealPath("/"); 
		webRootPath = webRootPath + "resources\\images\\";
		System.out.println(webRootPath);
		
		return "addPictureForm";
	}

	@RequestMapping("/doAddPicture")
	public String doAddPicture(HttpServletRequest request, Model model,	@RequestParam(value = "image") MultipartFile image) {
		
		String webRootPath = servletContext.getRealPath("/"); 
		webRootPath = webRootPath + "WebContent\\resources\\images\\";
		
		
		try {
			if (!image.isEmpty()) {
				FileHandler.validateImage(image);
				FileHandler.saveImage("myupload.jpg", image, webRootPath);
			}
		} catch (Exception e) {
			System.out.println("An exception occured when uploading the file");
			System.out.println(e.getMessage());
		}

		return "addPictureForm";
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
