package com.cts.designpattern.service;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cts.designpattern.dao.FileManipulationDAO;

public class FileManipulationService {

	Logger log = LoggerFactory.getLogger(FileManipulationService.class);

	FileManipulationDAO dao = new FileManipulationDAO();
	
	/**
	 * This method should return a Map with key as Tablet name and value as expire
	 * date of tablets for those tablets, which are already expired and are from a
	 * given manufacturer
	 */
	public Map<String, String> getExpiredTablets(Path path, String manufacturer){
		return dao.getExpiredTablets(path, manufacturer);
	}
	
	/**
	 * method to list all the files ending with .java in the current project's src
	 * folder and its subfolders
	 */
	public List<Path> fetchAllfiles() {
		return dao.fetchAllfiles();
	}
	
	/**
	 * method which takes two String parameters. The first parameter is the filename
	 * to be searched and the second parameter is the absolute path of the directory
	 * to be searched
	 */
	public Path search(String fileName, String dir) {
		Optional<Path> validate = dao.search(fileName, dir);
		if (validate.isPresent()) {
			log.info("File present in given directory");
			return validate.get();
		}
		log.info("File not present in given directory");
		return null;
	}

	
}
