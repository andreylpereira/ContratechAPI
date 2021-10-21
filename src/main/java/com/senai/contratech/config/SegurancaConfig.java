package com.senai.contratech.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.senai.contratech.model.usuario.service.AutenticacaoUsuarioService;
import com.senai.contratech.seguranca.AutenticacaoFilter;

//@EnableWebSecurity
//@EnableGlobalMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class SegurancaConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AutenticacaoUsuarioService autenticacaoUsuarioService;
	
	@Autowired
	private AutenticacaoFilter autenticacaoFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http //cors().and()
//			.csrf().disable().authorizeRequests()
//			.antMatchers("/api/cadastro").permitAll()
//			.antMatchers("/seguranca/login").permitAll()
//			.anyRequest().authenticated();
		
		
		http.addFilterBefore(autenticacaoFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public AutenticacaoUsuarioService autenticacaoUsuarioService() {
		return new AutenticacaoUsuarioService();
	}

	@Bean
	public DaoAuthenticationProvider daoAutenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		provider.setUserDetailsService(autenticacaoUsuarioService);
		return provider;
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAutenticationProvider());
	}

}
