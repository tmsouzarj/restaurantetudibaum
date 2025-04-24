package br.com.estudos.restaurantetudibaum.repositories.impl;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import br.com.estudos.restaurantetudibaum.entities.Usuario;
import br.com.estudos.restaurantetudibaum.exceptions.UsuarioNaoEncontradoException;
import br.com.estudos.restaurantetudibaum.repositories.IUsuarioRepository;

@Repository
public class UsuarioRepository {

	private final IUsuarioRepository usuarioRepository;
	
	
	@Autowired
	protected MessageSource messageSource;
	
	protected Locale locale = Locale.of("pt", "BR");
	
	public UsuarioRepository(IUsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public Usuario recuperaDadosUsuarioPorLoginSenha(String usuario, String senha) {
		return getUsuario(usuarioRepository.findUsuarioAtivoPorLoginSenha(usuario, senha));
	}

	public Optional<Usuario> recuperaDadosUsuarioPorLogin(String login) {
		return usuarioRepository.findByLoginAndIsAtivoTrue(login);
	}

	public Usuario recuperaDadosUsuarioPorId(Integer id) {
		return getUsuario(usuarioRepository.findById(id));
	}

	public List<Usuario> recuperaDadosUsuarioPorNome(String nome) {
		List<Usuario> usuarios = usuarioRepository.findAllByNomeContainsIgnoreCaseAndIsAtivoTrueOrderByNomeAsc(nome);
		
		if(!usuarios.isEmpty()) {		
			return usuarios;
		} else {
			throw new UsuarioNaoEncontradoException(messageSource.getMessage("exception.usuarios_nao_encontrados_por_nome", null, locale));
		}
	}

	public List<Usuario> recuperaDadosUsuarios() {
		List<Usuario> usuarios = usuarioRepository.findAllByIsAtivoTrue();
		
		if(!usuarios.isEmpty()) {		
			return usuarios;
		} else {
			throw new UsuarioNaoEncontradoException(messageSource.getMessage("exception.usuarios_nao_encontrados", null, locale));
		}
	}
	
	public void salvar(Usuario usuario) {
		usuarioRepository.save(usuario);	
	}
	
	protected Usuario getUsuario(Optional<Usuario> dadosUsuario) {
		if(dadosUsuario.isPresent()) {		
			return dadosUsuario.get();
		} else {
			throw new UsuarioNaoEncontradoException(messageSource.getMessage("exception.usuario_nao_encontrado", null, locale));
		}
	}
}