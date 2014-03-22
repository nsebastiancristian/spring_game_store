package com.sebastian.web.helper;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("fileHandler")
public class FileHandler {
	
	public FileHandler() {
		System.out.println("FileHandler has been initialized");
	}

	@Autowired
	private static ServletContext context;
	
	public static void validateImage(MultipartFile image) {
		if(context != null) {
			System.out.println("The context has been initialized");
		}
		else {
			System.out.println("The context has not been initialized");
		}
	}
	
	public static void saveImage(String filename, MultipartFile image) throws Exception{
		
	}
}
