package com.boots.security.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
            //тут указываем достпуный сервер для swagger в данные момент это http://localhost:8080
            .servers(
                List.of(new Server().url("http://localhost:8080"))
            )
            //информация о swagger тут можно написать описание загаловок и тд
            .info(
              new Info().title("Our cat API")
            );
    }
}
