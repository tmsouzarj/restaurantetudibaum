package br.com.estudos.restaurantetudibaum.entities;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UsuarioSecurity implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return usuario.getAcessos()
				      .stream()
				      .map(a -> new SimpleGrantedAuthority(a.getPerfilAcesso().getDescricao()))
				      .collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return usuario.getLogin();
	}
}
