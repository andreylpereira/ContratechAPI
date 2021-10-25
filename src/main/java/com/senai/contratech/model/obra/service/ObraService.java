package com.senai.contratech.model.obra.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.senai.contratech.model.etapa.entity.Etapa;
import com.senai.contratech.model.etapa.repository.EtapaRepository;
import com.senai.contratech.model.obra.entity.Obra;
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

	public Optional<Object> addObra(@PathVariable Long usuarioId, @Valid @RequestBody Obra obra)
			throws ValidationException {
		try {
			return usuarioRepository.findById(usuarioId).map(usuario -> {
				obra.setUsuario(usuario);
				return obraRepository.save(obra);
			});
		} catch (Exception e) {
			throw new ValidationException("Não foi possível adicionar a obra");
		}
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

	
	public Obra relatorio(@PathVariable Long usuarioId, @PathVariable Long obraId)
			throws NotFoundException {

		try {
			Usuario usuario = usuarioRepository.getById(usuarioId);
			Obra recuperarObra = obraRepository.findById(obraId).get();

			List<Etapa> listaEtapas = recuperarObra.getEtapas();
			
			double valorTotal = 0;
			for(int i = 0; i < listaEtapas.size(); i++) {
				valorTotal += listaEtapas.get(i).getValorTotal();
				System.out.println(valorTotal);
			}
			recuperarObra.setValorTotalFinal(valorTotal);
			
			int percentualMedio = 0;
			for(int i = 0; i < listaEtapas.size(); i++) {
				percentualMedio += listaEtapas.get(i).getPercentualMedio();
				System.out.println(percentualMedio);
			}
			recuperarObra.setPercentualMedioFinal(Math.round(percentualMedio/listaEtapas.size()));
			//System.out.println("Media calculo" + (recuperarObra.getPercentualMedioFinal() * 0.01));
			//System.out.println("valor total" + recuperarObra.getValorTotalFinal());
			//recuperarObra.setValorTotalFinal(recuperarObra.getValorTotalFinal() * (recuperarObra.getPercentualMedioFinal() * 0.01));
			
			
			recuperarObra.setUsuario(usuario);
			usuarioRepository.save(usuario);
			return recuperarObra;
		} catch (Exception e) {
			throw new NotFoundException("Não foi possível atualizar o nome da obra");
		}
	}
	
}