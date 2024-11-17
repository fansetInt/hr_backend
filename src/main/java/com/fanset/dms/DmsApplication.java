package com.fanset.dms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//Enabling Auditing
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@ComponentScan(basePackages = {"com.fanset.dms"})// Specify the package for the Token entity
public class DmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmsApplication.class, args);
	}

}
