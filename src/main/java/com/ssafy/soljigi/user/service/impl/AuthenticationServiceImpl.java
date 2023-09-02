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
import com.ssafy.soljigi.user.error.AppException;
import com.ssafy.soljigi.user.error.ErrorCode;
import com.ssafy.soljigi.user.repository.UserRepository;
import com.ssafy.soljigi.user.service.AuthenticationService;
import com.ssafy.soljigi.user.service.JwtService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationServiceImpl implements AuthenticationService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	@Override
	@Transactional
	public JwtAuthenticationResponse signup(SignUpRequest request) {
		log.info("signup service : " + request);
		userRepository.findByUsername(request.getUsername())
			.ifPresent(user -> {
				throw new AppException(ErrorCode.DUPLICATED_USERNAME);
			});

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
		log.info("signin service : " + request);
		var user = userRepository.findByUsername(request.getUsername())
			.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		var jwt = jwtService.generateToken(user);
		return JwtAuthenticationResponse.builder().token(jwt).build();
	}
}