package com.ssafy.soljigi.user.config.custom;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.soljigi.user.dto.response.ErrorResponse;
import com.ssafy.soljigi.user.dto.response.Response;
import com.ssafy.soljigi.user.error.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
		AccessDeniedException accessDeniedException) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		Response<ErrorResponse> error = Response.error(ErrorCode.INVALID_TOKEN);

		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		log.error(response);
		response.getWriter().write(objectMapper.writeValueAsString(error));

	}
}