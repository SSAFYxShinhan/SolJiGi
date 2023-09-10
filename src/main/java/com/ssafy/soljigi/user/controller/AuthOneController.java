package com.ssafy.soljigi.user.controller;

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

	@PostMapping("/one")
	public ResponseEntity<?> authOne(@RequestBody AuthOneRequest request) {

		return ResponseEntity.ok().body(authOneService.authOne(request.getAccountNumber()));
	}
}
