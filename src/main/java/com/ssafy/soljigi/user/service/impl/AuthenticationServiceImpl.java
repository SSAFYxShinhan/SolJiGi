package com.ssafy.soljigi.user.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.soljigi.user.dto.request.SignUpRequest;
import com.ssafy.soljigi.user.dto.request.SigninRequest;
import com.ssafy.soljigi.user.dto.response.JwtAuthenticationResponse;
import com.ssafy.soljigi.user.entity.Role;
import com.ssafy.soljigi.user.entity.User;
import com.ssafy.soljigi.user.repository.UserRepository;
import com.ssafy.soljigi.user.service.AuthenticationService;
import com.ssafy.soljigi.user.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	@Override
	public JwtAuthenticationResponse signup(SignUpRequest request) {
		userRepository.findByUsername(request.getUsername()).ifPresent(a -> new IllegalAccessException("message"));
		var user = User.builder()
			.username(request.getUsername())
			.password(passwordEncoder.encode(request.getPassword()))
			.role(Role.USER).build();
		userRepository.save(user);
		var jwt = jwtService.generateToken(user);
		return JwtAuthenticationResponse.builder().token(jwt).build();
	}

	@Override
	public JwtAuthenticationResponse signin(SigninRequest request) {
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		var user = userRepository.findByUsername(request.getUsername())
			.orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
		var jwt = jwtService.generateToken(user);
		return JwtAuthenticationResponse.builder().token(jwt).build();
	}
}