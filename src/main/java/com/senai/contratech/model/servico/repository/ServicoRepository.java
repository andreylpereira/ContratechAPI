package com.senai.contratech.model.servico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.contratech.model.servico.entity.Servico;


public interface ServicoRepository extends JpaRepository<Servico, Long>  {


	
//	@Query(value = "SELECT * FROM obras WHERE usuario_id = :usuarioId", nativeQuery = true)
//	List<Obra> findObraById(@Param("usuarioId") Long usuarioId);
}