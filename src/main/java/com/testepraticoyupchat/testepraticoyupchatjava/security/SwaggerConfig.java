package com.testepraticoyupchat.testepraticoyupchatjava.security;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        SecurityScheme schema = new SecurityScheme();
        schema.setName("Testepraticoyupchatjava");
        schema.setType(SecurityScheme.Type.HTTP);
        schema.setScheme("bearer");
        schema.bearerFormat("JWT");
        return new OpenAPI().info(new Info().title("Teste Pratico Yup Chat Java")
                .contact(new Contact().email("christianbmoura@gmail.com")
                        .name("Christian Bernardino de Moura")))
                .addSecurityItem(new SecurityRequirement()
                        .addList(schema.getName()))
                .components(new Components().addSecuritySchemes(schema.getName(), schema));
    }
}
