package com.senai.contratech.model.usuario.dto;

public class DtoToken {

	private Long id;
	private String login;
	private String token;
	private Boolean ativo = true;
	
	
	
	public DtoToken() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = true;
	}
	
	
	
}
