package com.ssafy.soljigi.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.soljigi.user.dto.request.SignUpRequest;
import com.ssafy.soljigi.user.dto.request.SigninRequest;
import com.ssafy.soljigi.user.dto.response.JwtAuthenticationResponse;
import com.ssafy.soljigi.user.dto.response.Response;
import com.ssafy.soljigi.user.service.AuthenticationService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationService authenticationService;

	@PostMapping("/signup")
	public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody SignUpRequest request) {
		log.warn("1. AuthenticationController : signup " + request.getUsername() + " " + request.getPassword());
		return ResponseEntity.ok(authenticationService.signUp(request));
	}

	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody SigninRequest request,
		HttpServletResponse response) {
		log.warn("1. AuthenticationController : signin " + request.getUsername() + " " + request.getPassword());
		JwtAuthenticationResponse jwt = authenticationService.signIn(request);
		Cookie cookie = new Cookie("token", "Bearer+" + jwt.getToken());
		cookie.setPath("/");
		cookie.setMaxAge(30 * 60);
		// cookie.setSecure(true);

		cookie.setHttpOnly(true);
		response.setHeader("token", cookie.toString());
		response.addCookie(cookie);
		return ResponseEntity.ok().body(Response.success(cookie));
	}
}