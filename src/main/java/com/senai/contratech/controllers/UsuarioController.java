package com.senai.contratech.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.contratech.model.usuario.entity.Usuario;
import com.senai.contratech.model.usuario.service.UsuarioService;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/api")
@Secured(value = "ROLE_USUARIO")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/usuarios")
	public List<Usuario> puxarTodosUsuarios() {
		return usuarioService.findAllUsuarios();
	}

	
	@GetMapping("/usuarios/{id}")
	public Usuario puxarUsuarioPelaId(@PathVariable Long id) throws NotFoundException {
		return usuarioService.findByUsuarioId(id);
	}
	
}
