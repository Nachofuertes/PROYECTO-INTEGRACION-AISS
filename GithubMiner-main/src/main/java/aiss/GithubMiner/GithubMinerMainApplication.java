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
				.title("Bitbucket Miner API")
				.version("1.0")
				.description("Documentación para la API que extrae y adapta datos de Bitbucket"));
	}
}
