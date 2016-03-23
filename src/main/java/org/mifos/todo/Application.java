package org.mifos.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author Sachith Senarathne
 * <p>
 * This Class contain the main method of the application and
 * bootstrapping the app
 * </p>
 *
 */
@Configuration
@EnableAutoConfiguration
@EnableSwagger2
@ComponentScan("org.mifos.todo.controller")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("Todo Application Started on port 8080");
	}

	@Bean
	public Docket todoApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("todoapi").apiInfo(apiInfo()).select()
				.paths(regex("/api/todo.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Springboot REST Sample with Swagger to GSOC2016")
				.description("Interactive API for REST application using Swagger and SpringFox")
				.termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
				.contact("Sachith Senarathne").license("Apache License Version 2.0")
				.licenseUrl("https://github.com/SachithS/mifos-swagger-rest-doc/blob/master/LICENSE").version("2.0")
				.build();
	}
}
