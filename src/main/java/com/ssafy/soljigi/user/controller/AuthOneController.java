package com.ssafy.soljigi.user.controller;

import com.ssafy.soljigi.user.dto.response.JwtAuthenticationResponse;
import com.ssafy.soljigi.user.service.UserService;
import com.ssafy.soljigi.user.service.impl.AuthenticationServiceImpl;
import com.ssafy.soljigi.user.service.impl.UserServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.soljigi.user.dto.request.AuthOneRequest;
import com.ssafy.soljigi.user.service.impl.AuthOneService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthOneController {
	private final AuthOneService authOneService;
	private final AuthenticationServiceImpl authenticationService;
	@PostMapping("/one")
	public ResponseEntity<?> authOne(@RequestBody AuthOneRequest request) {

		return ResponseEntity.ok().body(authOneService.authOne(request.getAccountNumber()));
	}

	@PostMapping("/fake")
	public ResponseEntity<?> makeFakeUser(@RequestBody AuthOneRequest request, HttpServletResponse response){
		JwtAuthenticationResponse jwtAuthenticationResponse = authenticationService.makeFakeUser(request);
		Cookie cookie = new Cookie("token", "Bearer+" + jwtAuthenticationResponse.getToken());
		cookie.setPath("/");
		cookie.setMaxAge(30 * 60);
		// cookie.setSecure(true);

		cookie.setHttpOnly(true);
		response.setHeader("token", cookie.toString());
		response.addCookie(cookie);
		return ResponseEntity.ok().body(jwtAuthenticationResponse.getToken());
	}
}
