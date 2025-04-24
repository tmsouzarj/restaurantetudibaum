package br.com.estudos.restaurantetudibaum.repositories.impl;

import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.estudos.restaurantetudibaum.entities.Usuario;
import br.com.estudos.restaurantetudibaum.entities.UsuarioSecurity;
import br.com.estudos.restaurantetudibaum.mappers.UsuarioMapper;
import br.com.estudos.restaurantetudibaum.repositories.IUsuarioSecurityRepository;

@Repository
public class UsuarioSecurityRepository implements IUsuarioSecurityRepository {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	protected MessageSource messageSource;
	
	protected Locale locale = Locale.of("pt", "BR");
	
	@Override
	public UsuarioSecurity loadUserByUsername(String login) {
		Optional<Usuario> dados = usuarioRepository.recuperaDadosUsuarioPorLogin(login);
		
		if(dados.isPresent()) {
			return UsuarioMapper.toUsuarioSecurity(dados.get());
		} else {
			throw new UsernameNotFoundException(messageSource.getMessage("exception.usuarios_nao_encontrados_por_nome", null, locale));
		}
	}
}