package com.senai.contratech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.contratech.model.servico.entity.Servico;
import com.senai.contratech.model.servico.service.ServicoService;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/api")
public class ServicoController {

	@Autowired
	private ServicoService servicoService;

	@GetMapping("/usuarios/{usuarioId}/obras/{obraId}/etapas/{etapaId}/servicos")
	@Secured(value = "ROLE_USUARIO")
	public List<Servico> puxarListaServicosPelasIds(@PathVariable Long usuarioId, @PathVariable Long obraId,
			@PathVariable Long etapaId) throws NotFoundException {
		return servicoService.findByIds(usuarioId, obraId, etapaId);
	}

	@PostMapping("/usuarios/{usuarioId}/obras/{obraId}/etapas/{etapaId}/servicos")
	@Secured(value = "ROLE_USUARIO")
	public void adicionarServico(@PathVariable Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId,
			Servico servico) throws NotFoundException {
		servicoService.addServico(usuarioId, obraId, etapaId, servico);
	}

	@DeleteMapping("/usuarios/{usuarioId}/obras/{obraId}/etapas/{etapaId}/servicos/{servicoId}")
	@Secured(value = "ROLE_USUARIO")
	public void deletarServico(Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId,
			@PathVariable Long servicoId) throws NotFoundException {
		servicoService.delServico(usuarioId, obraId, etapaId, servicoId);
	}

	@DeleteMapping("/usuarios/{usuarioId}/obras/{obraId}/etapas/{etapaId}/servicos/")
	@Secured(value = "ROLE_USUARIO")
	public void deletarTodosServicos(Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId) throws NotFoundException {
		servicoService.delAllServicos(usuarioId, obraId, etapaId);
	}

	@PutMapping("/usuarios/{usuarioId}/obras/{obraId}/etapas/{etapaId}/servicos/atualizar")
	@Secured(value = "ROLE_USUARIO")
	public void atualizarTodosServicos(Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId,
			@RequestBody List<Servico> servicos) throws NotFoundException {
		servicoService.putAllServicos(usuarioId, obraId, etapaId, servicos);
	}

}
