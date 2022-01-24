package ru.java.projectfinal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Docket cryptoApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.java.projectfinal.controllers")).build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("CryptoApiApp")
                .description("Сервис CryptoApiApp")
                .license("License")
                .licenseUrl("http://unlicense.org")
                .termsOfServiceUrl("")
                .version(getClass().getPackage().getImplementationVersion())
                .build();
    }
}
