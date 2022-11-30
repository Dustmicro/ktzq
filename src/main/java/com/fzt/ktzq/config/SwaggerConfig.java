package com.fzt.ktzq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.reflections.util.ConfigurationBuilder.build;

/**
 * 接口文档API
 * @author 黄弋峰 2022/11/30
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestAPi(){
         return new Docket(DocumentationType.SWAGGER_2)
                 .pathMapping("/")
                 .select()
                 .apis(RequestHandlerSelectors.basePackage("com.fzt.ktzq"))
                 .paths(PathSelectors.any())
                 .build().apiInfo(new ApiInfoBuilder()
                         .title("开拓足球")
                         .description("开拓足球  接口文档")
                         .version("1.0.0")
                         .build());
    }
}
