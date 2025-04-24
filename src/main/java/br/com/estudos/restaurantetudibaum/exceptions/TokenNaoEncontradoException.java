package br.com.estudos.restaurantetudibaum.exceptions;

public class TokenNaoEncontradoException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public TokenNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}
