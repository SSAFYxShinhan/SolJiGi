package com.ssafy.soljigi.api.service;

import org.springframework.stereotype.Service;

import com.ssafy.soljigi.api.dto.request.SearchTransactionRequest;
import com.ssafy.soljigi.api.dto.response.AccountTransactionResponse;
import com.ssafy.soljigi.api.entity.Account;
import com.ssafy.soljigi.api.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

	private final AccountRepository accountRepository;

	public AccountTransactionResponse search(SearchTransactionRequest.DataBody dataBody) {
		Account account = accountRepository.findByAccountNumber(dataBody.getAccountNumber())
			.orElseThrow(IllegalArgumentException::new);
		return AccountTransactionResponse.of(account);
	}
}
