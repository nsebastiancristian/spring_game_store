package com.sebastian.web.helper;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("fileHandler")
public class FileHandler {
	
	public FileHandler() {
		System.out.println("FileHandler has been initialized");
	}
	
	public static void validateImage(MultipartFile image) {
		System.out.println(image.getContentType());
		if(!image.getContentType().equals("image/pjpeg") && !image.getContentType().equals("image/jpeg") && !image.getContentType().equals("image/gif")) {
			throw new RuntimeException("Only JPG and gif images accepted");
		}
	}
	
	public static void saveImage(String filename, MultipartFile image, String webRootPath) throws Exception{
		try {
			File file = new File(webRootPath + "/" + filename);
			
			if (!file.exists()) {
	            file.createNewFile();
	        }
			
			FileUtils.writeByteArrayToFile(file, image.getBytes());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception("Unable to save image", e);
		}
	}
	
	/**
	 * This method converts a file/folder name into an accepted name for the Windows fyle handling system. It does this by stripping all the unwanted chars
	 * from the name and replacing them with spaces
	 * @param name - The name of the file/folder
	 * @return - A valid string containing the transformed input string
	 */
	public static String stripNameForbiddenChars(String name) {
		char[] forbiddenChars = {'\\', '/', ':', '*', '?', '"', '<', '>', '|'};
		String returnName = name;
		
		for(char replaced: forbiddenChars) {
			returnName = returnName.replace(replaced, ' ');
		}
		
		return returnName;
	}
}
