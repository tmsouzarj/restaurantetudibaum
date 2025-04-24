package br.com.estudos.restaurantetudibaum.repositories.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.estudos.restaurantetudibaum.entities.DadosEndereco;
import br.com.estudos.restaurantetudibaum.repositories.IDadosEnderecoRepository;

@Repository
public class DadosEnderecoRepository {

	IDadosEnderecoRepository dadosEnderecoRepository;
	
	public DadosEnderecoRepository(IDadosEnderecoRepository dadosEnderecoRepository) {
		this.dadosEnderecoRepository = dadosEnderecoRepository;
	}
	
	public Optional<DadosEndereco> recuperaDadosEndereco(Integer idUsuario) {
		return dadosEnderecoRepository.findById(idUsuario);
	}
}