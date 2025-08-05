package org.louai.blogapp;

import org.louai.blogapp.domain.entities.Category;
import org.louai.blogapp.repositories.CategoryRepository;
import org.louai.blogapp.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}






}
