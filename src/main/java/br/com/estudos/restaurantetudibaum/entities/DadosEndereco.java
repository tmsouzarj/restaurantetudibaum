package br.com.estudos.restaurantetudibaum.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "endereco")
public class DadosEndereco {
	@Id
	@Column(name = "id_usuario")
	private Integer id;
	private String cidade;
	private String cep;
	private String bairro;
	private String endereco;
	private String estado;
	private Integer numero;
	private String complemento;
	@Column(name = "sem_numero", columnDefinition = "tinyint")
	private Boolean semNumero;

	@OneToOne
	@MapsId
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
}