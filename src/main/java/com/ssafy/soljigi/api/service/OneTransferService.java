package com.ssafy.soljigi.api.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ssafy.soljigi.api.dto.request.OneTransferRequest;
import com.ssafy.soljigi.api.dto.request.SearchTransactionRequest;
import com.ssafy.soljigi.api.dto.response.OneTransferMemoResponse;
import com.ssafy.soljigi.api.dto.response.OneTransferResponse;
import com.ssafy.soljigi.api.entity.Account;
import com.ssafy.soljigi.api.entity.Transaction;
import com.ssafy.soljigi.api.repository.AccountRepository;
import com.ssafy.soljigi.api.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OneTransferService {

	private final AccountRepository accountRepository;
	private final TransactionRepository transactionRepository;

	public OneTransferResponse oneTransfer(OneTransferRequest.DataBody dataBody) {
		String accountNumber = dataBody.getAccountNumber();
		Account account = accountRepository.findByAccountNumber(accountNumber)
			.orElseThrow(IllegalArgumentException::new);
		account.addBalance(1);

		transactionRepository.save(Transaction.builder()
			.account(account)
			.transactionDateTime(LocalDateTime.now())
			.balance(account.getBalance())
			.deposit(1)
			.withdraw(0)
			.inOutType(1)
			.content(generateNumber())
			.summary("1원 인증")
			.branchName("1원 인증")
			.build());
		return OneTransferResponse.of(dataBody.getBankCode(), accountNumber);
	}

	public OneTransferMemoResponse getLatestOneTransferMemo(SearchTransactionRequest.DataBody dataBody) {
		Account account = accountRepository.findByAccountNumber(dataBody.getAccountNumber())
			.orElseThrow(IllegalArgumentException::new);

		String content = transactionRepository.findLatestOneTransfer(account)
			.orElseThrow(IllegalArgumentException::new)
			.getContent();
		return OneTransferMemoResponse.of(content);
	}

	private String generateNumber() {
		return (int)(Math.random() * 8999) + 1000 + "";
	}
}
