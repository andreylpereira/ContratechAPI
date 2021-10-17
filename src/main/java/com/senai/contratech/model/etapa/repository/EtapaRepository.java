package com.senai.contratech.model.etapa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.senai.contratech.model.etapa.entity.Etapa;

public interface EtapaRepository extends JpaRepository<Etapa, Long> {

	List<Etapa> findAllEtapasByObraId(Long obraId);

	@Query(value = "SELECT * FROM etapas WHERE (id = :idEtapa AND EXISTS (SELECT FROM obras WHERE usuario_id = :usuarioId and id = :obraId))", nativeQuery = true)
	Etapa findEtapa(@Param("usuarioId") Long usuarioId, @Param("obraId") Long obraId, @Param("idEtapa") Long EtapaId);

}
