package com.petcare.petCarepp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")}) // 추가
@SpringBootApplication
@ComponentScan(basePackages = {
		"com.petcare.petCarepp",
		"com.petcare.User" //
})
@EnableJpaRepositories(basePackages = {
		"com.petcare.petCarepp.Hospital.repository",
		"com.petcare.petCarepp.timeline.repository",
		"com.petcare.petCarepp.record.repository",
		"com.petcare.petCarepp.follow.repository",
		"com.petcare.User.Repository" //
})
@EntityScan(basePackages = {
		"com.petcare.petCarepp.Hospital.entity",
		"com.petcare.petCarepp.timeline.entity",
		"com.petcare.petCarepp.record.Entity",
		"com.petcare.petCarepp.follow.Entity",
		"com.petcare.User.entity" //
})
public class PetCareppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetCareppApplication.class, args);
	}

}
