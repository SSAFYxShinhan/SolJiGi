package com.ssafy.soljigi.user.service;

import com.ssafy.soljigi.user.dto.request.SignUpRequest;
import com.ssafy.soljigi.user.dto.request.SigninRequest;
import com.ssafy.soljigi.user.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
	JwtAuthenticationResponse signUp(SignUpRequest request);

	JwtAuthenticationResponse signIn(SigninRequest request);
}
