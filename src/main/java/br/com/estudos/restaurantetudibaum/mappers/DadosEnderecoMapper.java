package br.com.estudos.restaurantetudibaum.mappers;

import br.com.estudos.restaurantetudibaum.controller.record.DadosEnderecoRecord;
import br.com.estudos.restaurantetudibaum.entities.DadosEndereco;

public abstract class DadosEnderecoMapper {

	public static DadosEndereco toDadosEndereco(DadosEnderecoRecord dadosEnderecoRecord) {
		DadosEndereco dadosEndereco = new DadosEndereco();
		
		dadosEndereco.setBairro(dadosEnderecoRecord.bairro());
		dadosEndereco.setCep(dadosEnderecoRecord.cep());
		dadosEndereco.setCidade(dadosEnderecoRecord.cidade());
		dadosEndereco.setComplemento(dadosEnderecoRecord.complemento());
		dadosEndereco.setEndereco(dadosEnderecoRecord.endereco());
		dadosEndereco.setEstado(dadosEnderecoRecord.estado());
		dadosEndereco.setNumero(dadosEnderecoRecord.numero());
		dadosEndereco.setSemNumero(dadosEnderecoRecord.semNumero());
		
		return dadosEndereco;
	}
	
	public static DadosEnderecoRecord toDadosEnderecoRecord(DadosEndereco dadosEndereco) {
		return new DadosEnderecoRecord(dadosEndereco.getCidade(), dadosEndereco.getCep(), dadosEndereco.getBairro(), 
									   dadosEndereco.getEndereco(), dadosEndereco.getEstado(), dadosEndereco.getNumero(), 
									   dadosEndereco.getComplemento(), dadosEndereco.getSemNumero());
	}
}