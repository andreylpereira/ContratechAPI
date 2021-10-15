package com.senai.contratech.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.senai.contratech.model.obra.entity.Obra;
import com.senai.contratech.model.obra.service.ObraService;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/api")
public class ObraController {

	@Autowired
	private ObraService obraService;


	@GetMapping("/usuarios/{usuarioId}/obras")
	public List<Obra> PuxarTodasObrasDoUsuario(@PathVariable Long usuarioId)
			throws NotFoundException {
		return obraService.findAllByObraId(usuarioId);
	}

	@GetMapping("/usuarios/{usuarioId}/obras/{obraId}")
	public List<Obra> puxarObraPelasIds(@PathVariable Long usuarioId,
			@PathVariable Long obraId)
			throws NotFoundException {
		return obraService.findByObraId(usuarioId, obraId);
	}

	@PostMapping("/usuarios/{usuarioId}/obras")
	public Obra adicionarObra(@PathVariable Long usuarioId,
			@Valid @RequestBody Obra obra) throws NotFoundException {
		return obraService.addObra(usuarioId, obra);
	}
	
	
	@PutMapping("/usuarios/{usuarioId}/obras/{obraId}")
	public Obra editarObra(@PathVariable Long usuarioId, @PathVariable Long obraId, @RequestBody Obra obra) {
		return obraService.updateObra(usuarioId, obraId, obra);
		
	}
	

	//funcionou
//	@PutMapping("/usuarios/{usuarioId}/obras/{obraId}")
//	public void EditarObra(@PathVariable Long usuarioId, @PathVariable int obraId, @RequestBody Obra obra) {
//		Usuario usuario = usuarioRepository.getById(usuarioId);
//		obra.setUsuario(usuario);
//		usuario.getObras().get(obraId-1).setNomeObra(obra.getNomeObra());
//		usuarioRepository.save(usuario);
//	}
}
