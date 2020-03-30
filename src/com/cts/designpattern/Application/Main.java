package com.cts.designpattern.Application;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cts.designpattern.service.FileManipulationService;

/**
 * Implement DAO pattern for files exercise from Java-8
 */
public class Main {
	public static void main(String[] args) {
		
		String fileName = "TabletDetails.csv";
		Path path = Paths.get("dir", fileName);
		Logger log = LoggerFactory.getLogger(Main.class);
		FileManipulationService service  = new FileManipulationService();
		
		log.debug("getExpiredTablets: {}", service.getExpiredTablets(path, "Apotex Corp"));
		log.debug("fetchAllfiles: {}", service.fetchAllfiles());
		log.debug("Full path is : {}",service.search(fileName, "../designpattern/dir/"));
	}

}
