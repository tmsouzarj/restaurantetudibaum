package br.com.estudos.restaurantetudibaum.validator.record_aux;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ValidadorProvider {
	private static final Validator VALIDATOR;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        VALIDATOR = factory.getValidator();
    }

    public static Validator getValidator() {
        return VALIDATOR;
    }
}
