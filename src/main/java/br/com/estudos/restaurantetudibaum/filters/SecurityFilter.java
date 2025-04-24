package br.com.estudos.restaurantetudibaum.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.estudos.restaurantetudibaum.config.SecurityConfig;
import br.com.estudos.restaurantetudibaum.entities.UsuarioSecurity;
import br.com.estudos.restaurantetudibaum.exceptions.TokenNaoEncontradoException;
import br.com.estudos.restaurantetudibaum.repositories.impl.UsuarioSecurityRepository;
import br.com.estudos.restaurantetudibaum.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

	@Autowired
	TokenService tokenService;
	
	@Autowired
	UsuarioSecurityRepository usuarioSecurityRepository;
	
	@Autowired
	protected MessageSource messageSource;
	
	protected Locale locale = Locale.of("pt", "BR");
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {		
		if(!isEndpointLiberado(request.getRequestURI())) {
			String token = recuperaToken(request);
			String login = tokenService.recuperaDadosToken(token);
			UsuarioSecurity usuario = usuarioSecurityRepository.loadUserByUsername(login);
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(usuario.getUsername(), null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}
	
	protected boolean isEndpointLiberado(String endpoint) {
		return Arrays.asList(SecurityConfig.ENDPOINTS_LIBERADOS).contains(endpoint);
	}
	
	protected String recuperaToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if(!StringUtils.hasText(token)) {
			throw new TokenNaoEncontradoException(messageSource.getMessage("exception.token_nao_encotrado", null, locale));
		} 
		
		return token.replace("Bearer", "");
	}
}