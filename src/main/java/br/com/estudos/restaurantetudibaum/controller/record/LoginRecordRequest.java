package br.com.estudos.restaurantetudibaum.controller.record;

import br.com.estudos.restaurantetudibaum.validator.annotation.Login;
import br.com.estudos.restaurantetudibaum.validator.record_aux.Validador;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;

public record LoginRecordRequest(@Parameter(description = "Login do usuário") String login, 
								 @Parameter(description = "Senha do usuário") String senha) implements Validador { 
	public LoginRecordRequest(@NotBlank(message = "O campo login não foi informado.") 
							  @Login String login, 
							  @NotBlank(message = "O campo senha não foi informado.") String senha) {
		validar(login, senha);
		
		this.login = login;
		this.senha = senha;
	}
}
