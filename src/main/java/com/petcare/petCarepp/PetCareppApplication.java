package com.petcare.petCarepp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.petcare.petCarepp",
		"com.petcare.User" //
})
@EnableJpaRepositories(basePackages = {
		"com.petcare.petCarepp.Hospital.repository",
		"com.petcare.User.Repository" //
})
@EntityScan(basePackages = {
		"com.petcare.petCarepp.Hospital.entity",
		"com.petcare.User.entity" //
})
public class PetCareppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetCareppApplication.class, args);
	}

}
