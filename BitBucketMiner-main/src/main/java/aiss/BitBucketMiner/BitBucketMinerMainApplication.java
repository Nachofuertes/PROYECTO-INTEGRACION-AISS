package aiss.BitBucketMiner;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BitBucketMinerMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitBucketMinerMainApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("Bitbucket Miner API")
				.version("1.0")
				.description("BitbucketMiner se encarga de extraer datos de repositorios alojados en Bitbucket. Utiliza la API REST de Bitbucket para recuperar información sobre commits, issues y pull requests, transformándola al formato estandarizado definido por GitMiner"));
	}
}