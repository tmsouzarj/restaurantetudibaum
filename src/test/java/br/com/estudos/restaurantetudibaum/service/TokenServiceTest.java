package br.com.estudos.restaurantetudibaum.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.estudos.restaurantetudibaum.services.TokenService;

public class TokenServiceTest {

	String login;
	
	TokenService tokenService;
	
	@BeforeEach
	public void iniciar() {
		tokenService = new TokenService();
		
		login = "us000001";
	}
	
	@Test
	public void deveGerarTokenComSucesso() throws Exception {
		String token = tokenService.geraToken(login);
		
		assertNotNull(token);
	}
	@Test
	public void deveDecodificarTokenComSucesso() throws Exception {
		String token = tokenService.geraToken(login);
		
		String loginUsuario = tokenService.recuperaDadosToken(token);
		
		assertEquals(loginUsuario, login);
	}
}
