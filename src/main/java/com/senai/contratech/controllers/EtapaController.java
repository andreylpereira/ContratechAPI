package com.senai.contratech.controllers;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.contratech.model.etapa.entity.Etapa;
import com.senai.contratech.model.etapa.service.EtapaService;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/api/usuarios")
@RolesAllowed(value = "ROLE_USUARIO")
public class EtapaController {

	@Autowired
	private EtapaService etapaService;

	@GetMapping("/{usuarioId}/obras/{obraId}/etapas")
	public List<Etapa> puxarObrasDoUsuario(@PathVariable Long usuarioId, @PathVariable Long obraId)
			throws NotFoundException {
		return etapaService.findByObraId(usuarioId, obraId);
	}

	@GetMapping("/{usuarioId}/obras/{obraId}/etapas/{etapaId}")
	public Etapa puxarEtapaDaObra(@PathVariable Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId)
			throws NotFoundException {
		etapaService.findByIdsEtapa(usuarioId, obraId, etapaId);
		return etapaService.findByIdsEtapa(usuarioId, obraId, etapaId);
	}

	@PostMapping("/{usuarioId}/obras/{obraId}/etapas")
	public void adicionarEtapa(@PathVariable Long usuarioId, @PathVariable Long obraId, @RequestBody Etapa etapa) throws NotFoundException  {
		etapaService.addEtapa(usuarioId, obraId, etapa);
	}

	@PutMapping("/{usuarioId}/obras/{obraId}/etapas/{etapaId}")
	public Etapa editarEtapa(@PathVariable Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId,
			@RequestBody Etapa etapa) throws NotFoundException {
		return etapaService.updateEtapa(usuarioId, obraId, etapaId, etapa);

	}
	
	@DeleteMapping("/{usuarioId}/obras/{obraId}/etapas/{etapaId}")
	public void deleteEtapa(@PathVariable Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId) throws NotFoundException {		
		etapaService.delEtapa(usuarioId, obraId, etapaId);
	}

}
