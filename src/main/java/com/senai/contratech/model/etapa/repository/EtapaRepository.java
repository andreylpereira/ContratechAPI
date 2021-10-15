package com.senai.contratech.model.etapa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.senai.contratech.model.etapa.entity.Etapa;

public interface EtapaRepository extends JpaRepository<Etapa, Long> {

	List<Etapa> findEtapaById(Long obraId);
	
}
