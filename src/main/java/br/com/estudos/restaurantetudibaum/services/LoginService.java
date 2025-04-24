
package br.com.estudos.restaurantetudibaum.services;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estudos.restaurantetudibaum.controller.record.LoginRecordRequest;
import br.com.estudos.restaurantetudibaum.entities.Usuario;
import br.com.estudos.restaurantetudibaum.repositories.impl.UsuarioRepository;
import io.jsonwebtoken.security.InvalidKeyException;

@Service
public class LoginService {
	
	@Autowired
	private UsuarioRepository loginRepository;
	
	@Autowired
	private TokenService tokenService;
	
	public String gerarTokenAcesso(LoginRecordRequest dados) throws InvalidKeyException, UnsupportedEncodingException {
		Usuario usuario = loginRepository.recuperaDadosUsuarioPorLoginSenha(dados.login(), dados.senha());
		
		return tokenService.geraToken(usuario.getLogin());
	}
}