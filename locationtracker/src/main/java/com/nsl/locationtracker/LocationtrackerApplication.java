package com.nsl.locationtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
@ComponentScan(basePackages = {"com.nsl.locationtracker"})
@EnableJpaRepositories("com.nsl.locationtracker.repository")
public class LocationtrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationtrackerApplication.class, args);
	}

}
