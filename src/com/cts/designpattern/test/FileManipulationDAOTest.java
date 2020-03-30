package com.cts.designpattern.test;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cts.designpattern.dao.FileManipulationDAO;

@RunWith(MockitoJUnitRunner.class)
public class FileManipulationDAOTest {
	
	Logger log = LoggerFactory.getLogger(FileManipulationDAOTest.class);
	
	String fileName = "TabletDetails.csv";
	Path path = Paths.get("dir", fileName);
	
	@InjectMocks
	FileManipulationDAO dao;
	
	@Test
	public void searchTest() {
		Optional<Path> output=dao.search(fileName, "../designpattern/dir/");
		log.debug("searchTest - data fetched from DAO : {}",output);
		assertEquals("../designpattern/dir/TabletDetails.csv", output.get().toString());
	}
	
	@Test
	public void fetchAllfilesTest() {
		List<Path> outputList=dao.fetchAllfiles();
		log.debug("fetchAllfilesTest - data fetched from DAO : {}",outputList);
		assertEquals(12, outputList.size());
	}
	
	
	@Test
	public void getExpiredTabletsTest() {
		Map<String, String> output=dao.getExpiredTablets(path,"Apotex Corp");
		log.debug("getExpiredTabletsTest - data fetched from DAO : {}",output);
		assertEquals("03/02/2010", output.get("Amoxicillin"));
	}
	

}
