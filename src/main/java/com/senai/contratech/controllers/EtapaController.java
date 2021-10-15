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

import com.senai.contratech.model.etapa.service.EtapaService;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/api/usuarios")
public class EtapaController {

	@Autowired
	private EtapaService etapaService;

	@GetMapping("/{usuarioId}/obras/{obraId}/etapas")
	public List<Etapa> puxarObrasDoUsuario(@PathVariable Long usuarioId, @PathVariable Long obraId) throws NotFoundException {
		return etapaService.findByObraId(usuarioId, obraId);
	}

	@PostMapping("/{usuarioId}/obras/{obraId}/etapas")
	public void adicionarEtapa(@PathVariable Long usuarioId, @PathVariable Long obraId, @RequestBody Etapa etapa) {
		etapaService.addEtapa(usuarioId, obraId, etapa);
	}
}
