package com.senai.contratech.model.obra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.senai.contratech.model.obra.entity.Obra;


public interface ObraRepository extends JpaRepository<Obra, Long> {
	
	@Query(value = "SELECT * FROM obras WHERE usuario_id = :usuarioId", nativeQuery = true)
	List<Obra> findObraById(@Param("usuarioId") Long usuarioId);

	@Query(value = "SELECT * FROM obras WHERE usuario_id = :usuarioId and id = :obraId", nativeQuery = true)
	List<Obra> findUsuarioObraById(@Param("usuarioId") Long usuarioId, @Param("obraId") Long obraId);

	@Query(value = "SELECT * FROM obras WHERE usuario_id = :usuarioId and id = :obraId", nativeQuery = true)
	boolean findUsuarioObraByIdBoolean(@Param("usuarioId") Long usuarioId, @Param("obraId") Long obraId);
}
