package com.ssafy.soljigi.user.config.custom;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.soljigi.user.dto.response.Response;
import com.ssafy.soljigi.user.error.AppException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JwtExceptionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			filterChain.doFilter(request, response);
		} catch (AppException e) {
			log.warn("ex. JwtExceptionFilter" + " " + e.getErrorCode() + " " + e.getErrorCode().getMessage());
			response.setStatus(e.getErrorCode().getHttpStatus().value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setCharacterEncoding("UTF-8");
			objectMapper.writeValue(response.getWriter(), Response.error(e.getErrorCode()));
		}

	}
}
