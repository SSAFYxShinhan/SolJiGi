package com.ssafy.soljigi.user.config.custom;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.soljigi.user.dto.response.Response;
import com.ssafy.soljigi.user.error.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException {

		log.warn("CustomAuthenticationEntryPoint start");
		ObjectMapper objectMapper = new ObjectMapper();
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter()
			.write(objectMapper.writeValueAsString(
				ResponseEntity.badRequest().body(Response.error(ErrorCode.UNREACHABLE_SERVICE))));
		log.warn("CustomAuthenticationEntryPoint end" + response);
	}
}
