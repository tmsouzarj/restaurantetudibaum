package br.com.estudos.restaurantetudibaum.controller.record;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;

public class LoginRecordTest {

	@Test
	public void deveConseguirCriarLogin() {
		assertNotNull(new LoginRecordRequest("us1234", "1234"));
	}
	
	@Test
	public void deveFalharPorFormatoLoginUsuarioInvalido() {
		assertThrowsExactly(ConstraintViolationException.class, () -> new LoginRecordRequest("us1234568", "1234"));
	}
	
	@Test
	public void deveFalharPorLoginUsuarioNulo() {
		assertThrowsExactly(ValidationException.class, () -> new LoginRecordRequest(null, "1234"));
	}
	
	@Test
	public void deveFalharPorLoginUsuarioVazio() {
		assertThrowsExactly(ConstraintViolationException.class, () -> new LoginRecordRequest("", "1234"));
	}
	
	@Test
	public void deveFalharPorSenhaUsuarioNula() {
		assertThrowsExactly(ConstraintViolationException.class, () -> new LoginRecordRequest("us1234", null));
	}
	
	@Test
	public void deveFalharPorSenhaUsuarioVazia() {
		assertThrowsExactly(ConstraintViolationException.class, () -> new LoginRecordRequest("us1234", ""));
	}
}