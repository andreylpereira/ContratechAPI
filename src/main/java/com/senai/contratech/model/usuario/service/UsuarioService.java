package com.senai.contratech.model.usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.senai.contratech.model.usuario.entity.Usuario;
import com.senai.contratech.model.usuario.repository.UsuarioRepository;

import javassist.NotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> findAllUsuarios() {
		return usuarioRepository.findAll();
	}

	public Usuario findByUsuarioId(@PathVariable Long id) throws NotFoundException {
		Optional<Usuario> optUsuario = usuarioRepository.findById(id);
		if (optUsuario.isPresent()) {
			return optUsuario.get();
		} else {
			throw new NotFoundException("Não foi possível encontrar o usuário de id: " + id);
		}
	}

	public List<Usuario> addUsuario(@RequestBody Usuario usuario) {
		
		String senha = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senha);
		
		usuarioRepository.save(usuario);
		return usuarioRepository.findAll();
	}
}
