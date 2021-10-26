package com.senai.contratech.model.servico.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

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

import javassist.NotFoundException;

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

	public List<Servico> findByIds(@PathVariable Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId)
			throws NotFoundException {
		if (!servicoRepository.findAllServicosByIds(etapaId).isEmpty()) {
			return servicoRepository.findAllServicosByIds(etapaId);
		} else {
			throw new NotFoundException("Não foi possível recuperar a lista de serviços dessa etapa.");
		}

	}

	public void addServico(@PathVariable Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId,
			@RequestBody Servico servico) throws NotFoundException {

		try {
			Etapa etapa = etapaRepository.findEtapa(usuarioId, obraId, etapaId);
			servico.setEtapa(etapa);
			servico.setNomeServico("Serviço");
			servico.setPorcentagem(0);
			servico.setQuantidade(0);
			servico.setPreco(0);
			etapa.getServicos().add(servico);
			etapaRepository.save(etapa);
		} catch (Exception e) {
			throw new NotFoundException("Não foi possível adicionar um serviço a lista");

		}

	}

	public void delServico(Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId,
			@PathVariable Long servicoId) throws NotFoundException {

		if (!servicoRepository.findById(servicoId).isEmpty()) {
			Servico servico = servicoRepository.findById(servicoId).get();
			servicoRepository.deleteById(servico.getId());
		} else {
			throw new NotFoundException("Não foi possível deletar o serviço selecionado");
		}

	}

	public void delAllServicos(@PathVariable Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId)
			throws NotFoundException {
		if (!servicoRepository.findAllServicosByIds(etapaId).isEmpty()) {
			List<Servico> lista = servicoRepository.findAllServicosByIds(etapaId);
			lista.forEach(servico -> servicoRepository.deleteById(servico.getId()));
		} else {
			throw new NotFoundException("Não foi possível deletar a lista de serviços");
		}

	}

	public void updateAllServicos(@PathVariable Long usuarioId, @PathVariable Long obraId, @PathVariable Long etapaId,
			@RequestBody List<Servico> servicos) throws NotFoundException {

		
		if (etapaRepository.findById(etapaId).isPresent()) {
			Optional<Etapa> findEtapa = null;
			findEtapa = etapaRepository.findById(etapaId);
			Etapa etapa = new Etapa();
			
			if (!findEtapa.isEmpty()) {
				etapa.setId(findEtapa.get().getId());
				etapa.setObra(findEtapa.get().getObra());
				etapa.setNomeEtapa(findEtapa.get().getNomeEtapa());
				etapa.setServicos(servicos);
				servicos.forEach(a -> a.setEtapa(etapa));
		
				double valorTotal = 0;
				for(int i = 0; i < servicos.size(); i++) {
					valorTotal += ((servicos.get(i).getPreco() * servicos.get(i).getQuantidade()) * (servicos.get(i).getPorcentagem() * 0.01));
				}
				BigDecimal bd = new BigDecimal(valorTotal).setScale(2, RoundingMode.HALF_UP);
				etapa.setValorTotal(bd.doubleValue());
				
				int percentualMedio = 0;
				for(int i = 0; i < servicos.size(); i++) {
					percentualMedio += servicos.get(i).getPorcentagem();
				}
				etapa.setPercentualMedio(Math.round(percentualMedio/servicos.size()));

			}

			etapaRepository.save(etapa);
		} else {
			throw new NotFoundException("Não foi possível atualizar a lista de serviços");
		}
	}

}