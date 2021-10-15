package com.senai.contratech.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.contratech.model.obra.entity.Obra;
import com.senai.contratech.model.obra.repository.ObraRepository;
import com.senai.contratech.model.usuario.repository.UsuarioRepository;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/api")
public class ObraController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ObraRepository obraRepository;

	@GetMapping("/usuarios/{usuarioId}/obras")
	public List<Obra> getObrasByUsuarioId(@PathVariable Long usuarioId) throws NotFoundException {

		if (!usuarioRepository.existsById(usuarioId)) {
			throw new NotFoundException("Usuário não encontrado!");
		}
		return obraRepository.findObraById(usuarioId);
	}

	@GetMapping("/usuarios/{usuarioId}/obras/{obraId}")
	public List<Obra> getObrasByObraId(@PathVariable Long usuarioId, @PathVariable Long obraId)
			throws NotFoundException {

		if (!usuarioRepository.existsById(usuarioId)) {
			throw new NotFoundException("Usuário não encontrado!");
		} else if (!obraRepository.existsById(obraId)) {
			throw new NotFoundException("Obra não encontrado!");
		}

		return obraRepository.findUsuarioObraById(usuarioId, obraId);
	}

	@PostMapping("/usuarios/{usuarioId}/obras")
	public Obra addObra(@PathVariable Long usuarioId, @Valid @RequestBody Obra obra) throws NotFoundException {
		return usuarioRepository.findById(usuarioId).map(usuario -> {
			obra.setUsuario(usuario);
			return obraRepository.save(obra);
		}).orElseThrow(() -> new NotFoundException("Usuário não encontrado!"));

	}
}
