package com.ssafy.soljigi.user.config.custom;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.ssafy.soljigi.user.error.ErrorCode;
import com.ssafy.soljigi.user.service.impl.JwtServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException,
		ServletException {
		final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

		// 1. 토큰 없음 2. 시그니처 불일치
		if (authorization == null || !authorization.startsWith("Bearer ")) {
			log.error("토큰이 존재하지 않거나 Bearer로 시작하지 않는 경우");
			ErrorCode errorCode = ErrorCode.INVALID_TOKEN;
			JwtServiceImpl.MakeError(response, errorCode);

			// 3. 토큰 만료
		} else if (authorization.equals(ErrorCode.EXPIRED_TOKEN)) {
			log.error("토큰이 만료된 경우");
			ErrorCode errorCode = ErrorCode.EXPIRED_TOKEN;
			JwtServiceImpl.MakeError(response, errorCode);
		}
	}
}