package com.senai.contratech.model.servico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.senai.contratech.model.servico.entity.Servico;


public interface ServicoRepository extends JpaRepository<Servico, Long>  {

	@Query(value = "SELECT * FROM servicos WHERE etapa_id = :idEtapa", nativeQuery = true)
	List<Servico> findAllServicosByIds(
			//@Param("usuarioId") Long usuarioId, @Param("obraId") Long obraId, 
			@Param("idEtapa") Long EtapaId);

}