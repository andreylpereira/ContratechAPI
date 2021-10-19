package com.senai.contratech.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//padrão
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(jsr250Enabled = true)
@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SegurancaConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/cadastro").permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.httpBasic()
				.and()
				.formLogin();

	}

	/*
	 * Em memoria
	 * 
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { auth.inMemoryAuthentication().passwordEncoder(new
	 * BCryptPasswordEncoder()) .withUser("andrey")
	 * .password("$2a$10$v1CxVZfDjoOCVYKDMzFm.e6/OSmaJJcdUiEw3BIAAr32HcWDPGpDu")
	 * .roles("USUARIO"); }
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder()).dataSource(dataSource)
				.usersByUsernameQuery("SELECT LOGIN, SENHA, ATIVO FROM USUARIO WHERE LOGIN = ?")
				.authoritiesByUsernameQuery("SELECT LOGIN, ROLE FROM USUARIO WHERE LOGIN = ?").rolePrefix("ROLE_");
	}
}
