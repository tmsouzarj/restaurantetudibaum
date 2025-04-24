package br.com.estudos.restaurantetudibaum.services;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.SignatureException;

@Service
public class TokenService {

	private static final String CHAVE = "ff084f49daec4659d165f03bd4e5cd0c05208fabaaab79e59223cdf762789ed7d98c8d99f07dcb3004712c320da30558872c74efd237cddd41e28464f5001d1bacab973d1924822b79f0cc507ddd5bac36270cd1ba19be00eea466400e34a0284cb90f91d59d620847ed974242a64c70afec60a10d89e57dd75a08c653a9d64a9ef2177eb1b9661a21c1c7e35cbc1ec26384db955248a060b9b0b39540169f1d30ded588bdb3368d700715c2fe72070831b796857d0ee74c8ebc83df635dc652d53bd345ea0518502b72cc81d1b55054cbf5bc37481c184678f0b312cb2a90c7a0c80c66fd72ede42d6287a45c905256c4ee7f11517b1be8ccd806135128af5f";
	private static final String APLICACAO = "restaurantetudibaum";

	//@Value("${KEY_RESTAURANTE:teste}")
	//private String chave;
	
	@SuppressWarnings("deprecation")
	public String geraToken(String login) throws InvalidKeyException, UnsupportedEncodingException {
		return Jwts.builder()
				   .setIssuer(APLICACAO)
				   .setSubject(login)
				   .setIssuedAt(Date.from(recuperaDataCriacao()))
				   .setExpiration(Date.from(recuperaDataExpiracao()))
				   .signWith(SignatureAlgorithm.HS256, CHAVE.getBytes("UTF-8"))
				   .compact();
	}
	
	public String recuperaDadosToken(String token) throws SignatureException, ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, IllegalArgumentException, UnsupportedEncodingException {
		return Jwts.parserBuilder()
	                .setSigningKey(CHAVE.getBytes("UTF-8")).build()
	                .parseClaimsJws(token)
	                .getBody()
	                .getSubject();
	}
	
	protected Instant recuperaDataCriacao() {
		return Instant.now();
	}
	
	protected Instant recuperaDataExpiracao() {
		return recuperaDataCriacao().plus(1, ChronoUnit.HOURS);
	}
}