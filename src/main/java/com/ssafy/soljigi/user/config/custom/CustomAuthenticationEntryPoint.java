package com.ssafy.soljigi.user.config.custom;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.soljigi.user.error.AppException;
import com.ssafy.soljigi.user.error.exception.InvalidTokenException;
import com.ssafy.soljigi.user.error.exception.UserNotLoggedInException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException {
		boolean existsToken = (boolean)request.getAttribute("token");

		ObjectMapper objectMapper = new ObjectMapper();

		AppException e = existsToken ? new InvalidTokenException() : new UserNotLoggedInException();

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(objectMapper.writeValueAsString(e));
	}
}