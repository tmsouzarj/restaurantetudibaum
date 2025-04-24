package br.com.estudos.restaurantetudibaum.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario_has_perfil_acesso")
public class PerfilAcessoUsuario {

	@EmbeddedId
	private PerfilAcessoUsuarioKey id;
	
	@ManyToOne
	@MapsId("idUsuario")
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@MapsId("idPerfilAcesso")
	@JoinColumn(name = "id_perfil_acesso")
	private PerfilAcesso perfilAcesso;
	
	@Column(name = "data_inclusao")
	private LocalDateTime dataInclusao;

	@Column(name = "data_remocao")
	private LocalDateTime dataRemocao;
}
