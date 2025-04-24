package br.com.estudos.restaurantetudibaum.validators;

import java.util.Arrays;
import java.util.List;

import br.com.estudos.restaurantetudibaum.validator.annotation.Email;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String>{

	public static final List<String> dominiosAceitos = Arrays.asList("@restaurantetudibaum.com", "@restaurantetudibaum.net");
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return dominiosAceitos.stream().anyMatch(dominio -> email.endsWith(dominio));
	}
}