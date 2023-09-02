package com.ssafy.soljigi.user.config.custom;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.ssafy.soljigi.user.error.ErrorCode;
import com.ssafy.soljigi.user.service.impl.JwtServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
		AccessDeniedException accessDeniedException) throws
		IOException,
		ServletException,
		IOException {
		// 4. 토큰 인증 후 권한 거부
		ErrorCode errorCode = ErrorCode.FORBIDDEN_REQUEST;
		JwtServiceImpl.MakeError(response, errorCode);
	}
}