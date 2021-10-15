package com.senai.contratech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.contratech.model.etapa.entity.Etapa;
import com.senai.contratech.model.etapa.repository.EtapaRepository;
import com.senai.contratech.model.etapa.service.EtapaService;
import com.senai.contratech.model.obra.repository.ObraRepository;
import com.senai.contratech.model.usuario.repository.UsuarioRepository;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/api/usuarios")
public class EtapaController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ObraRepository obraRepository;

	@Autowired
	private EtapaRepository etapaRepository;
	
	@Autowired
	private EtapaService etapaService;

	@GetMapping("/{usuarioId}/obras/{obraId}/etapas")
	public List<Etapa> getObrasByUsuarioId(@PathVariable Long usuarioId, @PathVariable Long obraId)
			throws NotFoundException {

		if (!usuarioRepository.existsById(usuarioId) && !obraRepository.existsById(obraId)) {
			throw new NotFoundException("Usuário e/ou obra não encontrado(s)!");
		}
		return etapaRepository.findEtapaById(obraId);
	}


	@PostMapping("/{usuarioId}/obras/{obraId}/etapas")
	public void addEtapa(@PathVariable Long usuarioId, @PathVariable Long obraId, @RequestBody Etapa etapa) {
		etapaService.addEtapa(usuarioId, obraId, etapa);
	}
}
