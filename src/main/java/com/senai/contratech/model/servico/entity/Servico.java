package com.senai.contratech.model.servico.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.senai.contratech.model.etapa.entity.Etapa;

@Entity
@Table(name = "Servicos")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Servico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 5, max = 35, message = "O nome do serviço deve estar entre 5 e 35 caracteres")
	@Column(name = "nome_servico")
	private String nomeServico;

	@Min(value = 0, message = "O preço não pode ser menor que zero")
	private double preco;

	@Min(value = 0, message = "A quantidade têm que ser entre 0 e 00")
	@Max(value = 99, message = "A quantidade têm que ser entre 0 e 99")
	private int quantidade;

	@Min(value = 0, message = "A porcentagem têm que ser entre 0 e 100")
	@Max(value = 100, message = "A porcentagem têm que ser entre 0 e 100")
	private int porcentagem;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "etapa_id", nullable = false)
	@JsonIgnore
	private Etapa etapa;

	public Servico() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	public String getNomeServico() {
		return nomeServico;
	}

	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(int porcentagem) {
		this.porcentagem = porcentagem;
	}

}
