package com.senai.contratech.model.etapa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.senai.contratech.model.etapa.entity.Etapa;
import com.senai.contratech.model.obra.entity.Obra;
import com.senai.contratech.model.obra.repository.ObraRepository;

@Service
public class EtapaService {
	
	@Autowired ObraRepository obraRepository;

	public void addEtapa(@PathVariable Long usuarioId, @PathVariable Long obraId, @RequestBody Etapa etapa) {
		
		Obra obra = obraRepository.findById(obraId).get();
		etapa.setObra(obra);
		obra.getEtapas().add(etapa);
		obraRepository.save(obra);
		}
}
