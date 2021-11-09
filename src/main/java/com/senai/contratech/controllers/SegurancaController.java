package com.senai.contratech.controllers;

import java.util.Optional;

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

import com.senai.contratech.model.usuario.dto.DtoToken;
import com.senai.contratech.model.usuario.entity.Usuario;
import com.senai.contratech.model.usuario.repository.UsuarioRepository;
import com.senai.contratech.model.usuario.service.AutenticacaoUsuarioService;
import com.senai.contratech.seguranca.JWTHelper;

@RestController
@RequestMapping("/api")
public class SegurancaController {

	@Autowired
	private AutenticacaoUsuarioService autenticacaoUsuarioService;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private JWTHelper jwtHelper;

	@PostMapping("/seguranca/login")
	public ResponseEntity<DtoToken> buscarToken(@RequestBody Usuario usuario) {
		
		try {
			UserDetails detalhesUsuario = autenticacaoUsuarioService.loadUserByUsername(usuario.getLogin());
			if (!new BCryptPasswordEncoder().matches(usuario.getSenha(), detalhesUsuario.getPassword())) {
				Optional<Usuario> user = usuarioRepository.findByLogin(usuario.getLogin());
				DtoToken token = new DtoToken();
				token.setId(user.get().getId());
				token.setLogin(usuario.getLogin());
				token.setToken(jwtHelper.gerarToken(detalhesUsuario));
				return new ResponseEntity<>(token, HttpStatus.UNAUTHORIZED);
			}
			
			Optional<Usuario> user = usuarioRepository.findByLogin(usuario.getLogin());
			DtoToken token = new DtoToken();
			token.setId(user.get().getId());
			token.setLogin(usuario.getLogin());
			token.setToken(jwtHelper.gerarToken(detalhesUsuario));
			//jwtHelper.gerarToken(detalhesUsuario)
			
			return new ResponseEntity<>(token, HttpStatus.OK);
		} catch (UsernameNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
}
