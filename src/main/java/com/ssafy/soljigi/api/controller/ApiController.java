package com.ssafy.soljigi.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.soljigi.api.dto.response.AccountTransactionResponse;
import com.ssafy.soljigi.api.dto.request.OneTransferRequest;
import com.ssafy.soljigi.api.dto.request.SearchTransactionRequest;
import com.ssafy.soljigi.api.dto.response.OneTransferMemoResponse;
import com.ssafy.soljigi.api.dto.response.OneTransferResponse;
import com.ssafy.soljigi.api.service.AccountService;
import com.ssafy.soljigi.api.service.OneTransferService;
import com.ssafy.soljigi.base.api.response.ApiResponse;

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
	public AccountTransactionResponse search(@RequestBody SearchTransactionRequest request) {
		if (!request.getDataHeader().getApikey().equals(API_KEY)) {
			return AccountTransactionResponse.ofFail(FAIL_MESSAGE_INVALID_API);
		}

		AccountTransactionResponse response;
		try {
			response = accountService.search(request.getDataBody());
		} catch (IllegalArgumentException e) {
			return AccountTransactionResponse.ofFail(FAIL_MESSAGE_INVALID_ACCOUNT);
		}
		return response;
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

	@PostMapping("/search/1transfer")
	public OneTransferMemoResponse oneTransferMemo(@RequestBody SearchTransactionRequest request) {
		if (!request.getDataHeader().getApikey().equals(API_KEY)) {
			return OneTransferMemoResponse.ofFail(FAIL_MESSAGE_INVALID_API);
		}

		OneTransferMemoResponse response;
		try {
			response = oneTransferService.getLatestOneTransferMemo(request.getDataBody());
		} catch (IllegalArgumentException e) {
			return OneTransferMemoResponse.ofFail(FAIL_MESSAGE_INVALID_ACCOUNT);
		}
		return response;
	}
}
