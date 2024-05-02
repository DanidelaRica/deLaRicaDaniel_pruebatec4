package com.hackaboss.PruebaTenica4Final;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PruebaTenica4FinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaTenica4FinalApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("API Agencia")
				.version("0.0.1")
				.description("Agencia"));
	}



}
