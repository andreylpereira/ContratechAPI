package com.senai.contratech.model.etapa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.senai.contratech.model.etapa.entity.Etapa;
import com.senai.contratech.model.etapa.repository.EtapaRepository;
import com.senai.contratech.model.obra.entity.Obra;
import com.senai.contratech.model.obra.repository.ObraRepository;
import com.senai.contratech.model.usuario.repository.UsuarioRepository;

import javassist.NotFoundException;

@Service
public class EtapaService {

	@Autowired
	private ObraRepository obraRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EtapaRepository etapaRepository;

	public void addEtapa(@PathVariable Long usuarioId, @PathVariable Long obraId, @RequestBody Etapa etapa)
			throws NotFoundException {

		try {
			Obra obra = obraRepository.findById(obraId).get();
			etapa.setObra(obra);
			obra.getEtapas().add(etapa);
			obraRepository.save(obra);
		} catch (Exception e) {
			throw new NotFoundException("Não foi possível adicionar um etapa nesta obra");
		}
	}

	public List<Etapa> findByObraId(@PathVariable Long usuarioId, @PathVariable Long obraId) throws NotFoundException {

		if (!usuarioRepository.existsById(usuarioId) || !obraRepository.existsById(obraId)) {
			throw new NotFoundException("Não foi possível recuperar a lista de etapas da obra");
		}
		return etapaRepository.findAllEtapasByObraId(obraId);
	}

	public Etapa findByIdsEtapa(@PathVariable Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId)
			throws NotFoundException {

		try {
			return etapaRepository.findEtapa(usuarioId, obraId, etapaId);
		} catch (Exception e) {
			throw new NotFoundException("Não foi possível recuperar a etapa");
		}
	}

	public Etapa updateEtapa(@PathVariable Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId,
			@RequestBody Etapa etapa) throws NotFoundException {

		try {
			Obra obra = obraRepository.findById(obraId).get();
			Etapa recuperarEtapa = etapaRepository.findEtapa(usuarioId, obraId, etapaId);
			recuperarEtapa.setNomeEtapa(etapa.getNomeEtapa());
			etapa.setObra(obra);
			obraRepository.save(obra);
			return etapa;

		} catch (Exception e) {
			throw new NotFoundException("Não foi possível atualizar o nome da etapa");
		}
		
	}

	public void delEtapa(@PathVariable Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId)
			throws NotFoundException {
		try {
			Etapa etapa = etapaRepository.findEtapa(usuarioId, obraId, etapaId);
			etapaRepository.delete(etapa);
		} catch (Exception e) {
			throw new NotFoundException("Não foi possível deletar a etapa");
		}
	}

}
