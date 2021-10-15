package com.senai.contratech.model.obra.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.senai.contratech.model.etapa.repository.EtapaRepository;
import com.senai.contratech.model.obra.entity.Obra;
import com.senai.contratech.model.obra.repository.ObraRepository;
import com.senai.contratech.model.usuario.repository.UsuarioRepository;

import javassist.NotFoundException;

@Service
public class ObraService {

	@Autowired
	ObraRepository obraRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	EtapaRepository etapaRepository;

	public List<Obra> findAllByObraId(@PathVariable Long usuarioId) throws NotFoundException {

		if (!usuarioRepository.existsById(usuarioId)) {
			throw new NotFoundException("Usuário não encontrado!");
		}
		return obraRepository.findByObraId(usuarioId);
	}

	public List<Obra> findByObraId(@PathVariable Long usuarioId, @PathVariable Long obraId)
			throws NotFoundException {

		if (!usuarioRepository.existsById(usuarioId)) {
			throw new NotFoundException("Usuário não encontrado!");
		} else if (!obraRepository.existsById(obraId)) {
			throw new NotFoundException("Obra não encontrado!");
		}

		return obraRepository.findByUsuarioObraId(usuarioId, obraId);
	}

	public Obra addObra(@PathVariable Long usuarioId, @Valid @RequestBody Obra obra) throws NotFoundException {
		return usuarioRepository.findById(usuarioId).map(usuario -> {
			obra.setUsuario(usuario);
			return obraRepository.save(obra);
		}).orElseThrow(() -> new NotFoundException("Usuário não encontrado!"));
	}

}
