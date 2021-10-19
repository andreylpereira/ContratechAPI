package com.senai.contratech.controllers;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.contratech.model.usuario.entity.Usuario;
import com.senai.contratech.model.usuario.service.UsuarioService;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/api")
@RolesAllowed(value = "ROLE_USUARIO")
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

	@PostMapping("/cadastro")
	public List<Usuario> AdicionarUsuario(@RequestBody Usuario usuario) {
		return usuarioService.addUsuario(usuario);
	}
}
