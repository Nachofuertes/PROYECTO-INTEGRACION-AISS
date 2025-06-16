package aiss.GithubMiner;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GithubMinerMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubMinerMainApplication.class, args);
	}


	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) { return builder.build(); }

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("Github Miner API")
				.version("1.0")
				.description("GitHubMiner es el componente dedicado a la extracción de datos desde GitHub. Consume la API pública de GitHub para recopilar información relevante sobre la actividad del repositorio, asegurando la compatibilidad con la estructura común de GitMiner"));
	}
}
