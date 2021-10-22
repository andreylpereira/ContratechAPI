package com.senai.contratech.model.usuario.service;

import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

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

	public Usuario findbyUsuarioName(Usuario usuario) {
		Optional<Usuario> optUsuario = usuarioRepository.findByLogin(usuario.getLogin());
		return optUsuario.get();
	}

	public Usuario findbyUsuarioEmail(Usuario usuario) {
		Optional<Usuario> optUsuario = usuarioRepository.findByEmail(usuario.getEmail());
		return optUsuario.get();
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
		String pw = usuario.getSenha();
		
		if(pw.length() > 10 || pw.length() < 6) {
			throw new ValidationException("A senha têm que ter entre 6 e 10 caracteres");
		}
		if (usuarioRepository.findByLogin(usuario.getLogin()).isPresent()) {
			throw new ValidationException("Usuario cadastrado");
		}
		if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
			throw new ValidationException("E-mail cadastrado");
		} else {
			String senha = new BCryptPasswordEncoder().encode(usuario.getSenha());
			usuario.setSenha(senha);

			usuarioRepository.save(usuario);
			return usuarioRepository.findAll();
		}
	}
}
