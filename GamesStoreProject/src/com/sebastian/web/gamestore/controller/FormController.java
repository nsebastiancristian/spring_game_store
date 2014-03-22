package com.sebastian.web.gamestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sebastian.web.helper.FileHandler;

@Controller
public class FormController {
	
	@RequestMapping("/addPicture")
	public String showAddPicture(Model model, @RequestParam(value="image") MultipartFile image) {
		
		try {
			if (!image.isEmpty()) {
				System.out.println("We are validating the image");
				FileHandler.validateImage(image);
			}
		} catch (Exception e) {
			System.out.println("An exception occured when uploading the file");
		}
		
		return "addPictureForm";
	}
}
	
