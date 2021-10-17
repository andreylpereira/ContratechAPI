package com.senai.contratech.model.servico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.senai.contratech.model.etapa.entity.Etapa;
import com.senai.contratech.model.etapa.repository.EtapaRepository;
import com.senai.contratech.model.obra.repository.ObraRepository;
import com.senai.contratech.model.servico.entity.Servico;
import com.senai.contratech.model.servico.repository.ServicoRepository;
import com.senai.contratech.model.usuario.repository.UsuarioRepository;

@Service
public class ServicoService {

	@Autowired
	ObraRepository obraRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	EtapaRepository etapaRepository;

	@Autowired
	ServicoRepository servicoRepository;

	public List<Servico> findByIds(@PathVariable Long usuarioId, @PathVariable Long obraId,
			@PathVariable Long etapaId) {
		return servicoRepository.findAllServicosByIds(etapaId);
	}

	public void addServico(@PathVariable Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId,
			@RequestBody Servico servico) {
		Etapa etapa = etapaRepository.findEtapa(usuarioId, obraId, etapaId);
		servico.setEtapa(etapa);
		servico.setNomeServico("Serviço");
		servico.setPorcentagem(0);
		servico.setQuantidade(0);
		servico.setPreco(0);
		etapa.getServicos().add(servico);
		etapaRepository.save(etapa);
	}

	public void delServico(Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId,
			@PathVariable Long servicoId) {
		Servico servico = servicoRepository.findById(servicoId).get();
		servicoRepository.deleteById(servico.getId());
	}

	public void delAllServicos(@PathVariable Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId) {
		List<Servico> lista = servicoRepository.findAllServicosByIds(etapaId);
		lista.forEach(servico -> servicoRepository.deleteById(servico.getId()));

	}

//	// atualizar (fazer uma query dinamica pra atualizar a tabela servicos)
//	public void putAllServicos(@PathVariable Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId,
//			@RequestBody List<Servico> servicos) {
//
//		Optional<Etapa> findEtapa = null;
//		findEtapa = etapaRepository.findById(etapaId);
//		Etapa etapa = new Etapa();
//
//		if (!findEtapa.isEmpty()) {
//			etapa.setId(findEtapa.get().getId());
//			etapa.setObra(findEtapa.get().getObra());
//			etapa.setNomeEtapa(findEtapa.get().getNomeEtapa());
//			etapa.setServicos(servicos);
//			servicos.forEach(a -> a.setEtapa(etapa));
//		}
//
//		etapaRepository.save(etapa);
//	}

}