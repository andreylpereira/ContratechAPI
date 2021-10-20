package com.senai.contratech.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.senai.contratech.model.usuario.service.AutenticacaoUsuarioService;

//padrão
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true, securedEnabled = true)
//, prePostEnabled = true) retirado

public class SegurancaConfig extends WebSecurityConfigurerAdapter {

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http
			.authorizeRequests()
			.antMatchers("/api/cadastro/**")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and().httpBasic()
			.and().formLogin()
			;
	}
	
	
	@Bean
	public AutenticacaoUsuarioService autenticacaoUsuarioService() {
		return new AutenticacaoUsuarioService();
	}

	
	@Bean
	public DaoAuthenticationProvider daoAutenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		provider.setUserDetailsService(autenticacaoUsuarioService());
		return provider;
	}

	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAutenticationProvider());
	}


}
