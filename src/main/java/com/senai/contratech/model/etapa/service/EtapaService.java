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

	public void addEtapa(@PathVariable Long usuarioId, @PathVariable Long obraId, @RequestBody Etapa etapa) {

		Obra obra = obraRepository.findById(obraId).get();
		etapa.setObra(obra);
		obra.getEtapas().add(etapa);
		obraRepository.save(obra);
	}

	public List<Etapa> findByObraId(@PathVariable Long usuarioId, @PathVariable Long obraId)
			throws NotFoundException {

		if (!usuarioRepository.existsById(usuarioId) && !obraRepository.existsById(obraId)) {
			throw new NotFoundException("Usuário e/ou obra não encontrado(s)!");
		}
		return etapaRepository.findAllEtapasByObraId(obraId);
	}

}
