package br.com.estudos.restaurantetudibaum.validator.record_aux;

import java.lang.reflect.Constructor;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

public interface Validador {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	default void validar(Object... args) {
		Validator validator = ValidadorProvider.getValidator();
		Constructor constructor = getClass().getDeclaredConstructors()[0];
		Set<ConstraintViolation<?>> violations = validator.forExecutables().validateConstructorParameters(constructor, args);
		if (!violations.isEmpty()) {
			String message = violations.stream().map(ConstraintViolation::getMessage)
					.collect(Collectors.joining(System.lineSeparator()));
			throw new ConstraintViolationException(message, violations);
		}
	}
}
