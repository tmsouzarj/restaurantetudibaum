package br.com.estudos.restaurantetudibaum.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.estudos.restaurantetudibaum.entities.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	@Query("select u "
		 + "from Usuario u "
		 + "where u.login = ?1"
		 + "  and u.senha = ?2"
		 + "  and u.isAtivo = true")
	Optional<Usuario> findUsuarioAtivoPorLoginSenha(@Param("1") String login, @Param("2") String senha);

	Optional<Usuario> findByLoginAndIsAtivoTrue(String login);
	
	List<Usuario> findAllByIsAtivoTrue();
	
	List<Usuario> findAllByNomeContainsIgnoreCaseAndIsAtivoTrueOrderByNomeAsc(String nome);
}