package com.domaincheap.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.domaincheap.crud.dominio.Dominio;

/** Esta classe é o repositório da classe Dominio.*/
public interface DominioRepository extends JpaRepository<Dominio, Integer> {
	@Query("select d from Dominio d where d.nome like %:nome%")
	List<Dominio> findByNome(@Param("nome") String nome);
}
