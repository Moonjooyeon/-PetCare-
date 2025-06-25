package com.petcare.petCarepp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("🐾 PETCARE API 명세서")
                        .version("1.0.0")
                        .description("반려동물 병원 기록/관리 시스템의 공식 API 문서입니다.")
                );
    }
}
