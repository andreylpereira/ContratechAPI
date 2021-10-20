package com.senai.contratech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.contratech.model.usuario.entity.Usuario;
import com.senai.contratech.model.usuario.service.AutenticacaoUsuarioService;
import com.senai.contratech.seguranca.JWTHelper;

@RestController
@RequestMapping("/seguranca")
public class SegurancaController {

	@Autowired
	private AutenticacaoUsuarioService autenticacaoUsuarioService;
	
	@Autowired
	private JWTHelper jwtHelper;
	
	
	@PostMapping("/login")
	public ResponseEntity<String> buscarToken(@RequestBody Usuario usuario) {
		try {
			UserDetails detalhesUsuario = autenticacaoUsuarioService.loadUserByUsername(usuario.getLogin());
			if(!new BCryptPasswordEncoder().matches(usuario.getSenha(), detalhesUsuario.getPassword())) {
				return new ResponseEntity<>(jwtHelper.gerarToken(detalhesUsuario), HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<String>(jwtHelper.gerarToken(detalhesUsuario), HttpStatus.OK);
		} catch (UsernameNotFoundException e) {
			return new ResponseEntity<>("Usuário Inválido", HttpStatus.UNAUTHORIZED);
		}
	}
}
