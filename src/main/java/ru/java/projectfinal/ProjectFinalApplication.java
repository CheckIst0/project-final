package ru.java.projectfinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.java.projectfinal.controllers.CryptoApiController;
import ru.java.projectfinal.core.model.CryptoApiDto;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableSwagger2
@EnableAutoConfiguration
public class ProjectFinalApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectFinalApplication.class, args);
	}
}
