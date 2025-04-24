package br.com.estudos.restaurantetudibaum.controller.record;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolationException;

public class UsuarioRecordTest {

	String nome;
	String email;
	String senha;
	String login;
	DadosEnderecoRecord dadosEndereco; 
	
	@BeforeEach
	public void setup() {
		nome = "Thiago";
		email = "thiago@restaurantetudibaum.com";
		login = "us123456";
		senha = "123456";
		dadosEndereco =  new DadosEnderecoRecord("São Gonçalo", "24455450", "Nova Cidade", "Rua Aquidabã", "Rio de Janeiro", 79, "Casa 8", false);
	}
	
	@Test
	public void deveConseguirCriarUsuario() {
		assertNotNull(new UsuarioRecordRequest(nome, email, login, senha, dadosEndereco));
	}
	
	@Test
	public void deveFalharPorValidacaoEmail() {
		email = "thiago@teste.com";
		
		assertThrowsExactly(ConstraintViolationException.class, () -> new UsuarioRecordRequest(nome, email, login, senha, dadosEndereco));
	}
	
	@Test
	public void deveFalharPorValidacaoLogin() {
		login = "us12345";
		
		assertThrowsExactly(ConstraintViolationException.class, () -> new UsuarioRecordRequest(nome, email, login, senha, dadosEndereco));
	}
	
	@Test
	public void deveFalharPorValidacaoLoginFormatoInvalido() {
		login = "ur123456";
		
		assertThrowsExactly(ConstraintViolationException.class, () -> new UsuarioRecordRequest(nome, email, login, senha, dadosEndereco));
	}
	
	@Test
	public void deveFalharPorValidacaoNome() {
		nome = "ab";
		
		assertThrowsExactly(ConstraintViolationException.class, () -> new UsuarioRecordRequest(nome, email, login, senha, dadosEndereco));
	}
	
	@Test
	public void deveFalharPorValidacaoSenha() {
		senha = "124";
		
		assertThrowsExactly(ConstraintViolationException.class, () -> new UsuarioRecordRequest(nome, email, login, senha, dadosEndereco));
	}
}