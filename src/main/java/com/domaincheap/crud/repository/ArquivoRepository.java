package com.domaincheap.crud.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import com.domaincheap.crud.dominio.Arquivo;


/** Esta classe é o repositório da classe Arquivo.*/
public interface ArquivoRepository extends JpaRepository<Arquivo, Long>{

}
