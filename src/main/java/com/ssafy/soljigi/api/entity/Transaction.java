package com.ssafy.soljigi.api.entity;

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
	private String transactionDate;
	private String transactionTime;
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

	@Builder
	public Transaction(String transactionDate, String transactionTime, String summary, long withdraw, long deposit,
		String content, long balance, int inOutType, String branchName, Account account) {
		this.transactionDate = transactionDate;
		this.transactionTime = transactionTime;
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

	protected Transaction() {

	}
}