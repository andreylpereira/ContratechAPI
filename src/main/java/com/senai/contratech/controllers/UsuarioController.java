package com.senai.contratech.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.contratech.model.usuario.entity.Usuario;
import com.senai.contratech.model.usuario.repository.UsuarioRepository;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/api")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/usuarios")
	public List<Usuario> listaUsuarios() {
		return usuarioRepository.findAll();
	}

	@GetMapping("/usuarios/{id}")
	public Usuario getUsuarioById(@PathVariable Long id) throws NotFoundException {
		Optional<Usuario> optUsuario = usuarioRepository.findById(id);
		if (optUsuario.isPresent()) {
			return optUsuario.get();
		} else {
			throw new NotFoundException("Não foi possível encontrar o usuário de id: " + id);
		}
	}

	@PostMapping("/usuarios")
	public List<Usuario> addUsuario(@RequestBody Usuario usuario) {
		System.out.println("Salvando usuário " + usuario.getNome());
		usuarioRepository.save(usuario);
		return usuarioRepository.findAll();
	}
}
