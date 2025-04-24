package br.com.estudos.restaurantetudibaum.controller.record;

import br.com.estudos.restaurantetudibaum.validator.annotation.Email;
import br.com.estudos.restaurantetudibaum.validator.annotation.Login;
import br.com.estudos.restaurantetudibaum.validator.record_aux.Validador;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioRecordRequest(String nome, String email, String login, String senha, DadosEnderecoRecord dadosEndereco) implements Validador {
	public UsuarioRecordRequest(@NotBlank(message = "O campo nome precisa estar preenchido.")
								@Size(min = 3, max = 150, message = "O campo nome precisa ter entre 3 e 150 caracteres.") 
								String nome, 
						 		
								@NotBlank(message = "O campo email precisa estar preenchido.")
						 		@Size(min = 3, max = 70, message = "O campo email precisa ter entre 3 e 70 caracteres.") 
								@Email String email, 
						 		
								@NotBlank(message = "O campo login precisa estar preenchido.")
						 		@Size(min = 8, max = 8, message = "O campo login precisa ter 8 caracteres.") 
								@Login 
								String login, 
						 		
								@NotBlank(message = "O campo senha precisa estar preenchido.")
								@Size(min = 6, max = 10, message = "O campo senha precisa ter entre 6 e 10 caracteres.") 
								String senha,
						 		
								@NotNull(message = "Os dados do endereço precisam estar preenchidos.")
								DadosEnderecoRecord dadosEndereco) {
		validar(nome, email, login, senha, dadosEndereco);
		
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.dadosEndereco = dadosEndereco;
	}
}