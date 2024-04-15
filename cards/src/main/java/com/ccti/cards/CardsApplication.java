package com.ccti.cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.ccti.cards.dto.ContactInfoDTO;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
		info = @Info(
				title = "Restrictive Bank System",
				description = "Proyecto Ejemplo para Curso Spring Boot",
				version = "V1",
				contact = @Contact(
						name = "Divier Casas",
						email = "diviercasas26@gmail.com",
						url = "www.google.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "www.google.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Proyecto para curso de Microservicios con SprinBoot",
				url = "http://localhost:8082/swagger-ui/index.html#"
		)		
)
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@EnableConfigurationProperties(value = ContactInfoDTO.class)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
