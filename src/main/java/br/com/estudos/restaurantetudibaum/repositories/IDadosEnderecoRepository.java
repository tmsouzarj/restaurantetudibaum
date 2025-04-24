package br.com.estudos.restaurantetudibaum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudos.restaurantetudibaum.entities.DadosEndereco;

public interface IDadosEnderecoRepository extends JpaRepository<DadosEndereco, Integer>{ }