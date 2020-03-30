package com.cts.designpattern.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cts.designpattern.dao.FileManipulationDAO;
import com.cts.designpattern.service.FileManipulationService;

@RunWith(MockitoJUnitRunner.class)
public class FileManipulationServiceTest {
	
	Logger log = LoggerFactory.getLogger(FileManipulationServiceTest.class);
	
	String fileName = "TabletDetails.csv";
	Path path = Paths.get("dir", fileName);
	
	@InjectMocks
	FileManipulationService service;
	
	@Mock
	FileManipulationDAO dao;
	
	@Test
	public void searchValidTest() {
		when(dao.search(fileName, "../designpattern/dir/")).thenReturn(Optional.of(path));
		Path output=service.search(fileName, "../designpattern/dir/");
		log.debug("searchValidTest - data fetched from service : {}",output);
		assertEquals(path, output);
	}
	
	@Test
	public void searchInValidTest() {
		when(dao.search(fileName, "../designpattern/dir/")).thenReturn(Optional.empty());
		Path output=service.search(fileName, "../designpattern/dir/");
		log.debug("searchInValidTest - data fetched from service : {}",output);
		assertEquals(null, service.search(fileName, "../designpattern/dir/"));
	}
	
	@Test
	public void fetchAllfilesValidTest() {
		Path file1 = Paths.get("src/com/cts/designpattern/service", "FileManipulationService.java");
		Path file2 = Paths.get("src/com/cts/designpattern/Application", "Main.java");
		Path file3 = Paths.get("src/com/cts/designpattern/dao", "FileManipulationDAO.java");
		List<Path> pathList=new ArrayList<>();
		pathList.add(file1);
		pathList.add(file2);
		pathList.add(file3);
		when(dao.fetchAllfiles()).thenReturn(pathList);
		List<Path> outputList=service.fetchAllfiles();
		log.debug("fetchAllfilesValidTest - data fetched from service : {}",outputList);
		assertEquals(3, outputList.size());
	}
	
	@Test
	public void fetchAllfilesInValidTest() {
		when(dao.fetchAllfiles()).thenReturn(null);
		List<Path> outputList=service.fetchAllfiles();
		log.debug("fetchAllfilesInValidTest - data fetched from service : {}",outputList);
		assertEquals(null, outputList);
	}
	
	@Test
	public void getExpiredTabletsValidTest() {
		Map<String, String> expiredTablets=new HashMap<>();
		expiredTablets.put("Amoxicillin", "03/02/2010");
		when(dao.getExpiredTablets(path,"Apotex Corp")).thenReturn(expiredTablets);
		Map<String, String> output=service.getExpiredTablets(path,"Apotex Corp");
		log.debug("getExpiredTabletsValidTest - data fetched from service : {}",output);
		assertEquals("03/02/2010", output.get("Amoxicillin"));
	}
	
	@Test
	public void getExpiredTabletsInValidTest() {
		when(dao.getExpiredTablets(path,"Apotex Corp")).thenReturn(null);
		Map<String, String> output=service.getExpiredTablets(path,"Apotex Corp");
		log.debug("getExpiredTabletsValidTest - data fetched from service : {}",output);
		assertEquals(null, output);
	}

}
