package br.com.estudos.restaurantetudibaum.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "perfil_acesso")
public class PerfilAcesso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	
	@Column(name = "data_criacao")
	private LocalDate dataCriacao;
	
	@Column(name = "data_inativacao")
	private LocalDate dataInativacao;
	
	@OneToMany(mappedBy = "perfilAcesso", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PerfilAcessoUsuario> acessosUsuario;
}
