package com.redcare.pharmacy.popular.respositories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PopularGithubRepositoriesViewerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PopularGithubRepositoriesViewerApplication.class, args);
	}

}
