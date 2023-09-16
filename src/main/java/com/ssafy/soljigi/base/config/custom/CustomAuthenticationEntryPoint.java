package com.ssafy.soljigi.base.config.custom;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 인증이 안된 익명의 사용자가 인증이 필요한 엔드포인트로 접근하게 된다면 401
 */
@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException {

//		log.warn("1. CustomAuthenticationEntryPoint start");
		ObjectMapper objectMapper = new ObjectMapper();
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter()
			.write(objectMapper.writeValueAsString(
				ResponseEntity.badRequest().body(authException.getMessage())));
//		log.warn("2. CustomAuthenticationEntryPoint end " + response);
	}
}
