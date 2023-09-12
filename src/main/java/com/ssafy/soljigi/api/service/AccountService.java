package com.ssafy.soljigi.api.service;

import org.springframework.stereotype.Service;

import com.ssafy.soljigi.api.dto.response.AccountTransactionResponse;
import com.ssafy.soljigi.api.entity.Account;
import com.ssafy.soljigi.api.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

	private final AccountRepository accountRepository;

	public void saveAccount(Account account) {
		accountRepository.save(account);
	}

	public AccountTransactionResponse get(String accountNumber) {
		Account account = accountRepository.findByAccountNumber(accountNumber)
			.orElseThrow(IllegalArgumentException::new);
		return AccountTransactionResponse.of(account);
	}
}
