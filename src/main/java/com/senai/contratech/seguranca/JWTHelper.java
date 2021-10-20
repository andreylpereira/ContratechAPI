package com.senai.contratech.seguranca;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTHelper {

	@Value("${jwt.lifetime.token}")
	private Long lifeTimeToken;
	@Value("${jwt.secret}")
	private String tokenSecret;
	
	public String gerarToken(UserDetails usuario) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("usuario", usuario.getUsername());
		claims.put("permissoes", usuario.getAuthorities());
		
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + (lifeTimeToken * 1000)))
				.signWith(SignatureAlgorithm.HS512, tokenSecret)
				.compact();
	}
}
