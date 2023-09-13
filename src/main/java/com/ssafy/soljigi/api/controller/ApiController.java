package com.ssafy.soljigi.api.controller;

import com.ssafy.soljigi.user.entity.User;
import com.ssafy.soljigi.user.service.UserService;
import com.ssafy.soljigi.user.service.impl.UserServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.soljigi.api.dto.AccountTransactionDto;
import com.ssafy.soljigi.api.dto.request.SearchRequest;
import com.ssafy.soljigi.api.service.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

	private static final String apiKey = "2023_Shinhan_SSAFY_Hackathon";
	private final AccountService accountService;
	private final UserServiceImpl userService;

	@PostMapping("/v1/search/transaction")
	public AccountTransactionDto search(@RequestBody SearchRequest request) {
		log.info("{}", request);
		if (!request.getDataHeader().getApikey().equals(apiKey)) {
			return AccountTransactionDto.ofFail();
		}

		AccountTransactionDto resp = AccountTransactionDto.ofFail();

		try {
			resp = accountService.get(request.getDataBody().getAccountNumber());
		} catch (IllegalArgumentException e) {

		}
		return resp;
	}
}
