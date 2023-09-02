package com.ssafy.soljigi.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.soljigi.user.dto.request.SignUpRequest;
import com.ssafy.soljigi.user.dto.request.SigninRequest;
import com.ssafy.soljigi.user.dto.response.JwtAuthenticationResponse;
import com.ssafy.soljigi.user.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationService authenticationService;

	@PostMapping("/signup")
	public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
		return ResponseEntity.ok(authenticationService.signup(request));
	}

	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
		return ResponseEntity.ok(authenticationService.signin(request));
	}
}