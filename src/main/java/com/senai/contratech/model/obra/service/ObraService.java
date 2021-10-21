package com.senai.contratech.model.obra.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.senai.contratech.model.etapa.repository.EtapaRepository;
import com.senai.contratech.model.obra.entity.Obra;
import com.senai.contratech.model.obra.entity.Relatorio;
import com.senai.contratech.model.obra.repository.ObraRepository;
import com.senai.contratech.model.usuario.entity.Usuario;
import com.senai.contratech.model.usuario.repository.UsuarioRepository;

import javassist.NotFoundException;

@Service
public class ObraService {

	@Autowired
	ObraRepository obraRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	EtapaRepository etapaRepository;

	public List<Obra> findAllByObraId(@PathVariable Long usuarioId) throws NotFoundException {

		if (!usuarioRepository.existsById(usuarioId)) {
			throw new NotFoundException("Não foi possível encontrar a lista de obras pois o usuário não existe");
		}
		return obraRepository.findByObraId(usuarioId);
	}

	public Obra findByObraId(@PathVariable Long usuarioId, @PathVariable Long obraId) throws NotFoundException {

		if (!usuarioRepository.existsById(usuarioId)) {
			throw new NotFoundException("Não foi possível encontrar esta obra pois o usuário não existe");
		} else if (!obraRepository.existsById(obraId)) {
			throw new NotFoundException("Não foi possível encontrar esta obra");
		}

		return obraRepository.findByUsuarioObraId(usuarioId, obraId);
	}

	public Obra addObra(@PathVariable Long usuarioId, @Valid @RequestBody Obra obra) throws NotFoundException {
		return usuarioRepository.findById(usuarioId).map(usuario -> {
			obra.setUsuario(usuario);
			return obraRepository.save(obra);
		}).orElseThrow(() -> new NotFoundException("Não é possível adicionar esta obra"));
	}

	public void delObra(@PathVariable Long usuarioId, @PathVariable Long obraId) throws NotFoundException {
		try {
			Obra obra = obraRepository.findByUsuarioObraId(usuarioId, obraId);
			obraRepository.delete(obra);
		} catch (Exception e) {
			throw new NotFoundException("Não foi possível deletar a obra");
		}

	}

	public Obra updateObra(@PathVariable Long usuarioId, @PathVariable Long obraId, @RequestBody Obra obra)
			throws NotFoundException {

		try {
			Usuario usuario = usuarioRepository.getById(usuarioId);
			Obra recuperarObra = obraRepository.findById(obraId).get();

			recuperarObra.setNomeObra(obra.getNomeObra());
			recuperarObra.setUsuario(usuario);
			usuarioRepository.save(usuario);
			return recuperarObra;
		} catch (Exception e) {
			throw new NotFoundException("Não foi possível atualizar o nome da obra");
		}
	}
	
	public Relatorio relatorioObra(@PathVariable Long usuarioId, @PathVariable Long obraId) {
		Obra recuperarObra = obraRepository.findById(obraId).get();
		Relatorio relatorio = new Relatorio();
		relatorio.setIdObra(recuperarObra.getId());
		relatorio.setNomeObra(recuperarObra.getNomeObra());
		relatorio.setEtapas(recuperarObra.getEtapas());

		return relatorio;

	}
}