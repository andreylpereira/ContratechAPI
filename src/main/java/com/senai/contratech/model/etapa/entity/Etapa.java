package com.senai.contratech.model.etapa.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.senai.contratech.model.obra.entity.Obra;
import com.senai.contratech.model.servico.entity.Servico;

@Entity
@Table(name = "Etapas")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Etapa {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome_etapa")
	private String nomeEtapa;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "obra_id", nullable = false)
	@JsonIgnore
	private Obra obra;

	@OneToMany(mappedBy = "etapa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Servico> servicos;
	
	
	public Etapa() {

	}

	
	
	public Etapa(String nomeEtapa) {
		this.nomeEtapa = nomeEtapa;
	}

	public String getNomeEtapa() {
		return nomeEtapa;
	}

	public void setNomeEtapa(String nomeEtapa) {
		this.nomeEtapa = nomeEtapa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obraId) {
		this.obra = obraId;
	}
	

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}


}
