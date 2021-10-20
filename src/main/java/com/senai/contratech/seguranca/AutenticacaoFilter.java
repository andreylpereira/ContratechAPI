package com.senai.contratech.seguranca;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AutenticacaoFilter extends OncePerRequestFilter {

	@Autowired
	private JWTHelper jwtHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = request.getHeader("Authorization");
		
		
		if (request.getRequestURI().contains("/seguranca/login")) {
			filterChain.doFilter(request, response);
			return; 
		}
		
		if (token == null || !token.startsWith("Bearer")) {
			throw new ServletException("O token não foi informado");
		}

		token = token.substring(7);

		if (!jwtHelper.validarToken(token)) {
			throw new ServletException("O token é inválido");
		}

		autenticarUsuario(token);
		filterChain.doFilter(request, response);
	}

	private void autenticarUsuario(String token) {
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
				jwtHelper.getUsuarioDoToken(token), null, jwtHelper.getPermissoes(token));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
