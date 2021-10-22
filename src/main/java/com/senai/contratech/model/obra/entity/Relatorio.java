package com.senai.contratech.model.obra.entity;

import java.util.List;

import com.senai.contratech.model.etapa.entity.Etapa;

public class Relatorio {

	private Long idObra;
	private String nomeObra;
	private List<Etapa> etapas;
	private double valorTotal;
	private int mediaPorcentagem;

	public Long getIdObra() {
		return idObra;
	}

	public void setIdObra(Long idObra) {
		this.idObra = idObra;
	}

	public String getNomeObra() {
		return nomeObra;
	}

	public void setNomeObra(String nomeObra) {
		this.nomeObra = nomeObra;
	}

	public List<Etapa> getEtapas() {
		return etapas;
	}

	public void setEtapas(List<Etapa> etapas) {
		this.etapas = etapas;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getMediaPorcentagem() {
		return mediaPorcentagem;
	}

	public void setMediaPorcentagem(int mediaPorcentagem) {
		this.mediaPorcentagem = mediaPorcentagem;
	}

}
