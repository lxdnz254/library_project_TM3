package com.lxdnz.bit794.tm3.library_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.lxdnz.bit794.tm3.library_project.persistence.repo")
@EntityScan("com.lxdnz.bit794.tm3.library_project.persistence.model")
@SpringBootApplication
public class LibraryProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryProjectApplication.class, args);
	}
}
