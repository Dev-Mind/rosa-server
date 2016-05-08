package com.devmind.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger is used to document our public API
 */
@Configuration
@EnableSwagger2
public class RosaSwaggerConfig {

    @Bean
    public Docket cesarApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("rosa-api")
                .apiInfo(apiInfo())
                .select()
                .paths(or(
                        regex("/api/speaker.*"),
                        regex("/api/session.*"),
                        regex("/api/ranking.*")
                ))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Rosa API")
                .description("You can use the Rosa API to read informations about sessions")
                .contact("guillaume@dev-mind.fr")
                .license("MIT licence")
                .licenseUrl("http://opensource.org/licenses/MIT")
                .version("2.0")
                .build();
    }

}
