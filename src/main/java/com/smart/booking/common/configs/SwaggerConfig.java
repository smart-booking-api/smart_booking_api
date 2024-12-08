package com.smart.booking.common.configs;

import com.smart.booking.common.dto.MemberContextDto;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.List;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        final SecurityScheme securityScheme = new SecurityScheme()
            .name("Authorization")
            .type(SecurityScheme.Type.HTTP)
            .in(SecurityScheme.In.HEADER)
            .scheme("bearer")
            .bearerFormat("JWT");

        final Components components = new Components().addSecuritySchemes("Authorization", securityScheme);

        final Info info = new Info()
            .title("스크린 골프 API")
            .description("스크린 골프 API 명세서")
            .version("1.0.0");

        final SecurityRequirement securityRequirement = new SecurityRequirement().addList("Authorization");

        return new OpenAPI()
            .info(info)
            .components(components)
            .security(List.of(securityRequirement));
    }


//    @Bean
//    public OpenApiCustomizer customOpenApi() {
//        return openApi -> openApi.getComponents().getSchemas().remove(MemberContextDto.class.getSimpleName()); // 전역 스키마에서 제외
//    }
}