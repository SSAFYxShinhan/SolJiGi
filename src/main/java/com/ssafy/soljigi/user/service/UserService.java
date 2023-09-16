package com.ssafy.soljigi.user.service;

import com.ssafy.soljigi.user.dto.response.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
	UserDetailsService userDetailsService();

	UserDto findUserInfo(Long id);
}
