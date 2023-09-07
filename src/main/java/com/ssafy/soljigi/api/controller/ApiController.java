package com.ssafy.soljigi.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.soljigi.api.dto.AccountTransactionDto;
import com.ssafy.soljigi.api.dto.request.SearchRequest;
import com.ssafy.soljigi.api.service.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

	private static final String apiKey = "2023_Shinhan_SSAFY_Hackathon";
	private final AccountService accountService;

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
