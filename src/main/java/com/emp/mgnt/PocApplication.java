package com.emp.mgnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * Employee Management POC Application's Main Page
 */
@SpringBootApplication
@Slf4j 
public class PocApplication {

	/**
	 * main method to start application
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(PocApplication.class, args);
		log.info("________________________________");
		log.info("Employee Management POC APP Start");
		log.info("________________________________");
	}

}
