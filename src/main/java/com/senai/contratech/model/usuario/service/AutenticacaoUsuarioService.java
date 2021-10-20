package com.senai.contratech.model.usuario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.senai.contratech.model.usuario.dto.DetalhesUsuario;
import com.senai.contratech.model.usuario.entity.Usuario;
import com.senai.contratech.model.usuario.repository.UsuarioRepository;


public class AutenticacaoUsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByLogin(login);
		if(usuario.isEmpty()) {
			throw new UsernameNotFoundException("Usuário inválido");
		}
		return new DetalhesUsuario(usuario.get());
	}

	
}
