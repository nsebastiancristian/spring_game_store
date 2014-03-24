package com.sebastian.web.gamestore.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FormController implements ServletContextAware {

	private ServletContext servletContext;

	@RequestMapping("/addPicture")
	public String showAddPicture(HttpServletRequest request) {
		String path = request.getRequestURL().toString();
		System.out.println(path);

		return "addPictureForm";
	}

	@RequestMapping("/doAddPicture")
	public String doAddPicture(Model model,
			@RequestParam(value = "image") MultipartFile image) {

		try {
			if (!image.isEmpty()) {
				System.out.println("We are validating the image");
				// FileHandler.validateImage(image);
			}
		} catch (Exception e) {
			System.out.println("An exception occured when uploading the file");
		}

		return "addPictureForm";
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
