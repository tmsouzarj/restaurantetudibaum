package br.com.estudos.restaurantetudibaum.exceptions;

public class FalhaAutenticacaoException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public FalhaAutenticacaoException(String mensagem) {
		super(mensagem);
	}
}
