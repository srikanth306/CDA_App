package com.softgv.cda.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfiguration {
    @Bean
    OpenAPI usersMicroserviceOpenAPI() {

		Server localhost = new Server();
		localhost.setUrl("http://localhost:8080");
		localhost.setDescription("Development environment");

		Contact contact = new Contact();
		contact.setEmail("sb569381@gmail.com");
		contact.setName("CDA App");
		contact.setUrl("http://localhost:8080/users");

		License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

		Info info = new Info().title("CDA App RESTful Web Service documentation").version("0.1").contact(contact)
				.description("This API exposes endpoints to manage Application for CDA App.")
				.termsOfService("https://domainname/terms").license(mitLicense);

		return new OpenAPI().info(info).servers(List.of(localhost));
	}

}
