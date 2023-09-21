package com.springboot.Springbootversioning.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
@Configuration
public class SpringFoxHeaderConfig {
    @Bean
    public Docket swaggerPersonApiHeader() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("person-api-1.4")
                .select()
                .apis(p -> {
                    if (p.produces() != null) {
                        for (MediaType mt : p.produces()) {
                            if (mt.toString().equals("application/vnd.piomin.app-v1.2+json")) {
                                return true;
                            }
                        }
                    }
                    return false;
                })
                .build()
                .produces(Collections.singleton("application/vnd.piomin.app-v1.2+json"))
                .apiInfo(new ApiInfoBuilder().version("1.2").title("Person API").description("Documentation Person API v1.2").build());
    }
}
