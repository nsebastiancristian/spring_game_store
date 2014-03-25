package com.sebastian.web.gamestore.controller;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.sebastian.web.gamestore.dao.Company;
import com.sebastian.web.gamestore.service.CompaniesService;
import com.sebastian.web.helper.FileHandler;

@Controller
public class AdminController implements ServletContextAware {

	private CompaniesService companiesService;

	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

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
			// insert a company into the database
			companiesService.createCompany(company);

		} catch (DuplicateKeyException e) {
			System.out.println(e.getMessage());
			return "createdeveloper";
		}

		return "docreatedeveloper";
	}

	@RequestMapping("/addPicture")
	public String showAddPicture() {

		return "addPictureForm";
	}

	@RequestMapping("/doAddPicture")
	public String doAddPicture(Model model,	@RequestParam(value = "image") MultipartFile image) {

		String webRootPath = servletContext.getRealPath("/");
		webRootPath = webRootPath + "resources\\images\\";
		String ImageFolderPath = webRootPath + "My Image Folder";
		new File(ImageFolderPath).mkdir();

		try {
			if (!image.isEmpty()) {
				FileHandler.validateImage(image);
				FileHandler.saveImage("myupload.jpg", image, webRootPath);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "addPictureForm";
	}
}
