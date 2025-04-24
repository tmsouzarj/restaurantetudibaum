package br.com.estudos.restaurantetudibaum.validators;

import br.com.estudos.restaurantetudibaum.validator.annotation.Login;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LoginValidator implements ConstraintValidator<Login, String>{

	private static final String PADRAO_LOGIN = "us\\d{1,6}$";
	
	@Override
	public boolean isValid(String login, ConstraintValidatorContext context) {
		return login.matches(PADRAO_LOGIN);
	}
}