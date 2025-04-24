package br.com.estudos.restaurantetudibaum.controller.record;

public record UsuarioRecordResponse(Integer id, String nome, String email, String login, DadosEnderecoRecord dadosEndereco) { }