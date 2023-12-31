package com.ssafy.soljigi.api.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime transactionDateTime;
	private String summary;
	private long withdraw;
	private long deposit;
	private String content;
	private long balance;
	private int inOutType;
	private String branchName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	private Account account;

	protected Transaction() {

	}

	@Builder
	public Transaction(LocalDateTime transactionDateTime, String summary, long withdraw, long deposit, String content,
		long balance, int inOutType, String branchName, Account account) {
		this.transactionDateTime = transactionDateTime;
		this.summary = summary;
		this.withdraw = withdraw;
		this.deposit = deposit;
		this.content = content;
		this.balance = balance;
		this.inOutType = inOutType;
		this.branchName = branchName;
		setAccount(account);
	}

	public void setAccount(Account account) {
		account.getTransactions().add(this);
		this.account = account;
	}

	public String getTransactionDate() {
		return transactionDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}

	public String getTransactionTime() {
		return transactionDateTime.format(DateTimeFormatter.ofPattern("HHmmss"));
	}
}