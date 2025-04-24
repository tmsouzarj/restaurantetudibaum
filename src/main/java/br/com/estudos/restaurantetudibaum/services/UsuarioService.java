package br.com.estudos.restaurantetudibaum.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estudos.restaurantetudibaum.controller.record.UsuarioRecordRequest;
import br.com.estudos.restaurantetudibaum.controller.record.UsuarioRecordResponse;
import br.com.estudos.restaurantetudibaum.entities.DadosEndereco;
import br.com.estudos.restaurantetudibaum.entities.Usuario;
import br.com.estudos.restaurantetudibaum.mappers.UsuarioMapper;
import br.com.estudos.restaurantetudibaum.repositories.impl.DadosEnderecoRepository;
import br.com.estudos.restaurantetudibaum.repositories.impl.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	DadosEnderecoRepository dadosEnderecoRepository;

	public void salvar(UsuarioRecordRequest usuarioRecord) {
		Optional<Usuario> dadosUsuario = usuarioRepository.recuperaDadosUsuarioPorLogin(usuarioRecord.login());
		
		Usuario usuario;
		
		if(dadosUsuario.isEmpty()) {
			usuario = UsuarioMapper.toUsuario(usuarioRecord);
		} else {
			usuario = dadosUsuario.get();
			
			setaDadosAtualizacao(usuarioRecord, usuario);
		}
		
		salvar(usuario);
	}

	public List<UsuarioRecordResponse> buscarPorNome(String nome) {
		return usuarioRepository.recuperaDadosUsuarioPorNome(nome)
                			    .stream()
                			    .map(u -> UsuarioMapper.toUsuarioRecord(u))
                			    .collect(Collectors.toList());
	}

	public UsuarioRecordResponse buscarPorId(Integer id) {
		return UsuarioMapper.toUsuarioRecord(usuarioRepository.recuperaDadosUsuarioPorId(id));
	}
	
	public List<UsuarioRecordResponse> buscarUsuarios() {
		return usuarioRepository.recuperaDadosUsuarios()
				                .stream()
				                .map(u -> UsuarioMapper.toUsuarioRecord(u))
				                .collect(Collectors.toList());
	}

	public void atualizarStatus(Integer id, boolean isAtivo) {
		Usuario usuario  = usuarioRepository.recuperaDadosUsuarioPorId(id);
		
		usuario.setIsAtivo(isAtivo);
			
		salvar(usuario);
	}
	
	public void salvar(Usuario usuario) {
		usuarioRepository.salvar(usuario);
	}
	
	protected void setaDadosAtualizacao(UsuarioRecordRequest usuarioRecord, Usuario usuario) {
		usuario.setNome(usuarioRecord.nome());
		usuario.setEmail(usuarioRecord.email());
		usuario.setSenha(usuarioRecord.senha());
		usuario.setDataAtualizacao(LocalDateTime.now());
		
		Optional<DadosEndereco> dados = dadosEnderecoRepository.recuperaDadosEndereco(usuario.getId());
		
		DadosEndereco dadosEndereco;
		
		if (dados.isPresent()) {
			dadosEndereco = dados.get();
		} else {
			dadosEndereco = new DadosEndereco();
		}
		
		dadosEndereco.setBairro(usuarioRecord.dadosEndereco().bairro());
		dadosEndereco.setCep(usuarioRecord.dadosEndereco().cep());
		dadosEndereco.setCidade(usuarioRecord.dadosEndereco().cidade());
		dadosEndereco.setComplemento(usuarioRecord.dadosEndereco().complemento());
		dadosEndereco.setEndereco(usuarioRecord.dadosEndereco().endereco());
		dadosEndereco.setEstado(usuarioRecord.dadosEndereco().estado());
		dadosEndereco.setNumero(usuarioRecord.dadosEndereco().numero());
		dadosEndereco.setSemNumero(usuarioRecord.dadosEndereco().semNumero());
		
		usuario.setDadosEndereco(dadosEndereco);
	}
}