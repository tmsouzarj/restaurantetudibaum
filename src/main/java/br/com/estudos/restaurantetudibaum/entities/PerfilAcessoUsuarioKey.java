package br.com.estudos.restaurantetudibaum.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class PerfilAcessoUsuarioKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id_usuario")
	private Integer idUsuario;
	
	@Column(name = "id_perfil_acesso")
	private Integer idPerfilAcesso;
}
