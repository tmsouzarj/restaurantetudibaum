package br.com.estudos.restaurantetudibaum.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Erro {
	private int codigo;
	private String mensagem;
}