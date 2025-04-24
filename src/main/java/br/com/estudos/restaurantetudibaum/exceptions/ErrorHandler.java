package br.com.estudos.restaurantetudibaum.exceptions;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.InvalidKeyException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {
	@Autowired
	protected MessageSource messageSource;
	
	protected Locale locale = Locale.of("pt", "BR");
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Erro> trataViolacaoRegrasNegocioException(ConstraintViolationException e) { 
		log.error(e.getMessage(), e);
		
		return getExpectationFailedResponse(e.getMessage());
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Erro> trataValidacaoCamposException(ValidationException e) {
		log.error(e.getMessage(), e);
		
		return getExpectationFailedResponse(e.getMessage());
	}
	
	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<Erro> trataInternalErrorException(InternalServerError e) {
		log.error(e.getMessage(), e);
		
		return getInternalServerErrorResponse();
	}

	@ExceptionHandler(UsuarioNaoEncontradoException.class)
	public ResponseEntity<Erro> trataUsuarioNaoEncontradoException(UsuarioNaoEncontradoException e) {
		log.error(e.getMessage(), e);
		
		return getExpectationFailedResponse(e.getMessage());
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Erro> trataUsernameNotFoundException(UsernameNotFoundException e) {
		return getExpectationFailedResponse(e.getMessage());
	}

	@ExceptionHandler(UsuarioSemAcessoException.class)
	public ResponseEntity<Erro> trataUsuarioSemAcessoException(UsuarioSemAcessoException e) {
		log.error(e.getMessage(), e);
		
		return getForbiddenResponse(e.getMessage());
	}
	
	@ExceptionHandler(InvalidKeyException.class)
	public ResponseEntity<Erro> trataInvalidKeyException(InvalidKeyException e) {
		log.error(e.getMessage(), e);
		
		return getInternalServerErrorResponse();
	}
	
	@ExceptionHandler(UnsupportedEncodingException.class)
	public ResponseEntity<Erro> trataUnsupportedEncodingException(UnsupportedEncodingException e) {
		log.error(e.getMessage(), e);
		
		return getInternalServerErrorResponse();
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Erro> trataIllegalArgumentException(IllegalArgumentException e) {
		log.error(e.getMessage(), e);
		
		return getInternalServerErrorResponse();
	}
	
	@ExceptionHandler(TokenNaoEncontradoException.class)
	public ResponseEntity<Erro> trataTokenNaoEncontradoException(TokenNaoEncontradoException e) {
		log.error(e.getMessage(), e);
		
		return getExpectationFailedResponse(e.getMessage());
	}
	
	@ExceptionHandler(FalhaAutenticacaoException.class)
	public ResponseEntity<Erro> trataFalhaAutenticacaoException(FalhaAutenticacaoException e) {
		log.error(e.getMessage(), e);
		
		return getInternalServerErrorResponse();
	}
	
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<Erro> trataExpiredJwtException(ExpiredJwtException e) {
		log.error(e.getMessage(), e);
		
		return getInternalServerErrorResponse();
	}
	
	
	protected ResponseEntity<Erro> getInternalServerErrorResponse() {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Erro(HttpStatus.INTERNAL_SERVER_ERROR.value(), messageSource.getMessage("exception.internal_server_error", null, locale)));
	}
	
	protected ResponseEntity<Erro> getExpectationFailedResponse(String mensagem) {
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Erro(HttpStatus.EXPECTATION_FAILED.value(), mensagem));
	}
	
	protected ResponseEntity<Erro> getForbiddenResponse(String mensagem) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Erro(HttpStatus.FORBIDDEN.value(), mensagem));
	}
}