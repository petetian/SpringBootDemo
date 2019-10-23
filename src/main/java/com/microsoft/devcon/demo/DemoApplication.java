package com.microsoft.devcon.demo;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

	@PreDestroy
	public void tearDown() {
		logger.info(">>>>>>>>>>>>>>>> Shutting Down the app <<<<<<<<<<<<<<<<<");
	}

}
