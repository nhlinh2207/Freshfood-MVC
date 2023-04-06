package com.linh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration(value = "swaggerConfiguration")
@EnableSwagger2
public class SwaggerConfig {

    // Link swagger-ui : http://183.91.3.60:8080/sca-0.1/swagger-ui.html

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .tags(new Tag("FF", "Freshfood"))
                .select()
                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.linh"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiEndPointInfo());
    }

    private ApiInfo apiEndPointInfo(){
        return new ApiInfoBuilder().title("Spring Boot REST API")
                .description("Book Store REST API")
                .contact(new Contact("hunghust", "https://cist.cmc.com.vn", "hunga1k15tv@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}
