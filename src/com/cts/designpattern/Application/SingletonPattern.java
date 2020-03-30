package com.cts.designpattern.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Create an class that return a single instance of Logger Object.
 *
 */
public class SingletonPattern {
	
	private static Logger log;
	
	private SingletonPattern() {
		
	}
	
	static Logger getInstance() {
		if(log==null) {
			return LoggerFactory.getLogger(SingletonPattern.class);
		}
		return log;
	}
	
	
	public static void main(String[] args) {
		
		Logger logger=SingletonPattern.getInstance();
		logger.debug("logger object {}",logger);
	}

}
