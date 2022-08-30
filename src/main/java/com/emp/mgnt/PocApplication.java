package com.emp.mgnt;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import lombok.extern.slf4j.Slf4j;

/**
 * Employee Management POC Application's Main Page
 */
@Slf4j
@SpringBootApplication
public class PocApplication{
	
	@Autowired
	private Environment env;

	/**
	 * main method to start application
	 * @param args
	 */

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(PocApplication.class);
		SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
		addDefaultProfile(app, source);
		SpringApplication.run(PocApplication.class);
		log.info("________________________________");
		log.info("Employee Management POC APP Start");
		log.info("________________________________");
	}

	private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
		if (!source.containsProperty("spring.profiles.active")
				&& !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {
			app.setAdditionalProfiles("dev");
		}
	}
	
	@PostConstruct
	public void initApplication() {
		if (env.getActiveProfiles().length == 0) {
			log.warn("No Spring profile configured, running with default configuration");
		} else {
			log.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
			Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
			if (activeProfiles.contains("dev") && activeProfiles.contains("prod")) {
				log.error("You have misconfigured your application! "
						+ "It should not run with both the 'dev' and 'prod' profiles at the same time.");
			}
		}
	}

}
