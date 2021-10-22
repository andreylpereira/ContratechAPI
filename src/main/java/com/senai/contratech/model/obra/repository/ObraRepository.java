package com.senai.contratech.model.obra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.senai.contratech.model.obra.entity.Obra;

public interface ObraRepository extends JpaRepository<Obra, Long> {

	@Query(value = "SELECT * FROM obras WHERE usuario_id = :usuarioId", nativeQuery = true)
	List<Obra> findByObraId(@Param("usuarioId") Long usuarioId);

	@Query(value = "SELECT * FROM obras WHERE usuario_id = :usuarioId and id = :obraId", nativeQuery = true)
	Obra findByUsuarioObraId(@Param("usuarioId") Long usuarioId, @Param("obraId") Long obraId);
}
