package com.senai.contratech.model.usuario.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.senai.contratech.model.obra.entity.Obra;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 6, max = 20, message = "O login deve estar entre 5 e 25 caracteres")
	private String login;
	@NotBlank
	@Size(max = 30, message = "O nome deve ter no maximo 25 caracteres")
	private String nome;
	@NotBlank
	@Size(max = 30, message = "O nome deve ter no maximo 25 caracteres")
	private String sobrenome;
	@NotBlank
	@Email(message = "Email tem que ser válido")
	private String email;
	 
	//@NotBlank
	//@Size(min = 6, max= 10, message = "O valor têm que estar entre 6 e 10 caracteres")
	private String senha;
	private Boolean ativo = true;

	private String role = "ROLE_USUARIO";

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Obra> obras;

	public Usuario() {
	}

	public Usuario(String login, String nome, String sobrenome, String email, String senha, Boolean ativo,
			String role) {
		this.login = login;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.ativo = true;
		this.role = "ROLE_USUARIO";
	}

	public Usuario(String login, Long id) {
		// TODO Auto-generated constructor stub
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setObras(List<Obra> obras) {
		this.obras = obras;
	}

	public List<Obra> getObras() {
		return this.obras;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
