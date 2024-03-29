package com.senai.contratech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.contratech.model.usuario.entity.Usuario;
import com.senai.contratech.model.usuario.service.UsuarioService;

@RestController
@RequestMapping(value = "/api")
public class CadastroController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/cadastro")
	public void AdicionarUsuario(@RequestBody Usuario usuario) {
		usuarioService.addUsuario(usuario);
	}
	@PostMapping("/cadastro/verificacao")
	public String VerificarUsuario(@RequestBody Usuario usuario) {
		System.out.println(usuario);
		Usuario retorno = usuarioService.findbyUsuarioName(usuario);
		if(retorno.getLogin() != "") {
			return retorno.getLogin();
		} else {
			return "";
		}
	}
}
