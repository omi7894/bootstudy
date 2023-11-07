package com.example.bootstudy;

import com.example.bootstudy.config.ProfileManager;
import com.example.bootstudy.config.env.EnvConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class BootstudyApplication {

	private final Logger LOGGER = LoggerFactory.getLogger(BootstudyApplication.class);

	@Autowired
	public BootstudyApplication(EnvConfiguration envConfiguration, ProfileManager profileManager){
		LOGGER.info(envConfiguration.getMessage());
		profileManager.printActiveProfiles();
	}
	public static void main(String[] args) {
		SpringApplication.run(BootstudyApplication.class, args);
	}

}
