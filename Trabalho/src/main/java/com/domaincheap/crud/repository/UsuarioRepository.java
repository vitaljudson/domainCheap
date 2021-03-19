package com.domaincheap.crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.domaincheap.crud.dominio.Usuario;

/** Esta classe é o repositório da classe Usuario.*/
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	@Query("select u from Usuario u where u.nome like %:nome%")
	List<Usuario> findByNome(@Param("nome") String nome);
	List<Usuario> findByNomeAndSenha(@Param("nome") String nome, @Param("senha") String senha);
	
	@Query("select u from Usuario u where u.email like %:email%")
	Optional<Usuario> findByEmail(@Param("email") String email);
}
