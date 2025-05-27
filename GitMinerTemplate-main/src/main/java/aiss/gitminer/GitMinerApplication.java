package aiss.gitminer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GitMinerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitMinerApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("Bitbucket Miner API")
				.version("1.0")
				.description("Documentación para la API que extrae y adapta datos de Bitbucket"));
	}
}
