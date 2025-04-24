package br.com.estudos.restaurantetudibaum.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;
	private String login;
	private String senha;
	
	@Column(name = "data_criacao", nullable = false)
	private LocalDateTime dataCriacao;
	
	@Column(name = "data_atualizacao", nullable = true)
	private LocalDateTime dataAtualizacao;
	
	@Column(name = "ativo", columnDefinition = "tinyint")
	private Boolean isAtivo;
	
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn(name = "id_usuario")
	private DadosEndereco dadosEndereco;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<PerfilAcessoUsuario> acessos;
	
	public void setDadosEndereco(DadosEndereco dadosEndereco) {
		this.dadosEndereco = dadosEndereco;
		this.dadosEndereco.setUsuario(this);
	}
}