package com.ssafy.soljigi.base.config.custom;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

/**
 * 인증은 완료되었으나 해당 앤드포인트로 접근할 권한이 없을 경우 403forbidden
 */
@Component
@Log4j2
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
		AccessDeniedException accessDeniedException) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();

//		log.warn("1. CustomAccessDeniedHandler start");
		ResponseEntity<?> body = ResponseEntity.badRequest()
			.body(accessDeniedException.getMessage());

		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
//		log.warn("2. CustomAccessDeniedHandler end" + response);
		response.getWriter().write(objectMapper.writeValueAsString(body));

	}
}