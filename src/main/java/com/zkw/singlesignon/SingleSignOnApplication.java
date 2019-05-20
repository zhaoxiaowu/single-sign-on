package com.zkw.singlesignon;

import com.zkw.singlesignon.sdk.TokenConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableSwagger2
public class SingleSignOnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingleSignOnApplication.class, args);
    }

    //配置swagger
    @Bean
    public Docket swaggerDocket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(buildApiInfo()).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();
    }
    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder().title("SingleSignOn.API").version("1.0").build();
    }
//    @Bean
//    public Docket swaggerDocket() {
//        return new Docket(DocumentationType.SWAGGER_2).select()
//                .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName())).build();
//    }
    @Bean(TokenConfig.BEAN_ID)
    @ConfigurationProperties(prefix = "dcommons-token")
    public TokenConfig getTokenConfig() {
        return new TokenConfig();
    }
}
