package com.cts.designpattern.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.cts.designpattern.model.Tablet;

public class FileManipulationDAO {
	
	/**
	 * This method should return a Map with key as Tablet name and value as expire
	 * date of tablets for those tablets, which are already expired and are from a
	 * given manufacturer
	 */
	public Map<String, String> getExpiredTablets(Path path, String manufacturer) {

		Map<String, String> mapData = getTabletData(path).stream()
				.filter(t -> testDate(t.getExpiryDate()))
				.collect(Collectors.toMap(Tablet::getTabletName, Tablet::getExpiryDate));

		return mapData;

	}

	private List<Tablet> getTabletData(Path filePath) {
		List<Tablet> tabletList = null;
		try (Stream<String> bufferData = Files.newBufferedReader(filePath).lines()) {
			tabletList = bufferData.map(t->getTablets(t)).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return tabletList;
	}

	private Tablet getTablets(String data) {
		String[] input = data.split(",");
		return new Tablet(input[0], input[1], input[2], input[3]);

	}

	private boolean testDate(String input) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate today = LocalDate.now();
		LocalDate inputDate = LocalDate.parse(input, format);
		boolean output = inputDate.isBefore(today);
		return output;
	}

	/**
	 * method to list all the files ending with .java in the current project's src
	 * folder and its subfolders
	 */
	public List<Path> fetchAllfiles() {
		try (Stream<Path> pathObjects = Files.walk(Paths.get("src"))) {
			return pathObjects.filter(Files::isRegularFile).filter(f -> f.toString().endsWith(".java"))
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * method which takes two String parameters. The first parameter is the filename
	 * to be searched and the second parameter is the absolute path of the directory
	 * to be searched
	 */
	public Optional<Path> search(String fileName, String dir) {
		try (Stream<Path> pathObjects = Files.walk(Paths.get(dir))) {
			return pathObjects.filter(Files::isRegularFile).filter(f -> f.getFileName().toString().equals(fileName))
					.findAny();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


}
