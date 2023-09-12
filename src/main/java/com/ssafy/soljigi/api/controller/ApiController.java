package com.ssafy.soljigi.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.soljigi.api.dto.AccountTransactionDto;
import com.ssafy.soljigi.api.dto.request.OneTransferRequest;
import com.ssafy.soljigi.api.dto.request.SearchRequest;
import com.ssafy.soljigi.api.dto.response.OneTransferResponse;
import com.ssafy.soljigi.api.service.AccountService;
import com.ssafy.soljigi.api.service.OneTransferService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Slf4j
public class ApiController {

	private static final String API_KEY = "2023_Shinhan_SSAFY_Hackathon";
	private static final String FAIL_MESSAGE_INVALID_API = "This Api Key is not validate.";
	private static final String FAIL_MESSAGE_INVALID_ACCOUNT = "This Account Number is not validate.";
	private final AccountService accountService;
	private final OneTransferService oneTransferService;

	@PostMapping("/search/transaction")
	public AccountTransactionDto search(@RequestBody SearchRequest request) {
		log.info("{}", request);
		if (!request.getDataHeader().getApikey().equals(API_KEY)) {
			return AccountTransactionDto.ofFail();
		}

		AccountTransactionDto resp = AccountTransactionDto.ofFail();

		try {
			resp = accountService.get(request.getDataBody().getAccountNumber());
		} catch (IllegalArgumentException e) {

		}
		return resp;
	}

	@PostMapping("/auth/1transfer")
	public OneTransferResponse oneTransfer(@RequestBody OneTransferRequest request) {
		if (!request.getDataHeader().getApikey().equals(API_KEY)) {
			return OneTransferResponse.ofFail(FAIL_MESSAGE_INVALID_API);
		}

		OneTransferResponse response;
		try {
			response = oneTransferService.oneTransfer(request.getDataBody());
		} catch (IllegalArgumentException e) {
			return OneTransferResponse.ofFail(FAIL_MESSAGE_INVALID_ACCOUNT);
		}
		return response;
	}
}
