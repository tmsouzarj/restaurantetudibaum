package br.com.estudos.restaurantetudibaum.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.restaurantetudibaum.controller.record.LoginRecordRequest;
import br.com.estudos.restaurantetudibaum.controller.record.LoginRecordResponse;
import br.com.estudos.restaurantetudibaum.services.LoginService;
import br.com.estudos.restaurantetudibaum.services.TokenService;
import io.jsonwebtoken.security.InvalidKeyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/login")
@Slf4j
//@Tag(name = "login", description = "Classe responsável por realizar o login do usuário.")
@Api(tags = "login", description = "Classe responsável por realizar o login do usuário.")
public class LoginController {
	
	@Autowired
	protected LoginService loginService;
	
	@Autowired
	protected TokenService tokenService;
	
	@PostMapping
	//@Operation(summary = "Realização a autenticação do usuário utilizando o seu usuário e a sua senha.")
	@ApiOperation(value = "Realização a autenticação do usuário utilizando o seu usuário e a sua senha.")
	
	/*@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Token de acesso gerado com sucesso."),
		@ApiResponse(responseCode = "500", description = "Falha ao realizar a geração do token de acesso.")
	})*/	
	public ResponseEntity<LoginRecordResponse> realizaLogin(@RequestBody @Valid LoginRecordRequest dados) throws InvalidKeyException, UnsupportedEncodingException {
		log.info("realizaLogin():dados do login {}", dados);
		
		return ResponseEntity.ok().body(new LoginRecordResponse(loginService.gerarTokenAcesso(dados)));	
	}
}