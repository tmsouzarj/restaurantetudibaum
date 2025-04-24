package br.com.estudos.restaurantetudibaum.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.estudos.restaurantetudibaum.exceptions.FalhaAutenticacaoException;
import br.com.estudos.restaurantetudibaum.filters.SecurityFilter;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
	@Autowired
	private SecurityFilter securityFilter;
	
	public final String[] ENDPOINTS_BLOQUEADOS = { "/restaurantetudibaum/", "/restaurantetudibaum/usuarios/**", "/usuarios/**" };
	/*public final static String[] ENDPOINTS_LIBERADOS = { "/restaurantetudibaum/login", 
			"/restaurantetudibaum/swagger-ui/**", "/restaurantetudibaum/v3/api-docs/**", 
			"/restaurantetudibaum/swagger-resources/**", "/restaurantetudibaum/swagger-ui/index.html", "/v3/api-docs",
			"/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/swagger-ui/**"};*/
	public final String[] ENDPOINTS_ADMINISTADOR = { "/restaurantetudibaum/admin/**" };
	public final static String[] ENDPOINTS_LIBERADOS = { "/restaurantetudibaum/login", "/swagger-ui/", "/v3/api-docs/","/swagger-ui.html" };
	
	@Autowired
	protected MessageSource messageSource;
	
	protected Locale locale = Locale.of("pt", "BR");
	
	@Bean
	SecurityFilterChain configurarSecurityFilterChain(HttpSecurity security) {
		try {
			return security.csrf(csrf -> csrf.disable())
						   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
						   .authorizeHttpRequests(req -> {
								req.requestMatchers(ENDPOINTS_LIBERADOS)
									.permitAll();
								req.requestMatchers(ENDPOINTS_BLOQUEADOS)
									.authenticated();
								req.requestMatchers(ENDPOINTS_ADMINISTADOR)
									.hasRole("Administrador");
					   	   })
					   	   .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
						   .build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			throw new FalhaAutenticacaoException(messageSource.getMessage("exception.internal_server_error", null, locale));		
		}
	}
	
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /*@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(
        		"/swagger-ui/", "/v3/api-docs/","/swagger-ui.html"
        );
    }*/
}