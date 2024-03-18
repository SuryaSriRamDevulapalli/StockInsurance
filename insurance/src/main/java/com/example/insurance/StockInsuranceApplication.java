package com.example.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;



@SpringBootApplication
@ComponentScan(basePackages = "com.example")
@EnableR2dbcRepositories(basePackageClasses = com.example.repo.InsuranceRepo.class)
@EntityScan("com.example.*")
public class StockInsuranceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockInsuranceApplication.class, args);
	}

}
