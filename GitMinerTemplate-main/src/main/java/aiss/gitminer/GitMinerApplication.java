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
				.title("GitMiner API")
				.version("1.0")
				.description("GitMiner es el motor central del sistema de minería de datos de repositorios. Orquesta la extracción, transformación y almacenamiento de datos relacionados con issues, commits y pull requests desde distintas plataformas (Bitbucket, GitHub, etc.). Este componente actúa como intermediario entre los mineros específicos de cada proveedor y el sistema de almacenamiento o procesamiento posterior."));
	}
}
