package br.com.estudos.restaurantetudibaum.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.restaurantetudibaum.controller.record.UsuarioRecordRequest;
import br.com.estudos.restaurantetudibaum.controller.record.UsuarioRecordResponse;
import br.com.estudos.restaurantetudibaum.services.UsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/usuarios")
@Slf4j
public class UsuarioController {
	
	@Autowired
	protected UsuarioService usuarioService;
		
	@Autowired
	protected MessageSource messageSource;
	
	protected Locale locale = Locale.of("pt", "BR");
	
	@PostMapping
	public ResponseEntity<String> salvar(@Valid @RequestBody UsuarioRecordRequest usuario) {
		log.info("salvar():dados do usuário {}", usuario);
		
		usuarioService.salvar(usuario);
		
		return ResponseEntity.ok(messageSource.getMessage("sucesso_operacao", null, locale));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> inativar(@PathVariable @Valid @NotNull Integer id) {
		log.info("inativar():id {}", id);

		usuarioService.atualizarStatus(id, false);
		
		return ResponseEntity.ok(messageSource.getMessage("inativar_usuario", null, locale));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<String> reativar(@PathVariable @Valid @NotNull Integer id) {
		log.info("reativar():id {}", id);

		usuarioService.atualizarStatus(id, true);
		
		return ResponseEntity.ok(messageSource.getMessage("reativar_usuario", null, locale));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioRecordResponse> buscarUsuarioPorId(@PathVariable @Valid @NotNull Integer id) {
		log.info("buscarUsuarioPorId():id {}", id);

		return ResponseEntity.ok().body(usuarioService.buscarPorId(id));
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<UsuarioRecordResponse>> buscarUsuarioPorNome(@PathVariable @Valid @NotBlank String nome) {
		log.info("buscarUsuarioPorNome():nome {}", nome);

		return ResponseEntity.ok().body(usuarioService.buscarPorNome(nome));
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioRecordResponse>> buscarUsuarios() {
		log.info("buscarUsuarios()");

		return ResponseEntity.ok().body(usuarioService.buscarUsuarios());
	}
}