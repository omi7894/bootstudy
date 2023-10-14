package com.example.bootstudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo()) //api정보
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.bootstudy")) //scan범위설정, 패키지이름 바꾸기
//                .paths(PathSelectors.any())
//                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.bootstudy"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
//        return new ApiInfoBuilder() //필요한 api정보만 입력
//                .title("Around Hun Open API Test with Swagger")
//                .description("설명 부분")
//                .version("1.0") //maven에서 설정했던 정보
//                .build(); //설정내용 적용
        return new ApiInfoBuilder()
                .title("Around Hun Open API Test with Swagger")
                .version("1.0")
                .description("설명 부분")
                .build();
    }
}
