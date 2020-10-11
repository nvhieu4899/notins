package go.nvhieucs.notins.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static go.nvhieucs.notins.security.JWTUtils.HEADER_STRING;

@Configuration
@EnableSwagger2
@Import(SpringDataRestConfiguration.class)
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("go.nvhieucs.notins.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(List.of(new ApiKey("JWT", HEADER_STRING, "header")))
                .apiInfo(apiEndpointsInfo());
    }

    private ApiInfo apiEndpointsInfo() {
        return new ApiInfoBuilder().title("NOTINS REST API").description("NOTINS - An Instagram clone RESTFUL API")
                .license("Apache 2.0")
                .contact(new Contact("Nguyen Van Hieu", "https://www.linkedin.com/in/nvhieu48/", "nvhieu4899@outlook.com"))
                .version("1.0.0").build();
    }
}
