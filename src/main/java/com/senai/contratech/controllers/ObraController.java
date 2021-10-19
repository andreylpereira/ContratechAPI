package com.senai.contratech.controllers;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.contratech.model.obra.entity.Obra;
import com.senai.contratech.model.obra.entity.Relatorio;
import com.senai.contratech.model.obra.service.ObraService;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/api")
@RolesAllowed(value = "ROLE_USUARIO")
public class ObraController {

	@Autowired
	private ObraService obraService;

	@GetMapping("/usuarios/{usuarioId}/obras")
	public List<Obra> PuxarTodasObrasDoUsuario(@PathVariable Long usuarioId) throws NotFoundException {
		return obraService.findAllByObraId(usuarioId);
	}

	@GetMapping("/usuarios/{usuarioId}/obras/{obraId}")
	public Obra puxarObraPelasIds(@PathVariable Long usuarioId, @PathVariable Long obraId) throws NotFoundException {
		return obraService.findByObraId(usuarioId, obraId);
	}

	@PostMapping("/usuarios/{usuarioId}/obras")
	public Obra adicionarObra(@PathVariable Long usuarioId, @Valid @RequestBody Obra obra) throws NotFoundException {
		return obraService.addObra(usuarioId, obra);
	}

	@DeleteMapping("/usuarios/{usuarioId}/obras/{obraId}")
	public void deleteObra(@PathVariable Long usuarioId, @PathVariable Long obraId) throws NotFoundException {
		obraService.delObra(usuarioId, obraId);
	}

	@PutMapping("/usuarios/{usuarioId}/obras/{obraId}")
	public Obra editarObra(@PathVariable Long usuarioId, @PathVariable Long obraId, @RequestBody Obra obra)
			throws NotFoundException {
		return obraService.updateObra(usuarioId, obraId, obra);

	}

	// relatório
	@GetMapping("/usuarios/{usuarioId}/obras/{obraId}/relatorio")
	public Relatorio puxarRelatorioPelasIds(@PathVariable Long usuarioId, @PathVariable Long obraId)
			throws NotFoundException {
		return obraService.relatorioObra(usuarioId, obraId);
	}
}
