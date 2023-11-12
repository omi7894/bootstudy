package com.example.bootstudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@EnableSwagger2
public class  SwaggerConfiguration {

    private static final String API_NAME = "Programmers Spring Boot Application";
    private static final String API_VERSION = "1.0.0";
    private static final String API_DESCRIPTION = "프로그래머스 스프링 부트 애플리케이션입니다.";

    // 접속 경로 : http://localhost:8080/swagger-ui/
    @Bean
    public Docket api(){
        Parameter parameterBuilder = (Parameter) new ParameterBuilder()
                .name(HttpHeaders.AUTHORIZATION)
                .description("Access Tocken")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        List<Parameter> globalParameters = new ArrayList<>();
        globalParameters.add(parameterBuilder);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) //api정보
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.bootstudy")) //scan범위설정, 패키지이름 바꾸기
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder() //필요한 api정보만 입력
                .title(API_NAME)
                .description(API_DESCRIPTION)
                .version(API_VERSION) //maven에서 설정했던 정보
                .build(); //설정내용 적용
    }
}
