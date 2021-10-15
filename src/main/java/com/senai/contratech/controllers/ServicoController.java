package com.senai.contratech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.senai.contratech.model.servico.entity.Servico;
import com.senai.contratech.model.servico.service.ServicoService;


@RestController
@RequestMapping(value = "/api")
public class ServicoController {
	
	@Autowired
	private ServicoService servicoService;
	
	@GetMapping("/usuarios/{usuarioId}/obras/{obraId}/etapas/{etapaId}/servicos")
	public List<Servico> puxarListaServicosPelasIds(@PathVariable Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId) {
		return servicoService.findByIds(usuarioId, obraId, etapaId);
	}
	
	@PostMapping("/usuarios/{usuarioId}/obras/{obraId}/etapas/{etapaId}/servicos")
	public void adicionarEtapa(@PathVariable Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId, Servico servico) {
		servicoService.addService(usuarioId, obraId, etapaId, servico);
	}

}


	