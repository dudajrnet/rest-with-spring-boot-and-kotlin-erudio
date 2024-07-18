package br.com.erudio.config

import org.springframework.context.annotation.Configuration
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean

@Configuration
class OpenApiConfig {
    @Bean
    fun customOpenApi(): OpenAPI{
        return OpenAPI()
            .info(
                Info()
                    .title("RESTful API with Kotlin and Spring Boot")
                    .version("v1")
                    .description("Some description about your API.")
                    .termsOfService("https://pub.erudio.com.br/meus-cursos")
                    .license(
                            License().name("Apache 2.0")
                                .url("https://pub.erudio.com.br/meus-cursos")
                    )
        )
    }
}