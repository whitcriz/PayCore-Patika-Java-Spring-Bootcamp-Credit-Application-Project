package com.paycore.patika.credit_application_system.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@ConfigurationPropertiesScan("com.paycore.patika.credit_application_system.config")
public class SwaggerConfig {

// https://springfox.github.io/springfox/docs/current/

    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("com.paycore.patika")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.paycore.patika.credit_application_system.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}






