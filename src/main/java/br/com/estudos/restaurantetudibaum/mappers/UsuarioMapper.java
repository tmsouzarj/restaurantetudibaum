package br.com.estudos.restaurantetudibaum.mappers;

import java.time.LocalDateTime;

import br.com.estudos.restaurantetudibaum.controller.record.UsuarioRecordRequest;
import br.com.estudos.restaurantetudibaum.controller.record.UsuarioRecordResponse;
import br.com.estudos.restaurantetudibaum.entities.Usuario;
import br.com.estudos.restaurantetudibaum.entities.UsuarioSecurity;

public abstract class UsuarioMapper {

	public static Usuario toUsuario(UsuarioRecordRequest usuarioRecord) {
		Usuario usuario = new Usuario();
		
		usuario.setNome(usuarioRecord.nome());
		usuario.setEmail(usuarioRecord.email());
		usuario.setLogin(usuarioRecord.login());
		usuario.setSenha(usuarioRecord.senha());
		usuario.setDadosEndereco(DadosEnderecoMapper.toDadosEndereco(usuarioRecord.dadosEndereco()));
		usuario.setIsAtivo(true);
		usuario.setDataCriacao(LocalDateTime.now());
		
		return usuario;
	}
	
	public static UsuarioRecordResponse toUsuarioRecord(Usuario usuario) {
		return new UsuarioRecordResponse(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getLogin(), DadosEnderecoMapper.toDadosEnderecoRecord(usuario.getDadosEndereco()));
	}
	
	public static UsuarioSecurity toUsuarioSecurity(Usuario usuario) {
		return new UsuarioSecurity(usuario);
	}
}