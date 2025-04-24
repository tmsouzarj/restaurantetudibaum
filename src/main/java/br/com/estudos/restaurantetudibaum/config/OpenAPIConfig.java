package br.com.estudos.restaurantetudibaum.config;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Server;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class OpenAPIConfig {

	/*@Bean
	OpenAPI configurarOpenAPI() {
		Server dadosServidor = new Server();
		
		dadosServidor.setUrl("http://localhost:8080");
		dadosServidor.setDescription("Servidor local");
		
		Contact contato = new Contact();
		
		contato.setName("Thiago Motta");
		contato.setEmail("thiago.motta.souza.23@gmail.com");
		
		Info informacao = new Info().contact(contato)
									.description("Aplicação do Restaurante TuDiBaum, utilizada para realizar as operações necessárias para o funcionamento do restaurante.")
									.title("Restaurante TuDiBaum v1")
									.version("1.0");
		
		return new OpenAPI().info(informacao)
							.servers(Arrays.asList(dadosServidor));
	}*/
	
	@Bean
	public Docket getDocketForDev() {
		final Set<String> produces = new HashSet<String>();
		
		produces.add(MediaType.APPLICATION_JSON_VALUE);
		produces.add(MediaType.APPLICATION_XML_VALUE);

		Server serverLocal = new Server("local", "http://localhost:8080", "Uso local", Collections.emptyList(), Collections.emptyList());
        
		return new Docket(DocumentationType.OAS_30)
				.apiInfo(new ApiInfoBuilder()
						.title("Restaurante TuDiBaum")
						.description("Aplicação do Restaurante TuDiBaum, utilizada para realizar as operações necessárias para o funcionamento do restaurante.")
						.version("0.0.1")
						.license("MIT")
						.licenseUrl("https://opensource.org/licenses/MIT")
						.build())
				.produces(produces).consumes(produces)
				.servers(serverLocal)
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.build();
	}
}