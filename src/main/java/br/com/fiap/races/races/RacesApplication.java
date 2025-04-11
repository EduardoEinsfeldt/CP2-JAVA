package br.com.fiap.races.races;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "API de Raças Fictícias", description = "Exemplo de API RESTful com Swagger", version = "v1"))
public class RacesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RacesApplication.class, args);
	}

}
